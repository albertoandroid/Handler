package com.androiddesdecero.handleryoutube;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvMainActivity;
    private static final String MENSAJE = "mensaje";
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMainActivity = findViewById(R.id.tvMainActivity);

        handler = new Handler(){
            @Override
            public void handleMessage(Message message){
                Bundle bundle = message.getData();
                tvMainActivity.setText(bundle.getString(MENSAJE));
            }
        };

        Thread thread = new Thread(new MiHilo());
        thread.start();
    }

    class MiHilo implements Runnable{
        @Override
        public void run(){
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString(MENSAJE, "Petición al servidor Correcta");
            message.setData(bundle);
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){

            }
            //tvMainActivity.setText(bundle.getString(MENSAJE));
            handler.sendMessage(message);
        }
    }
}
