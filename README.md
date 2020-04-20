# contextMenu
第一题Android ListView<br>
![image](https://github.com/shuxiaoduo/contextMenu/blob/master/listView.png)<br>
主要代码<br>



      private ListView listView;
      private String[] name={"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
      private int image[]={R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
      private List<Map<String,Object>> lists;
      private SimpleAdapter adapter;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        lists=new ArrayList<>();
        for(int i = 0;i < 6;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("name",name[i]);
            map.put("image",image[i]);
            lists.add(map);
        }

        adapter=new SimpleAdapter(this,lists,R.layout.slayout,new String[] {"name","image"}, new int[] {R.id.text1,R.id.image1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),name[position],Toast.LENGTH_SHORT).show();
            }
        });

    }
}

<br>

第二题创建自定义布局的<br>
![image](https://github.com/shuxiaoduo/contextMenu/blob/master/customdialog.png)
<br>
主要代码<br>








       public class customDialog extends AppCompatActivity {
       @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
      Button button=(Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               customDialog();
            }
        });
    }
    public void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.dialoglayout, null);
        dialog.setView(dialogView);
        dialog.show();
        final Button cancel = (Button) findViewById(R.id.cancel);
        final Button sign = (Button) dialogView.findViewById(R.id.signin);
        try {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vew) {
                    dialog.dismiss();
                }
            });
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        } catch (Exception e)
        {
            System.out.println();
            System.out.println();
            System.out.println("message:"+e.getMessage());
        }
    }

}
<br>
第三题使用 XML 定义菜单<br>
![image](https://github.com/shuxiaoduo/contextMenu/blob/master/xmlMenu.png)<br>
主要代码<br>



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
<br>
第四题创建上下文操作模式 (ActionMode)
<br>
![Image](https://github.com/shuxiaoduo/contextMenu/blob/master/contextMenu1.png)
<br>
全选菜单选项
<br>
![Image](https://github.com/shuxiaoduo/contextMenu/blob/master/allselect.png)
<br>
清空菜单选项
<br>
![Image](https://github.com/shuxiaoduo/contextMenu/blob/master/alldelete.png)
<br>
主要代码
<br>




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
