package com.amdocs.bellca.readers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.bellca.erdComparator.repository.DocErdRepository;
import com.amdocs.bellca.erdComparator.services.ReadDocFile;

public class DocErdReader extends Thread{

	List<String> FilePaths;
	DocErdRepository Repo;
	public DocErdReader(List<String> filePaths, DocErdRepository repo) {
		FilePaths = filePaths;
		Repo = repo;
	}
	
	public void run() {
		List<ReadDocFile> docReaders = new ArrayList<ReadDocFile>();
		//Init all threads
		for(String paths: FilePaths) {
			try {
				docReaders.add(new ReadDocFile(paths,Repo));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Start All threads
		for(ReadDocFile reader: docReaders) {
			reader.start();
		}
		
		//Join all thread
		for(ReadDocFile reader: docReaders) {
			try {
				reader.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
