package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MotionDetectionService extends Service {

    private static final String TAG = "MotionService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId ){
        Log.d(TAG, "Service Started");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
