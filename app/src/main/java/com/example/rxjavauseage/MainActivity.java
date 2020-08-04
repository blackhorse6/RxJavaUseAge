package com.example.rxjavauseage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnDouble, btnThrottleFirst, btnTimer;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDouble = findViewById(R.id.btn_debounce);
        btnThrottleFirst = findViewById(R.id.btn_throttle_first);
        editText = findViewById(R.id.edit_query);
        textView = findViewById(R.id.text_tip);
        btnTimer = findViewById(R.id.btn_timer);
        debounce();
        throttleFirst();
        editControlThrottleFirst();
        RxJavaFilter.debounce();
//        RxJavaFilter.sample();
//        RxJavaFilter.throttleFirst();
//        RxJavaFilter.throttleLast();
        RxJavaFilter.throttleWithTimeOut();
        timer();


    }

    /**
     * 5秒内只发射1次
     */
    private void debounce() {
        RxView.clicks(btnDouble)
                .debounce(5, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(unit -> Log.d(TAG, "btnDouble debounce 被点击了"));
    }

    /**
     * 只发射第一个开始计时的5秒内数据
     */
    private void throttleFirst() {
        RxView.clicks(btnThrottleFirst)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(unit -> Log.d(TAG, "btnDouble debounce 被点击了"));
    }

    /**
     * 5秒内有效 debounce
     */
    private void editControlDebounce() {
        RxTextView.textChanges(editText)
                .skipInitialValue()
                .debounce(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    textView.setText(charSequence);
                    Log.d(TAG, "搜索的内容是:" + charSequence);
                });
    }

    private void editControlThrottleFirst() {
        RxTextView.textChanges(editText)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    textView.setText(charSequence);
                    Log.d(TAG, "搜索的内容是:" + charSequence);
                });
    }

    private void timer() {

        RxView.clicks(btnTimer)
                .throttleFirst(20, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(unit -> Observable.intervalRange(1, 20, 0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {

                            Log.d(TAG, "method=【timer】" + aLong);
                            btnTimer.setText("定时器:" + aLong);
                        }));

    }

}
