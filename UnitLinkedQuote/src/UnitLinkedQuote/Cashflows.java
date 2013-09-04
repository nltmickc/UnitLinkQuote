package UnitLinkedQuote;

import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement()
@XmlType( factoryMethod = "Instance" )
public class Cashflows {
	// Cashflows is a Singleton
	@XmlTransient
	private static Cashflows _Instance = null; // once instance per type
	
	@XmlElementWrapper( name = "CashFlowSchedule" )
	private Map<Integer, Double> _Cashflows = new HashMap<Integer, Double>();
	
	private Cashflows() {} // private constructor	
		
	public static Cashflows Instance() {
	
		if( _Instance == null ) {
			
			_Instance = new Cashflows();
		}
		return _Instance;
	}
		
	public void AddCashflow( Tick time, double cashflow ) {
		
		int key = time.getMonthNumber();
				
		if ( _Cashflows.containsKey( key ) ) {
			
			_Cashflows.put( key , _Cashflows.get( key ) + cashflow );
		}
		else _Cashflows.put( key, cashflow );
	}
	
	public void Clear() {
		
		_Cashflows.clear();
	}
	
	@XmlAttribute
	public double getIRR() {
		
		int iter = 0;
		double x = 0.01;
		final int MAX_ITER = 20;
		final double EPSILON = 0.0000001;
		
		// Iteratively uses the Newton Raphson method  http://en.wikipedia.org/wiki/Newton's_method
		// to solve for the rate of interest for which the equation of value is zero this is possible 
		// because the equation of value is a power series so its derivative is also a power series of a 
		// lesser degree.  Efficiency is good because convergence is quadratic - the difference between the root 
		// and the approximation is squared thus the number of digits doubles with each iteration.
		while ( iter++ < MAX_ITER ) {
		
			double fx = 0.0;
			double dfx = 0.0;
			final double x1 = 1.0 + x;
				
			for ( Map.Entry<Integer, Double> CF : _Cashflows.entrySet() ) {	
								
				final double v = CF.getValue();
				
				final double x1_i = Math.pow( x1, CF.getKey() );
				
				fx += v / x1_i;
				
				final double x1_i1 = x1_i * x1;
				
				dfx += ( -CF.getKey() ) * v / x1_i1;
			}
			
			final double new_x = x - fx / dfx; 
			
			final double epsilon = Math.abs( new_x - x );

			if ( epsilon <= EPSILON ) {
			
				if ( x == 0.0 && Math.abs( new_x ) <= EPSILON ) {
			
					return 0.0; 
				}
				else {
					return Math.pow( 1 + new_x, 12 ) - 1.0;
				}
			}
			x = new_x;
		}
		return Math.pow( 1 + x, 12 ) - 1.0;
	}
}