package com.example.lenovo.contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.*;
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Map<String,Object>> list=new ArrayList<>();
    private SimpleAdapter adapter;

    private ActionMode mode;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);

        LinearLayout l=(LinearLayout)findViewById(R.id.one);
        String []name=new String[]{"One","Two","Three","Four","Five"};
        int image=R.mipmap.ic_launcher;
        for(int i=0;i<name.length;i++)
        {
            Map<String,Object> map=new HashMap<>();
            map.put("image",image);
            map.put("name",name[i]);
            list.add(map);
        }
        adapter=new SimpleAdapter(this,list,R.layout.layout,new String[]{"image","name"},new int[]{R.id.image,R.id.text});
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(checked) {
                    num++;
                }
                else {
                    num--;
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
                    case R.drawable.select:
                        mode.setTitle(num+"selected ");
                        break;

                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }


}
