package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnSave;
    private Button btnRate;
    private Button btnExit;
    private EditText editName;
    private String mPlayerName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        editName = (EditText) findViewById(R.id.player_name);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mPlayerName = editName.getText().toString();
                if (mPlayerName == null) {
                    String tips = "请输入您当局的游戏ID";
                    Toast.makeText(StartActivity.this,tips,Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                Bundle bundle = new Bundle();//将游戏用户ID传入到游戏界面
                bundle.putInt("name",mPlayerName);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mPlayerName = editName.getText().toString();
                if (mPlayerName == null) {
                    String tips = "请输入您当局的游戏ID";
                    Toast.makeText(StartActivity.this,tips,Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        btnRate = (Button) findViewById(R.id.btn_rate);
        btnRate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mPlayerName = editName.getText().toString();
                if (mPlayerName == null) {
                    String tips = "请输入您当局的游戏ID";
                    Toast.makeText(StartActivity.this,tips,Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mPlayerName = editName.getText().toString();
                if (mPlayerName == null) {
                    String tips = "请输入您当局的游戏ID";
                    Toast.makeText(StartActivity.this,tips,Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
