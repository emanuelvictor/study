package br.com.emanuelvictor.domain.model.employee;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Function {

    private final String name;
    private final BigDecimal averageSalary;
    private final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary;
    private final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary;

    public Function(final String name,
                    final BigDecimal averageSalary,
                    final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary,
                    final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary) {
        this.name = name;
        this.averageSalary = averageSalary;
        this.percentageOfDiscountFromSalaryLessThanAverageSalary = percentageOfDiscountFromSalaryLessThanAverageSalary;
        this.percentageOfDiscountFromSalaryGreaterThanAverageSalary = percentageOfDiscountFromSalaryGreaterThanAverageSalary;
    }

    BigDecimal calculateSalary(final BigDecimal salary) {
        final BigDecimal percentageOfDiscount = getPercentageOfDiscount(salary);
        return salary.subtract(salary.multiply(percentageOfDiscount));
    }

    BigDecimal getPercentageOfDiscount(final BigDecimal salary) {
        if (salaryIsGreaterOrEqualsToAverageSalary(salary))
            return percentageOfDiscountFromSalaryGreaterThanAverageSalary;
        return percentageOfDiscountFromSalaryLessThanAverageSalary;
    }

    boolean salaryIsGreaterOrEqualsToAverageSalary(final BigDecimal salary) {
        return (salary.compareTo(averageSalary) >= 0);
    }
}
