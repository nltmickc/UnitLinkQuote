package Implemenation;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class LifeStyleStrategies implements Iterable<LifeStyleStrategy> {

	private Map<String, LifeStyleStrategy> _LifeStyleStrategies = new HashMap<String, LifeStyleStrategy>();

	public void AddLifeStyleStrategy(LifeStyleStrategy LifeStyleStrategy) {

		_LifeStyleStrategies.put( LifeStyleStrategy.getCode(), LifeStyleStrategy );
	}

	public void RemoveLifeStyleStrategy(LifeStyleStrategy LifeStyleStrategy) {

		_LifeStyleStrategies.remove( LifeStyleStrategy.getCode() );
	}

	public void Clear() {

		_LifeStyleStrategies.clear();
	}

	@Override
	public Iterator<LifeStyleStrategy> iterator() {
						
		return _LifeStyleStrategies.values().iterator(); 
	}
	
	public LifeStyleStrategy getLifeStyleStrategy(String Code) {
		
		return _LifeStyleStrategies.get(Code);
	}
}