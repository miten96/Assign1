package com.example.assign1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assign1.Model.Record;


import java.util.ArrayList;



public class RecordAdapter extends ArrayAdapter<Record> {

    private ArrayList<Record> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tvid;
        TextView tvweight;
        TextView tvage;
        TextView tvdate;
    }

    public RecordAdapter(ArrayList<Record> data, Context context) {
        super(context, R.layout.mylist, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mylist, parent, false);
            viewHolder.tvid = (TextView) convertView.findViewById(R.id.tvid);
            viewHolder.tvweight = (TextView) convertView.findViewById(R.id.tvweight);
            viewHolder.tvage = (TextView) convertView.findViewById(R.id.tvage);
            viewHolder.tvage = (TextView) convertView.findViewById(R.id.tvdate);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.tvid.setText(dataModel.getId());
        viewHolder.tvweight.setText(dataModel.getWeight());
        viewHolder.tvage.setText(dataModel.getAge());
        viewHolder.tvdate.setText(dataModel.getDate());

        return convertView;
    }
}
