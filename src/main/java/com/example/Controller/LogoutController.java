package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/logoutController")
public class LogoutController {
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session =  request.getSession(false);
		if(	session != null){
			session.invalidate();
		}
		
		return "redirect:/login";
	}
}
