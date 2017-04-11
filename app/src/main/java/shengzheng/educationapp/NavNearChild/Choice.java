package shengzheng.educationapp.NavNearChild;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import shengzheng.educationapp.R;

/**
 * Created by join on 2016/9/26.
 */
public class Choice extends Activity implements View.OnClickListener {
    private ImageView iv_1, iv_2, iv_3, iv_4, iv_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        initView();
    }

    private void initView() {
        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
        iv_4 = (ImageView) findViewById(R.id.iv_4);
        iv_5 = (ImageView) findViewById(R.id.iv_5);

        iv_1.setOnClickListener(this);
        iv_2.setOnClickListener(this);
        iv_3.setOnClickListener(this);
        iv_4.setOnClickListener(this);
        iv_5.setOnClickListener(this);
    }
    /*
     *
     */
private void initBackground(){
    iv_1.setBackgroundResource(R.mipmap.juli_uncheck);
    iv_2.setBackgroundResource(R.mipmap.juli_uncheck);
    iv_3.setBackgroundResource(R.mipmap.juli_uncheck);
    iv_4.setBackgroundResource(R.mipmap.juli_uncheck);
    iv_5.setBackgroundResource(R.mipmap.juli_uncheck);
}
    @Override
    public void onClick(View v) {
        initBackground();
        switch (v.getId()) {
            case R.id.iv_1:
                iv_1.setBackgroundResource(R.mipmap.juli_check);
                break;
            case R.id.iv_2:
                iv_2.setBackgroundResource(R.mipmap.juli_check);
                break;
            case R.id.iv_3:
                iv_3.setBackgroundResource(R.mipmap.juli_check);
                break;
            case R.id.iv_4:
                iv_4.setBackgroundResource(R.mipmap.juli_check);
                break;
            case R.id.iv_5:
                iv_5.setBackgroundResource(R.mipmap.juli_check);
                break;
        }
    }
}
