package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public final class MinAMCArithmetic extends Charge implements Observer {

	private Events _Freq;
	private double _Min;
	private double _MonthlyChargeRate;
	
	MinAMCArithmetic (double amc, Events freq, String name, double min) {
		
		this._Freq = freq;
		
		super._Name = name;
		
		this._Min = min;
				
		_MonthlyChargeRate = amc / 12.0;
		
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
			
			ChargeAmount = ( fund.Total() / funds.Total() ) * _MonthlyChargeRate;
			
			ChargeAmount = Math.max( ChargeAmount, _Min / 12.0 );
			
			StoreCharge( FundName, ChargeAmount );
				
		}
		else ChargeAmount = 0;
					
		return ChargeAmount;
		
	}
	
}