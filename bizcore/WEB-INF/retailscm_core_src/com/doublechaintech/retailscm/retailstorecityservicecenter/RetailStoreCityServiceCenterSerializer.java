package com.doublechaintech.retailscm.retailstorecityservicecenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.retailscm.RetailscmObjectPlainCustomSerializer;

public class RetailStoreCityServiceCenterSerializer
    extends RetailscmObjectPlainCustomSerializer<RetailStoreCityServiceCenter> {

  @Override
  public void serialize(
      RetailStoreCityServiceCenter retailStoreCityServiceCenter,
      JsonGenerator jgen,
      SerializerProvider provider)
      throws IOException, JsonProcessingException {
    Map<String, Object> ctx = new HashMap<>();
    this.writeBaseEntityField(jgen, null, retailStoreCityServiceCenter, provider, ctx);
  }
}
