package UnitLinkedQuote;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement()
public class Premium implements Observer {
	
	@XmlAttribute
	private String _Name;
	@XmlAttribute
	private Events _Freq;
	@XmlAttribute
	private double _Premium;
	@XmlTransient
	private Tick _Tick;
	@XmlTransient
	private Allocation _Alloc;
	@XmlElementWrapper( name = "PremiumSchedule" )
	private Map<Integer, Double> _PremiumSchedule = new HashMap<Integer, Double>(600);
	
	public Premium() {}
	
	public Premium( double premium, Events freq, Allocation alloc, String name ) {
		
		_Name = name;
		
		_Freq = freq;
		
		_Premium = premium;
		
		_Alloc = alloc;
				
	}
				
	public String getName() {
		
		return _Name;
	
	}
	
	public double getPremium() {
		
		double Temp;
		
		if ( _Freq == _Tick.getEvent() ) {
		
			Temp = _Premium;
			
			_PremiumSchedule.put( _Tick.getMonthNumber(), Temp  );
			
		}
		else Temp = 0 ;
		
		return Temp;
	
	}
			
	@Override
	public void Update( Tick tick ) {
		
		_Tick = tick;
		
		if ( _Tick.getEvent() == Events.INITIAL ) {
		
			_PremiumSchedule.clear();
		}
		
		System.out.println( "\t\tPremium "+ _Name + " Update " + tick.getEvent() );
	}
	
	public double Apply() {
		
		double NetAllocationPremium;
							
		NetAllocationPremium = getPremium() * getAllocation();
				
		System.out.println( "\t\t\tPremium "+ _Name + " Amount : " + NetAllocationPremium );
		
		return NetAllocationPremium;
		
	}
	
	public double Total() {
		
		double Temp = 0;
			
		for ( Map.Entry<Integer, Double> PA  : _PremiumSchedule.entrySet() ) {
			
			Temp += (double)PA.getValue();
		
		}
		
		return Temp;
	}
	
	private double getAllocation() {
		
		return _Alloc.get_AllocationRate( _Premium );
	}

}