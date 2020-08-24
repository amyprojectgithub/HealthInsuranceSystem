package com.ashokit.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.ashokit.model.AdminAcc;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public  boolean sendMail(AdminAcc adminAcc) {
		try {
		 MimeMessage msg = javaMailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(msg);
		 helper.setTo(adminAcc.getEmailId());
		 helper.setSubject("Unlock your account");
		 helper.setText(getUnlockAccEmailBody(adminAcc),true);
		 javaMailSender.send(msg);
		 return true;
		}
		 catch(Exception e) {
			 e.printStackTrace();
			 
		 }
	return false;	
	}
	private String getUnlockAccEmailBody(AdminAcc adminAcc) throws IOException {
		StringBuffer sb=new StringBuffer("");
		FileReader raeder=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
    	BufferedReader br=new BufferedReader(raeder);
    	String line = br.readLine();
    	while(line!=null) {
    		sb.append(line);
        	line=br.readLine();
    	}
    	br.close();
    	String mailBody=sb.toString();
        mailBody=mailBody.replace("{FNAME}", adminAcc.getFirstName());
        mailBody=mailBody.replace("{LNAME}", adminAcc.getLastName());
        mailBody=mailBody.replace("{TEMP-PWD}", adminAcc.getPazzword());
        mailBody=mailBody.replace("{EMAIL}", adminAcc.getEmailId());
		return mailBody;
	}

}
