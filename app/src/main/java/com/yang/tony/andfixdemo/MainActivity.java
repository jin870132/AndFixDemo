package com.yang.tony.andfixdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * main activity
 */
public class MainActivity extends Activity {

    private TextView tv_1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        test();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    //打包1.apk后，修改文本内容，打包2.apk
    private void test() {
        tv_1.setText("新版显示文字2");
        tv_1.setTextSize(33);
        tv_1.setGravity(Gravity.RIGHT);
    }
}
