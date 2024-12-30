package com.example.wallpaper;


import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button wallCg;
    Timer mtTimer;
    Drawable drawable;
    WallpaperManager wpm;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mtTimer = new Timer();
        wpm = WallpaperManager.getInstance(this);
        wallCg = (Button) findViewById(R.id.btn_1);
        wallCg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "wallpaper set successfully", Toast.LENGTH_SHORT).show();

                setWallpaper2();
            }
        });
    }


    private void setWallpaper2() {
        mtTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Drawable drawable;
                switch (id) {
                    case 1:
                        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img_1, null);
                        id = 2;
                        break;
                    case 2:
                        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img_2, null);
                        id = 3;
                        break;
                    case 3:
                        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img_3, null);
                        id = 1;
                        break;
                    default:
                        // Handle unexpected id values, maybe log an error or reset id
                        drawable = null;
                        break;
                }

                if (drawable != null) {
                    try {
                        Bitmap wallpaper = ((BitmapDrawable) drawable).getBitmap();
                        wpm.setBitmap(wallpaper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 3000);
    }
}
