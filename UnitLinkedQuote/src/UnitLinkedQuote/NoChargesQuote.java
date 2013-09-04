package UnitLinkedQuote;

public class NoChargesQuote implements QuoteStrategy {
	
	private Policy _Policy;
	
	public NoChargesQuote() {
						
		Schedule.Instance().registerObserver( this );
	}
	
	@Override
	public void Quote(Policy p) {
	
		_Policy = p;
		
		Charges PolicyCharges = _Policy.getCharges(); 
		
		for ( Charge c : PolicyCharges ) {
			
			if ( c.CompanyCharge() ) {
				
				PolicyCharges.RemoveCharge( c );
			}
		}
	}
	
	@Override
	public void Update(Tick tick) {
		
		System.out.println( "\tStart Quote " + tick.getEvent() + " Year : " + tick.getYear() + " Month : " + tick.getMonth() );
		
		System.out.println("\r\t\tApplyPremiums " + tick.getEvent()+ " Year : " + tick.getYear() + " Month : " + tick.getMonth() );
		_Policy.ApplyPremiums();
		
		System.out.println("\r\t\tFundRollup " + tick.getEvent() + " Year : " + tick.getYear() + " Month : " + tick.getMonth() );
		_Policy.FundRollUp();
		
		System.out.println("\r\t\tDeductCharges " + tick.getEvent() + " Year : " + tick.getYear() + " Month : " + tick.getMonth() );
		_Policy.DeductCharges();
		
		System.out.println("\r\tEnd Quote " + tick.getEvent() + " Year : " + tick.getYear() + " Month : " + tick.getMonth() + "\r");
	}
}