package com.example.medabde.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;


    public void show(int progress){
        int proMin = progress/60;
        int proSec = progress - proMin*60;

        String proMinStr= "0"+ proMin;
        String proSecStr = "0"+proSec;
        if(proMin<10&&proSec<10){
            textView.setText(proMinStr+":"+proSecStr);
        }else if(proMin<10){
            textView.setText(proMinStr+":"+proSec);
        }else{
            textView.setText(proMin+":"+proSecStr);
        }

    }
    boolean isCounting=true;
    CountDownTimer count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.time);
        button = findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                show(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCounting){
                    count = new CountDownTimer(seekBar.getProgress()*1000+100,1000) {
                        @Override
                        public void onTick(long l) {
                            show((int)l/1000);
                        }
                        @Override
                        public void onFinish() {
                            textView.setText("00:00");
                            Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                    seekBar.setEnabled(false);
                    button.setText("stop");
                    isCounting = false;
                }else {
                    button.setText("start");
                    seekBar.setEnabled(true);
                    count.cancel();
                    textView.setText("00:10");
                    seekBar.setProgress(10);
                    isCounting = true;
                }
            }
        });
    }
}
