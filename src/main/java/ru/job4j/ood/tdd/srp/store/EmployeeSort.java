package ru.job4j.ood.tdd.srp.store;

import ru.job4j.ood.tdd.srp.model.Employee;

import java.util.Comparator;

public class EmployeeSort implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        int res;
        if (o1.getSalary() > o2.getSalary()) {
            res = -1;
        } else if (o1.getSalary() < o2.getSalary()) {
            res = 1;
        } else {
            res = 0;
        }
        return res;
    }

}
