package com.purestation.widgetplus.text;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * ClickableDrawableTextView를 상속받고, EditText의 기능을 추가하는 방식도 애매하다.
 * <p>
 * Created by puresprout@sk.com on 2016. 12. 14..
 */
public class ClickableDrawableEditText extends EditText implements DrawableClickable {
    private final DrawableClickDetector mDetector = new DrawableClickDetector(this);

    private ClickableDrawableTextView.OnDrawableClickListener mListener;

    public ClickableDrawableEditText(Context context) {
        super(context);
    }

    public ClickableDrawableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableDrawableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClickableDrawableEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnDrawableClickListener(ClickableDrawableTextView.OnDrawableClickListener listener) {
        mListener = listener;
    }

    public ClickableDrawableTextView.OnDrawableClickListener getOnDrawableClickListener() {
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
            mListener.onClick(this, ClickableDrawableTextView.DrawablePosition.values()[i]);
        }
    }
}
