package com.doublechaintech.retailscm.publicholiday;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.retailscm.RetailscmObjectPlainCustomSerializer;
public class PublicHolidaySerializer extends RetailscmObjectPlainCustomSerializer<PublicHoliday>{

	@Override
	public void serialize(PublicHoliday publicHoliday, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, publicHoliday, provider);
		
	}
}


