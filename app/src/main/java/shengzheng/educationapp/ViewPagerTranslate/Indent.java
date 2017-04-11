package shengzheng.educationapp.ViewPagerTranslate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import shengzheng.educationapp.R;

/**
 * Created by join on 2016/9/25.
 */
public class Indent extends FragmentActivity implements View.OnClickListener {

    private List<Fragment> listFragment;
    private ViewPager mViewPager;
    //覆盖Button
    private ImageView imageviewOvertab;
    private Button firstButton1;
    private Button firstButton2;
    private Button firstButton3;
    private Button firstButton4;

    //屏幕宽度
    private int screenWidth;
    //当前选中的项    上次选中的item
    private int currenttab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indent);
        obtainFragmentList();
        /**
         * 主要是的设置ImageView覆盖button的面积,于定位在布局的底部
         */
        //获取屏幕宽度
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        //设置ImageView的宽高,
        firstButton1.measure(400, 120);
        int width = firstButton1.getWidth();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth / listFragment.size(), 5);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        //layoutParams.addRule(RelativeLayout.ABOVE);
        //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);//

        imageviewOvertab.setLayoutParams(layoutParams);
        mViewPager.setAdapter(new MyAdapterFragment(getSupportFragmentManager()));

        Intent obtainIntent = getIntent();
        Bundle obtainBundle = obtainIntent.getExtras();
        String s = obtainBundle.getString("tag");
        if (s.equals("intent1")) {
            obtainViewpager(0);
        } else if (s.equals("intent2")) {
            obtainViewpager(1);
        } else if (s.equals("intent3")) {
            obtainViewpager(2);

        } else if (s.equals("intent4")) {
            obtainViewpager(3);

        }
    }

    //增加Fragment进List集合
    void obtainFragmentList() {
        firstButton1 = (Button) findViewById(R.id.btn_one);
        firstButton2 = (Button) findViewById(R.id.btn_two1);
        firstButton3 = (Button) findViewById(R.id.btn_three);
        firstButton4 = (Button) findViewById(R.id.btn_four);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        imageviewOvertab = (ImageView) findViewById(R.id.imgv_overtab);

        CustomFragment1 fragment1 = new CustomFragment1();
        CustomFragment2 fragment2 = new CustomFragment2();
        CustomFragment3 fragment3 = new CustomFragment3();
        CustomFragment4 fragment4 = new CustomFragment4();

        listFragment = new ArrayList<>();
        listFragment.add(fragment1);
        listFragment.add(fragment2);
        listFragment.add(fragment3);
        listFragment.add(fragment4);

        firstButton1.setOnClickListener(this);
        firstButton2.setOnClickListener(this);
        firstButton3.setOnClickListener(this);
        firstButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                obtainViewpager(0);
                break;
            case R.id.btn_two1:
                obtainViewpager(1);
                break;
            case R.id.btn_three:
                obtainViewpager(2);
                break;
            case R.id.btn_four:
                obtainViewpager(3);
                break;
        }
    }

    class MyAdapterFragment extends FragmentStatePagerAdapter {

        public MyAdapterFragment(FragmentManager fm) {
            super(fm);
        }

        //拿到集合中指定位置的Fragment
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        //拿到listFragment的大小
        @Override
        public int getCount() {
            return listFragment.size();
        }

        //每次ViewPager更新完后系统就会调用该方法,此处用于更新ImageView动态跟随button的效果
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            //拿到当前ViewPager在listFragment集合的第几项(移动之前的位置)
            int currentItem = mViewPager.getCurrentItem();
            //如果当前的等于上一次的,那么就结束该方法,(说明不用移动ImageView)
            if (currentItem == currenttab) {
                return;
            }
            //如果不一样,那么就要把imageView移动到对应的viewPager所在的位置上
            moveImageEnevt(mViewPager.getCurrentItem());
            //移动之后再次记录position
            currenttab = mViewPager.getCurrentItem();
        }
    }

    public void moveImageEnevt(int moveToPostion) {
        int startPosition = currenttab * (screenWidth / 4);
        int endPosition = moveToPostion * (screenWidth / 4);
        TranslateAnimation translater = new TranslateAnimation(startPosition, endPosition, 0, 0);
        translater.setFillAfter(true);
        translater.setDuration(100);//持续100 milli
        imageviewOvertab.startAnimation(translater);

    }

    //得到指定位置的ViewPager
    public void obtainViewpager(int position) {
        mViewPager.setCurrentItem(position, true);
    }

}


