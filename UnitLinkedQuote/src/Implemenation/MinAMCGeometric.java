package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public final class MinAMCGeometric extends Charge implements Observer {

	private double _GrossReturn;
	private double _AMC;
	private Events _Freq;
	private double _Min;
	private double _MonthlyChargeRate;
	
	MinAMCGeometric(double grossreturn, double amc, Events freq, String name, double min) {
		
		this._GrossReturn = grossreturn;
		
		this._AMC = amc;
		
		this._Freq = freq;
		
		super._Name = name;
		
		this._Min = min;
				
		_MonthlyChargeRate = Math.pow( 1 + _GrossReturn , 1.0 / 12 ) - Math.pow( 1 + _GrossReturn - _AMC, 1.0 / 12 );
		
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
			
			ChargeAmount = ( fund.Total() / funds.Total() ) * Math.pow( 1 + _GrossReturn, - 1.0 / 12 ) * _MonthlyChargeRate;
			
			ChargeAmount = Math.max( ChargeAmount,  _Min / 12.0 );
			
			StoreCharge( FundName, ChargeAmount );
				
		}
		else ChargeAmount = 0;
					
		return ChargeAmount;
		
	}
	
}