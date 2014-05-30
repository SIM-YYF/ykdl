package com.ykdl.tangyoubang.ui;



import android.app.Activity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ykdl.tangyoubang.AppService;
import com.ykdl.tangyoubang.R;
import com.ykdl.tangyoubang.TybApplication;
import com.ykdl.tangyoubang.ui.view.MyRelaterLayout;
import com.ykdl.tangyoubang.ui.view.MyRelaterLayout_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.btn_get)
    Button btn_get;

    @ViewById(R.id.MyRelaterLayout_1)
    MyRelaterLayout relativeLayout_1;
    @ViewById(R.id.MyRelaterLayout_2)
    MyRelaterLayout relativeLayout_2;
    @ViewById(R.id.MyRelaterLayout_3)
    MyRelaterLayout relativeLayout_3;


    @AfterViews
    public void initView(){
        relativeLayout_1.setText("11111111");
        relativeLayout_2.setText("22222222");
        relativeLayout_3.setText("3333333333");

        MyRelaterLayout myRelaterLayout = MyRelaterLayout_.build(this);
        myRelaterLayout.setText("444444444444444");
        relativeLayout_3.addView(myRelaterLayout);

    }
    @UiThread
    public void onEvent(Object event){
        Toast.makeText(this, event.toString(), Toast.LENGTH_LONG).show();
    }

    @Click(R.id.btn_get)
    public void mybutton(){
        appService.get_captcha();
    }



}
