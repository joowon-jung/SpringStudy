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
 * 	Web ���߽� ���� ���� ���̴�
 * 	return DataType �� ModelAndView �� String �� ��������
 *  Method �Ķ���͸� �پ��ϰ� �޾� �����Ͽ�
 *  �پ��� Client Form Data �� ó�� �� ����! 
 * 
 */

@Controller
public class UserController {

	public UserController() {
		System.out.println("==> UserController default Constructor call....");
	}
	
	//=========================================================
	// [ Return DataType �� ModelAndView ��� ]
	
	// 00 : �ܼ� Navigation ��� / Business Logic �������
	@RequestMapping("/logonViewModelAndView.do")
	public ModelAndView logonViewModelAndView() {

		System.out.println(":: ==>logonViewModelAndView() start....");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logonView.jsp");

		return modelAndView;
	}
	
	// 01 : HttpServletRequest �� �̿��� Client Data ó��, HttpSession ó�� 
	@RequestMapping("/logon01.do")
	public ModelAndView logon01 (HttpServletRequest request) {
		
		System.out.println(":: ==> logon01() start....");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller ���� ����");
		
		// default : forward 
		modelAndView.setViewName("/user/logonResult.jsp");
		
		// ==> SendRedirect �� ��� => request scope �� ��� �� ���� X 
		//modelAndView.setViewName("redirect:/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 02 : HttpServletRequest, HttpSession �̿� ó�� 
	@RequestMapping("/logon02.do")
	public ModelAndView logon02 (HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon02() start....");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller ���� ����");
		
		// default : forward 
		modelAndView.setViewName("/user/logonResult.jsp");
		
		// ==> SendRedirect �� ��� => request scope �� ��� �� ���� X 
		//modelAndView.setViewName("redirect:/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 03 : HttpServletRequest, HttpSession, @RequestParam �̿� ó�� 
	@RequestMapping("/logon03.do")
	public ModelAndView logon03 ( @RequestParam("userId") String userId,
									@RequestParam("password") String password,
							HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon03() start....");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller ���� ����");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 04 : HttpServletRequest, HttpSession �̿� ó��
	// 		@ModelAttribute ���
	//		 - Client Form Data ==> ���ε� ��!
	//		 - request ObjectScope�� �����! 
	@RequestMapping("/logon04.do")
	public ModelAndView logon04 ( @ModelAttribute("user") User user,
							HttpServletRequest request, HttpSession session) {
		
		System.out.println(":: ==> logon04() start....");
		
		// ==> Client Form Data �� ���ε��� user instance ���� value ����
		// ==> �ʿ��� ObjectScope �� ���� (Model/View ����) 
		request.setAttribute("userId", user.getUserId());
		session.setAttribute("password", user.getPassword());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller ���� ����");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	// 05 : @ModelAttribute ���
	//		 - Client Form Data ==> ���ε� ��!
	//		 - request ObjectScope�� �����! 
	@RequestMapping("/logon05.do")
	public ModelAndView logon05 ( @ModelAttribute("user") User user ) {
		
		System.out.println(":: ==> logon05() start....");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller ���� ����");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	//=========================================================
	// [ Return DataType �� String ��� ]
	
	// 00 : �ܼ� Navigation ��� / Business Logic �������
	@RequestMapping("/logonViewString.do")
	public String logonViewString() {
		
		System.out.println(":: ==> logonViewString() start....");
		
		return "/user/logonView.jsp";
	}
	
	// 06 : HttpServletRequest, HttpSession �̿� ó��
	//		@ModelAttribute ���
	//		 - Client Form Data ==> ���ε� ��!
	//		 - request ObjectScope�� �����! 
	//	  : java.util.Map �̿� ó��
	//		 - view���� ������ Data(Model) Map ����
	//		 - request ObjectScope �� ����� 
	@RequestMapping("/logon06.do")
	public String logon06 ( @ModelAttribute("user") User user,
							HttpServletRequest request, HttpSession session,
							Map<String, String> map) {
		
		System.out.println(":: ==> logon06() start....");
		
		// ==> Client Form Data �� ���ε��� user instance ���� value ����
		// ==> �ʿ��� ObjectScope �� ���� (Model/View ����) 
		request.setAttribute("userId", user.getUserId());
		session.setAttribute("password", user.getPassword());
		
		// ==> Model/View ���� (request ObjectScope ����) 
		map.put("message", "Controller ���� ����");
		// View ���� requestScope.message �̷��� ������ ��! 
		//	Map ���ٰ� ��������� requestScope�� �� ����!!
		
		//==> Forward �� ��� 
		return "/user/logonResult.jsp";
		
		//==> SendRedirect �� ���
		//return "redirect:/user/logonResult.jsp";
	}
	
	// 07 : @RequestParam
	//    : org.springframework.ui.Model �̿� ó��
	//		- view ������ Data(Model) Map ����
	// 		- request ObjectScope �� ����� 
	@RequestMapping("/logon07.do")
	public String logon07 ( @RequestParam("userId") String userId,
							@RequestParam("password") String password,
							Model model) {
		// Model : Map �� ����! �Ѵ� key & value �����ε�
		// Map�� j2se API �̰�, Model�� Spring API �� 
		
		System.out.println(":: ==> logon07() start....");
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		
		// ==> Model/View ���� (request ObjectScope ����) 
		model.addAttribute("user", user);
		
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		
		model.addAttribute("message", "Controller ���� ����");
		
		return "/user/logonResult.jsp";
	}

}
