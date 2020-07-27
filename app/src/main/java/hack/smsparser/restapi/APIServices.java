package hack.smsparser.restapi;

import hack.smsparser.models.SmsResponse;
import hack.smsparser.models.SmsStructure;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIServices {

    @POST("transaction/message/")
    Call<SmsResponse> sendSmsData(@Body SmsStructure smsStructure);
}
