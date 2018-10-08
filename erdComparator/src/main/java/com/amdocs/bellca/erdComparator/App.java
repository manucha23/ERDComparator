package com.amdocs.bellca.erdComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.bellca.erdComparator.repository.DocErdRepository;
import com.amdocs.bellca.erdComparator.repository.XmlErdRepository;
import com.amdocs.bellca.erdComparator.services.CreateReport;
import com.amdocs.bellca.erdComparator.services.FindERDInXml;
import com.amdocs.bellca.readers.DocErdReader;
import com.amdocs.bellca.readers.XmlErdReader;

/**
 * Hello world!
 *
 */
public class App 
{

	
	public static void main( String[] args ) throws IOException, InterruptedException
	{
		List<String> xmlFiles = new ArrayList<String>();
		
		System.out.println("Please enter XML files to be scanned. Press enter when done");
		boolean continueScan = true;
		while(continueScan) {
			String filePath = System.console().readLine();
			if(filePath != null && !filePath.isEmpty()) {
				xmlFiles.add(filePath);
			}
			else {
				continueScan = false;
			}
		}
		
//		xmlFiles.add("C:\\Dev2017\\LuckyMobilePIS2\\LiveContent\\MYB\\Content\\GlobalResources\\UXP\\Widgets\\Usage\\Mobility\\Prepaid\\mobility-prepaid-usage-dashboard\\mobility-prepaid-usage-dashboard.xml");
//		xmlFiles.add("C:\\Dev2017\\LuckyMobilePIS2\\LiveContent\\MYB\\Content\\GlobalResources\\UXP\\Widgets\\Usage\\Mobility\\Prepaid\\mobility-prepaid-data-usage-card\\mobility-prepaid-data-usage-card.xml");
//		xmlFiles.add("C:\\Dev2017\\LuckyMobilePIS2\\LiveContent\\MYB\\Content\\GlobalResources\\UXP\\Widgets\\Usage\\Mobility\\Prepaid\\mobility-prepaid-ld-usage-card\\mobility-prepaid-ld-usage-card.xml");
//		xmlFiles.add("C:\\Dev2017\\LuckyMobilePIS2\\LiveContent\\MYB\\Content\\GlobalResources\\UXP\\Widgets\\Usage\\Mobility\\Prepaid\\mobility-prepaid-text-usage-card\\mobility-prepaid-text-usage-card.xml");
//		xmlFiles.add("C:\\Dev2017\\LuckyMobilePIS2\\LiveContent\\MYB\\Content\\GlobalResources\\UXP\\Widgets\\Usage\\Mobility\\Prepaid\\mobility-prepaid-transaction-history\\mobility-prepaid-transaction-history.xml");
//		
		
		List<String> docFiles = new ArrayList<String>();
		
		System.out.println("Please enter Document files with ERDs. Press enter when done");
		continueScan = true;
		while(continueScan) {
			String filePath = System.console().readLine();
			if(filePath != null && !filePath.isEmpty()) {
				docFiles.add(filePath);
			}
			else {
				continueScan = false;
			}
		}
		
		System.out.println("Please enter the path where report file will be genrated");
		String outputSilePath = System.console().readLine();
//		docFiles.add("C:\\ERDs\\New folder\\ERD-LuckyMobile-Phase2-Overview-Pages DG FINAL v2 EN+FR May 18.docx");
//		docFiles.add("C:\\ERDs\\New folder\\ERD-LuckyMobile-Phase2-Overview-Pages DG FINAL v2 EN+FR May 18.docx");
//		
		System.out.println( "Starting Scan......" );
		
		XmlErdRepository xmlRepo = new XmlErdRepository();
		DocErdRepository docRepo = new DocErdRepository();
		
		DocErdReader docReader = new DocErdReader(docFiles, docRepo);
		XmlErdReader xmlReader = new XmlErdReader(xmlFiles, xmlRepo);
		
		docReader.start();
		xmlReader.start();
        
        docReader.join();
        xmlReader.join();
        System.out.println("Total size of XML document: " + xmlRepo.getXmlErdList().size());
        System.out.println("Total size of ERD document: " + docRepo.getErdStandardModel().size());
        
        FindERDInXml findErd = new FindERDInXml();
        findErd.checKErdXML(docRepo.getErdStandardModel(), xmlRepo.getXmlErdList());
        CreateReport.writeToXlsxFile(docRepo.getErdStandardModel(), outputSilePath + "\\ERDReport.xlsx");
        
        System.out.println("Scan complete.....");
        System.out.println("Report genrated at: " + outputSilePath + "\\ERDReport.xlsx");
        System.console().readLine();
        
//      //print Xml information
        
//        for(ErdStandardModel erd : xmlRepo.getXmlErdList())
//            System.out.println(erd);
//        
//      //print Docfile information
        
//        for(ErdStandardModel erd : docRepo.getErdStandardModel())
//            System.out.println(erd);
	}
}
