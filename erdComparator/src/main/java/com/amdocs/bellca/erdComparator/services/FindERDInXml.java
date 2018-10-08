package com.amdocs.bellca.erdComparator.services;

import java.util.List;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;

public class FindERDInXml {
	
	public void checKErdXML(List<ErdStandardModel> docErd, List<ErdStandardModel> xmlErdList) {
		for(ErdStandardModel erd: docErd) {
			String enValue = erd.getEnValue();
			String frValue = erd.getFrValue();
			for(ErdStandardModel xmlErd: xmlErdList) {
				if(xmlErd.getEnValue().equals(enValue) && xmlErd.getFrValue().equals(frValue))
					erd.setIsPresent(true);
			}
		}
	}

}
