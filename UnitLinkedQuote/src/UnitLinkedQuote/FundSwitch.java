package UnitLinkedQuote;

import Implemenation.LifeStyleStrategy;

public abstract class FundSwitch implements Observer {
	public abstract void setLifeStyleStrategy( LifeStyleStrategy LSS ); 
	public abstract Funds getSourceFunds();
	public abstract Funds getDestinationFunds();
	public abstract void GlidePathFunction();
}