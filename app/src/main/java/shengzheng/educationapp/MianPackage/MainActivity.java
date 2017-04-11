package shengzheng.educationapp.MianPackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import shengzheng.educationapp.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button enter_1;
    private Button forget_password;
    private Button register_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_interface);
        initView();


    }


    private void initView() {
        enter_1 = (Button) findViewById(R.id.enter_1);
        forget_password = (Button) findViewById(R.id.forget_password);
        register_account = (Button) findViewById(R.id.register_account);
        enter_1.setOnClickListener(this);
        forget_password.setOnClickListener(this);
        register_account.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter_1:
                Intent intent1 = new Intent(MainActivity.this, MainInterface.class);
                startActivity(intent1);
                break;
            case R.id.forget_password:
                Intent intent2 = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent2);
                break;
            case R.id.register_account:
                Intent intent3 = new Intent(MainActivity.this, RegisterAccount.class);
                startActivity(intent3);
                break;
        }
    }
}
