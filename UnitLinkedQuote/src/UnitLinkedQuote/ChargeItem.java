package UnitLinkedQuote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.NONE)
public class ChargeItem {
	
	@XmlAttribute
	private String _Fund;
	@XmlAttribute
	private int _Month;
	@XmlAttribute
	private double _Amount;
	
	public ChargeItem() {};
	
	public ChargeItem ( String fund, int month, double amount ) {
		
		this._Fund = fund;
		
		this._Month = month;
		
		this._Amount = amount;
	}
	
	public String getFund() {
		
		return _Fund;
	}
	
	public int getMonth() {
		
		return _Month;
	}
	
	public double getAmount() {
		
		return _Amount;
	}
}
