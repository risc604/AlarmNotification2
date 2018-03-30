package com.demo.tomcat.alarmnotification2;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

// https://javapapers.com/android/android-alarm-clock-tutorial/


public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    TimePicker      alarmTimerPicker;
    ToggleButton    alarmToggle;
    TextView        alarmTextView;

    AlarmManager    alarmManager;
    PendingIntent   pendingIntent;
    //AlarmService    alarmServiceBinder;
    //ServiceConnection AlarmServiceConnection = new ServiceConnection() {
    //    @Override
    //    public void onServiceConnected(ComponentName name, IBinder service)
    //    {
    //        Log.w(TAG, "onServiceConnected(), ");
    //        alarmServiceBinder = ((AlarmService.AlarmServiceBinder) service).getAlarmService();
    //    }
    //
    //    @Override
    //    public void onServiceDisconnected(ComponentName name)
    //    {
    //        Log.w(TAG, "onServiceDisconnected(), ");
    //    }
    //};



    @SuppressLint("StaticFieldLeak")
    private static MainActivity inst;

    public static MainActivity instance()
    {
        Log.w(TAG, "instance(), ");
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.w(TAG, "onCreate(), ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();
    }

    @Override
    protected void onStart()
    {
        Log.w(TAG, "onStart(), ");
        super.onStart();
        inst = this;
    }

    public void onToggleClicked(View view)
    {
        Log.w(TAG, "onToggleClicked(), ");
        if (((ToggleButton)view).isChecked() && pendingIntent == null)
        {
            Log.d(TAG, "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimerPicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimerPicker.getCurrentMinute());
            Intent newIntent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                                            0, newIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
        else
        {
            alarmManager.cancel(pendingIntent);
            pendingIntent = null;
            setAlarmText("");
            Log.d(TAG, "Alarm Off");
        }
    }


    //------------------------- user function ---------------------------//
    private void initView()
    {
        Log.w(TAG, " initView(), ");
        alarmTimerPicker = findViewById(R.id.alarmTimerPicker);
        alarmToggle = findViewById(R.id.alarmToggle);
        alarmTextView = findViewById(R.id.alarmText);
    }

    private void initControl()
    {
        Log.w(TAG, " initControl(), ");
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    public void setAlarmText(String alarmText)
    {
        alarmTextView.setText(alarmText);
    }




}
