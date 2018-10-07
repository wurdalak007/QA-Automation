package com.company;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet;
        sheet = workbook.createSheet("Просто лист");

        // заполняем список какими-то данными
        List<DataModel> dataList = fillData();

        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Возраст");
        row.createCell(4).setCellValue("Пол");
        row.createCell(5).setCellValue("Дата рождения");
        row.createCell(6).setCellValue("ИНН");
        row.createCell(7).setCellValue("Почтовый индекс");
        row.createCell(8).setCellValue("Страна");
        row.createCell(9).setCellValue("Область");
        row.createCell(10).setCellValue("Город");
        row.createCell(11).setCellValue("Улица");
        row.createCell(12).setCellValue("Номер дома");
        row.createCell(13).setCellValue("Номер квартиры");

        // заполняем лист данными
        for (DataModel dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        // записываем созданный в памяти Excel документ в файл
        String path = new File("").getAbsolutePath();
        String fileName = "Table.xls";
        try (FileOutputStream out = new FileOutputStream(new File(path + "/" + fileName))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан! Путь :" + path);
    }

    // заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, DataModel dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getName());
        row.createCell(1).setCellValue(dataModel.getSurname());
        row.createCell(2).setCellValue(dataModel.getSecondName());
        row.createCell(3).setCellValue(dataModel.getAge());

        if (dataModel.isSex()) {
            row.createCell(4).setCellValue(1);
        } else {
            row.createCell(4).setCellValue(0);
        }

        row.createCell(5).setCellValue(dataModel.getDateOfBirth());
        row.createCell(6).setCellValue(dataModel.getINN());
        row.createCell(7).setCellValue(dataModel.getPostIndex());
        row.createCell(8).setCellValue(dataModel.getCountry());
        row.createCell(9).setCellValue(dataModel.getRegion());
        row.createCell(10).setCellValue(dataModel.getCity());
        row.createCell(11).setCellValue(dataModel.getStreet());
        row.createCell(12).setCellValue(dataModel.getHouseNum());
        row.createCell(13).setCellValue(dataModel.getFlatNum());


    }

    private static List<DataModel> fillData() throws IOException {
        List<DataModel> dataModels = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(30);
        for (int i = 0; i < size; i++) {
            dataModels.add(new DataModel());
        }
        return dataModels;
    }

}
