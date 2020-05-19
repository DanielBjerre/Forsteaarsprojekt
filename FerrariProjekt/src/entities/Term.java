package entities;

public class Term {
	private String termNumber;
	private String previousBalance;
	private String payment;
	private String interest;
	private String principal;
	private String newBalance;
	
	public String getPreviousBalance() {
		return previousBalance;
	}
	public void setPreviousBalance(String previousBalance) {
		this.previousBalance = previousBalance;
	}
	public String getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(String newBalance) {
		this.newBalance = newBalance;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getTermNumber() {
		return termNumber;
	}
	public void setTermNumber(String termNumber) {
		this.termNumber = termNumber;
	}
}
