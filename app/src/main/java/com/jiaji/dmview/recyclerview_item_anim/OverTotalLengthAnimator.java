package com.jiaji.dmview.recyclerview_item_anim;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class OverTotalLengthAnimator extends BaseItemAnimator {
    @Override
    protected void animateRemoveImpl(RecyclerView.ViewHolder holder) {
        Log.e("TAG", "animateRemoveImpl...........................");
    }

    @Override
    protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
        Log.e("TAG", "preAnimateRemoveImpl...........................");
    }

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        Log.e("TAG", "preAnimateAddImpl...........................");
        ViewCompat.setTranslationX(holder.itemView, holder.itemView.getRootView().getWidth());
    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        Log.e("TAG", "animateAddImpl...........................");
        startAnimation(holder);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        Log.e("TAG", "animateChange...........................");
        newHolder.itemView.setVisibility(View.VISIBLE);
        ViewCompat.setTranslationX(newHolder.itemView, newHolder.itemView.getRootView().getWidth());
        startAnimation(newHolder);
        return true;
    }


    private void startAnimation(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .translationX(-holder.itemView.getRootView().getWidth())
                .setDuration(8000)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        Log.e("TAG", "onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        holder.itemView.setVisibility(View.GONE);
                        if (onAnimListener != null) {
                            onAnimListener.over();
                        }
                        Log.e("TAG", "onAnimationEnd");
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        Log.e("TAG", "onAnimationCancel");
                    }
                })
                .setStartDelay(getAddDelay(holder))
                .start();
    }
    private OnAnimListener onAnimListener;
    public void setOnAnimListener(OnAnimListener l) {
        this.onAnimListener = l;
    }
    public interface OnAnimListener {
        void over();
    }
}
