package code;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
    }

    List<Waiter> waiters;
    Predicate<Waiter> isNotTemporal = waiter -> !waiter.isTemporal();

    Predicate<Waiter> isOlderThan(Double age) {
        return waiter -> waiter.getAge() > age;
    }

    Predicate<Waiter> hasMinAmountTip(Double minTip) {
        return waiter -> waiter.getTip().equals(minTip);
    }

    public List<Waiter> getWaitersNoTemporalAndOlderThan(
            List<Waiter> waiters, Double age) {
        return waiters
                .stream()
                .filter(isNotTemporal
                        .and(isOlderThan(age)))
                .collect(Collectors.toList());
    }

    public void incrementBaseSalaryByTenthPercentWithMinTipAmount(
            List<Waiter> waiters, Double age, Double minTip) {
        waiters
                .stream()
                .filter(isNotTemporal
                        .and(isOlderThan(age))
                        .and(hasMinAmountTip(minTip)))
                .forEach(waiter -> waiter.setBaseSalary(waiter.getBaseSalary() * 1.1));
    }
}

public class Waiter {
    private Integer age;
    private Boolean temporal;
    private Double baseSalary;
    private Double tip;

    public Waiter() {
    }

    public Waiter(Integer age, Boolean temporal, Double baseSalary, Double tip) {
        this.age = age;
        this.temporal = temporal;
        this.baseSalary = baseSalary;
        this.tip = tip;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isIsTemporal() {
        return this.temporal;
    }

    public Boolean isTemporal() {
        return this.temporal;
    }

    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    public Double getBaseSalary() {
        return this.baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getTip() {
        return this.tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

}
