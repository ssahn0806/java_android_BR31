package com.example.baskinrobbins;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ThirdActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TabHost tabHost = getTabHost();

        TabSpec tabStep1 = tabHost.newTabSpec("SIZE").setIndicator("[1단계] 크기 선택");
        tabStep1.setContent(R.id.tabStep1);
        tabHost.addTab(tabStep1);

        TabSpec tabStep2 = tabHost.newTabSpec("FLAVOR").setIndicator("[2단계] 맛 선택");
        tabStep2.setContent(R.id.tabStep2);
        tabHost.addTab(tabStep2);

        TabSpec tabStep3 = tabHost.newTabSpec("CONFIRM").setIndicator("[3단계] 최종 확인");
        tabStep3.setContent(R.id.tabStep3);
        tabHost.addTab(tabStep3);
        tabHost.setCurrentTab(0);

        Button btn1 = (Button)findViewById(R.id.btn_return1);
        Button btn2 = (Button)findViewById(R.id.btn_return2);
        Button btn3 = (Button)findViewById(R.id.btn_return3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
