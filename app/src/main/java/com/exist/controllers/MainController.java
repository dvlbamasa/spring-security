package com.exist.controllers;

import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class MainController{

	@RequestMapping(value="/login", method={ RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView){
		modelAndView.addObject("loggedInUser", getPrincipal());
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value="/logout", method={ RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {    
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
		modelAndView.setViewName("redirect:login?logout");
		return modelAndView;
	}

	@RequestMapping(value="/accessDenied", method={ RequestMethod.GET, RequestMethod.POST})
	public ModelAndView accessDenied(ModelAndView modelAndView){
		modelAndView.setViewName("accessDenied");
		return modelAndView;
	}

	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        }
        else {
            userName = principal.toString();
        }
        return userName;
    }
}