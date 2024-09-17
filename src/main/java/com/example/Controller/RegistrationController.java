package com.example.Controller;

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

@Controller
@RequestMapping("/api/registrationController")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "/api/registrationController/register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttribute) {
		userService.save(user);
		redirectAttribute.addFlashAttribute("message", "Registration successful! please login");
		return "redirect:/api/loginController/login";
	}

}
