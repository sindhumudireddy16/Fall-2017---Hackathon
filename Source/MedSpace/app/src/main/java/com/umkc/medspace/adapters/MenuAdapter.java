package com.umkc.medspace.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.R;
import com.umkc.medspace.api.objects.Menu;

import java.util.ArrayList;
import java.util.List;


public class MenuAdapter extends BaseAdapter {
    Context mCtx;
    List<Menu> mMenuList = new ArrayList<>();

    public MenuAdapter(Context mCtx, List<Menu> list) {
        this.mCtx = mCtx;
        this.mMenuList = list;
    }

    @Override
    public int getCount() {
        return mMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder;
        View convertView = view;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mCtx).getLayoutInflater();
            holder = new Holder();

            convertView = inflater.inflate(R.layout.singleitem, null);
            holder.tvMenuName = (TextView) convertView
                    .findViewById(R.id.tvMenuItem);
            holder.ivMenu = (ImageView) convertView
                    .findViewById(R.id.ivProfileMenu);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();

        }
        Menu item = mMenuList.get(position);
        holder.tvMenuName.setText(item.getMenuTitle());
        holder.ivMenu.setImageResource(item.getMenuImage());

        return convertView;
    }

    static class Holder {
        TextView tvMenuName;
        ImageView ivMenu;
    }
}