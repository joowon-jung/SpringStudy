package spring.web.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field 
	@Value("#{contextProperties['pageSize']}")
	int pageSize;
	
	//@Value("#{contextProperties['page']}")
	int page01;
	
	@Value("#{contextProperties['page'] ?: 10}")
	int page02;

	public UserController() {
		System.out.println(":: TestController default Contructor call");
		System.out.println("pageSize : " + pageSize);
		System.out.println("page01 : " + page01);
		System.out.println("page02 : " + page02);
	}
	
	@RequestMapping("testView")
	public ModelAndView testView() {
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping(value="xml/{value}", method=RequestMethod.GET)
	public ModelAndView abc(@PathVariable String value) {
		System.out.println("value : " + value);
		System.out.println("pageSize : " + pageSize);
		System.out.println("page01 : " + page01);
		System.out.println("page02 : " + page02);
		System.out.println();
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping(value="json/{id}", method=RequestMethod.GET)
	public ModelAndView def(@PathVariable String id) {
		System.out.println("id : " + id);
		System.out.println("pageSize : " + pageSize);
		System.out.println("page01 : " + page01);
		System.out.println("page02 : " + page02);
		System.out.println();
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable String userId) {
		User user = new User();
		user.setUserId(userId);
		return new ModelAndView("/test/testView.jsp","user",user);
	}
	
	@RequestMapping(value="/{userId}/{userName}", method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable String userId, 
								@PathVariable String userName) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		return new ModelAndView("/test/testView.jsp","user",user);
	}
	
	@RequestMapping(value="/{userId}/{userName}", method=RequestMethod.POST)
	public ModelAndView getUser(@PathVariable String userId, 
								@PathVariable String userName,
								@ModelAttribute("userForm") User userForm) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		
		return new ModelAndView("/test/testView.jsp","user",user);
	}
}
