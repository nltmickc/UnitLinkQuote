package UnitLinkedQuote;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChargeSchedules implements Iterable<ChargeSchedule> {
	
	private List<ChargeSchedule> _ChargeSchedules = new CopyOnWriteArrayList<ChargeSchedule>();
									
	public void AddChargeSchedule( ChargeSchedule chargeschedule ) {
			
			_ChargeSchedules.add( chargeschedule );
	}
	
	public void RemoveChargeSchedule(ChargeSchedule chargeschedule ) {
		
		_ChargeSchedules.remove( chargeschedule );
	}	
				
	public void Clear() {
			
		_ChargeSchedules.clear();
	}
				
	@Override
	public Iterator<ChargeSchedule> iterator() {
			
		Iterator<ChargeSchedule> ichargeschedule = _ChargeSchedules.iterator();
	        
		return ichargeschedule; 
	}
		
	public double Total() throws Exception {
			
		double _Temp = 0;
				
		for ( ChargeSchedule cs : _ChargeSchedules ) {

			_Temp +=  cs.Total();
		}	
		return _Temp;
	}
}