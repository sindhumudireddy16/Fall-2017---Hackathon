/*
 * Copyright (C) 2017 Bharathi Tech Services Pvt. Ltd.
 */

package com.umkc.medspace.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.umkc.medspace.R;

import java.util.List;


public class Chat_list_Adapter extends ArrayAdapter<String> {

    Context context;
    LayoutInflater inflater;
    List<String> list;

    public Chat_list_Adapter(@NonNull Context context, int resource, List<String> list) {
        super(context, resource);
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView textView;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.chat_adapter, null);
            holder.textView = (TextView) view.findViewById(R.id.message);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(position).toUpperCase());
        return view;
    }
}
