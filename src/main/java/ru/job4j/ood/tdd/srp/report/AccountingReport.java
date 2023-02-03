package ru.job4j.ood.tdd.srp.report;

import ru.job4j.ood.tdd.srp.currency.Currency;
import ru.job4j.ood.tdd.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.tdd.srp.formatter.DateTimeParser;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    private final Currency convertCurr;

    public AccountingReport(Store store, DateTimeParser<Calendar> dateTimeParser, Currency convertCurr) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.convertCurr = convertCurr;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        InMemoryCurrencyConverter convert = new InMemoryCurrencyConverter();
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; Salary in %s;", convertCurr))
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(convert.convert(employee.getCurrency(), employee.getSalary(), convertCurr))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
