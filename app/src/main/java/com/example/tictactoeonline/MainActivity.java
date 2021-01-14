package com.example.tictactoeonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.bg_image);
        lottie = findViewById(R.id.lottie);

        imageView.animate().translationY(2800).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationX(-2800).setDuration(1000).setStartDelay(4000);

        Thread timer  = new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
//                    overridePendingTransition(R.anim.startup_animation, R.anim.startup_animation);
                    finish();

                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}