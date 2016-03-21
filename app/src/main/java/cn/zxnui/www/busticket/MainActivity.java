package cn.zxnui.www.busticket;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import cn.zxnui.www.busticket.util.PicEdit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updatePic(View view){
        ImageView image = (ImageView) findViewById(R.id.image_pic);
    }

    public void getPicByMess(View v){
        EditText firstNum =(EditText) findViewById(R.id.first_num);
        EditText time =(EditText) findViewById(R.id.second_num);
        EditText bus_num =(EditText) findViewById(R.id.three_num);

        Intent intent = new Intent(MainActivity.this,TicketActivity.class);
        intent.putExtra("num",firstNum.getText().toString());
        intent.putExtra("time",time.getText().toString());
        intent.putExtra("busNum",bus_num.getText().toString());
        startActivity(intent);
    }
}
