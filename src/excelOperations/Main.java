package excelOperations;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends dataVisualizer {

    public static ArrayList<gdpStructure> structure = new ArrayList<>();
    public static List<gdpStructure> dataToDisplay = new ArrayList<>();

    private static final String FILE = ".//datafiles//global_gdp.xlsx";


    //String name, int estimate, int year, String prop
    public static void dataStructure(String prop) throws IOException {
        try{
            FileInputStream fis = new FileInputStream(prop);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(1).getLastCellNum();

            for (int r = 1; r <= rows; r++) {
                XSSFRow row = sheet.getRow(r);
                gdpStructure gdp = new gdpStructure(null, null, 0.0,
                        0.0, 0.0, 0.0, 0.0, 0.0
                );
                for (int c = 0; c <= cols; c++) {
                    XSSFCell cell = row.getCell(c);
                    if (cell == null) {
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case STRING:
                            if (c == 0) {
                                gdp.setCountry(cell.getStringCellValue());
                            }
                            else if (c == 1) {
                                gdp.setRegion(cell.getStringCellValue());
                            }
                            break;
                        case NUMERIC:
                            int[] yr = {2019, 2020, 2021, 2022};
                            for (int j : yr) {
                                if (cell.getNumericCellValue() == j) {
                                    if(c==3){
                                        gdp.setIMFyear(cell.getNumericCellValue());
                                    }
                                    else if(c==5){
                                        gdp.setUNyear(cell.getNumericCellValue());
                                    }
                                    else if(c==7){
                                        gdp.setWByear(cell.getNumericCellValue());
                                    }
                                } else {
                                    if(c == 2) {
                                        gdp.setIMFestimate(cell.getNumericCellValue());
                                    }
                                    else if(c == 4) {
                                        gdp.setUNestimate(cell.getNumericCellValue());
                                    }
                                    else if(c == 6) {
                                        gdp.setWBestimate(cell.getNumericCellValue());
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }

                    structure.add(gdp);
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("\n\nError: " + e);
        }
    }

    public static void dataCleanse(){
        try{
            for(int i = 0; i < structure.size(); i++){
                for(int j = 0; j < structure.size(); j++){
                    if(i>0){
                        try{
                            if(structure.get(i).getCountry().equals(structure.get(i-1).getCountry())){
                                structure.remove(i);
                            }
                        } catch (Exception e){
                            continue;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println();
        }
    }

    public static void getDataToDisplay() {
        dataToDisplay.addAll(structure);
    }

    public static void readData(String prop) throws IOException {
        try{

            FileInputStream fis = new FileInputStream(prop);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Looping through sheet

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(1).getLastCellNum();

            for(int r=0; r<=rows; r++){
                XSSFRow row = sheet.getRow(r); //0
                for(int c=0; c<cols; c++){
                    XSSFCell cell = row.getCell(c);
                    switch (cell.getCellType()) {
                        case STRING -> System.out.print(cell.getStringCellValue() + " ");
                        case NUMERIC -> System.out.print(cell.getNumericCellValue() + " ");
                        case BOOLEAN -> System.out.print(cell.getBooleanCellValue() + " ");
                        case FORMULA -> System.out.print(cell.getCellFormula() + " ");
                    }
                    System.out.print(" | ");
                }
                System.out.println();
            }

            /*
            // Iterator method
            Iterator<Row> iterator= sheet.iterator();
            while(iterator.hasNext()){
               XSSFRow row =(XSSFRow) iterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                while(cellIterator.hasNext()){
                    XSSFCell cell = (XSSFCell) cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING -> System.out.print(cell.getStringCellValue() + " ");
                        case NUMERIC -> System.out.print(cell.getNumericCellValue() + " ");
                        case BOOLEAN -> System.out.print(cell.getBooleanCellValue() + " ");
                        case FORMULA -> System.out.print(cell.getCellFormula() + " ");
                    }
                    System.out.print(" | ");
                }
                System.out.println();
            }
            */

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("\n\nError: " + e);
        }
    }

    public static void readDataStructure(){
        for(gdpStructure g : structure){
            System.out.println(
                    g.getCountry() + ", " +
                            g.getRegion() + ", " + g.getIMFestimate() + ", "
                            + g.getUNestimate() + ", " + g.getWBestimate() + ", "
                            + g.getIMFyear() + ", " + g.getUNyear() + ", " +
                            g.getWByear() + "\n");
        }
        System.out.println("\n\n" + structure.size() + " countries found");
        System.out.println("\n\n" + dataToDisplay.size() + " countries to display");

        for (excelOperations.gdpStructure gdpStructure : dataToDisplay) {
            System.out.println("\n" + gdpStructure.getCountry());
        }
    }

    private static void appendDataToArray(Object[][] obj){
        for(int i = 0; i < obj.length; i++){
            for(int j = 1; j < obj[i].length; j++){
                for (excelOperations.gdpStructure gdpStructure : dataToDisplay) {
                    obj[i][j] = gdpStructure;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread dataManipulation = new Thread(() -> {
            try {
                dataStructure(FILE);
                dataCleanse();
                getDataToDisplay();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread dataDisplay = new Thread(() -> {
            try{
                transferAndConvert(dataToDisplay);
                CalcAverage();
                statisticalRatio();

                SwingUtilities.invokeLater(() -> {
                    try {
                        dataVisualizer.createAndShowGUI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new dataVisualizer();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        dataManipulation.start();
        dataManipulation.join();
        dataDisplay.start();
        dataDisplay.join();
    }
}
