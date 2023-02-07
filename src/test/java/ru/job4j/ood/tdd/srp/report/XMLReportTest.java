package ru.job4j.ood.tdd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.srp.currency.Currency;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.model.Employees;
import ru.job4j.ood.tdd.srp.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {

    @Test
    public void whenXMLFormatGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("123", now, now, 100, Currency.RUB);
        Employee employee2 = new Employee("abc", now, now, 120, Currency.RUB);
        store.add(employee1);
        store.add(employee2);
        Report report = new XMLReport(store);
        List<Employee> list = List.of(employee1, employee2);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String expect = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(list), writer);
            expect = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

}