package jBlock.Objects;

import com.google.gson.GsonBuilder;

public class Token {

    public String name;
	public String symbol;

	public Token(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getJson() {
		Token res = new Token(this.name, this.symbol);
		return new GsonBuilder().setPrettyPrinting().create().toJson(res);
	}

}