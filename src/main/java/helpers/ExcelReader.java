package helpers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import utilities.ExcelManager;
import utilities.MyLogger;

import java.util.*;

public class ExcelReader {
    private final Sheet sheet;
    private final int rowSize;
    private final int columnSize;
    private final Set<Integer> generatedIndexes = new HashSet<>();

    /**
     * Initialize an instance form ExcelManager class, and setting the data for sheet, rowSize, and columnSize.
     * @param fileName  The name of the Excel file.
     * @param sheetName The name of the Sheet.
     */
    public ExcelReader(String fileName, String sheetName) {
        sheet = ExcelManager.getInstance(Constants.TEST_RESOURCES_PATH + fileName, sheetName).getSheet();
        rowSize = sheet.getPhysicalNumberOfRows();
        columnSize = sheet.getRow(0).getLastCellNum();
    }

    /**
     * Using the Sheet object to load data in 2D Object.
     *
     * @return All data exists in the Excel sheet as 2D Object.
     */
    public Object[][] getData() {
        var data = new Object[rowSize - 1][columnSize];

        for (int rowIndex = 1; rowIndex < rowSize; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                data[rowIndex - 1][columnIndex] = currentRow.getCell(columnIndex).toString();
            }
        }
        return data;
    }

    /**
     * Using the sheet object to loads data for a row as Map of String key "the headers of the columns in
     * the Excel sheet" and Object value.
     *
     * @param rowIndex The index of the wanted row.
     * @return A Map with that row "headers as key and data as value".
     */
    public Map<String, Object> getRow(int rowIndex) {
        if (rowIndex >= rowSize) {
            MyLogger.severe(this.getClass().getSimpleName(), "Invalid Row Index: " + rowIndex);
        }
        var data = new HashMap<String, Object>();
        var headerRow = sheet.getRow(0);

        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            data.put(headerRow.getCell(columnIndex).toString(), sheet.getRow(rowIndex).getCell(columnIndex).toString());
        }
        return data;
    }

    /**
     * A wrapper method of gerRow method to provide only the first row.
     *
     * @return The data that exist in the first row in the sheet as a Map of headers as key and data as value.
     */
    public Map<String, Object> getFirstRow() {
        return getRow(1);
    }

    /**
     * A wrapper method of gerRow method to provide a random row.
     *
     * @return The data that exist in a random row in the sheet as a Map of headers as key and data as value.
     */
    public Map<String, Object> getRandomRow() {
        return getRow(generateRandomIndex());
    }

    /**
     * Generates a random number and ensure that this number is Unique.
     * if the all available number has been generated before the counter will be reset.
     *
     * @return A random number from 1 to rowSize - 1
     */
    private int generateRandomIndex() {
        var limit = rowSize - 1;
        var randomIndex = new Random().nextInt(limit) + 1;

        var isAdded = generatedIndexes.add(randomIndex);

        if (isAdded) {
            return randomIndex;
        } else {
            if (generatedIndexes.size() == limit) {
                MyLogger.warning(this.getClass().getSimpleName(),
                        "The all available indexes are generated before," +
                                " so the list will be clear and start from the beginning.");
                generatedIndexes.clear();
            }
            return generateRandomIndex();
        }
    }
}
