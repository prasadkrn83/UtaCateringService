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

import uta.com.cateringsystem.service.beans.Event;
import uta.com.cateringsystem.service.beans.User;
import uta.com.cateringsystem.service.dao.impl.DbManagerImpl;

@RestController
public class EventController {

	@Autowired
	DbManagerImpl dbManager;

	@RequestMapping(value = "/requestuserevent", method = RequestMethod.POST)
	public @ResponseBody String requestUserEvent(@RequestParam(name = "eventRequest") Event eventRequest,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "User not logged in";
		}
		if (dbManager.requestEvent(eventRequest)) {
			return "Event Created Successfully";
		} else {
			return "Failed to create Event";
		}

	}

	@RequestMapping("/cancelevent")
	public @ResponseBody String cancelEvent(@RequestParam(name = "eventId") int eventId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "User not logged in";
		}
		if (dbManager.cancelEvent(eventId, user.getUser_id())) {
			return "Event Cancelled Successfully";
		}else

	{
		return "Failed to cancel Event";
	}

	}

	@RequestMapping("/caterer/createevent")
	public @ResponseBody String createEvent(@RequestParam(name = "eventId") int eventId, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "User not logged in";
		}
		if(dbManager.createEvent(eventId, user.getUser_id())) {
			return "Event Created Successfully";
		}
		else {
			return "Failed to create Event";
		
		}
	}

	@RequestMapping("/caterer/rejectevent")
	public @ResponseBody String rejectEvent(@RequestParam(name = "eventId") int eventId, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "User not logged in";
		}
		if(dbManager.rejectEvent(eventId, user.getUser_id())){
			
			return "Event Rejected Successfully";
		}
		else {
			return "Failed to reject Event";
		}
	}

	@RequestMapping("/caterer/assignstaff")
	public @ResponseBody String assignStaffToEvent(@RequestParam(name = "eventId") int eventId,
			@RequestParam(name = "staffList") List<Integer> staffList, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "User not logged in";
		}
		if(dbManager.assignStaffToEvent(eventId,staffList, user.getUser_id())) {
			return "Staff Assigned Successfully";
		}
		else {
			return "Failed to assign staff";
		}
	}
	
	@RequestMapping("/performestimation")
	public @ResponseBody Double performEstimation(@RequestParam(name = "event") Event event,
			HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return performEventEstimation(event);
	}

	private Double performEventEstimation(Event event) {
		// TODO Auto-generated method stub
		return null;
	}
}
