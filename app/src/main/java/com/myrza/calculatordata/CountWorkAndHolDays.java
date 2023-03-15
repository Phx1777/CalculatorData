package com.myrza.calculatordata;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CountWorkAndHolDays {

    //gde true eto rabo4ie dni, false - vyhodnye...
    public static String calcWorkAndHolDaysMethod(String inputData1, String inputData2, boolean bol_pondelnik, boolean bol_vtornik,
                                                  boolean bol_sreda, boolean bol_4etverg, boolean bol_pyatnica,
                                                  boolean bol_subbota, boolean bol_voskresenie,
                                                  boolean switch_cur_data1,
                                                  boolean switch_cur_data2,
                                                  String return_ex_if_dates_equals,
                                                  String return_ex_data1_later_data2,
                                                  String return_ex_date_format_ex,
                                                  String rabo4ih_dnei,
                                                  String vyhodnyh_dnei,
                                                  String monday, String tuesday, String wednesday,
                                                  String thursday, String friday, String saturday,
                                                  String sunday, String ex_date_not_exist) {


        int countPonedelnik = 0;
        int countVtornik = 0;
        int countSreda = 0;
        int count4etverg = 0;
        int countPyatnica = 0;
        int countSubbota = 0;
        int countVoskresenie = 0;

        int countWorkDays = 0;
        int countHolidayDays = 0;

        boolean check = false;
        int plusData = 0;
        String data1;
        String data2;

        String s1;
        String s2;
        String s3;

        ArrayList<Integer> listNumbers;


        try {
            if (switch_cur_data1 && switch_cur_data2) {
                data1 = plusZeroForDataToCorrectFormat(currentData());
                data2 = plusZeroForDataToCorrectFormat(currentData());
            } else if (switch_cur_data2) {
                data2 = plusZeroForDataToCorrectFormat(currentData());
                data1 = plusZeroForDataToCorrectFormat(inputData1.replaceAll("[\\s|\\D]+", " ").trim());
            } else if (switch_cur_data1) {
                data1 = plusZeroForDataToCorrectFormat(currentData());
                data2 = plusZeroForDataToCorrectFormat(inputData2.replaceAll("[\\s|\\D]+", " ").trim());
            } else {
                data1 = plusZeroForDataToCorrectFormat(inputData1.replaceAll("[\\s|\\D]+", " ").trim());
                data2 = plusZeroForDataToCorrectFormat(inputData2.replaceAll("[\\s|\\D]+", " ").trim());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return return_ex_date_format_ex;
        }

        //////проверить на несущ-ю дату
        String[] getDate1 = data1.split(" ");
        String[] getDate2 = data2.split(" ");

        int data1_day = Integer.parseInt(getDate1[0]);
        int data1_month = Integer.parseInt(getDate1[1]);
        int data1_year = Integer.parseInt(getDate1[2]);

        int data2_day = Integer.parseInt(getDate2[0]);
        int data2_month = Integer.parseInt(getDate2[1]);
        int data2_year = Integer.parseInt(getDate2[2]);

        try {
            LocalDate localDate1 = LocalDate.of(data1_year, data1_month, data1_day);
        } catch (DateTimeException e) {
            return ex_date_not_exist;
        }

        try {
            LocalDate localDate2 = LocalDate.of(data2_year, data2_month, data2_day);
        } catch (DateTimeException e) {
            return ex_date_not_exist;
        }
        //////

        try {

            if (checkLaterOrNot(data1, data2)) {
                return return_ex_data1_later_data2;
            }

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return return_ex_date_format_ex;
        }

        if (data1.equals(data2)) {
            return return_ex_if_dates_equals;
        }

        ArrayList<String> listDates = new ArrayList<>();
        listNumbers = new ArrayList<>();
        String[] getData = data1.split(" ");

        int day = Integer.parseInt(getData[0]);
        int month = Integer.parseInt(getData[1]);
        int year = Integer.parseInt(getData[2]);


        try {

            ///делаю цикл пока от даты1 не дойду до даты2
            while (!check) {
                LocalDate localDate = LocalDate.of(year, month, day);
                String res = String.valueOf(localDate.plusDays(plusData));
                String[] retRes = res.split("-");
                String getRes = retRes[2] + " " + retRes[1] + " " + retRes[0];
                listDates.add(getRes);

                if (listDates.contains(data2)) {
                    check = true;
                }
                plusData++;
            }

            ///прохожусь по всем датам которые были добавлены в лист, потом определяю
            ///дни недели этих дат и добавляю в другой лист
            for (int i = 0; i < listDates.size(); i++) {
                listNumbers.add(getDayOfWeek(listDates.get(i)));
            }

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return return_ex_date_format_ex;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return return_ex_if_dates_equals;
        }


        ///беру лист с днями неделями в цифрах, и считаю сколько их
        for (Integer i : listNumbers) {
            if (i == 1) {
                countVoskresenie++;
            } else if (i == 2) {
                countPonedelnik++;
            } else if (i == 3) {
                countVtornik++;
            } else if (i == 4) {
                countSreda++;
            } else if (i == 5) {
                count4etverg++;
            } else if (i == 6) {
                countPyatnica++;
            } else if (i == 7) {
                countSubbota++;
            }
        }



        ///считаю какие комбинации выбрал пользователь, и выдаю результат

        ////7 kombinacii proiti

        ///////////////7 kombinacii start//////////////////

        if (bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) {

            countWorkDays = ((countPonedelnik + countVtornik + countSreda) + (count4etverg + countPyatnica + countSubbota + countVoskresenie));

            countHolidayDays = 0;

        } else if ((!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_4etverg && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {

            countWorkDays = 0;

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countPyatnica +
                    countSubbota + countVoskresenie);
        }

        ///////////////7 kombinacii end//////////////////


        ///////////////6 kombinacii start//////////////////

        //vt,sr,4t,pt,sub,voskr
        else if ((bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_pondelnik)) {

            countWorkDays = (countVtornik + countSreda + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik);
        }

        //pn,sr,4t,pt,sub,voskr
        else if ((bol_pondelnik && bol_sreda && bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_vtornik)) {

            countWorkDays = (countPonedelnik + countSreda + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik);
        }

        //pn,vt,4t,pt,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_sreda)) {

            countWorkDays = (countPonedelnik + countVtornik + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countSreda);
        }


        //pn,vt,sr,pt,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_4etverg)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (count4etverg);
        }


        //pn,vt,sr,4t,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_subbota && bol_voskresenie) && (!bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countSubbota + countVoskresenie);

            countHolidayDays = (countPyatnica);
        }


        //pn,vt,sr,4t,pt,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica && bol_voskresenie) && (!bol_subbota)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countPyatnica + countVoskresenie);

            countHolidayDays = (countSubbota);
        }

        //pn,vt,sr,4t,pt,sub
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica && bol_subbota) && (!bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countPyatnica + countSubbota);

            countHolidayDays = (countVoskresenie);
        }

        ///////////////6 kombinacii end//////////////////


        ///////////////2 kombinacii start//////////////////

        //pn & vt = true
        else if ((bol_pondelnik && bol_vtornik) && (!bol_sreda && !bol_4etverg && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik);

            countHolidayDays = (countSreda + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            //pn & sreda = true
        } else if ((bol_pondelnik && bol_sreda) && (!bol_vtornik && !bol_4etverg && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countSreda);

            countHolidayDays = (countVtornik + count4etverg + countPyatnica + countSubbota + countVoskresenie);
            //pn,chetverg true
        } else if ((bol_pondelnik && bol_4etverg) && (!bol_vtornik && !bol_sreda && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + count4etverg);

            countHolidayDays = (countVtornik + countSreda + countPyatnica + countSubbota + countVoskresenie);
        }
        //pn, pt true
        else if ((bol_pondelnik && bol_pyatnica) && (!bol_vtornik && !bol_sreda && !bol_4etverg && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countPyatnica);

            countHolidayDays = (countVtornik + countSreda + count4etverg + countSubbota + countVoskresenie);
        }
        //pn, sub true
        else if ((bol_pondelnik && bol_subbota) && (!bol_vtornik && !bol_sreda && !bol_4etverg && !bol_pyatnica && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countSubbota);

            countHolidayDays = (countVtornik + countSreda + count4etverg + countPyatnica + countVoskresenie);
        }
        //pn , voskr true
        else if ((bol_pondelnik && bol_voskresenie) && (!bol_vtornik && !bol_sreda && !bol_pyatnica && !bol_subbota && !bol_4etverg)) {
            countWorkDays = (countPonedelnik + countVoskresenie);

            countHolidayDays = (countVtornik + countSreda + count4etverg + countPyatnica + countSubbota);
        }

        //vt, sreda true
        else if ((bol_vtornik && bol_sreda) && (!bol_pondelnik && !bol_4etverg && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countSreda);

            countHolidayDays = (countPonedelnik + count4etverg + countPyatnica + countSubbota + countVoskresenie);
        }
        //vt, 4etverg true
        else if ((bol_vtornik && bol_4etverg) && (!bol_pondelnik && !bol_sreda && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + count4etverg);

            countHolidayDays = (countPonedelnik + countSreda + countPyatnica + countSubbota + countVoskresenie);
        }
        //vt, pt true
        else if ((bol_vtornik && bol_pyatnica) && (!bol_pondelnik && !bol_sreda && !bol_4etverg && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countPyatnica);

            countHolidayDays = (countPonedelnik + countSreda + count4etverg + countSubbota + countVoskresenie);
        }
        //vt, sub true
        else if ((bol_vtornik && bol_subbota) && (!bol_pondelnik && !bol_sreda && !bol_pyatnica && !bol_4etverg && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countSubbota);

            countHolidayDays = (countPonedelnik + countSreda + countPyatnica + count4etverg + countVoskresenie);
        }
        //vt, voskr
        else if ((bol_vtornik && bol_voskresenie) && (!bol_pondelnik && !bol_sreda && !bol_pyatnica && !bol_subbota && !bol_4etverg)) {
            countWorkDays = (countVtornik + countVoskresenie);

            countHolidayDays = (countPonedelnik + countSreda + countPyatnica + countSubbota + count4etverg);
        }
        //sr, 4etv true
        else if ((bol_sreda && bol_4etverg) && (!bol_pondelnik && !bol_vtornik && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countSreda + count4etverg);

            countHolidayDays = (countPonedelnik + countVtornik + countPyatnica + countSubbota + countVoskresenie);
        }
        //sr, pt true
        else if ((bol_sreda && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_4etverg && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countSreda + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg + countSubbota + countVoskresenie);
        }
        //sr,sub true
        else if ((bol_sreda && bol_subbota) && (!bol_pondelnik && !bol_vtornik && !bol_4etverg && !bol_pyatnica && !bol_voskresenie)) {
            countWorkDays = (countSreda + countSubbota);

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg + countPyatnica + countVoskresenie);
        }
        //sr,voskr true
        else if ((bol_sreda && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_4etverg && !bol_subbota && !bol_pyatnica)) {
            countWorkDays = (countSreda + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg + countSubbota + countPyatnica);
        }
        //4et,pt true
        else if ((bol_4etverg && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (count4etverg + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countSubbota + countVoskresenie);
        }
        //4et, sub true
        else if ((bol_4etverg && bol_subbota) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_pyatnica && !bol_voskresenie)) {
            countWorkDays = (count4etverg + countSubbota);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countPyatnica + countVoskresenie);
        }
        //4et, voskr true
        else if ((bol_4etverg && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_subbota && !bol_pyatnica)) {
            countWorkDays = (count4etverg + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countSubbota + countPyatnica);
        }
        //pt,sub true
        else if ((bol_pyatnica && bol_subbota) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_4etverg && !bol_voskresenie)) {
            countWorkDays = (countPyatnica + countSubbota);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countVoskresenie);
        }
        //pt, voskr true
        else if ((bol_pyatnica && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_4etverg && !bol_subbota)) {
            countWorkDays = (countPyatnica + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countSubbota);
        }
        //sub, voskr true
        else if ((bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_4etverg && !bol_pyatnica)) {
            countWorkDays = (countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countPyatnica);
        }

        ///////////////2 kombinacii end//////////////////


        ///////////////3 kombinacii start//////////////////

        //pn,vt,sr true
        else if ((bol_pondelnik && bol_vtornik && bol_sreda) && (!bol_4etverg && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik + countSreda);

            countHolidayDays = (count4etverg + countPyatnica + countSubbota + countVoskresenie);
        }
        //pn,vt,chetverg
        else if ((bol_pondelnik && bol_vtornik && bol_4etverg) && (!bol_sreda && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik + count4etverg);

            countHolidayDays = (countSreda + countPyatnica + countSubbota + countVoskresenie);
        }
        //pn,vt,pt
        else if ((bol_pondelnik && bol_vtornik && bol_pyatnica) && (!bol_sreda && !bol_4etverg && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik + countPyatnica);

            countHolidayDays = (countSreda + count4etverg + countSubbota + countVoskresenie);
        }
        //pn,vt,sub
        else if ((bol_pondelnik && bol_vtornik && bol_subbota) && (!bol_sreda && !bol_pyatnica && !bol_4etverg && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik + countSubbota);

            countHolidayDays = (countSreda + countPyatnica + count4etverg + countVoskresenie);
        }
        //pn,vt,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_voskresenie) && (!bol_sreda && !bol_pyatnica && !bol_subbota && !bol_4etverg)) {
            countWorkDays = (countPonedelnik + countVtornik + countVoskresenie);

            countHolidayDays = (countSreda + countPyatnica + countSubbota + count4etverg);
        }
        //vt,sr,4etv
        else if ((bol_vtornik && bol_sreda && bol_4etverg) && (!bol_pondelnik && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countSreda + count4etverg);

            countHolidayDays = (countPonedelnik + countPyatnica + countSubbota + countVoskresenie);
        }
        //vt,sr,pt
        else if ((bol_vtornik && bol_sreda && bol_pyatnica) && (!bol_pondelnik && !bol_4etverg && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countSreda + countPyatnica);

            countHolidayDays = (countPonedelnik + count4etverg + countSubbota + countVoskresenie);
        }
        //pn,sr,sub
        else if ((bol_pondelnik && bol_sreda && bol_subbota) && (!bol_vtornik && !bol_pyatnica && !bol_4etverg && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countSreda + countSubbota);

            countHolidayDays = (countVtornik + countPyatnica + count4etverg + countVoskresenie);
        }
        //vt,sr,sub
        else if ((bol_vtornik && bol_sreda && bol_subbota) && (!bol_pondelnik && !bol_4etverg && !bol_pyatnica && !bol_voskresenie)) {
            countWorkDays = (countVtornik + countSreda + countSubbota);

            countHolidayDays = (countPonedelnik + count4etverg + countPyatnica + countVoskresenie);
        }
        //vt,sr,voskr
        else if ((bol_vtornik && bol_sreda && bol_voskresenie) && (!bol_pondelnik && !bol_4etverg && !bol_subbota && !bol_pyatnica)) {
            countWorkDays = (countVtornik + countSreda + countVoskresenie);

            countHolidayDays = (countPonedelnik + count4etverg + countSubbota + countPyatnica);
        }
        //pn,sr,4et
        else if ((bol_pondelnik && bol_sreda && bol_4etverg) && (!bol_vtornik && !bol_pyatnica && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countSreda + count4etverg);

            countHolidayDays = (countVtornik + countPyatnica + countSubbota + countVoskresenie);
        }
        //sr,4t,pt
        else if ((bol_4etverg && bol_sreda && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (count4etverg + countSreda + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + countSubbota + countVoskresenie);
        }
        //sr,4t,sub
        else if ((bol_4etverg && bol_sreda && bol_subbota) && (!bol_pondelnik && !bol_vtornik && !bol_pyatnica && !bol_voskresenie)) {
            countWorkDays = (count4etverg + countSreda + countSubbota);

            countHolidayDays = (countPonedelnik + countVtornik + countPyatnica + countVoskresenie);
        }
        //sr,4t,voskr
        else if ((bol_4etverg && bol_sreda && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_subbota && !bol_pyatnica)) {
            countWorkDays = (count4etverg + countSreda + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSubbota + countVoskresenie);
        }
        //pn,4t,pt
        else if ((bol_pondelnik && bol_4etverg && bol_pyatnica) && (!bol_vtornik && !bol_sreda && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + count4etverg + countPyatnica);

            countHolidayDays = (countVtornik + countSreda + countSubbota + countVoskresenie);
        }
        //vt,4et,ptt
        else if ((bol_vtornik && bol_4etverg && bol_pyatnica) && (!bol_pondelnik && !bol_sreda && !bol_subbota && !bol_voskresenie)) {
            countWorkDays = (countVtornik + count4etverg + countPyatnica);

            countHolidayDays = (countPonedelnik + countSreda + countSubbota + countVoskresenie);
        }
        //4et,pt,sub
        else if ((bol_4etverg && bol_subbota && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_voskresenie)) {

            countWorkDays = (count4etverg + countSubbota + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countVoskresenie);
        }
        //4et,pt,voskr
        else if ((bol_4etverg && bol_voskresenie && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_subbota)) {

            countWorkDays = (count4etverg + countVoskresenie + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countSubbota);
        }
        //pn,pt,sub
        else if ((bol_pondelnik && bol_subbota && bol_pyatnica) && (!bol_4etverg && !bol_vtornik && !bol_sreda && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countSubbota + countPyatnica);

            countHolidayDays = (count4etverg + countVtornik + countSreda + countVoskresenie);
        }
        //vt,pt,sub
        else if ((bol_vtornik && bol_subbota && bol_pyatnica) && (!bol_pondelnik && !bol_4etverg && !bol_sreda && !bol_voskresenie)) {

            countWorkDays = (countVtornik + countSubbota + countPyatnica);

            countHolidayDays = (countPonedelnik + count4etverg + countSreda + countVoskresenie);
        }
        //sr,pt,sub
        else if ((bol_sreda && bol_subbota && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countSreda + countSubbota + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg + countVoskresenie);
        }
        //pt,sub,voskr
        else if ((bol_voskresenie && bol_subbota && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_4etverg)) {

            countWorkDays = (countVoskresenie + countSubbota + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + count4etverg);
        }
        //pn,sub,voskr
        else if ((bol_pondelnik && bol_subbota && bol_voskresenie) && (!bol_4etverg && !bol_vtornik && !bol_sreda && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countSubbota + countVoskresenie);

            countHolidayDays = (count4etverg + countVtornik + countSreda + countPyatnica);
        }
        //vt,sub,voskr
        else if ((bol_vtornik && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_4etverg && !bol_sreda && !bol_pyatnica)) {

            countWorkDays = (countVtornik + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + count4etverg + countSreda + countPyatnica);
        }
        //sr,sub,voskr
        else if ((bol_sreda && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_4etverg && !bol_pyatnica)) {

            countWorkDays = (countSreda + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg + countPyatnica);
        }
        //4et,sub,voskr
        else if ((bol_4etverg && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_sreda && !bol_pyatnica)) {

            countWorkDays = (count4etverg + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda + countPyatnica);
        }
        //pn,sr,pt
        else if ((bol_pondelnik && bol_sreda && bol_pyatnica) && (!bol_4etverg && !bol_vtornik && !bol_voskresenie && !bol_subbota)) {

            countWorkDays = (countPonedelnik + countSreda + countPyatnica);

            countHolidayDays = (count4etverg + countVtornik + countSubbota + countVoskresenie);
        }
        //pn,sr,voskr
        else if ((bol_pondelnik && bol_sreda && bol_voskresenie) && (!bol_4etverg && !bol_vtornik && !bol_subbota && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countSreda + countVoskresenie);

            countHolidayDays = (count4etverg + countVtornik + countSubbota + countPyatnica);
        }
        //sr,pt,voskr
        else if ((bol_voskresenie && bol_sreda && bol_pyatnica) && (!bol_4etverg && !bol_vtornik && !bol_pondelnik && !bol_subbota)) {

            countWorkDays = (countVoskresenie + countSreda + countPyatnica);

            countHolidayDays = (count4etverg + countVtornik + countPonedelnik + countSubbota);
        }
        //pn,4et,sub
        else if ((bol_pondelnik && bol_4etverg && bol_subbota) && (!bol_sreda && !bol_vtornik && !bol_voskresenie && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + count4etverg + countSubbota);

            countHolidayDays = (countSreda + countVtornik + countPyatnica + countVoskresenie);
        }
        //pn,4et,voskr
        else if ((bol_pondelnik && bol_4etverg && bol_voskresenie) && (!bol_pyatnica && !bol_vtornik && !bol_sreda && !bol_subbota)) {

            countWorkDays = (countPonedelnik + count4etverg + countVoskresenie);

            countHolidayDays = (countPyatnica + countVtornik + countSreda + countSubbota);
        }
        //pn,pt,voskr
        else if ((bol_pondelnik && bol_voskresenie && bol_pyatnica) && (!bol_4etverg && !bol_vtornik && !bol_subbota && !bol_sreda)) {

            countWorkDays = (countPonedelnik + countVoskresenie + countPyatnica);

            countHolidayDays = (count4etverg + countVtornik + countSubbota + countSreda);
        }
        //vt,4et,sub
        else if ((bol_vtornik && bol_4etverg && bol_subbota) && (!bol_pondelnik && !bol_sreda && !bol_voskresenie && !bol_pyatnica)) {

            countWorkDays = (countVtornik + count4etverg + countSubbota);

            countHolidayDays = (countPonedelnik + countSreda + countPyatnica + countVoskresenie);
        }
        //vt,pt,voskr
        else if ((bol_vtornik && bol_voskresenie && bol_pyatnica) && (!bol_4etverg && !bol_sreda && !bol_pondelnik && !bol_subbota)) {

            countWorkDays = (countVtornik + countVoskresenie + countPyatnica);

            countHolidayDays = (count4etverg + countSreda + countSubbota + countPonedelnik);
        }

        ///////////////3 kombinacii end//////////////////


        ///////////////4 kombinacii start//////////////////

        //pn,vt,sr,4et
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg) && (!bol_pyatnica && !bol_subbota && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg);

            countHolidayDays = (countPyatnica + countSubbota + countVoskresenie);
        }

        //pn,vt,sr,pt
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_pyatnica) && (!bol_4etverg && !bol_subbota && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countPyatnica);

            countHolidayDays = (count4etverg + countSubbota + countVoskresenie);
        }

        //pn,vt,sr,sub
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_subbota) && (!bol_pyatnica && !bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countSubbota);

            countHolidayDays = (countPyatnica + count4etverg + countVoskresenie);
        }

        //pn,vt,sr,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_voskresenie) && (!bol_pyatnica && !bol_subbota && !bol_4etverg)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countVoskresenie);

            countHolidayDays = (countPyatnica + countSubbota + count4etverg);
        }

        //vt,sr,4et,pt
        else if ((bol_pyatnica && bol_vtornik && bol_sreda && bol_4etverg) && (!bol_pondelnik && !bol_subbota && !bol_voskresenie)) {

            countWorkDays = (countPyatnica + countVtornik + countSreda + count4etverg);

            countHolidayDays = (countPonedelnik + countSubbota + countVoskresenie);
        }

        //vt,sr,4et,sub
        else if ((bol_subbota && bol_vtornik && bol_sreda && bol_4etverg) && (!bol_pyatnica && !bol_pondelnik && !bol_voskresenie)) {

            countWorkDays = (countSubbota + countVtornik + countSreda + count4etverg);

            countHolidayDays = (countPyatnica + countPonedelnik + countVoskresenie);
        }

        //vt,sr,4et,voskr
        else if ((bol_voskresenie && bol_vtornik && bol_sreda && bol_4etverg) && (!bol_pyatnica && !bol_subbota && !bol_pondelnik)) {

            countWorkDays = (countVoskresenie + countVtornik + countSreda + count4etverg);

            countHolidayDays = (countPyatnica + countSubbota + countPonedelnik);
        }

        //sr,4et,pt,sub
        else if ((bol_pyatnica && bol_subbota && bol_sreda && bol_4etverg) && (!bol_pondelnik && !bol_vtornik && !bol_voskresenie)) {

            countWorkDays = (countPyatnica + countSubbota + countSreda + count4etverg);

            countHolidayDays = (countPonedelnik + countVtornik + countVoskresenie);
        }

        //sr,4et,pt,voskr
        else if ((bol_sreda && bol_4etverg && bol_pyatnica && bol_voskresenie) && (!bol_pondelnik && !bol_subbota && !bol_vtornik)) {

            countWorkDays = (countPyatnica + countVoskresenie + countSreda + count4etverg);

            countHolidayDays = (countPonedelnik + countSubbota + countVtornik);
        }

        //4et,pt,sub,voskr
        else if ((bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_vtornik && !bol_sreda)) {

            countWorkDays = (count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countVtornik + countSreda);
        }

        //pn,vt,4et,pt
        else if ((bol_pondelnik && bol_vtornik && bol_pyatnica && bol_4etverg) && (!bol_sreda && !bol_subbota && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countPyatnica + count4etverg);

            countHolidayDays = (countSreda + countSubbota + countVoskresenie);
        }

        //pn,vt,pt,sub
        else if ((bol_pondelnik && bol_vtornik && bol_pyatnica && bol_subbota) && (!bol_sreda && !bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countPyatnica + countSubbota);

            countHolidayDays = (countSreda + count4etverg + countVoskresenie);
        }

        //pn,vt,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_subbota && bol_voskresenie) && (!bol_pyatnica && !bol_sreda && !bol_4etverg)) {

            countWorkDays = (countPonedelnik + countVtornik + countSubbota + countVoskresenie);

            countHolidayDays = (countPyatnica + countSreda + count4etverg);
        }

        //vt,sr,pt,sub
        else if ((bol_pyatnica && bol_vtornik && bol_sreda && bol_subbota) && (!bol_pondelnik && !bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countPyatnica + countVtornik + countSreda + countSubbota);

            countHolidayDays = (countPonedelnik + count4etverg + countVoskresenie);
        }

        //vt,sr,sub,voskr
        else if ((bol_voskresenie && bol_vtornik && bol_sreda && bol_subbota) && (!bol_pondelnik && !bol_4etverg && !bol_pyatnica)) {

            countWorkDays = (countVoskresenie + countVtornik + countSreda + countSubbota);

            countHolidayDays = (countPonedelnik + count4etverg + countPyatnica);
        }

        //pn,sr,pt,voskr
        else if ((bol_pondelnik && bol_pyatnica && bol_sreda && bol_voskresenie) && (!bol_vtornik && !bol_subbota && !bol_4etverg)) {

            countWorkDays = (countPonedelnik + countPyatnica + countSreda + countVoskresenie);

            countHolidayDays = (countVtornik + countSubbota + count4etverg);
        }

        //pn,sr,sub,voskr
        else if ((bol_pondelnik && bol_subbota && bol_sreda && bol_voskresenie) && (!bol_vtornik && !bol_4etverg && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countSubbota + countSreda + countVoskresenie);

            countHolidayDays = (countVtornik + count4etverg + countPyatnica);
        }

        //pn,4et,sub,voskr
        else if ((bol_pondelnik && bol_subbota && bol_voskresenie && bol_4etverg) && (!bol_pyatnica && !bol_vtornik && !bol_sreda)) {

            countWorkDays = (countPonedelnik + countSubbota + countVoskresenie + count4etverg);

            countHolidayDays = (countPyatnica + countVtornik + countSreda);
        }

        //pn,4t,pt,voskr
        else if ((bol_pondelnik && bol_4etverg && bol_pyatnica && bol_voskresenie) && (!bol_vtornik && !bol_subbota && !bol_sreda)) {

            countWorkDays = (countPonedelnik + countPyatnica + countVoskresenie + count4etverg);

            countHolidayDays = (countVtornik + countSubbota + countSreda);
        }

        //pn.pt.sub,voskr
        else if ((bol_pondelnik && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_vtornik && !bol_sreda && !bol_4etverg)) {

            countWorkDays = (countPonedelnik + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + countSreda + count4etverg);
        }

        //vt.pt.sub,voskr
        else if ((bol_vtornik && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_sreda && !bol_4etverg)) {

            countWorkDays = (countVtornik + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countSreda + count4etverg);
        }

        //sr,pt,sub,voskr
        else if ((bol_sreda && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_vtornik && !bol_pondelnik && !bol_4etverg)) {

            countWorkDays = (countSreda + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + countPonedelnik + count4etverg);
        }

        //pn,sr,4et,sub
        else if ((bol_sreda && bol_pondelnik && bol_subbota && bol_4etverg) && (!bol_vtornik && !bol_pyatnica && !bol_voskresenie)) {

            countWorkDays = (countSreda + countPonedelnik + countSubbota + count4etverg);

            countHolidayDays = (countVtornik + countPyatnica + countVoskresenie);
        }

        //pn,sr,4et,voskr
        else if ((bol_sreda && bol_pondelnik && bol_voskresenie && bol_4etverg) && (!bol_vtornik && !bol_pyatnica && !bol_subbota)) {

            countWorkDays = (countSreda + countPonedelnik + countVoskresenie + count4etverg);

            countHolidayDays = (countVtornik + countPyatnica + countSubbota);
        }

        //pn,4et,pt,sub
        else if ((bol_pyatnica && bol_pondelnik && bol_subbota && bol_4etverg) && (!bol_vtornik && !bol_sreda && !bol_voskresenie)) {

            countWorkDays = (countPyatnica + countPonedelnik + countSubbota + count4etverg);

            countHolidayDays = (countVtornik + countSreda + countVoskresenie);
        }

        //pn,sr,4et,pt
        else if ((bol_sreda && bol_pondelnik && bol_pyatnica && bol_4etverg) && (!bol_vtornik && !bol_voskresenie && !bol_subbota)) {

            countWorkDays = (countSreda + countPonedelnik + countPyatnica + count4etverg);

            countHolidayDays = (countVtornik + countVoskresenie + countSubbota);
        }

        //pn,sr,pt,sub
        else if ((bol_sreda && bol_pondelnik && bol_pyatnica && bol_subbota) && (!bol_vtornik && !bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countSreda + countPonedelnik + countPyatnica + countSubbota);

            countHolidayDays = (countVtornik + count4etverg + countVoskresenie);
        }
        //pn,vt,4t,sub
        else if ((bol_pondelnik && bol_vtornik && bol_4etverg && bol_subbota) && (!bol_sreda && !bol_pyatnica
                && !bol_voskresenie)) {
            countWorkDays = (countPonedelnik + countVtornik + count4etverg + countSubbota);

            countHolidayDays = (countSreda + countPyatnica + countVoskresenie);
        }
        //pn,vt,pt,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_pyatnica && bol_voskresenie) &&
                (!bol_sreda && !bol_4etverg && !bol_subbota)) {
            countWorkDays = (countPonedelnik + countVtornik + countPyatnica + countVoskresenie);

            countHolidayDays = (countSreda + count4etverg + countSubbota);
        }

        ///////////////4 kombinacii end//////////////////


        ///////////////5 kombinacii start//////////////////

        //pn,vt,sr,4et,pt
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica)
                && (!bol_subbota && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countPyatnica);

            countHolidayDays = (countSubbota + countVoskresenie);
        }

        //vt,sr,4et,pt,sub
        else if ((bol_subbota && bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica) && (!bol_pondelnik && !bol_voskresenie)) {

            countWorkDays = (countSubbota + countVtornik + countSreda + count4etverg + countPyatnica);

            countHolidayDays = (countPonedelnik + countVoskresenie);
        }

        //vt,sr,pt,sub,voskr
        else if ((bol_vtornik && bol_sreda && bol_pyatnica && bol_subbota && bol_voskresenie) &&
                (!bol_pondelnik && !bol_4etverg)) {
            countWorkDays = (countVtornik + countSreda + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + count4etverg);

        }

        //vt,sr,4t,pt,voskr
        else if ((bol_vtornik && bol_sreda && bol_4etverg && bol_pyatnica && bol_voskresenie) &&
                (!bol_pondelnik && !bol_subbota)) {
            countWorkDays = (countVtornik + countSreda + count4etverg + countPyatnica + countVoskresenie);

            countHolidayDays = (countPonedelnik + countSubbota);

        }

        //pn,vt,4t,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_4etverg && bol_subbota && bol_voskresenie) &&
                (!bol_sreda && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countVtornik + count4etverg + countSubbota + countVoskresenie);

            countHolidayDays = (countSreda + countPyatnica);
        }

        //sr,4et,pt,sub,voskr
        else if ((bol_subbota && bol_voskresenie && bol_sreda && bol_4etverg && bol_pyatnica) && (!bol_pondelnik && !bol_vtornik)) {

            countWorkDays = (countSubbota + countVoskresenie + countSreda + count4etverg + countPyatnica);

            countHolidayDays = (countPonedelnik + countVtornik);
        }

        //pn,vt,4t,pt,sub
        else if ((bol_pondelnik && bol_vtornik && bol_subbota && bol_4etverg && bol_pyatnica) && (!bol_sreda && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSubbota + count4etverg + countPyatnica);

            countHolidayDays = (countSreda + countVoskresenie);
        }

        //pn,vt,pt,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_subbota && bol_voskresenie && bol_pyatnica) && (!bol_sreda && !bol_4etverg)) {

            countWorkDays = (countPonedelnik + countVtornik + countSubbota + countVoskresenie + countPyatnica);

            countHolidayDays = (countSreda + count4etverg);
        }

        //pn,vt,sr,sub,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_subbota && bol_voskresenie) && (!bol_4etverg && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countSubbota + countVoskresenie);

            countHolidayDays = (count4etverg + countPyatnica);
        }

        //pn,vt,sr,pt,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_pyatnica && bol_voskresenie) && (!bol_4etverg && !bol_subbota)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countPyatnica + countVoskresenie);

            countHolidayDays = (count4etverg + countSubbota);
        }

        //pn,vt,sr,pt,sub
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_subbota && bol_pyatnica) && (!bol_4etverg && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countSubbota + countPyatnica);

            countHolidayDays = (count4etverg + countVoskresenie);
        }

        //pn,sr,4t,pt,voskr
        else if ((bol_pondelnik && bol_4etverg && bol_sreda && bol_pyatnica && bol_voskresenie) && (!bol_vtornik && !bol_subbota)) {

            countWorkDays = (countPonedelnik + count4etverg + countSreda + countPyatnica + countVoskresenie);

            countHolidayDays = (countVtornik + countSubbota);
        }

        //pn,sr,4t,pt,sub
        else if ((bol_pondelnik && bol_4etverg && bol_sreda && bol_subbota && bol_pyatnica) && (!bol_vtornik && !bol_voskresenie)) {

            countWorkDays = (countPonedelnik + count4etverg + countSreda + countSubbota + countPyatnica);

            countHolidayDays = (countVtornik + countVoskresenie);
        }

        //pn,sr,4t,sub,voskr
        else if ((bol_pondelnik && bol_4etverg && bol_sreda && bol_subbota && bol_voskresenie) && (!bol_vtornik && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + count4etverg + countSreda + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + countPyatnica);
        }

        //vt,sr,4t,sub,voskr
        else if ((bol_4etverg && bol_vtornik && bol_sreda && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_pyatnica)) {

            countWorkDays = (count4etverg + countVtornik + countSreda + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countPyatnica);
        }

        //pn,vt,sr,4t,voskr
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_4etverg && bol_voskresenie) && (!bol_subbota && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + count4etverg + countVoskresenie);

            countHolidayDays = (countSubbota + countPyatnica);
        }

        //pn,vt,sr,4t,sub
        else if ((bol_pondelnik && bol_vtornik && bol_sreda && bol_subbota && bol_4etverg) && (!bol_voskresenie && !bol_pyatnica)) {

            countWorkDays = (countPonedelnik + countVtornik + countSreda + countSubbota + count4etverg);

            countHolidayDays = (countVoskresenie + countPyatnica);
        }

        //vt,4t,pt,sub,voskr
        else if ((bol_pyatnica && bol_vtornik && bol_4etverg && bol_subbota && bol_voskresenie) && (!bol_pondelnik && !bol_sreda)) {

            countWorkDays = (countPyatnica + countVtornik + count4etverg + countSubbota + countVoskresenie);

            countHolidayDays = (countPonedelnik + countSreda);
        }

        //pn,4t,pt,sub,voskr
        else if ((bol_pondelnik && bol_4etverg && bol_pyatnica && bol_subbota && bol_voskresenie) && (!bol_vtornik && !bol_sreda)) {

            countWorkDays = (countPonedelnik + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + countSreda);

            //pn,vt,4t,pt,voskr
        } else if ((bol_pondelnik && bol_vtornik && bol_4etverg && bol_pyatnica && bol_voskresenie) && (!bol_subbota && !bol_sreda)) {

            countWorkDays = (countPonedelnik + count4etverg + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + countSreda);
        }

        //pn,sr,pt,sub,voskr
        else if ((bol_pondelnik && bol_sreda && bol_pyatnica && bol_subbota && bol_voskresenie)
                && (!bol_vtornik && !bol_4etverg)) {
            countWorkDays = (countPonedelnik + countSreda + countPyatnica + countSubbota + countVoskresenie);

            countHolidayDays = (countVtornik + count4etverg);
        }
        ///////////////5 kombinacii end//////////////////


        ///////////////1 kombinacii start//////////////////
        else if (bol_pondelnik) {
            countWorkDays = countPonedelnik;

            countHolidayDays = (countVtornik + countSreda + count4etverg
                    + countPyatnica + countSubbota + countVoskresenie);
        } else if (bol_vtornik) {
            countWorkDays = countVtornik;

            countHolidayDays = (countPonedelnik + countSreda
                    + count4etverg + countPyatnica + countSubbota + countVoskresenie);
        } else if (bol_sreda) {
            countWorkDays = countSreda;

            countHolidayDays = (countPonedelnik + countVtornik + count4etverg +
                    countPyatnica + countSubbota + countVoskresenie);
        } else if (bol_4etverg) {
            countWorkDays = count4etverg;

            countHolidayDays = (countPonedelnik + countVtornik + countSreda
                    + countPyatnica + countSubbota + countVoskresenie);
        } else if (bol_pyatnica) {
            countWorkDays = countPyatnica;

            countHolidayDays = (countPonedelnik + countVtornik + countSreda
                    + count4etverg + countSubbota + countVoskresenie);
        } else if (bol_subbota) {
            countWorkDays = countSubbota;

            countHolidayDays = (countPonedelnik + countVtornik + countSreda
                    + count4etverg + countPyatnica + countVoskresenie);
        } else if (bol_voskresenie) {
            countWorkDays = countVoskresenie;

            countHolidayDays = (countPonedelnik + countVtornik + countSreda
                    + count4etverg + countPyatnica + countSubbota);

        }

        ///////////////1 kombinacii end//////////////////

        ///////////////vse 7 kombinacii end//////////////////


        /////*********************
        s1 = rabo4ih_dnei + ": " + countWorkDays;
        s2 = vyhodnyh_dnei + ": " + countHolidayDays;

        String countPn = monday + ": " + countPonedelnik;
        String countVt = tuesday + ": " + countVtornik;
        String countSr = wednesday + ": " + countSreda;
        String count4t = thursday + ": " + count4etverg;
        String countPt = friday + ": " + countPyatnica;
        String countSub = saturday + ": " + countSubbota;
        String countVoskr = sunday + ": " + countVoskresenie;

        s3 = countPn + "\n" + countVt + "\n" + countSr + "\n" +
                count4t + "\n" + countPt + "\n" + countSub + "\n" + countVoskr;


        return s1 + "\n" + s2 + "\n\n" + s3;
    }


    public static String currentData() {
        SimpleDateFormat format1 = new SimpleDateFormat("d M yyyy");
        Date getCurrentData = new Date(System.currentTimeMillis());
        return format1.format(getCurrentData);
    }

    public static int getDayOfWeek(String data) {
        Calendar calendar = Calendar.getInstance();

        String[] getData = data.split(" ");

        int day = Integer.parseInt(getData[0]);
        int month = Integer.parseInt(getData[1]);
        int year = Integer.parseInt(getData[2]);

        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean checkLaterOrNot(String data1, String data2) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d M yyyy H m");

        data1 += " 00 00";
        data2 += " 00 00";

        LocalDateTime start = LocalDateTime.parse(data1, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(data2, dateTimeFormatter);

        // если start раньше end то:  < 0
        // если start == end то:        0
        // если end раньше start то:  > 0
        int compareData = start.compareTo(end);

        return compareData > 0;
    }

    public static String plusZeroForDataToCorrectFormat(String data) {
        String[] getData = data.split(" ");
        String day = getData[0];
        String month = getData[1];
        String year = getData[2];

        if (day.length() == 1) {
            day = "0" + day;
        }

        if (month.length() == 1) {
            month = "0" + month;
        }

        return day + " " + month + " " + year;
    }
}
