package UnitLinkedQuote;

import java.text.DecimalFormat;

public class TargetFundQuote implements QuoteStrategy {
	
	private final double TOLERANCE = 0.005; 
	private final int MAX_ITERATIONS = 50;
	
	private Policy _Policy;
	private double _TargetFund;
	
	public TargetFundQuote( double target ) {
		
		_TargetFund = target;
	}
		
	@Override
	public void Quote( Policy p ) {
		
		_Policy = p;
		
		_Policy.setQuoteStrategy( new NormalQuote() );
		
		this.doBisectionAlgorithm(0, _TargetFund);
		
	}
	
	@Override
	public void Update(Tick tick) {
		// Do Nothing
	}
	
	//Test function for targeting algorithm
	private double F( double premium ) {
		
		_Policy.getPremiums().Clear();
		
		_Policy.getPremiums().AddPremium( new Premium( premium, Events.SINGLE, new Allocation(), "Single" ) );
		
		_Policy.Quote();
		
		return _Policy.getFunds().Total() - _TargetFund;
		
	}
	
	// This method implements a bisection algorithm with the specified starting and ending points.
	private double doBisectionAlgorithm( double x1, double x2 ) {
		
		int iterNum = 1;   
	    double f1 = 0, f2 = 0, fmid = 0;  /* function values */
	    double x1_old = 0, mid = 0;       /* points for function evaluation */
	    
	    /* to print numbers with 7 digits behind the decimal point */
	    DecimalFormat df = new DecimalFormat("0.0000000");
	    System.out.println("Iteration #\tX1\t\tX2\t\tX3\t\tF(X3)");

	    do {
	    	
	    	//Cuts down on number of evaluations of F(x) by using one of last values of F(x) from previous loop.  
	    	if (iterNum == 1 ) {
	    		
	    		// for first iteration evaluate the function at both end points
	    		f1 = F(x1); 
		        
	    		f2 = F(x2);
	    	}
	    	else {	// for future iterations we can reuse one of the previous values - just have to decide which one
	    		
	    		if (x1_old == x1) {
	    			
	    			f2 = F(x2); // f1 for iterNum is the same as (iterNum - 1) so don't evaluate: F(x) is a full policy roll-up so expensive  
	    			
	    		}
	    		else {
	    			f1 = F(x1);  // f2 for iterNum is the same as (iterNum - 1) as above 
	    		}
	    			    		
	    	} 
	    		        
	        if (f1 * f2 > 0) {   // can't do bisection
	        
	        	System.out.println("Values do not bracket a root");
	            
	        	break;            // give up
	        }
	        mid = (x1 + x2) / 2;  // bisection gives us the "average" of the point values
	         
	        fmid = F(mid); // evaluate function at midpoint
	         
	        System.out.println(iterNum + "\t\t" + df.format(x1) + "\t" + df.format(x2) + "\t" + df.format(mid) + "\t" + df.format(fmid));
	         
	         // determine next interval bound
	        if (fmid * f1 < 0) {
	        
	        	x2 = mid;
	        	x1_old = x1;
	        }
	        else {
	        	x1 = mid;
	        	//x2_old = x2;
	        }
	         
	        iterNum++;          // current iteration has been completed
	        
	    } while (Math.abs(x1 - x2) / 2 >= TOLERANCE && Math.abs(fmid) > TOLERANCE && iterNum <= MAX_ITERATIONS);
	    
	    // interval size minimum not reached yet and we haven't found a root and it's not time
	    // to give up yet
	    return mid;
	    
	}

}