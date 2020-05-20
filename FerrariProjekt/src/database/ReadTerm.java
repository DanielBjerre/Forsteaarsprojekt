package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Offer;
import entities.Term;

public class ReadTerm {
    public ArrayList<Term> findTerm(Offer offer) {
		ArrayList<Term> alTerm = new ArrayList<Term>();
		try (Connection con = new dbConnection().newConnection()) {
            String sql = "SELECT * FROM term WHERE offerID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, offer.getOfferID());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Term t = new Term();
                    t.setTermNumber(rs.getInt("termNumber"));
                    t.setPreviousBalance(rs.getString("previousBalance"));
                    t.setPayment(rs.getString("payment"));
                    t.setInterest(rs.getString("interest"));
                    t.setPrincipal(rs.getString("principal"));
                    t.setNewBalance(rs.getString("newBalance"));
					alTerm.add(t);
				}

			} catch (SQLException e) {
			}
		} catch (SQLException e) {
		}
		return alTerm;
	}
}