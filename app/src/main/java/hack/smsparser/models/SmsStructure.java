package hack.smsparser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsStructure {

    @SerializedName("contact")
    @Expose
    public String contact;

    @SerializedName("message")
    @Expose
    public String message;

    public SmsStructure(String contact, String message) {
        this.contact = contact;
        this.message = message;
    }

    @Override
    public String toString() {
        return "SmsStructure{" +
                "contact='" + contact + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
