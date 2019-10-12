package com.qiyi.video.readerdemo.viewDragHelper.view;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.qiyi.video.readerdemo.R;

public class DirectionDragLayout extends ConstraintLayout {
    private ViewDragHelper mViewDragHelper;
    private View mReadView;
    private View mUnlockView;
    private View mRedbagView;
    private View mHomeView;

    Point mHomeCenterPoint = new Point();
    Point mReadCenterPoint = new Point();
    Point mUnlockCenterPoint = new Point();
    Point mRedbagCenterPoint = new Point();
    Point mHomeOriginalPoint = new Point();
    private int mTopBound;
    private int mBottomBound;
    private int mLeftBound;
    private int mRightBound;

    /**
     * 当前正在水平拖拽的标记
     */
    private boolean mIsHorizontalDrag = false;
    /**
     * 当前正在竖直拖拽的标记
     */
    private boolean mIsVerticalDrag = false;
    /**
     * 是否到达边界的标记
     */
    private boolean mIsReachBound;

    public DirectionDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
                return view == mHomeView;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (mIsHorizontalDrag) {
                    return mHomeView.getTop();
                }
                if (Math.abs(dy) > 0) {
                    mIsVerticalDrag = true;
                }
                return Math.min(Math.max(mTopBound, top), mBottomBound);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (mIsVerticalDrag) {
                    return mHomeView.getLeft();
                }
                if (Math.abs(dx) > 0) {
                    mIsHorizontalDrag = true;
                }
                return Math.min(Math.max(mLeftBound, left), mRightBound);
            }

            @Override
            //松手返回起始点
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mHomeView) {
                    // 让释放的 view 停在给定的位置
                    mViewDragHelper.settleCapturedViewAt(mHomeOriginalPoint.x, mHomeOriginalPoint.y);
                    invalidate();
                }
            }

            // 当拖拽的View的位置发生变化的时候回调(特指capturedview)
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                // 12, 到达边界时触发操作
                if (mIsReachBound) {
                    return;
                }
                if (left <= mLeftBound) {
                    mIsReachBound = true;
                    Toast.makeText(getContext(), "到达左边界", Toast.LENGTH_SHORT).show();
                }
                if (left >= mRightBound) {
                    mIsReachBound = true;
                    Toast.makeText(getContext(), "到达右边界", Toast.LENGTH_SHORT).show();
                }
                if (top <= mTopBound) {
                    mIsReachBound = true;
                    Toast.makeText(getContext(), "到达上边界", Toast.LENGTH_SHORT).show();
                }
            }

            /*@Override
            public int  getViewHorizontalDragRange(View child) {
                return mViewDragHelper.getTouchSlop();
            }*/
        });
    }

    //松手返回起始点
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        mReadView = findViewById(R.id.iv_read);
        mUnlockView = findViewById(R.id.iv_unlock);
        mRedbagView = findViewById(R.id.iv_redbag);
        mHomeView = findViewById(R.id.iv_home);
        /*mHomeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开getViewHorizontalDragRange 的代码
            }
        });*/
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        resetFlags();
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    private void resetFlags() {
        mIsHorizontalDrag = false;
        mIsVerticalDrag = false;
        mIsReachBound = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mHomeOriginalPoint.x = mHomeView.getLeft();
        mHomeOriginalPoint.y = mHomeView.getTop();

        mHomeCenterPoint.x = mHomeView.getLeft() + mHomeView.getMeasuredWidth() / 2;
        mHomeCenterPoint.y = mHomeView.getTop() + mHomeView.getMeasuredHeight() / 2;

        mReadCenterPoint.x = mReadView.getLeft() + mReadView.getMeasuredWidth() / 2;
        mReadCenterPoint.y = mReadView.getTop() + mReadView.getMeasuredHeight() / 2;

        mUnlockCenterPoint.x = mUnlockView.getLeft() + mUnlockView.getMeasuredWidth() / 2;
        mUnlockCenterPoint.y = mUnlockView.getTop() + mUnlockView.getMeasuredHeight() / 2;

        mRedbagCenterPoint.x = mRedbagView.getLeft() + mRedbagView.getMeasuredWidth() / 2;
        mRedbagCenterPoint.y = mRedbagView.getTop() + mRedbagView.getMeasuredHeight() / 2;

        mTopBound = mRedbagCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mBottomBound = mHomeCenterPoint.y - mHomeView.getMeasuredHeight() / 2;
        mLeftBound = mReadCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
        mRightBound = mUnlockCenterPoint.x - mHomeView.getMeasuredWidth() / 2;
    }
}
