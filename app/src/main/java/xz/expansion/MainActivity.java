package xz.expansion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import xz.expansion.adapter.ListAdapter;
import xz.expansion.bean.ItemBean;
import xz.expansion.bean.ListBean;

public class MainActivity extends AppCompatActivity {
    private FamiliarRecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler= (FamiliarRecyclerView) findViewById(R.id.recycler);
        ListAdapter adapter=new ListAdapter(mRecycler);
        List<ListBean> data=new ArrayList<>();
        List<ItemBean> data_items=new ArrayList<>();
        data_items.add(new ItemBean(1,"长颈鹿的第一个孩子"));
        data_items.add(new ItemBean(2,"长颈鹿的第二个孩子"));
        data.add(new ListBean(1,"长颈鹿",R.mipmap.img_1,data_items));
        data.add(new ListBean(2,"蓝美人",R.mipmap.img_2,data_items));
        data.add(new ListBean(3,"黄美人",R.mipmap.img_3,data_items));
        adapter.setDatas(data);
        mRecycler.setAdapter(adapter);
    }
}
