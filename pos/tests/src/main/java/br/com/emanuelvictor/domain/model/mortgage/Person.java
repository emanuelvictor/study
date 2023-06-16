package br.com.emanuelvictor.domain.model.mortgage;

import br.com.emanuelvictor.domain.exceptions.BusinessLogicException;

import java.math.BigDecimal;

public class Person {

    static final int MAX_AGE = 55;
    static final int MIN_AGE = 18;
    static final String NULL_AGE_ERROR_MESSAGE = "The age of person is required.";
    static final String MAX_AGE_ERROR_MESSAGE = "The person must be less than " + MAX_AGE + " years old.";
    static final String MIN_AGE_ERROR_MESSAGE = "The person must be over than " + MIN_AGE + " years old.";
    static final BigDecimal MAX_SALARY = BigDecimal.valueOf(10000);
    static final BigDecimal MIN_SALARY = BigDecimal.ZERO;
    static final String NULL_SALARY_ERROR_MESSAGE = "The salary of person is required.";
    static final String MAX_SALARY_ERROR_MESSAGE = "The salary of person must be less than " + MAX_SALARY + ".";
    static final String MIN_SALARY_ERROR_MESSAGE = "The salary of person must be over than " + MIN_SALARY + ".";
    static final String NULL_GENDER_ERROR_MESSAGE = "The gender of person is required ('true' for man 'false' for woman).";

    static final BigDecimal MORTGAGE_FACTOR_TO_YOUNG_MAN = BigDecimal.valueOf(75);
    static final BigDecimal MORTGAGE_FACTOR_TO_MEDIUM_MAN = BigDecimal.valueOf(55);
    static final BigDecimal MORTGAGE_FACTOR_TO_OLD_MAN = BigDecimal.valueOf(30);
    static final BigDecimal MORTGAGE_FACTOR_TO_YOUNG_WOMAN = BigDecimal.valueOf(70);
    static final BigDecimal MORTGAGE_FACTOR_TO_MEDIUM_WOMAN = BigDecimal.valueOf(50);
    static final BigDecimal MORTGAGE_FACTOR_TO_OLD_WOMAN = BigDecimal.valueOf(35);

    // TODO must be enum gender
    private final Boolean male;
    private final Integer age;
    private final BigDecimal salary;

    public Person(final Boolean male, final Integer age, final BigDecimal salary) {
        validateData(male, age, salary);
        this.male = male;
        this.age = age;
        this.salary = salary;
    }

    private void validateData(final Boolean male, final Integer age, final BigDecimal salary) {
        BusinessLogicException.create()
                .whenNull(age, NULL_AGE_ERROR_MESSAGE)
                .whenNull(salary, NULL_SALARY_ERROR_MESSAGE)
                .whenNull(male, NULL_GENDER_ERROR_MESSAGE)
                .thenThrows();
        BusinessLogicException.create()
                .whenTrue(age > MAX_AGE, MAX_AGE_ERROR_MESSAGE)
                .whenTrue(age < MIN_AGE, MIN_AGE_ERROR_MESSAGE)
                .whenTrue(salary.compareTo(MAX_SALARY) > 0, MAX_SALARY_ERROR_MESSAGE)
                .whenTrue(salary.compareTo(MIN_SALARY) < 0, MIN_SALARY_ERROR_MESSAGE)
                .thenThrows();
    }

    public boolean isMale() {
        return male;
    }

    public boolean isFemale() {
        return !male;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    BigDecimal getMortgageFactor() {
        if (isYoungMan())
            return MORTGAGE_FACTOR_TO_YOUNG_MAN;
        else if (isMediumMan())
            return MORTGAGE_FACTOR_TO_MEDIUM_MAN;
        else if (isOldMan())
            return MORTGAGE_FACTOR_TO_OLD_MAN;
        if (isYoungWoman())
            return MORTGAGE_FACTOR_TO_YOUNG_WOMAN;
        else if (isMediumWoman())
            return MORTGAGE_FACTOR_TO_MEDIUM_WOMAN;
        else if (isOldWoman())
            return MORTGAGE_FACTOR_TO_OLD_WOMAN;
        else
            throw BusinessLogicException.create("Mortgage is not allowed");
    }

    // TODO when gender be a enum, this calculates must will to enum.

    public boolean isYoungMan() {
        return isMale() && getAge() >= 18 && getAge() <= 35;
    }

    public boolean isMediumMan() {
        return isMale() && getAge() >= 36 && getAge() <= 45;
    }

    public boolean isOldMan() {
        return isMale() && getAge() >= 46 && getAge() <= 55;
    }

    public boolean isYoungWoman() {
        return isFemale() && getAge() >= 18 && getAge() <= 30;
    }

    public boolean isMediumWoman() {
        return isFemale() && getAge() >= 31 && getAge() <= 40;
    }

    public boolean isOldWoman() {
        return isFemale() && getAge() >= 41 && getAge() <= 50;
    }

    public BigDecimal calculateAllowedMortgage() {
        return getMortgageFactor().multiply(this.getSalary());
    }
}
