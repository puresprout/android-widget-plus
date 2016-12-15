package com.purestation.widgetplus.text;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.TextView;

public class DrawableClickDetector {
    private static final int NONE = -1;

    private TextView mTextView;

    private int mTouchingDrawable = NONE;

    public DrawableClickDetector(TextView textView) {
        this.mTextView = textView;

        if (!(mTextView instanceof DrawableClickable)) {
            throw new RuntimeException("Your custom textView should implement DrawableClickable interface.");
        }
    }

    public boolean detect(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchingDrawable = findTouchingDrawable(event);
            if (mTouchingDrawable != NONE) {
                return true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mTouchingDrawable != NONE) {
                if (mTouchingDrawable == findTouchingDrawable(event)) {
                    ((DrawableClickable) mTextView).performDrawableClick(mTouchingDrawable);
                }
                mTouchingDrawable = NONE;
                return true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            if (mTouchingDrawable != NONE) {
                mTouchingDrawable = NONE;
                return false;
            }
        }

        return false;
    }

    private int findTouchingDrawable(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Drawable[] drawables = mTextView.getCompoundDrawables();

        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable = drawables[i];

            if (drawable != null) {
                Rect rect = drawable.getBounds();

                if (i == 0) {
                    if (x >= mTextView.getPaddingLeft() && x <= rect.width() + mTextView.getPaddingLeft() &&
                            y >= (mTextView.getHeight() / 2) - (rect.height() / 2) && y <= (mTextView.getHeight() / 2) + (rect.height() / 2)) {
                        return i;
                    }
                } else if (i == 1) {
                    if (y >= mTextView.getPaddingTop() && y <= rect.height() + mTextView.getPaddingTop() &&
                            x >= (mTextView.getWidth() / 2) - (rect.width() / 2) && x <= (mTextView.getWidth() / 2) + (rect.width() / 2)) {
                        return i;
                    }
                } else if (i == 2) {
                    if (x >= mTextView.getWidth() - rect.width() - mTextView.getPaddingRight() && x <= mTextView.getWidth() - mTextView.getPaddingRight() &&
                            y >= (mTextView.getHeight() / 2) - (rect.height() / 2) && y <= (mTextView.getHeight() / 2) + (rect.height() / 2)) {
                        return i;
                    }
                } else if (i == 3) {
                    if (y >= mTextView.getHeight() - rect.height() - mTextView.getPaddingBottom() && y <= mTextView.getHeight() - mTextView.getPaddingBottom() &&
                            x >= (mTextView.getWidth() / 2) - (rect.width() / 2) && x <= (mTextView.getWidth() / 2) + (rect.width() / 2)) {
                        return i;
                    }
                }
            }
        }

        return NONE;
    }
}