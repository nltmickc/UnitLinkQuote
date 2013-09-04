package xml;

import UnitLinkedQuote.Policy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class xmlMarshaller {
 
    public static void Marshal( Policy p ) throws IOException, JAXBException {
    	
    	BufferedWriter writer = new BufferedWriter( new FileWriter( "xmlOutput/" + p.getContractNo() + ".xml" ) );
        
    	JAXBContext context = JAXBContext.newInstance( Policy.class );
        
    	Marshaller m = context.createMarshaller();
        
    	m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        
    	m.marshal( p, writer );
        
    	writer.close();
    }
}