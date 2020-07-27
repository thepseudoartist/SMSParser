package hack.smsparser;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

import hack.smsparser.models.SendSmsData;
import hack.smsparser.models.SendSmsResponse;
import hack.smsparser.models.SmsResponse;
import hack.smsparser.models.SmsStructure;
import hack.smsparser.restapi.APIServices;
import hack.smsparser.restapi.AppClient;
import hack.smsparser.smsparser.SMSBroadcastReceiver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SMSBroadcastReceiver.MessageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isSmsPermissionGranted())
            requestReadAndSendSmsPermission();

        SMSBroadcastReceiver.bindListener(this);
        ((TextView) findViewById(R.id.text_main)).setText("Listening to SMS.");

    }

    public boolean isSmsPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_LONG).show();
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1010);
    }

    @Override
    public void messageReceived(String message) {
        Toast.makeText(this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();
        apiCall(new SmsStructure(message.split(":")[0], message.split(":")[1]));
    }

    private void apiCall(SmsStructure structure) {
        Call<SmsResponse> call = AppClient.getInstance().createService(APIServices.class).sendSmsData(structure);

        call.enqueue(new Callback<SmsResponse>() {
            @Override
            public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                if (response.isSuccessful())
                    Log.i(MainActivity.class.getName(), "Message : " + structure.message);
            }

            @Override
            public void onFailure(Call<SmsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "SMS Sending Failed.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
