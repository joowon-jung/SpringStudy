package spring.service.lifecycle;

/*
 * 	Bean LifeCycle :: Container가 wiring 작업 통해 instance 생성, 소멸시 까지의 주기
 * 	Bean LifeCycle 참여
 * 		instance 직접 생성을 Factory Method 이용 생성 하려 할 경우.. (예: SingleTon Pattern)
 */

public class LifeCycle02 {
	
	private static LifeCycle02 lifeCycle;

	public LifeCycle02() {
		System.out.println("::"+getClass().getName()+" 디폴트 생성자");
	}
	
	// 인스턴스 한개만 생성하고 싶을 시 이 메소드 사용! 
	public static LifeCycle02 getInstance() {
		System.out.println("\n::LifeCycle02.getInstance()");
		if (lifeCycle == null) {
			lifeCycle = new LifeCycle02(); // 여기서 한번 만들면 계속 만들지 않음 
		}
		return lifeCycle;
	}
}
