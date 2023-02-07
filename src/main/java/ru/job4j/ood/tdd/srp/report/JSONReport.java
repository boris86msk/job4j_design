package ru.job4j.ood.tdd.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.ood.tdd.srp.model.Employee;
import ru.job4j.ood.tdd.srp.store.Store;

import java.util.function.Predicate;

public class JSONReport implements Report {
    private final Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var lib = new GsonBuilder().create();
        return lib.toJson(store.findBy(filter));
    }
}
