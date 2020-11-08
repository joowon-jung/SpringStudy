package spring.service.dice;

/*
 * FileName : DiceA.java
 * ㅇ Contract (계약 ? 기능에 대한 규약) 를 갖는 interface declaration
 * ㅇ Polymorphism // ==> interface 기반 Programming
 */
public interface Dice {

	///Method
	//==> value 를 return 하는 Method declaration
	public int getValue();
	
	//==> 주사위를 던져 선택되는 숫자를 생산하는 Method declaration
	public void selectedNumber();
}
