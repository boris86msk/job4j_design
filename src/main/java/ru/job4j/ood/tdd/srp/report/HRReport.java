package ru.job4j.ood.tdd.srp.report;

import ru.job4j.ood.tdd.srp.formatter.DateTimeParser;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.report.Report;
import ru.job4j.ood.tdd.srp.store.EmployeeSort;
import ru.job4j.ood.tdd.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {
    private final Store store;

    public HRReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortList = store.findBy(filter);
        sortList.sort(new EmployeeSort());
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : sortList) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
