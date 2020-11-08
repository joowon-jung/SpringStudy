package spring.service.dice.play;

import spring.service.dice.Dice;

/*
 * 
 * 	[ Field 에 interface Dice dice 선언만 했음 ]
 * 	- Association Relation 을 interface 기반으로 변경함으로 유연한 구조로 변경
 *    => 특정 객체와 유연한 결합! ( Loose Coupling )
 *  - interface Dice 만 선언 함으로 하위 객체인 DiceAImpl, B, C 등 is - a 관계를 갖는 객체는 모두 사용 가능
 *  - 선언만 하였으며, 어떤 구현체를 사용 할 지 결정 되어 있지 않으며
 *    생성자를 통해 생성시 구현객체를 받거나 (constructor injection)
 *    setter method를 통해 구현체를 받아 (setter injection) Dice를 사용할 수 있다.
 *  - 어떤 Dice가 주입될 지 모르며, 알 필요 또한 없다 (Encapsulation / Polymorphism )
 *  
 */
public class Player02 {

	/// Field
	private Dice dice;
	private int totalValue;

	/// Constructor Method
	public Player02() {
		System.out.println("::"+getClass().getName()+" 디폴트 생성자....");
	}

	public Player02(Dice dice) {
		System.out.println("::"+getClass().getName()+" Dice 인스턴스를 인자로 받는 생성자....");
		this.dice = dice;
	}
	
	/// Method (getter/setter)
	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		System.out.println("::"+getClass().getName()+" setDice()...");
		this.dice = dice; // 인스턴스 세팅 
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	// ==> count 만큼 주사위를 굴려서 합을 후하는 행위
	public void playDice(int count) {

		System.out.println("==>" + getClass().getName() + ".playDice() start....");

		for (int i = 0; i < count; i++) {
			dice.selectedNumber();
			System.out.println("::[ " + dice.getClass().getName() + " ] 의 선택된수 : " + dice.getValue());
			totalValue += dice.getValue();
		}

		System.out.println("==>" + getClass().getName() + ".playDice() end....");
	}
}
