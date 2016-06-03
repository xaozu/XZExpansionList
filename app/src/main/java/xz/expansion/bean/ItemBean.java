package xz.expansion.bean;

import java.io.Serializable;

/**
 * 创建时间:  16/6/2
 * 创建人: xaozu
 * Email: zhulunjun@gmail.com
 * <p>
 * 描述:子list对象
 */

public class ItemBean implements Serializable{

    public int num;
    public String name;

    public ItemBean(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
