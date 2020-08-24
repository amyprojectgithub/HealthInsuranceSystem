package com.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.AdminAccEntity;

public interface AccountMgmtRepository extends JpaRepository<AdminAccEntity, Serializable> {
	
          public AdminAccEntity findByEmailIdAndPazzword(String email,String pazzword);
          
          public List<AdminAccEntity> findByRole(String role);
          
          @Modifying
          @Query(value= "UPDATE AdminAccEntity set deleteSwitch='Y' where accountId=?",nativeQuery=true)
          public void softDelete(Integer id);
          
}
