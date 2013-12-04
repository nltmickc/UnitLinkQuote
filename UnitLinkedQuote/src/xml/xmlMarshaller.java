package xml;

import UnitLinkedQuote.Policy;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class xmlMarshaller {
	
	public static Policy MarshalInput( ) throws IOException, JAXBException {
    		
    	JAXBContext jaxbContext = JAXBContext.newInstance( Policy.class );
    	
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    	Policy policy = (Policy) jaxbUnmarshaller.unmarshal( new FileReader( "xmlInput/in15082816.xml" ) );
    	
    	return policy;
    }
 
    public static void MarshalOutput( Policy p ) throws IOException, JAXBException {
    	
    	BufferedWriter writer = new BufferedWriter( new FileWriter( "xmlOutput/" + p.getContractNo() + ".xml" ) );
        
    	JAXBContext context = JAXBContext.newInstance( Policy.class );
        
    	Marshaller m = context.createMarshaller();
        
    	m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        
    	m.marshal( p, writer );
        
    	writer.close();
    }
}