package com.example.baskinrobbins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
        setTitle("배스킨 라빈스 31");

        Button.OnClickListener onClickListener = new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (view.getId()){
                    case R.id.btn_start:
                        intent = new Intent(getApplicationContext(),SecondActivity.class);
                        startActivityForResult(intent,0);
                        break;
                    case R.id.btn_help:
                        intent = new Intent(getApplicationContext(),ThirdActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_end:
                        finish();
                        break;
                }
            }
        };

        Button btn_start,btn_help,btn_end;
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(onClickListener);
        btn_help = (Button)findViewById(R.id.btn_help);
        btn_help.setOnClickListener(onClickListener);
        btn_end = (Button)findViewById(R.id.btn_end);
        btn_end.setOnClickListener(onClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            int time = data.getIntExtra("Time",0);
            Toast.makeText(getApplicationContext(),Integer.toString(time)+"분 내에 방문해주세요",Toast.LENGTH_SHORT).show();
        }
    }
}
