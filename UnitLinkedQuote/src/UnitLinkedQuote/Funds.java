package UnitLinkedQuote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Funds")
public class Funds implements Iterable<Fund> {
	
	@XmlTransient
	private Charges _Charges;
	@XmlTransient
	private Premiums _Premiums;
	
	@XmlElement(name = "Fund", type = Fund.class)
	private List<Fund> _Funds = new ArrayList<Fund>();
	
	public void AddFund( Fund fund ) {
		
		Schedule.Instance().registerObserver( fund );
		
		_Funds.add( fund );
	}
	
	public void RemoveFund( Fund fund ) {
		
		Schedule.Instance().removeObserver( fund );
		
		_Funds.remove( fund );
	}
	
	public void Clear() {
		
		for ( Fund f : _Funds) {
			
			Schedule.Instance().removeObserver( f );
		}

		_Funds.clear();
	}
	
	public void setCharges( Charges charges ) {
		
		_Charges = charges;
		
		for ( Fund f : _Funds ) {
		
			f.Charges( _Charges );
		}
	}
	
	public void setPremiums( Premiums premiums ) {
		
		_Premiums = premiums;
		
		for ( Fund f : _Funds ) {
		
			f.Premiums( _Premiums );
		}
	}
	
	public void ApplyPremiums() {
		
		for ( Fund f : _Funds ) {
			
			f.ApplyPremiums();
		}
	}
	
	public void Rollup() {
		
		for ( Fund f : _Funds ) {
			
			f.RollUp();
		}
	}
		
	public void DeductCharges() {
		
		for ( Fund f : _Funds ) {
			
			f.CalculateCharges( this );
		}
		
		for ( Fund f : _Funds ) {
			
			f.DeductCharges();
		}
	}
	
	@Override
	public Iterator<Fund> iterator() {
		
        Iterator<Fund> ifund = _Funds.iterator();
        
        return ifund; 
    }
	
	public double Total( int Month ) {
		
		double _Temp = 0;
			
		for (Fund f : _Funds) {
			
			_Temp +=  f.Total( Month );
		
		}
		return _Temp;
	}
			
	public double Total() {
		
		double _Temp = 0;
			
		for (Fund f : _Funds) {
			
			_Temp +=  f.Total();
		
		}
		return _Temp;
	}
}