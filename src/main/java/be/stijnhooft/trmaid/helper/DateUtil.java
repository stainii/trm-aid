package be.stijnhooft.trmaid.helper;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

public class DateUtil {

    private Clock clock;

    public DateUtil() {
        this(Clock.systemDefaultZone());
    }

    public DateUtil(Clock clock) {
        this.clock = clock;
    }

    public Stream<LocalDate> getDaysOfMonthUntilAndIncludingToday() {
        LocalDate today = LocalDate.now(clock);
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        return firstDayOfMonth.datesUntil(today.plusDays(1));
    }

    public Stream<LocalDate> getWeekDaysOfMonthUntilAndIncludingToday() {
        return getDaysOfMonthUntilAndIncludingToday()
                .filter(this::isWeekDay);
    }

    public boolean isWeekDay(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                localDate.getDayOfWeek() != DayOfWeek.SUNDAY;
    }


}
