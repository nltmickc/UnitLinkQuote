package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;
import UnitLinkedQuote.Tick;

public final class EscalatingCharge extends Charge implements Observer  {
		
	private Events _Freq;
	private double _EscRate;
	private double _InitialChargeAmount;
	private double _CurrentChargeAmount;
				
	public EscalatingCharge( double InitialCharge, double AnnualEscRate, Events freq, String name ) {
		
		this._InitialChargeAmount = InitialCharge;
		
		this._EscRate = AnnualEscRate;
		
		this._Freq = freq;
		
		super._Name = name;
		
		super._ChargeSchedule = new ChargeSchedule ( name );
	}
	
	@Override
	public void Update(Tick tick) {
		
		super.Update( tick );
			
		if ( super._Tick.getEvent() == Events.ANNUAL ) {
			
			_CurrentChargeAmount = _InitialChargeAmount * Math.pow( ( 1 + _EscRate ), tick.getYear() );
		}
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
			
			ChargeAmount = ( fund.Total() / funds.Total() ) * _CurrentChargeAmount;
			
			StoreCharge( FundName, ChargeAmount );
				
		}
		else 
			ChargeAmount = 0;
				
		return ChargeAmount;
	}
}