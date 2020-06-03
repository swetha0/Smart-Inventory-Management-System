package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.SupplierDaoInterface;
import utility.ConnectionManager;

public class SuppplierDAO implements SupplierDaoInterface {

	@Override
	public void displayProductsFromSuppliers() throws Exception {
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt=con.prepareStatement("select * from supplierproducts");
		ResultSet rs=pstmt.executeQuery();
		
	}

	@Override
	public void removeSupplier(int supplierId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
