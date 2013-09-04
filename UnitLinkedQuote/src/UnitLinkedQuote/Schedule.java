package UnitLinkedQuote;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedule implements Subject {

	// Schedule is a Singleton
	private static Schedule _Instance = null; // once instance per type
	// the multiple lists ensure that the objects receive notifications 
	// in the right order - importnat as some quotes remove/replace objects.
	// Doing it this way ensures that all the premiums notified in order 
	// then the funds then the charges and finally the quote strategy and
	// fund switch - if set. 
	private List<Observer> _PremiumObservers = new ArrayList<Observer>();
	private List<Observer> _FundObservers = new ArrayList<Observer>();
	private List<Observer> _ChargeObservers = new ArrayList<Observer>();
	private Observer _QuoteStrategyObserver;
	private Observer _FundSwitchObserver;
			
	private int _TermInMonths;
	private int _CurrentYear;
	private int _CurrentMonth;
	private Calendar _Cal;
	private Tick _Tick;
	
	private Schedule() {}
	
	public static Schedule Instance() {
		
		if( _Instance == null ) {
			
			_Instance = new Schedule();
		}
		return _Instance;
	}
			
	public void Init( int terminmonths, Calendar PolStartDate ) {
		
		_Cal = Calendar.getInstance();
		
		_Cal = PolStartDate;
		
		_TermInMonths = terminmonths;
		
		_Tick = new Tick( _TermInMonths );
	}
	
	@Override
	public void registerObserver( Observer o ) {
		
		if ( o instanceof Premium ) {
			
			_PremiumObservers.add( o );
		}
		
		if ( o instanceof Fund ) {
			
			_FundObservers.add( o );
		}
		
		if ( o instanceof Charge ) {
			
			_ChargeObservers.add( o );
		}
				
		if ( o instanceof QuoteStrategy ) { //Can only have one quote strategy in observers collection.
			
			_QuoteStrategyObserver = o;
		}
		
		if ( o instanceof FundSwitch ) { //Can only have one fund switch in observers collection.
			
			_FundSwitchObserver = o;
		}
	}
	
	@Override
	public void removeObserver( Observer o ) {
		
		int i; 
		
		if ( o instanceof Premium ) {
			
			i = _PremiumObservers.indexOf( o );
			
			_PremiumObservers.remove( i );
		}
		
		if ( o instanceof Fund ) {
			
			i = _FundObservers.indexOf( o );
			
			_FundObservers.remove( i );
		}
		
		if ( o instanceof Charge ) {
			
			i = _ChargeObservers.indexOf( o );
			
			_ChargeObservers.remove( i );
		}
		
		if ( o instanceof QuoteStrategy ) {
			
			_QuoteStrategyObserver = null;
		}
		
		if ( o instanceof FundSwitch ) {
			
			_FundSwitchObserver = null;
		}
	}
		
	@Override
	public void notifyObservers( Tick tick ) {
		
		for ( Observer o : _PremiumObservers ) {
			
			o.Update( tick );
		}
		
		for ( Observer o : _ChargeObservers ) {
			
			o.Update( tick );
		}
		
		for ( Observer o : _FundObservers ) {
			
			o.Update( tick );
		}
		
		_QuoteStrategyObserver.Update( tick );
		
		if ( _FundSwitchObserver != null ) {
		
			_FundSwitchObserver.Update( tick );
		}
	}
			
	public void Run() {
		
		setTick( 0, 0, Events.INITIAL, _Cal );
										
		for( int i = 0; i < _TermInMonths; i++ ) {
			
			_CurrentYear = i / 12;
			
			_CurrentMonth = ( i % 12 ) + 1;
			
			if ( i == 0 ) {
				setTick(_CurrentYear, _CurrentMonth, Events.SINGLE, _Cal );
			}
			 
			if ( i % 12 == 0 ) {
				setTick(_CurrentYear, _CurrentMonth, Events.ANNUAL, _Cal );
			}
			
			setTick( _CurrentYear, _CurrentMonth, Events.MONTHLY, _Cal );
						
			_Cal.add( Calendar.MONTH, 1 );
		}

		setTick( _TermInMonths / 12, ( _TermInMonths % 12 ) + 1, Events.FINAL, _Cal );
	}
	
	private void setTick( int year, int month, Events e, Calendar curr ) {
		
		_Tick.Set( year, month, e, _Cal );
	
		notifyObservers( _Tick );
	}
}