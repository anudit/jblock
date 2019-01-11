package jBlock.Objects;

import java.util.Date;
import com.google.gson.GsonBuilder;

import jBlock.StringUtils;

public class Transaction {

    public String to;
	public String from;
	public int amount;
	public Token token;
	public long timeStamp;
	public String txhash;

	public Transaction(String to, String from, int amount, Token token) {
		this.to = to;
		this.from = from;
		this.amount = amount;
		this.token = token;
		this.timeStamp = new Date().getTime();
		this.txhash = generateTxHash();
	}

	public String generateTxHash() {
		String calculatedhash = StringUtils.sha256(txhash + Long.toString(timeStamp));
		return calculatedhash;
	}

}