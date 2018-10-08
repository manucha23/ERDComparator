package com.amdocs.bellca.readers;

import java.util.ArrayList;
import java.util.List;

import com.amdocs.bellca.erdComparator.repository.XmlErdRepository;
import com.amdocs.bellca.erdComparator.services.ReadXMLFile;

public class XmlErdReader extends Thread{
	
	List<String> FilePaths;
	XmlErdRepository Repo;
	public XmlErdReader(List<String> filePaths, XmlErdRepository repo) {
		FilePaths = filePaths;
		Repo = repo;
	}
	
	public void run() {
		List<ReadXMLFile> fileReaders = new ArrayList<ReadXMLFile>();
		for(String xmlFilePath : FilePaths) {
			fileReaders.add(new ReadXMLFile(xmlFilePath, Repo));
		}		
		
		for(ReadXMLFile reader: fileReaders) {
			reader.start();
		}
		for(ReadXMLFile reader: fileReaders) {
			try {
				reader.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
