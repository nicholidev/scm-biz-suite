package com.doublechaintech.retailscm.salarygrade;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.retailscm.RetailscmObjectPlainCustomSerializer;
public class SalaryGradeSerializer extends RetailscmObjectPlainCustomSerializer<SalaryGrade>{

	@Override
	public void serialize(SalaryGrade salaryGrade, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, salaryGrade, provider);
		
	}
}


