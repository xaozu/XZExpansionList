package xz.expansion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xz.expansion.R;

/**
 * time: 16/6/2
 * creator:xaozu
 * email:zhulunjun@gmail.com
 */

public class ListBean implements Serializable {
    public int num=1;
    public String name="默认";
    public int content_resource= R.mipmap.ic_launcher;
    public List<ItemBean> items=new ArrayList<>();

    public ListBean(int num, String name, int content_resource,List<ItemBean> items) {
        this.num = num;
        this.name = name;
        this.content_resource = content_resource;
        this.items=items;
    }
}
