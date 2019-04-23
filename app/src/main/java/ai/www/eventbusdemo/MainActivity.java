package ai.www.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

      //你好

        EventBus.getDefault().unregister(this);
    }


    //threadMode的几种类型 用于指定线程模型
    /*ThreadMode.POSTING    默认的线程模式;接收事件和发送的事件在同一个线程中
    *ThreadMode: MAIN       事件的处理会在UI线程中执行
    *ThreadMode: BACKGROUND   如果发送在ui线程，则处理在新线程，否则在发送线程。 不可进行ui更新
    *
    * ThreadMode: ASYNC       该事件处理函数都会在新建的子线程中执行
    *
    *
    * EventBus.getDefault().postSticky()
    * 先发布事件，然后在订阅
    * */


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void studentReso(Student student) {


        Log.e("student", "= " + student.getName());

    }

    public void send(View view) {

        EventBus.getDefault().postSticky(new Student("海洋"));//先发布事件，然后在订阅
        startActivity(new Intent(this, SecondActivity.class));
    }
}
