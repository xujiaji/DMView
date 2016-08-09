package io.xujiaji.dmlib.barrage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.xujiaji.dmlib.recyclerview_item_anim.OverTotalLengthAnimator;

/**
 * Created by Administrator on 2016/6/7.
 */
public class BarrageUtil {
    private Context context;
    private RecyclerView rvBarrage;
    private List<BarrageEntity> barrageList;
    private BarrageAdapter mBarrageAdapter;
    private List<Integer> indexList;
    private List<BarrageEntity> barrageCache;

    public BarrageUtil(Context context, RecyclerView rvBarrage) {
        this.context = context;
        this.rvBarrage = rvBarrage;
        init();
    }

    private void init() {
        barrageList = new ArrayList<>();
        barrageCache = new ArrayList<>();
        indexList = new ArrayList<>();
        rvBarrage.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
        OverTotalLengthAnimator anim = new OverTotalLengthAnimator();
        rvBarrage.setItemAnimator(anim);
        mBarrageAdapter = new BarrageAdapter(barrageList);
        rvBarrage.setAdapter(mBarrageAdapter);

        anim.setOnAnimListener(new OverTotalLengthAnimator.OnAnimListener() {
            @Override
            public void over() {
                int index = indexList.get(0);
                barrageList.get(index).over();
                indexList.remove(0);
                if (!barrageCache.isEmpty()) {
                    BarrageEntity b = barrageCache.get(0);
                    addBarrage(b.getPname(), b.getChatStr(), b.getPic());
                    barrageCache.remove(0);
                }
            }
        });
    }

    public void addBarrage(String name, String msg, String pic) {
        boolean isAdd = false;
        if (barrageList.size() >= 10) {
            for (int i = 0, len = barrageList.size(); i < len; i++) {
                BarrageEntity barrageEntity = barrageList.get(i);
                if (barrageEntity.isLive()) {
                    continue;
                }

                if (rvBarrage.isComputingLayout()) {
                    isAdd = false;
                    break;
                }
                barrageEntity.change(name, msg, pic);
                mBarrageAdapter.notifyItemChanged(i);
                indexList.add(i);
                isAdd = true;
                break;
            }
        } else {
            isAdd = true;
            BarrageEntity barrageEntity = new BarrageEntity(name, msg, pic);
            barrageList.add(barrageEntity);
            mBarrageAdapter.notifyItemInserted(barrageList.size() - 1);
            indexList.add(barrageList.size() - 1);
        }

        if (!isAdd) {
            barrageCache.add(new BarrageEntity(name, msg, pic));
        }
    }
}
