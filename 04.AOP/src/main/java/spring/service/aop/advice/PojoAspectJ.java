package spring.service.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class PojoAspectJ {
	
	public PojoAspectJ() {
		System.out.println(":: PojoAspectJ Default Constructor");
	}

	public void before(JoinPoint joinPoint) {
		
		System.out.println("[before LOG] " + getClass()+".before() start.....");
		System.out.println("[before LOG] Target Object : " + joinPoint.getTarget().getClass().getName());
		System.out.println("[before LOG] Target Object method : " + joinPoint.getSignature().getName());
		
		//==> ���� �� ���� Ȯ��
		if (joinPoint.getArgs().length != 0) {
			System.out.println("[before LOG] Target Object Call : " 
								 + "method argument" + joinPoint.getArgs()[0]);
		}
		
		System.out.println("[before LOG] " + getClass()+".before() end.....");
	}
	
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		
		System.out.println("[after LOG] " + getClass()+".afterReturning() start.....");
		System.out.println("[after LOG] Ÿ�� ��ü : " + joinPoint.getTarget().getClass().getName());
		System.out.println("[after LOG] Ÿ�� ��ü�� ȣ��� method : " + joinPoint.getSignature().getName());
		System.out.println("[after LOG] Ÿ�� ��ü�� ȣ���� return value : " + returnValue);
		System.out.println("[after LOG] " + getClass()+".afterReturning() end.....");
	}
	
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("[Around before] " + getClass()+".invoke() start.....");
		System.out.println("[Around before] Ÿ�� ��ü : " + joinPoint.getTarget().getClass().getName());
		System.out.println("[Around before] Ÿ�� ��ü�� ȣ�� �� method : " + joinPoint.getSignature().getName());
		if (joinPoint.getArgs().length != 0) {
			System.out.println("[Around before] Ÿ�� ��ü�� ȣ���� " 
								+ "method�� ���޵Ǵ� ���� : " + joinPoint.getArgs()[0]);
		}

		//==> Ÿ�� ��ü�� Method �� ȣ���ϴ� �κ�
		Object obj = joinPoint.proceed();
		
		System.out.println("[Around after] Ÿ�� ��ü�� ȣ���� return value : " + obj);
		System.out.println("[Around after] " + getClass()+".invoke() end.....");
		
		return obj;
	}
	
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		
		System.out.println("[exception] "+ getClass()+".afterThrowing() start.....");
		System.out.println("[exception] Ÿ�� ��ü : " + joinPoint.getTarget().getClass().getName());
		System.out.println("[exception] Ÿ�� ��ü�� ȣ�� �� method : " + joinPoint.getSignature().getName());
		System.out.println("[exception] Exception �߻�....");
		System.out.println("[exception] Exception Message : " + throwable.getMessage());
		System.out.println("[exception] "+getClass()+".afterThrowing() end.....");
	}

}
