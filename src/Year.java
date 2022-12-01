import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class Year {

    int number;

    ArrayList<YearRow> yearRows;

    public Year(int number, ArrayList<YearRow> yearRows) {
        this.number = number;
        this.yearRows = yearRows;
    }

    public static class YearRow {
        int month;
        int amount;
        boolean is_expense;

        public YearRow(int month, int amount, boolean is_expense) {
            this.month = month;
            this.amount = amount;
            this.is_expense = is_expense;
        }
    }

    public void printReport() {
        System.out.println("Год: " + number);
        System.out.println("Прибыль по каждому месяцу: ");
        printIncomes();
        System.out.println("Средний расход за все месяцы в году: " + getAverageExpense());
        System.out.println("Средний доход за все месяцы в году: " + getAverageIncome());
    }

    public void printIncomes() {
        HashMap<Integer, Integer> incomes = new HashMap<>();
        HashMap<Integer, Integer> outcomes = new HashMap<>();

        for (YearRow yearRow : yearRows) {
            if (yearRow.is_expense) {
                outcomes.put(yearRow.month, yearRow.amount);
            } else {
                incomes.put(yearRow.month, yearRow.amount);
            }
        }

        for (int key : incomes.keySet()) {
            System.out.println("За " + key + " месяц " + " прибыль составила: " + (incomes.get(key) - outcomes.get(key)));
        }
    }

    public double getAverageExpense() {
        double expensesSum = 0;
        int expensesCount = 0;
        double averageExpense = 0;
        for (YearRow yearRow : yearRows) {
            if (yearRow.is_expense) {
                expensesSum += yearRow.amount;
                expensesCount++;
            }
        }
        averageExpense = expensesSum / expensesCount;
        return round(averageExpense);
    }

    public double getAverageIncome() {
        double incomesSum = 0;
        int incomesCount = 0;
        double averageIncome = 0;

        for (YearRow yearRow : yearRows) {
            if (!yearRow.is_expense) {
                incomesSum += yearRow.amount;
                incomesCount++;
            }
        }
        averageIncome = incomesSum / incomesCount;
        return round(averageIncome);
    }

    static double round(double input) {
        return new BigDecimal(input)
                .setScale(2, RoundingMode.HALF_DOWN)
                .doubleValue();
    }
}
