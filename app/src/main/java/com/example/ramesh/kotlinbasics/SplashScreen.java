package com.example.ramesh.kotlinbasics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        TextView textView = (TextView) findViewById(R.id.textView53);
        TextView textView1 = (TextView) findViewById(R.id.textView54);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        Animation animation5 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        imageView.startAnimation(animation5);
        textView.startAnimation(animation2);
        textView1.startAnimation(animation2);

        Thread timer = new Thread(){

            public void run(){
                try{
                    sleep(2000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }
                finally{
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        };timer.start();
    }
}
