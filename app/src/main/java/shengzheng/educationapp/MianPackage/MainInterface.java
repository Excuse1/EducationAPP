package shengzheng.educationapp.MianPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import shengzheng.educationapp.MyFragment.FindFragment;
import shengzheng.educationapp.MyFragment.NavMeFragment1;
import shengzheng.educationapp.MyFragment.NavNearbyFragment;
import shengzheng.educationapp.MyFragment.TimetableFragment;
import shengzheng.educationapp.R;

/**
 * Created by john on 2016/9/17.
 */
public class MainInterface extends FragmentActivity {
    //定义一个FragmentTabHost对象
    private FragmentTabHost mTabHost;
    //布局填充器
    private LayoutInflater inflater;
    private Class[] fragmentArray;
    private int[] iconArray;
    private String[] textArray;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);
        initArray();
        initMTabHost();
    }

    /**
     * 初始化数组
     */
    private void initArray() {
        fragmentArray = new Class[]{NavNearbyFragment.class, TimetableFragment.class, FindFragment.class, NavMeFragment1.class};
        iconArray = new int[]{R.drawable.nav_nerby, R.drawable.timetable, R.drawable.find, R.drawable.nav_me};
        textArray = new String[]{"附近", "课程", "发现", "我"};
    }

    /**
     * 初始化mTabHost
     */
    private void initMTabHost() {
        inflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fragment_container);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容  newTabSpec()增加标签,setIndicator("Simple")是标签显示的label
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.tab_item_view_host_tab, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(iconArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(textArray[index]);
        return view;
    }
}
