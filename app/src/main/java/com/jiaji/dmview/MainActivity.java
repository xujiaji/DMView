package com.jiaji.dmview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiaji.dmview.recyclerview_item_anim.OverTotalLengthAnimator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBarrage;
    private List<BarrageEntity> barrageList;
    private BarrageAdapter mBarrageAdapter;
    private OverTotalLengthAnimator anim;
    private List<Integer> indexList;
    private List<BarrageEntity> barrageCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barrageList = new ArrayList<>();
        barrageCache = new ArrayList<>();
        indexList = new ArrayList<>();
        rvBarrage = (RecyclerView) findViewById(R.id.rvBarrage);
        rvBarrage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        anim = new OverTotalLengthAnimator();
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

    public void onAddClick(View view) {
        addBarrage(new Date().toString(), "聊天消息。。。。", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=150237755,4294706681&fm=116&gp=0.jpg");
    }

    private void addBarrage(String name, String msg, String pic) {
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
