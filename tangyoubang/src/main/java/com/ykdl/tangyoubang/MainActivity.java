package com.ykdl.tangyoubang;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.ykdl.tangyoubang.Event.UserEvent;
import com.ykdl.tangyoubang.RestClient.Handler.TybRestErrorHandler;
import com.ykdl.tangyoubang.RestClient.TybApi;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


    String[] books;// get array of string
    String   name = null;

    @ViewById(R.id.btn_get)
    Button btn_get;

    @RestService
    TybApi tybApi;

    @App
    TybApplication  application;

    @Bean
    AppService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
//            tybApi.setHeader("head1", "a");
//            tybApi.setHeader("head2", "b");
//            tybApi.getUser();


           DB snappydb = DBFactory.open(this);

            snappydb.put("name", "Jack Reacher");
            snappydb.putInt("age", 42);
            snappydb.putBoolean("single", true);
            snappydb.put("books", new String[]{"One Shot", "Tripwire", "61 Hours"});

            name   =  snappydb.get("name");
            int      age    =  snappydb.getInt("age");
            boolean  single =  snappydb.getBoolean("single");

            books = snappydb.getArray("books", String.class);

            snappydb.close();

        } catch (SnappydbException e) {
        }
    }


    @UiThread
    public void onEvent(UserEvent userEvent){
        Toast.makeText(this, userEvent.getJson(), Toast.LENGTH_LONG).show();
    }
    @Click(R.id.btn_get)
    public void mybutton(){
        service.getUser();
//        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        application.BUS.register(this);
    }

    @Override
    protected void onStop() {
        super.onDestroy();
        application.BUS.unregister(this);
    }
}
