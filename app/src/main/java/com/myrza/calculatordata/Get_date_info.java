package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class Get_date_info extends AppCompatActivity implements View.OnClickListener {
    private EditText editText3;
    private TextView textView;
    private String showResult;

    Button clear_button_get_date_info;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;

    public String january = null;
    public String february = null;
    public String march = null;
    public String april = null;
    public String may = null;
    public String june = null;
    public String july = null;
    public String august = null;
    public String september = null;
    public String october = null;
    public String november = null;
    public String december = null;

    public String eto = null;
    public String denVGodu = null;
    public String ex_format_date = null;
    public String ex_date_not_exist;
    public String ex_empty_date;

    public String leapYear1 = null;
    public String leapYear2 = null;
    public String notLeapYear1 = null;
    public String notLeapYear2 = null;
    public String godu = null;

    public boolean switch_button_current_date;

    Switch b_switch;

    private boolean bool_clear_button_was_pressed_or_not = false;


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_button_get_date_info:
                editText3.setText("");
                textView.setText(getResources().getString(R.string.infoAboutDateTextView));
                b_switch.setChecked(false);
                break;
            case R.id.b_to_main2:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            /*case R.id.share_test:
                Intent intent3 = new Intent(Intent.ACTION_SEND);
                intent3.setType("text/plain");
                String body = "Download this app";
                String sub = "http://play.google.com";
                intent3.putExtra(Intent.EXTRA_TEXT, body);
                intent3.putExtra(Intent.EXTRA_TEXT, sub);
                startActivity(Intent.createChooser(intent3, "Share using"));
                break;*/
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return showResult;
        }

        @Override
        protected void onPostExecute(String response) {
            textView.setText(response);
        }

        @Override
        protected void onPreExecute() {
            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);

            january = getResources().getString(R.string.january);
            february = getResources().getString(R.string.february);
            march = getResources().getString(R.string.march);
            april = getResources().getString(R.string.april);
            may = getResources().getString(R.string.may);
            june = getResources().getString(R.string.june);
            july = getResources().getString(R.string.july);
            august = getResources().getString(R.string.august);
            september = getResources().getString(R.string.september);
            october = getResources().getString(R.string.october);
            november = getResources().getString(R.string.november);
            december = getResources().getString(R.string.december);

            eto = getResources().getString(R.string.eto);
            denVGodu = getResources().getString(R.string.denVgodu);
            ex_format_date = getResources().getString(R.string.ex_info_data);
            ex_date_not_exist = getResources().getString(R.string.res_ex_not_exist_date);
            ex_empty_date = getResources().getString(R.string.res_ex_empty_date);

            leapYear1 = getResources().getString(R.string.leapYear1);
            leapYear2 = getResources().getString(R.string.leapYear2);
            notLeapYear1 = getResources().getString(R.string.notLeapYear1);
            notLeapYear2 = getResources().getString(R.string.notLeapYear2);
            godu = getResources().getString(R.string.godu);

            showResult = DateInfo.dateInformation(editText3.getText().toString(),
                    january, february, march, april, may, june, july, august, september, october,
                    november, december,
                    monday, tuesday, wednesday, thursday, friday, saturday, sunday, eto, denVGodu,
                    ex_format_date, leapYear1, leapYear2, notLeapYear1, notLeapYear2, godu,
                    switch_button_current_date, ex_date_not_exist, ex_empty_date);


        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_date_info);

        getSupportActionBar().setTitle(getResources().getString(R.string.get_date_information));

        editText3 = findViewById(R.id.date_info_edit);

        Button get_info_about_date = findViewById(R.id.b_get_info_about_date);

        textView = findViewById(R.id.data_result);
        textView = findViewById(R.id.info_result_date);

        clear_button_get_date_info = findViewById(R.id.clear_button_get_date_info);
        clear_button_get_date_info.setOnClickListener(this);

        /////new

        clear_button_get_date_info.setOnTouchListener(new View.OnTouchListener() {
            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    bool_clear_button_was_pressed_or_not = true;
                }
                return false;
            }
        });

        /////


        b_switch = (Switch) findViewById(R.id.b_switch_current_data_activity_get_date);
        b_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch_button_current_date = isChecked;

                if (!bool_clear_button_was_pressed_or_not) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.b_cur_data_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.b_cur_data_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not = false;
            }
        });


        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);

        get_info_about_date.setOnClickListener(onClickListener);

        //////*********

        /*CardView share = findViewById(R.id.share_test);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Download this app";
                String sub = showResult;
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.putExtra(Intent.EXTRA_TEXT, sub);
                startActivity(Intent.createChooser(intent, "share using"));
                *//*startActivity(intent);*//*
            }
        });*/

        ///////************
    }

}