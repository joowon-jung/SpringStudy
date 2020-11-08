package spring.service.dice.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import spring.service.dice.impl.DiceAImpl;
import spring.service.dice.impl.DiceBImpl;
import spring.service.dice.play.Player02;

/*
 * Bean / IoC Container(Spring Framework 제공) 을 사용
 * - instance 생성 / DI (setter Injection / constructor injection) 을 메타데이터에
 * 	 서술적, 선언적 기술하고, 필요 instance를 Lookup 사용
 */
public class DiceTestAppUseSpring {

	public static void main(String[] args) {
		
		// 1. BeanFactory ( Bean/IoC Container)을 이용 Meta-Data에 선언적, 서술적으로 선언된
		// 내용을 통해 instance 생성 및 Dependency Injection 의 일련의 과정 수행 
		BeanFactory factory = 
				new XmlBeanFactory ( new FileSystemResource 
									("./src/main/resources/config/diceservice.xml") );
		
		// 2. Container 로 부터 player01 의 id를 갖는 instance Lookup
		Player02 player01 = (Player02) factory.getBean("player01");
		player01.playDice(3);
		System.out.println("==================");
		System.out.println("선택된 주사위 수의 통합은 : " + player01.getTotalValue());
		System.out.println("==================\n\n");
		
		// 3. Container 로 부터 player02 의 id를 갖는 instance Lookup
		Player02 player02 = (Player02) factory.getBean("player02");
		//Player02 player02 = new Player02();
		//player02.setDice(new DiceBImpl());
		player02.playDice(3);
		System.out.println("==================");
		System.out.println("선택된 주사위 수의 통합은 : " + player02.getTotalValue());
		System.out.println("==================\n\n");
		
		// 4. Container 로 부터 player03 의 id를 갖는 instance Lookup
		Player02 player03 = (Player02) factory.getBean("player03");
		player03.playDice(3);
		System.out.println("==================");
		System.out.println("선택된 주사위 수의 통합은 : " + player03.getTotalValue());
		System.out.println("==================\n\n");
		
		// 5. Container 로 부터 player04 의 id를 갖는 instance Lookup
		Player02 player04 = (Player02) factory.getBean("player04");
		//Player02 player01 = new Player02(new DiceAImpl());
		player04.playDice(3);
		System.out.println("==================");
		System.out.println("선택된 주사위 수의 통합은 : " + player04.getTotalValue());
		System.out.println("==================\n\n");
	}
}
