package br.com.emanuelvictor.domain.model.employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class EmployeeTests {

    @Test
    void mustCreateInstanceOfEmployee() {
        final String name = "Emanuel";
        final String email = "emanuel@email";
        final BigDecimal grossSalary = BigDecimal.valueOf(4000);
        final Function function = createDeveloperFunction();

        final Employee employee = new Employee(name, email, grossSalary, function);

        Assertions.assertThat(employee).isNotNull().satisfies(it -> {
            Assertions.assertThat(it.getName()).isEqualTo(name);
            Assertions.assertThat(it.getEmail()).isEqualTo(email);
            Assertions.assertThat(it.getGrossSalary()).isEqualTo(grossSalary);
            Assertions.assertThat(it.getFunction()).isEqualTo(function);
        });
    }

    @Test
    void mustCalculateSalaryToDeveloper() {
        final String name = "Emanuel";
        final String email = "emanuel@email";
        final BigDecimal grossSalary = BigDecimal.valueOf(5000);
        final BigDecimal salaryExpected = BigDecimal.valueOf(4000.0);
        final Function function = createDeveloperFunction();

        final Employee employee = new Employee(name, email, grossSalary, function);

        Assertions.assertThat(employee.getSalary()).isEqualTo(salaryExpected);
    }

    @Test
    void mustCalculateSalaryToManager() {
        final String name = "Emanuel";
        final String email = "emanuel@email";
        final BigDecimal grossSalary = BigDecimal.valueOf(2500);
        final BigDecimal salaryExpected = BigDecimal.valueOf(2000.0);
        final Function function = createManagerFunction();

        final Employee employee = new Employee(name, email, grossSalary, function);

        Assertions.assertThat(employee.getSalary()).isEqualTo(salaryExpected);
    }

    @Test
    void mustCalculateSalaryToTester() {
        final String name = "Emanuel";
        final String email = "emanuel@email";
        final BigDecimal grossSalary = BigDecimal.valueOf(550);
        final BigDecimal salaryExpected = BigDecimal.valueOf(46750, 2);
        final Function function = createTesterFunction();

        final Employee employee = new Employee(name, email, grossSalary, function);

        Assertions.assertThat(employee.getSalary()).isEqualTo(salaryExpected);
    }

    private Function createTesterFunction() {
        final String name = "TESTER";
        final BigDecimal averageSalary = BigDecimal.valueOf(2000);
        final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary = BigDecimal.valueOf(0.15);
        final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary = BigDecimal.valueOf(0.25);

        return new Function(name, averageSalary, percentageOfDiscountFromSalaryLessThanAverageSalary, percentageOfDiscountFromSalaryGreaterThanAverageSalary);
    }

    private Function createManagerFunction() {
        final String name = "MANAGER";
        final BigDecimal averageSalary = BigDecimal.valueOf(5000);
        final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary = BigDecimal.valueOf(0.20);
        final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary = BigDecimal.valueOf(0.30);

        return new Function(name, averageSalary, percentageOfDiscountFromSalaryLessThanAverageSalary, percentageOfDiscountFromSalaryGreaterThanAverageSalary);
    }

    private Function createDeveloperFunction() {
        final String name = "DEVELOPER";
        final BigDecimal averageSalary = BigDecimal.valueOf(3000);
        final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary = BigDecimal.valueOf(0.10);
        final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary = BigDecimal.valueOf(0.20);

        return new Function(name, averageSalary, percentageOfDiscountFromSalaryLessThanAverageSalary, percentageOfDiscountFromSalaryGreaterThanAverageSalary);
    }
}
