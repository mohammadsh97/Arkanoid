package com.mohammadSharabati_ahmadSharabati.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "" ;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private GameView gameV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor == null) { // this sensor NOT exists on this device!
            Toast.makeText(getApplicationContext(), "this sensor NOT exists on this device!", Toast.LENGTH_SHORT).show();
            finish();
        }
        gameV = (GameView) findViewById(R.id.GameView);
        System.out.println((gameV == null) + "hello bitch");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        float x = event.values[0]; // Acceleration force along the x axis
        float y = event.values[1]; // Acceleration force along the y axis
        float z = event.values[2]; // Acceleration force along the z axis
        Log.d(TAG, "onSensorChanged: the y is\n" + y+"this is x "+x+"this is z"+z);
        //  gameV.padMov();
        //break;
//        if(gameV.k != 0) {
            //   System.out.println("ana hooon  "+(int)gameV.ball.gety());
            if(y!=0)
                gameV.paddle.p.setColor(Color.BLUE);
            else
                gameV.paddle.p.setColor(Color.RED);

            // gameV.padMov(x);
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
