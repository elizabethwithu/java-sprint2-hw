import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearManager yearManager;

    // создали конструктор
    public Checker(MonthlyReport monthlyReport, YearManager yearManager) {
        this.monthlyReport = monthlyReport;
        this.yearManager = yearManager;
    }

    public void check() {
        HashMap<Integer, Integer> yearMonthlyIncomes = new HashMap<>(); // месяц/доход
        HashMap<Integer, Integer> yearMonthlyOutcomes = new HashMap<>(); // месяц/расход
        HashMap<Integer, Integer> yearMonthlyProfit = new HashMap<>();

        for (Year.YearRow yearRow : yearManager.year.yearRows) {
            if (yearRow.is_expense) {
                yearMonthlyOutcomes.put(yearRow.month, yearRow.amount);
            } else {
                yearMonthlyIncomes.put(yearRow.month, yearRow.amount);
            }
        }

        for (int key : yearMonthlyIncomes.keySet()) {
            yearMonthlyProfit.put(key, yearMonthlyIncomes.get(key) - yearMonthlyOutcomes.get(key));
        }

        HashMap<Integer, Integer> monthlyProfit = new HashMap<>();

        for (Month month : monthlyReport.months) {
            int profit = 0;
            for (Month.MonthRow monthRow : month.monthRows) {
                int amount = monthRow.quantity * monthRow.sum_of_one;
                if (monthRow.is_expense) {
                    profit -= amount;
                } else {
                    profit += amount;
                }
            }
            monthlyProfit.put(month.number, profit);
        }


        ArrayList<Integer> errors = new ArrayList<>();
        for (int yearMonthlyProfitKey : yearMonthlyProfit.keySet()) {
            int yearMonthlyProfitValue = yearMonthlyProfit.get(yearMonthlyProfitKey);
            int monthlyProfitValue = monthlyProfit.get(yearMonthlyProfitKey);

            if (yearMonthlyProfitValue != monthlyProfitValue) {
                errors.add(yearMonthlyProfitKey);
            }
        }

        if (errors.size() == 0) {
            System.out.println("Сверка данных успешно завершена. Ошибок не обнаружено.");
        } else {
            for (int errorMonth : errors) {
                System.out.println("Обнаружено несоответсвие годового и месячного отчета в " + errorMonth + " месяце");
            }
        }

    }
}
