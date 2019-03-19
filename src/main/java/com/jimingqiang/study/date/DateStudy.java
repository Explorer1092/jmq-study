package com.jimingqiang.study.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/18 19:13
 * @Description:
 */
public class DateStudy {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println("今天的日期："+ now);

        LocalTime now1 = LocalTime.now().withNano(0);

        System.out.println("今天的时间："+ now1);

        LocalDateTime now2 = LocalDateTime.now();

        System.out.println("今天的日期和时间："+ now2);

        Instant now3 = Instant.now();
        System.out.println("时间戳："+ now3);


        LocalDate with = now.with(TemporalAdjusters.firstDayOfMonth());

        System.out.println("当前月份的第一天"+with);

        LocalDate tomorrow = now.plusDays(1);

        System.out.println("明天"+ tomorrow);

        LocalDate nextMonth = now.plusMonths(1);

        System.out.println("下个月"+ nextMonth);

        LocalDate nextWeek = now.plusWeeks(1);

        System.out.println("下一周"+nextWeek);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek.getValue());
        DayOfWeek monday = DayOfWeek.valueOf("SUNDAY");
        System.out.println(monday.getValue());


        LocalDate of = LocalDate.of(2019, 3, 18);

        System.out.println(of);


        System.out.println(LocalDate.of(2019,12,3).isAfter(LocalDate.of(2018,12,3)));

        LocalDate of1 = LocalDate.of(2019, 12, 3);
        LocalDate of2 = LocalDate.of(2019, 8, 3);

        Period period = Period.between(of1, of2);

        System.out.println(period.getDays());  //0
        System.out.println(period.getMonths()); //-4

        System.out.println(of1.until(of2, ChronoUnit.DAYS));

        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;
        System.out.println(LocalDate.parse("20181111",basicIsoDate));


        Instant now4 = Instant.now();

        System.out.println(Date.from(now4));


    }
}
