package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.impl.UserDAO;

//@Controller
public class User004Controller {

	public User004Controller() {
		System.out.println("==> User004 Controller default Constructor call.....");
	}

	@RequestMapping("/logon.do")
	public ModelAndView logon(HttpSession session) {

		System.out.println("[ logon() start....]");

		String message = "[ logon() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/logon.jsp");
		modelAndView.addObject("message", message);

		System.out.println("[ logon() end....]\n");

		return modelAndView;
	}

	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {

		System.out.println("[ home() start....]");

		String message = "[ home() ] WELCOME ";
		;

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/home.jsp");
		modelAndView.addObject("message", message);

		System.out.println("[ home() end....] \n");

		return modelAndView;
	}

	@RequestMapping(value = "/logonAction.do", method = RequestMethod.GET)
	public ModelAndView logonAction() {

		System.out.println("[ logonAction() method=RequestMethod.GET start....]");

		// get ��� (url ���� �ļ� ������ ���) ���� �ٷ� logonAction���� �� �Ŵϱ� ���� ����� ���� �ʾƵ� ��!
		// �׷��Ƿ� logon.do �� �ٷ� �������� !
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/logon.do");

		System.out.println("[ logonAction() method=RequestMethod.GET end....]");

		return modelAndView;
	}

	// POST ��� : ���������� work-flow �� Ÿ�� ������
	@RequestMapping(value = "/logonAction.do", method = RequestMethod.POST)
	public ModelAndView logonAction(@ModelAttribute("user") User user, HttpSession session) {

		System.out.println("[ logonAction() method=RequestMethod.POST start....]");

		String viewName = "/user002/logon.jsp";

		UserDAO userDAO = new UserDAO();
		userDAO.getUser(user);

		if (user.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� ��
			viewName = "/user002/home.jsp";
			session.setAttribute("sessionUser", user);
		}

		System.out.println("[ action : " + viewName + "]");

		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ logonAction() ] WELCOME ";
		} else {
			message = "[ logonAction() ] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);

		System.out.println("[ logonAction() method=RequestMethod.POST end....]");

		return modelAndView;
	}

	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {

		System.out.println("[ logout() start....]");

		session.removeAttribute("sessionUser");

		String message = "[Logout()] ���̵�, �н����� 3���̻� �Է��ϼ���.";

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/logon.jsp");
		modelAndView.addObject("message", message);

		System.out.println("[ logout() end....]\n");

		return modelAndView;
	}
}
