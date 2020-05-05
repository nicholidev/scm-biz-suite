
package com.doublechaintech.retailscm.retailstoreorder;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;
import com.terapico.utils.MapUtil;
import com.terapico.utils.ListofUtils;
import com.terapico.utils.TextUtil;
import com.terapico.caf.BlobObject;
import com.terapico.caf.viewpage.SerializeScope;

import com.doublechaintech.retailscm.*;
import com.doublechaintech.retailscm.tree.*;
import com.doublechaintech.retailscm.treenode.*;
import com.doublechaintech.retailscm.RetailscmUserContextImpl;
import com.doublechaintech.retailscm.iamservice.*;
import com.doublechaintech.retailscm.services.IamService;
import com.doublechaintech.retailscm.secuser.SecUser;
import com.doublechaintech.retailscm.userapp.UserApp;
import com.doublechaintech.retailscm.BaseViewPage;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.retailscm.goods.Goods;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.retailstoreordershippinggroup.RetailStoreOrderShippingGroup;
import com.doublechaintech.retailscm.retailstoreorderpaymentgroup.RetailStoreOrderPaymentGroup;
import com.doublechaintech.retailscm.retailstoreorderlineitem.RetailStoreOrderLineItem;

import com.doublechaintech.retailscm.retailstorecountrycenter.CandidateRetailStoreCountryCenter;
import com.doublechaintech.retailscm.retailstore.CandidateRetailStore;

import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.supplyorder.SupplyOrder;
import com.doublechaintech.retailscm.goodsallocation.GoodsAllocation;
import com.doublechaintech.retailscm.sku.Sku;
import com.doublechaintech.retailscm.receivingspace.ReceivingSpace;
import com.doublechaintech.retailscm.smartpallet.SmartPallet;
import com.doublechaintech.retailscm.shippingspace.ShippingSpace;
import com.doublechaintech.retailscm.transporttask.TransportTask;
import com.doublechaintech.retailscm.retailstoreorder.RetailStoreOrder;






public class RetailStoreOrderManagerImpl extends CustomRetailscmCheckerManager implements RetailStoreOrderManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(RetailscmUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = RetailStoreOrderTokens.start().withTokenFromListName(listName).done();
		RetailStoreOrder  retailStoreOrder = (RetailStoreOrder) this.loadRetailStoreOrder(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = retailStoreOrder.collectRefercencesFromLists();
		retailStoreOrderDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, retailStoreOrder, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new RetailStoreOrderGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "RetailStoreOrder";
	@Override
	public RetailStoreOrderDAO daoOf(RetailscmUserContext userContext) {
		return retailStoreOrderDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws RetailStoreOrderManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new RetailStoreOrderManagerException(message);

	}



 	protected RetailStoreOrder saveRetailStoreOrder(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, String [] tokensExpr) throws Exception{	
 		//return getRetailStoreOrderDAO().save(retailStoreOrder, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveRetailStoreOrder(userContext, retailStoreOrder, tokens);
 	}
 	
 	protected RetailStoreOrder saveRetailStoreOrderDetail(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder) throws Exception{	

 		
 		return saveRetailStoreOrder(userContext, retailStoreOrder, allTokens());
 	}
 	
 	public RetailStoreOrder loadRetailStoreOrder(RetailscmUserContext userContext, String retailStoreOrderId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreOrderManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder( userContext, retailStoreOrderId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,retailStoreOrder, tokens);
 	}
 	
 	
 	 public RetailStoreOrder searchRetailStoreOrder(RetailscmUserContext userContext, String retailStoreOrderId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreOrderManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder( userContext, retailStoreOrderId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,retailStoreOrder, tokens);
 	}
 	
 	

 	protected RetailStoreOrder present(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,retailStoreOrder,tokens);
		
		
		RetailStoreOrder  retailStoreOrderToPresent = retailStoreOrderDaoOf(userContext).present(retailStoreOrder, tokens);
		
		List<BaseEntity> entityListToNaming = retailStoreOrderToPresent.collectRefercencesFromLists();
		retailStoreOrderDaoOf(userContext).alias(entityListToNaming);
		
		return  retailStoreOrderToPresent;
		
		
	}
 
 	
 	
 	public RetailStoreOrder loadRetailStoreOrderDetail(RetailscmUserContext userContext, String retailStoreOrderId) throws Exception{	
 		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder( userContext, retailStoreOrderId, allTokens());
 		return present(userContext,retailStoreOrder, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String retailStoreOrderId) throws Exception{	
 		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder( userContext, retailStoreOrderId, viewTokens());
 		return present(userContext,retailStoreOrder, allTokens());
		
 	}
 	protected RetailStoreOrder saveRetailStoreOrder(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, Map<String,Object>tokens) throws Exception{	
 		return retailStoreOrderDaoOf(userContext).save(retailStoreOrder, tokens);
 	}
 	protected RetailStoreOrder loadRetailStoreOrder(RetailscmUserContext userContext, String retailStoreOrderId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreOrderManagerException.class);

 
 		return retailStoreOrderDaoOf(userContext).load(retailStoreOrderId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, Map<String, Object> tokens){
		super.addActions(userContext, retailStoreOrder, tokens);
		
		addAction(userContext, retailStoreOrder, tokens,"@create","createRetailStoreOrder","createRetailStoreOrder/","main","primary");
		addAction(userContext, retailStoreOrder, tokens,"@update","updateRetailStoreOrder","updateRetailStoreOrder/"+retailStoreOrder.getId()+"/","main","primary");
		addAction(userContext, retailStoreOrder, tokens,"@copy","cloneRetailStoreOrder","cloneRetailStoreOrder/"+retailStoreOrder.getId()+"/","main","primary");
		
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.transfer_to_buyer","transferToAnotherBuyer","transferToAnotherBuyer/"+retailStoreOrder.getId()+"/","main","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.transfer_to_seller","transferToAnotherSeller","transferToAnotherSeller/"+retailStoreOrder.getId()+"/","main","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.addRetailStoreOrderLineItem","addRetailStoreOrderLineItem","addRetailStoreOrderLineItem/"+retailStoreOrder.getId()+"/","retailStoreOrderLineItemList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.removeRetailStoreOrderLineItem","removeRetailStoreOrderLineItem","removeRetailStoreOrderLineItem/"+retailStoreOrder.getId()+"/","retailStoreOrderLineItemList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.updateRetailStoreOrderLineItem","updateRetailStoreOrderLineItem","updateRetailStoreOrderLineItem/"+retailStoreOrder.getId()+"/","retailStoreOrderLineItemList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.copyRetailStoreOrderLineItemFrom","copyRetailStoreOrderLineItemFrom","copyRetailStoreOrderLineItemFrom/"+retailStoreOrder.getId()+"/","retailStoreOrderLineItemList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.addRetailStoreOrderShippingGroup","addRetailStoreOrderShippingGroup","addRetailStoreOrderShippingGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderShippingGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.removeRetailStoreOrderShippingGroup","removeRetailStoreOrderShippingGroup","removeRetailStoreOrderShippingGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderShippingGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.updateRetailStoreOrderShippingGroup","updateRetailStoreOrderShippingGroup","updateRetailStoreOrderShippingGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderShippingGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.copyRetailStoreOrderShippingGroupFrom","copyRetailStoreOrderShippingGroupFrom","copyRetailStoreOrderShippingGroupFrom/"+retailStoreOrder.getId()+"/","retailStoreOrderShippingGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.addRetailStoreOrderPaymentGroup","addRetailStoreOrderPaymentGroup","addRetailStoreOrderPaymentGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderPaymentGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.removeRetailStoreOrderPaymentGroup","removeRetailStoreOrderPaymentGroup","removeRetailStoreOrderPaymentGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderPaymentGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.updateRetailStoreOrderPaymentGroup","updateRetailStoreOrderPaymentGroup","updateRetailStoreOrderPaymentGroup/"+retailStoreOrder.getId()+"/","retailStoreOrderPaymentGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.copyRetailStoreOrderPaymentGroupFrom","copyRetailStoreOrderPaymentGroupFrom","copyRetailStoreOrderPaymentGroupFrom/"+retailStoreOrder.getId()+"/","retailStoreOrderPaymentGroupList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.addGoods","addGoods","addGoods/"+retailStoreOrder.getId()+"/","goodsList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.removeGoods","removeGoods","removeGoods/"+retailStoreOrder.getId()+"/","goodsList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.updateGoods","updateGoods","updateGoods/"+retailStoreOrder.getId()+"/","goodsList","primary");
		addAction(userContext, retailStoreOrder, tokens,"retail_store_order.copyGoodsFrom","copyGoodsFrom","copyGoodsFrom/"+retailStoreOrder.getId()+"/","goodsList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public RetailStoreOrder createRetailStoreOrder(RetailscmUserContext userContext, String buyerId,String sellerId,String title,BigDecimal totalAmount) throws Exception
	//public RetailStoreOrder createRetailStoreOrder(RetailscmUserContext userContext,String buyerId, String sellerId, String title, BigDecimal totalAmount) throws Exception
	{

		

		

		checkerOf(userContext).checkTitleOfRetailStoreOrder(title);
		checkerOf(userContext).checkTotalAmountOfRetailStoreOrder(totalAmount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


		RetailStoreOrder retailStoreOrder=createNewRetailStoreOrder();	

			
		RetailStore buyer = loadRetailStore(userContext, buyerId,emptyOptions());
		retailStoreOrder.setBuyer(buyer);
		
		
			
		RetailStoreCountryCenter seller = loadRetailStoreCountryCenter(userContext, sellerId,emptyOptions());
		retailStoreOrder.setSeller(seller);
		
		
		retailStoreOrder.setTitle(title);
		retailStoreOrder.setTotalAmount(totalAmount);
		retailStoreOrder.setLastUpdateTime(userContext.now());

		retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, emptyOptions());
		
		onNewInstanceCreated(userContext, retailStoreOrder);
		return retailStoreOrder;


	}
	protected RetailStoreOrder createNewRetailStoreOrder()
	{

		return new RetailStoreOrder();
	}

	protected void checkParamsForUpdatingRetailStoreOrder(RetailscmUserContext userContext,String retailStoreOrderId, int retailStoreOrderVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkVersionOfRetailStoreOrder( retailStoreOrderVersion);
		
		

				

		
		if(RetailStoreOrder.TITLE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTitleOfRetailStoreOrder(parseString(newValueExpr));
		
			
		}
		if(RetailStoreOrder.TOTAL_AMOUNT_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTotalAmountOfRetailStoreOrder(parseBigDecimal(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


	}



	public RetailStoreOrder clone(RetailscmUserContext userContext, String fromRetailStoreOrderId) throws Exception{

		return retailStoreOrderDaoOf(userContext).clone(fromRetailStoreOrderId, this.allTokens());
	}

	public RetailStoreOrder internalSaveRetailStoreOrder(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder) throws Exception
	{
		return internalSaveRetailStoreOrder(userContext, retailStoreOrder, allTokens());

	}
	public RetailStoreOrder internalSaveRetailStoreOrder(RetailscmUserContext userContext, RetailStoreOrder retailStoreOrder, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingRetailStoreOrder(userContext, retailStoreOrderId, retailStoreOrderVersion, property, newValueExpr, tokensExpr);


		synchronized(retailStoreOrder){
			//will be good when the retailStoreOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreOrder.
			if (retailStoreOrder.isChanged()){
			retailStoreOrder.updateLastUpdateTime(userContext.now());
			}
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, options);
			return retailStoreOrder;

		}

	}

	public RetailStoreOrder updateRetailStoreOrder(RetailscmUserContext userContext,String retailStoreOrderId, int retailStoreOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreOrder(userContext, retailStoreOrderId, retailStoreOrderVersion, property, newValueExpr, tokensExpr);



		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		if(retailStoreOrder.getVersion() != retailStoreOrderVersion){
			String message = "The target version("+retailStoreOrder.getVersion()+") is not equals to version("+retailStoreOrderVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(retailStoreOrder){
			//will be good when the retailStoreOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreOrder.
			retailStoreOrder.updateLastUpdateTime(userContext.now());
			retailStoreOrder.changeProperty(property, newValueExpr);
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			//return saveRetailStoreOrder(userContext, retailStoreOrder, tokens().done());
		}

	}

	public RetailStoreOrder updateRetailStoreOrderProperty(RetailscmUserContext userContext,String retailStoreOrderId, int retailStoreOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreOrder(userContext, retailStoreOrderId, retailStoreOrderVersion, property, newValueExpr, tokensExpr);

		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		if(retailStoreOrder.getVersion() != retailStoreOrderVersion){
			String message = "The target version("+retailStoreOrder.getVersion()+") is not equals to version("+retailStoreOrderVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(retailStoreOrder){
			//will be good when the retailStoreOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreOrder.

			retailStoreOrder.changeProperty(property, newValueExpr);
			retailStoreOrder.updateLastUpdateTime(userContext.now());
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			//return saveRetailStoreOrder(userContext, retailStoreOrder, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected RetailStoreOrderTokens tokens(){
		return RetailStoreOrderTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return RetailStoreOrderTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortRetailStoreOrderLineItemListWith("id","desc")
		.sortRetailStoreOrderShippingGroupListWith("id","desc")
		.sortRetailStoreOrderPaymentGroupListWith("id","desc")
		.sortGoodsListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return RetailStoreOrderTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherBuyer(RetailscmUserContext userContext, String retailStoreOrderId, String anotherBuyerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
 		checkerOf(userContext).checkIdOfRetailStore(anotherBuyerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

 	}
 	public RetailStoreOrder transferToAnotherBuyer(RetailscmUserContext userContext, String retailStoreOrderId, String anotherBuyerId) throws Exception
 	{
 		checkParamsForTransferingAnotherBuyer(userContext, retailStoreOrderId,anotherBuyerId);
 
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());	
		synchronized(retailStoreOrder){
			//will be good when the retailStoreOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStore buyer = loadRetailStore(userContext, anotherBuyerId, emptyOptions());		
			retailStoreOrder.updateBuyer(buyer);		
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, emptyOptions());
			
			return present(userContext,retailStoreOrder, allTokens());
			
		}

 	}

	


	public CandidateRetailStore requestCandidateBuyer(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStore result = new CandidateRetailStore();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStore> candidateList = retailStoreDaoOf(userContext).requestCandidateRetailStoreForRetailStoreOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherSeller(RetailscmUserContext userContext, String retailStoreOrderId, String anotherSellerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
 		checkerOf(userContext).checkIdOfRetailStoreCountryCenter(anotherSellerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

 	}
 	public RetailStoreOrder transferToAnotherSeller(RetailscmUserContext userContext, String retailStoreOrderId, String anotherSellerId) throws Exception
 	{
 		checkParamsForTransferingAnotherSeller(userContext, retailStoreOrderId,anotherSellerId);
 
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());	
		synchronized(retailStoreOrder){
			//will be good when the retailStoreOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreCountryCenter seller = loadRetailStoreCountryCenter(userContext, anotherSellerId, emptyOptions());		
			retailStoreOrder.updateSeller(seller);		
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, emptyOptions());
			
			return present(userContext,retailStoreOrder, allTokens());
			
		}

 	}

	


	public CandidateRetailStoreCountryCenter requestCandidateSeller(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStoreCountryCenter result = new CandidateRetailStoreCountryCenter();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStoreCountryCenter> candidateList = retailStoreCountryCenterDaoOf(userContext).requestCandidateRetailStoreCountryCenterForRetailStoreOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected RetailStore loadRetailStore(RetailscmUserContext userContext, String newBuyerId, Map<String,Object> options) throws Exception
 	{

 		return retailStoreDaoOf(userContext).load(newBuyerId, options);
 	}
 	


	

 	protected RetailStoreCountryCenter loadRetailStoreCountryCenter(RetailscmUserContext userContext, String newSellerId, Map<String,Object> options) throws Exception
 	{

 		return retailStoreCountryCenterDaoOf(userContext).load(newSellerId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String retailStoreOrderId, int retailStoreOrderVersion) throws Exception {
		//deleteInternal(userContext, retailStoreOrderId, retailStoreOrderVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String retailStoreOrderId, int retailStoreOrderVersion) throws Exception{

		retailStoreOrderDaoOf(userContext).delete(retailStoreOrderId, retailStoreOrderVersion);
	}

	public RetailStoreOrder forgetByAll(RetailscmUserContext userContext, String retailStoreOrderId, int retailStoreOrderVersion) throws Exception {
		return forgetByAllInternal(userContext, retailStoreOrderId, retailStoreOrderVersion);
	}
	protected RetailStoreOrder forgetByAllInternal(RetailscmUserContext userContext,
			String retailStoreOrderId, int retailStoreOrderVersion) throws Exception{

		return retailStoreOrderDaoOf(userContext).disconnectFromAll(retailStoreOrderId, retailStoreOrderVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new RetailStoreOrderManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return retailStoreOrderDaoOf(userContext).deleteAll();
	}


	//disconnect RetailStoreOrder with sku_id in RetailStoreOrderLineItem
	protected RetailStoreOrder breakWithRetailStoreOrderLineItemBySkuId(RetailscmUserContext userContext, String retailStoreOrderId, String skuIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveRetailStoreOrderLineItemListWithSkuId(retailStoreOrder, skuIdId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with sku in Goods
	protected RetailStoreOrder breakWithGoodsBySku(RetailscmUserContext userContext, String retailStoreOrderId, String skuId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithSku(retailStoreOrder, skuId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with receiving_space in Goods
	protected RetailStoreOrder breakWithGoodsByReceivingSpace(RetailscmUserContext userContext, String retailStoreOrderId, String receivingSpaceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithReceivingSpace(retailStoreOrder, receivingSpaceId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with goods_allocation in Goods
	protected RetailStoreOrder breakWithGoodsByGoodsAllocation(RetailscmUserContext userContext, String retailStoreOrderId, String goodsAllocationId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithGoodsAllocation(retailStoreOrder, goodsAllocationId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with smart_pallet in Goods
	protected RetailStoreOrder breakWithGoodsBySmartPallet(RetailscmUserContext userContext, String retailStoreOrderId, String smartPalletId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithSmartPallet(retailStoreOrder, smartPalletId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with shipping_space in Goods
	protected RetailStoreOrder breakWithGoodsByShippingSpace(RetailscmUserContext userContext, String retailStoreOrderId, String shippingSpaceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithShippingSpace(retailStoreOrder, shippingSpaceId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with transport_task in Goods
	protected RetailStoreOrder breakWithGoodsByTransportTask(RetailscmUserContext userContext, String retailStoreOrderId, String transportTaskId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithTransportTask(retailStoreOrder, transportTaskId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with retail_store in Goods
	protected RetailStoreOrder breakWithGoodsByRetailStore(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithRetailStore(retailStoreOrder, retailStoreId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}
	//disconnect RetailStoreOrder with biz_order in Goods
	protected RetailStoreOrder breakWithGoodsByBizOrder(RetailscmUserContext userContext, String retailStoreOrderId, String bizOrderId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());

			synchronized(retailStoreOrder){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreOrderDaoOf(userContext).planToRemoveGoodsListWithBizOrder(retailStoreOrder, bizOrderId, this.emptyOptions());

				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				return retailStoreOrder;
			}
	}






	protected void checkParamsForAddingRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId, String skuId, String skuName, BigDecimal amount, int quantity, String unitOfMeasurement,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);

		
		checkerOf(userContext).checkSkuIdOfRetailStoreOrderLineItem(skuId);
		
		checkerOf(userContext).checkSkuNameOfRetailStoreOrderLineItem(skuName);
		
		checkerOf(userContext).checkAmountOfRetailStoreOrderLineItem(amount);
		
		checkerOf(userContext).checkQuantityOfRetailStoreOrderLineItem(quantity);
		
		checkerOf(userContext).checkUnitOfMeasurementOfRetailStoreOrderLineItem(unitOfMeasurement);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


	}
	public  RetailStoreOrder addRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId, String skuId, String skuName, BigDecimal amount, int quantity, String unitOfMeasurement, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingRetailStoreOrderLineItem(userContext,retailStoreOrderId,skuId, skuName, amount, quantity, unitOfMeasurement,tokensExpr);

		RetailStoreOrderLineItem retailStoreOrderLineItem = createRetailStoreOrderLineItem(userContext,skuId, skuName, amount, quantity, unitOfMeasurement);

		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, emptyOptions());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.addRetailStoreOrderLineItem( retailStoreOrderLineItem );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderLineItemManager().onNewInstanceCreated(userContext, retailStoreOrderLineItem);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingRetailStoreOrderLineItemProperties(RetailscmUserContext userContext, String retailStoreOrderId,String id,String skuId,String skuName,BigDecimal amount,int quantity,String unitOfMeasurement,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderLineItem(id);

		checkerOf(userContext).checkSkuIdOfRetailStoreOrderLineItem( skuId);
		checkerOf(userContext).checkSkuNameOfRetailStoreOrderLineItem( skuName);
		checkerOf(userContext).checkAmountOfRetailStoreOrderLineItem( amount);
		checkerOf(userContext).checkQuantityOfRetailStoreOrderLineItem( quantity);
		checkerOf(userContext).checkUnitOfMeasurementOfRetailStoreOrderLineItem( unitOfMeasurement);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder updateRetailStoreOrderLineItemProperties(RetailscmUserContext userContext, String retailStoreOrderId, String id,String skuId,String skuName,BigDecimal amount,int quantity,String unitOfMeasurement, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreOrderLineItemProperties(userContext,retailStoreOrderId,id,skuId,skuName,amount,quantity,unitOfMeasurement,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withRetailStoreOrderLineItemListList()
				.searchRetailStoreOrderLineItemListWith(RetailStoreOrderLineItem.ID_PROPERTY, "is", id).done();

		RetailStoreOrder retailStoreOrderToUpdate = loadRetailStoreOrder(userContext, retailStoreOrderId, options);

		if(retailStoreOrderToUpdate.getRetailStoreOrderLineItemList().isEmpty()){
			throw new RetailStoreOrderManagerException("RetailStoreOrderLineItem is NOT FOUND with id: '"+id+"'");
		}

		RetailStoreOrderLineItem item = retailStoreOrderToUpdate.getRetailStoreOrderLineItemList().first();

		item.updateSkuId( skuId );
		item.updateSkuName( skuName );
		item.updateAmount( amount );
		item.updateQuantity( quantity );
		item.updateUnitOfMeasurement( unitOfMeasurement );


		//checkParamsForAddingRetailStoreOrderLineItem(userContext,retailStoreOrderId,name, code, used,tokensExpr);
		RetailStoreOrder retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrderToUpdate, tokens().withRetailStoreOrderLineItemList().done());
		synchronized(retailStoreOrder){
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}


	protected RetailStoreOrderLineItem createRetailStoreOrderLineItem(RetailscmUserContext userContext, String skuId, String skuName, BigDecimal amount, int quantity, String unitOfMeasurement) throws Exception{

		RetailStoreOrderLineItem retailStoreOrderLineItem = new RetailStoreOrderLineItem();
		
		
		retailStoreOrderLineItem.setSkuId(skuId);		
		retailStoreOrderLineItem.setSkuName(skuName);		
		retailStoreOrderLineItem.setAmount(amount);		
		retailStoreOrderLineItem.setQuantity(quantity);		
		retailStoreOrderLineItem.setUnitOfMeasurement(unitOfMeasurement);
	
		
		return retailStoreOrderLineItem;


	}

	protected RetailStoreOrderLineItem createIndexedRetailStoreOrderLineItem(String id, int version){

		RetailStoreOrderLineItem retailStoreOrderLineItem = new RetailStoreOrderLineItem();
		retailStoreOrderLineItem.setId(id);
		retailStoreOrderLineItem.setVersion(version);
		return retailStoreOrderLineItem;

	}

	protected void checkParamsForRemovingRetailStoreOrderLineItemList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderLineItemIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		for(String retailStoreOrderLineItemIdItem: retailStoreOrderLineItemIds){
			checkerOf(userContext).checkIdOfRetailStoreOrderLineItem(retailStoreOrderLineItemIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderLineItemList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderLineItemIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingRetailStoreOrderLineItemList(userContext, retailStoreOrderId,  retailStoreOrderLineItemIds, tokensExpr);


			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
			synchronized(retailStoreOrder){
				//Will be good when the retailStoreOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreOrderDaoOf(userContext).planToRemoveRetailStoreOrderLineItemList(retailStoreOrder, retailStoreOrderLineItemIds, allTokens());
				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
				deleteRelationListInGraph(userContext, retailStoreOrder.getRetailStoreOrderLineItemList());
				return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderLineItem(retailStoreOrderLineItemId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderLineItem(retailStoreOrderLineItemVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingRetailStoreOrderLineItem(userContext,retailStoreOrderId, retailStoreOrderLineItemId, retailStoreOrderLineItemVersion,tokensExpr);

		RetailStoreOrderLineItem retailStoreOrderLineItem = createIndexedRetailStoreOrderLineItem(retailStoreOrderLineItemId, retailStoreOrderLineItemVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.removeRetailStoreOrderLineItem( retailStoreOrderLineItem );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
			deleteRelationInGraph(userContext, retailStoreOrderLineItem);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderLineItem(retailStoreOrderLineItemId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderLineItem(retailStoreOrderLineItemVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder copyRetailStoreOrderLineItemFrom(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingRetailStoreOrderLineItem(userContext,retailStoreOrderId, retailStoreOrderLineItemId, retailStoreOrderLineItemVersion,tokensExpr);

		RetailStoreOrderLineItem retailStoreOrderLineItem = createIndexedRetailStoreOrderLineItem(retailStoreOrderLineItemId, retailStoreOrderLineItemVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			retailStoreOrder.copyRetailStoreOrderLineItemFrom( retailStoreOrderLineItem );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderLineItemManager().onNewInstanceCreated(userContext, (RetailStoreOrderLineItem)retailStoreOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderLineItem(retailStoreOrderLineItemId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderLineItem(retailStoreOrderLineItemVersion);
		

		if(RetailStoreOrderLineItem.SKU_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSkuIdOfRetailStoreOrderLineItem(parseString(newValueExpr));
		
		}
		
		if(RetailStoreOrderLineItem.SKU_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSkuNameOfRetailStoreOrderLineItem(parseString(newValueExpr));
		
		}
		
		if(RetailStoreOrderLineItem.AMOUNT_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAmountOfRetailStoreOrderLineItem(parseBigDecimal(newValueExpr));
		
		}
		
		if(RetailStoreOrderLineItem.QUANTITY_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkQuantityOfRetailStoreOrderLineItem(parseInt(newValueExpr));
		
		}
		
		if(RetailStoreOrderLineItem.UNIT_OF_MEASUREMENT_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkUnitOfMeasurementOfRetailStoreOrderLineItem(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}

	public  RetailStoreOrder updateRetailStoreOrderLineItem(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderLineItemId, int retailStoreOrderLineItemVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingRetailStoreOrderLineItem(userContext, retailStoreOrderId, retailStoreOrderLineItemId, retailStoreOrderLineItemVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withRetailStoreOrderLineItemList().searchRetailStoreOrderLineItemListWith(RetailStoreOrderLineItem.ID_PROPERTY, "eq", retailStoreOrderLineItemId).done();



		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, loadTokens);

		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreOrder.removeRetailStoreOrderLineItem( retailStoreOrderLineItem );
			//make changes to AcceleraterAccount.
			RetailStoreOrderLineItem retailStoreOrderLineItemIndex = createIndexedRetailStoreOrderLineItem(retailStoreOrderLineItemId, retailStoreOrderLineItemVersion);

			RetailStoreOrderLineItem retailStoreOrderLineItem = retailStoreOrder.findTheRetailStoreOrderLineItem(retailStoreOrderLineItemIndex);
			if(retailStoreOrderLineItem == null){
				throw new RetailStoreOrderManagerException(retailStoreOrderLineItem+" is NOT FOUND" );
			}

			retailStoreOrderLineItem.changeProperty(property, newValueExpr);
			
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderLineItemList().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId, String name, BigDecimal amount,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);

		
		checkerOf(userContext).checkNameOfRetailStoreOrderShippingGroup(name);
		
		checkerOf(userContext).checkAmountOfRetailStoreOrderShippingGroup(amount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


	}
	public  RetailStoreOrder addRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId, String name, BigDecimal amount, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingRetailStoreOrderShippingGroup(userContext,retailStoreOrderId,name, amount,tokensExpr);

		RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = createRetailStoreOrderShippingGroup(userContext,name, amount);

		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, emptyOptions());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.addRetailStoreOrderShippingGroup( retailStoreOrderShippingGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderShippingGroupList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderShippingGroupManager().onNewInstanceCreated(userContext, retailStoreOrderShippingGroup);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingRetailStoreOrderShippingGroupProperties(RetailscmUserContext userContext, String retailStoreOrderId,String id,String name,BigDecimal amount,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderShippingGroup(id);

		checkerOf(userContext).checkNameOfRetailStoreOrderShippingGroup( name);
		checkerOf(userContext).checkAmountOfRetailStoreOrderShippingGroup( amount);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder updateRetailStoreOrderShippingGroupProperties(RetailscmUserContext userContext, String retailStoreOrderId, String id,String name,BigDecimal amount, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreOrderShippingGroupProperties(userContext,retailStoreOrderId,id,name,amount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withRetailStoreOrderShippingGroupListList()
				.searchRetailStoreOrderShippingGroupListWith(RetailStoreOrderShippingGroup.ID_PROPERTY, "is", id).done();

		RetailStoreOrder retailStoreOrderToUpdate = loadRetailStoreOrder(userContext, retailStoreOrderId, options);

		if(retailStoreOrderToUpdate.getRetailStoreOrderShippingGroupList().isEmpty()){
			throw new RetailStoreOrderManagerException("RetailStoreOrderShippingGroup is NOT FOUND with id: '"+id+"'");
		}

		RetailStoreOrderShippingGroup item = retailStoreOrderToUpdate.getRetailStoreOrderShippingGroupList().first();

		item.updateName( name );
		item.updateAmount( amount );


		//checkParamsForAddingRetailStoreOrderShippingGroup(userContext,retailStoreOrderId,name, code, used,tokensExpr);
		RetailStoreOrder retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrderToUpdate, tokens().withRetailStoreOrderShippingGroupList().done());
		synchronized(retailStoreOrder){
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}


	protected RetailStoreOrderShippingGroup createRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String name, BigDecimal amount) throws Exception{

		RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = new RetailStoreOrderShippingGroup();
		
		
		retailStoreOrderShippingGroup.setName(name);		
		retailStoreOrderShippingGroup.setAmount(amount);
	
		
		return retailStoreOrderShippingGroup;


	}

	protected RetailStoreOrderShippingGroup createIndexedRetailStoreOrderShippingGroup(String id, int version){

		RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = new RetailStoreOrderShippingGroup();
		retailStoreOrderShippingGroup.setId(id);
		retailStoreOrderShippingGroup.setVersion(version);
		return retailStoreOrderShippingGroup;

	}

	protected void checkParamsForRemovingRetailStoreOrderShippingGroupList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderShippingGroupIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		for(String retailStoreOrderShippingGroupIdItem: retailStoreOrderShippingGroupIds){
			checkerOf(userContext).checkIdOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderShippingGroupList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderShippingGroupIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingRetailStoreOrderShippingGroupList(userContext, retailStoreOrderId,  retailStoreOrderShippingGroupIds, tokensExpr);


			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
			synchronized(retailStoreOrder){
				//Will be good when the retailStoreOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreOrderDaoOf(userContext).planToRemoveRetailStoreOrderShippingGroupList(retailStoreOrder, retailStoreOrderShippingGroupIds, allTokens());
				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderShippingGroupList().done());
				deleteRelationListInGraph(userContext, retailStoreOrder.getRetailStoreOrderShippingGroupList());
				return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingRetailStoreOrderShippingGroup(userContext,retailStoreOrderId, retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion,tokensExpr);

		RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = createIndexedRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.removeRetailStoreOrderShippingGroup( retailStoreOrderShippingGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderShippingGroupList().done());
			deleteRelationInGraph(userContext, retailStoreOrderShippingGroup);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder copyRetailStoreOrderShippingGroupFrom(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingRetailStoreOrderShippingGroup(userContext,retailStoreOrderId, retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion,tokensExpr);

		RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = createIndexedRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			retailStoreOrder.copyRetailStoreOrderShippingGroupFrom( retailStoreOrderShippingGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderShippingGroupList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderShippingGroupManager().onNewInstanceCreated(userContext, (RetailStoreOrderShippingGroup)retailStoreOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupVersion);
		

		if(RetailStoreOrderShippingGroup.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfRetailStoreOrderShippingGroup(parseString(newValueExpr));
		
		}
		
		if(RetailStoreOrderShippingGroup.AMOUNT_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAmountOfRetailStoreOrderShippingGroup(parseBigDecimal(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}

	public  RetailStoreOrder updateRetailStoreOrderShippingGroup(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderShippingGroupId, int retailStoreOrderShippingGroupVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingRetailStoreOrderShippingGroup(userContext, retailStoreOrderId, retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withRetailStoreOrderShippingGroupList().searchRetailStoreOrderShippingGroupListWith(RetailStoreOrderShippingGroup.ID_PROPERTY, "eq", retailStoreOrderShippingGroupId).done();



		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, loadTokens);

		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreOrder.removeRetailStoreOrderShippingGroup( retailStoreOrderShippingGroup );
			//make changes to AcceleraterAccount.
			RetailStoreOrderShippingGroup retailStoreOrderShippingGroupIndex = createIndexedRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupId, retailStoreOrderShippingGroupVersion);

			RetailStoreOrderShippingGroup retailStoreOrderShippingGroup = retailStoreOrder.findTheRetailStoreOrderShippingGroup(retailStoreOrderShippingGroupIndex);
			if(retailStoreOrderShippingGroup == null){
				throw new RetailStoreOrderManagerException(retailStoreOrderShippingGroup+" is NOT FOUND" );
			}

			retailStoreOrderShippingGroup.changeProperty(property, newValueExpr);
			
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderShippingGroupList().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId, String name, String cardNumber,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);

		
		checkerOf(userContext).checkNameOfRetailStoreOrderPaymentGroup(name);
		
		checkerOf(userContext).checkCardNumberOfRetailStoreOrderPaymentGroup(cardNumber);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


	}
	public  RetailStoreOrder addRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId, String name, String cardNumber, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingRetailStoreOrderPaymentGroup(userContext,retailStoreOrderId,name, cardNumber,tokensExpr);

		RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = createRetailStoreOrderPaymentGroup(userContext,name, cardNumber);

		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, emptyOptions());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.addRetailStoreOrderPaymentGroup( retailStoreOrderPaymentGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderPaymentGroupList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderPaymentGroupManager().onNewInstanceCreated(userContext, retailStoreOrderPaymentGroup);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingRetailStoreOrderPaymentGroupProperties(RetailscmUserContext userContext, String retailStoreOrderId,String id,String name,String cardNumber,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderPaymentGroup(id);

		checkerOf(userContext).checkNameOfRetailStoreOrderPaymentGroup( name);
		checkerOf(userContext).checkCardNumberOfRetailStoreOrderPaymentGroup( cardNumber);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder updateRetailStoreOrderPaymentGroupProperties(RetailscmUserContext userContext, String retailStoreOrderId, String id,String name,String cardNumber, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreOrderPaymentGroupProperties(userContext,retailStoreOrderId,id,name,cardNumber,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withRetailStoreOrderPaymentGroupListList()
				.searchRetailStoreOrderPaymentGroupListWith(RetailStoreOrderPaymentGroup.ID_PROPERTY, "is", id).done();

		RetailStoreOrder retailStoreOrderToUpdate = loadRetailStoreOrder(userContext, retailStoreOrderId, options);

		if(retailStoreOrderToUpdate.getRetailStoreOrderPaymentGroupList().isEmpty()){
			throw new RetailStoreOrderManagerException("RetailStoreOrderPaymentGroup is NOT FOUND with id: '"+id+"'");
		}

		RetailStoreOrderPaymentGroup item = retailStoreOrderToUpdate.getRetailStoreOrderPaymentGroupList().first();

		item.updateName( name );
		item.updateCardNumber( cardNumber );


		//checkParamsForAddingRetailStoreOrderPaymentGroup(userContext,retailStoreOrderId,name, code, used,tokensExpr);
		RetailStoreOrder retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrderToUpdate, tokens().withRetailStoreOrderPaymentGroupList().done());
		synchronized(retailStoreOrder){
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}


	protected RetailStoreOrderPaymentGroup createRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String name, String cardNumber) throws Exception{

		RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = new RetailStoreOrderPaymentGroup();
		
		
		retailStoreOrderPaymentGroup.setName(name);		
		retailStoreOrderPaymentGroup.setCardNumber(cardNumber);
	
		
		return retailStoreOrderPaymentGroup;


	}

	protected RetailStoreOrderPaymentGroup createIndexedRetailStoreOrderPaymentGroup(String id, int version){

		RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = new RetailStoreOrderPaymentGroup();
		retailStoreOrderPaymentGroup.setId(id);
		retailStoreOrderPaymentGroup.setVersion(version);
		return retailStoreOrderPaymentGroup;

	}

	protected void checkParamsForRemovingRetailStoreOrderPaymentGroupList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderPaymentGroupIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		for(String retailStoreOrderPaymentGroupIdItem: retailStoreOrderPaymentGroupIds){
			checkerOf(userContext).checkIdOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderPaymentGroupList(RetailscmUserContext userContext, String retailStoreOrderId,
			String retailStoreOrderPaymentGroupIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingRetailStoreOrderPaymentGroupList(userContext, retailStoreOrderId,  retailStoreOrderPaymentGroupIds, tokensExpr);


			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
			synchronized(retailStoreOrder){
				//Will be good when the retailStoreOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreOrderDaoOf(userContext).planToRemoveRetailStoreOrderPaymentGroupList(retailStoreOrder, retailStoreOrderPaymentGroupIds, allTokens());
				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderPaymentGroupList().done());
				deleteRelationListInGraph(userContext, retailStoreOrder.getRetailStoreOrderPaymentGroupList());
				return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingRetailStoreOrderPaymentGroup(userContext,retailStoreOrderId, retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion,tokensExpr);

		RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = createIndexedRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.removeRetailStoreOrderPaymentGroup( retailStoreOrderPaymentGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderPaymentGroupList().done());
			deleteRelationInGraph(userContext, retailStoreOrderPaymentGroup);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder copyRetailStoreOrderPaymentGroupFrom(RetailscmUserContext userContext, String retailStoreOrderId,
		String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingRetailStoreOrderPaymentGroup(userContext,retailStoreOrderId, retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion,tokensExpr);

		RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = createIndexedRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			retailStoreOrder.copyRetailStoreOrderPaymentGroupFrom( retailStoreOrderPaymentGroup );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderPaymentGroupList().done());
			
			userContext.getManagerGroup().getRetailStoreOrderPaymentGroupManager().onNewInstanceCreated(userContext, (RetailStoreOrderPaymentGroup)retailStoreOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupVersion);
		

		if(RetailStoreOrderPaymentGroup.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfRetailStoreOrderPaymentGroup(parseString(newValueExpr));
		
		}
		
		if(RetailStoreOrderPaymentGroup.CARD_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCardNumberOfRetailStoreOrderPaymentGroup(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}

	public  RetailStoreOrder updateRetailStoreOrderPaymentGroup(RetailscmUserContext userContext, String retailStoreOrderId, String retailStoreOrderPaymentGroupId, int retailStoreOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingRetailStoreOrderPaymentGroup(userContext, retailStoreOrderId, retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withRetailStoreOrderPaymentGroupList().searchRetailStoreOrderPaymentGroupListWith(RetailStoreOrderPaymentGroup.ID_PROPERTY, "eq", retailStoreOrderPaymentGroupId).done();



		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, loadTokens);

		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreOrder.removeRetailStoreOrderPaymentGroup( retailStoreOrderPaymentGroup );
			//make changes to AcceleraterAccount.
			RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroupIndex = createIndexedRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupId, retailStoreOrderPaymentGroupVersion);

			RetailStoreOrderPaymentGroup retailStoreOrderPaymentGroup = retailStoreOrder.findTheRetailStoreOrderPaymentGroup(retailStoreOrderPaymentGroupIndex);
			if(retailStoreOrderPaymentGroup == null){
				throw new RetailStoreOrderManagerException(retailStoreOrderPaymentGroup+" is NOT FOUND" );
			}

			retailStoreOrderPaymentGroup.changeProperty(property, newValueExpr);
			
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withRetailStoreOrderPaymentGroupList().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingGoods(RetailscmUserContext userContext, String retailStoreOrderId, String name, String rfid, String uom, int maxPackage, Date expireTime, String skuId, String receivingSpaceId, String goodsAllocationId, String smartPalletId, String shippingSpaceId, String transportTaskId, String retailStoreId, String bizOrderId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);

		
		checkerOf(userContext).checkNameOfGoods(name);
		
		checkerOf(userContext).checkRfidOfGoods(rfid);
		
		checkerOf(userContext).checkUomOfGoods(uom);
		
		checkerOf(userContext).checkMaxPackageOfGoods(maxPackage);
		
		checkerOf(userContext).checkExpireTimeOfGoods(expireTime);
		
		checkerOf(userContext).checkSkuIdOfGoods(skuId);
		
		checkerOf(userContext).checkReceivingSpaceIdOfGoods(receivingSpaceId);
		
		checkerOf(userContext).checkGoodsAllocationIdOfGoods(goodsAllocationId);
		
		checkerOf(userContext).checkSmartPalletIdOfGoods(smartPalletId);
		
		checkerOf(userContext).checkShippingSpaceIdOfGoods(shippingSpaceId);
		
		checkerOf(userContext).checkTransportTaskIdOfGoods(transportTaskId);
		
		checkerOf(userContext).checkRetailStoreIdOfGoods(retailStoreId);
		
		checkerOf(userContext).checkBizOrderIdOfGoods(bizOrderId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);


	}
	public  RetailStoreOrder addGoods(RetailscmUserContext userContext, String retailStoreOrderId, String name, String rfid, String uom, int maxPackage, Date expireTime, String skuId, String receivingSpaceId, String goodsAllocationId, String smartPalletId, String shippingSpaceId, String transportTaskId, String retailStoreId, String bizOrderId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingGoods(userContext,retailStoreOrderId,name, rfid, uom, maxPackage, expireTime, skuId, receivingSpaceId, goodsAllocationId, smartPalletId, shippingSpaceId, transportTaskId, retailStoreId, bizOrderId,tokensExpr);

		Goods goods = createGoods(userContext,name, rfid, uom, maxPackage, expireTime, skuId, receivingSpaceId, goodsAllocationId, smartPalletId, shippingSpaceId, transportTaskId, retailStoreId, bizOrderId);

		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, emptyOptions());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.addGoods( goods );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
			
			userContext.getManagerGroup().getGoodsManager().onNewInstanceCreated(userContext, goods);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingGoodsProperties(RetailscmUserContext userContext, String retailStoreOrderId,String id,String name,String rfid,String uom,int maxPackage,Date expireTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfGoods(id);

		checkerOf(userContext).checkNameOfGoods( name);
		checkerOf(userContext).checkRfidOfGoods( rfid);
		checkerOf(userContext).checkUomOfGoods( uom);
		checkerOf(userContext).checkMaxPackageOfGoods( maxPackage);
		checkerOf(userContext).checkExpireTimeOfGoods( expireTime);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder updateGoodsProperties(RetailscmUserContext userContext, String retailStoreOrderId, String id,String name,String rfid,String uom,int maxPackage,Date expireTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGoodsProperties(userContext,retailStoreOrderId,id,name,rfid,uom,maxPackage,expireTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withGoodsListList()
				.searchGoodsListWith(Goods.ID_PROPERTY, "is", id).done();

		RetailStoreOrder retailStoreOrderToUpdate = loadRetailStoreOrder(userContext, retailStoreOrderId, options);

		if(retailStoreOrderToUpdate.getGoodsList().isEmpty()){
			throw new RetailStoreOrderManagerException("Goods is NOT FOUND with id: '"+id+"'");
		}

		Goods item = retailStoreOrderToUpdate.getGoodsList().first();

		item.updateName( name );
		item.updateRfid( rfid );
		item.updateUom( uom );
		item.updateMaxPackage( maxPackage );
		item.updateExpireTime( expireTime );


		//checkParamsForAddingGoods(userContext,retailStoreOrderId,name, code, used,tokensExpr);
		RetailStoreOrder retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrderToUpdate, tokens().withGoodsList().done());
		synchronized(retailStoreOrder){
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}
	}


	protected Goods createGoods(RetailscmUserContext userContext, String name, String rfid, String uom, int maxPackage, Date expireTime, String skuId, String receivingSpaceId, String goodsAllocationId, String smartPalletId, String shippingSpaceId, String transportTaskId, String retailStoreId, String bizOrderId) throws Exception{

		Goods goods = new Goods();
		
		
		goods.setName(name);		
		goods.setRfid(rfid);		
		goods.setUom(uom);		
		goods.setMaxPackage(maxPackage);		
		goods.setExpireTime(expireTime);		
		Sku  sku = new Sku();
		sku.setId(skuId);		
		goods.setSku(sku);		
		ReceivingSpace  receivingSpace = new ReceivingSpace();
		receivingSpace.setId(receivingSpaceId);		
		goods.setReceivingSpace(receivingSpace);		
		GoodsAllocation  goodsAllocation = new GoodsAllocation();
		goodsAllocation.setId(goodsAllocationId);		
		goods.setGoodsAllocation(goodsAllocation);		
		SmartPallet  smartPallet = new SmartPallet();
		smartPallet.setId(smartPalletId);		
		goods.setSmartPallet(smartPallet);		
		ShippingSpace  shippingSpace = new ShippingSpace();
		shippingSpace.setId(shippingSpaceId);		
		goods.setShippingSpace(shippingSpace);		
		TransportTask  transportTask = new TransportTask();
		transportTask.setId(transportTaskId);		
		goods.setTransportTask(transportTask);		
		RetailStore  retailStore = new RetailStore();
		retailStore.setId(retailStoreId);		
		goods.setRetailStore(retailStore);		
		SupplyOrder  bizOrder = new SupplyOrder();
		bizOrder.setId(bizOrderId);		
		goods.setBizOrder(bizOrder);
	
		
		return goods;


	}

	protected Goods createIndexedGoods(String id, int version){

		Goods goods = new Goods();
		goods.setId(id);
		goods.setVersion(version);
		return goods;

	}

	protected void checkParamsForRemovingGoodsList(RetailscmUserContext userContext, String retailStoreOrderId,
			String goodsIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		for(String goodsIdItem: goodsIds){
			checkerOf(userContext).checkIdOfGoods(goodsIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeGoodsList(RetailscmUserContext userContext, String retailStoreOrderId,
			String goodsIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingGoodsList(userContext, retailStoreOrderId,  goodsIds, tokensExpr);


			RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
			synchronized(retailStoreOrder){
				//Will be good when the retailStoreOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreOrderDaoOf(userContext).planToRemoveGoodsList(retailStoreOrder, goodsIds, allTokens());
				retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
				deleteRelationListInGraph(userContext, retailStoreOrder.getGoodsList());
				return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingGoods(RetailscmUserContext userContext, String retailStoreOrderId,
		String goodsId, int goodsVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfGoods(goodsId);
		checkerOf(userContext).checkVersionOfGoods(goodsVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder removeGoods(RetailscmUserContext userContext, String retailStoreOrderId,
		String goodsId, int goodsVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingGoods(userContext,retailStoreOrderId, goodsId, goodsVersion,tokensExpr);

		Goods goods = createIndexedGoods(goodsId, goodsVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreOrder.removeGoods( goods );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
			deleteRelationInGraph(userContext, goods);
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingGoods(RetailscmUserContext userContext, String retailStoreOrderId,
		String goodsId, int goodsVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreOrder( retailStoreOrderId);
		checkerOf(userContext).checkIdOfGoods(goodsId);
		checkerOf(userContext).checkVersionOfGoods(goodsVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}
	public  RetailStoreOrder copyGoodsFrom(RetailscmUserContext userContext, String retailStoreOrderId,
		String goodsId, int goodsVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingGoods(userContext,retailStoreOrderId, goodsId, goodsVersion,tokensExpr);

		Goods goods = createIndexedGoods(goodsId, goodsVersion);
		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, allTokens());
		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			retailStoreOrder.copyGoodsFrom( goods );
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
			
			userContext.getManagerGroup().getGoodsManager().onNewInstanceCreated(userContext, (Goods)retailStoreOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingGoods(RetailscmUserContext userContext, String retailStoreOrderId, String goodsId, int goodsVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreOrder(retailStoreOrderId);
		checkerOf(userContext).checkIdOfGoods(goodsId);
		checkerOf(userContext).checkVersionOfGoods(goodsVersion);
		

		if(Goods.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfGoods(parseString(newValueExpr));
		
		}
		
		if(Goods.RFID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkRfidOfGoods(parseString(newValueExpr));
		
		}
		
		if(Goods.UOM_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkUomOfGoods(parseString(newValueExpr));
		
		}
		
		if(Goods.MAX_PACKAGE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMaxPackageOfGoods(parseInt(newValueExpr));
		
		}
		
		if(Goods.EXPIRE_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkExpireTimeOfGoods(parseDate(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreOrderManagerException.class);

	}

	public  RetailStoreOrder updateGoods(RetailscmUserContext userContext, String retailStoreOrderId, String goodsId, int goodsVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingGoods(userContext, retailStoreOrderId, goodsId, goodsVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withGoodsList().searchGoodsListWith(Goods.ID_PROPERTY, "eq", goodsId).done();



		RetailStoreOrder retailStoreOrder = loadRetailStoreOrder(userContext, retailStoreOrderId, loadTokens);

		synchronized(retailStoreOrder){
			//Will be good when the retailStoreOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreOrder.removeGoods( goods );
			//make changes to AcceleraterAccount.
			Goods goodsIndex = createIndexedGoods(goodsId, goodsVersion);

			Goods goods = retailStoreOrder.findTheGoods(goodsIndex);
			if(goods == null){
				throw new RetailStoreOrderManagerException(goods+" is NOT FOUND" );
			}

			goods.changeProperty(property, newValueExpr);
			
			retailStoreOrder = saveRetailStoreOrder(userContext, retailStoreOrder, tokens().withGoodsList().done());
			return present(userContext,retailStoreOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(RetailscmUserContext userContext, RetailStoreOrder newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

	// -----------------------------------//  登录部分处理 \\-----------------------------------
	// 手机号+短信验证码 登录
	public Object loginByMobile(RetailscmUserContextImpl userContext, String mobile, String verifyCode) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(RetailscmBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByMobile");
		LoginData loginData = new LoginData();
		loginData.setMobile(mobile);
		loginData.setVerifyCode(verifyCode);

		LoginContext loginContext = LoginContext.of(LoginMethod.MOBILE, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 账号+密码登录
	public Object loginByPassword(RetailscmUserContextImpl userContext, String loginId, Password password) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(RetailscmBaseUtils.getRequestAppType(userContext), this.getBeanName(), "loginByPassword");
		LoginData loginData = new LoginData();
		loginData.setLoginId(loginId);
		loginData.setPassword(password.getClearTextPassword());

		LoginContext loginContext = LoginContext.of(LoginMethod.PASSWORD, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 微信小程序登录
	public Object loginByWechatMiniProgram(RetailscmUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(RetailscmBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 企业微信小程序登录
	public Object loginByWechatWorkMiniProgram(RetailscmUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(RetailscmBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatWorkMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_WORK_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 调用登录处理
	protected Object processLoginRequest(RetailscmUserContextImpl userContext, LoginContext loginContext) throws Exception {
		IamService iamService = (IamService) userContext.getBean("iamService");
		LoginResult loginResult = iamService.doLogin(userContext, loginContext, this);
		// 根据登录结果
		if (!loginResult.isAuthenticated()) {
			throw new Exception(loginResult.getMessage());
		}
		if (loginResult.isSuccess()) {
			return onLoginSuccess(userContext, loginResult);
		}
		if (loginResult.isNewUser()) {
			throw new Exception("请联系你的上级,先为你创建账号,然后再来登录.");
		}
		return new LoginForm();
	}

	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
		RetailscmUserContextImpl userContext = (RetailscmUserContextImpl)baseUserContext;
		IamService iamService = (IamService) userContext.getBean("iamService");
		Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);

		SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
		UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
		if (userApp != null) {
			userApp.setSecUser(secUser);
		}
		if (secUser == null) {
			iamService.onCheckAccessWhenAnonymousFound(userContext, loginInfo);
		}
		afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
		if (!isMethodNeedLogin(userContext, methodName, parameters)) {
			return accessOK();
		}

		return super.checkAccess(baseUserContext, methodName, parameters);
	}

	// 判断哪些接口需要登录后才能执行. 默认除了loginBy开头的,其他都要登录
	protected boolean isMethodNeedLogin(RetailscmUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("loginBy")) {
			return false;
		}
		if (methodName.startsWith("logout")) {
			return false;
		}
		return true;
	}

	// 在checkAccess中加载了secUser和userApp后会调用此方法,用于定制化的用户数据加载. 默认什么也不做
	protected void afterSecUserAppLoadedWhenCheckAccess(RetailscmUserContextImpl userContext, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) throws IllegalAccessException{
	}



	protected Object onLoginSuccess(RetailscmUserContext userContext, LoginResult loginResult) throws Exception {
		// by default, return the view of this object
		UserApp userApp = loginResult.getLoginContext().getLoginTarget().getUserApp();
		return this.view(userContext, userApp.getObjectId());
	}

	public void onAuthenticationFailed(RetailscmUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	// when user authenticated success, but no sec_user related, this maybe a new user login from 3-rd party service.
	public void onAuthenticateNewUserLogged(RetailscmUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// Generally speaking, when authenticated user logined, we will create a new account for him/her.
		// you need do it like :
		// First, you should create new data such as:
		//   RetailStoreOrder newRetailStoreOrder = this.createRetailStoreOrder(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newRetailStoreOrder
		//   UserApp uerApp = userAppManagerOf(userContext).createUserApp(userContext, secUser.getId(), ...
		// Also, set it into loginContext:
		//   loginContext.getLoginTarget().setUserApp(userApp);
		// Since many of detailed info were depending business requirement, So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	public void onAuthenticateUserLogged(RetailscmUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, find the correct user-app
		SecUser secUser = loginResult.getLoginContext().getLoginTarget().getSecUser();
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.OBJECT_TYPE_PROPERTY, RetailStoreOrder.INTERNAL_TYPE);
		SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
		if (userApps == null || userApps.isEmpty()) {
			throw new Exception("您的账号未关联销售人员,请联系客服处理账号异常.");
		}
		UserApp userApp = userApps.first();
		userApp.setSecUser(secUser);
		loginResult.getLoginContext().getLoginTarget().setUserApp(userApp);
		BaseEntity app = userContext.getDAOGroup().loadBasicData(userApp.getObjectType(), userApp.getObjectId());
		((RetailscmBizUserContextImpl)userContext).setCurrentUserInfo(app);
	}
	// -----------------------------------\\  登录部分处理 //-----------------------------------


	// -----------------------------------// list-of-view 处理 \\-----------------------------------
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<RetailStoreOrder> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<RetailStore> buyerList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, RetailStore.class);
		userContext.getDAOGroup().enhanceList(buyerList, RetailStore.class);
		List<RetailStoreCountryCenter> sellerList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, RetailStoreCountryCenter.class);
		userContext.getDAOGroup().enhanceList(sellerList, RetailStoreCountryCenter.class);


    }
	
	public Object listByBuyer(RetailscmUserContext userContext,String buyerId) throws Exception {
		return listPageByBuyer(userContext, buyerId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByBuyer(RetailscmUserContext userContext,String buyerId, int start, int count) throws Exception {
		SmartList<RetailStoreOrder> list = retailStoreOrderDaoOf(userContext).findRetailStoreOrderByBuyer(buyerId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(RetailStoreOrder.class);
		page.setContainerObject(RetailStore.withId(buyerId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("生超的订单列表");
		page.setRequestName("listByBuyer");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByBuyer/%s/",  getBeanName(), buyerId)));

		page.assemblerContent(userContext, "listByBuyer");
		return page.doRender(userContext);
	}
  
	public Object listBySeller(RetailscmUserContext userContext,String sellerId) throws Exception {
		return listPageBySeller(userContext, sellerId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageBySeller(RetailscmUserContext userContext,String sellerId, int start, int count) throws Exception {
		SmartList<RetailStoreOrder> list = retailStoreOrderDaoOf(userContext).findRetailStoreOrderBySeller(sellerId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(RetailStoreOrder.class);
		page.setContainerObject(RetailStoreCountryCenter.withId(sellerId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("生超的订单列表");
		page.setRequestName("listBySeller");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listBySeller/%s/",  getBeanName(), sellerId)));

		page.assemblerContent(userContext, "listBySeller");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String retailStoreOrderId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getRetailStoreOrderDetailScope().clone();
		RetailStoreOrder merchantObj = (RetailStoreOrder) this.view(userContext, retailStoreOrderId);
    String merchantObjId = retailStoreOrderId;
    String linkToUrl =	"retailStoreOrderManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "生超的订单"+"详情";
		Map result = new HashMap();
		List propList = new ArrayList();
		List sections = new ArrayList();
 
		propList.add(
				MapUtil.put("id", "1-id")
				    .put("fieldName", "id")
				    .put("label", "序号")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("id", merchantObj.getId());

		propList.add(
				MapUtil.put("id", "2-buyer")
				    .put("fieldName", "buyer")
				    .put("label", "买方")
				    .put("type", "auto")
				    .put("linkToUrl", "retailStoreManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"description\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("buyer", merchantObj.getBuyer());

		propList.add(
				MapUtil.put("id", "3-seller")
				    .put("fieldName", "seller")
				    .put("label", "卖方")
				    .put("type", "auto")
				    .put("linkToUrl", "retailStoreCountryCenterManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"description\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("seller", merchantObj.getSeller());

		propList.add(
				MapUtil.put("id", "4-title")
				    .put("fieldName", "title")
				    .put("label", "头衔")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("title", merchantObj.getTitle());

		propList.add(
				MapUtil.put("id", "5-totalAmount")
				    .put("fieldName", "totalAmount")
				    .put("label", "总金额")
				    .put("type", "money")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("totalAmount", merchantObj.getTotalAmount());

		propList.add(
				MapUtil.put("id", "6-lastUpdateTime")
				    .put("fieldName", "lastUpdateTime")
				    .put("label", "最后更新时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("lastUpdateTime", merchantObj.getLastUpdateTime());

		//处理 sectionList

		//处理Section：retailStoreOrderLineItemListSection
		Map retailStoreOrderLineItemListSection = ListofUtils.buildSection(
		    "retailStoreOrderLineItemListSection",
		    "零售门店订单行项目列表",
		    null,
		    "",
		    "__no_group",
		    "retailStoreOrderLineItemManager/listByBizOrder/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(retailStoreOrderLineItemListSection);

		result.put("retailStoreOrderLineItemListSection", ListofUtils.toShortList(merchantObj.getRetailStoreOrderLineItemList(), "retailStoreOrderLineItem"));
		vscope.field("retailStoreOrderLineItemListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( RetailStoreOrderLineItem.class.getName(), null));

		//处理Section：retailStoreOrderShippingGroupListSection
		Map retailStoreOrderShippingGroupListSection = ListofUtils.buildSection(
		    "retailStoreOrderShippingGroupListSection",
		    "零售门店订单发货组列表",
		    null,
		    "",
		    "__no_group",
		    "retailStoreOrderShippingGroupManager/listByBizOrder/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(retailStoreOrderShippingGroupListSection);

		result.put("retailStoreOrderShippingGroupListSection", ListofUtils.toShortList(merchantObj.getRetailStoreOrderShippingGroupList(), "retailStoreOrderShippingGroup"));
		vscope.field("retailStoreOrderShippingGroupListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( RetailStoreOrderShippingGroup.class.getName(), null));

		//处理Section：retailStoreOrderPaymentGroupListSection
		Map retailStoreOrderPaymentGroupListSection = ListofUtils.buildSection(
		    "retailStoreOrderPaymentGroupListSection",
		    "零售门店订单支付组列表",
		    null,
		    "",
		    "__no_group",
		    "retailStoreOrderPaymentGroupManager/listByBizOrder/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(retailStoreOrderPaymentGroupListSection);

		result.put("retailStoreOrderPaymentGroupListSection", ListofUtils.toShortList(merchantObj.getRetailStoreOrderPaymentGroupList(), "retailStoreOrderPaymentGroup"));
		vscope.field("retailStoreOrderPaymentGroupListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( RetailStoreOrderPaymentGroup.class.getName(), null));

		//处理Section：goodsListSection
		Map goodsListSection = ListofUtils.buildSection(
		    "goodsListSection",
		    "商品列表",
		    null,
		    "",
		    "__no_group",
		    "goodsManager/listByRetailStoreOrder/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(goodsListSection);

		result.put("goodsListSection", ListofUtils.toShortList(merchantObj.getGoodsList(), "goods"));
		vscope.field("goodsListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( Goods.class.getName(), null));

		result.put("propList", propList);
		result.put("sectionList", sections);
		result.put("pageTitle", pageTitle);
		result.put("linkToUrl", linkToUrl);

		vscope.field("propList", SerializeScope.EXCLUDE())
				.field("sectionList", SerializeScope.EXCLUDE())
				.field("pageTitle", SerializeScope.EXCLUDE())
				.field("linkToUrl", SerializeScope.EXCLUDE());
		userContext.forceResponseXClassHeader("com.terapico.appview.DetailPage");
		return BaseViewPage.serialize(result, vscope);
	}

}


