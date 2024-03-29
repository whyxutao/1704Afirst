package bw.com.xutao0830.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        initView();

        initData();

    }

    protected abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();
}
