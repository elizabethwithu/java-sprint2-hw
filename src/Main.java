import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearManager yearManager = new YearManager();

        MonthlyReport monthlyReport = new MonthlyReport();

        Checker checker;

       Scanner scanner = new Scanner(System.in);
       printMenu();
       int command = scanner.nextInt();

       while (command != 0) {
           if (command == 1) {
               monthlyReport.loadFiles();

           } else if (command == 2) {
               yearManager = new YearManager( "resources/y.2021.csv");

           } else if (command == 3) {
              checker = new Checker(monthlyReport, yearManager);
              checker.check();

           } else if (command == 4) {
               monthlyReport.printMonthlyReport();

           } else if (command == 5) {
            // Вывести информацию о годовом отчёте
               yearManager.printYearlyReport();


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

