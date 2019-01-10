package jBlock;

import com.google.gson.GsonBuilder;

public class ResponseObject {

    public String data;
	public Boolean bool;

	public ResponseObject(String data, Boolean bool) {
		this.data = data;
		this.bool = bool;
	}

	public String getJson() {
		ResponseObject res = new ResponseObject(this.data, this.bool);
		return new GsonBuilder().setPrettyPrinting().create().toJson(res);
	}

}

// ResponseObject res = new ResponseObject(  ,  );