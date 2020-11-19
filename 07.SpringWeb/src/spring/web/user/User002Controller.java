package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.impl.UserDAO;

/*
 *  - Controller Coding Policy
 *  	: return type : ModelAndView ���� 
 *  	: Method parameter
 *  		- Client Data : @ModelAttribute / @RequestParam ������ ���
 *  		- �ʿ�� : Servlet API ���
 */

@Controller
public class User002Controller {

	public User002Controller() {
		System.out.println("==> User002 Controller default Constructor call.....");
	}

	@RequestMapping("/logon.do")
	public ModelAndView logon(HttpSession session) {

		System.out.println("[ logon() start....]");

		// ==> CONTROLLER : ���� / ����ó�� �۾� 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User()); // session �� ���Ÿ� ���� ����
		}
		User sessionUser = (User) session.getAttribute("sessionUser");
		
		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� �� 
			viewName = "/user002/home.jsp";
		}
		
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client �� ������ Message ���� 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ logon() ] WELCOME ";
		} else {
			message = "[ logon() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		}
		
		// ==> Model (data) / View (jsp) ������ ���� ModelAndView ���� 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("[ logon() end....]\n");
		
		return modelAndView;
	}

	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {

		System.out.println("[ home() start....]");
		
		// ==> CONTROLLER : ���� / ����ó�� �۾� 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User()); // session �� ���Ÿ� ���� ����
		}
		User sessionUser = (User) session.getAttribute("sessionUser");

		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� �� 
			viewName = "/user002/home.jsp";
		}
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client �� ������ Message ���� 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ home() ] WELCOME ";
		} else {
			message = "[ home() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		}
		
		// ==> Model (data) / View (jsp) ������ ���� ModelAndView ���� 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);

		System.out.println("[ home() end....] \n");
		
		return modelAndView;
	}

	// Business Logic ���� / Navigation 
	@RequestMapping("/logonAction.do")
	public ModelAndView logonAction( @ModelAttribute("user") User user,
									HttpSession session) {

		System.out.println("[ logonAction() start....]");
		
		// ==> CONTROLLER : ���� / ����ó�� �۾� 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", user); // session �� ���Ÿ� ���� ����
		}
		User sessionUser = (User) session.getAttribute("sessionUser");
		
		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� �� 
			viewName = "/user002/home.jsp";
		} else {
			UserDAO userDAO = new UserDAO();
			userDAO.getUser(user);
			
			if (user.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� �� 
				viewName = "/user002/home.jsp";
				session.setAttribute("sessionUser", user);
			}
		}
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client �� ������ Message ���� 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ logonAction() ] WELCOME ";
		} else {
			message = "[ logonAction() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		}
		
		// ==> Model (data) / View (jsp) ������ ���� ModelAndView ���� 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("[ logonAction() end....]");

		return modelAndView;
	}

	// ���� (logon ��������) Ȯ�� / navigation 
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {

		System.out.println("[ logout() start....]");

		// ==> logon ���� ���� 
		session.removeAttribute("sessionUser");
		
		// ==> Client �� ������ Message ���� 
		String message = "[Logout()] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		
		// ==> Model (data) / View (jsp) ������ ���� ModelAndView ���� 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/logon.jsp");
		modelAndView.addObject("message", message);
		
		System.out.println("[ logout() end....]\n");

		return modelAndView;
	}
}
