package datingapp.datingapp.dto;

public class ResponseData {
    private String message;

    public ResponseData() {
        // Default constructor for deserialization
    }

    public ResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
