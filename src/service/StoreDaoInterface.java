package service;

public interface StoreDaoInterface {
	public void addProductsToStore(int supplierId, int quantity) throws Exception ;
	public void displayCustomerPurchases() throws Exception ;
	public void displayProductsInStore() throws Exception ;
}