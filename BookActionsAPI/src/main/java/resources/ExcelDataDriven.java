package resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataDriven {
    public ArrayList<String> getTestData(String testCase) throws IOException {
        FileInputStream filepath = new FileInputStream("D:\\Automation_testing\\API_auto_testing\\BookActionsAPI\\TestData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(filepath);
        ArrayList<String> testValues = new ArrayList<>();
        int sheets = workbook.getNumberOfSheets();
        //System.out.println("Number of sheets in Excel file: " + sheets);

        for (int i = 0; i < sheets; i++) {
            //Определение необходимой книги (вкладки таблицы) с данными
            if (workbook.getSheetName(i).equalsIgnoreCase("BookApiTestData")) {
                XSSFSheet testSheet = workbook.getSheetAt(i);
                //определение первого (текущего) ряда в таблице с данными
                Iterator<Row> rows = testSheet.iterator();
                Row currentRow = rows.next();
                Iterator<Cell> cell = currentRow.cellIterator();

                int columnNumber = 0;
                //Перебор значений в ячейках текущего ряда и определение номера столбца с которого отсчитываются значения
                while (cell.hasNext() == true) {
                    Cell value = cell.next();
                    //System.out.println(value.getStringCellValue());
                    if (value.getStringCellValue().equalsIgnoreCase("Test cases")) {
                        columnNumber = columnNumber;
                        //System.out.println(columnNumber);
                        break;
                    }
                    columnNumber++;
                }
                System.out.println(columnNumber);

                //System.out.println("Row 1");
                //System.out.println(currentRow);
                //Поиск по горизонтали и получение значений из требуемой строки таблицы
                while (rows.hasNext() == true) {
                    currentRow = rows.next();
                    //System.out.println(currentRow);
                    if (currentRow.getCell(columnNumber).getStringCellValue().equalsIgnoreCase(testCase)) {
                        cell = currentRow.cellIterator();
                        while (cell.hasNext() == true) {
                            //Создание текущей ячейки и проверка её значения на тип данных (текстовый или числовой)
                            Cell cellValue = cell.next();
                            if (cellValue.getCellType() != CellType.STRING) {
                                testValues.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
                            } else testValues.add(cellValue.getStringCellValue());
                        }
                    }
                }
            }
        }
        return testValues;
    }
}
