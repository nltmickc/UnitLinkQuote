package UnitLinkedQuote;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement()
public class Fund implements Observer {
	
	@XmlAttribute
	private String _Name;
	@XmlTransient
	private double _FundValue;
	@XmlAttribute
	private double _InvestPerCent;
	@XmlElement( name = "BidOfferSpread" )
	private BidOfferSpread _BidOfferSpread;
	@XmlTransient
	private Premiums _Premiums;
	@XmlTransient
	private Charges _Charges;
	@XmlTransient
	private Tick _Tick;
	@XmlAttribute
	private double _GrossMonthlyGrowthRate;
	@XmlTransient
	private Map<String, Double> _ChargeAmounts = new HashMap<String, Double>();
	@XmlElementWrapper( name = "StartMonthFundValue" )
	private Map<Integer, Double> _StartMonthFundValues = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "EndMonthFundValues" )
	private Map<Integer, Double> _EndMonthFundValues = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyInvRetAmounts" )
	private Map<Integer, Double> _InvRetAmounts = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyChargeAmounts" )
	private Map<Integer, Double> _MonthlyChargeAmounts = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyPremiumAmounts" )
	private Map<Integer, Double> _MonthlyPremiumAmounts = new HashMap<Integer, Double>();
		
	public Fund() {}
	 			
	public Fund( String Name, double GRY, double InvPC, BidOfferSpread BOS ) {
						
		_Name = Name;

		_InvestPerCent = InvPC;
		
		_GrossMonthlyGrowthRate = Math.pow( 1 + GRY, 1.0 / 12 ) - 1;
		
		_BidOfferSpread = BOS;
	}
		
	public String getName() {
		
		return _Name;
	}
	
	public void Charges( Charges charges ) {
	
		this._Charges = charges;
	}
	
	public void Premiums( Premiums premiums ) {
	
		this._Premiums = premiums;
	}
		
	public void ApplyPremiums() {
		
		double GrossPremium;
		double NetPremium;
		
		for  ( Premium p : _Premiums ) {
			
			GrossPremium = p.getPremium() * _InvestPerCent;
						
			NetPremium = p.Apply() * _InvestPerCent * _BidOfferSpread.getBidOfferSpread();
			
			UpdateFundPremiumSchedule( GrossPremium );
			
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
						
			_FundValue += _InvRet;
			
			System.out.println("\t\t\t    " + _Name + " Inv Ret: " + _InvRet);
			
			_InvRetAmounts.put( Month,_InvRet );
			
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
		
		//_ChargeAmounts.clear();
		
		for ( Charge c : _Charges ) {
			
			System.out.println("\t\t\t    " + _Name + " Before Charge : " + c.getName() + " : " + _FundValue );
						
			Charge = c.Apply( this, funds );
			
			_ChargeAmounts.put( c.getName(), Charge );
			
			System.out.println( "\t\t\t\t" + _Name + " Charge : " + c.getName() + ", Amount: " + Charge );
		}
	}
	
	//...then deduct previously calculated charges that are stored in the HashMap...
	public void DeductCharges () {
		
		double Temp = 0;
		
		for ( Map.Entry<String, Double> CA  : _ChargeAmounts.entrySet() ) {
			
			Temp += CA.getValue();;
			
		}
		
		_MonthlyChargeAmounts.put( _Tick.getMonthNumber(), Temp );
		
		_FundValue -= Temp;
		
		System.out.println( "\t\t\t    " + _Name + " After Charges : " + _FundValue );
	}	
		
	@Override
	public void Update( Tick tick ) {
		
		_Tick = tick;
		
		_ChargeAmounts.clear();
		
		if (_Tick.getEvent() == Events.INITIAL) {
			
			_FundValue = 0;
			
			_StartMonthFundValues.clear();
						
			_InvRetAmounts.clear();
			
			_EndMonthFundValues.clear();
			
		}
						
		if ( _Tick.getEvent() == Events.FINAL) {

			Cashflows.Instance().AddCashflow(tick, - _FundValue );
			
		}
		System.out.println("\t\tFund Update " + _Name + " " + tick.getEvent() );
	}
	
	public double Total() {
		
		return _FundValue;
	}
	
	public double Total( int Month ) {
		
		return _EndMonthFundValues.get( Month - 1 );
	}
	
	public double TotalInvRet() {
		
		double _Temp = 0;
		
		for ( Map.Entry<Integer, Double> IRA : _InvRetAmounts.entrySet() ) {
			
			_Temp += IRA.getValue();
		
		}
		
		return _Temp;
	}
	
	public double TotalFundGrossPrem() {
		
		double _Temp = 0;
		
		for ( Map.Entry<Integer, Double> MPA : _MonthlyPremiumAmounts.entrySet() ) {
			
			_Temp += MPA.getValue();
		
		}
		
		return _Temp;
	}
	
	public void SwitchOut( double Out, double InvPC ) {
		
		_FundValue -= Out;
		
		_InvestPerCent = InvPC;
	}
	
	public void SwitchIn( double In, double InvPC ) {
		
		_FundValue += In;
		
		_InvestPerCent = InvPC;
	}
	
	private void UpdateFundPremiumSchedule(double premiumamount ) {
		
		int key = _Tick.getMonthNumber();
		
		if ( _MonthlyPremiumAmounts.containsKey( key ) ) {
			
			_MonthlyPremiumAmounts.put( key , _MonthlyPremiumAmounts.get( key ) + premiumamount  );
		}
		else _MonthlyPremiumAmounts.put( key, premiumamount  );
	}
}