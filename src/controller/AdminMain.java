package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import model.Login;

public class AdminMain {
	private static final int Id = 0;
	static Login login;
	static BufferedReader br;
	static String password=null,username=null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.out.println("Welcome to ***SMART INVENTORY MANAGEMENT SYSTEM*** \n Administrator Block\n");
		System.out.println("Login as a Admin");

		Login login=new Login(username, password);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));        
		
				System.out.println("Enter Username:");
				username = br.readLine();
				System.out.println("Enter Password :");
				password = br.readLine();
				
				if (username.equals("Admin")&&password.equals("Admin@123")) {
					login.setUsername(username);
					login.setPassword(password);
					System.out.println("Successfully login as a Admin");
					System.out.println("You have to perform bellow operations");
					Object adminDecision;
					do {
						System.out.println("1.Add products to store");
						System.out.println("2.Retreive Products to the store");
						System.out.println("3.Remove products from store");
						System.out.println("4.Update products list");
						System.out.println("5.View all the products in the store");
						
						//take the admin choice to perform some operation
						int adminChoice = Integer.parseInt(br.readLine());
						//String adminDecision;
						switch(adminChoice) {
							case 1:
							
								break;
							case 2:
								System.out.println("To Remove products enter the productId");
								
								
								System.out.println("Successully removed supplier");
								break;
							case 3:
								System.out.println("Products In store :");
								
								break;	
							case 4:
								System.out.println("All customer purchases");
								
								break;
							default:
								System.out.println("Invalid Input");
						}
						
						System.out.println("Do you want to continue : yes/no");
						 adminDecision=br.readLine();		
					}while(adminDecision.equals("yes"));
				}//if
				else
					System.out.println("Invalid Username/Password");
				
	}// PSVM	

}// main close
