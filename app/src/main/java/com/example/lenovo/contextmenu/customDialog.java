package com.example.lenovo.contextmenu;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
