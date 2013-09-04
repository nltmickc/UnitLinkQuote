package Implemenation;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public final class ExitTax extends Charge implements Observer {
	
	private final String NAME = "Exit Tax"; 
	private final double RATE = 0.28;
		
	public ExitTax() {
		
		super._Name = NAME;
		
		super._ChargeSchedule = new ChargeSchedule ( NAME );
	}
	
	@Override
	public boolean CompanyCharge() {
		
		return false;
	}
		
	@Override
	public double Apply( Fund fund, Funds funds ) { 
		
		double ChargeAmount = 0;
		double ChargeBasedOn = 0;
		String FundName = fund.getName();
		int MonthNumber = super._Tick.getMonthNumber();
		
		//Every eight years or on maturity - based on rolling investment return
		if ( MonthNumber > 0 ) {
		
			if ( ( MonthNumber % 96 == 0 ) || ( super._Tick.getEvent() == Events.FINAL ) ) {
				
				ChargeBasedOn = Math.max(0, fund.Total() - fund.TotalFundGrossPrem() ); 
				
				ChargeAmount = ChargeBasedOn * RATE;
				
				ChargeAmount = Math.max( 0, ChargeAmount - super.Total( fund.getName() ) ); 
				
				StoreCharge( FundName, ChargeAmount );
				
			}
		}
		else ChargeAmount = 0;
					
		return ChargeAmount;
		
	}

}