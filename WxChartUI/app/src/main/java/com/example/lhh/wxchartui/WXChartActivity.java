package com.example.lhh.wxchartui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lhh on 2017/11/18.
 */
public class WXChartActivity extends AppCompatActivity {

    private final int VIEW_TYPE = 0xb01;
    private final int VIEW_TYPE_LEFT = -10;
    private final int VIEW_TYPE_RIGHT = -11;

    private final int MESSAGE = 0xb02;
    private final int MSG_SIZE = 10;

    private ArrayList<HashMap<Integer, Object>> items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_chart);

        final ListView lstView = (ListView) findViewById(android.R.id.list);
        items = new ArrayList<HashMap<Integer, Object>>();

        for (int iIndex = 0; iIndex < MSG_SIZE; ++iIndex){
            if (iIndex % 2 == 0){
                HashMap<Integer, Object> hMap = new HashMap<Integer, Object>();
                hMap.put(VIEW_TYPE, VIEW_TYPE_RIGHT);
                hMap.put(MESSAGE, "本人的消息" + iIndex);
                items.add(hMap);
            }
            else{
                HashMap<Integer, Object> hMap = new HashMap<Integer, Object>();
                hMap.put(VIEW_TYPE, VIEW_TYPE_LEFT);
                hMap.put(MESSAGE, "对方的消息" + iIndex);
                items.add(hMap);
            }
            // 输入框发送消息后将ListView滚动到最底部
            lstView.setSelection(ListView.FOCUS_DOWN);
        }

        final MyAdapter myAdapter = new MyAdapter(this, -1);
        lstView.setAdapter(myAdapter);

        final EditText editText_send = (EditText) findViewById(R.id.edit_send);
        final Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String msg = editText_send.getText() + "";
                HashMap<Integer, Object> map = new HashMap<Integer, Object>();
                map.put(VIEW_TYPE, VIEW_TYPE_RIGHT);
                map.put(MESSAGE, msg);
                items.add(map);

                myAdapter.notifyDataSetChanged();

                // 发送后清空输入框内容
                editText_send.setText(null);

                // 输入框发送消息后将ListView滚动到最底部
                lstView.setSelection(ListView.FOCUS_DOWN);

            }
        });
    }


    private class MyAdapter extends ArrayAdapter {

        private LayoutInflater layoutInflater;

        public MyAdapter(Context context, int resource) {
            super(context, resource);
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            int type = getItemViewType(pos);
            String msg = getItem(pos);

            switch (type) {
                case VIEW_TYPE_LEFT:
                    convertView = layoutInflater.inflate(R.layout.base_left_usr, null);
                    TextView textLeft = (TextView) convertView.findViewById(R.id.usr_msg);
                    textLeft.setText(msg);
                break;

                case VIEW_TYPE_RIGHT:
                    convertView = layoutInflater.inflate(R.layout.base_right_usr, null);
                    TextView textRight = (TextView) convertView.findViewById(R.id.usr_msg);
                    textRight.setText(msg);
                break;
            }

            return convertView;
        }

        @Override
        public String getItem(int pos) {
            String s = items.get(pos).get(MESSAGE) + "";
            return s;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(int pos) {
            int type = (Integer) items.get(pos).get(VIEW_TYPE);
            return type;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }

}
