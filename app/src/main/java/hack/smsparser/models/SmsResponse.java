package hack.smsparser.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SmsResponse implements Serializable {

    @Expose
    public String message;
}
