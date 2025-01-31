package com.example.display;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rootView;
    ImageView imgView;
    TextView viewSize;
    TextView imgSize;
    float screen_density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (RelativeLayout) findViewById(R.id.view_container);
        imgView =(ImageView) findViewById(R.id.imageView1);
        TextView screenSizePx = (TextView) findViewById(R.id.textView1);
        TextView screenDensity = (TextView) findViewById(R.id.textView2);
        TextView screenSizeDip = (TextView) findViewById(R.id.textView3);
        TextView screenSizeCm = (TextView) findViewById(R.id.textView4);
        TextView screenRotation = (TextView) findViewById(R.id.textView5);
        viewSize = (TextView) findViewById(R.id.textView6);
        imgSize = (TextView) findViewById(R.id.textView7);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        Display display = getWindowManager().getDefaultDisplay();

        //Dimensione display, in pixel reali
        Point size = new Point();
        display.getSize(size);
        int screenw_px = size.x;
        int screenh_px = size.y;

        //Densità
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_density = metrics.density;

        //Dimensioni in dip
        int screenw_dp = (int)(screenw_px/screen_density);
        int screenh_dp = (int)(screenh_px/screen_density);

        //Dimensioni in cm
        float screenw_cm = 2.54f*screenw_dp/160;
        float screenh_cm = 2.54f*screenh_dp/160;

        String str;
        int textSize = 0;

        int rotation = display.getRotation();
        switch(rotation) {
            case Surface.ROTATION_90:
                str = "ROTATION_90";
                break;
            case Surface.ROTATION_0:
                str = "ROTATION_0";
                break;
            case Surface.ROTATION_180:
                str = "ROTATION_180";
                break;
            case Surface.ROTATION_270:
                str = "ROTATION_270";
                break;
            default:
                str = "ROTATION_UNKNOWN";
        }
        screenRotation.setText("Rotazione: "+str);

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            textSize = (int)(screenw_px/(30*screen_density));
            //textSize = (int)(screenw_px/(50));
        }
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            textSize = (int)(screenw_px/(30*screen_density));
            //textSize = (int)(screenw_px/(30));
        }


        Log.d("DEBUG","textSize="+textSize);

        screenRotation.setTextSize(textSize);
        viewSize.setTextSize(textSize);
        imgSize.setTextSize(textSize);
        screenSizeCm.setTextSize(textSize);
        screenSizeDip.setTextSize(textSize);
        screenDensity.setTextSize(textSize);
        screenSizePx.setTextSize(textSize);
        button1.setTextSize(textSize);
        button2.setTextSize(textSize);

        screenSizePx.setText("w="+screenw_px+"px x h="+screenh_px+"px (pixel reali)");
        screenDensity.setText("Densità = "+screen_density);
        screenSizeDip.setText("w="+screenw_dp+"dip x h="+screenh_dp+"dip (pixel dip)");
        str = String.format("w=%5.2fcm x h=%5.2fcm", screenw_cm, screenh_cm);
        screenSizeCm.setText(str);


        int view_h = rootView.getHeight();
        int view_w = rootView.getWidth();
        float vieww_cm = pxToCm(view_w,screen_density);
        float viewh_cm = pxToCm(view_h,screen_density);
        str = String.format("onCreate RootView w=%dpx (%5.2fcm) x h=%dpx (%5.2fcm)", view_w, vieww_cm, view_h, viewh_cm);
        viewSize.setText(str);

        int img_h = imgView.getHeight();
        int img_w = imgView.getWidth();
        float imgw_cm = pxToCm(img_w,screen_density);
        float imgh_cm = pxToCm(img_h,screen_density);
        str = String.format("onCreate ImageView w=%dpx (%5.2fcm) x h=%dpx (%5.2fcm)", img_w, imgw_cm, img_h, imgh_cm);
        imgSize.setText(str);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        int view_h = rootView.getHeight();
        int view_w = rootView.getWidth();
        float vieww_cm = pxToCm(view_w,screen_density);
        float viewh_cm = pxToCm(view_h,screen_density);
        String str = String.format("On start rootView w=%4.2fcm x h=%4.2fcm", vieww_cm, viewh_cm);
        viewSize.setText(str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int view_h = rootView.getHeight();
        int view_w = rootView.getWidth();
        float vieww_cm = pxToCm(view_w,screen_density);
        float viewh_cm = pxToCm(view_h,screen_density);
        String str = String.format("On resume rootView w=%4.2fcm x h=%4.2fcm", vieww_cm, viewh_cm);
        viewSize.setText(str);
    }


    public void showViewSize(View v) {
        int view_h = rootView.getHeight();
        int view_w = rootView.getWidth();
        float vieww_cm = pxToCm(view_w,screen_density);
        float viewh_cm = pxToCm(view_h,screen_density);
        String str = String.format("Button RootView w=%dpx (%5.2fcm) x h=%dpx (%5.2fcm)", view_w, vieww_cm, view_h, viewh_cm);
        viewSize.setText(str);
    }

    public void showImgSize(View v) {
        int img_h = imgView.getHeight();
        int img_w = imgView.getWidth();
        float imgw_cm = pxToCm(img_w,screen_density);
        float imgh_cm = pxToCm(img_h,screen_density);
        String str = String.format("Button ImageView w=%dpx (%5.2fcm) x h=%dpx (%5.2fcm)", img_w, imgw_cm, img_h, imgh_cm);
        imgSize.setText(str);
    }

    float pxToCm(int px, float density) {
        return (px*2.54f)/(density*160);
    }

    int cmToPx(float cm, float density) {
        return (int)((cm*density*160)/2.54f);
    }
}
