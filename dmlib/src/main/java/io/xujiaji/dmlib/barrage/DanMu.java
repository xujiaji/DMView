package io.xujiaji.dmlib.barrage;
/*
 * Copyright 2016 xujiaji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.xujiaji.dmlib.barrage.builder.All;
import io.xujiaji.dmlib.barrage.builder.Builder;
import io.xujiaji.dmlib.recyclerview_item_anim.OverTotalLengthAnimator;
import io.xujiaji.dmlib.util.DMUtil;

/**
 * Bullet screen configuration
 */
public class DanMu {
    private Context context;
    private RecyclerView rv;
    private List<BarrageEntity> barrageList;
    private BarrageAdapter mBarrageAdapter;
    private List<Integer> indexList;
    private List<BarrageEntity> barrageCache;
    private static DanMu mDanMu;
    private int rowNum = 6;

    private DanMu() {
    }

    private static DanMu Instance() {
        if (mDanMu == null) {
            synchronized (DanMu.class) {
                mDanMu = new DanMu();
            }
        }
        return mDanMu;
    }

    public static void init(RecyclerView rv) {
        if (DanMu.Instance().rv == null)
            DanMu.Instance().rv = DMUtil.checkNotNull(rv, "DanMu.init(): RecyclerView is null");
        DanMu.Instance().context = DanMu.Instance().rv.getContext();
        DanMu.Instance().initView();
    }

    public static void init(RecyclerView rv, Config config) {
        init(rv);
        if (config == null) return;
        if (config.getRowNum() != 0) {
            DanMu.Instance().setRowNum(config.getRowNum());
        }
        if (config.getRootId() != 0){
            DanMu.Instance().mBarrageAdapter.setConfigHolder(config);
        }
    }

    public static All call() {
        Builder builder = new Builder();
        return builder.call();
    }

    protected static void build() {
        DMUtil.checkNotNull(DanMu.Instance().rv, "Not running DanMu.init()");
        DanMu danMu = DanMu.Instance();
        danMu.addBarrage(Builder.get_name(), Builder.get_msg(), Builder.get_picUrl());
        Builder.clear();
    }

    private void initView() {
        barrageList = new ArrayList<>();
        barrageCache = new ArrayList<>();
        indexList = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
        OverTotalLengthAnimator anim = new OverTotalLengthAnimator();
        rv.setItemAnimator(anim);
        mBarrageAdapter = new BarrageAdapter(barrageList);
        rv.setAdapter(mBarrageAdapter);

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
        if (barrageList.size() >= rowNum) {
            for (int i = 0, len = barrageList.size(); i < len; i++) {
                BarrageEntity barrageEntity = barrageList.get(i);
                if (barrageEntity.isLive()) {
                    continue;
                }

                if (rv.isComputingLayout()) {
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

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}
