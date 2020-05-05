
package com.doublechaintech.retailscm.consumerorderpriceadjustment;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.retailscm.RetailscmUserContext;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.BaseManager;
import com.doublechaintech.retailscm.SmartList;

public interface ConsumerOrderPriceAdjustmentManager extends BaseManager{

		

	public ConsumerOrderPriceAdjustment createConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String name,String bizOrderId,BigDecimal amount,String provider) throws Exception;
	public ConsumerOrderPriceAdjustment updateConsumerOrderPriceAdjustment(RetailscmUserContext userContext,String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ConsumerOrderPriceAdjustment loadConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderPriceAdjustmentId, String [] tokensExpr) throws Exception;
	public ConsumerOrderPriceAdjustment internalSaveConsumerOrderPriceAdjustment(RetailscmUserContext userContext, ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment) throws Exception;
	public ConsumerOrderPriceAdjustment internalSaveConsumerOrderPriceAdjustment(RetailscmUserContext userContext, ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment,Map<String,Object>option) throws Exception;

	public ConsumerOrderPriceAdjustment transferToAnotherBizOrder(RetailscmUserContext userContext, String consumerOrderPriceAdjustmentId, String anotherBizOrderId)  throws Exception;
 

	public void delete(RetailscmUserContext userContext, String consumerOrderPriceAdjustmentId, int version) throws Exception;
	public int deleteAll(RetailscmUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(RetailscmUserContext userContext, ConsumerOrderPriceAdjustment newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByBizOrder(RetailscmUserContext userContext,String bizOrderId) throws Exception;
	public Object listPageByBizOrder(RetailscmUserContext userContext,String bizOrderId, int start, int count) throws Exception;
  

}


