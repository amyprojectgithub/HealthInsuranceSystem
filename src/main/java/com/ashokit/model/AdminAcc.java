package com.ashokit.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminAcc implements Serializable {
	
	private static final long serialVersionUID = -8300459057777858572L;
	
	private Integer accountId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String pazzword;
	private String gender;
	private String role;
	private String lockedStatus;
	private String deleteSwitch;

}
