package com.amdocs.bellca.erdComparator.repository;

import java.util.ArrayList;
import java.util.List;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;

public class XmlErdRepository {
	
	public List<ErdStandardModel> XmlErdList;

	public List<ErdStandardModel> getXmlErdList() {
		return XmlErdList;
	}

	public synchronized void setXmlErdList(List<ErdStandardModel> xmlErdList) {
		System.out.println(Thread.currentThread().getName() + ": Writing data to main list");
		if(XmlErdList == null)
			XmlErdList = new ArrayList<ErdStandardModel>();
		XmlErdList.addAll(xmlErdList);
		System.out.println(Thread.currentThread().getName() + ": Writing complete");
	}
	
	

}
