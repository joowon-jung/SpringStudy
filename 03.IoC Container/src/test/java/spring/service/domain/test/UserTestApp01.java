package spring.service.domain.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import spring.service.domain.User;

public class UserTestApp01 {

	public static void main(String[] args) {
		
//		BeanFactory factory =
//				new XmlBeanFactory( new FileSystemResource
//									("./src/main/resources/config/userservice01.xml") );
		
		// ApplicationContext : BeanFactory를 확장해서 더 많은 기능을 제공해준다! => 현업 사용 
		// 어떤 기능이 추가 ? [ pre-loading ] xml에 wiring 하는 순간 인스턴스가 다 생성됨!
		// 인스턴스는 wiring 시 미리 다 생성해놓고, getBean 할 때 값이 세팅되는 것임 
		ApplicationContext factory = new ClassPathXmlApplicationContext("/config/userservice01.xml");
		
		System.out.println("\n========================");
		User user01 = (User) factory.getBean("user01");
		System.out.println(user01);
		
		System.out.println("\n========================");
		User user02 = (User) factory.getBean("user02");
		System.out.println(user02);
		
		System.out.println("\n========================");
		User user03 = (User) factory.getBean("user03");
		System.out.println(user03);
		
		System.out.println("\n========================");
		User user04 = (User) factory.getBean("user04");
		System.out.println(user04);
		
		System.out.println("\n========================");
		User user05 = (User) factory.getBean("user05");
		System.out.println(user05);
		System.out.println("\n========================");
	}

}
