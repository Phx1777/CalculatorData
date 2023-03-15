package com.myrza.calculatordata;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CountingTime {


    public static String plusAndMinus(String inputData, int inputSec, int inputMin, int inputHour,
                                      int inputDay, int inputWeek, int inputMonth, int inputYear,
                                      boolean bol_checkBox_plus, boolean bol_checkBox_minus, boolean b_current_data,
                                      String sunday, String monday, String tuesday,
                                      String wednesday, String thursday, String friday,
                                      String saturday, String exception_format, String info1,
                                      String info2, String info3, String info4,
                                      String if_2_button_true, String if_2_button_false,
                                      String ex_if_date_not_exist) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String data;
        String resultData = null;
        String getDayOfWeek = null;
        String returnDayOfWeek;
        String result;

        LocalDateTime localDateTime;

        try {

            if (b_current_data) {
                data = currentDataWithTime();
            } else {
                data = inputData.replaceAll("[\\s|\\D]+", " ").trim();

                if (data.length() >= 8 && data.length() <= 10) {
                    data += " 00 00";
                }
            }


            String[] arrData = data.split(" ");

            int day = Integer.parseInt(arrData[0]);
            int month = Integer.parseInt(arrData[1]);
            int year = Integer.parseInt(arrData[2]);
            int hour = Integer.parseInt(arrData[3]);
            int min = Integer.parseInt(arrData[4]);

            try {
                localDateTime = LocalDateTime.of(year, month, day, hour, min);
            } catch (DateTimeException e) {
                return ex_if_date_not_exist;
            }

            if (bol_checkBox_minus && bol_checkBox_plus) {
                return if_2_button_true;
            }
            if (!bol_checkBox_minus && !bol_checkBox_plus) {
                return if_2_button_false;
            }


            if (bol_checkBox_minus) {

                resultData = localDateTime.
                        minus(inputSec, ChronoUnit.SECONDS).
                        minus(inputMin, ChronoUnit.MINUTES).
                        minus(inputHour, ChronoUnit.HOURS).
                        minus(inputDay, ChronoUnit.DAYS).
                        minus(inputWeek, ChronoUnit.WEEKS).
                        minus(inputMonth, ChronoUnit.MONTHS).
                        minus(inputYear, ChronoUnit.YEARS).format(dateTimeFormatter);

                //////to info day of week
                getDayOfWeek = String.valueOf(localDateTime.
                        minus(inputSec, ChronoUnit.SECONDS).
                        minus(inputMin, ChronoUnit.MINUTES).
                        minus(inputHour, ChronoUnit.HOURS).
                        minus(inputDay, ChronoUnit.DAYS).
                        minus(inputWeek, ChronoUnit.WEEKS).
                        minus(inputMonth, ChronoUnit.MONTHS).
                        minus(inputYear, ChronoUnit.YEARS).getDayOfWeek());

            } else if (bol_checkBox_plus) {

                resultData = localDateTime.
                        plus(inputSec, ChronoUnit.SECONDS).
                        plus(inputMin, ChronoUnit.MINUTES).
                        plus(inputHour, ChronoUnit.HOURS).
                        plus(inputDay, ChronoUnit.DAYS).
                        plus(inputWeek, ChronoUnit.WEEKS).
                        plus(inputMonth, ChronoUnit.MONTHS).
                        plus(inputYear, ChronoUnit.YEARS).format(dateTimeFormatter);

                //////to info day of week
                getDayOfWeek = String.valueOf(localDateTime.
                        plus(inputSec, ChronoUnit.SECONDS).
                        plus(inputMin, ChronoUnit.MINUTES).
                        plus(inputHour, ChronoUnit.HOURS).
                        plus(inputDay, ChronoUnit.DAYS).
                        plus(inputWeek, ChronoUnit.WEEKS).
                        plus(inputMonth, ChronoUnit.MONTHS).
                        plus(inputYear, ChronoUnit.YEARS).getDayOfWeek());

            }

            returnDayOfWeek = returnDayOfWeekMethod(getDayOfWeek, sunday, monday, tuesday,
                    wednesday, thursday, friday, saturday);

        } catch (Exception e) {
            return exception_format;
        }

        //return resultData + " - " + returnDayOfWeek;
        String[] getResult = resultData.replaceAll("[\\s|\\D]+", " ").split(" ");
        int resDay = Integer.parseInt(getResult[0]);
        int resMonth = Integer.parseInt(getResult[1]);
        int resYear = Integer.parseInt(getResult[2]);
        int resHour = Integer.parseInt(getResult[3]);
        int resMin = Integer.parseInt(getResult[4]);

        result = info1 + "\n" + info2 + " " + addZero(resMin, resHour, resDay, resMonth)[2] + "."
                + addZero(resMin, resHour, resDay, resMonth)[3] + "." + resYear +
                "\n" + info3 + " " + addZero(resMin, resHour, resDay, resMonth)[1] + ":"
                + addZero(resMin, resHour, resDay, resMonth)[0] + "\n" +
                info4 + " " + returnDayOfWeek;

        return result;
    }


    public static String currentDataWithTime() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd MM yyyy HH mm");
        Date getCurrentData = new Date(System.currentTimeMillis());
        return format1.format(getCurrentData);
    }


    public static String returnDayOfWeekMethod(String day, String sunday, String monday,
                                               String tuesday, String wednesday, String thursday,
                                               String friday, String saturday) {
        switch (day) {
            case "SUNDAY":
                return sunday;
            case "MONDAY":
                return monday;
            case "TUESDAY":
                return tuesday;
            case "WEDNESDAY":
                return wednesday;
            case "THURSDAY":
                return thursday;
            case "FRIDAY":
                return friday;
            case "SATURDAY":
                return saturday;
            default:
                return null;
        }
    }


    public static String[] addZero(int min, int hour, int day, int month) {
        String minn = Integer.toString(min);
        String hourr = Integer.toString(hour);
        String dayy = Integer.toString(day);
        String monthh = Integer.toString(month);

        String resMin;
        String resHour;
        String resDay;
        String resMonth;

        if (minn.length() == 1) {
            resMin = "0" + min;
        } else {
            resMin = minn;
        }

        if (hourr.length() == 1) {
            resHour = "0" + hour;
        } else {
            resHour = hourr;
        }

        if (dayy.length() == 1) {
            resDay = "0" + day;
        } else {
            resDay = dayy;
        }

        if (monthh.length() == 1) {
            resMonth = "0" + month;
        } else {
            resMonth = monthh;
        }

        return new String[]{resMin, resHour, resDay, resMonth};
    }


}
