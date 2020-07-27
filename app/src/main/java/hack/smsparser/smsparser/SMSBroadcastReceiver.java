package hack.smsparser.smsparser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private static MessageListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
       if(intent.getAction() != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

           Bundle bundle = intent.getExtras();
           SmsMessage[] msgs;
           StringBuilder strMessage = new StringBuilder();
           String format = bundle.getString("format");

           Object[] pdus = (Object[]) bundle.get("pdus");

           if (pdus != null) {

               msgs = new SmsMessage[pdus.length];
               for (int i = 0; i < msgs.length; i++) {

                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                   } else {
                       msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                   }

                   strMessage.append("").append(msgs[i].getOriginatingAddress());
                   strMessage.append(":").append(msgs[i].getMessageBody()).append("\n");
               }


               Log.d(SMSBroadcastReceiver.class.getName(), "onReceive: " + strMessage);
               mListener.messageReceived(strMessage.toString());
           }
       }
    }

    public static void bindListener (MessageListener listener){
        mListener = listener;
    }

    public interface MessageListener {
        void messageReceived(String message);
    }
}
