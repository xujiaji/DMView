# DMView [中文介绍](http://www.jianshu.com/p/2232a63442d6)
Library for display Bullet screen.You only need to add a few base info.
# Version
1.0.0
# Screen
![弹幕演示.gif](弹幕演示.gif)

#Installation
To use this library in your android project, just simply add the following dependency into your build.gradle
```
dependencies {
    compile 'com.github.xujiaji:dmlib:1.0.0'
}
```
#Usage
```
    <android.support.v7.widget.RecyclerView
        android:id="@+id/rvBarrage"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="90dp"
        android:layout_marginBottom="90dp"
        android:overScrollMode="never" />
```
```
public class MainActivity extends AppCompatActivity {
    private BarrageUtil mBarrageUtil;
    private RecyclerView rvBarrage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvBarrage = (RecyclerView) findViewById(R.id.rvBarrage);
        mBarrageUtil = new BarrageUtil(this, rvBarrage);
    }

    public void onAddClick(View view) {
        mBarrageUtil.addBarrage(new Date().toString(), "聊天消息。。。。", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=150237755,4294706681&fm=116&gp=0.jpg");
    }
}
```
#Change Logs
###v1.0.0
Initial version,Only realized the Bullet screen function
#License

    Copyright 2016 xujiaji

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
