package jBlock;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

import jBlock.Objects.Token;
import jBlock.Objects.Transaction;
import jBlock.Objects.ResponseObject;
import jBlock.Server;

public class MainChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static ArrayList<Token> Tokens = new ArrayList<Token>();
	public static ArrayList<Transaction> Transactions = new ArrayList<Transaction>();

	public static int difficulty = 1;

	public static void main(String[] args) {	
		
		addToken("Ethereum", "ETH");

		addTransaction("0x0", "0x1", 12, returnToken("ETH"));
		addTransaction("0x1", "0x0", 8, returnToken("ETH"));
		mineBlock();

		String blockchainJson = StringUtils.getJson(blockchain);
		System.out.println(blockchainJson);

		System.out.println(blockchain.get(blockchain.size() - 1).returnData());
		Server.startServer();
	}
	
	public static ResponseObject validateChain() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				// System.out.println("Current Hashes not equal");	
				ResponseObject res = new ResponseObject("Current Hashes not equal", false);
				return res;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				// System.out.println("Previous Hashes not equal");
				ResponseObject res = new ResponseObject("Previous Hashes not equal", false);
				return res;
			}
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				// System.out.println("This block hasn't been mined");
				ResponseObject res = new ResponseObject("This block hasn't been mined", false);
				return res;
			}
			
		}
		return (new ResponseObject("The Chain is Valid", true));
	}
	
	public static void mineBlock() {
		String transactionsJson = StringUtils.getJson(Transactions);
		Transactions.clear();
		System.out.println("\nMining Block #" + Integer.toString(blockchain.size()+1));
		Block newBlock = new Block( transactionsJson , ((blockchain.size() == 0) ? "0" : blockchain.get(blockchain.size()-1).hash ) );
		System.out.println(StringUtils.getJson(newBlock.mineBlock(difficulty)));
		blockchain.add(newBlock);
	}

	public static Block returnBlock(int index){
		if(index <= blockchain.size()){
			return blockchain.get(index);
		}
		else {
			return null;
		}
	}

	public static ArrayList<Block> returnChain(){
		return blockchain;
	}

	public static int returnChainLength(){
		return blockchain.size();
	}

	public static void addTransaction(String to, String from, int amount, Token token){
		Transaction trans = new Transaction(to, from, amount, token);
		Transactions.add(trans);
	}

	public static void addToken(String name, String symbol){
		Token tok = new Token(name, symbol);
		Tokens.add(tok);
	}

	public static Token returnToken(String symbol){
		for (int i=0; i <= Tokens.size(); i++){
			Token t1 = Tokens.get(i);
			if(t1.symbol == symbol){
				return t1;
			}
		}
		return (new Token("null", "null"));
	}

}
