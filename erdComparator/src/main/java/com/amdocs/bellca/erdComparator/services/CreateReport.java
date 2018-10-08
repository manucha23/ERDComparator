package com.amdocs.bellca.erdComparator.services;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;

public class CreateReport {

	public static void createCSVReport(List<ErdStandardModel> docErd) throws IOException {
		try(FileWriter writer = new FileWriter("C:\\ERDs\\ERDReport.csv")){
			StringBuilder builder = new StringBuilder();
			for(ErdStandardModel model: docErd) {
				builder.append(model.toString());
			}
			writer.write(builder.toString());
		}
	}
	
	public static void writeToXlsxFile(List<ErdStandardModel> docErd, String fileName) {

        try (OutputStream fileStream = new FileOutputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Test People Sheet");

            // Create a header row describing what the columns mean
            CellStyle boldStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            boldStyle.setFont(font);

            Row headerRow = sheet.createRow(0);
            List<String> heading = new ArrayList<String>();
            heading.add("English Value");
            heading.add("French Value");
            heading.add("Found in code?");
            addStringCells(headerRow, heading, boldStyle);

            // Define how a cell containing a date is displayed
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat()
                    .getFormat("yyyy/m/d"));

            // Add the person data as rows
            for (int i = 0; i < docErd.size(); i++) {
                // Add one due to the header row
                Row row = sheet.createRow(i + 1);
                ErdStandardModel erd = docErd.get(i);
                addCells(erd, row, dateCellStyle);
            }

            workbook.write(fileStream);
            workbook.close();

        } catch (IOException e) {
            System.err.println("Could not create XLSX file at " + fileName);
            e.printStackTrace();
        }
    }
	
	private static void addCells(ErdStandardModel erd, Row row,
			CellStyle dateCellStyle) {

		Cell enCell = row.createCell(0, CellType.STRING);
		enCell.setCellValue(erd.getEnValue());

		Cell frCell = row.createCell(1, CellType.STRING);
		// Convert LocalDate to a legacy Date object
		frCell.setCellValue(erd.getFrValue());

		Cell foundCell = row.createCell(2, CellType.BOOLEAN);
		foundCell.setCellValue(erd.isIsPresent());
	}
	
	// Adds strings as styled cells to a row
    private static void addStringCells(Row row, List<String> strings,
                                      CellStyle style) {
        for (int i = 0; i < strings.size(); i++) {
            Cell cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(strings.get(i));
            cell.setCellStyle(style);
        }
    }
}
