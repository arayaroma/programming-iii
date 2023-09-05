package cr.ac.una.unaplanilla.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.SimpleStringProperty;

public class ExpenseDto {
   public SimpleStringProperty id;
   public SimpleStringProperty description;
   public Double amount;
   public SimpleStringProperty date;
   private Double IVA;

   public ExpenseDto() {
   }

   public ExpenseDto(String id, String description, Double amount, String date) {
      this.id = new SimpleStringProperty(id);
      this.description = new SimpleStringProperty(description);
      this.amount = amount;
      this.date = new SimpleStringProperty(date);
   }

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
   public Double getTotalExpensesWithIVA(List<ExpenseDto> expenses, Double IVA) {
      return expenses
            .stream()
            .filter(expense -> expense.getAmount() * IVA < 100)
            .mapToDouble(expense -> expense.getAmount() * IVA)
            .sum();
   }

   public SimpleStringProperty getId() {
      return this.id;
   }

   public void setId(SimpleStringProperty id) {
      this.id = id;
   }

   public SimpleStringProperty getDescription() {
      return this.description;
   }

   public void setDescription(SimpleStringProperty description) {
      this.description = description;
   }

   public Double getAmount() {
      return this.amount;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public SimpleStringProperty getDate() {
      return this.date;
   }

   public void setDate(SimpleStringProperty date) {
      this.date = date;
   }

   public Double getIVA() {
      return this.IVA;
   }

   public void setIVA(Double IVA) {
      this.IVA = IVA;
   }

}
