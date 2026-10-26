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

    private float x;
    private float y;
    private float z;

    private float oldX;
    private float oldY;
    private float oldZ;

    private static final float MOVEMENT_THRESHOLD = 2.0f;
    private boolean monitoring = true;


    @Override
    public void onCreate(){
        super.onCreate();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId ){
        Log.d(TAG, "Service Started");

        if (accelerometer != null){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        Thread backgroundthread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(monitoring){
                    float changeX = Math.abs(x - oldX);
                    float changeY = Math.abs(y - oldY);
                    float changeZ = Math.abs(z- oldZ);
                    if(changeX > MOVEMENT_THRESHOLD || changeY > MOVEMENT_THRESHOLD || changeZ > MOVEMENT_THRESHOLD){
                        Log.d(TAG, "MOVEMENT DETECTED");
                    }

                    oldX = x;
                    oldY = y;
                    oldZ = z;
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        backgroundthread.start();
        return START_STICKY;
    }

    @Override
    public void onSensorChanged (SensorEvent event){
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onDestroy(){
        monitoring = false;
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }

}
