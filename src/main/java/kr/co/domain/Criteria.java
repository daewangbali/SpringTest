package kr.co.domain;

public class Criteria {
	
	private int pageNum, amount;

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria() {
		this(1, 10);
	}
	

}