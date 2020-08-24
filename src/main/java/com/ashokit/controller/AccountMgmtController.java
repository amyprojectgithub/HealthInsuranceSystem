package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.model.AdminAcc;
import com.ashokit.service.AccountMgmtService;


@Controller
public class AccountMgmtController {
	@Autowired
	private AccountMgmtService service;
		
	@GetMapping("/createAcc")
     public String loadCreateAccForm(Model model) {
		AdminAcc adminAcc=new  AdminAcc();
		model.addAttribute("adminAcc", adminAcc);
    	 return "createAccForm";
     }
	@PostMapping("/saveAcc")
	public String saveAdminAcc(@ModelAttribute AdminAcc adminAcc,Model model ) {
		boolean isSaved = service.saveAcc(adminAcc);
		if(isSaved)
			model.addAttribute("msg","Acccount creation is almost completed,Please check your email to Unlock your Account");
		else
			model.addAttribute("msg", "Account creation Failed..Try again");
		return "createAccForm";
	}
}
