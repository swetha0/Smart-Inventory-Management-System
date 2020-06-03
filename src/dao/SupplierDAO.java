package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.SupplierDaoInterface;
import utility.ConnectionManager;

public class SupplierDAO implements SupplierDaoInterface {

	@Override
	public void displayProductsFromSuppliers() throws Exception {
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt=con.prepareStatement("select * from suppliersProducts");
		ResultSet rs=pstmt.executeQuery();
		System.out.printf("ID     Name                 ProductId    ProductName            Category                   Brand              Quantity         Price %n");
		System.out.println("____________________________________________________________________________________________________________________________________________");
		while(rs.next()) {
			System.out.printf("%-6d %-20s %-10d %-25s %-25s %-20s %-15d %-15d %n",rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
		}
		System.out.println("_____________________________________________________________________________________________________________________________________________");
	}

	@Override
	public void removeSupplier(int supplierId) throws Exception {
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pstmt=con.prepareStatement("delete from suppliersProducts where id="+supplierId);
		pstmt.executeUpdate();
	}

}