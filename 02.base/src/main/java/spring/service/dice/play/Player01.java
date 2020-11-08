package spring.service.dice.play;

import spring.service.dice.DiceA;

/*
 *	FileName : Player01.java
 *	ㅇ DiceA 을 가지고(?, has a :: Association Relationship)있는 Player Object 
 *  ㅇ DiceA 를 사용, 합을 리턴하는 Method Definition 
 */
public class Player01 {
	
	///Field
	private DiceA diceA = new DiceA();
	private int totalValue;
	
	///Constructor Method
	public Player01() {
	}
	
	///Method (getter/setter)
	public DiceA getDiceA() {
		return diceA;
	}
	public void setDiceA(DiceA diceA) {
		this.diceA = diceA;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
	//==> count 만큼 주사위를 굴려서 합을 후하는 행위
	public void playDice(int count){
		
		System.out.println("==>"+getClass().getName()+".playDice() start....");

		for (int i = 0; i < count; i++) {
			diceA.selectedNumber(); // 랜덤한 숫자 찾아내서 DiceA 클래스 안의 필드 value 에 저장 
			System.out.println("::[ "+diceA.getClass().getName()+" ] 의 선택된수 : "+diceA.getValue());
			totalValue += diceA.getValue(); // 숫자 뽑을 때 마다 totalValue에 저장 
		}
		
		System.out.println("==>"+getClass().getName()+".playDice() end....");
	}

}//end of class