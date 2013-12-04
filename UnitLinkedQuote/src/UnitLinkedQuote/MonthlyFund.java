package UnitLinkedQuote;

import java.util.Map;
import javax.xml.bind.annotation.*;

@XmlRootElement( name = "MonthlyFund" )
public class MonthlyFund extends Fund {

	public MonthlyFund(String Name, double GRY, double InvPC, BidOfferSpread BOS ) {

		super( Name, GRY, InvPC, BOS );
	}

	//The next four routines
	public void ApplyPremiums() {

		double GrossPremium;
		double NetPremium;

		for  ( Premium p : _Premiums ) {

			GrossPremium = p.getPremium() * _InvestPerCent;

			NetPremium = p.Apply() * _InvestPerCent * _BidOfferSpread.getBidOfferSpread();

			super.UpdateFundPremiumSchedule( GrossPremium );

			//IRR is based on the premium paid by the customer not allocated to fund.
			Cashflows.Instance().AddCashflow( _Tick, GrossPremium );

			_FundValue = _FundValue + NetPremium;

			System.out.println( "\t\t\t\tNet " + p.getName() + " Premium Amount : " + NetPremium + " in " + _Name);
		}
	}

	public void RollUp() {

		double _InvRet;

		if ( _Tick.getEvent() == Events.MONTHLY ) {

			int Month = _Tick.getMonthNumber(); 

			System.out.println("\t\t\t    " + _Name + " Start Month : " + _FundValue );

			_StartMonthFundValues.put( Month, _FundValue );

			_InvRet = _FundValue * _GrossMonthlyGrowthRate;;

			_InvRetAmounts.put( Month,_InvRet );

			_FundValue += _InvRet;

			System.out.println("\t\t\t    " + _Name + " Inv Ret: " + _InvRet);

			_EndMonthFundValues.put( Month, _FundValue ); 

			System.out.println("\t\t\t    " + _Name + " End Month : " + _FundValue );
		}
	}

	//Calculate charges - need to pass in funds because the charge my be fund specific 
	//we need to calculate all charges based on the month end fund value and then deduct them 
	//all - as the amount to be charged may be dependent on the fund value.  Thus the 
	//charge amounts would vary depending on the order that the charges are calculated in. 
	public void CalculateCharges( Funds funds ) {

		double Charge = 0;

		for ( Charge c : _Charges ) {

			System.out.println("\t\t\t    " + _Name + " Before Charge : " + c.getName() + " : " + _FundValue );

			Charge = c.Apply( this, funds );

			_ChargeAmounts.put( c.getName(), Charge );

			System.out.println( "\t\t\t\t" + _Name + " Charge : " + c.getName() + ", Amount: " + Charge );
		}
	}

	//...then deduct previously calculated charges that are stored in the HashMap _ChargeAmounts
	public void DeductCharges () {

		double Temp = 0;

		for ( Map.Entry<String, Double> CA  : _ChargeAmounts.entrySet() ) {

			Temp += CA.getValue();;

		}

		_MonthlyChargeAmounts.put( _Tick.getMonthNumber(), Temp );

		_FundValue -= Temp;

		System.out.println( "\t\t\t    " + _Name + " After Charges : " + _FundValue );
	}	
}
