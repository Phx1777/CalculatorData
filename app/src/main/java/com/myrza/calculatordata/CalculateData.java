package com.myrza.calculatordata;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalculateData {

    public static String calcData(String inputData1, String inputData2,
                                  String inputSec, String inputMin, String inputHour,
                                  String inputDay, String inputWeek, String inputMonth,
                                  String inputYear, String exDate2Later,
                                  String exDatesEquals, String exDateFormat,
                                  String inputGoodbye, String interval) throws InterruptedException {

        StringBuilder result = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d M yyyy H m");
        String data1 = inputData1.replaceAll("[\\s|\\D]+", " ").trim();
        String data2 = inputData2.replaceAll("[\\s|\\D]+", " ").trim();

        boolean checkLengthToShowOnlyData = false;

        //7-10 только дата
        //12-16 дата и время
        if (data1.length() >= 7 && data1.length() <= 10 && data2.length() >= 7 && data2.length() <= 10) {
            data1 += " 00 00";
            data2 += " 00 00";
            checkLengthToShowOnlyData = true;
        }

        try {
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
                ////////


                result.append("\n");
                result.append("______________________________");
                result.append("\n");
                result.append(interval);
                result.append("\n");

                if (checkLengthToShowOnlyData) {

                    if (year > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek).append(" ").append(inputDay).append(".\n");
                        result.append(perData[3]).append(" ").append(inputMonth).append(", ").append(perData[2]).append(" ").append(inputDay).append(".\n");
                        result.append(perData[0]).append(" ").append(inputYear).append(", ").append(perData[1]).append(" ").append(inputMonth).append(", ")
                                .append(perData[2]).append(" ").append(inputDay).append(".\n").append("\n").append(inputGoodbye);

                    } else if (year == 0 && perData[3] > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek).append(inputDay).append(".\n");
                        result.append(perData[3]).append(" ").append(inputMonth).append(", ").append(perData[2]).append(" ").append(inputDay).append(".\n")
                                .append("\n").append(inputGoodbye);
                    } else if (year == 0 && perData[3] == 0 && weeks > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n");
                        result.append(weeks).append(" ").append(inputWeek).append(", ").append(restDaysOfWeek)
                                .append(" ").append(inputDay).append(".\n").append("\n").append(inputGoodbye);
                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(duration.toHours()).append(" ").append(inputHour).append(".\n");
                        result.append(duration.toDays()).append(" ").append(inputDay).append(".\n")
                                .append("\n").append(inputGoodbye);
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
                                .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n").append("\n").append(inputGoodbye);


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
                                .append(" ").append(inputMin).append(".\n").append("\n").append(inputGoodbye);
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
                                .append(inputMin).append(".\n").append("\n").append(inputGoodbye);

                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                                .append(" ").append(inputMin).append(".\n");

                        result.append(duration.toDaysPart()).append(" ").append(inputDay).append(", ")
                                .append(duration.toHoursPart()).append(" ").append(inputHour).append(", ")
                                .append(duration.toMinutesPart()).append(" ").append(inputMin).append(".\n")
                                .append("\n").append(inputGoodbye);
                    } else if (year == 0 && perData[3] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n");
                        result.append(hour).append(" ").append(inputHour).append(", ").append(min)
                                .append(" ").append(inputMin).append(".\n").append("\n").append(inputGoodbye);
                    } else if (year == 0 && perData[1] == 0 && weeks == 0 && duration.toDays() == 0 && duration.toHours() == 0 && duration.toMinutes() > 0) {
                        result.append(duration.toSeconds()).append(" ").append(inputSec).append(".\n");
                        result.append(duration.toMinutes()).append(" ").append(inputMin).append(".\n").append("\n").append(inputGoodbye);
                    }

                }
            } else if (compareData > 0) {
                result.append("__________________________________");
                result.append("\n");
                result.append(exDate2Later);
                return result.toString();
            } else if (compareData == 0) {
                result.append("__________________________________");
                result.append("\n");
                result.append(exDatesEquals);
                return result.toString();
            }

        } catch (DateTimeParseException e) {
            result.append("__________________________________");
            result.append("\n");
            result.append(exDateFormat);
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

}
