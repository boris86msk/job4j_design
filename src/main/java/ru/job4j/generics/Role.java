package ru.job4j.generics;

public class Role extends Base {

    private final String rolename;

    private Integer salary;

    public Role(String id, String rolename, Integer salary) {
        super(id);
        this.rolename = rolename;
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getRolename() {
        return rolename;
    }
}
