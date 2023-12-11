package com.shinhan.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinhan.model.UserDAO;
import com.shinhan.model.testDAO;

//import com.shinhan.model.testDAO;

@Controller
public class WebSocketController {

private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	testDAO test;
	
	@Autowired
	UserDAO uDao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("test", test.selectAll());
		return "home";
	}
	
	@GetMapping("/page.do")
	public String pageDisplay(Model model) {
		model.addAttribute("user1",uDao.selectUserById(1));
		model.addAttribute("user2",uDao.selectUserById(2));
		model.addAttribute("user3",uDao.selectUserById(3));
		return "page";
	}
	
	@GetMapping("/page_client.do")
	public String pageClient(Model model) {
		model.addAttribute("user1",uDao.selectUserById(1));
		model.addAttribute("user2",uDao.selectUserById(2));
		model.addAttribute("user3",uDao.selectUserById(3));
		return "page_sse";
	}
	
	@RequestMapping(value = "/echo.do", method = RequestMethod.GET)
	public String chat() {
		return "echo";
	}
}
