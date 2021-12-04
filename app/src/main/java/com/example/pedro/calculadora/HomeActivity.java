package com.example.pedro.calculadora;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class HomeActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;
    private static final String DEBUG = "Gesture";
    private int status = 0;
    private static float minSpeed = 1000;
    private static float minDistance = 100;

    @Override
    public void onCreate (Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.layout_inicio);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onResume(){
        super.onResume();
        status = 0;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown (MotionEvent event){

            if (status == 1){
                //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(intent);
                status = 0;
            }
            status += 1;
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            float distance = event2.getX() - event1.getX();
            float speed = Math.abs(velocityX);
            Log.d(DEBUG,String.valueOf(distance) + " ooo " + String.valueOf(speed));

            //Testa se o movimento é para a esquerda
            if (distance > 0){
                return true;
            }
            //Configuração do movimento
            if ((Math.abs(distance) > minDistance) && (speed > minSpeed)){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                status = 0;
            }
            return true;
        }
    }
}
