package com.ykdl.tangyoubang.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ykdl.tangyoubang.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yuanwenfei on 2014/5/30.
 */
@EViewGroup(R.layout.component)
public class MyRelaterLayout extends RelativeLayout {

    @ViewById(R.id.textview)
    TextView textView;

    public MyRelaterLayout(Context context) {
        super(context);
    }
    public MyRelaterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyRelaterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setText(String str){
        textView.setText(str);
    }



}
