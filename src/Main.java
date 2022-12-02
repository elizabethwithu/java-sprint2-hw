import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearlyReport yearlyReport = new YearlyReport();

        MonthlyReport monthlyReport = new MonthlyReport();

        Checker checker;

       Scanner scanner = new Scanner(System.in);
       printMenu();
       int command = scanner.nextInt();

       while (command != 0) {
           if (command == 1) {
               monthlyReport.loadFiles();

           } else if (command == 2) {
               yearlyReport = new YearlyReport( "resources/y.2021.csv");

           } else if (command == 3) {
               if (monthlyReport.months.size() > 0 && yearlyReport.year != null) {
                   checker = new Checker(monthlyReport, yearlyReport);
                   checker.check();
               } else {
                   System.out.println("Отчёты не найдены. Загрузите отчёты.");
               }

           } else if (command == 4) {
               if (monthlyReport.months.size() > 0) {
                   monthlyReport.printMonthlyReport();
               } else {
                   System.out.println("Месячные отчёты не найдены. Добавьте месячные отчёты.");
               }

           } else if (command == 5) {
               if (yearlyReport.year != null) {
                   yearlyReport.printYearlyReport();
               } else {
                   System.out.println("Годовой отчёт не найден. Добавьте годовой отчёт.");
               }

           } else {
               System.out.println("Введенной команды не существует");
           }
           printMenu();
           command = scanner.nextInt();
       }
        System.out.println("Программа завершена.");
    }

    private static void printMenu() {
        String menu = "Что вы хотите сделать?\n" +
                "1 - Считать все месячные отчёты\n" +
                "2 - Считать годовой отчёт\n" +
                "3 - Сверить отчёты\n" +
                "4 - Вывести информацию о всех месячных отчётах\n" +
                "5 - Вывести информацию о годовом отчёте\n" +
                "0 - Выйти из приложения";
        System.out.println(menu);
    }
}

