package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Offer;
import entities.Term;

public class CreateTerm {
	public CreateTerm(int offerID, Offer offer) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "INSERT INTO Term" + "(offerID, termNumber, previousBalance, payment, "
					+ "interest, principal, newBalance) values (?,?,?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			for (Term term : offer.getPeriods()) {
				stmt.setInt(1, offerID);
				stmt.setString(2, term.getTermNumber());
				stmt.setString(3, term.getPreviousBalance());
				stmt.setString(4, term.getPayment());
				stmt.setString(5, term.getInterest());
				stmt.setString(6, term.getPrincipal());
				stmt.setString(7, term.getNewBalance());
				stmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
