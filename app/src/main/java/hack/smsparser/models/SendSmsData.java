package hack.smsparser.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SendSmsData implements Serializable {

    @Expose
    public String contact;

    @Expose
    public String message;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendSmsData(String contact, String message) {
        this.contact = contact;
        this.message = message;
    }

    @Override
    public String
    toString() {
        return "SendSmsData{" +
                "contact='" + contact + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
