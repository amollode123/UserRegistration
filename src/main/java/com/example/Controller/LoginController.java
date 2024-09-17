package com.example.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Entity.User;
import com.example.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/loginController")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginPage(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		if(Objects.nonNull(currentUser)) {
			model.addAttribute("user", currentUser);
			return "redirect:/dashboard";
		}
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session){
		User validUser = userService.validateUser(user.getEmail(), user.getPassword());
		if(Objects.nonNull(validUser)) {
			redirectAttributes.addFlashAttribute("message", "Login successful");
			session.setAttribute("currentUser", validUser);
			return "redirect:/dashboard";
		}
		else {
			redirectAttributes.addFlashAttribute("error", "Invalid email or password. please try again");
			return "redirect:/api/loginController/login";
		}
	}
	
	@GetMapping("/dashboard")
	public String showDashboardPage(Model model, HttpSession session, RedirectAttributes redirectAttributes){
		User currentUser = (User) session.getAttribute("currentUser");
		if(Objects.isNull(currentUser)) {
			redirectAttributes.addFlashAttribute("error", "you are not logged in please try again");
			return "redirect:/api/loginController/login";
		}
		model.addAttribute("user", currentUser);
		return "dashboard";
	}

}
