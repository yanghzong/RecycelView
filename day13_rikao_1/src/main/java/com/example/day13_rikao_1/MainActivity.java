package com.example.day13_rikao_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;
    private ArrayList<String> datas;
    private MyRecycleViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();

        //设置RecyclerView的适配器
        mAdapter = new MyRecycleViewAdapter(this, datas);
        recyclerview.setAdapter(mAdapter);

        //设置布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        mAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, ""+data, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        datas = new ArrayList<>();
        for(int x=0; x<100; x++){
            datas.add("条目"+x);
        }
    }

    private void initView() {
        btn_add =  findViewById(R.id.btn_add);
        btn_delete =  findViewById(R.id.btn_delete);
        btn_list =  findViewById(R.id.btn_list);
        btn_grid =  findViewById(R.id.btn_grid);
        btn_flow =  findViewById(R.id.btn_flow);
        recyclerview =  findViewById(R.id.recyclerview);

        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add://添加条目
                mAdapter.addData(0,"NEW_Content");
                recyclerview.scrollToPosition(0);
                break;
            case R.id.btn_delete://删除条目
                mAdapter.removeData(0);
                break;
            case R.id.btn_list://让RecycleView显示出ListView效果
                recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));
                break;
            case R.id.btn_grid://让RecycleView显示出GridView效果
                recyclerview.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_flow://让RecycleView显示出瀑布流效果
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                break;
        }
    }
}
