package Implemenation;

import UnitLinkedQuote.BidOfferSpread;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.FundSwitch;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;
import UnitLinkedQuote.Schedule;
import UnitLinkedQuote.Tick;

public class CPIFundSwitch extends FundSwitch implements Observer {
	
	private int _SwitchTerm;
	private LifeStyleStrategy _LSS; 
	private Funds _Source = new Funds();
	private Funds _Destination = new Funds();
			
	@Override
	public void setLifeStyleStrategy(LifeStyleStrategy LSS) {
				
		this._LSS = LSS;
		
		Fund temp = new Fund( LSS.getSourceFund(), 0.09, 1.0, new BidOfferSpread() );
		
		_Source.AddFund( temp );
				
		Schedule.Instance().removeObserver( temp );
		
		temp = new Fund( LSS.getDestinationFund(), 0.09, 0.0, new BidOfferSpread() );
		
		_Destination.AddFund( temp );
		
		Schedule.Instance().removeObserver( temp );
		
		_SwitchTerm = _LSS.getFundingPeriod();
	}
		
	@Override
	public Funds getSourceFunds() {
		
		return _Source;
	}
	
	@Override
	public Funds getDestinationFunds() {

		return _Destination;
	}
	
	@Override
	public void GlidePathFunction() {

		double InvPC;
		double SwitchAmount;
		
		if ( _SwitchTerm > 0 ) {
		
			SwitchAmount = _Source.Total() / _SwitchTerm;
			
			InvPC = 1.0 / _SwitchTerm;  
		}
		else {
			SwitchAmount = 0;
			
			InvPC = 1.0;
		}
							
		System.out.println( "Switching out: " + SwitchAmount );
		
		for ( Fund f : _Source ) {
		 
			f.SwitchOut( SwitchAmount, 1 - InvPC );
		}
		
		for ( Fund f : _Destination ) {
			
			f.SwitchIn( SwitchAmount,  InvPC );
		}
		_SwitchTerm -= 1; 
	}
	
	@Override
	public void Update( Tick tick ) {
				
		if ( ( tick.getMonthsRemaining() < _SwitchTerm ) && ( tick.getEvent() == Events.MONTHLY ) ) {
		
			GlidePathFunction();
		}
	}
}
