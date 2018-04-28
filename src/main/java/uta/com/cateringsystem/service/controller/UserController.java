package uta.com.cateringsystem.service.controller;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uta.com.cateringsystem.service.beans.User;
import uta.com.cateringsystem.service.dao.impl.DbManagerImpl;
@RestController
public class UserController {
	
	@Autowired
	DbManagerImpl dbManager;
	
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public  @ResponseBody Boolean register(@RequestParam(value = "user") User user,
			HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.registerUser(user);
			
	}
	
	

	@RequestMapping(value="/updateprofile",method=RequestMethod.POST)
	public  @ResponseBody Boolean updateProfile(@RequestParam(value = "user") User user,
			@RequestParam(value = "userId") int userId,
			HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.updateProfile(user, userId);
		
		
	}
	@RequestMapping(value="/deleteprofile",method=RequestMethod.POST)
	public  @ResponseBody Boolean deleteProfile(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "adminId") int adminId,
			HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.deleteProfile(userId, adminId);
		
	}
	
	@RequestMapping(value="/admin/approve",method=RequestMethod.POST)
	public  @ResponseBody Boolean approveUser(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "adminId") int adminId,
			HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.approveRegistrationRequest(userId, adminId);
		
	}
	@RequestMapping(value="/admin/reject",method=RequestMethod.POST)
	public  @ResponseBody Boolean rejectUser(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "adminId") int adminId, 
	        HttpServletResponse response) {
		return dbManager.rejectRegistrationRequest(userId, adminId);
		
	}

	@RequestMapping(value="/admin/getregistrationlist",method=RequestMethod.POST)
	public  @ResponseBody List<User> getRegistationList(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getRegistrationRequestList();
		
	}
}
