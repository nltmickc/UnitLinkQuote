package UnitLinkedQuote;

public class NormalQuote implements QuoteStrategy {

	private Policy _Policy;
	
	public NormalQuote() {
						
		Schedule.Instance().registerObserver( this );
		
	}
	
	@Override
	public void Quote(Policy p) {
	
		_Policy = p;
		
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