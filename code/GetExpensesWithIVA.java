package code;

import java.util.List;

public class GetExpensesWithIVA {
    /**
     * Calculates the total expenses for a list of expenses, where each expense
     * is
     * less than 100 after applying the specified IVA (Value Added Tax) rate.
     *
     * @param expenses A list of ExpenseDto objects representing individual
     *                 expenses.
     * @param IVA      The IVA (Value Added Tax) rate to apply to each expense. It
     *                 should be provided as a decimal, e.g., 1.13 for 13% IVA.
     * @return The total of expenses that are less than 100 after applying IVA.
     */
    public Double getTotalExpensesWithIVA(List<Expense> expenses, Double IVA) {
        return expenses
                .stream()
                .filter(expense -> expense.getAmount() * IVA < 100)
                .mapToDouble(expense -> expense.getAmount() * IVA)
                .sum();
    }
}

class Expense {
    private String name;
    private Double amount;

    public Expense(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }
}
