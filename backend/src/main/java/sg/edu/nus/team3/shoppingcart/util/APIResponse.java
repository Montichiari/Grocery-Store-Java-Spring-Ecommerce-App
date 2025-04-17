package sg.edu.nus.team3.shoppingcart.util;

import java.util.HashMap;

public class APIResponse {
    private String status;
    private String message;

    public APIResponse() {
        this.status = "400";
        this.message = "Something has gone wrong.";
    }

    public HashMap<String, String> getResponse() {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", this.status);
        response.put("message", this.message);
        return response;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
