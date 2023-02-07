package ru.job4j.ood.tdd.srp.report;

import ru.job4j.ood.tdd.srp.model.Employee;

import javax.xml.bind.JAXBException;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter) throws JAXBException;
}
