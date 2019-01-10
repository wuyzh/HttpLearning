package com.wuyazhou.learn.httplearning.showlogview;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wuyazhou.learn.httplearning.utils.UIDisplayHelper;

import java.util.List;

/**
 * @author wuyzh
 * */
public class LogListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShowLogView.LogModel> mList = null;
    public LogListViewAdapter(Context context, List<ShowLogView.LogModel> list){
        mContext = context;
        mList = list;

    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holderView;
        ShowLogView.LogModel entity = mList.get(position);
        Resources resources = mContext.getResources();

        if (convertView == null){
            convertView = new TextView(mContext);
            ((TextView) convertView).setTextSize(UIDisplayHelper.sp2px(5));
            holderView = new ViewHolder();

            holderView.textLogView = (TextView) convertView;
            convertView.setTag(holderView);
        }else {
            holderView = (ViewHolder) convertView.getTag();
        }

        holderView.textLogView.setText(Html.fromHtml( "<font color=#35A5BB>"+ mList.get(position).key+": "+"</font> "+ "<font color=#EDB03A>"+ mList.get(position).value+"</font>"));

        return convertView;

    }

    public final class ViewHolder {
        TextView textLogView;
    }
}
