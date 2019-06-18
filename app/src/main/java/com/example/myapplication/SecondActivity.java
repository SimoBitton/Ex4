package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[][] maze = {
                {1, 1, 1, 1, 1, 0, 1, 0},

                {1, 1, 0, 0, 1, 0, 1, 1}
        };
        Point playerPos = new Point(0, 1);
        Point exitPos = new Point(5, 9);
        JoyStickView mazeBoard = new JoyStickView(this);
        setContentView(mazeBoard);
    }

}
