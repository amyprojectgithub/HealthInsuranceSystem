package com.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.AdminAccEntity;

public interface AccountMgmtRepository extends JpaRepository<AdminAccEntity, Serializable> {
	
          public AdminAccEntity findByEmailIdAndPazzword(String email,String pazzword);
          
          public List<AdminAccEntity> findByRole(String role);
          
          @Modifying
          @Transactional
          @Query(value= "UPDATE AdminAccEntity SET deleteSwitch='Y' where accountId= :id")
          public void softDelete(Integer id);
          
          @Modifying
          @Transactional
          @Query(value= "UPDATE AdminAccEntity SET deleteSwitch='N' where accountId= :id")
          public void activateAcc(Integer id);
}
