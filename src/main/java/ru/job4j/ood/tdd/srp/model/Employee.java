package ru.job4j.ood.tdd.srp.model;

import ru.job4j.ood.tdd.srp.currency.Currency;

import java.util.Calendar;
import java.util.Objects;

public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;
    private Currency currency;

    public Employee(String name, Calendar hired, Calendar fired, double salary, Currency currency) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", salary=" + salary
                + '}';
    }
}
