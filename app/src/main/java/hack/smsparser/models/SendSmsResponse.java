package hack.smsparser.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class SendSmsResponse implements Serializable {

    @Expose
    public List<SendSmsData> data;
}
