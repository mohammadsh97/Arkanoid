package com.mohammadSharabati_ahmadSharabati.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import static com.mohammadSharabati_ahmadSharabati.ex2.GameView.State.GET_READY;
import static com.mohammadSharabati_ahmadSharabati.ex2.GameView.State.PLAYING;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "myApp";
    public GameView gameV;
    public float z;
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    private Thread task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometerSensor, sensorManager.SENSOR_STATUS_ACCURACY_HIGH);
        if (accelerometerSensor == null) {
            Toast.makeText(this, "No Sensor Found !\n Good Bye", Toast.LENGTH_LONG).show();
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameV = (GameView) findViewById(R.id.GameView);
        System.out.println((gameV == null) + "hello bitch");
    }


    @Override
    public void onSensorChanged(SensorEvent event) {


        float x = event.values[0]; // Acceleration force along the x axis
        final float y = event.values[1]; // Acceleration force along the y axis
        float z = event.values[2]; // Acceleration force along the z axis
        Log.d(TAG, "onSensorChanged: the y is  " + y + "this is x " + x + "this is z  " + z);
        //  gameV.padMov();
        //break;
        if (gameV.k != 0) {
            task = new Thread() {
                @Override
                public void run() {
                    if (gameV.state == PLAYING) {
                        if (y < -1.9f) {
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                            gameV.paddle.moveLeft();
                        } else if (y > 1.9f && gameV.paddle.getLeft() < gameV.getWidth()) {
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                            gameV.paddle.moveRight(gameV.getWidth());
                        }

                    }
                }
            };
            task.start();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}