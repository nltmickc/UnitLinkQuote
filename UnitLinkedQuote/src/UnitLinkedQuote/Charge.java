package UnitLinkedQuote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE) 
public abstract class Charge implements Observer {
	
	protected Tick _Tick;
	@XmlAttribute
	protected String _Name;
	@XmlElement( name = "ChargeSchedule" )
	protected ChargeSchedule _ChargeSchedule;
	
	public abstract boolean CompanyCharge();
	
	public abstract double Apply( Fund fund, Funds funds );
			
	public String getName() {
		
		return this._Name;
	}
		
	public void Update( Tick tick ) {
		
		_Tick = tick;
	}
	
	public double Total() {
		
		return _ChargeSchedule.Total();
	}
	
	public double Total( String fund ) {
		
		return _ChargeSchedule.Total( fund );
	}
	
	public double Total( int month ) {
		
		return _ChargeSchedule.Total( month );
	}
		
	public ChargeSchedule Schedule() {
		
		return _ChargeSchedule;
	}
	
	protected void StoreCharge( String fundname, double chargeamount ) {
		
		_ChargeSchedule.AddChargeAmount( new ChargeItem( fundname, _Tick.getMonthNumber(), chargeamount ) );
	}
}