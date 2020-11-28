package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	/////////////////////// Business logic UserService DI //////////////////////
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	////////////////////////////////////////////////////////////////////////////

	public UserController() {
		System.out.println("==> UserController default Constructor call.....");
	}
	

	@RequestMapping
	public String logon(Model model) {

		System.out.println("[ logon() start....]");

		String message = "[ logon() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";

		//==> Model(data) 
		model.addAttribute("message", message);

		System.out.println("[ logon() end....]\n");

		return "/user/logon.jsp";
	}

	@RequestMapping
	public String home(Model model) {

		System.out.println("[ home() start....]");

		String message = "[ home() ] WELCOME ";

		//==> Model(data) 
		model.addAttribute("message", message);

		System.out.println("[ home() end....] \n");

		return "/user/home.jsp";
	}

	// POST ��� : ���������� work-flow �� Ÿ�� ������
	@RequestMapping(method = RequestMethod.POST)
	public String logonAction(@ModelAttribute("user") User user, HttpSession session, Model model) throws Exception {

		System.out.println("[ logonAction() method=RequestMethod.POST start....]");

		String viewName = "/user/logon.jsp";
		
		User returnUser = userService.getUser(user.getUserId());
		if (returnUser.getPassword().equals(user.getPassword())) {
			returnUser.setActive(true);
			user = returnUser;
		}

		if (user.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� ��
			viewName = "/user/home.jsp";
	        session.setAttribute("sessionUser", user);
		}

		System.out.println("[ action : " + viewName + "]");

		String message = null;
		if (viewName.equals("/user/home.jsp")) {
			message = "[ logonAction() ] WELCOME ";
		} else {
			message = "[ logonAction() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		}

		//==> Model(data) 
		model.addAttribute("message", message);

		System.out.println("[ logonAction() method=RequestMethod.POST end....]");

		return viewName;
	}

	@RequestMapping
	public String logout(Model model, HttpSession session) {

		System.out.println("[ logout() start....]");

		session.removeAttribute("sessionUser");

		String message = "[Logout()] ���̵�, �н����� 3���̻� �Է��ϼ���.";

		//==> Model(data) 
		model.addAttribute("message", message);

		System.out.println("[ logout() end....]\n");

		return "/user/logon.jsp";
	}
}
