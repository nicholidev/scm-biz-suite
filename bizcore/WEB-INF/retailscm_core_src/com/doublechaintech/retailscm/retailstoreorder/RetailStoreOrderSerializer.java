package com.doublechaintech.retailscm.retailstoreorder;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.retailscm.RetailscmObjectPlainCustomSerializer;
public class RetailStoreOrderSerializer extends RetailscmObjectPlainCustomSerializer<RetailStoreOrder>{

	@Override
	public void serialize(RetailStoreOrder retailStoreOrder, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, retailStoreOrder, provider);
		
	}
}


