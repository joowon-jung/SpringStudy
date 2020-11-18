package spring.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * 	1개의 Controller 를 사용하여 다수의 Request 처리 :: @RequestMapping
 * 	@RequestMapping 선언된 Method 의 다양한 사용법
 * 	@ 의 사용법을 Servlet API 와 비교하여 이해하자 
 */

@Controller
public class TestController {

	public TestController() {
		System.out.println(":: TestController default Constructor call");
	}
	
	// ** Request 당 ~~Action (클래스) 로 대응했던 게 Request 당 "메소드"로 대응하고 있음!
	
	//==> 특정 interface 를 구현하지 않음으로 오버라이딩 할 메소드가 없다
	@RequestMapping("/testView01.do") // Navigation 할 때 ModelAndView 객체까지 가지고 리턴! 
	public ModelAndView testView01() {
		System.out.println("[ testView01() start....]");
		
		//ViewName 값만 갖는 ModelAndView 객체 Return
		return new ModelAndView("/test/testView.jsp");
	}
	
	//==> return Data Type이 Fix 되어 있지 않다
	@RequestMapping("/testView02.do") //그냥 단순 Navigation 만 한 것!
	public String testView02() {
		System.out.println("[ testView02() start....]");
		
		//viewName 를 갖는 String Return
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView03.do") // 값 2개 넘겨주지 않으면 Error 발생 
	public String testView03( @RequestParam("abc") int no, 
								@RequestParam("def") String name) {
		System.out.println("[ testView03() start....]");
		
		System.out.println("no : " + no + "=== name : " + name);
		
		//viewName 를 갖는 String Return
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView04.do")
	public String testView04( @RequestParam(value="abc") int no, 
								@RequestParam(value="def", required=false) String name) {
									// required=false : 값 넘겨주지 않아도(null 이여도) 에러 X 
		System.out.println("[ testView04() start....]");
		
		System.out.println("no : " + no + "=== name : " + name);
		
		//viewName 를 갖는 String Return
		return "/test/testView.jsp";
	}
	
	@RequestMapping("/testView05.do")
														// 값 넘겨주지 않으면(null 이면) 1을 줌! 
	public String testView05( @RequestParam(value="abc", defaultValue="1") int no, // listUser 에서 null 일때 1 줬던 것과 같은 논리! 
								@RequestParam(value="def", required=false) String name) {
		System.out.println("[ testView05() start....]");
		
		System.out.println("no : " + no + "=== name : " + name);
		
		//viewName 를 갖는 String Return
		return "/test/testView.jsp";
	}
	
}
