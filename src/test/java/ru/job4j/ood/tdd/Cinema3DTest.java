package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class Cinema3DTest {

    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoAddSessionThenItNotExistsBetweenAllSession() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        List<Session> sessions = cinema.find(ses -> false);
        assertThat(sessions).doesNotContain(session);
    }

    @Test
    public void whenBuyOccupiedPlaceThenGetException() {
        Account account = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnThePastDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date1 = new GregorianCalendar(2023, Calendar.JANUARY,
                5, 18, 30);
        Calendar date2 = Calendar.getInstance();
        assertThat(date1.before(date2)).isTrue();
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date1)).
                isInstanceOf(IllegalArgumentException.class);
    }

}