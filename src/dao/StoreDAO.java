package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.StoreDaoInterface;
import utility.ConnectionManager;

public class StoreDAO implements StoreDaoInterface {

	@Override
	public void addProductsToStore(int supplierId, int quantity) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement p = con.prepareStatement("select quantity from suppliersproducts where id =" + supplierId);
		ResultSet result = p.executeQuery();
		result.next();
		if (result.getInt("quantity") - quantity < 0) {
			System.out.println("Doesn't have enough quantity in store");
		} else {

			PreparedStatement pstmt = con.prepareStatement(
					"select productsinstore.productid from productsinstore,suppliersproducts where productsinstore.productid =suppliersproducts.productid and suppliersproducts.id="
							+ supplierId);

			if (pstmt.executeUpdate() == 0) {
				PreparedStatement pstmt1 = con.prepareStatement(
						"select * from suppliersproducts where id =" + supplierId + " and quantity >=" + quantity + "");
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
				String query = "insert into productsinstore values(?,?,?,?,?,?)";
				PreparedStatement pstmt2 = con.prepareStatement(query);
				pstmt2.setInt(1, rs.getInt("productid"));
				pstmt2.setString(2, rs.getString("productName"));
				pstmt2.setString(3, rs.getString("category"));
				pstmt2.setString(4, rs.getString("brand"));
				pstmt2.setInt(5, quantity);
				pstmt2.setInt(6, rs.getInt("price"));

				if (pstmt2.executeUpdate() != 0) {
					PreparedStatement pstmt3 = con
							.prepareStatement("update suppliersproducts set quantity = quantity-? where id = ?");
					pstmt3.setInt(1, quantity);
					pstmt3.setInt(2, supplierId);
					pstmt3.executeUpdate();
				}
			} else {
				PreparedStatement ps = con.prepareStatement(
						"select * from suppliersproducts where id =" + supplierId + " and quantity >=" + quantity + "");
				ResultSet rs = ps.executeQuery();
				rs.next();

				PreparedStatement pstmt1 = con
						.prepareStatement("update productsinstore set quantity = quantity+? where productid = ?");
				pstmt1.setInt(1, quantity);
				pstmt1.setInt(2, rs.getInt("productId"));
				pstmt1.executeUpdate();

				PreparedStatement pstmt3 = con
						.prepareStatement("update suppliersproducts set quantity = quantity-? where id = ?");
				pstmt3.setInt(1, quantity);
				pstmt3.setInt(2, supplierId);
				pstmt3.executeUpdate();
			}
			System.out.println("Successully added products to store");
		}
	}

	@Override
	public void displayCustomerPurchases() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement(
				"select customerpurchases.id,customerpurchases.productname,customerpurchases.category,customerpurchases.quantity,customerpurchases.price,customerdata.name from customerpurchases,customerdata where customerpurchases.id=customerdata.id order by customerdata.name");
		ResultSet rs = pstmt.executeQuery();
		System.out.printf("ID          Name           Category         ProductName           Quantity        Price %n");
		System.out.println(
				"______________________________________________________________________________________________");
		while (rs.next()) {
			System.out.printf("%-6d %10s %15s %20s %15d %15d %n", rs.getInt("id"), rs.getString("name"),
					rs.getString("category"), rs.getString("productname"), rs.getInt("quantity"),
					rs.getInt("price") * rs.getInt("quantity"));
		}
		System.out.println(
				"______________________________________________________________________________________________");
	}

	@Override
	public void displayProductsInStore() throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement("select * from productsInStore");
		ResultSet rs = pstmt.executeQuery();
		System.out.printf("ID             Category         ProductName           Quantity        Price %n");
		System.out.println("__________________________________________________________________________________");
		while (rs.next()) {
			System.out.printf("%-6d %15s %20s %15d %15d %n", rs.getInt("productid"), rs.getString("category"),
					rs.getString("productname"), rs.getInt("quantity"), rs.getInt("price"));
		}
		System.out.println("__________________________________________________________________________________");
	}

}
