package com.example.manutd.demochatheads;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ManUtd on 11/21/2016.
 */

public class ChatHeadsService extends Service {
    WindowManager windowManager;
    WindowManager.LayoutParams params;
    ImageView imageView;
    int initX, initY, initX1, initY1;

    @Override
    public void onCreate() {
        super.onCreate();
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.bobo2);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        windowManager.addView(imageView, params);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initX = (int) event.getRawX();
                        initY = (int) event.getRawY();
                        initX1 = params.x;
                        initY1 = params.y;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (System.currentTimeMillis() - 0 < 300) {
                            Log.d("Test","OK");
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initX1 + (int) ((event.getRawX() - initX));
                        params.y = initY1 + (int) ((event.getRawY() - initY));
                        windowManager.updateViewLayout(imageView, params);

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageView != null) {
            windowManager.removeView(imageView);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
