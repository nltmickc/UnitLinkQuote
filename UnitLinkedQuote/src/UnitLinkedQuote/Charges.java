package UnitLinkedQuote;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Charges")
public class Charges implements Iterable<Charge> {
	
	@XmlElement(name = "Charge", type = Charge.class)
	private List<Charge> _Charges = new CopyOnWriteArrayList<Charge>();
	@XmlTransient
	private ChargeSchedules _ChargeSchedules;
	
	public Charges() {
		
		_ChargeSchedules = new ChargeSchedules();
		
	}
		
	public void AddCharge(Charge charge) {
		
		Schedule.Instance().registerObserver( charge );
		
		_Charges.add( charge );
		
		_ChargeSchedules.AddChargeSchedule( charge.Schedule() );
				
	}
	
	public void RemoveCharge(Charge charge) {
		
		Schedule.Instance().removeObserver( charge );
		
		_Charges.remove( charge );
		
		_ChargeSchedules.RemoveChargeSchedule( charge.Schedule() );
		
	}
	
	public void Clear() {
		
		for ( Charge c : _Charges ) {
			
			Schedule.Instance().removeObserver( c );
		}
		
		_Charges.clear();
		
		_ChargeSchedules.Clear();
	}
			
	@Override
	public Iterator<Charge> iterator() {
		
        Iterator<Charge> icharge = _Charges.iterator();
        
        return icharge; 
    }
	
	public double Total() {
		
		double _Temp = 0;
			
		for ( Charge c : _Charges ) {
			
			_Temp +=  c.Total();
			
		}
		
		return _Temp;
	}
		
	public double Total( String fund ) {
		
		double _Temp = 0;
			
		for ( Charge c : _Charges ) {
			
			_Temp +=  c.Total( fund );
		
		}
		
		return _Temp;
	}
	
}