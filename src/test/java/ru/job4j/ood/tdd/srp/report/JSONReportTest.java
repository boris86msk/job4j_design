package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.Test;
import ru.job4j.ood.tdd.srp.currency.Currency;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenJSONFormatGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("user1", now, now, 90, Currency.RUB);
        Employee employee2 = new Employee("user2", now, now, 115, Currency.EUR);
        store.add(employee1);
        store.add(employee2);
        Report report = new JSONReport(store);
        var lib = new GsonBuilder().create();
        String expect = lib.toJson(store.findBy(em -> true));
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

}