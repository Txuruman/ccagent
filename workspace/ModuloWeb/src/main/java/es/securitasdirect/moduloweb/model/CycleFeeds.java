package es.securitasdirect.moduloweb.model;

import java.util.Date;

/**
 * @author JAS
 *Los Cycle Fees son conceptos que pueden estar asociados a facturas, como servicios recurrentes de facturación. Pero también pueden estar asociados directamente a la instalación, para especificar, por ejemplo, descuentos que tiene o ha tenido activos esa instalación.
 *En el caso que el Cycle Fee esté asociado a una factura, se almacenará como Item de la Factura, pero si se debe mostrar en la pantalla independiente de Cycle Fees, se asociará únicamente a la instalación.
 */
public class CycleFeeds {
	/**
	 * Número de instalación (si el Cycle Fee no está asociado )
	 */
	public String insNo;
	
	/**
	 * Fecha de inicio del Cycle Fee
	 */
	public Date FromDate;
	
	/**
	 * Fecha de fin del Cycle Fee
	 */
	public Date ToDate;
	
	/**
	 * Cuota
	 */
	public double Fee;
	
	/**
	 * Pte SD
	 */
	public String RevTp;
	
	/**
	 * Concepto
	 */
	public String Description;
	
	/**
	 * Cantidad de items.
	 */
	public int Count;
	
	public String getInsNo() {
		return insNo;
	}
	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}
	public Date getFromDate() {
		return FromDate;
	}
	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}
	public Date getToDate() {
		return ToDate;
	}
	public void setToDate(Date toDate) {
		ToDate = toDate;
	}
	public double getFee() {
		return Fee;
	}
	public void setFee(double fee) {
		Fee = fee;
	}
	public String getRevTp() {
		return RevTp;
	}
	public void setRevTp(String revTp) {
		RevTp = revTp;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	
	

}
