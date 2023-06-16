package br.com.emanuelvictor.domain.model.employee;

import java.math.BigDecimal;

public enum Function {
    DEVELOPER(BigDecimal.valueOf(3000)),
    DBA(BigDecimal.valueOf(2000)),
    TESTER(BigDecimal.valueOf(2000)),
    MANAGER(BigDecimal.valueOf(5000));

    private BigDecimal averageSalary;

    Function(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }

    BigDecimal getAverageSalary() {
        return this.averageSalary;
    }

    BigDecimal calculateSalary(final BigDecimal salary) {
        if (this.equals(DEVELOPER)) {
            if (salary.compareTo(averageSalary) >= 1)
                return salary.multiply(BigDecimal.valueOf(0.20));
            else
                return salary.multiply(BigDecimal.valueOf(0.10));
        } else if (this.equals(DBA)) {
            if (salary.compareTo(averageSalary) >= 1)
                return salary.multiply(BigDecimal.valueOf(0.25));
            else
                return salary.multiply(BigDecimal.valueOf(0.15));
        } else if (this.equals(TESTER)) {
            if (salary.compareTo(averageSalary) >= 1)
                return salary.multiply(BigDecimal.valueOf(0.25));
            else
                return salary.multiply(BigDecimal.valueOf(0.15));
        } else {
            if (salary.compareTo(averageSalary) >= 1)
                return salary.multiply(BigDecimal.valueOf(0.30));
            else
                return salary.multiply(BigDecimal.valueOf(0.20));
        }
    }
}
