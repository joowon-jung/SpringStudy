package spring.service.dice.test;

import spring.service.dice.impl.DiceAImpl;
import spring.service.dice.impl.DiceBImpl;
import spring.service.dice.play.Player02;

public class DiceTestApp02 {

	public static void main(String[] args) {

		// ==> 생성자 이용
		// ==> 사용할 Dice 인 DiceAImpl 인스턴스를 Player02 생성시 전달 (injection)
		// ==> Constructor Injection 을 통한 DiceAImpl을 사용하는 Player02 instance 생성
		Player02 player01 = new Player02(new DiceAImpl());
		player01.playDice(3);
		System.out.println("=======================");
		System.out.println("선택된 주사위 수의 통합은 : " + player01.getTotalValue());
		System.out.println("=======================\n\n");

		// ==> setter method 이용
		// ==> 사용할 Dice 인 DiceBImpl 인스턴스를 Player02 생성 후 전달 (injection)
		// ==> Setter Injection 을 통한 DiceBImpl 을 사용하는 Player02 instance 생성
		Player02 player02 = new Player02();
		player02.setDice(new DiceBImpl());
		player02.playDice(3);
		System.out.println("=======================");
		System.out.println("선택된 주사위 수의 통합은 : " + player02.getTotalValue());
		System.out.println("=======================\n\n");

	}
}
