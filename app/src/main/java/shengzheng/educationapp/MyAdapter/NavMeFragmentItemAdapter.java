package shengzheng.educationapp.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shengzheng.educationapp.ContentAdapterData.NavMeFragmentContentProvider;
import shengzheng.educationapp.R;

/**
 * Created by john on 2016/9/21.
 */
public class NavMeFragmentItemAdapter extends BaseAdapter {
 private List<NavMeFragmentContentProvider> list = new ArrayList<>();
  private Context context;

    public NavMeFragmentItemAdapter(List<NavMeFragmentContentProvider> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.nav_me_fragment_adapter_item,null);

    }

    ImageView lv_temp = (ImageView) view.findViewById(R.id.lv_temp_me);
    TextView tv_content = (TextView) view.findViewById(R.id.tv_content_me);
    ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon_me);
    NavMeFragmentContentProvider contentProvider = list.get(i);
    lv_temp.setImageResource(contentProvider.getIcon());
    tv_content.setText(contentProvider.getTitle());
    iv_icon.setImageResource(contentProvider.getIconTail());

        return view;
    }
}
