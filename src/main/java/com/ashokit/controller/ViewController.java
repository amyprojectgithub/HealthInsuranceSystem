package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.ashokit.model.AdminAcc;
import com.ashokit.service.AccountMgmtService;

@Controller
public class ViewController {
	@Autowired
	private AccountMgmtService service;
	
	@GetMapping("/view")
	public String viewAdminAcc(@ModelAttribute AdminAcc adminAcc,Model model) {
		String role = adminAcc.getRole();
		List<AdminAcc> list = service.fetchAllByRole(role);
		model.addAttribute("adminList", list);
         return "viewAcc";
	}
	
	@GetMapping("/editAcc")
	public String editMe( @RequestParam("id") Integer id, Model model) {
		AdminAcc  getOneAcc= service.getAccountById(id);
		model.addAttribute("adminAcc", getOneAcc);
		return "createAccForm";
	}

	@GetMapping("/deleteAcc")
	public String deleteMe(@RequestParam("id")Integer id,Model model) {
		 service.softDelete(id);
		return "redirect:/view";
	}
	
}
