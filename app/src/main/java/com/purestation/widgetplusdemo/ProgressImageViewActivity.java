package com.purestation.widgetplusdemo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.purestation.widgetplus.image.ProgressImageView;

public class ProgressImageViewActivity extends AppCompatActivity {
    private ProgressImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_image_view);

        imageView = (ProgressImageView) findViewById(R.id.progressImageView);
        imageView.setColor(Color.argb(128, 255, 255, 255));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressTask().execute(100);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setDisabledProgress(!imageView.isDisabledProgress());
            }
        });

//        int imageSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
//
//        ProgressImageView imageView = new ProgressImageView(this);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(imageSize, imageSize));
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        imageView.setProgress(50);
//
//        setContentView(imageView);
    }

    class ProgressTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i <= integers[0]; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            imageView.setProgress(values[0]);
        }
    }
}
