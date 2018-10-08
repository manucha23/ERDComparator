package com.amdocs.bellca.erdComparator.repository;

import java.util.ArrayList;
import java.util.List;

import com.amdocs.bellca.erdComparator.models.ErdStandardModel;

public class DocErdRepository {

	private List<ErdStandardModel> ErdStandardModel;

	public List<ErdStandardModel> getErdStandardModel() {
		return ErdStandardModel;
	}

	public synchronized void setErdStandardModel(List<ErdStandardModel> erdStandardModel) {
		if(ErdStandardModel == null)
			ErdStandardModel = new ArrayList<ErdStandardModel>();
		if(erdStandardModel != null)
			this.ErdStandardModel.addAll(erdStandardModel);
	}

}
