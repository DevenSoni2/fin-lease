package com.nagarro.fin.lease.app.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nagarro.fin.lease.app.constants.ApplicationConstants;
import com.nagarro.fin.lease.app.payload.response.LeaseResponseDTO;

/**
 * The Class LeaseExporterClass.
 */
public class LeaseExporterClass {
	
	/** The workbook. */
	private XSSFWorkbook workbook;
	
	/** The sheet. */
	private XSSFSheet sheet;
	
	/** The lease list. */
	private List<LeaseResponseDTO> leaseList;
	
	/** The status. */
	private String status;

	/**
	 * Instantiates a new lease exporter class.
	 *
	 * @param leaseList the lease list
	 * @param status the status
	 */
	public LeaseExporterClass(List<LeaseResponseDTO> leaseList, String status) {
		this.leaseList = leaseList;
		workbook = new XSSFWorkbook();
		this.status = status;
	}

	/**
	 * Write header line.
	 */
	private void writeHeaderLine() {
		sheet = workbook.createSheet(ApplicationConstants.LEASE);

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, ApplicationConstants.REFERENCE_ID, style);
		createCell(row, 1, ApplicationConstants.CREATED_BY, style);
		createCell(row, 2, ApplicationConstants.BUISNESS_UNIT, style);
		createCell(row, 3, ApplicationConstants.CUSTOMER_NAME, style);
		createCell(row, 4, ApplicationConstants.CUSTOMER_NUMBER, style);
		createCell(row, 5, ApplicationConstants.APPLIED_ON, style);
		createCell(row, 6, ApplicationConstants.LEASE_TYPE, style);
		createCell(row, 7, ApplicationConstants.DECISION, style);
		createCell(row, 8, ApplicationConstants.ASSET_MAKE, style);
		createCell(row, 9, ApplicationConstants.ASSET_MODEL, style);
		createCell(row, 10, ApplicationConstants.ASSET_PURPOSE, style);
		createCell(row, 11, ApplicationConstants.USER_ID, style);
		createCell(row, 12, ApplicationConstants.USER_NAME, style);
		if (this.status.equals(ApplicationConstants.CLOSED)) {
			createCell(row, 13, ApplicationConstants.APPROVAL_USER_ID, style);
			createCell(row, 14, ApplicationConstants.APPROVAL_DATE, style);
			createCell(row, 15, ApplicationConstants.APPROVAL_COMMENT, style);
		}

	}

	/**
	 * Creates the cell.
	 *
	 * @param row the row
	 * @param columnCount the column count
	 * @param value the value
	 * @param style the style
	 */
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	/**
	 * Write data lines.
	 */
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (LeaseResponseDTO lease : leaseList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, lease.getReferenceId(), style);
			createCell(row, columnCount++, lease.getCreatedBy(), style);
			createCell(row, columnCount++, lease.getBuisnessUnit(), style);
			createCell(row, columnCount++, lease.getCustomerName(), style);
			createCell(row, columnCount++, lease.getCustomerNumber(), style);
			createCell(row, columnCount++, lease.getCreatedTime().toString(), style);
			createCell(row, columnCount++, lease.getLeaseType(), style);
			createCell(row, columnCount++, lease.getStatus(), style);

			createCell(row, columnCount++, lease.getAssetMake(), style);
			createCell(row, columnCount++, lease.getAssetModel(), style);
			createCell(row, columnCount++, lease.getAssetPurpose(), style);
			createCell(row, columnCount++, lease.getProposerUserId(), style);
			createCell(row, columnCount++, lease.getPropserUserName(), style);
			if (this.status.equals(ApplicationConstants.CLOSED)) {
				createCell(row, columnCount++, lease.getApprovalUserId(), style);
				createCell(row, columnCount++, lease.getApprovalDate().toString(), style);
				createCell(row, columnCount++, lease.getApprovalComment(), style);
			}
		}
	}

	/**
	 * Export.
	 *
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
