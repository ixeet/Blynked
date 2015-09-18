package com.example.admin.blynked;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by PRIYANKA GUPTA on 6/19/2015.
 */
public class New extends BroadcastReceiver
{
    SmsMessage[] msgs = null;
    int f;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String messageReceived = "";
    String phoneNumber="";
    String phoneNo;
    public static final String STORAGE_NAME = "MySharedPreferencess";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            //---get the SMS message passed in---
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                //---retrieve the SMS message received---
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++)

                {

                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    phoneNumber = msgs[i].getDisplayOriginatingAddress();
                    messageReceived += msgs[i].getMessageBody().toString();
                    messageReceived += "\n";
                    String senderNum = phoneNumber;
                    String message = msgs[i].getDisplayMessageBody();

                }
                SharedPreferences sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

                phoneNo = sharedPreferences.getString("phoneNo", "");
             //   String p = "+91" + phoneNo;
                try {
                //    if (phoneNumber.equals(p)) {
                        //           this.context=context;
                        //
                        //  Toast.makeText(context, phoneNumber, Toast.LENGTH_SHORT).show();
                        // Otp Sms = new Otp();
                        //Sms.recivedSms(messageReceived);
                        //  SharedPreferences sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                     //   editor.putString("message", messageReceived);
                        editor.putString("flag","yes");
                        editor.apply();
                     //   Intent i = new Intent(context, Otp.class);
                        //currentContext.startActivity(activityChangeIntent);
                        // activityChangeIntent.putExtra("message","");
                        //   i.putExtra("messageReceived","");
                     //   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                      //  context.startActivity(i);
                //    }

                } catch (Exception e) {
                }


                //  Toast.makeText(context, messageReceived ,Toast.LENGTH_SHORT).show();

                //---display the new SMS message---


                // Get the Sender Phone Number

                String senderPhoneNumber = msgs[0].getOriginatingAddress();

            }
        }

    }
}


