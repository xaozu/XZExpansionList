package xz.expansion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import xz.expansion.R;
import xz.expansion.bean.ListBean;
import xz.expansion.widget.ExpansionItem;

/**
 * time: 16/6/2
 * creator:xaozu
 * email:zhulunjun@gmail.com
 * 适配器
 */

public class ListAdapter extends BGARecyclerViewAdapter<ListBean> {

    public ListAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_list);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, ListBean s) {
        ExpansionItem item=bgaViewHolderHelper.getView(R.id.expanded_item);
        item.setNumber(s.num+"");

        View view= LayoutInflater.from(mContext).inflate(R.layout.content_layout,null);
        ImageView img= (ImageView) view.findViewById(R.id.img);
        img.setImageResource(s.content_resource);

        FamiliarRecyclerView recycler= (FamiliarRecyclerView) view.findViewById(R.id.recycler_item);
        ItemAdapter adapter=new ItemAdapter(recycler);
        adapter.setDatas(s.items);
        recycler.setAdapter(adapter);

        item.setContent(view);
        item.setTitle(s.name);
    }
}
