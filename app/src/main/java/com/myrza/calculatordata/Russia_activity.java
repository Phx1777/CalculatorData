package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_den_narodnogo_edinstva;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_den_pobedy;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_den_rossii;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_den_zawit_ote4;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_march8;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_maslenica;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_new_year;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_pasha;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_prazdnik_vesny_i_truda;
import com.myrza.calculatordata.ru_holl_all.Ru_hol_rojdestvo;

public class Russia_activity extends AppCompatActivity implements View.OnClickListener {

    public static boolean bol_to_activate_new_year;
    public static boolean bol_to_activate_rojdestvo;
    public static boolean bol_to_activate_march8;
    public static boolean bol_to_activate_den_zawit_ote4;
    public static boolean bol_to_activate_den_pobedy;
    public static boolean bol_to_activate_maslenica;
    public static boolean bol_to_activate_pasha;
    public static boolean bol_to_activate_prazd_vesny_i_truda;
    public static boolean bol_to_activate_den_rosii;
    public static boolean bol_to_activate_den_narod_edinstva_ru;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russia);

        getSupportActionBar().setTitle(getResources().getString(R.string.russia));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_to_main2:
                Intent intent_main = new Intent(this, MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.b_back_to_holidays_activity_ruu:
                Intent intent_holidays = new Intent(this, Holidays.class);
                startActivity(intent_holidays);
                break;
            case R.id.new_year_ru:
                Intent new_year = new Intent(this, Ru_hol_new_year.class);
                startActivity(new_year);
                bol_to_activate_new_year = true;
                break;
            case R.id.rojdestvo_ru:
                Intent rojdestvo = new Intent(this, Ru_hol_rojdestvo.class);
                startActivity(rojdestvo);
                bol_to_activate_rojdestvo = true;
                break;
            case R.id.den_zawitnika_ote4estva_ru:
                Intent zawit_ote4estva = new Intent(this, Ru_hol_den_zawit_ote4.class);
                startActivity(zawit_ote4estva);
                bol_to_activate_den_zawit_ote4 = true;
                break;
            case R.id.maslenica_ru:
                Intent maslenica = new Intent(this, Ru_hol_maslenica.class);
                startActivity(maslenica);
                bol_to_activate_maslenica = true;
                break;
            case R.id.march8_ru:
                Intent march8 = new Intent(this, Ru_hol_march8.class);
                startActivity(march8);
                bol_to_activate_march8 = true;
                break;
            case R.id.pasha_ru:
                Intent pasha = new Intent(this, Ru_hol_pasha.class);
                startActivity(pasha);
                bol_to_activate_pasha = true;
                break;
            case R.id.prazdink_vesny_i_truda_ru:
                Intent vesny_i_truda = new Intent(this, Ru_hol_prazdnik_vesny_i_truda.class);
                startActivity(vesny_i_truda);
                bol_to_activate_prazd_vesny_i_truda = true;
                break;
            case R.id.den_pobedy_ru:
                Intent den_pobedy = new Intent(this, Ru_hol_den_pobedy.class);
                startActivity(den_pobedy);
                bol_to_activate_den_pobedy = true;
                break;
            case R.id.den_rosii_ru:
                Intent den_rossii = new Intent(this, Ru_hol_den_rossii.class);
                startActivity(den_rossii);
                bol_to_activate_den_rosii = true;
                break;
            case R.id.den_narodnogo_edinstva:
                Intent den_narod_edinstva = new Intent(this, Ru_hol_den_narodnogo_edinstva.class);
                startActivity(den_narod_edinstva);
                bol_to_activate_den_narod_edinstva_ru = true;
                break;
            default:
                break;
        }
    }
}