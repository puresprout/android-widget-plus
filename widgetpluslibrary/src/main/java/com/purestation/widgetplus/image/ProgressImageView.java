package com.purestation.widgetplus.image;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 진행률을 보여주는 이미지뷰
 */
public class ProgressImageView extends ImageView {

    public enum ProgressType {
        RECTANGLE,
        ARC
    }

    private int progress;
    private Paint paint;
    private Paint textPaint;
    private Rect textBounds;
    private RectF progressRect;
    private int color = Color.argb(128, 0, 0, 0);

    private ProgressType progressType = ProgressType.ARC;

    /**
     * 진행률이 0%일 때 진행정보를 없앨지 여부. 기본값은 없앤다.
     */
    private boolean dismissProgressWhenThisIsMin = true;

    /**
     * 진행률이 100%가 되었을 때 진행정보를 없앨지 여부. 기본값은 없앤다.
     */
    private boolean dismissProgressWhenThisIsMax = true;

    private boolean dismissProgress = true;

    public ProgressImageView(Context context) {
        super(context);

        init();
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);

        float textSize = getResources().getDisplayMetrics().scaledDensity * 18;

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
        //textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        textBounds = new Rect();
        progressRect = new RectF();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setProgressType(ProgressType progressType) {
        this.progressType = progressType;
    }

    public ProgressType getProgressType() {
        return progressType;
    }

    /**
     * 진행률을 설정한다.
     *
     * @param progress 0~100까지의 값만 주어야 한다.
     */
    public void setProgress(int progress) {
        this.progress = progress;

        if (progress == 0 && dismissProgressWhenThisIsMin) {
            dismissProgress = true;
        } else {
            dismissProgress = false;
        }

        invalidate();

        if (this.progress >= 100 && dismissProgressWhenThisIsMax) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissProgress = true;

                    invalidate();
                }
            }, 1000);
        }
    }

    public int getProgress() {
        return progress;
    }

    public void setDismissProgressWhenThisIsMax(boolean dismissProgressWhenThisIsMax) {
        this.dismissProgressWhenThisIsMax = dismissProgressWhenThisIsMax;

        if (dismissProgressWhenThisIsMax) {
            dismissProgress = true;
        } else {
            dismissProgress = false;
        }

        if (progress >= 100) {
            invalidate();
        }
    }

    public boolean doesDismissProgressWhenThisIsMax() {
        return dismissProgressWhenThisIsMax;
    }

    public boolean isDismissProgressWhenThisIsMin() {
        return dismissProgressWhenThisIsMin;
    }

    public void setDismissProgressWhenThisIsMin(boolean dismissProgressWhenThisIsMin) {
        this.dismissProgressWhenThisIsMin = dismissProgressWhenThisIsMin;

        if (dismissProgressWhenThisIsMin) {
            dismissProgress = true;
        } else {
            dismissProgress = false;
        }

        if (progress == 0) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!dismissProgress) {
            drawProgressFigure(canvas);
            drawProgressText(canvas);
        }
    }

    private void drawProgressFigure(Canvas canvas) {
        // Type1 아래에서 위로 진행
        if (progressType == ProgressType.RECTANGLE) {
            int height = getHeight() - getHeight() * progress / 100;

            canvas.drawRect(0, 0, getWidth(), height, paint);
        } else if (progressType == ProgressType.ARC) {  // Type2 원호로
            // TODO rect 계산 부분을 어느 메소드에 넣으면 좋을까?
            int halfWidth = getWidth() / 2;
            int halfHeight = getHeight() / 2;
            progressRect.set(-halfWidth, -halfHeight, getWidth() + halfWidth, getHeight() + halfHeight);

            int startDegree = 360 * progress / 100;
            int endDegree = 360 - startDegree;

            canvas.drawArc(progressRect, startDegree, endDegree, true, paint);
        }
    }

    private void drawProgressText(Canvas canvas) {
        String text = progress + "%";

        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        int textWidth = textBounds.width();
        int textHeight = textBounds.height();

        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, textPaint);
    }

}
