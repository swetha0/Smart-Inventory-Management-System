package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import businessLogic.RegistrationValidation;
import dao.UserDAO;
import model.SignUp;


public class Main {
	
	public static void main(String args[]) throws Exception {
		String username = null,password,confirmPassword,name,email;
		boolean validate=false;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));      
			
			//Begin the process
			System.out.println("Welcome to ***SMART INVENTORY MANAGEMENT SYSTEM***");
			System.out.println("1.SignUp");
			System.out.println("2.Login");
			System.out.println("3.Admin");
			//take a option from the console
			int option = Integer.parseInt(br.readLine());
			
			//cretae objects for dao classes
			UserDAO userdao = new UserDAO();
			
			//perform operation from user option
			switch (option) {
				case 1://SignUp
					
						System.out.println("Hello! Welcome to **Smart Inventory Management System \n You are registering your details as an User \n");
						System.out.println("Enter Your Name:");
						name = br.readLine();
						System.out.println("Enter your Email:");
						email = br.readLine();
						System.out.println("Enter Password");
						password = br.readLine();
						System.out.println("Confirm Password");
						confirmPassword = br.readLine();
						RegistrationValidation rv=new RegistrationValidation();
						SignUp signup=new SignUp(name, email,password, confirmPassword);
						if (rv.checkUserDetails(name,password, confirmPassword,email)) {
							System.out.println("ALL IS WELL");
							signup.setName(name);
							signup.setEmail(email);
							signup.setPassword(password);
							signup.setConfirmPassword(confirmPassword);
							printDetails(signup);
							System.out.println("Successfully Registred as a User\n-----------------------------------------------\n");
						} else 
							System.out.println("Invalid Details! Please Enter valid Details\n-----------------------------------------------\n");
						main(args);
						//bufferedReader.close();
				break;
				case 2:
					do{
						System.out.println("Enter email :");
						email = br.readLine(); 
						System.out.println("Enter password :");
						password = br.readLine();
						if(userdao.checkUserCredentials(username, password)) {
							validate = true;
							System.out.println("Successfully logged in!\n-----------------------------------------------\n");
						}	
						else
						System.out.println("Invalid username/password\n-----------------------------------------------\n");
						main(args);
					}while(!validate);
				break;
					case 3:
						System.out.println("Entering into AdminMain block");
						System.out.println("Enter Username: ");
						username = br.readLine();
						System.out.println("Enter Password: ");
						password = br.readLine();
						//check the crededtials of admin 
						if(userdao.checkAdminCredentials(username, password)) {
							validate = true;
							System.out.println("Successfully Logged in as Admin\n-----------------------------------------------\n");
						}
						else
							System.out.println("Invalid username/password\n-----------------------------------------------\n");
						main(args);
				break;
					default:
						System.out.println("Opps! this is not a valid option\n-----------------------------------------------\n");
						main(args);
		}// switch
	}// PSVM
	private static void printDetails(SignUp signup) {
		System.out.println("*******************************************************************************************");
		System.out.println("Your Successfully Registered Here is your Details");
		System.out.println("Your Name:" + signup.getName());
		System.out.println("Your Email:" + signup.getEmail());
		System.out.println("Your Password:" + signup.getPassword());
		System.out.println("Recheck Your Password:" + signup.getConfirmPassword());
		System.out.println("*******************************************************************************************");
	}
}// main close
