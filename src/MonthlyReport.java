import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    public ArrayList<Month> months = new ArrayList<>();

    public void loadFiles() {
        loadFile( "resources/m.202101.csv");
        loadFile( "resources/m.202102.csv");
        loadFile( "resources/m.202103.csv");
    }
    private void loadFile(String path) { // путь для подгрузки определенного файла
        Month month;
        ArrayList<Month.MonthRow> monthRows = new ArrayList<>();

        // получить номер месяца из имени файла
        int extensionStartIndex = path.lastIndexOf(".");
        int monthNumber = Integer.parseInt(path.substring(extensionStartIndex - 2, extensionStartIndex));

        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String item_name = parts[0];
            boolean is_expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum_of_one = Integer.parseInt(parts[3]);

            Month.MonthRow monthRow = new Month.MonthRow(item_name, is_expense, quantity, sum_of_one);
            monthRows.add(monthRow);
        }
        month = new Month(monthNumber, monthRows);
        months.add(month);
    }

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public void printMonthlyReport() {
        for (Month month : months) {
            month.printReport();
        }
    }
}

