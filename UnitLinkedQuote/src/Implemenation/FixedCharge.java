package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public final class FixedCharge extends Charge implements Observer {
		
	private Events _Freq;
	private double _FixedChargeAmount;
							
	public FixedCharge( double amount, Events freq, String name ) {
		
		this._FixedChargeAmount = amount;
		
		this._Freq = freq;
		
		super._Name = name;
		
		super._ChargeSchedule = new ChargeSchedule ( name );
		 
	}
	
	@Override
	public boolean CompanyCharge() {
		
		return true;
	}
			
	@Override
	public double Apply( Fund fund, Funds funds ) {
		
		double ChargeAmount = 0;
		String FundName = fund.getName();
		
		if ( _Freq == super._Tick.getEvent() ) {
					
			ChargeAmount = ( fund.Total() / funds.Total() ) * _FixedChargeAmount;
			
			StoreCharge( FundName, ChargeAmount); 
															
		}
		else 
			ChargeAmount = 0;
				
		return ChargeAmount;
	}
		
}