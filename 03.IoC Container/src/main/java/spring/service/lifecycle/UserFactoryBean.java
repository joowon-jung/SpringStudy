package spring.service.lifecycle;

import org.springframework.beans.factory.FactoryBean;

import spring.service.domain.User;

/*
 * 	Bean LifeCycle :: Container가 wiring 작업 통해 instance 생성, 소멸시 까지의 주기
 * 	Bean LifeCycle 참여
 * 		Spring 에서 제공하는 interface / API 를 이용 Factory Pattern 구현 가능 
 */
public class UserFactoryBean implements FactoryBean {
								// Spring API는 FactoryBean을 구현하고 있는 게 많다! 
	public UserFactoryBean() {
		System.out.println("::" + getClass().getName() + " 디폴트 생성자");
	}
	
	@Override
	public User getObject() throws Exception {
		System.out.println("::" + getClass().getName() + " .getObject()");
		return new User();
	}

	@Override
	public Class getObjectType() {
		System.out.println("::" + getClass().getName() + " .getObjectType()");
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		System.out.println("::" + getClass().getName() + " .isSingleton()");
		return true; // true면 Singleton : 인스턴스 하나만 생성 
		//return false; // false면 prototype : 계속 인스턴스 생성 
	}

}