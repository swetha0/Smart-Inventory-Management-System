package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.CustomerDaoInterface;
import utility.ConnectionManager;

public class CustomerDAO implements CustomerDaoInterface {

	@Override
	public void displayCustomerPurchases(String email) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement(
				"select customerpurchases.productname,customerpurchases.category,customerpurchases.quantity,customerpurchases.price,customerdata.name from customerpurchases,customerdata where customerpurchases.id=customerdata.id and customerdata.email=?");
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		System.out.printf("Name             Category         ProductName           Quantity        Price %n");
		System.out.println("__________________________________________________________________________________");
		while (rs.next()) {
			System.out.printf("%-6s %15s %20s %15d %15d %n", rs.getString("name"),rs.getString("category"),rs.getString("productname"),rs.getInt("quantity"),rs.getInt("price")*rs.getInt("quantity"));
		}
		System.out.println("__________________________________________________________________________________");
	}

	@Override
	public void user(String email, int productId, int quantity) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement p = con
				.prepareStatement("select quantity from productsinstore where productid =" + productId);
		ResultSet result = p.executeQuery();
		result.next();
		if (result.getInt("quantity") - quantity < 0) {
			System.out.println("Supplier Doesn't have enough quantity");
		} else {

			PreparedStatement pst = con.prepareStatement("select * from customerdata where email = ?");
			pst.setString(1, email);
			ResultSet rs1 = pst.executeQuery();
			rs1.next();

			PreparedStatement pstmt = con.prepareStatement("select id from customerpurchases where productid="
					+ productId + " and id=" + rs1.getInt("id") + "");
			if (pstmt.executeUpdate() == 0) {
				PreparedStatement pstmt1 = con
						.prepareStatement("select * from productsinstore where productid =" + productId);
				ResultSet rs = pstmt1.executeQuery();
				rs.next();

				String query = "insert into customerpurchases values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt2 = con.prepareStatement(query);
				pstmt2.setInt(1, rs1.getInt("id"));
				pstmt2.setInt(2, rs.getInt("productid"));
				pstmt2.setString(3, rs.getString("productName"));
				pstmt2.setString(4, rs.getString("category"));
				pstmt2.setString(5, rs.getString("brand"));
				pstmt2.setInt(6, quantity);
				pstmt2.setInt(7, rs.getInt("price"));

				if (pstmt2.executeUpdate() != 0) {
					PreparedStatement pstmt3 = con
							.prepareStatement("update productsinstore set quantity = quantity-? where productid = ?");
					pstmt3.setInt(1, quantity);
					pstmt3.setInt(2, productId);
					pstmt3.executeUpdate();
				}
			}

			else {
				PreparedStatement stmt = con.prepareStatement("select id from customerpurchases where productid="
						+ productId + " and id=" + rs1.getInt("id") + "");
				ResultSet stmtRs = stmt.executeQuery();
				stmtRs.next();

				PreparedStatement ps = con
						.prepareStatement("select productid from productsinstore where productid =" + productId);
				ResultSet rs = ps.executeQuery();
				rs.next();

				PreparedStatement pstmt1 = con.prepareStatement(
						"update customerpurchases set quantity = quantity+? where productid = ? and id =?");
				pstmt1.setInt(1, quantity);
				pstmt1.setInt(2, rs.getInt("productId"));
				pstmt1.setInt(3, stmtRs.getInt("id"));
				pstmt1.executeUpdate();

				PreparedStatement pstmt2 = con.prepareStatement(
						"update customerpurchases set quantity = quantity-? where productid = ? and id =?");
				pstmt2.setInt(1, quantity);
				pstmt2.setInt(2, rs.getInt("productId"));
				pstmt2.setInt(3, stmtRs.getInt("id"));
				pstmt2.executeUpdate();

				if (pstmt1.executeUpdate() != 0) {
					PreparedStatement pstmt3 = con
							.prepareStatement("update productsinstore set quantity = quantity-? where productid = ?");
					pstmt3.setInt(1, quantity);
					pstmt3.setInt(2, productId);
					pstmt3.executeUpdate();
				}
			}
			System.out.println("Successully added products to store");
		}
	}
}
