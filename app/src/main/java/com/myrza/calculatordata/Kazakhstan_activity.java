package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myrza.calculatordata.kz_hol_all.*;


public class Kazakhstan_activity extends AppCompatActivity implements View.OnClickListener {

    public static boolean bol_to_activate_new_year;
    public static boolean bol_to_activate_rojdestvo;
    public static boolean bol_to_activate_march8;
    public static boolean bol_to_activate_nauryz;
    public static boolean bol_to_activate_edinstv_naroda_kz;
    public static boolean bol_to_activate_den_zawit_ote4;
    public static boolean bol_to_activate_den_pobedy;
    public static boolean bol_to_activate_den_stolicy;
    public static boolean bol_to_activate_kurban_ait;
    public static boolean bol_to_activate_den_konstitucii;
    public static boolean bol_to_activate_den_nezavisimosti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kazakhstan);

        getSupportActionBar().setTitle(getResources().getString(R.string.b_kazakhstan));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_to_main2:
                Intent intent_main = new Intent(this, MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.b_back_to_holidays_activity_kzz:
                Intent intent_holidays = new Intent(this, Holidays.class);
                startActivity(intent_holidays);
                break;
            case R.id.new_year_kz:
                Intent intent_new_year = new Intent(this, Kz_hol_new_year.class);
                startActivity(intent_new_year);
                bol_to_activate_new_year = true;
                break;
            case R.id.rojdestvo:
                Intent intent_rojdestvo = new Intent(this, Kz_hol_rojdestvo.class);
                startActivity(intent_rojdestvo);
                bol_to_activate_rojdestvo = true;
                break;
            case R.id.march8:
                Intent intent_march8 = new Intent(this, Kz_hol_march8.class);
                startActivity(intent_march8);
                bol_to_activate_march8 = true;
                break;
            case R.id.nauryz:
                Intent intent_nauryz = new Intent(this, Kz_hol_nauryz.class);
                startActivity(intent_nauryz);
                bol_to_activate_nauryz = true;
                break;
            case R.id.edinstvo_naroda_kz:
                Intent intent_edinstvo_naroda_kz = new Intent(this, Kz_hol_edinstv_naroda.class);
                startActivity(intent_edinstvo_naroda_kz);
                bol_to_activate_edinstv_naroda_kz = true;
                break;
            case R.id.den_zawitnika_ote4estva:
                Intent intent_den_zawitnika_ote4 = new Intent(this, Kz_hol_den_zawitnik_ote4estva.class);
                startActivity(intent_den_zawitnika_ote4);
                bol_to_activate_den_zawit_ote4 = true;
                break;
            case R.id.den_pobedy:
                Intent intent_den_pobedy = new Intent(this, Kz_hol_den_pobedy.class);
                startActivity(intent_den_pobedy);
                bol_to_activate_den_pobedy = true;
                break;
            case R.id.den_stolicy:
                Intent intent_den_stolicy = new Intent(this, Kz_hol_den_stolicy.class);
                startActivity(intent_den_stolicy);
                bol_to_activate_den_stolicy = true;
                break;
            case R.id.kurban_ait:
                Intent intent_kurban_ait = new Intent(this, Kz_hol_kurban_ait.class);
                startActivity(intent_kurban_ait);
                bol_to_activate_kurban_ait = true;
                break;
            case R.id.den_konstitucii:
                Intent intent_den_konstitucii = new Intent(this, Kz_hol_den_konstitucii.class);
                startActivity(intent_den_konstitucii);
                bol_to_activate_den_konstitucii = true;
                break;
            case R.id.den_nezavisimosti:
                Intent intent_den_nezavisimosti = new Intent(this, Kz_hol_den_nezavisimosti.class);
                startActivity(intent_den_nezavisimosti);
                bol_to_activate_den_nezavisimosti = true;
                break;
            default:
                break;
        }
    }
}