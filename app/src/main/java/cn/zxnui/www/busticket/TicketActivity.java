package cn.zxnui.www.busticket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

import cn.zxnui.www.busticket.util.PicEdit;

/**
 * Created by zxnui on 2016/3/17.
 */
public class TicketActivity extends Activity {

    private Handler handler;
    private int i=0;
    private ImageView clock;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);
        clock = (ImageView) findViewById(R.id.clock);

        ImageView v = (ImageView) findViewById(R.id.bus_pic);
        Intent intent = getIntent();
        InputStream in = this.getResources().openRawResource(R.raw.card);
        InputStream bus = this.getResources().openRawResource(R.raw.bus);
        Bitmap b =  PicEdit.doPic(in,bus, intent.getStringExtra("num"), intent.getStringExtra("time"), intent.getStringExtra("busNum"));
        v.setImageBitmap(b);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what==1) {
                    if (i == 0) {
                        clock.setVisibility(View.GONE);
                        i = 1;
                    } else {
                        clock.setVisibility(View.VISIBLE);
                        i = 0;
                    }
                }else {
                    super.sendMessage(msg);
                }
            }
        };
        new Thread(new timeRun()).start();
    }

    public class timeRun implements Runnable{
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(100);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
