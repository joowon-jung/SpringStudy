package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/*
 * 	Advice :: 선 처리 
 */
public class BeforeLogAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("\n=====================================");
		System.out.println("[before LOG] " + getClass()+".before() start.....");
		System.out.println("[before LOG] targetObject call Method : " + method);
		if (args.length != 0) {
			System.out.println("[before LOG] targetObject method 전달 argument : " + args[0]);
		}
		System.out.println("[before LOG] " + getClass()+".before() end.....");
	}

}
