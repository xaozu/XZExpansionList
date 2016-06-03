package xz.expansion.widget;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import xz.expansion.R;

/**
 * Created by xaozu on 16/6/1.
 *
 */

public class ExpansionItem extends LinearLayout {
    private long duration = 350;
    private Context mContext;
    private TextView text_num;
    private TextView text_name;
    private LinearLayout lin_content;
    private LinearLayout lin_title;
    private ImageView img;
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;
    public ExpansionItem(Context context) {
        super(context);
        initView(context);
    }

    public ExpansionItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ExpansionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpansionItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    //初始化
    private void initView(Context context){
        mContext=context;
        LayoutInflater.from(mContext).inflate(R.layout.item_expansion, this);
        text_name= (TextView) findViewById(R.id.text_name);
        text_num= (TextView) findViewById(R.id.text_num);
        lin_content= (LinearLayout) findViewById(R.id.lin_content);
        lin_title= (LinearLayout) findViewById(R.id.lin_title);
        img= (ImageView) findViewById(R.id.img);

        lin_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //先旋转箭头,然后展开隐藏部分
                rotateArrow();
            }
        });
        collapse(lin_content);//初始状态折叠
    }

    //计算大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //根据父容器高宽测量需要展开的控件的高宽
        parentHeightMeasureSpec=heightMeasureSpec;
        parentWidthMeasureSpec=widthMeasureSpec;
    }
    //计算位置

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setNumber(String number){
        if(!TextUtils.isEmpty(number)){
            text_num.setText(number);
        }
    }

    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            text_name.setText(title);
        }
    }

    public void setContent(View view){
//        View view=LayoutInflater.from(mContext).inflate(resID,null);
        LinearLayout.LayoutParams layoutParams=
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        lin_content.addView(view);
    }

    //旋转箭头
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void rotateArrow() {
        int degree = 0;
        if (img.getTag() == null || img.getTag().equals(true)) {
            img.setTag(false);
            degree = -180;
            expand(lin_content);
        } else {
            degree = 0;
            img.setTag(true);
            collapse(lin_content);
        }
        img.animate().setDuration(duration).rotation(degree);
    }
    //展开
    private void expand(final View view) {
//        int widthMeasureSpec=MeasureSpec.makeMeasureSpec(getScreenWidth(mContext), MeasureSpec.EXACTLY);
//        int heightMeasureSpec=MeasureSpec.makeMeasureSpec((1<<30)-1, MeasureSpec.AT_MOST);
//        view.measure(widthMeasureSpec, heightMeasureSpec);
        view.measure(parentWidthMeasureSpec,parentHeightMeasureSpec);
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.getLayoutParams().height =measuredHeight;
                }else{
                    view.getLayoutParams().height =(int) (measuredHeight * interpolatedTime);
                }
                view.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
    // 折叠
    private void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
