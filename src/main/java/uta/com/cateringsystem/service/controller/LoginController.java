package uta.com.cateringsystem.service.controller;




import uta.com.cateringsystem.service.beans.User;
import uta.com.cateringsystem.service.dao.impl.DbManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Autowired
	DbManagerImpl dbManager;
	
	
	User user;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public  @ResponseBody User performLogin(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password,HttpServletRequest request, 
	        HttpServletResponse response) {
		user = dbManager.getUser(userName);
		
		if(user!=null && user.validatePassword(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return user;
		}else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public @ResponseBody Boolean performLogout(HttpServletRequest request, 
	        HttpServletResponse response) {
			
		HttpSession session = request.getSession();
		User user= (User)session.getAttribute("user");
		if(user!=null) {
			session.removeAttribute("user");
			session.invalidate();
			return true;

		}
		return false;
	}

}
