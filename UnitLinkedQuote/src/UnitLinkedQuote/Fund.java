package UnitLinkedQuote;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "Fund")
public abstract class Fund implements Observer {
	@XmlAttribute
	protected String _Name;
	@XmlTransient
	protected double _FundValue;
	@XmlAttribute
	protected double _InvestPerCent;
	@XmlElement( name = "BidOfferSpread" )
	protected BidOfferSpread _BidOfferSpread;
	@XmlTransient
	protected Premiums _Premiums;
	@XmlTransient
	protected Charges _Charges;
	@XmlTransient
	protected Tick _Tick;
	@XmlAttribute
	protected double _GrossMonthlyGrowthRate;
	@XmlTransient
	protected Map<String, Double> _ChargeAmounts = new HashMap<String, Double>();
	@XmlElementWrapper( name = "StartMonthFundValue" )
	protected Map<Integer, Double> _StartMonthFundValues = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "EndMonthFundValues" )
	protected Map<Integer, Double> _EndMonthFundValues = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyInvRetAmounts" )
	protected Map<Integer, Double> _InvRetAmounts = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyChargeAmounts" )
	protected Map<Integer, Double> _MonthlyChargeAmounts = new HashMap<Integer, Double>();
	@XmlElementWrapper( name = "MonthlyPremiumAmounts" )
	protected Map<Integer, Double> _MonthlyPremiumAmounts = new HashMap<Integer, Double>();
		
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
		
	//The next four routines implement the fund mechanism.
	public abstract void ApplyPremiums(); 
	
	public abstract void RollUp(); 
	
	public abstract void CalculateCharges( Funds funds );
		
	public abstract void DeductCharges ();
		
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
	
	protected void UpdateFundPremiumSchedule(double premiumamount ) {
		
		int key = _Tick.getMonthNumber();
		
		if ( _MonthlyPremiumAmounts.containsKey( key ) ) {
			
			_MonthlyPremiumAmounts.put( key , _MonthlyPremiumAmounts.get( key ) + premiumamount  );
		}
		else _MonthlyPremiumAmounts.put( key, premiumamount  );
	}
}