package ru.job4j.ood.tdd.srp.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/*
   Класс создан для реализации XML-отчета
 */
@XmlRootElement(name = "employees")
public class Employees {
    public List<Employee> employee;

    public Employees(){}

    public Employees(List<Employee> employeeList) {
        this.employee = employeeList;
    }
}
