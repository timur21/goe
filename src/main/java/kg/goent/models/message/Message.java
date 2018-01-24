package kg.goent.models.message;

/**
 * Created by azire on 5/14/2017.
 */

public class Message {
    private String message;
    private int type;

    public Message() {
    }

    public Message(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
