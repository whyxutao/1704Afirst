package bw.com.xutao0830.untils;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    private static HttpUtils httpUtils = null;

    private HttpUtils(){}

    public static HttpUtils getInstance(){
        if (httpUtils==null){
            synchronized (HttpUtils.class){
                if (httpUtils==null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    private Handler handler = new Handler();

    public interface ICallBack{
        void onSuccess(Object json);
        void onError(String msg);
    }

    public void getJson(final String path, final ICallBack iCallBack){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200) {
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while ((len=inputStream.read(buffer))!=-1){
                            bos.write(buffer,0,len);
                        }

                        final String s = bos.toString();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (iCallBack!=null) {
                                    iCallBack.onSuccess(s);
                                }
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
