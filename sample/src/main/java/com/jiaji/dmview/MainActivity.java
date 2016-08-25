package com.jiaji.dmview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.xujiaji.dmlib.barrage.DanMu;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBarrage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvBarrage = (RecyclerView) findViewById(R.id.rvBarrage);
        //sample.1: default init
        DanMu.init(rvBarrage);

        //sample.2: config yourself layout
//        Config config = new Config(
//                R.layout.item,
//                R.id.tvName,
//                R.id.tvMsg,
//                R.id.imgHead);
//        config.setRowNum(5);  // setting bullet screen's rows
//        DanMu.init(rvBarrage, config);

        //sample.3: setting bullet screen's rows
//        Config config = new Config();
//        config.setRowNum(3);
//        DanMu.init(rvBarrage, config);

        //sample.4 setting bullet screen's animator duration
//        Config config = new Config();
//        config.setDuration(10000);
//        DanMu.init(rvBarrage, config);
    }

    public void onAddClick(View view) {
        DanMu.call()
                .picUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=150237755,4294706681&fm=116&gp=0.jpg")
                .name("Name名字")
                .msg("This is a massage!")
                .show();
    }

    @Override
    protected void onDestroy() {
        DanMu.destroy();
        super.onDestroy();
    }
}
