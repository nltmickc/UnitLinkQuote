package UnitLinkedQuote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
public class Premiums implements Iterable<Premium> {
	
	@XmlElement(name = "Premium", type = Premium.class)
	private List<Premium> _Premiums = new ArrayList<Premium>();
	
	public void AddPremium( Premium premium ) {
		
		Schedule.Instance().registerObserver( premium );
		
		_Premiums.add( premium );
		
	}
	
	public void RemovePremium( Premium premium ) {
		
		Schedule.Instance().removeObserver( premium );
		
		_Premiums.remove( premium );
	}
	
	public void Clear(){
		
		for ( Premium p : _Premiums ) {
		
			Schedule.Instance().removeObserver( p );
		}
		
		_Premiums.clear();
	}
	
	@Override
	public Iterator<Premium> iterator() {        
		
        Iterator<Premium> ipremium = _Premiums.iterator();
        
        return ipremium; 
    }
	
	public double Total() {
		
		double _Temp = 0;
			
		for (Premium p : _Premiums) {
			
			_Temp +=  p.Total();
		
		}
		
		return _Temp;
	}
			
}