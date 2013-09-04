package UnitLinkedQuote;

public class RebalanceFundQuote implements QuoteStrategy, Observer {
	
	private Policy _Policy;
	private FundSwitch _FundSwitch;
	
	public RebalanceFundQuote( FundSwitch fs ) {
		
		_FundSwitch = fs;
		
		Schedule.Instance().registerObserver( this );
		
		Schedule.Instance().registerObserver( _FundSwitch );
	}

	@Override
	public void Quote( Policy p ) {
		
		_Policy = p;
		 
		Funds TempFunds = new Funds();
				
		_Policy.getFunds().Clear();
						
		for ( Fund f : _FundSwitch.getSourceFunds() ) {
			
			TempFunds.AddFund(f);
		}
		
		for ( Fund f : _FundSwitch.getDestinationFunds() ) {
			
			TempFunds.AddFund(f);
		}
		
		_Policy.setFunds( TempFunds );
	}

	@Override
	public void Update( Tick tick ) {
		
		_Policy.ApplyPremiums();
				
		_Policy.FundRollUp();
				
		_Policy.DeductCharges();
 
	}
}