package com.amdocs.bellca.erdComparator.services;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.amdocs.bellca.erdComparator.models.ErdXmlModel;

public class XmlHandler extends DefaultHandler{

	private List<ErdXmlModel> erdsInCodeList = null;
	private ErdXmlModel erdsInCode = null;

	public List<ErdXmlModel> getErdsInCodeList(){
		return erdsInCodeList;
	}

	boolean bID = false;
	boolean blang = false;
	boolean bValue = false;

	@Override
	public void startElement (String uri, String localName,
			String qName, Attributes attributes)
					throws SAXException
	{
		
		if(qName.equalsIgnoreCase("TargetableProperty")) {
			erdsInCode = new ErdXmlModel();
			if(erdsInCodeList == null)
				erdsInCodeList = new ArrayList<ErdXmlModel>();			
		}
		else if(qName.equalsIgnoreCase("ID")) {
			bID = true;
		}
		else if(qName.equalsIgnoreCase("lang")) {
			blang  = true;
		}
		else if(qName.equalsIgnoreCase("Value")) {
			bValue = true;
		}
	}
	
	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("TargetableRule")) {            
        	erdsInCodeList.add(erdsInCode);
        	erdsInCode = new ErdXmlModel();
        }
    }
	
	@Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bID) {
            //age element, set Employee age
        	erdsInCode.setID(new String(ch, start, length));
        	bID = false;
        } else if (blang) {
        	erdsInCode.setLang(new String(ch, start, length));
        	blang = false;
        } else if (bValue) {
        	erdsInCode.setValue(new String(ch, start, length));
        	bValue = false;
        }
    }
}
