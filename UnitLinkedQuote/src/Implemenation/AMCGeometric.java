package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public class AMCGeometric extends Charge implements Observer {

	private Events _Freq;
	private double _GrossReturn;
	private double _AMC;
	private double _MonthlyChargeRate;
	
	public AMCGeometric( double grossreturn, double amc, Events freq, String name ) {
		
		this._GrossReturn = grossreturn;
		
		this._AMC = amc;
	
		this._Freq = freq;
				
		super._Name = name;
		
		this._MonthlyChargeRate = Math.pow(1 + _GrossReturn, 1.0 / 12 ) - Math.pow(1 + _GrossReturn - _AMC, 1.0 / 12 );
		
		super._ChargeSchedule = new ChargeSchedule ( name );
	}
	
	@Override
	public boolean CompanyCharge() {
		
		return true;
	}
		
	@Override
	public double Apply( Fund fund, Funds funds ) {
		
		double ChargeAmount;
		String FundName = fund.getName();
		
		if ( _Freq == super._Tick.getEvent() ) {
			
			ChargeAmount = fund.Total() * _MonthlyChargeRate;
			
			StoreCharge( FundName, ChargeAmount);
							
		}
		else 
			ChargeAmount = 0;
							
		return ChargeAmount;
	}
}	