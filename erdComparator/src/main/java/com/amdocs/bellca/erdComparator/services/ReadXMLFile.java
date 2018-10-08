package com.amdocs.bellca.erdComparator.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;
import com.amdocs.bellca.erdComparator.models.ErdXmlModel;
import com.amdocs.bellca.erdComparator.repository.XmlErdRepository;

public class ReadXMLFile extends Thread{

	String XmlPath;
	XmlErdRepository XmlRepo;
	public ReadXMLFile(String xmlPath, XmlErdRepository xmlRepo) {
		XmlPath = xmlPath;
		XmlRepo = xmlRepo;
	}
	
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName() + ": Start");
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XmlHandler handler = new XmlHandler();
	        saxParser.parse(new File(XmlPath), handler);
	        //Get Employees list
	        List<ErdXmlModel> erdList = handler.getErdsInCodeList();
	        XmlRepo.setXmlErdList(ConverXmlListToStandard(erdList));
	        System.out.println(Thread.currentThread().getName() + ": End");
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	}

	private List<ErdStandardModel> ConverXmlListToStandard(List<ErdXmlModel> erdList){
		List<ErdStandardModel> responseList = new ArrayList<ErdStandardModel>();
		for(int i=0; i<erdList.size(); i++) {
			ErdStandardModel model = new ErdStandardModel();
			model.setId(erdList.get(i).getID());
			model.setEnValue(erdList.get(i).getValue());
			model.setFrValue(erdList.get(++i).getValue());
			responseList.add(model);
		}
		return responseList;
	}
}
