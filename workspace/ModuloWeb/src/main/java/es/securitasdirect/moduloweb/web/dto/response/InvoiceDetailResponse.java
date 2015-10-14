package es.securitasdirect.moduloweb.web.dto.response;

import java.util.ArrayList;
import java.util.List;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;
import es.securitasdirect.moduloweb.model.InvoiceItem;

public class InvoiceDetailResponse extends BaseResponse{
	private List<InvoiceItem> itemList=new ArrayList<InvoiceItem>();

	public InvoiceDetailResponse(List<InvoiceItem> itemList) {
		super();
		this.itemList = itemList;
	}

	public InvoiceDetailResponse() {}

	public List<InvoiceItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<InvoiceItem> itemList) {
		this.itemList = itemList;
	}
	
}
