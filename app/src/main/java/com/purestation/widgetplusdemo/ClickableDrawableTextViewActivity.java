package com.purestation.widgetplusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.purestation.widgetplus.text.ClickableDrawableEditText;
import com.purestation.widgetplus.text.ClickableDrawableTextView;

public class ClickableDrawableTextViewActivity extends AppCompatActivity {

    private ClickableDrawableTextView.OnDrawableClickListener listener = new ClickableDrawableTextView.OnDrawableClickListener() {
        @Override
        public void onClick(View v, ClickableDrawableTextView.DrawablePosition drawablePosition) {
            Toast.makeText(ClickableDrawableTextViewActivity.this, drawablePosition + " drawable clicked.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickable_drawable_text_view);

        ClickableDrawableTextView textView = (ClickableDrawableTextView) findViewById(R.id.textView);
        textView.setOnDrawableClickListener(listener);

        ClickableDrawableTextView textView2 = (ClickableDrawableTextView) findViewById(R.id.textView2);
        textView2.setOnDrawableClickListener(listener);

        ClickableDrawableEditText editText = (ClickableDrawableEditText) findViewById(R.id.editText);
        editText.setOnDrawableClickListener(listener);
    }
}
