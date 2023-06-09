package com.example.ranga.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ranga.R;

public class MainActivity extends AppCompatActivity
{
    private static final long DOUBLE_PRESS_TIME_DELTA = 2000; // Временной интервал между двойными нажатиями в миллисекундах
    private long lastBackPressedTime = 0; // Время последнего нажатия на кнопку "Назад"

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}