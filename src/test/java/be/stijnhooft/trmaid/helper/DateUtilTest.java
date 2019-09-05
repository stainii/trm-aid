package be.stijnhooft.trmaid.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    private DateUtil dateUtil;
    private LocalDate fixedDateTime;

    @BeforeEach
    void init() {
        this.fixedDateTime = LocalDate.of(2019, Month.OCTOBER, 11);
        this.dateUtil = new DateUtil(Clock.fixed(fixedDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()));
    }

    @DisplayName("Get days of month until, and including, today")
    @Test
    void getDaysOfMonthUntilAndIncludingToday() {
        List<LocalDate> result = dateUtil.getDaysOfMonthUntilAndIncludingToday()
                .collect(Collectors.toList());


        for (int i = 1; i <= 11; i++) {
            assertEquals(i, result.get(i - 1).getDayOfMonth());
            assertEquals(Month.OCTOBER, result.get(i - 1).getMonth());
            assertEquals(2019, result.get(i - 1).getYear());
        }

        assertEquals(11, result.size());
    }

    @DisplayName("Get week days of month until, and including, today")
    @Test
    void getWeekDaysOfMonthUntilAndIncludingToday() {
        List<LocalDate> result = dateUtil.getWeekDaysOfMonthUntilAndIncludingToday()
                .collect(Collectors.toList());

        assertEquals(9, result.size());

        assertEquals(1, result.get(0).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(0).getMonth());
        assertEquals(2019, result.get(0).getYear());

        assertEquals(2, result.get(1).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(1).getMonth());
        assertEquals(2019, result.get(1).getYear());

        assertEquals(3, result.get(2).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(2).getMonth());
        assertEquals(2019, result.get(2).getYear());

        assertEquals(4, result.get(3).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(3).getMonth());
        assertEquals(2019, result.get(3).getYear());

        assertEquals(7, result.get(4).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(4).getMonth());
        assertEquals(2019, result.get(4).getYear());

        assertEquals(8, result.get(5).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(5).getMonth());
        assertEquals(2019, result.get(5).getYear());

        assertEquals(9, result.get(6).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(6).getMonth());
        assertEquals(2019, result.get(6).getYear());

        assertEquals(10, result.get(7).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(7).getMonth());
        assertEquals(2019, result.get(7).getYear());

        assertEquals(11, result.get(8).getDayOfMonth());
        assertEquals(Month.OCTOBER, result.get(8).getMonth());
        assertEquals(2019, result.get(8).getYear());
    }

    @DisplayName("Is week day? On a Saturday -> false")
    @Test
    void isWeekDayWhenSaturday() {
        assertFalse(dateUtil.isWeekDay(LocalDate.of(2019, Month.SEPTEMBER, 14)));
    }

    @DisplayName("Is week day? On a Sunday -> false")
    @Test
    void isWeekDayWhenSunday() {
        assertFalse(dateUtil.isWeekDay(LocalDate.of(2019, Month.SEPTEMBER, 15)));
    }

    @DisplayName("Is week day? On a Wednesday -> false")
    @Test
    void isWeekDayWhenWednesday() {
        assertTrue(dateUtil.isWeekDay(LocalDate.of(2019, Month.SEPTEMBER, 4)));
    }


}
