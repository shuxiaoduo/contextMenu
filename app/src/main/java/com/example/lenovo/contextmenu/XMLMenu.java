package com.example.lenovo.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class XMLMenu extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlmenu_layout);
        text=(TextView)findViewById(R.id.text);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator=new MenuInflater(this);
        //装填R.menu.my_menu对应的菜单，并添加到menu中
        inflator.inflate(R.menu.xmlmenu, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_res_test, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId())
        {
            case R.id.font_10:
                text.setTextSize(10);
                break;
            case R.id.font_16:
                text.setTextSize(16);
                break;
            case R.id.font_20:
                text.setTextSize(20);
                break;
            case R.id.putong:
                Toast.makeText(this,"普通菜单项",Toast.LENGTH_LONG).show();
                break;
            case R.id.font_red:
                text.setTextColor(Color.RED);
                break;
            case R.id.font_black:
                text.setTextColor(Color.BLACK);
                break;
        }
        return true;
    }

}
