package com.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	// This method has to run on a call of something else 
	// (before getName() method of Circle class):
	@Before("execution(public String com.demo.model.Circle.getName())")
	public void LoggingAdvice(){
		System.out.println("Advice run. Get method called.");
	}
	
	// Wild cards, for every getter 
	// with zero or any number of arguments:
	@Before("execution(public * get*(..))")
	public void LoggingAdvice2(){
		System.out.println("Advice run. Get method called.");
	}
	
	// Wild cards, for every getter 
	// with any number of arguments (at least one):
	@Before("execution(public * get*(*))")
	public void LoggingAdvice3(){
		System.out.println("Advice run. Get method called.");
	}
	
	//
	// *****
	// "execution(public * get*(*))" is a pointcut for LoggingAdvice method.
	// To define a pointcut separetely to be used for several methods:
	@Pointcut("execution(public * get*(*))")
	public void allGetters(){}
	// This dummi method holds a pointcut expression.
	
	// Now this dummi method can be used as a pointcut for many methods:
	@Before("allGetters()")
	public void LoggingAdvice4(){
		System.out.println("Advice run. Get method called.");
	}
	
	@Before("allGetters()")
	public void LoggingAdvice5(){
		System.out.println("Advice run. Get method called.");
	}
	// *****
	
	// A pointcut for all methods in a particular class:
	@Pointcut("execution(public void com.demo.model.Circle.getName())")
	public void allCircleMethods(){}
	
	@Before("allCircleMethods()")
	public void LoggingAdvice6(){
		System.out.println("Advice run. Get method called.");
	}
	
	// Another way to say that a pointcut should apply for any method in a class:
	@Pointcut("within(com.demo.model.Circle)")
	public void AllCircleMethods2(){}
	
	// To apply a pointcut to all classes and methods in a package:
	@Pointcut("within(com.demo.model.*)")
	public void AllPackageMethods(){}
	
	// To apply a pointcut to all classes and methods in a package and subpackages:
	@Pointcut("within(com.demo.model..*)")
	public void AllSubPackageMethods(){}
	
	// To apply several pointcuts for an advice (&&):
	@Before("allCircleMethods() && AllPackageMethods()")
	public void LoggingAdvice7(){
		System.out.println("Advice run. Get method called.");
	}
	
	// Around:
	@Around("allGetters()")
	public void myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

		try {
			System.out.println("Before:");
			proceedingJoinPoint.proceed(); // this target method call can be skipped
			System.out.println("After");
		} catch (Throwable e) {
			System.out.println("After");
		} 
		
	}
}
