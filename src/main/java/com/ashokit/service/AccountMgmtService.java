package com.ashokit.service;

import java.util.List;

import com.ashokit.model.AdminAcc;

public interface AccountMgmtService {
	public boolean saveAcc(AdminAcc adminAcc);

	public List<AdminAcc> fetchAllByRole(String role);

	public AdminAcc findByEmailIdAndPazzword(String email,String pazzword);

	public boolean updateAdminAcc(AdminAcc adminAcc);

	public AdminAcc  getAccountById(Integer id);
	
	public void softDelete(Integer id);
	
	public void activateAcc(Integer id);
}
