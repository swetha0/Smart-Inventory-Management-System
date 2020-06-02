package service;

public interface UserDaoInterface {
//User validations
	public boolean checkAdminCredentials(String username,String password)throws Exception; 
	public boolean checkUserCredentials(String email, String password) throws Exception;
	
	
}
