package spring.web.user;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;

/*
 * 	Web 개발시 가장 많이 쓰이는
 * 	return DataType 인 ModelAndView 와 String 을 기준으로
 *  Method 파라미터를 다양하게 받아 정의하여
 *  다양한 Client Form Data 를 처리 해 보자! 
 * 
 */

@Controller
public class UserController {

	public UserController() {
		System.out.println("==> UserController default Constructor call....");
	}
	
	//=========================================================
	// [ Return DataType 을 ModelAndView 사용 ]
	
	// 00 : 단순 Navigation 기능 / Business Logic 수행없음
	@RequestMapping("/logonViewModelAndView.do")
	public ModelAndView logonViewModelAndView() {

		System.out.println(":: ==>logonViewModelAndView() start....");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logonView.jsp");

		return modelAndView;
	}
	
	// 01 : HttpServletRequest 를 이용한 Client Data 처리, HttpSession 처리 
	@RequestMapping("/logon01.do")
	public ModelAndView logon01 (HttpServletRequest request) {
		
		System.out.println(":: ==> logon01() start....");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		// default : forward 
		modelAndView.setViewName("/user/logonResult.jsp");
		
		// ==> SendRedirect 할 경우 => request scope 에 담긴 것 유지 X 
		//modelAndView.setViewName("redirect:/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 02 : HttpServletRequest, HttpSession 이용 처리 
	@RequestMapping("/logon02.do")
	public ModelAndView logon02 (HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon02() start....");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		// default : forward 
		modelAndView.setViewName("/user/logonResult.jsp");
		
		// ==> SendRedirect 할 경우 => request scope 에 담긴 것 유지 X 
		//modelAndView.setViewName("redirect:/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 03 : HttpServletRequest, HttpSession, @RequestParam 이용 처리 
	@RequestMapping("/logon03.do")
	public ModelAndView logon03 ( @RequestParam("userId") String userId,
									@RequestParam("password") String password,
							HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon03() start....");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 04 : HttpServletRequest, HttpSession 이용 처리
	// 		@ModelAttribute 사용
	//		 - Client Form Data ==> 바인딩 됨!
	//		 - request ObjectScope에 저장됨! 
	@RequestMapping("/logon04.do")
	public ModelAndView logon04 ( @ModelAttribute("user") User user,
							HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon04() start....");
		
		// ==> Client Form Data 가 바인딩된 user instance 에서 value 추출
		// ==> 필요한 ObjectScope 에 저장 (Model/View 연결) 
		request.setAttribute("userId", user.getUserId());
		session.setAttribute("password", user.getPassword());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 05 : @ModelAttribute 사용
	//		 - Client Form Data ==> 바인딩 됨!
	//		 - request ObjectScope에 저장됨! 
	@RequestMapping("/logon05.do")
	public ModelAndView logon05 ( @ModelAttribute("user") User user ) {
		
		System.out.println(":: ==> logon05() start....");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	//=========================================================
	// [ Return DataType 을 String 사용 ]
	
	// 00 : 단순 Navigation 기능 / Business Logic 수행없음
	@RequestMapping("/logonViewString.do")
	public String logonViewString() {
		
		System.out.println(":: ==> logonViewString() start....");
		
		return "/user/logonView.jsp";
	}
	
	// 06 : HttpServletRequest, HttpSession 이용 처리
	//		@ModelAttribute 사용
	//		 - Client Form Data ==> 바인딩 됨!
	//		 - request ObjectScope에 저장됨! 
	//	  : java.util.Map 이용 처리
	//		 - view에서 보여줄 Data(Model) Map 저장
	//		 - request ObjectScope 에 저장됨 
	@RequestMapping("/logon06.do")
	public String logon06 ( @ModelAttribute("user") User user,
							HttpServletRequest request, HttpSession session,
							Map<String, String> map) {
		
		System.out.println(":: ==> logon06() start....");
		
		// ==> Client Form Data 가 바인딩된 user instance 에서 value 추출
		// ==> 필요한 ObjectScope 에 저장 (Model/View 연결) 
		request.setAttribute("userId", user.getUserId());
		session.setAttribute("password", user.getPassword());
		
		// ==> Model/View 연결 (request ObjectScope 저장) 
		map.put("message", "Controller 구현 연습");
		// View 에서 requestScope.message 이렇게 받으면 됨! 
		//	Map 에다가 집어넣은게 requestScope에 들어가 있음!!
		
		//==> Forward 할 경우 
		return "/user/logonResult.jsp";
		
		//==> SendRedirect 할 경우
		//return "redirect:/user/logonResult.jsp";
	}
	
	// 07 : @RequestParam
	//    : org.springframework.ui.Model 이용 처리
	//		- view 보여줄 Data(Model) Map 저장
	// 		- request ObjectScope 에 저장됨 
	@RequestMapping("/logon07.do")
	public String logon07 ( @RequestParam("userId") String userId,
							@RequestParam("password") String password,
							Model model) {
		// Model : Map 의 하위! 둘다 key & value 형태인데
		// Map은 j2se API 이고, Model은 Spring API 임 
		
		System.out.println(":: ==> logon07() start....");
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		
		// ==> Model/View 연결 (request ObjectScope 저장) 
		model.addAttribute("user", user);
		
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		
		model.addAttribute("message", "Controller 구현 연습");
		
		return "/user/logonResult.jsp";
	}

}
