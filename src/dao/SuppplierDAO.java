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
		PreparedStatement pstmt=con.prepareStatement("select * from suppliersProducts");
		ResultSet rs=pstmt.executeQuery();
		System.out.printf("ID             Category         ProductName           Quantity        Price %n");
		System.out.println("__________________________________________________________________________________");
		while(rs.next()) {
			
		}
		System.out.println("__________________________________________________________________________________");
	}

	@Override
	public void removeSupplier(int supplierId) throws Exception {
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt=con.prepareStatement("delete from suppliersProducts where id="+supplierId);
		pstmt.executeUpdate();
	}

}
