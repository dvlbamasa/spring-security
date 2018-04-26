package com.exist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class MainController{

	@RequestMapping(value="/login", method={ RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView){
		modelAndView.setViewName("home");
		return modelAndView;
	}

}