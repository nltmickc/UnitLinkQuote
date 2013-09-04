package Implemenation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import UnitLinkedQuote.Charge;
import UnitLinkedQuote.ChargeSchedule;
import UnitLinkedQuote.Events;
import UnitLinkedQuote.Fund;
import UnitLinkedQuote.Funds;
import UnitLinkedQuote.Observer;

public class PensionsLevy extends Charge implements Observer {
	
	private final String NAME = "Pension Levy"; 
	private final double RATE = 0.006;
	private final Calendar LAST_CHARGE_DATE = new GregorianCalendar( 2014, Calendar.JUNE, 30 );
		
	public PensionsLevy() {
		
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
		String FundName = fund.getName();
		
		if ( super._Tick.getEvent() == Events.MONTHLY ) {
		
			if ( super._Tick.getCalendar().get( Calendar.MONTH ) == LAST_CHARGE_DATE.get( Calendar.MONTH ) ) {
							
				//only charge if before 2014
				if ( super._Tick.getCalendar().get( Calendar.YEAR ) <= LAST_CHARGE_DATE.get( Calendar.YEAR ) ) {
					
					ChargeAmount = RATE * fund.Total();
					
					StoreCharge( FundName, ChargeAmount );
				}
				
			}
		}
		
		else ChargeAmount = 0;
		
		return ChargeAmount;
	}
	
}