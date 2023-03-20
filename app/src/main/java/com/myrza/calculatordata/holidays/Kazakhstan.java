package com.myrza.calculatordata.holidays;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Kazakhstan {

    public static String new_year(String res_sec, String res_min, String res_hour, String res_day,
                                  String res_week, String res_month, String res_year,
                                  String res_do_ng_ostalos, String res_if_now,
                                  String monday, String tuesday, String wednesday,
                                  String thursday, String friday, String saturday,
                                  String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 1;
        int dest_month = 1;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 1 && getCurMonth == 1) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_ng_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    }

    public static String rojdestvo(String res_sec, String res_min, String res_hour, String res_day,
                                   String res_week, String res_month, String res_year,
                                   String res_do_rojdestva_ostalos, String res_if_now,
                                   String monday, String tuesday, String wednesday,
                                   String thursday, String friday, String saturday,
                                   String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 25;
        int dest_month = 12;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 25 && getCurMonth == 12) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_rojdestva_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    public static String march8(String res_sec, String res_min, String res_hour, String res_day,
                                String res_week, String res_month, String res_year,
                                String res_do_8march_ostalos, String res_if_now,
                                String monday, String tuesday, String wednesday,
                                String thursday, String friday, String saturday,
                                String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 8;
        int dest_month = 3;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 8 && getCurMonth == 3) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_8march_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    public static String nauryz(String res_sec, String res_min, String res_hour, String res_day,
                                String res_week, String res_month, String res_year,
                                String res_do_nauryz_ostalos, String res_if_now,
                                String monday, String tuesday, String wednesday,
                                String thursday, String friday, String saturday,
                                String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 21;
        int dest_month = 3;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if ((getCurDay == 21 && getCurMonth == 3) ||
                (getCurDay == 22 && getCurMonth == 3) ||
                (getCurDay == 23 && getCurMonth == 3)) {


            res_if_now += "\n" + "(21.03." + getCurYear + ") - (23.03." + getCurYear + ")";

            return res_if_now;
        }


        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_nauryz_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }


    public static String edinstvo_naroda_kz(String res_sec, String res_min, String res_hour, String res_day,
                                            String res_week, String res_month, String res_year,
                                            String res_do_edinstvo_naroda_kz_ostalos, String res_if_now,
                                            String monday, String tuesday, String wednesday,
                                            String thursday, String friday, String saturday,
                                            String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 1;
        int dest_month = 5;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 1 && getCurMonth == 5) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_edinstvo_naroda_kz_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    public static String den_zawitnika_ote4estva(String res_sec, String res_min, String res_hour,
                                                 String res_day, String res_week, String res_month,
                                                 String res_year,
                                                 String res_do_den_zawitnika_ote4stva_ostalos,
                                                 String res_if_now,
                                                 String monday, String tuesday, String wednesday,
                                                 String thursday, String friday, String saturday,
                                                 String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 23;
        int dest_month = 2;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 7 && getCurMonth == 5) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_den_zawitnika_ote4stva_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    public static String den_pobedy(String res_sec, String res_min, String res_hour, String res_day,
                                    String res_week, String res_month, String res_year,
                                    String res_do_den_pobedy_ostalos, String res_if_now,
                                    String monday, String tuesday, String wednesday,
                                    String thursday, String friday, String saturday,
                                    String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 9;
        int dest_month = 5;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 9 && getCurMonth == 5) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_den_pobedy_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }


    public static String den_stolicy(String res_sec, String res_min, String res_hour, String res_day,
                                     String res_week, String res_month, String res_year,
                                     String res_do_den_stolicy_ostalos, String res_if_now,
                                     String monday, String tuesday, String wednesday,
                                     String thursday, String friday, String saturday,
                                     String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 6;
        int dest_month = 7;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 6 && getCurMonth == 7) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_den_stolicy_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    ///даты могут меняться так что чекать...
    public static String kurban_ait(String res_sec, String res_min, String res_hour, String res_day,
                                    String res_week, String res_month, String res_year,
                                    String res_do_kurban_ait_ostalos, String res_if_now,
                                    String monday, String tuesday, String wednesday,
                                    String thursday, String friday, String saturday,
                                    String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 0;
        int dest_month = 0;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurYear == 2022) {
            dest_day = 9;
            dest_month = 7;
        } else if (getCurYear == 2023) {
            dest_day = 28;
            dest_month = 6;
        } else if (getCurYear == 2024) {
            dest_day = 16;
            dest_month = 6;
        } else if (getCurDay == 2025) {
            dest_day = 6;
            dest_month = 6;
        }

        if ((getCurDay == 9 && getCurMonth == 7 && getCurYear == 2022) ||
                (getCurDay == 10 && getCurMonth == 7 && getCurYear == 2022) ||
                (getCurDay == 11 && getCurMonth == 7 && getCurYear == 2022)) {

            res_if_now += "\n" + "(09.07." + getCurYear + ") - (11.07." + getCurYear + ")";
            return res_if_now;

        } else if ((getCurDay == 28 && getCurMonth == 6 && getCurYear == 2023) ||
                (getCurDay == 29 && getCurMonth == 6 && getCurYear == 2023) ||
                (getCurDay == 30 && getCurMonth == 6 && getCurYear == 2023) ||
                (getCurDay == 1 && getCurMonth == 7 && getCurYear == 2023)) {

            res_if_now += "\n" + "(28.06." + getCurYear + ") - (01.07." + getCurYear + ")";
            return res_if_now;

        } else if ((getCurDay == 16 && getCurMonth == 6 && getCurYear == 2024) ||
                (getCurDay == 17 && getCurMonth == 6 && getCurYear == 2024) ||
                (getCurDay == 18 && getCurMonth == 6 && getCurYear == 2024) ||
                (getCurDay == 19 && getCurMonth == 6 && getCurYear == 2024)) {

            res_if_now += "\n" + "(16.06." + getCurYear + ") - (19.06." + getCurYear + ")";
            return res_if_now;

        } else if ((getCurDay == 6 && getCurMonth == 6 && getCurYear == 2025) ||
                (getCurDay == 7 && getCurMonth == 6 && getCurYear == 2025) ||
                (getCurDay == 8 && getCurMonth == 6 && getCurYear == 2025) ||
                (getCurDay == 9 && getCurMonth == 6 && getCurYear == 2025)) {

            res_if_now += "\n" + "(06.06." + getCurYear + ") - (09.06." + getCurYear + ")";
            return res_if_now;

        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_kurban_ait_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }


    public static String den_konstitucii(String res_sec, String res_min, String res_hour, String res_day,
                                         String res_week, String res_month, String res_year,
                                         String res_do_den_konstitucii_ostalos, String res_if_now,
                                         String monday, String tuesday, String wednesday,
                                         String thursday, String friday, String saturday,
                                         String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 30;
        int dest_month = 8;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if (getCurDay == 30 && getCurMonth == 8) {
            return res_if_now;
        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_den_konstitucii_ostalos,
                monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }


    public static String den_nezavisimosti(String res_sec, String res_min, String res_hour, String res_day,
                                           String res_week, String res_month, String res_year,
                                           String res_do_den_nezavisimosti_ostalos, String res_if_now,
                                           String monday, String tuesday, String wednesday,
                                           String thursday, String friday, String saturday,
                                           String sunday) {

        String[] getCurrData = currentData().split(" ");

        int dest_day = 16;
        int dest_month = 12;

        int getCurYear = Integer.parseInt(getCurrData[2]);
        int getCurDay = Integer.parseInt(getCurrData[0]);
        int getCurMonth = Integer.parseInt(getCurrData[1]);

        if ((getCurDay == 16 && getCurMonth == 12) || (getCurDay == 17 && getCurMonth == 12)) {

            res_if_now += "\n" + "(16.12." + getCurYear + ") - (17.12." + getCurYear + ")";
            return res_if_now;

        }

        return calcData(currentData(), dest_day, dest_month, getCurYear, res_sec, res_min, res_hour,
                res_day, res_week, res_month, res_year, res_do_den_nezavisimosti_ostalos, monday,
                tuesday, wednesday, thursday, friday, saturday, sunday);

    }


    public static String calcData(String inputData1, int inputData2Day, int inputData2Month,
                                  int inputData2Year,
                                  String inputSec, String inputMin, String inputHour,
                                  String inputDay, String inputWeek, String inputMonth,
                                  String inputYear,
                                  String info,
                                  String monday, String tuesday, String wednesday,
                                  String thursday, String friday, String saturday,
                                  String sunday) {


        StringBuilder result = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d M yyyy H m");


        String checkData1 = inputData1;
        String checkData2 = inputData2Day + " " + inputData2Month + " " + inputData2Year + " " + "00 00";
        int getYear2input = inputData2Year;

        LocalDateTime checkStart = LocalDateTime.parse(checkData1, dateTimeFormatter);
        LocalDateTime checkEnd = LocalDateTime.parse(checkData2, dateTimeFormatter);

        int checkDates = checkStart.compareTo(checkEnd);

        if (checkDates == 0 || checkDates > 0) {
            getYear2input += 1;
        }

        String data1 = inputData1;
        String data2 = inputData2Day + " " + inputData2Month + " " + getYear2input + " " + "00 00";


        LocalDateTime start = LocalDateTime.parse(data1, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(data2, dateTimeFormatter);

        // если start раньше end то:  < 0
        // если start == end то:        0
        // если end раньше start то:  > 0

        Duration duration = Duration.between(start, end);
        long hour = ((duration.toDays() * 24) + (duration.toHours() % 24));
        long min = duration.toMinutes() % 60;
        long weeks = duration.toDays() / 7;
        long restDaysOfWeek = duration.toDays() % 7;
        long year = duration.toDays() / 365;

        String[] data1StrArr = data1.split(" ");
        String[] data2StrArr2 = data2.split(" ");

        int[] data1IntArr = new int[data1StrArr.length];
        int[] data2IntArr = new int[data2StrArr2.length];

        for (int i = 0; i < data1IntArr.length; i++) {
            data1IntArr[i] = Integer.parseInt(data1StrArr[i]);
        }
        for (int i = 0; i < data2IntArr.length; i++) {
            data2IntArr[i] = Integer.parseInt(data2StrArr2[i]);
        }

        int getYear1 = data1IntArr[2];
        int getYear2 = data2IntArr[2];
        int getMonth1 = data1IntArr[1];
        int getMonth2 = data2IntArr[1];
        int getDays1 = data1IntArr[0];
        int getDays2 = data2IntArr[0];

        long[] perData = periodData(getYear1, getMonth1, getDays1, getYear2, getMonth2, getDays2);

        result.append(info);
        result.append("\n");
        result.append("______________________________");
        result.append("\n\n");


        if (year > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
            result.append(hour).append(" ").append(inputHour).append(", ").append(min).append(" ").append(inputMin).append(".\n");

            result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ").append(duration.toHoursPart()).append(" ")
                    .append(inputHour).append(", ").append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");

            result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ").append(duration.toMinutesPart()).append(" ")
                    .append(inputMin).append(".\n");

            result.append(perData[3]).append(" ").append(inputMonth).append(", ").append(perData[2]).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ").append(duration.toMinutesPart())
                    .append(" ").append(inputMin).append(".\n");

            result.append(perData[0]).append(" ").append(inputYear).append(", ").append(perData[1]).append(" ")
                    .append(inputMonth).append(", ").append(perData[2]).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                    .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");


        } else if (year == 0 && perData[3] > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
            result.append(hour).append(" ").append(inputHour).append(", ").append(min).append(" ").append(inputMin).append(".\n");

            result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                    .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");

            result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek)
                    .append(" ").append(inputDay).append(", ").append(duration.toHoursPart())
                    .append(" ").append(inputHour).append(", ")
                    .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");

            result.append(perData[3]).append(" ").append(inputMonth).append(", ").append(perData[2])
                    .append(" ").append(inputDay).append(", ").append(duration.toHoursPart())
                    .append(" ").append(inputHour).append(", ").append(duration.toMinutesPart())
                    .append(" ").append(inputMin).append(".\n");
        } else if (year == 0 && perData[3] == 0 && weeks > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
            result.append(hour).append(" ").append(inputHour).append(", ")
                    .append(min).append(" ").append(inputMin).append(".\n");

            result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                    .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");

            result.append(weeks).append(" ").append(inputWeek).append(", ")
                    .append(restDaysOfWeek).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour)
                    .append(", ").append(duration.toMinutesPart()).append(" ")
                    .append(inputMin).append(".\n");

        } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
            result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                    .append(" ").append(inputMin).append(".\n");

            result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ")
                    .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                    .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n");
        } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
            result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                    .append(" ").append(inputMin).append(".\n");
        } else if (year == 0 && perData[1] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() == 0 && duration.toMinutes() > 0) {
            result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
            result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
        }

        result.append("\n");
        String moreInfo = addZeroForInfo(inputData2Day, inputData2Month) + getYear2input + ")" +
                " - " + returnNameDayOfWeekInStringFormat(returnDayOfWeek(getYear2input,
                        inputData2Month, inputData2Day), monday, tuesday, wednesday, thursday, friday,
                saturday, sunday);
        result.append(moreInfo);

        return result.toString();
    }

    public static long[] periodData(int year1, int month1, int days1, int year2, int month2, int days2) {
        LocalDate start = LocalDate.of(year1, month1, days1);
        LocalDate end = LocalDate.of(year2, month2, days2);
        Period period = Period.between(start, end);

        long year = period.getYears();
        long month = period.getMonths();
        long days = period.getDays();
        long totalMonths = period.toTotalMonths();

        return new long[]{year, month, days, totalMonths};
    }

    public static String currentData() {
        SimpleDateFormat format1 = new SimpleDateFormat("d M yyyy HH mm");
        Date getCurrentData = new Date(System.currentTimeMillis());
        return format1.format(getCurrentData);
    }

    public static String addZeroForInfo(int day, int month) {
        String dayS = Integer.toString(day);
        String monthS = Integer.toString(month);

        String resDay;
        String resMonth;

        if (dayS.length() == 1) {
            resDay = "0" + dayS;
        } else {
            resDay = dayS;
        }

        if (monthS.length() == 1) {
            resMonth = "0" + monthS;
        } else {
            resMonth = monthS;
        }

        return "(" + resDay + "." + resMonth + ".";
    }

    ///воскресенье это цифра 1, пн-2, вт-3 итд..
    public static String returnNameDayOfWeekInStringFormat(int week,
                                                           String Monday, String Tuesday, String Wednesday,
                                                           String Thursday, String Friday, String Saturday,
                                                           String Sunday) {

        if (week == 1) {
            return Sunday;
        } else if (week == 2) {
            return Monday;
        } else if (week == 3) {
            return Tuesday;
        } else if (week == 4) {
            return Wednesday;
        } else if (week == 5) {
            return Thursday;
        } else if (week == 6) {
            return Friday;
        } else if (week == 7) {
            return Saturday;
        } else return " ";

    }

    public static int returnDayOfWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


}
