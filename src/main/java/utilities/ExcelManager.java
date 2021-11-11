package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * A Class loads an Excel sheet and providing this sheet.
 */
public class ExcelManager {
    private static ExcelManager instance = null;
    private static final Set<String> loadedSheets = new HashSet<>();
    private final String filePath;
    private final String sheetName;
    private Sheet sheet;

    /**
     * Set the file name and sheet name values.
     * @param filePath The path of the Excel file with the file name.
     * @param sheetName The name of the Sheet.
     */
    private ExcelManager(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        loadExcelSheet();
    }

    /**
     * Ensure that this class will have only one instance per sheet.
     * @param filePath The name of the Excel file.
     * @param sheetName The name of the Sheet.
     * @return If the instance != null && this sheet is already loaded return the created instance,
     * otherwise create new instance.
     */
    public static ExcelManager getInstance(String filePath, String sheetName) {
        if (instance == null || !loadedSheets.contains(filePath + sheetName)) {
            loadedSheets.add(filePath + sheetName);
            instance = new ExcelManager(filePath, sheetName);
        }
        return instance;
    }

    /**
     * Loads a sheet object form the Excel file.
     * and set it's value to the sheet variable.
     */
    private void loadExcelSheet() {
        try (FileInputStream inputStream = new FileInputStream(
                filePath + ".xlsx");
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            MyLogger.severe(ExcelManager.class.getSimpleName(), "Can't read data from: "+ filePath
                    +" sheet: "+ sheetName);
            e.printStackTrace();
        }
    }

    /**
     * @return sheet variable.
     */
    public Sheet getSheet() {
        return sheet;
    }
}
