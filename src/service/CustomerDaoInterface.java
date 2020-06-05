package service;

public interface CustomerDaoInterface {
	public void displayCustomerPurchases(String email) throws Exception;

	public void user(String email, int productId, int quantity) throws Exception;
}
