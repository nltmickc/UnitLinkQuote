package UnitLinkedQuote;

import java.text.NumberFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "Policy")
public class Policy {
	
	@XmlAttribute()
	private String _ContractNumber;
	@XmlElement(name = "Funds") 
	private Funds _Funds;
	@XmlElement(name = "Premiums")
	private Premiums _Premiums;
	@XmlElement(name = "Charges")
	private Charges _Charges;
	@XmlTransient
	private QuoteStrategy _QuoteStrategy;
	@XmlElement(name = "CashFlows")
	private Cashflows _CashFlows;
	
	public Policy() {}
				
	public Policy(String ContractNo, Premiums premiums, Funds funds, Charges charges ) {
		
		this._ContractNumber = ContractNo;
		
		this._Premiums = premiums;
				
		this._Funds = funds;
				
		this._Charges = charges;
		
		_Funds.setPremiums( _Premiums );
		
		_Funds.setCharges( _Charges );
		
		_CashFlows = Cashflows.Instance();
	}
	
	public void setQuoteStrategy( QuoteStrategy strategy ) {
	
		this._QuoteStrategy = strategy;
	}
	
	public void ApplyPremiums() {
		
		_Funds.ApplyPremiums();
	}
	
	public void FundRollUp() {
		
		_Funds.Rollup();
	}
	
	public void DeductCharges() {
		
		_Funds.DeductCharges();
	}
	
	public String getContractNo() {
		
		return this._ContractNumber;
	}
	
	public Premiums getPremiums() {
		
		return this._Premiums;
	}
	
	public Funds getFunds() {
		
		return this._Funds;
	}
	
	public void setFunds( Funds funds ) {
		
		this._Funds = funds;
		
		this._Funds.setPremiums( _Premiums );
		
		this._Funds.setCharges( _Charges );
		
	}
		
	public Charges getCharges() {
		
		return this._Charges;
	}
	
	public void Quote() {
		
		_CashFlows.Clear();
		
		_QuoteStrategy.Quote( this );
		
		Schedule.Instance().Run();
	}
		
	public void Totals() throws Exception {
		
		NumberFormat CF = NumberFormat.getCurrencyInstance();

		System.out.println( "**** Contract No : " + _ContractNumber + "****" );
		System.out.println( "* Premiums : " + CF.format( _Premiums.Total() ) );

		for ( Fund f : _Funds ) {

			System.out.println( "    " + f.getName() + ": " + CF.format( f.Total() ) + " Inv Ret : " + CF.format( f.TotalInvRet() ) );	
		}

		System.out.println( "* Funds    : " +  CF.format( _Funds.Total() ) );

		for ( Charge c : _Charges ) {

			System.out.println( "    " + c.getName() + ": " + CF.format( c.Total() ) );	
		}
		System.out.println( "* Charges  : " + CF.format( _Charges.Total() ) );

		CF = NumberFormat.getPercentInstance();

		CF.setMaximumFractionDigits(4);

		System.out.println( "Annual IRR: " + CF.format( Cashflows.Instance().getIRR() ) );

		System.out.println("**********************************************");
	}
}