package com.umkc.medspace.patient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.R;
import com.umkc.medspace.adapters.Chat_list_Adapter;
import com.umkc.medspace.api.objects.ChatMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat extends BaseAppcompatActivity implements View.OnClickListener {

    private String name, mobile;
    private ListView lvMessages;
    private ImageView send;
    private EditText etmessage;
    private List<String> stringsR = new ArrayList<>();
    private List<String> stringsS = new ArrayList<>();
    String[] ListElements = new String[]{};
    private ListView listOfMessagesR;
    private ListView listOfMessagesS;
    private Chat_list_Adapter adapter;
    private ArrayList<String> ListElementsArrayList;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        name = getIntent().getExtras().getString("name", "");
        mobile = getIntent().getExtras().getString("mobile", "");
        toolbar_title.setText(name);

        etmessage = (EditText) findViewById(R.id.message);
        send = (ImageView) findViewById(R.id.send);

        send.setOnClickListener(this);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listOfMessagesR = (ListView) findViewById(R.id.lvMessagesReceive);
        listOfMessagesS = (ListView) findViewById(R.id.lvMessagesSent);
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                String message = etmessage.getText().toString();
                postMessage(message);
                break;
        }
    }

    private void postMessage(String message) {
        stringsS.add(message);
        Log.e("stringsS", String.valueOf(stringsS));
        adapter = new Chat_list_Adapter(this, R.layout.chat_adapter, stringsS);
        listOfMessagesS.setAdapter(adapter);
    }
}
