package com.purestation.widgetplusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.purestation.widgetplus.image.ProgressImageView;

public class ProgressImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_progress_image_view);

        int imageSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());

        ProgressImageView imageView = new ProgressImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(imageSize, imageSize));
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setProgress(50);

        setContentView(imageView);
    }

}