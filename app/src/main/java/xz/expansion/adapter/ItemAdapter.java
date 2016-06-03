package xz.expansion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import xz.expansion.R;
import xz.expansion.bean.ItemBean;

/**
 * time: 16/6/2
 * creator:xaozu
 * email:zhulunjun@gmail.com
 */

public class ItemAdapter extends BGARecyclerViewAdapter<ItemBean>{

    public ItemAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_item);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, ItemBean itemBean) {
        TextView text_num=bgaViewHolderHelper.getView(R.id.text_num);
        TextView text_name=bgaViewHolderHelper.getView(R.id.text_name);
        text_num.setText(itemBean.num+"");
        text_name.setText(itemBean.name);
        if(i==0){
            bgaViewHolderHelper.getView(R.id.text_hind).setVisibility(View.VISIBLE);
        }else{
            bgaViewHolderHelper.getView(R.id.text_hind).setVisibility(View.GONE);
        }

    }
}
