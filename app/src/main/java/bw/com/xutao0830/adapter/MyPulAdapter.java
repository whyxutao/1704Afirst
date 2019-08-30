package bw.com.xutao0830.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.com.xutao0830.R;
import bw.com.xutao0830.bean.NewsInfo;

public class MyPulAdapter extends BaseAdapter {
    private List<NewsInfo.ResultInfo> result;

    private Context context;

    public MyPulAdapter(List<NewsInfo.ResultInfo> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null){

            convertView = View.inflate(context, R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.itemimage);
            viewHolder.textView = convertView.findViewById(R.id.itemtext);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(result.get(position).getSummary());
        Glide.with(context).load(result.get(position).getImageUrl()).into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder{

        TextView textView;
        ImageView imageView;

    }

}
