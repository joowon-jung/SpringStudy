package spring.service.lifecycle;

import org.springframework.beans.factory.BeanNameAware;

/*
 * 	Bean LifeCycle :: Container가 wiring 작업 통해 instance 생성, 소멸시 까지의 주기
 * 	Bean LifeCycle 참여
 * 		생성시 생성자 이외에 추가적 초기화 작업을 해야 할 경우,
 * 		instance 소멸시 추가적 작업을 해야 할 경우,
 * 		instance 생성시 Container 에게 일련의 정보를 공유 하여야 할 경우 등
 * 		=> Spring Framework와 커플링 관계 발생하기 때문에 사용 잘 안 함
 */
public class LifeCycle01 implements BeanNameAware {

	public LifeCycle01() {
		System.out.println("\n::"+getClass().getName()+" 디폴트 생성자");
	}
	
	@Override
	// ==> ~~Aware interface 구현으로 반드시 OverRiding
	public void setBeanName(String name) {
		System.out.println("\n::"+getClass().getName()+" setBeanName() start");
		System.out.println("xml에 정의된 beanName : " + name);
		System.out.println("::"+getClass().getName()+" setBeanName() end");
	}
	
	public void init() {
		System.out.println("::"+getClass().getName()+".init()");
	}

	private void destroy() {
		System.out.println("::"+getClass().getName()+".destroy()");
	}
}
