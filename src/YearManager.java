import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

public class YearManager {
    public Year year;

    public YearManager() {
    }

    public YearManager(String path) { // путь к одному файлу
        String content = readFileContentsOrNull(path); // считываем файл и получаем его весь в виде стринга
        String[] lines = content.split("\r?\n"); // упаковываем в массивы каждую строчку
        ArrayList<Year.YearRow> yearRows = new ArrayList<>();

        // получить номер года из имени файла
        int extensionStartIndex = path.lastIndexOf(".");
        int yearNumber = Integer.parseInt(path.substring(extensionStartIndex - 4, extensionStartIndex));

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // 01,1593150,false
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean is_expense = Boolean.parseBoolean(parts[2]);

            Year.YearRow yearRow = new Year.YearRow(month, amount, is_expense);
            yearRows.add(yearRow);
        }

        year = new Year(yearNumber, yearRows);
    }

    public String readFileContentsOrNull(String path) { // инструмент, который по пути вернёт текст содержимого
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public void printYearlyReport() {
            year.printReport();
    }
}
