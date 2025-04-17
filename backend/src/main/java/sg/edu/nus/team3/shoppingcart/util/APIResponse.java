package sg.edu.nus.team3.shoppingcart.util;

import java.util.HashMap;

/**
 * @author Jared Chua, Dion Yao
 */

public class APIResponse {
    private String Error;

    public APIResponse() {
        this.Error = "Something has gone wrong.";
    }
    
    public APIResponse(String message) {
        this.Error = message;
    }

	public String getError() {
		return Error;
	}

	public void setError(String message) {
		this.Error = message;
	}

    /*
    public HashMap<String, String> getResponse() {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", this.messageHead);
        response.put("message", this.message);
        return response;
    }
    */


    

    
    
    
    
    

}
