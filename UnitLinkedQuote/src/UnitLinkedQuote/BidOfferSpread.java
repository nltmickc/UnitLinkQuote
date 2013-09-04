package UnitLinkedQuote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
public class BidOfferSpread {
	
	@XmlAttribute( name = "value")
	private double _BidOfferSpread;
	
	public BidOfferSpread() {
		
		_BidOfferSpread = 1.0;
	}

	public double getBidOfferSpread() {
		return _BidOfferSpread;
	}

	public void setBidOfferSpread( double BidOfferSpread ) {
		this._BidOfferSpread = BidOfferSpread;
	}
	
}