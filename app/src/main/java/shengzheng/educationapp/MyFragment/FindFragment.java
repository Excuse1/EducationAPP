package shengzheng.educationapp.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shengzheng.educationapp.R;

/**
 * Created by john on 2016/9/17.
 */
public class FindFragment extends Fragment {


    @Bind(R.id.tv_content_find)
    TextView tvContentFind;
    @Bind(R.id.iv_icon_find)
    ImageView ivIconFind;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_fragment, null);

        ButterKnife.bind(getActivity(), view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(getActivity());
    }

    @OnClick({R.id.tv_content_find, R.id.iv_icon_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content_find:
                break;
            case R.id.iv_icon_find:
                break;
        }
    }
}
