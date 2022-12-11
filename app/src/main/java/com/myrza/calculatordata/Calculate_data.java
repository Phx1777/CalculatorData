package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculate_data extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private String showResult;

    Button restart_button;

    public String goodbye = null;
    public String sec = null;
    public String min = null;
    public String hour = null;
    public String day = null;
    public String week = null;
    public String month = null;
    public String year = null;
    public String interval = null;
    public String exceptionDate2Later = null;
    public String exceptionDatesEquals = null;
    public String exceptionFormatData = null;


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restart_button:
                Intent intent = new Intent(this, Calculate_data.class);
                startActivity(intent);
                break;
            case R.id.b_to_main:
                Intent goBack = new Intent(this, MainActivity.class);
                startActivity(goBack);
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
            goodbye = getText(R.string.goodbye).toString();
            sec = getResources().getString(R.string.sec);
            min = getResources().getString(R.string.min);
            hour = getResources().getString(R.string.hour);
            day = getResources().getString(R.string.day);
            week = getResources().getString(R.string.week);
            month = getResources().getString(R.string.month);
            year = getResources().getString(R.string.year);

            interval = getResources().getString(R.string.interval);

            exceptionDate2Later = getResources().getString(R.string.exceptionDate2Later);
            exceptionDatesEquals = getResources().getString(R.string.exceptionDatesEquals);
            exceptionFormatData = getResources().getString(R.string.exceptionFormatData);

            try {
                showResult = CalculateData.calcData(
                        editText1.getText().toString(),
                        editText2.getText().toString(), sec, min, hour, day, week, month, year,
                        exceptionDate2Later, exceptionDatesEquals, exceptionFormatData, goodbye,
                        interval);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_data);

        editText1 = findViewById(R.id.input_data1);
        editText2 = findViewById(R.id.input_data2);

        Button do_calc_button = findViewById(R.id.button_do_calc_data);

        textView = findViewById(R.id.data_result);

        restart_button = findViewById(R.id.restart_button);
        restart_button.setOnClickListener(this);

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);

        do_calc_button.setOnClickListener(onClickListener);
    }

}