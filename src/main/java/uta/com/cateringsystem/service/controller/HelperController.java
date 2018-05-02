package uta.com.cateringsystem.service.controller;




import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uta.com.cateringsystem.service.beans.AvailableHall;
import uta.com.cateringsystem.service.beans.Hall;
import uta.com.cateringsystem.service.beans.User;
import uta.com.cateringsystem.service.dao.impl.DbManagerImpl;
import uta.com.cateringsystem.service.util.UtaCateringServiceUtil;
@RestController
public class HelperController {
	
	@Autowired
	DbManagerImpl dbManager;
	
	
	User user;
	
	@RequestMapping(value="/getdrinkvenue",method=RequestMethod.GET)
	public  @ResponseBody Map<Integer, String> getDrinkVenues(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getDrinkVenues();
		
	}
	
	@RequestMapping(value="/getfoodvenue",method=RequestMethod.GET)
	public  @ResponseBody Map<Integer, String> getFoodVenues(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getFoodVenues();
		
	}
	
	@RequestMapping(value="/gethalls",method=RequestMethod.GET)
	public  @ResponseBody List<Hall> getHalls(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getHalls();
		
	}
	
	@RequestMapping(value="/getmeals",method=RequestMethod.GET)
	public  @ResponseBody  Map<Integer, String> getMeals(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getMeals();
		
	}
	
	@RequestMapping(value="/getmealformalities",method=RequestMethod.GET)
	public  @ResponseBody  Map<Integer, String> getMealFormalities(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getMealFormalities();
		
	}
	
	@RequestMapping(value="/getusertypes",method=RequestMethod.GET)
	public  @ResponseBody  Map<Integer, String> getUserTypes(HttpServletRequest request, 
	        HttpServletResponse response) {
		return dbManager.getUserTypes();
		
	}
	
	@RequestMapping(value="/searchavailablehalls",method=RequestMethod.GET)
	public  @ResponseBody  List<AvailableHall> searchAvailableHalls(@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,@RequestParam(value = "startTime") String startTime,
			@RequestParam(value = "endTime") String endTime,
			HttpServletRequest request, 
	        HttpServletResponse response) {
		
		return UtaCateringServiceUtil.computeAvailableHalls(dbManager.getHalls(), dbManager.searchAvailableHalls(startDate, endDate, startTime, endTime), 
				startDate, endDate, startTime, endTime);
		
	}
	
}
