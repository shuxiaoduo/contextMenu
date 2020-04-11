package com.example.lenovo.contextmenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.*;
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    People people[]=new People[5];
    private SimpleAdapter adapter;
    String []name=new String[]{"One","Two","Three","Four","Five"};
    int image=R.mipmap.ic_launcher;
    private ActionMode mode;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<people.length;i++)
        {
            people[i]=new People();
            people[i].setChecked(false);
            people[i].setImage(R.mipmap.ic_launcher);
        }
        people[0].setName("ONE");
        people[1].setName("TWO");
        people[2].setName("THREE");people[3].setName("FOUR");people[4].setName("FIVE");
        listView=(ListView)findViewById(R.id.listview);
        final MyAdapter adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(checked) {
                    people[position].setChecked(true);
                    num++;
                    adapter.notifyDataSetChanged();
                }
                else {
                    people[position].setChecked(false);
                    num--;
                    adapter.notifyDataSetChanged();
                }
                mode.setTitle(num+"selected");

            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.mymenu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int n=item.getItemId();
                switch(n)
                {
                    case R.id.select:
                        for(int i=0;i<people.length;i++) {
                            people[i].setChecked(true);
                            listView.setItemChecked(i,true);
                        }
                        num=5;
                        Toast.makeText(getApplicationContext(),"All selected",Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        mode.setTitle(num+"selected");
                        break;
                    case R.id.delete:
                        for(int i=0;i<people.length;i++)
                        {
                            people[i].setChecked(false);
                            listView.setItemChecked(i,false);
                        }
                        num=0;
                        Toast.makeText(getApplicationContext(),"All delete",Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        mode.setTitle(num+"selected");
                        break;


                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return name[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("TEST", "refresh once");
            convertView = inflater.inflate(R.layout.layout, null, false);
            ImageView img = (ImageView) convertView.findViewById(R.id.image);// 用于显示图片
            TextView tv = (TextView) convertView.findViewById(R.id.text);// 显示文字
            tv.setText(people[position].getName());
            img.setImageResource(people[position].getImage());
            if (people[position].getChecked()) {// 如果当前的行就是ListView中选中的一行，就更改显示样式
                convertView.setBackgroundColor(Color.LTGRAY);// 更改整行的背景色
                tv.setTextColor(Color.RED);// 更改字体颜色
            }
            return convertView;
        }
    }

}
