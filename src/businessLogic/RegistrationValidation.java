package businessLogic;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.SignUp;

public class RegistrationValidation{
	
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	private Pattern pattern;
	private Matcher matcher1,matcher2;
	
	public boolean checkUserDetails(String name, String password, String confirmPassword, String email) {
		SignUp signup = new SignUp(name,password, confirmPassword,email);
		if(validPassword(signup.getPassword(),signup.getConfirmPassword())&& isValidEmail(signup.getEmail()))
			return true;
		else
			return false;
	}

	//passwords validation
	private boolean validPassword(String password, String confirmPassword) {
		 pattern = Pattern.compile(PASSWORD_PATTERN);
		 if(password.equals(confirmPassword)) {
		 
		 matcher1 = pattern.matcher(password);
		 matcher2 = pattern.matcher(confirmPassword);
		  if(matcher2.matches() && matcher1.matches())
		  return true;
		  else{
			  System.out.println("password must be strong");
			  return false;
		  }	
		 }
		 System.out.println("password and confirm password must be same");
		 return false;
	}

	//Username validation	 
	private boolean isValidEmail(String s) 
  	{ 
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
	  if(s.matches(emailRegex))
		return true;
	  else
	  {
		  System.out.println("Invalid Email");
		  return false;
	  }	
  	}
}    