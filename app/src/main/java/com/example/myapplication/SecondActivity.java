package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private JoyStickView joyStickView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joyStickView = new JoyStickView(this);
        setContentView(joyStickView);
    }
    public void onDestroy() {
        joyStickView.close();
        super.onDestroy();
    }
}
