package net.electrifai.library.utils.excelsheet;


import net.electrifai.library.utils.PropertiesFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class ReadAndWriteExcel
{

    public static void readExcel()
    {
        try {

            FileInputStream file = new FileInputStream(new File(PropertiesFile.getProperty("testEnvironment.properties").getString("testDataPath")));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row>rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() );
                            break;
                        case STRING:
                            System.out.println(cell.getStringCellValue() );
                            break;
                        case BLANK:
                            System.out.println("Cell has blank");
                            break;
                        case FORMULA:
                            System.out.println(cell.getCellFormula());
                            break;
                        default:   System.out.print("could not get cell type");
                    }
                }
                System.out.println("");
            }
            file.close();

        }catch(Exception e)
        {e.printStackTrace();}

    }


    public static List<List<Object>>  readExcelTab(String excelFilePath,String tabName, String suiteType)
    {
        List<List<Object>> rowData=null;
        try
        {

            List<Object> header=new ArrayList<Object>();
            rowData=new ArrayList<List<Object>>();
            List<Object> data;


            FileInputStream file = new FileInputStream(new File(excelFilePath));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(tabName);

            //Get Header  row
            XSSFRow headerRow= sheet.getRow(0);

            for(Cell cell:headerRow)
            {
                header.add(cell.getStringCellValue());
            }


            //Iterate through each rows one by one
            Iterator<Row>rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                data=new ArrayList<>();

                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() );
                            data.add(cell.getNumericCellValue());
                            break;
                        case STRING:
                            System.out.println(cell.getStringCellValue() );
                            data.add(cell.getStringCellValue());
                            break;
                        case BLANK:
                            System.out.println("Cell has blank");
                            data.add("");
                            break;
                        case FORMULA:
                            System.out.println(cell.getCellFormula());
                            data.add(cell.getCellFormula());
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            data.add(cell.getBooleanCellValue());
                            break;
                        default:   System.out.print("could not get cell type");
                    }


                }
                if(header.size()!=data.size())
                {
                    data.add("");
                }
                rowData.add(data);

            }
            rowData.remove(0);
            System.out.println(header);
            System.out.println(rowData);
            file.close();






        }catch(Exception e)
        {e.printStackTrace();
        }
        return rowData;
    }




    public static Map<Object, Object>  readExcelTabRow(String excelFilePath,String tabName, String suiteType, int rowNm)
    {
        Map<Object, Object> keyValues=null;
        try
        {

            List<Object> header=new ArrayList<Object>();
            List<List<Object>> rowData=new ArrayList<List<Object>>();
            List<Object> data=new ArrayList<Object>();
            keyValues= new HashMap<>();

            FileInputStream file = new FileInputStream(new File(excelFilePath));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(tabName);

            //Get Header  row
            XSSFRow headerRow= sheet.getRow(0);

            for(Cell cell:headerRow)
            {
                header.add(cell.getStringCellValue());
            }


            //Iterate through each rows one by one
             XSSFRow valueRow= sheet.getRow(rowNm);
             Iterator<Row> rowIterator=valueRow.getSheet().rowIterator();


                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = valueRow.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() );
                            data.add(cell.getNumericCellValue());
                            break;
                        case STRING:
                            System.out.println(cell.getStringCellValue() );
                            data.add(cell.getStringCellValue());
                            break;
                        case BLANK:
                            System.out.println("Cell has blank");
                            data.add("");
                            break;
                        case FORMULA:
                            System.out.println(cell.getCellFormula());
                            data.add(cell.getCellFormula());
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            data.add(cell.getBooleanCellValue());
                            break;
                        default:   System.out.print("could not get cell type");
                    }


                }
            if(header.size()!=data.size())
            {
                data.add("");
            }
            rowData.add(data);

            System.out.println(header);
            System.out.println(rowData);
            file.close();


            for (int i = 0; i <= rowData.size() - 1; i++)
            {
                for(int j=0;j<=header.size()-1;j++)
                {
                    keyValues.put(header.get(j), rowData.get(i).get(j));
                }
            }

        }catch(Exception e)
        {e.printStackTrace();
        }
        return keyValues;
    }




    public static Map<String, String>  readExcelTabRowNew(String folderPath,String fileNam,String tabName, String rowNm)
    {
        Map<String, String> keyValues=null;
        try
        {

            List<Object> header=new ArrayList<Object>();
            List<List<Object>> rowData=new ArrayList<List<Object>>();
            List<Object> data=new ArrayList<Object>();
            keyValues= new HashMap<>();

            FileInputStream file = new FileInputStream(new File(folderPath+fileNam));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(tabName);

            //Get Header  row
            XSSFRow headerRow= sheet.getRow(0);

            for(Cell cell:headerRow)
            {
                header.add(cell.getStringCellValue());
            }


            //Iterate through each rows one by one
            XSSFRow valueRow= sheet.getRow(Integer.valueOf(rowNm));

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = valueRow.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue() );
                        data.add(cell.getNumericCellValue());
                        break;
                    case STRING:
                        System.out.println(cell.getStringCellValue() );
                        data.add(cell.getStringCellValue());
                        break;
                    case BLANK:
                        System.out.println("Cell has blank");
                        data.add("");
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        data.add(cell.getCellFormula());
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        data.add(cell.getBooleanCellValue());
                        break;
                    default:   System.out.print("could not get cell type");
                }


            }
            if(header.size()!=data.size())
            {
                data.add("");
            }
            rowData.add(data);

            System.out.println(header);
            System.out.println(rowData);
            file.close();


            for (int i = 0; i <= rowData.size() - 1; i++)
            {
                for(int j=0;j<=header.size()-1;j++)
                {
                    keyValues.put(header.get(j).toString(), rowData.get(i).get(j).toString());
                }
            }

        }catch(Exception e)
        {e.printStackTrace();
        }
        return keyValues;
    }



    public static void  writeExcelTabRowNew(String folderPath,String fileNam,String tabName, String rowNm, String columnName , String setData)
    {
        Map<String, String> keyValues=null;
        try
        {

            List<Object> header=new ArrayList<Object>();
            List<List<Object>> rowData=new ArrayList<List<Object>>();
            List<Object> data=new ArrayList<Object>();
            keyValues= new HashMap<>();


            FileInputStream readFile = new FileInputStream(new File(folderPath+fileNam));

            //Create Workbook instance holding reference to .xlsx readFile
            XSSFWorkbook workbook = new XSSFWorkbook(readFile);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(tabName);

            //Get Header  row
            XSSFRow headerRow= sheet.getRow(0);

            for(Cell cell:headerRow)
            {
                header.add(cell.getStringCellValue());
            }


            //Get first/desired sheet from the workbook
            sheet = workbook.getSheet(tabName);


            //Iterate through each rows one by one
            XSSFRow valueRow= sheet.getRow(Integer.valueOf(rowNm));

            for(int i=0; i<=header.size()-1;i++)
            {
                if(header.get(i).toString().trim().equals(columnName))
                {
                    valueRow.createCell(i).setCellValue(setData);
                }
            }

            readFile.close();

            FileOutputStream outputStream = new FileOutputStream(new File(folderPath+fileNam));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        }catch(Exception e)
        {e.printStackTrace();
        }

    }





}
