package es.securitasdirect.moduloweb.model;

import java.util.Date;

/**
 * @author JAS
 *Los Cycle Fees son conceptos que pueden estar asociados a facturas, como servicios recurrentes de facturación. Pero también pueden estar asociados directamente a la instalación, para especificar, por ejemplo, descuentos que tiene o ha tenido activos esa instalación.
 *En el caso que el Cycle Fee esté asociado a una factura, se almacenará como Item de la Factura, pero si se debe mostrar en la pantalla independiente de Cycle Fees, se asociará únicamente a la instalación.
 */
public class CycleFeeds {

    /**
     * clave autonumerica para la tabla
     */
    private String id;
	/**
	 * Número de instalación (si el Cycle Fee no está asociado )
	 */
	public String insNo;
	
	/**
	 * Fecha de inicio del Cycle Fee
	 */
	public String FromDate;
	
	/**
	 * Fecha de fin del Cycle Fee
	 */
	public String ToDate;
	
	/**
	 * Cuota
	 */
	public String Fee;
	
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
	public String Count;


    //constructor copia de la clase CycleFeeds
    /*
    public CycleFeeds(final org.wso2.ws.dataservice.GetCycleFeedsResult getCycleFeedsResult) {
        this.id = getCycleFeedsResult.getId().intValue();
        this.insNo = getCycleFeedsResult.getInsNo();
        this.FromDate = getCycleFeedsResult.getFromDate();
        this.ToDate = getCycleFeedsResult.getToDate();
        this.Fee = getCycleFeedsResult.Fee();
        this.RevTp = getCycleFeedsResult.getRevTp();
        this.Description = getCycleFeedsResult.getDescription();
        this.Count = getCycleFeedsResult.Count();
    }
    */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getInsNo() {
		return insNo;
	}
	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}
	public String getFromDate() {
		return FromDate;
	}
	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}
	public String getToDate() {
		return ToDate;
	}
	public void setToDate(String toDate) {
		ToDate = toDate;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
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
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	
	

}
