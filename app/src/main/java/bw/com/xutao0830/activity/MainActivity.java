package bw.com.xutao0830.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import bw.com.xutao0830.R;
import bw.com.xutao0830.adapter.MyPulAdapter;
import bw.com.xutao0830.base.BaseActivity;
import bw.com.xutao0830.bean.NewsInfo;
import bw.com.xutao0830.untils.HttpUtils;

public class MainActivity extends BaseActivity {


    private PullToRefreshListView pul;
    private int page = 1;
    private Handler handler = new Handler();
    private String path = "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?page=1&count=20";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        pul = findViewById(R.id.pul);
        pul.setMode(PullToRefreshListView.Mode.BOTH);

    }

    @Override
    protected void initData() {

        pul.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                page =1;

                getServerData(page);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pul.onRefreshComplete();
                    }
                },2000);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                page++;

                getServerData(page);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pul.onRefreshComplete();
                    }
                },2000);

            }
        });

    }

    private void getServerData(int page) {

        HttpUtils.getInstance().getJson(path+page, new HttpUtils.ICallBack() {
            @Override
            public void onSuccess(Object json) {

                Gson gson = new Gson();
                NewsInfo newsInfo = gson.fromJson(json.toString(), NewsInfo.class);
                List<NewsInfo.ResultInfo> result = newsInfo.getResult();

                MyPulAdapter adapter = new MyPulAdapter(result,MainActivity.this);
                pul.setAdapter(adapter);

            }

            @Override
            public void onError(String msg) {

            }
        });

    }
}
