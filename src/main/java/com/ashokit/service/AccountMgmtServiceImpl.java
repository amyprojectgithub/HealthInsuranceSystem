package com.ashokit.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashokit.constants.AppConstants;
import com.ashokit.entity.AdminAccEntity;
import com.ashokit.exception.HisException;
import com.ashokit.model.AdminAcc;
import com.ashokit.repo.AccountMgmtRepository;
import com.ashokit.utils.EmailUtils;
import com.ashokit.utils.PwdUtils;

@Service
public class AccountMgmtServiceImpl implements AccountMgmtService {

	private static final Logger logger=LoggerFactory.getLogger(AccountMgmtServiceImpl.class);

	@Autowired
	private AccountMgmtRepository accMgmtRepo;  

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean saveAcc(AdminAcc adminAcc) {
		logger.debug("Method Excecution Started");
		boolean isSaved=false;
		logger.debug("Setting pazzword ,LockedStatus,DeleteSwitch to AdminAcc");
		adminAcc.setPazzword(PwdUtils.generatePassword(AppConstants.TEMP_PWD_LENGTH));
		adminAcc.setLockedStatus(AppConstants.LOCKED_STR);
		adminAcc.setDeleteSwitch("N");
		logger.debug("Copy data from AdminAcc to AdminAccEntity");
		AdminAccEntity entity=new AdminAccEntity();
		BeanUtils.copyProperties(adminAcc, entity);

		try {
			AdminAccEntity adminAccEntity = accMgmtRepo.save(entity);
			if(adminAccEntity != null && adminAccEntity.getAccountId() != null) {
				isSaved=true;
				logger.info("Record Inserted but Email Sending in process");
				if(emailUtils.sendMail(adminAcc)) {
					logger.info("Email has been sent");
				}else {
					logger.warn("Record Inserted But Email hasn't sent");
				}
			}

		}catch(Exception e) {
			logger.error(AppConstants.ADMIN_ACC_SAVE_EXCEPTION+e.getMessage());
			throw new HisException(AppConstants.ADMIN_ACC_SAVE_EXCEPTION);
		}

		logger.debug("Method Excecution ended");
		return isSaved;
	}

	@Override
	public List<AdminAcc> fetchAllByRole(String role) {
		logger.debug("Method Execution Started");
		List<AdminAcc>  adminAccList=new LinkedList<>();
		List<AdminAccEntity> entityList = accMgmtRepo.findByRole(role);
		logger.debug("Copy data from List<AdminAccEntity> to List<AdminAcc> ");
		entityList.forEach(entity->{
			AdminAcc adminAcc=new AdminAcc();
			BeanUtils.copyProperties(entity, adminAcc);
			adminAccList.add(adminAcc);
		});
		logger.debug("Method Execution ended");
		return adminAccList;
	}

	@Override
	public AdminAcc findByEmailIdAndPazzword(String email, String pazzword) {
		logger.debug("Method Execution Started");
		AdminAcc adminAcc=null;
		AdminAccEntity adminAccEntity=null;
		try {
			adminAccEntity = accMgmtRepo.findByEmailIdAndPazzword(email, pazzword);
			if(adminAccEntity !=null && adminAccEntity.getAccountId() != null) {
				logger.info("Acc has been fetched by email and pazzword");
				logger.debug("Copy data from AdminAccEntity to AdminAcc ");
				adminAcc=new AdminAcc();
				BeanUtils.copyProperties(adminAccEntity, adminAcc);
			}else {
				logger.info("Acc couldn't fetch by email and pazzword ");
			}
		}catch (Exception e) {
			logger.error("Error in findByEmailIdAndPazzword(-,-): : "+e.getMessage());
			throw new HisException();
		}
		logger.debug("Method Execution ended");
		return adminAcc;
	}

	@Override
	public boolean updateAdminAcc(AdminAcc adminAcc) {
		logger.debug("Method execution started");
		boolean isUpdated=false;
		AdminAccEntity adminAccEntity=new AdminAccEntity();
		BeanUtils.copyProperties(adminAcc, adminAccEntity);
		AdminAccEntity accEntity = accMgmtRepo.save(adminAccEntity);
		if(null != accEntity) {
			isUpdated=true;
			logger.info("AdminAcc is updated with confirm pazzword and Unlocked status");
		}
		logger.debug("Method execution ended");
		return isUpdated;
	}

	@Override
	public AdminAcc getAccountById(Integer id) {
		logger.debug("Method execution started");
		AdminAccEntity adminAccEntity =null;
		AdminAcc adminAcc=null;
		try {
			Optional<AdminAccEntity> optional = accMgmtRepo.findById(id);
			if(optional.isPresent()) {
				adminAccEntity= optional.get();
			}
			logger.debug("Copy data from AdminAccEntity to AdminAcc ");
			adminAcc=new AdminAcc();
			BeanUtils.copyProperties(adminAccEntity, adminAcc);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new HisException();
		}
		logger.debug("Method execution ended");
		return adminAcc;
	}
	
	@Override
	public void softDelete(Integer id) {
		try {
		accMgmtRepo.softDelete(id);
		}catch (Exception e) {
			logger.error("Soft Deletion failed::"+e.getMessage());
			throw new HisException("Soft Deletion failed");
		}
	}
	@Override
	public void activateAcc(Integer id) {
		try {
			accMgmtRepo.activateAcc(id);
		}catch (Exception e) {
			logger.error("Account activation failed::"+e.getMessage());
			throw new HisException("Account activation failed");
		}
	}

}
