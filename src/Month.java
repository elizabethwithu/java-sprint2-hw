import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Month {

    int number;
    String name;

    public ArrayList<MonthRow> monthRows;

    public Month(int number, ArrayList<MonthRow> monthRows) {
        this.number = number;
        this.monthRows = monthRows;
        this.name = getNameByNumber(number);
    }

    public static class MonthRow {
        final String item_name;
        final boolean is_expense;
        final int quantity;
        final int sum_of_one;

        public MonthRow(String item_name, boolean is_expense, int quantity, int sum_of_one) {
            this.item_name = item_name;
            this.is_expense = is_expense;
            this.quantity = quantity;
            this.sum_of_one = sum_of_one;
        }
    }

    public static String getNameByNumber(int number) {
        if (Objects.equals(number, 1)) {
            return "Январь";
        } else if (Objects.equals(number, 2)) {
            return "Февраль";
        } else if (Objects.equals(number, 3)) {
            return "Март";
        } else {
            return null;
        }
    }

    public void printReport() {
        System.out.println("Название месяца: " + name);

        Map.Entry<String, Integer> topProfit = getTopProfit();
        Map.Entry<String, Integer> topExpense = getTopExpense();

        if (topProfit.getKey().equals("undefined") || topExpense.getKey().equals("undefined")) {
            System.out.println("Нет данных по месяцам");
        } else {
            System.out.println("Самый прибыльный товар/услуга: " + topProfit.getKey() + ", прибыль: " + topProfit.getValue());
            System.out.println("Самая большая трата: " + topExpense.getValue() + ", товар/услуга: " + topExpense.getKey());
        }
    }

    public Map.Entry<String, Integer> getTopProfit() {
        HashMap<String, Integer> profit = new HashMap<>();

        for (MonthRow monthRow : monthRows) {
            if (!monthRow.is_expense) {
                profit.put(monthRow.item_name, monthRow.sum_of_one * monthRow.quantity);
            }
        }

        Map.Entry<String, Integer> topProfit = new AbstractMap.SimpleEntry<>("undefined", 0);

        for (Map.Entry<String, Integer> entry : profit.entrySet()) {
            if (entry.getValue() > topProfit.getValue()) {
                topProfit = entry;
            }
        }
        return topProfit;
    }

    public Map.Entry<String, Integer> getTopExpense() {
        HashMap<String, Integer> expenses = new HashMap<>();

        for (MonthRow monthRow : monthRows) {
            if (monthRow.is_expense) {
                expenses.put(monthRow.item_name, monthRow.sum_of_one * monthRow.quantity);
            }
        }

        Map.Entry<String, Integer> topExpense = new AbstractMap.SimpleEntry<>("undefined", 0);

        for (Map.Entry<String, Integer> entry : expenses.entrySet()) {
            if (entry.getValue() > topExpense.getValue()) {
                topExpense = entry;
            }
        }
        return topExpense;
    }

}