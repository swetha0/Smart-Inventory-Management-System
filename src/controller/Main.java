package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import businessLogic.RegistrationValidation;
import dao.UserDAO;
import model.SignUp;


public class Main {
	
	public static void main(String args[]) throws Exception {
		String username = null,password,confirmPassword = null,name = null,email = null;
		boolean validate=false;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));      
			
			//Begin the process
			System.out.println("Welcome to *********-----SMART INVENTORY MANAGEMENT SYSTEM-----**********");
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
						System.out.println("*********Customer Block*********");
						System.out.println("REGISTRATION");
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
							userdao.addUserDetails(name, password,confirmPassword, email);
								printDetails(signup);
								System.out.println("Successfully Registred!\n-----------------------------------------------\n");
						} else 
							System.out.println("Invalid Details! Please Enter valid Details\n-----------------------------------------------\n");
						main(args);
				break;
				case 2://Login
					do{
						System.out.println("*********Customer Block*********");
						System.out.println("LOGIN");
						System.out.println("Enter email :");
						email = br.readLine(); 
						System.out.println("Enter password :");
						password = br.readLine();
						if(userdao.checkUserCredentials(email, password)) {
							validate = true;
							System.out.println("Successfully logged in!\n-----------------------------------------------\n");
							String adminDecision;
							do {
								System.out.println("---------1.Add Products to the store---------");
								System.out.println("---------2.Remove Suppliers-----------");
								System.out.println("---------3.View all products in store----------");
								System.out.println("---------4.View all customer purchases----------");
								//take admin choice to perform some operations
								int adminChoice =Integer.parseInt(br.readLine());
								switch(adminChoice) {
								case 1:
									System.out.println("Products from the Suppliers:");
									
									break;
								case 2:
									System.out.println("To Remove Suppliers enter suppliersId");
									
									break;
								case 3:
									System.out.println("Products in Store");
									
									break;
								case 4:
									System.out.println("All customers purchases");
									
									break;
								default:
									System.out.println("Opps This is not a valid option Admin!");
								}
								System.out.println("Do you want to continue:Yes/No");
								adminDecision=br.readLine();
							}while(adminDecision.equals("Yes"));
						}	
						else
						System.out.println("Invalid Data!\n-----------------------------------------------\n");
						main(args);
					}while(!validate);
				break;
				case 3://Admin
						System.out.println("**********Admin Block***********");
						System.out.println("Enter Username: ");
						username = br.readLine();
						System.out.println("Enter Password: ");
						password = br.readLine();
						if(userdao.checkAdminCredentials(username, password)) {
							validate = true;
							System.out.println("Successfully Logged in as Admin!\n-----------------------------------------------\n");
						}
						else
							System.out.println("Invalid username&password!\n-----------------------------------------------\n");
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
