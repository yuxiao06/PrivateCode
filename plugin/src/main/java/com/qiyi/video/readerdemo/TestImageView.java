package com.qiyi.video.readerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class TestImageView extends ImageView {
    public TestImageView(Context context) {
        super(context);
        Log.e("TAG",context.getClass().getName());
    }

    public TestImageView(Context context, AttributeSet attrs) {
            super(context, attrs);

       /* final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.test, 0, 0);

        Log.e("TAG",context.getClass().getName());
        Log.e("TAG",a.getResources().toString());*/
     //   final Drawable d = a.getDrawable(R.styleable.test_tu);
    }

    public TestImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("TAG",context.getClass().getName());
    }

    public TestImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        Log.e("TAG",context.getClass().getName());
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
    }
}
