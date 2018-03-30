package com.demo.tomcat.alarmnotification2;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.service.voice.VoiceInteractionService;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.content.Context.BIND_IMPORTANT;

public class AlarmService extends IntentService
{
    private static final String TAG = AlarmService.class.getSimpleName();
     NotificationManager     alarmNotificationManager;

    public AlarmService()
    {
        super("AlarmService");
        Log.w(TAG, "AlarmService() constructor, ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Log.w(TAG, "onHandleIntent(), ");
        sendNotification("Wake Up! Wake Up!");
    }

    private void sendNotification(String msg)
    {
        Log.w(TAG, "sendNotification(), ");

        if (alarmNotificationManager == null) {
            alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, MainActivity.class), 0);

            NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("Alarm")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setContentText(msg);

            alarmNotificationBuilder.setContentIntent(contentIntent);
            alarmNotificationManager.notify(1, alarmNotificationBuilder.build());
            Log.d(TAG, "Notification sent.");
        }
        else
        {
            alarmNotificationManager.cancelAll();
        }
    }

}

//public class AlarmService extends Service
//{
//    private static final String TAG = AlarmService.class.getSimpleName();
//    private final IBinder binder = new AlarmServiceBinder();
//
//    NotificationManager     alarmNotificationManager;
//
//
//    public AlarmService()
//    {
//        //super("AlarmService");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.w(TAG, "onCreate(), ");
//
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        //return super.onStartCommand(intent, flags, startId);
//        String msg = intent.getStringExtra("MSG");
//        sendNotification(msg);
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        // throw new UnsupportedOperationException("Not yet implemented");
//
//        return binder;
//    }
//
//    private void sendNotification(String msg)
//    {
//        Log.w(TAG, "sendNotification(), ");
//
//        alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), 0);
//
//        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this)
//                .setContentTitle("Alarm")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
//                .setContentText(msg);
//
//        alarmNotificationBuilder.setContentIntent(contentIntent);
//        alarmNotificationManager.notify(1, alarmNotificationBuilder.build());
//        Log.d(TAG, "Notification sent.");
//    }
//
//
//    //--------------- inner class ------------------//
//    public class AlarmServiceBinder extends Binder
//    {
//        AlarmService getAlarmService()
//        {
//            Log.w(TAG, "getAlarmService(), ");
//            return AlarmService.this;
//        }
//    };
//}

