package shengzheng.educationapp.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import shengzheng.educationapp.CustomCalendar.CalendarView;
import shengzheng.educationapp.CustomCalendar.ClickDataListener;
import shengzheng.educationapp.NavNearChild.ObtainMessage;
import shengzheng.educationapp.R;

/**
 * Created by john on 2016/9/17.
 */
public class TimetableFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_icon_timetable;
    private View view;
    CalendarView calendarview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timetable_fragment, null);
        initView();

        //设置点击日期的事件监听
        calendarview.setClickDataListener(new ClickDataListener() {
            @Override
            public void clickData(String year, String month, String day) {
                Toast.makeText(getActivity(),
                        year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }

    private void initView() {
        iv_icon_timetable = (ImageView) view.findViewById(R.id.iv_icon_timetable);
        iv_icon_timetable.setOnClickListener(this);
        //自定义日历
        calendarview = (CalendarView) view.findViewById(R.id.calendarview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon_timetable:
                Intent intent = new Intent(getActivity(), ObtainMessage.class);
                startActivity(intent);
                break;
        }
    }
}
