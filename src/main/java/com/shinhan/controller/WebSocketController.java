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

import com.shinhan.dto.OwnerVO;
import com.shinhan.model.OwnerDAO;
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
	
	@Autowired
	OwnerDAO oDao;
	
	
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
	public String pageClient(Model model, Integer owner_id) {
		model.addAttribute("user1",uDao.selectUserById(1));
		model.addAttribute("user2",uDao.selectUserById(2));
		model.addAttribute("user3",uDao.selectUserById(3));
		
		owner_id = 1; //원래는 가게 열 때 가져올 것
		
		OwnerVO owner = oDao.selectOwnerById(owner_id); //가게정보 표시
		if(owner.getOwner_path() == null) {
			System.out.println("현재 주문 받기 불가능한 가게");
			return "redirect:/home";
		}
		model.addAttribute("owner", owner);
		return "page_sse";
	}
	
	@RequestMapping(value = "/echo.do", method = RequestMethod.GET)
	public String chat() {
		return "echo";
	}
}
