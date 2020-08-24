package com.ashokit.model;

import lombok.Data;

@Data
public class AdminUnlockAcc {
	private String emailId;
	private String tempPazzword;
	private String newPazzword;
	private String confirmPazzword;
}
