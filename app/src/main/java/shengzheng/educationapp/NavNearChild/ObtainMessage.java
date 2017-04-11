package shengzheng.educationapp.NavNearChild;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import shengzheng.educationapp.R;

/**
 * Created by john on 2016/9/22.
 */
public class ObtainMessage extends Activity implements View.OnClickListener {
    private ImageView message_obtain,phone_obtain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obtain_message);
        initView();
    }
    private void initView(){
        message_obtain = (ImageView) findViewById(R.id.message_obtain);
        phone_obtain = (ImageView) findViewById(R.id.phone_obtain);
        message_obtain.setOnClickListener(this);
        phone_obtain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_obtain:
                message_obtain.setImageResource(R.drawable.message_phone);
                break;
            case R.id.phone_obtain:
             phone_obtain.setImageResource(R.drawable.message_phone);
                break;
        }
    }
}
