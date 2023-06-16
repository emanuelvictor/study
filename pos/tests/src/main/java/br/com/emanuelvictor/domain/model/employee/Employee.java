package br.com.emanuelvictor.domain.model.employee;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Employee {

    private final String name;
    private final String email;
    private final BigDecimal grossSalary;
    private final Function function;

    public Employee(String name, String email, BigDecimal grossSalary, Function function) {
        this.name = name;
        this.email = email;
        this.grossSalary = grossSalary;
        this.function = function;
    }

    BigDecimal getSalary() {
        return function.calculateSalary(grossSalary);
    }
}
