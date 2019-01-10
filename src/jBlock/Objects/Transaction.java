package jBlock.Objects;

import com.google.gson.GsonBuilder;

public class Transaction {

    public String to;
	public String from;
	public int amount;
	public Token token;

	public Transaction(String to, String from, int amount, Token token) {
		this.to = to;
		this.from = from;
		this.amount = amount;
		this.token = token;
	}

	public String getJson() {
		Transaction res = new Transaction(this.to, this.from, this.amount, this.token);
		return new GsonBuilder().setPrettyPrinting().create().toJson(res);
	}

}