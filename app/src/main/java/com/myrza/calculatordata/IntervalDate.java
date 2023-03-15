package com.myrza.calculatordata;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class IntervalDate {

    public static String calcData(String inputData1, String inputData2,
                                  String inputSec, String inputMin, String inputHour,
                                  String inputDay, String inputWeek, String inputMonth,
                                  String inputYear, String exDate2Later,
                                  String exDatesEquals, String exDateFormat,
                                  String interval,
                                  boolean switch_button1, boolean switch_button2,
                                  String monday, String tuesday, String wednesday,
                                  String thursday, String friday, String saturday,
                                  String sunday,
                                  String exNotExistDate, String exEmptyDate)
            throws InterruptedException {

        StringBuilder result = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d M yyyy H m");
        String data1 = null;
        String data2 = null;

        String dopInfoData1;
        String dopInfoData2;


        //если пользователь нажал выбор текущей даты
        //тогда в дату1 присвоить текущ дату, или в дату2 в зависимости от boolean

        ///присвоить 2 текущ даты (useless)
        if (switch_button1 && switch_button2) {

            data1 = currentData();
            data2 = currentData();

            //присвоить в поле1 текущ дату
        } else if (switch_button1) {
            data1 = currentData();

            data2 = inputData2.replaceAll("[\\s|\\D]+", " ").trim();

            ///if this true, то значит ввел вторую дату без времени
            if (data2.length() >= 7 && data2.length() <= 10) {
                data2 += " 00 00";
            }

            ///присвоить в поле2 текущ дату
        } else if (switch_button2) {
            data2 = currentData();

            data1 = inputData1.replaceAll("[\\s|\\D]+", " ").trim();

            if (data1.length() >= 7 && data1.length() <= 10) {
                data1 += " 00 00";
            }

            ////иначе ввести 2 даты которые ввел пользователь, без текущей
        } else {
            data1 = inputData1.replaceAll("[\\s|\\D]+", " ").trim();
            data2 = inputData2.replaceAll("[\\s|\\D]+", " ").trim();
        }

        boolean checkLengthToShowOnlyData = false;

        //7-10 только дата
        //12-16 дата и время
        if (data1.length() >= 7 && data1.length() <= 10 && data2.length() >= 7 && data2.length() <= 10) {
            data1 += " 00 00";
            data2 += " 00 00";
            checkLengthToShowOnlyData = true;
        }

        try {


            ////проверить на сущ-ю дату
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
                e.printStackTrace();
                result.append("\n");
                result.append(exNotExistDate);
                return result.toString();
            }

            try {
                LocalDate localDate2 = LocalDate.of(data2_year, data2_month, data2_day);
            } catch (DateTimeException e) {
                e.printStackTrace();
                result.append("\n");
                result.append(exNotExistDate);
                return result.toString();
            }


            LocalDateTime start = LocalDateTime.parse(data1, dateTimeFormatter);
            LocalDateTime end = LocalDateTime.parse(data2, dateTimeFormatter);

            // если start раньше end то:  < 0
            // если start == end то:        0
            // если end раньше start то:  > 0
            int compareData = start.compareTo(end);

            if (compareData < 0) {
                Duration duration = Duration.between(start, end);
                long hour = ((duration.toDays() * 24) + (duration.toHours() % 24));
                long min = duration.toMinutes() % 60;
                long weeks = duration.toDays() / 7;
                long restDaysOfWeek = duration.toDays() % 7;
                long year = duration.toDays() / 365;

                //////
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

                String sForInfo1 = getDays1 + " " + getMonth1 + " " + getYear1;
                String sForInfo2 = getDays2 + " " + getMonth2 + " " + getYear2;

                dopInfoData1 = addZeroForInfo(getDays1, getMonth1) + getYear1 + ") - " +
                        returnNameDayOfWeekInStringFormat(dayOfData(sForInfo1), monday, tuesday,
                                wednesday, thursday, friday, saturday, sunday);

                dopInfoData2 = addZeroForInfo(getDays2, getMonth2) + getYear2 + ") - " +
                        returnNameDayOfWeekInStringFormat(dayOfData(sForInfo2), monday, tuesday,
                                wednesday, thursday, friday, saturday, sunday);

                result.append(interval);
                result.append("\n\n");

                if (checkLengthToShowOnlyData) {

                    if (year > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek).append(" ").append(inputDay).append(".\n");
                        result.append(perData[3]).append(" ").append(inputMonth).append(", ").append(perData[2]).append(" ").append(inputDay).append(".\n");
                        result.append(perData[0]).append(" ").append(inputYear).append(", ").append(perData[1]).append(" ").append(inputMonth).append(", ")
                                .append(perData[2]).append(" ").append(inputDay).append(".\n\n")
                                .append(dopInfoData1).append("\n").append(dopInfoData2);

                    } else if (year == 0 && perData[3] > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek).append(inputDay).append(".\n");
                        result.append(perData[3]).append(" ").append(inputMonth).append(", ")
                                .append(perData[2]).append(" ").append(inputDay).append(".\n\n")
                                .append(dopInfoData1).append("\n").append(dopInfoData2);
                    } else if (year == 0 && perData[3] == 0 && weeks > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek)
                                .append(" ").append(inputDay).append(".\n\n").append(dopInfoData1).append("\n").append(dopInfoData2);
                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay)
                                .append(".\n\n").append(dopInfoData1).append("\n").append(dopInfoData2);
                    }

                } else {

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
                                .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n\n")
                                .append(dopInfoData1).append("\n").append(dopInfoData2);


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
                                .append(" ").append(inputMin).append(".\n\n")
                                .append(dopInfoData1).append("\n").append(dopInfoData2);
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
                                .append(inputMin).append(".\n\n").append(dopInfoData1).append("\n").append(dopInfoData2);

                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                                .append(" ").append(inputMin).append(".\n");

                        result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ")
                                .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                                .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n")
                                .append("\n").append(dopInfoData1).append("\n").append(dopInfoData2);
                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                                .append(" ").append(inputMin).append(".\n").append("\n");
                    } else if (year == 0 && perData[1] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() == 0 && duration.toMinutes() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n").append("\n");
                    }

                }
            } else if (compareData > 0) {
                result.append("\n");
                result.append(exDate2Later);
                return result.toString();
            } else if (compareData == 0) {
                result.append("\n");
                result.append(exDatesEquals);
                return result.toString();
            }

        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            result.append("\n");
            result.append(exDateFormat);
            return result.toString();
        } catch (NumberFormatException e) {
            result.append("\n");
            result.append(exEmptyDate);
            return result.toString();
        }


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
        String currentDataFormat1 = format1.format(getCurrentData);
        return currentDataFormat1;
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

    public static int dayOfData(String data) {
        Calendar calendar = Calendar.getInstance();
        String[] toArr = data.split(" ");

        int day = Integer.parseInt(toArr[0]);
        int month = Integer.parseInt(toArr[1]);
        int year = Integer.parseInt(toArr[2]);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);

        return calendar.get(Calendar.DAY_OF_WEEK);
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

}
