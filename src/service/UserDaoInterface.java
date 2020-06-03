package service;

public interface UserDaoInterface {
	public boolean checkAdminCredentials(String username,String password)throws Exception; 
	public boolean checkUserCredentials(String email, String password) throws Exception;
	public void addUserDetails(String name, String password,String confirmPassword,String email) throws Exception;
}
