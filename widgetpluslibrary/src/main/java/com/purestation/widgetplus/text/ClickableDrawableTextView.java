package com.purestation.widgetplus.text;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by puresprout@sk.com on 2016. 12. 14..
 */
public class ClickableDrawableTextView extends TextView implements DrawableClickable {
    public enum DrawablePosition {
        LEFT, TOP, RIGHT, BOTTOM
    }

    public interface OnDrawableClickListener {
        void onClick(View v, DrawablePosition drawablePosition);
    }

    private final DrawableClickDetector mDetector = new DrawableClickDetector(this);

    private OnDrawableClickListener mListener;

    public ClickableDrawableTextView(Context context) {
        super(context);
    }

    public ClickableDrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClickableDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnDrawableClickListener(OnDrawableClickListener listener) {
        mListener = listener;
    }

    public OnDrawableClickListener getOnDrawableClickListener() {
        return mListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDetector.detect(event)) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void performDrawableClick(int i) {
        if (mListener != null) {
            mListener.onClick(this, DrawablePosition.values()[i]);
        }
    }
}
