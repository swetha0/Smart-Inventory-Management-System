package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Login;
import service.UserDaoInterface;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface{

	@Override
	public boolean checkAdminCredentials(String username, String password) throws Exception {
		Login login=new Login(username,password);
		boolean status=false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from Admin1 where username = ? and password = ? ");
			
			pstmt.setString(1,login.getUsername());
			pstmt.setString(2,login.getPassword());
			
			ResultSet rs=pstmt.executeQuery();
			status=rs.next();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean checkUserCredentials(String email, String password) throws Exception {
		Login login=new Login(email,password);
		boolean status = false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from customerData1 where email = ? and password = ? ");
			
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());

			ResultSet rs = pstmt.executeQuery();
			status = rs.next();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public void addUserDetails(String name, String password, String confirmPassword, String email) throws Exception {
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into customerData1(name,password,email) values(?,?,?)");
		
		pstmt.setString(1,name);
		pstmt.setString(2, password);
		pstmt.setString(3, email);
		pstmt.executeUpdate();
	}		
}

