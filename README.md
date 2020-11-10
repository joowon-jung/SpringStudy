# SpringStudy

> Spring Framework 공부

# 02.base

Bean / Meta-Data / App(Test) 분류하여 패키지 관리

기존의 시스템의 문제점을 찾고 Spring의 Bean Container & IoC Container가 왜 필요한지 이해

---

- [Hello.java](http://hello.java) / [HelloTestApp01.java](http://hellotestapp01.java)

AS-IS | 기존의 시스템의 문제 : 클래스간 커플링 관계가 발생

1. new라는 키워드를 이용하여 인스턴스 생성하므로 다른 Bean 사용하려면 다시 코딩 후 컴파일 과정 필요
2. 출력할 message 하드 코딩 되어있어 다른 message 를 출력하고자 한다면 다시 코딩 후 컴파일 과정 필요

TO-BE | 문제점을 Framework을 적용하여 하드코딩된 부분을 Meta Data 에 기술하고, 저장된 내용을 읽어 수행하는 구조로 변경. 요구사항이 변경됐을 시 Meta Data 만 수정하면 됨 ! ⇒ 유지관리가 용이

- [Hello.java](http://hello.java) / [HelloFactory.java](http://hellofactory.java) / [HelloTestApp01.java](http://hellotestapp01.java) / [hello.properties](http://hello.properties)

사용할 Bean 을 hello.properties(meta-data)에 저장. instance 를 new라는 키워드를 이용하여 직접 생성하지 않으며, HelloFactory 에서 생성 & return 되는 instance 사용.

- [Hello.java](http://hello.java) / [HelloTestApp03UseSpring.java](http://hellotestapp03usespring.java) **(Spring Framework 사용)**

Spring Framework 에서 제공하는 API를 사용하여 instance 생성. Spring Framework 은 다양한 메타데이터 중 xml 을 일반적으로 사용하며, instance 생성, 초기화, 객체의 상호 관계를 xml 에 선언적으로 기술.

- [DiceA.java](http://dicea.java) / [DiceB.java](http://diceb.java) / [DiceC.java](http://dicec.java) / [Player01.java](http://player01.java) / [DiceTestApp01.java](http://dicetestapp01.java)

AS-IS | 다른 주사위로 game 을 원할 경우, new라는 키워드로 인스턴스 생성 한 코딩을 수정해야 한다. 클래스간 커플링 관계 발생. 

TO-BE | 

1. Player 가 사용할 주사위 객체를 직접 생성 X, 외부로 부터 사용할 주사위를 주입 받는다면? (Injection)
2. Player 는 주사위 사용법을 알고 있으면 됨. 구체적인 DiceA / DiceB / DiceC 는 중요한 사항이 아니다. Polymorphism, Encapsulation 활용 → 구체적인 주사위를 선택 X 주사위 사용법만 알고 있게 하기

- [DiceAImpl.java](http://diceaimpl.java) / [DiceBImpl.java](http://dicebimpl.java) / [DiceCImpl.java](http://dicecimpl.java) / [Dice.java](http://dice.java) / [Player02.java](http://player02.java) / [DiceTestApp02.java](http://dicetestapp02.java)

Association Relation 을 interface 기반으로 변경함으로 유연한 구조로 변경. ⇒ Loose Coupling. 필드에 인터페이스를 선언만 하고 어떤 구현체를 사용 할 지 결정 되어 있지 않으며 생성자를 통해 생성시 구현객체를 받거나 (constructor injection) & setter method를 통해 구현체를 받아 (setter injection) Dice를 사용할 수 있다.

- [DiceAImpl.java](http://diceaimpl.java) / [DiceBImpl.java](http://dicebimpl.java) / [DiceCImpl.java](http://dicecimpl.java) / [Dice.java](http://dice.java) / [Player02.java](http://player02.java) / [DiceTestAppUseSpring.java](http://dicetestappusespring.java) **(Spring Framework 사용)**

instance 생성 / DI (setter Injection / constructor injection) 을 메타데이터에 서술적, 선언적 기술하고, 필요한 instance를 Lookup 사용
