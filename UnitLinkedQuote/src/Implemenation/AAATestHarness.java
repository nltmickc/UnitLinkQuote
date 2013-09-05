package Implemenation;

import java.text.*;
import java.util.*;

import xml.*;
import UnitLinkedQuote.*;

public class AAATestHarness {
	
	private static LifeStyleStrategies CPIStrategyLoad() {
		
		LifeStyleStrategies LSSs = new LifeStyleStrategies();

		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M14", "SAVB", "SB6F", 10 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M15", "SARB", "SAIF", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M16", "SARB", "SA6B", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M17", "SARB", "SA4B", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M18", "SBCC", "SAII", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M19", "SBCC", "SBHC", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M20", "SBCC", "SBGC", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M21", "SBJD", "SAIJ", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M22", "SBJD", "SBPD", 7 ) );
		LSSs.AddLifeStyleStrategy( new LifeStyleStrategy( "M23", "SBJD", "SBND", 7 ) );
				
		return LSSs;
	}
		
	private static Policy PolicyFactory(String ContractNumber ) {
		
		//ContractData _ContractData = new ContractData( ContractNumber );
				
		Calendar PolStartDate = new GregorianCalendar();
		
		PolStartDate.set(2013, Calendar.JANUARY, 1);
				
		//PolStartDate.setTime( _ContractData.get_ContractHeader().get_CURRFROM() );
		
		//colCovrpf temp = _ContractData.get_ContractComponents();
		
		//int TermInMonths = 0; 
		
//		for (Covrpf c : temp ) {
//			TermInMonths = c.getTermInMonths() ;
//		}
														
		Schedule.Instance().Init( 24, PolStartDate );
		
		//Add Premiums
		Premiums _Premiums = new Premiums();
				
		//_Premiums.AddPremium( new Premium( 10000, Events.SINGLE, new Allocation(), "Single" ) );
		
		_Premiums.AddPremium( new Premium( 300, Events.MONTHLY, new Allocation(), "Monthly" ) );
				
		//Add Funds
		Funds _Funds = new Funds();
														
			_Funds.AddFund( new MonthlyFund( "Fund 1", 0.08, 0.5, new BidOfferSpread() ) );
			
			_Funds.AddFund( new MonthlyFund( "Fund 2", 0.50, 0.5, new BidOfferSpread() ) );
							
		//Add charges
		Charges _Charges = new Charges();
			
			_Charges.AddCharge( new AMCArithmetic( 0.01, Events.MONTHLY, "AMC Geometric" ) );

			_Charges.AddCharge( new PensionsLevy() );
			
			_Charges.AddCharge( new FixedCharge( 10, Events.MONTHLY, "Fixed Charge" ) );
			
			_Charges.AddCharge( new ExitTax() );
										
		Policy _Policy = new Policy( ContractNumber, _Premiums, _Funds, _Charges );
		
		return _Policy;
	}
	
	public static void main(String[] args) throws Exception {
			
		final long startTime = System.currentTimeMillis();
		
		final boolean XML_OUTPUT = true;
		
		double NetRet = 0;
		double GrossRet = 0;
				
		NumberFormat PF = NumberFormat.getPercentInstance();
		
		PF.setMaximumFractionDigits(4);
		
		String ContractNumber = "15082816";
						
		//build the contract - add premiums, funds & charges to policy object 
		Policy _Policy = PolicyFactory( ContractNumber );  //re-factor into abstract factory later
		
		System.out.println("--------------First Quote Normal--------------------");
		System.out.println("");
		
		// Quotation methods are separated out into 
		//strategies - normal, zero charges, target etc.
		_Policy.setQuoteStrategy( new NormalQuote() );  
				
		_Policy.Quote();
		
		if ( XML_OUTPUT ) {
			
			xmlMarshaller.Marshal( _Policy );
		}
           			 		
		_Policy.Totals();
		
		GrossRet = Cashflows.Instance().getIRR();
		
//		System.out.println("");
//		System.out.println("-------------Second Quote No Charges --------------------");
//		System.out.println("");
//		
//		_Policy.setQuoteStrategy( new NoChargesQuote() );
//						
//		_Policy.Quote();
//		
//		_Policy.Totals();
//		
//		if ( XML_OUTPUT ) {
//			
//			xmlMarshaller.Marshal( _Policy );
//		}
//		
//		NetRet = Cashflows.Instance().getIRR();
//		
//		System.out.println("Reduction in yield: " + PF.format(NetRet - GrossRet) );
//		
//		System.out.println("");
//		System.out.println("--------------Third Quote Target Fund--------------------");
//		System.out.println("");
//		
//		final double TARGET_FUND = 10000.0;
//		
//		_Policy.setQuoteStrategy( new TargetFundQuote( TARGET_FUND ) );
//		
//		_Policy.Quote();
//		
//		_Policy.Totals();
//		
//		System.out.println("");
//		System.out.println("-------------Fourth Quote CPI Strategy--------------------");
//		System.out.println("");
//		
//		CPIFundSwitch CPI = new CPIFundSwitch();
//		
//		CPI.setLifeStyleStrategy( CPIStrategyLoad().getLifeStyleStrategy("M14") );
//						
//		QuoteStrategy _CPILifestyleStrategy = new RebalanceFundQuote( CPI );
//				
//		_Policy.setQuoteStrategy( _CPILifestyleStrategy );
//						
//		_Policy.Quote();
//		
//		_Policy.Totals();
//		
//		xmlMarshaller.Marshal( _Policy );
		
		//NetRet = Cashflows.Instance().getIRR();
		
		//System.out.println("Reduction in yield: " + PF.format( NetRet - GrossRet ) );
				
		final long endTime = System.currentTimeMillis();
		
		System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
	}
}