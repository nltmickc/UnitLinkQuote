package UnitLinkedQuote;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public class ChargeSchedule implements Iterable<ChargeItem> {
		
	private String _ChargeName;
	@XmlElement( name = "ChargeAmount" )
	private List<ChargeItem> _ChargeAmounts = new ArrayList<ChargeItem>();
    
    public ChargeSchedule() { }
		
	public ChargeSchedule( String chargename ) {
		
		_ChargeName = chargename; 
	}
	
	public String getChargeName() {
		
		return _ChargeName;
	}
		
	public void AddChargeAmount(ChargeItem chargeamount) {
		
		_ChargeAmounts.add( chargeamount );
	}
	
	public void Clear() {
				
		_ChargeAmounts.clear();
	}
			
	@Override
	public Iterator<ChargeItem> iterator() {
		
        Iterator<ChargeItem> ichargeamount = _ChargeAmounts.iterator();
        
        return ichargeamount; 
    }
	
	public double Total() {
		
		double _Temp = 0;
								
		for ( ChargeItem ci : _ChargeAmounts ) {
				
			_Temp +=  ci.getAmount();
		}
						
		return _Temp;
	}
		
	public double Total( String fundname ) {
		
		double _Temp = 0;
			
		for ( ChargeItem ci : _ChargeAmounts ) {
			
			if ( ci.getFund() == fundname ) {
			
				_Temp +=  ci.getAmount();
			}
		}
		
		return _Temp;
	}
	
	public double Total( int month ) {
		
		double _Temp = 0;
			
		for ( ChargeItem ci : _ChargeAmounts ) {
			
			if ( ci.getMonth() == month) {
			
				_Temp +=  ci.getAmount();
			}
		}
		return _Temp;
	}
}