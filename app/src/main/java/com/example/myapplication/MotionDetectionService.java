package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class MotionDetectionService extends Service implements SensorEventListener {

    private static final String TAG = "MotionService";
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    public void onCreate(){
        super.onCreate();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId ){
        Log.d(TAG, "Service Started");

        return START_STICKY;
    }

    @Override
    public void onSensorChanged (SensorEvent event){

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
