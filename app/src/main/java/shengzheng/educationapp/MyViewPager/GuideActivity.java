package shengzheng.educationapp.MyViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import shengzheng.educationapp.MianPackage.MainActivity;
import shengzheng.educationapp.R;

public class GuideActivity extends Activity {

    // 到达最后一张
    private static final int TO_THE_END = 0;
    // 离开最后一张
    private static final int LEAVE_FROM_END = 1;

    //图片数组
    private int[] ids = {R.mipmap.welcome01,
            R.mipmap.welcome02, R.mipmap.welcome03,
            R.mipmap.welcome04
    };

    private List<View> guides = new ArrayList<View>();
    private ViewPager pager;
    private Button start;
    private ImageView curDot;
    private LinearLayout dotContain; // 存储点的容器
    private int offset;              // 位移量
    private int curPos = 0;          // 记录当前的位置

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_view_pager);
        init();
    }

    //得到ImageView
    private ImageView buildImageView(int id) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(id);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ScaleType.FIT_XY);
        return iv;
    }

    // 功能介绍界面的初始化
    private void init() {

        this.getView();
        initDot();
        ImageView iv = null;
        guides.clear();
        for (int i = 0; i < ids.length; i++) {
            //得到数值里图片
            iv = buildImageView(ids[i]);
            //把ImageView增加到集合
            guides.add(iv);
        }

        System.out.println("guild_size=" + guides.size());

        // 当curDot的所在的树形层次将要被绘出时此方法被调用
        curDot.getViewTreeObserver().addOnPreDrawListener(
                new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        offset = curDot.getWidth();
                        return true;
                    }
                });

        final GuidePagerAdapter adapter = new GuidePagerAdapter(guides);

        pager.setAdapter(adapter);
        pager.clearAnimation();

        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                int pos = position % ids.length;

                moveCursorTo(pos);

                if (pos == ids.length - 1) {
                    handler.sendEmptyMessageDelayed(TO_THE_END, 500);

                } else if (curPos == ids.length - 1) {
                    handler.sendEmptyMessageDelayed(LEAVE_FROM_END, 100);
                }
                curPos = pos;
                super.onPageSelected(position);
            }
        });

    }


    private void getView() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //得到布局
        dotContain = (LinearLayout) this.findViewById(R.id.dot_contain);
        //得到ViewPager
        pager = (ViewPager) findViewById(R.id.contentPager);
        //得到小点2
        curDot = (ImageView) findViewById(R.id.cur_dot);
        //得到
        start = (Button) findViewById(R.id.open);
        start.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean initDot() {

        if (ids.length > 0) {
            ImageView dotView;
            for (int i = 0; i < ids.length; i++) {
                dotView = new ImageView(this);
                //得到选中的小点
                dotView.setImageResource(R.drawable.dot1_w);
                dotView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

                dotContain.addView(dotView);
            }
            return true;
        } else {
            return false;
        }
    }


    private void moveCursorTo(int position) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation tAnim =
                new TranslateAnimation(offset * curPos, offset * position, 0, 0);
        animationSet.addAnimation(tAnim);
        animationSet.setDuration(300);
        animationSet.setFillAfter(true);
        curDot.startAnimation(animationSet);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TO_THE_END)
                start.setVisibility(View.VISIBLE);
            else if (msg.what == LEAVE_FROM_END)
                start.setVisibility(View.GONE);
        }
    };

    // ViewPager
    class GuidePagerAdapter extends PagerAdapter {

        private List<View> views;

        public GuidePagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1 % views.size()));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {

            return views.size() * 20;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            Log.e("tag", "instantiateItem = " + arg1);
            ((ViewPager) arg0).addView(views.get(arg1 % views.size()), 0);
            return views.get(arg1 % views.size());
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }


    }

}