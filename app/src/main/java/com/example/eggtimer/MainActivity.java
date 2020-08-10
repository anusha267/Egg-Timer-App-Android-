package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
     int activeT=0;
    int t;
    public void startTimer(View view)
    {
        if(activeT==0) {
            setCount(t);
            SeekBar s = findViewById(R.id.seekBar);
            s.setEnabled(false);
        }
        else
        {
            Toast.makeText(this, "Timer in Progress! Please Wait!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void setTxt(int m, int s)
    {
        TextView t= findViewById(R.id.textView);
        if(s<10) t.setText(m + ":0" + s);
        else
        {
            t.setText(m+ ":" +s);
        }
    }
    public void setCount(long i)
    {
        i=i*1000;
        new CountDownTimer(i,1000){
           @Override
            public void onTick(long i)
            {
                activeT=1;
                i=i/1000;
                setTxt((int) (i/60), (int) (i%60) );
                SeekBar s= findViewById(R.id.seekBar);

                s.setProgress((int)i);
                Log.i("left",Long.toString(i));
            }


           public void onFinish() {
               SeekBar s=findViewById(R.id.seekBar);
               s.setEnabled(true);
               activeT=0;

           }
       }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTxt(0,0);


        SeekBar s=findViewById(R.id.seekBar);

        s.setMax(600);

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int m,s;
                m=i/60;
                s=i%60;
                setTxt(m,s);
                t=i;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });
    }
}