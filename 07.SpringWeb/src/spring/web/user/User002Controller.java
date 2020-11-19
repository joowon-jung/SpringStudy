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
 *  	: return type : ModelAndView 적용 
 *  	: Method parameter
 *  		- Client Data : @ModelAttribute / @RequestParam 적절히 사용
 *  		- 필요시 : Servlet API 사용
 */

@Controller
public class User002Controller {

	public User002Controller() {
		System.out.println("==> User002 Controller default Constructor call.....");
	}

	@RequestMapping("/logon.do")
	public ModelAndView logon(HttpSession session) {

		System.out.println("[ logon() start....]");

		// ==> CONTROLLER : 권한 / 인증처리 작업 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User()); // session 이 새거면 새로 만듦
		}
		User sessionUser = (User) session.getAttribute("sessionUser");
		
		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // 로그인 되어있으면 isActive가 true로 바뀔 것 
			viewName = "/user002/home.jsp";
		}
		
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client 에 전달할 Message 생성 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ logon() ] WELCOME ";
		} else {
			message = "[ logon() ] 아이디, 패스워드 3자이상 입력하세요.";
		}
		
		// ==> Model (data) / View (jsp) 정보를 갖는 ModelAndView 생성 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("[ logon() end....]\n");
		
		return modelAndView;
	}

	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {

		System.out.println("[ home() start....]");
		
		// ==> CONTROLLER : 권한 / 인증처리 작업 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User()); // session 이 새거면 새로 만듦
		}
		User sessionUser = (User) session.getAttribute("sessionUser");

		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // 로그인 되어있으면 isActive가 true로 바뀔 것 
			viewName = "/user002/home.jsp";
		}
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client 에 전달할 Message 생성 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ home() ] WELCOME ";
		} else {
			message = "[ home() ] 아이디, 패스워드 3자이상 입력하세요.";
		}
		
		// ==> Model (data) / View (jsp) 정보를 갖는 ModelAndView 생성 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);

		System.out.println("[ home() end....] \n");
		
		return modelAndView;
	}

	// Business Logic 수행 / Navigation 
	@RequestMapping("/logonAction.do")
	public ModelAndView logonAction( @ModelAttribute("user") User user,
									HttpSession session) {

		System.out.println("[ logonAction() start....]");
		
		// ==> CONTROLLER : 권한 / 인증처리 작업 
		if (session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", user); // session 이 새거면 새로 만듦
		}
		User sessionUser = (User) session.getAttribute("sessionUser");
		
		// ==> CONTROLLER : Navigation 
		String viewName = "/user002/logon.jsp";
		
		if (sessionUser.isActive()) { // 로그인 되어있으면 isActive가 true로 바뀔 것 
			viewName = "/user002/home.jsp";
		} else {
			UserDAO userDAO = new UserDAO();
			userDAO.getUser(user);
			
			if (user.isActive()) { // 로그인 되어있으면 isActive가 true로 바뀔 것 
				viewName = "/user002/home.jsp";
				session.setAttribute("sessionUser", user);
			}
		}
		System.out.println("[ action : " + viewName + "]");
		
		// ==> Client 에 전달할 Message 생성 
		String message = null;
		if (viewName.equals("/user002/home.jsp")) {
			message = "[ logonAction() ] WELCOME ";
		} else {
			message = "[ logonAction() ] 아이디, 패스워드 3자이상 입력하세요.";
		}
		
		// ==> Model (data) / View (jsp) 정보를 갖는 ModelAndView 생성 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("[ logonAction() end....]");

		return modelAndView;
	}

	// 권한 (logon 정보삭제) 확인 / navigation 
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {

		System.out.println("[ logout() start....]");

		// ==> logon 정보 삭제 
		session.removeAttribute("sessionUser");
		
		// ==> Client 에 전달할 Message 생성 
		String message = "[Logout()] 아이디, 패스워드 3자이상 입력하세요.";
		
		// ==> Model (data) / View (jsp) 정보를 갖는 ModelAndView 생성 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/logon.jsp");
		modelAndView.addObject("message", message);
		
		System.out.println("[ logout() end....]\n");

		return modelAndView;
	}
}
