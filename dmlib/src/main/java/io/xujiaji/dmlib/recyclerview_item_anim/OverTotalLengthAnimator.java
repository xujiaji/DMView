package io.xujiaji.dmlib.recyclerview_item_anim;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.xujiaji.dmlib.util.DMLog;

public class OverTotalLengthAnimator extends BaseItemAnimator {
    @Override
    protected void animateRemoveImpl(RecyclerView.ViewHolder holder) {
        DMLog.i("animateRemoveImpl...........................");
    }

    @Override
    protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
        DMLog.i("preAnimateRemoveImpl...........................");
    }

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        DMLog.i("preAnimateAddImpl...........................");
        ViewCompat.setTranslationX(holder.itemView, holder.itemView.getRootView().getWidth());
    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        DMLog.i("animateAddImpl...........................");
        startAnimation(holder);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        DMLog.i("animateChange...........................");
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
                        DMLog.i("onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        holder.itemView.setVisibility(View.GONE);
                        if (onAnimListener != null) {
                            onAnimListener.over();
                        }
                        DMLog.i("onAnimationEnd");
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        DMLog.i("onAnimationCancel");
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
