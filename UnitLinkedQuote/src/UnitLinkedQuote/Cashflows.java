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
		//initial guess for i
		double i = 0.01;
		//Maximum number of iterations
		final int MAX_ITER = 20;
		//Tolerance of difference between two guesses that indicates a solution has been found
		final double EPSILON = 0.0000001;
		
		// Iteratively uses the Newton Raphson method  http://en.wikipedia.org/wiki/Newton's_method
		// to solve for the rate of interest for which the equation of value is zero this is possible 
		// because the equation of value is a power series so its derivative is also a power series of a 
		// lesser degree.  Efficiency is good because convergence is quadratic - the difference between  
		// the root and the approximation is squared thus the number of digits doubles with each iteration.
		while ( iter++ < MAX_ITER ) {
		
			double fx = 0.0;  //equation of value
			double dfx = 0.0; //derivative of equation of value
						
			//Set up the equation of value & its derivative
			for ( Map.Entry<Integer, Double> CF : _Cashflows.entrySet() ) {	
								
				final double CFt = CF.getValue();
				
				final double t = CF.getValue();
				
				final double vt = Math.pow( 1.0 + i, -t );
				
				fx += CFt * vt;
				
				final double vtplus1 = vt / ( 1.0 + i );
				
				dfx += ( -t ) * CFt * vtplus1;
			}
			
			final double new_i = i - fx / dfx;  //Newton-Raphson guess  
			
			final double epsilon = Math.abs( new_i - i );

			//Stop iteration if no solution found or tolerance has been reached
			if ( epsilon <= EPSILON ) {
			
				if ( i == 0.0 && Math.abs( new_i ) <= EPSILON ) {
			
					return 0.0; 
				}
				else {
					return Math.pow( 1 + new_i, 12 ) - 1.0;
				}
			}
			i = new_i;
		}
		return Math.pow( 1 + i, 12 ) - 1.0;
	}
}