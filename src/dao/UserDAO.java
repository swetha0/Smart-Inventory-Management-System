package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.SignUp;
import service.UserDaoInterface;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface{

	@Override
	public boolean checkAdminCredentials(String username, String password) throws Exception {
		Login login=new Login(username,password);
		boolean status=false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from admin where username = ? and password = ? ");
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

	public int addUserDetails(SignUp signup) throws Exception {
		int result=0;
		try {
			
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into customerData1(name,password,email) values(?,?,?)");
		pstmt.setString(1, signup.getName());
		pstmt.setString(2, signup.getPassword());
		pstmt.setString(3,signup.getEmail());
		pstmt.executeUpdate(); 
		result=1;
		return result;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return result;
	}

}
