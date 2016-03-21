package cn.zxnui.www.busticket.util;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zxnui on 2016/3/17.
 */
public class PicEdit {
    public static Bitmap doPic(InputStream in,InputStream bus,String num,String time,String busNum){
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        Bitmap busmap = BitmapFactory.decodeStream(bus);
        Matrix matrix = new Matrix();
        matrix.postScale(1.5f,1.5f); //长和宽放大缩小的比例
        busmap = Bitmap.createBitmap(busmap,0,0,busmap.getWidth(),busmap.getHeight(),matrix,true);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }

        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        Rect bounds = new Rect();

        //产生渐进矩形颜色，覆盖原来的信息
        int color_begin = bitmap.getPixel(50,260);
        int color_end = bitmap.getPixel(50,650);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Shader m = new LinearGradient(50,260,50,670,new int[]{color_begin,color_end},null,Shader.TileMode.REPEAT);
        paint.setShader(m);
        canvas.drawRect(50, 260, 680, 650, paint);

        //巴哥水印
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAlpha(75);
        canvas.drawBitmap(busmap,230,340,null);

        //随机数
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(150);
        paint.getTextBounds(num, 0, num.length(), bounds);
        canvas.drawText(num, 130, 400, paint);
        // 序列号
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(150);
        paint.getTextBounds(num, 0, 2, bounds);
        canvas.drawText(String.valueOf((int) (Math.random() * 45) + 1), 420, 400, paint);
        //上车时间
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(75);
        paint.getTextBounds(time, 0, time.length(), bounds);
        canvas.drawText(time, 315, 565, paint);
        //班次
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        canvas.drawRect(120, 880, 180, 920, paint);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.getTextBounds(time, 0, time.length(), bounds);
        canvas.drawText(time, 130, 908, paint);
        //车牌
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        canvas.drawRect(375, 880, 480, 920, paint);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.getTextBounds(busNum, 0, busNum.length(), bounds);
        canvas.drawText(busNum, 380, 908, paint);
        //乘车时间
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        canvas.drawRect(509, 880, 630, 920, paint);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String now = f.format(new Date());
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.getTextBounds(now, 0, now.length(), bounds);
        canvas.drawText(now, 515, 908, paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bitmap;
    }
}
