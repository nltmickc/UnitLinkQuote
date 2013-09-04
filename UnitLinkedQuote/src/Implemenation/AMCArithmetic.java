package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public class AMCArithmetic extends Charge implements Observer {
	
	private Events _Freq;
	private double _MonthlyChargeRate;
		
	public AMCArithmetic (double percentcharge, Events freq, String name) {
		
		this._Freq = freq;
		
		this._MonthlyChargeRate = percentcharge / 12.0;
		
		super._Name = name;
		
		super._ChargeSchedule = new ChargeSchedule ( name );
	}
	
	@Override
	public boolean CompanyCharge() {
		
		return true;
	}
			
	@Override
	public double Apply(Fund fund, Funds funds) {
		
		double ChargeAmount;
		String FundName = fund.getName();
		
		if ( _Freq == super._Tick.getEvent() ) {
			
			ChargeAmount = fund.Total() * _MonthlyChargeRate;
			
			StoreCharge( FundName, ChargeAmount );
							
		}
		
		else 
			ChargeAmount = 0;
					
		return ChargeAmount;
		
	}
	
}
