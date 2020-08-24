$(document).ready(function(){

	$("#confirmPwdErr").hide();
	var confirmPwdErr=false;
	function validateConfirmPwd(){
		var confirm=$("#confirmPazzword").val();
		var newPwd=$("#newPazzword").val();

		if(confirm!=newPwd){
			$("#confirmPwdErr").show();
			$("#confirmPwdErr").html("<b style='font-size:15px'>Password didn't match.Please try again</b>");
			$("#confirmPwdErr").css("color","red");
			confirmPwdErr=true;
		}else{
			$("#confirmPwdErr").hide();
			confirmPwdErr=true;
		}
        return confirmPwdErr;
           
	}
	
	//link action event
	 $("#confirmPazzword").blur(function(){
		 validateConfirmPwd();
	      });
	 
	  //linking submit button 
	    $("#unlockBtn").click(function(){
	    	confirmPwdErr=false;
	        //invoke validate methods
	    	validateConfirmPwd();
	        if(confirmPwdErr)
	            return true;
	        else
	            return false;
	        });

});