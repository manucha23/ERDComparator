package com.amdocs.bellca.erdComparator.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;
import com.amdocs.bellca.erdComparator.repository.DocErdRepository;

public class ReadDocFile extends Thread{

	private XWPFDocument doc;
	private File docFile;
	DocErdRepository Repo;
	private Iterator<IBodyElement> documentElements;

	public ReadDocFile(String filePath, DocErdRepository repo) throws IOException {
		docFile = new File(filePath);
		Repo = repo;
	}



	@Override
	public void run() {
		System.out.println("Thread name: " + Thread.currentThread().getName() + ". Thread started");
		if(!docFile.exists()) {
			try {
				throw new FileNotFoundException(String.format("Word document does not exist")) ;
			} catch (FileNotFoundException e) {
				System.out.println("Thread name: " + Thread.currentThread().getName() + " . Unable to read file");
				e.printStackTrace();
			}
		}
		try(FileInputStream fis = new FileInputStream(docFile)){
			doc = new XWPFDocument(fis);
			documentElements = doc.getBodyElementsIterator();
			while(documentElements.hasNext()) {
				IBodyElement element = documentElements.next();
				if("TABLE".equalsIgnoreCase(element.getElementType().name())) {
					List<XWPFTable> tableList =  element.getBody().getTables();
					//List<XWPFTable> tableList1 =  element1.getBody().getTables();
					for (int i=0; i<tableList.size(); i++){
						XWPFTable table1 = tableList.get(i);
						System.out.println("Total Number of Rows of Table:"+table1.getNumberOfRows());
						//String tableDate = table1.getText();						
						//System.out.println(tableDate);
						CreateErdObjects(table1.getText());
					}
				}
			}


			System.out.println("Thread name: " + Thread.currentThread().getName() + " . Finished execution");
		} catch (FileNotFoundException e) {
			System.out.println("Thread name: " + Thread.currentThread().getName() + " . Unable to read file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Thread name: " + Thread.currentThread().getName() + " . Failed to read file");
			e.printStackTrace();
		}

	}

	private void CreateErdObjects(String table) {
		List<ErdStandardModel> modelArray = new ArrayList<ErdStandardModel>();
		String[] rowArray = table.split("\n");
		for(int i=1; i<rowArray.length; i++) {
			ErdStandardModel model = new ErdStandardModel();
			String row = rowArray[i];
			String[] cells = row.split("\t");
			if(cells.length >= 4) {			
				model.setEnValue(cells[2]);
				model.setFrValue(cells[3]);
				modelArray.add(model);
			}
		}
		Repo.setErdStandardModel(modelArray);
	}

}
