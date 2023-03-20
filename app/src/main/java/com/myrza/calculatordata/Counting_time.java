package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Counting_time extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_entered_data;

    private EditText edit_sec;
    private EditText edit_min;
    private EditText edit_hour;
    private EditText edit_days;
    private EditText edit_weeks;
    private EditText edit_month;
    private EditText edit_years;

    private TextView textView;
    private String showResult;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;

    public int getSec;
    public int getMin;
    public int getHour;
    public int getDays;
    public int getWeeks;
    public int getMonth;
    public int getYears;

    public String info1;
    public String info2;
    public String info3;
    public String info4;

    public String res_if_2_butt_true;
    public String res_if_2_butt_false;

    public String ex_format_data = null;
    public String ex_if_date_not_exist;

    private boolean switch_button_current_date;
    private boolean bool_checkBox_plus;
    private boolean bool_checkBox_minus;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch b_switch_cur_data;
    private CheckBox checkBox_plus;
    private CheckBox checkBox_minus;

    private boolean bool_clear_button_was_pressed_or_not1 = false;
    private boolean bool_clear_button_was_pressed_or_not2 = false;
    private boolean bool_clear_button_was_pressed_or_not3 = false;

    ///////new*****
    private int index = 0;
    private final long delay = 35; // задержка между выводом символов, в миллисекундах
    ///////

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restart_button_counting_time:
                edit_sec.setText("");
                edit_min.setText("");
                edit_hour.setText("");
                edit_days.setText("");
                edit_weeks.setText("");
                edit_month.setText("");
                edit_years.setText("");

                edit_entered_data.setText("");
                textView.setText(getResources().getString(R.string.res_textview_counting_date));

                b_switch_cur_data.setChecked(false);
                checkBox_plus.setChecked(false);
                checkBox_minus.setChecked(false);

                break;
            case R.id.b_to_main_counting_time:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return showResult = CountingTime.plusAndMinus(edit_entered_data.getText().toString(),
                    getSec, getMin, getHour, getDays, getWeeks, getMonth, getYears,
                    bool_checkBox_plus, bool_checkBox_minus, switch_button_current_date,
                    sunday, monday, tuesday, wednesday, thursday, friday, saturday, ex_format_data,
                    info1, info2, info3, info4, res_if_2_butt_true, res_if_2_butt_false,
                    ex_if_date_not_exist);
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


            try {
                getSec = Integer.parseInt(edit_sec.getText().toString());
            } catch (NumberFormatException e) {
                getSec = 0;
            }

            try {
                getMin = Integer.parseInt(edit_min.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                getMin = 0;
            }

            try {
                getHour = Integer.parseInt(edit_hour.getText().toString());
            } catch (NumberFormatException e) {
                getHour = 0;
            }

            try {
                getDays = Integer.parseInt(edit_days.getText().toString());
            } catch (NumberFormatException e) {
                getDays = 0;
            }

            try {
                getWeeks = Integer.parseInt(edit_weeks.getText().toString());
            } catch (NumberFormatException e) {
                getWeeks = 0;
            }

            try {
                getMonth = Integer.parseInt(edit_month.getText().toString());
            } catch (NumberFormatException e) {
                getMonth = 0;
            }

            try {
                getYears = Integer.parseInt(edit_years.getText().toString());
            } catch (NumberFormatException e) {
                getYears = 0;
            }


            info1 = getResources().getString(R.string.info1_for_counting_date);
            info2 = getResources().getString(R.string.info2_for_counting_date);
            info3 = getResources().getString(R.string.info3_for_counting_date);
            info4 = getResources().getString(R.string.info4_for_counting_date);

            ex_format_data = getResources().getString(R.string.exception_format_data_for_counting_date);
            ex_if_date_not_exist = getResources().getString(R.string.res_ex_not_exist_date);

            res_if_2_butt_true = getResources().getString(R.string.res_if_2_button_true);
            res_if_2_butt_false = getResources().getString(R.string.res_if_2_button_false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_time);

        /////new
        Context context = this;
        Resources resources = context.getResources();
        //////

        getSupportActionBar().setTitle(getResources().getString(R.string.res_b_counting_time));

        edit_entered_data = findViewById(R.id.edit_counting_date);
        edit_sec = findViewById(R.id.edit_sec);
        edit_min = findViewById(R.id.edit_min);
        edit_hour = findViewById(R.id.edit_hour);
        edit_days = findViewById(R.id.edit_days);
        edit_weeks = findViewById(R.id.edit_weeks);
        edit_month = findViewById(R.id.edit_month);
        edit_years = findViewById(R.id.edit_years);

        Button get_info_about_count = findViewById(R.id.b_get_info_about_count);

        textView = findViewById(R.id.info_result_date_count);

        /////new

        findViewById(R.id.restart_button_counting_time).setOnTouchListener(new View.OnTouchListener() {
            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    bool_clear_button_was_pressed_or_not1 = true;
                    bool_clear_button_was_pressed_or_not2 = true;
                    bool_clear_button_was_pressed_or_not3 = true;
                }
                return false;
            }
        });

        /////

        b_switch_cur_data = (Switch) findViewById(R.id.b_switch_current_data_activity_counting);
        b_switch_cur_data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch_button_current_date = isChecked;

                if (!bool_clear_button_was_pressed_or_not1) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.switch_button_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.switch_button_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not1 = false;
            }
        });

        checkBox_plus = (CheckBox) findViewById(R.id.radioButton_plus);
        checkBox_plus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_checkBox_plus = isChecked;

                if (!bool_clear_button_was_pressed_or_not2) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.res_b_plus), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.res_b_plus2), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not2 = false;
            }
        });

        checkBox_minus = (CheckBox) findViewById(R.id.radioButton_minus);
        checkBox_minus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_checkBox_minus = isChecked;

                if (!bool_clear_button_was_pressed_or_not3) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.res_b_minus), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.res_b_minus2), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not3 = false;
            }
        });

        ///////new*****
        String info_text = resources.getString(R.string.res_textview_counting_date);

        // создаем Handler для задержки вывода каждого символа
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // выводим следующий символ
                textView.setText(info_text.substring(0, index++));
                // проверяем, достигли ли конца строки
                if (index <= info_text.length()) {
                    // постим задачу для следующего символа через delay миллисекунд
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
        /////////////************

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);
        get_info_about_count.setOnClickListener(onClickListener);


    }

}