package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.constants.AppConstants;
import com.ashokit.model.AdminAcc;
import com.ashokit.model.AdminUnlockAcc;
import com.ashokit.service.AccountMgmtService;

@Controller
public class UnlockAccController {
	@Autowired
	private AccountMgmtService service;
	
	@GetMapping("/unlockAcc")
	public String loadUnlockAccForm(@RequestParam String email,Model model) {
		AdminUnlockAcc unlockAcc=new AdminUnlockAcc();
		unlockAcc.setEmailId(email);
		model.addAttribute("unlockAccount", unlockAcc);
		return "unlockAccHome";
	}

	@PostMapping("/unlockAcc")
	public String handleUnlockButton(@ModelAttribute("unlockAccount") AdminUnlockAcc adminUnlockAcc,Model model) {
		AdminAcc adminAcc = service.findByEmailIdAndPazzword(adminUnlockAcc.getEmailId(), adminUnlockAcc.getTempPazzword());
		
		if(null != adminAcc.getAccountId()) {
			adminAcc.setLockedStatus(AppConstants.UNLOCKED_STR);
			adminAcc.setPazzword(adminUnlockAcc.getConfirmPazzword());
			boolean isUpdated = service.updateAdminAcc(adminAcc);
			if(isUpdated) 
	         		return "unlockAccSucc";
		}
			model.addAttribute("errorMsg","Please enter correct temporary password.");
		    return "unlockAccHome";
	}
}
