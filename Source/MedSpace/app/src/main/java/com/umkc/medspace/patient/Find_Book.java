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
import com.umkc.medspace.adapters.Menu_Book_Adapter;
import com.umkc.medspace.api.objects.Menu;

import java.util.ArrayList;
import java.util.List;

public class Find_Book extends BaseAppcompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Menu_Book_Adapter adapter;
    List<Menu> menuList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Find & Book");

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        listView = (ListView) findViewById(R.id.lvtest);
        adapter = new Menu_Book_Adapter(this, menuList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        menuList.add(new Menu(0, "Doctors", R.drawable.doctors));
        menuList.add(new Menu(1, "Dentists", R.drawable.tooth));
        menuList.add(new Menu(2, "Alternative Medicine Doctors", R.drawable.first));
        menuList.add(new Menu(3, "Therapists & Nutritionists", R.drawable.woman));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://
                startActivity(new Intent(this, Specialist.class));
                Constants.activity_stack.add(this);
                break;
        }
    }
}
