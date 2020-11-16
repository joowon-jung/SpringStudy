package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class TestAdvice implements MethodBeforeAdvice, 
									AfterReturningAdvice,
									ThrowsAdvice, 
									MethodInterceptor {

	@Override
	//==> Target Object Method 호출 전 :: before() Method 구현 
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("[before LOG] " + getClass()+".before() start.....");
		System.out.println("[before LOG] targetObject call Method : " + method);
		if (args.length != 0) {
			System.out.println("[before LOG] targetObject method 전달 argument : " + args[0]);
		}
		System.out.println("[before LOG] " + getClass()+".before() end.....");
	}
	
	@Override
	//==> Target Object Method 호출 후 :: afterReturning() Method 구현 
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("[after LOG] " + getClass()+".afterReturning() start.....");
		System.out.println("[after LOG] targetObject call Method : " + method);
		System.out.println("[after LOG] target 객체의 호출후 return value : " + returnValue);
		System.out.println("[after LOG] " + getClass()+".afterReturning() end.....");
	}
	
	@Override
	//==> Target Object Method 호출 전/후 :: invoke() Method 구현 
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println("[Around before] " + getClass()+".invoke() start.....");
		System.out.println("[Around before] targetObject call Method : " + invocation.getMethod());
		
		if (invocation.getArguments().length != 0) {
			System.out.println("[Around before] targetObject method 전달 argument : " + invocation.getArguments()[0]);
		}
		//==> targetObject Method call
		Object obj = invocation.proceed();
		
		System.out.println("[Around after] target 객체의 호출후 return value : " + obj);
		System.out.println("[Around after] " + getClass()+".invoke() end.....");
		
		return obj;
	}
	
	//==> Target Object Method 호출 Exception 발생 :: afterThrowing() Method 구현 
	public void afterThrowing(Throwable throwable) {
		
		System.out.println("[exception] "+ getClass()+".afterThrowing() start.....");
		System.out.println("[exception] Exception 발생...");
		System.out.println("[exception] Exception Message : " + throwable.getMessage());
		System.out.println("[exception] "+getClass()+".afterThrowing() end.....");
	}

}
