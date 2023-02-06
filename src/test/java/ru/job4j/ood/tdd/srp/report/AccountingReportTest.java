package ru.job4j.ood.tdd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.srp.currency.Currency;
import ru.job4j.ood.tdd.srp.currency.CurrencyConverter;
import ru.job4j.ood.tdd.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.tdd.srp.formatter.DateTimeParser;
import ru.job4j.ood.tdd.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class AccountingReportTest {

    @Test
    public void wenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter convert = new InMemoryCurrencyConverter();
        Employee worker = new Employee("Boris", now, now, 150, Currency.RUB);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new AccountingReport(store, parser, convert, Currency.EUR);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary in EUR;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(convert.convert(worker.getCurrency(), worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}