package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class World_holidays extends AppCompatActivity implements View.OnClickListener {

    Switch switchChangeTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем текущую тему из SharedPreferences
        sharedPreferences = getSharedPreferences("THEME", Context.MODE_PRIVATE);
        int currentTheme = sharedPreferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_NO);

        // Устанавливаем тему
        AppCompatDelegate.setDefaultNightMode(currentTheme);

        setContentView(R.layout.activity_world_holidays);

        switchChangeTheme = findViewById(R.id.switch_test_change_theme);
        switchChangeTheme.setOnClickListener(this);

        // Устанавливаем положение Switch в зависимости от текущей темы
        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            switchChangeTheme.setChecked(true);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_to_main2:
                Intent intent_main = new Intent(this, MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.b_back_to_holidays_activity:
                Intent intent_holidays = new Intent(this, Holidays.class);
                startActivity(intent_holidays);
                break;
            case R.id.switch_test_change_theme:
                if (switchChangeTheme.isChecked()) {
                    // Устанавливаем ночную тему
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    // Сохраняем тему в SharedPreferences
                    editor = sharedPreferences.edit();
                    editor.putInt("theme", AppCompatDelegate.MODE_NIGHT_YES);
                    editor.apply();
                } else {
                    // Устанавливаем дневную тему
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    // Сохраняем тему в SharedPreferences
                    editor = sharedPreferences.edit();
                    editor.putInt("theme", AppCompatDelegate.MODE_NIGHT_NO);
                    editor.apply();
                }
                break;
            default:
                break;
        }
    }
}