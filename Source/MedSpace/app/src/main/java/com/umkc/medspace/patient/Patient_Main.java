package com.umkc.medspace.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.adapters.MenuAdapter;
import com.umkc.medspace.api.objects.Menu;

import java.util.ArrayList;
import java.util.List;

public class Patient_Main extends BaseAppcompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private MenuAdapter adapter;
    List<Menu> menuList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_patient_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.GONE);
        toolbar_title.setText("Home");


        listView = (ListView) findViewById(R.id.lvtest);
        adapter = new MenuAdapter(this, menuList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        menuList.add(new Menu(0, "Book an appointment", R.drawable.book));
        menuList.add(new Menu(1, "Chat with doctor now", R.drawable.smartphone));
        menuList.add(new Menu(2, "Set Medicine reminders", R.drawable.door_bell));
        menuList.add(new Menu(3, "Help & Support", R.drawable.info));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://book
                startActivity(new Intent(this, Find_Book.class));
                Constants.activity_stack.add(this);
                break;
            case 1://Specialist
                startActivity(new Intent(this, Specialist.class));
                Constants.activity_stack.add(this);
                break;
            case 2://reminders
                startActivity(new Intent(this, Reminder.class));
                Constants.activity_stack.add(this);
                break;
            case 3://book
                startActivity(new Intent(this, Help_Support.class));
                break;
        }
    }
}
