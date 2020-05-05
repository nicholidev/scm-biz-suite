
package com.doublechaintech.retailscm.retailstorecityservicecenter;

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


import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.retailstoreprovincecenter.RetailStoreProvinceCenter;
import com.doublechaintech.retailscm.potentialcustomer.PotentialCustomer;
import com.doublechaintech.retailscm.cityevent.CityEvent;
import com.doublechaintech.retailscm.citypartner.CityPartner;

import com.doublechaintech.retailscm.retailstoreprovincecenter.CandidateRetailStoreProvinceCenter;

import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.retailstoreclosing.RetailStoreClosing;
import com.doublechaintech.retailscm.retailstoreinvestmentinvitation.RetailStoreInvestmentInvitation;
import com.doublechaintech.retailscm.retailstoredecoration.RetailStoreDecoration;
import com.doublechaintech.retailscm.retailstorecreation.RetailStoreCreation;
import com.doublechaintech.retailscm.citypartner.CityPartner;
import com.doublechaintech.retailscm.retailstoreopening.RetailStoreOpening;
import com.doublechaintech.retailscm.retailstorecityservicecenter.RetailStoreCityServiceCenter;
import com.doublechaintech.retailscm.retailstorefranchising.RetailStoreFranchising;






public class RetailStoreCityServiceCenterManagerImpl extends CustomRetailscmCheckerManager implements RetailStoreCityServiceCenterManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(RetailscmUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = RetailStoreCityServiceCenterTokens.start().withTokenFromListName(listName).done();
		RetailStoreCityServiceCenter  retailStoreCityServiceCenter = (RetailStoreCityServiceCenter) this.loadRetailStoreCityServiceCenter(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = retailStoreCityServiceCenter.collectRefercencesFromLists();
		retailStoreCityServiceCenterDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, retailStoreCityServiceCenter, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new RetailStoreCityServiceCenterGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "RetailStoreCityServiceCenter";
	@Override
	public RetailStoreCityServiceCenterDAO daoOf(RetailscmUserContext userContext) {
		return retailStoreCityServiceCenterDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws RetailStoreCityServiceCenterManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new RetailStoreCityServiceCenterManagerException(message);

	}



 	protected RetailStoreCityServiceCenter saveRetailStoreCityServiceCenter(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, String [] tokensExpr) throws Exception{	
 		//return getRetailStoreCityServiceCenterDAO().save(retailStoreCityServiceCenter, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens);
 	}
 	
 	protected RetailStoreCityServiceCenter saveRetailStoreCityServiceCenterDetail(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter) throws Exception{	

 		
 		return saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, allTokens());
 	}
 	
 	public RetailStoreCityServiceCenter loadRetailStoreCityServiceCenter(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreCityServiceCenterManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter( userContext, retailStoreCityServiceCenterId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,retailStoreCityServiceCenter, tokens);
 	}
 	
 	
 	 public RetailStoreCityServiceCenter searchRetailStoreCityServiceCenter(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreCityServiceCenterManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter( userContext, retailStoreCityServiceCenterId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,retailStoreCityServiceCenter, tokens);
 	}
 	
 	

 	protected RetailStoreCityServiceCenter present(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,retailStoreCityServiceCenter,tokens);
		
		
		RetailStoreCityServiceCenter  retailStoreCityServiceCenterToPresent = retailStoreCityServiceCenterDaoOf(userContext).present(retailStoreCityServiceCenter, tokens);
		
		List<BaseEntity> entityListToNaming = retailStoreCityServiceCenterToPresent.collectRefercencesFromLists();
		retailStoreCityServiceCenterDaoOf(userContext).alias(entityListToNaming);
		
		return  retailStoreCityServiceCenterToPresent;
		
		
	}
 
 	
 	
 	public RetailStoreCityServiceCenter loadRetailStoreCityServiceCenterDetail(RetailscmUserContext userContext, String retailStoreCityServiceCenterId) throws Exception{	
 		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter( userContext, retailStoreCityServiceCenterId, allTokens());
 		return present(userContext,retailStoreCityServiceCenter, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String retailStoreCityServiceCenterId) throws Exception{	
 		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter( userContext, retailStoreCityServiceCenterId, viewTokens());
 		return present(userContext,retailStoreCityServiceCenter, allTokens());
		
 	}
 	protected RetailStoreCityServiceCenter saveRetailStoreCityServiceCenter(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, Map<String,Object>tokens) throws Exception{	
 		return retailStoreCityServiceCenterDaoOf(userContext).save(retailStoreCityServiceCenter, tokens);
 	}
 	protected RetailStoreCityServiceCenter loadRetailStoreCityServiceCenter(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).throwExceptionIfHasErrors( RetailStoreCityServiceCenterManagerException.class);

 
 		return retailStoreCityServiceCenterDaoOf(userContext).load(retailStoreCityServiceCenterId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, Map<String, Object> tokens){
		super.addActions(userContext, retailStoreCityServiceCenter, tokens);
		
		addAction(userContext, retailStoreCityServiceCenter, tokens,"@create","createRetailStoreCityServiceCenter","createRetailStoreCityServiceCenter/","main","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"@update","updateRetailStoreCityServiceCenter","updateRetailStoreCityServiceCenter/"+retailStoreCityServiceCenter.getId()+"/","main","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"@copy","cloneRetailStoreCityServiceCenter","cloneRetailStoreCityServiceCenter/"+retailStoreCityServiceCenter.getId()+"/","main","primary");
		
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.transfer_to_belongs_to","transferToAnotherBelongsTo","transferToAnotherBelongsTo/"+retailStoreCityServiceCenter.getId()+"/","main","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.addCityPartner","addCityPartner","addCityPartner/"+retailStoreCityServiceCenter.getId()+"/","cityPartnerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.removeCityPartner","removeCityPartner","removeCityPartner/"+retailStoreCityServiceCenter.getId()+"/","cityPartnerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.updateCityPartner","updateCityPartner","updateCityPartner/"+retailStoreCityServiceCenter.getId()+"/","cityPartnerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.copyCityPartnerFrom","copyCityPartnerFrom","copyCityPartnerFrom/"+retailStoreCityServiceCenter.getId()+"/","cityPartnerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.addPotentialCustomer","addPotentialCustomer","addPotentialCustomer/"+retailStoreCityServiceCenter.getId()+"/","potentialCustomerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.removePotentialCustomer","removePotentialCustomer","removePotentialCustomer/"+retailStoreCityServiceCenter.getId()+"/","potentialCustomerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.updatePotentialCustomer","updatePotentialCustomer","updatePotentialCustomer/"+retailStoreCityServiceCenter.getId()+"/","potentialCustomerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.copyPotentialCustomerFrom","copyPotentialCustomerFrom","copyPotentialCustomerFrom/"+retailStoreCityServiceCenter.getId()+"/","potentialCustomerList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.addCityEvent","addCityEvent","addCityEvent/"+retailStoreCityServiceCenter.getId()+"/","cityEventList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.removeCityEvent","removeCityEvent","removeCityEvent/"+retailStoreCityServiceCenter.getId()+"/","cityEventList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.updateCityEvent","updateCityEvent","updateCityEvent/"+retailStoreCityServiceCenter.getId()+"/","cityEventList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.copyCityEventFrom","copyCityEventFrom","copyCityEventFrom/"+retailStoreCityServiceCenter.getId()+"/","cityEventList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.addRetailStore","addRetailStore","addRetailStore/"+retailStoreCityServiceCenter.getId()+"/","retailStoreList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.removeRetailStore","removeRetailStore","removeRetailStore/"+retailStoreCityServiceCenter.getId()+"/","retailStoreList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.updateRetailStore","updateRetailStore","updateRetailStore/"+retailStoreCityServiceCenter.getId()+"/","retailStoreList","primary");
		addAction(userContext, retailStoreCityServiceCenter, tokens,"retail_store_city_service_center.copyRetailStoreFrom","copyRetailStoreFrom","copyRetailStoreFrom/"+retailStoreCityServiceCenter.getId()+"/","retailStoreList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public RetailStoreCityServiceCenter createRetailStoreCityServiceCenter(RetailscmUserContext userContext, String name,Date founded,String belongsToId) throws Exception
	//public RetailStoreCityServiceCenter createRetailStoreCityServiceCenter(RetailscmUserContext userContext,String name, Date founded, String belongsToId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfRetailStoreCityServiceCenter(name);
		checkerOf(userContext).checkFoundedOfRetailStoreCityServiceCenter(founded);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


		RetailStoreCityServiceCenter retailStoreCityServiceCenter=createNewRetailStoreCityServiceCenter();	

		retailStoreCityServiceCenter.setName(name);
		retailStoreCityServiceCenter.setFounded(founded);
			
		RetailStoreProvinceCenter belongsTo = loadRetailStoreProvinceCenter(userContext, belongsToId,emptyOptions());
		retailStoreCityServiceCenter.setBelongsTo(belongsTo);
		
		
		retailStoreCityServiceCenter.setLastUpdateTime(userContext.now());

		retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, emptyOptions());
		
		onNewInstanceCreated(userContext, retailStoreCityServiceCenter);
		return retailStoreCityServiceCenter;


	}
	protected RetailStoreCityServiceCenter createNewRetailStoreCityServiceCenter()
	{

		return new RetailStoreCityServiceCenter();
	}

	protected void checkParamsForUpdatingRetailStoreCityServiceCenter(RetailscmUserContext userContext,String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkVersionOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterVersion);
		

		if(RetailStoreCityServiceCenter.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfRetailStoreCityServiceCenter(parseString(newValueExpr));
		
			
		}
		if(RetailStoreCityServiceCenter.FOUNDED_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkFoundedOfRetailStoreCityServiceCenter(parseDate(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


	}



	public RetailStoreCityServiceCenter clone(RetailscmUserContext userContext, String fromRetailStoreCityServiceCenterId) throws Exception{

		return retailStoreCityServiceCenterDaoOf(userContext).clone(fromRetailStoreCityServiceCenterId, this.allTokens());
	}

	public RetailStoreCityServiceCenter internalSaveRetailStoreCityServiceCenter(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter) throws Exception
	{
		return internalSaveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, allTokens());

	}
	public RetailStoreCityServiceCenter internalSaveRetailStoreCityServiceCenter(RetailscmUserContext userContext, RetailStoreCityServiceCenter retailStoreCityServiceCenter, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion, property, newValueExpr, tokensExpr);


		synchronized(retailStoreCityServiceCenter){
			//will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreCityServiceCenter.
			if (retailStoreCityServiceCenter.isChanged()){
			retailStoreCityServiceCenter.updateLastUpdateTime(userContext.now());
			}
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, options);
			return retailStoreCityServiceCenter;

		}

	}

	public RetailStoreCityServiceCenter updateRetailStoreCityServiceCenter(RetailscmUserContext userContext,String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion, property, newValueExpr, tokensExpr);



		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		if(retailStoreCityServiceCenter.getVersion() != retailStoreCityServiceCenterVersion){
			String message = "The target version("+retailStoreCityServiceCenter.getVersion()+") is not equals to version("+retailStoreCityServiceCenterVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(retailStoreCityServiceCenter){
			//will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreCityServiceCenter.
			retailStoreCityServiceCenter.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter.changeProperty(property, newValueExpr);
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			//return saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().done());
		}

	}

	public RetailStoreCityServiceCenter updateRetailStoreCityServiceCenterProperty(RetailscmUserContext userContext,String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion, property, newValueExpr, tokensExpr);

		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		if(retailStoreCityServiceCenter.getVersion() != retailStoreCityServiceCenterVersion){
			String message = "The target version("+retailStoreCityServiceCenter.getVersion()+") is not equals to version("+retailStoreCityServiceCenterVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(retailStoreCityServiceCenter){
			//will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to RetailStoreCityServiceCenter.

			retailStoreCityServiceCenter.changeProperty(property, newValueExpr);
			retailStoreCityServiceCenter.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			//return saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected RetailStoreCityServiceCenterTokens tokens(){
		return RetailStoreCityServiceCenterTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return RetailStoreCityServiceCenterTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortCityPartnerListWith("id","desc")
		.sortPotentialCustomerListWith("id","desc")
		.sortCityEventListWith("id","desc")
		.sortRetailStoreListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return RetailStoreCityServiceCenterTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherBelongsTo(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String anotherBelongsToId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
 		checkerOf(userContext).checkIdOfRetailStoreProvinceCenter(anotherBelongsToId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

 	}
 	public RetailStoreCityServiceCenter transferToAnotherBelongsTo(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String anotherBelongsToId) throws Exception
 	{
 		checkParamsForTransferingAnotherBelongsTo(userContext, retailStoreCityServiceCenterId,anotherBelongsToId);
 
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());	
		synchronized(retailStoreCityServiceCenter){
			//will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreProvinceCenter belongsTo = loadRetailStoreProvinceCenter(userContext, anotherBelongsToId, emptyOptions());		
			retailStoreCityServiceCenter.updateBelongsTo(belongsTo);		
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, emptyOptions());
			
			return present(userContext,retailStoreCityServiceCenter, allTokens());
			
		}

 	}

	


	public CandidateRetailStoreProvinceCenter requestCandidateBelongsTo(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStoreProvinceCenter result = new CandidateRetailStoreProvinceCenter();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStoreProvinceCenter> candidateList = retailStoreProvinceCenterDaoOf(userContext).requestCandidateRetailStoreProvinceCenterForRetailStoreCityServiceCenter(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected RetailStoreProvinceCenter loadRetailStoreProvinceCenter(RetailscmUserContext userContext, String newBelongsToId, Map<String,Object> options) throws Exception
 	{

 		return retailStoreProvinceCenterDaoOf(userContext).load(newBelongsToId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion) throws Exception {
		//deleteInternal(userContext, retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion) throws Exception{

		retailStoreCityServiceCenterDaoOf(userContext).delete(retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion);
	}

	public RetailStoreCityServiceCenter forgetByAll(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion) throws Exception {
		return forgetByAllInternal(userContext, retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion);
	}
	protected RetailStoreCityServiceCenter forgetByAllInternal(RetailscmUserContext userContext,
			String retailStoreCityServiceCenterId, int retailStoreCityServiceCenterVersion) throws Exception{

		return retailStoreCityServiceCenterDaoOf(userContext).disconnectFromAll(retailStoreCityServiceCenterId, retailStoreCityServiceCenterVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new RetailStoreCityServiceCenterManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return retailStoreCityServiceCenterDaoOf(userContext).deleteAll();
	}


	//disconnect RetailStoreCityServiceCenter with city_partner in PotentialCustomer
	protected RetailStoreCityServiceCenter breakWithPotentialCustomerByCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String cityPartnerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemovePotentialCustomerListWithCityPartner(retailStoreCityServiceCenter, cityPartnerId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with retail_store_country_center in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByRetailStoreCountryCenter(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String retailStoreCountryCenterId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithRetailStoreCountryCenter(retailStoreCityServiceCenter, retailStoreCountryCenterId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with creation in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByCreation(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String creationId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithCreation(retailStoreCityServiceCenter, creationId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with investment_invitation in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByInvestmentInvitation(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String investmentInvitationId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithInvestmentInvitation(retailStoreCityServiceCenter, investmentInvitationId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with franchising in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByFranchising(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String franchisingId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithFranchising(retailStoreCityServiceCenter, franchisingId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with decoration in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByDecoration(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String decorationId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithDecoration(retailStoreCityServiceCenter, decorationId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with opening in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByOpening(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String openingId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithOpening(retailStoreCityServiceCenter, openingId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}
	//disconnect RetailStoreCityServiceCenter with closing in RetailStore
	protected RetailStoreCityServiceCenter breakWithRetailStoreByClosing(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String closingId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());

			synchronized(retailStoreCityServiceCenter){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreListWithClosing(retailStoreCityServiceCenter, closingId, this.emptyOptions());

				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				return retailStoreCityServiceCenter;
			}
	}






	protected void checkParamsForAddingCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String description,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);

		
		checkerOf(userContext).checkNameOfCityPartner(name);
		
		checkerOf(userContext).checkMobileOfCityPartner(mobile);
		
		checkerOf(userContext).checkDescriptionOfCityPartner(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


	}
	public  RetailStoreCityServiceCenter addCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String description, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingCityPartner(userContext,retailStoreCityServiceCenterId,name, mobile, description,tokensExpr);

		CityPartner cityPartner = createCityPartner(userContext,name, mobile, description);

		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, emptyOptions());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.addCityPartner( cityPartner );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityPartnerList().done());
			
			userContext.getManagerGroup().getCityPartnerManager().onNewInstanceCreated(userContext, cityPartner);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingCityPartnerProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,String id,String name,String mobile,String description,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityPartner(id);

		checkerOf(userContext).checkNameOfCityPartner( name);
		checkerOf(userContext).checkMobileOfCityPartner( mobile);
		checkerOf(userContext).checkDescriptionOfCityPartner( description);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter updateCityPartnerProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String id,String name,String mobile,String description, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCityPartnerProperties(userContext,retailStoreCityServiceCenterId,id,name,mobile,description,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withCityPartnerListList()
				.searchCityPartnerListWith(CityPartner.ID_PROPERTY, "is", id).done();

		RetailStoreCityServiceCenter retailStoreCityServiceCenterToUpdate = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, options);

		if(retailStoreCityServiceCenterToUpdate.getCityPartnerList().isEmpty()){
			throw new RetailStoreCityServiceCenterManagerException("CityPartner is NOT FOUND with id: '"+id+"'");
		}

		CityPartner item = retailStoreCityServiceCenterToUpdate.getCityPartnerList().first();

		item.updateName( name );
		item.updateMobile( mobile );
		item.updateDescription( description );


		//checkParamsForAddingCityPartner(userContext,retailStoreCityServiceCenterId,name, code, used,tokensExpr);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterToUpdate, tokens().withCityPartnerList().done());
		synchronized(retailStoreCityServiceCenter){
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}


	protected CityPartner createCityPartner(RetailscmUserContext userContext, String name, String mobile, String description) throws Exception{

		CityPartner cityPartner = new CityPartner();
		
		
		cityPartner.setName(name);		
		cityPartner.setMobile(mobile);		
		cityPartner.setDescription(description);		
		cityPartner.setLastUpdateTime(userContext.now());
	
		
		return cityPartner;


	}

	protected CityPartner createIndexedCityPartner(String id, int version){

		CityPartner cityPartner = new CityPartner();
		cityPartner.setId(id);
		cityPartner.setVersion(version);
		return cityPartner;

	}

	protected void checkParamsForRemovingCityPartnerList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String cityPartnerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		for(String cityPartnerIdItem: cityPartnerIds){
			checkerOf(userContext).checkIdOfCityPartner(cityPartnerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeCityPartnerList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String cityPartnerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingCityPartnerList(userContext, retailStoreCityServiceCenterId,  cityPartnerIds, tokensExpr);


			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
			synchronized(retailStoreCityServiceCenter){
				//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveCityPartnerList(retailStoreCityServiceCenter, cityPartnerIds, allTokens());
				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityPartnerList().done());
				deleteRelationListInGraph(userContext, retailStoreCityServiceCenter.getCityPartnerList());
				return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityPartnerId, int cityPartnerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityPartner(cityPartnerId);
		checkerOf(userContext).checkVersionOfCityPartner(cityPartnerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityPartnerId, int cityPartnerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingCityPartner(userContext,retailStoreCityServiceCenterId, cityPartnerId, cityPartnerVersion,tokensExpr);

		CityPartner cityPartner = createIndexedCityPartner(cityPartnerId, cityPartnerVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.removeCityPartner( cityPartner );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityPartnerList().done());
			deleteRelationInGraph(userContext, cityPartner);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityPartnerId, int cityPartnerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityPartner(cityPartnerId);
		checkerOf(userContext).checkVersionOfCityPartner(cityPartnerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter copyCityPartnerFrom(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityPartnerId, int cityPartnerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingCityPartner(userContext,retailStoreCityServiceCenterId, cityPartnerId, cityPartnerVersion,tokensExpr);

		CityPartner cityPartner = createIndexedCityPartner(cityPartnerId, cityPartnerVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			cityPartner.updateLastUpdateTime(userContext.now());

			retailStoreCityServiceCenter.copyCityPartnerFrom( cityPartner );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityPartnerList().done());
			
			userContext.getManagerGroup().getCityPartnerManager().onNewInstanceCreated(userContext, (CityPartner)retailStoreCityServiceCenter.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String cityPartnerId, int cityPartnerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityPartner(cityPartnerId);
		checkerOf(userContext).checkVersionOfCityPartner(cityPartnerVersion);
		

		if(CityPartner.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfCityPartner(parseString(newValueExpr));
		
		}
		
		if(CityPartner.MOBILE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMobileOfCityPartner(parseString(newValueExpr));
		
		}
		
		if(CityPartner.DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDescriptionOfCityPartner(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}

	public  RetailStoreCityServiceCenter updateCityPartner(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String cityPartnerId, int cityPartnerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingCityPartner(userContext, retailStoreCityServiceCenterId, cityPartnerId, cityPartnerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withCityPartnerList().searchCityPartnerListWith(CityPartner.ID_PROPERTY, "eq", cityPartnerId).done();



		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, loadTokens);

		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreCityServiceCenter.removeCityPartner( cityPartner );
			//make changes to AcceleraterAccount.
			CityPartner cityPartnerIndex = createIndexedCityPartner(cityPartnerId, cityPartnerVersion);

			CityPartner cityPartner = retailStoreCityServiceCenter.findTheCityPartner(cityPartnerIndex);
			if(cityPartner == null){
				throw new RetailStoreCityServiceCenterManagerException(cityPartner+" is NOT FOUND" );
			}

			cityPartner.changeProperty(property, newValueExpr);
			cityPartner.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityPartnerList().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingPotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String cityPartnerId, String description,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);

		
		checkerOf(userContext).checkNameOfPotentialCustomer(name);
		
		checkerOf(userContext).checkMobileOfPotentialCustomer(mobile);
		
		checkerOf(userContext).checkCityPartnerIdOfPotentialCustomer(cityPartnerId);
		
		checkerOf(userContext).checkDescriptionOfPotentialCustomer(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


	}
	public  RetailStoreCityServiceCenter addPotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String cityPartnerId, String description, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingPotentialCustomer(userContext,retailStoreCityServiceCenterId,name, mobile, cityPartnerId, description,tokensExpr);

		PotentialCustomer potentialCustomer = createPotentialCustomer(userContext,name, mobile, cityPartnerId, description);

		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, emptyOptions());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.addPotentialCustomer( potentialCustomer );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
			
			userContext.getManagerGroup().getPotentialCustomerManager().onNewInstanceCreated(userContext, potentialCustomer);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPotentialCustomerProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,String id,String name,String mobile,String description,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfPotentialCustomer(id);

		checkerOf(userContext).checkNameOfPotentialCustomer( name);
		checkerOf(userContext).checkMobileOfPotentialCustomer( mobile);
		checkerOf(userContext).checkDescriptionOfPotentialCustomer( description);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter updatePotentialCustomerProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String id,String name,String mobile,String description, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPotentialCustomerProperties(userContext,retailStoreCityServiceCenterId,id,name,mobile,description,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPotentialCustomerListList()
				.searchPotentialCustomerListWith(PotentialCustomer.ID_PROPERTY, "is", id).done();

		RetailStoreCityServiceCenter retailStoreCityServiceCenterToUpdate = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, options);

		if(retailStoreCityServiceCenterToUpdate.getPotentialCustomerList().isEmpty()){
			throw new RetailStoreCityServiceCenterManagerException("PotentialCustomer is NOT FOUND with id: '"+id+"'");
		}

		PotentialCustomer item = retailStoreCityServiceCenterToUpdate.getPotentialCustomerList().first();

		item.updateName( name );
		item.updateMobile( mobile );
		item.updateDescription( description );


		//checkParamsForAddingPotentialCustomer(userContext,retailStoreCityServiceCenterId,name, code, used,tokensExpr);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterToUpdate, tokens().withPotentialCustomerList().done());
		synchronized(retailStoreCityServiceCenter){
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}


	protected PotentialCustomer createPotentialCustomer(RetailscmUserContext userContext, String name, String mobile, String cityPartnerId, String description) throws Exception{

		PotentialCustomer potentialCustomer = new PotentialCustomer();
		
		
		potentialCustomer.setName(name);		
		potentialCustomer.setMobile(mobile);		
		CityPartner  cityPartner = new CityPartner();
		cityPartner.setId(cityPartnerId);		
		potentialCustomer.setCityPartner(cityPartner);		
		potentialCustomer.setDescription(description);		
		potentialCustomer.setLastUpdateTime(userContext.now());
	
		
		return potentialCustomer;


	}

	protected PotentialCustomer createIndexedPotentialCustomer(String id, int version){

		PotentialCustomer potentialCustomer = new PotentialCustomer();
		potentialCustomer.setId(id);
		potentialCustomer.setVersion(version);
		return potentialCustomer;

	}

	protected void checkParamsForRemovingPotentialCustomerList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String potentialCustomerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		for(String potentialCustomerIdItem: potentialCustomerIds){
			checkerOf(userContext).checkIdOfPotentialCustomer(potentialCustomerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removePotentialCustomerList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String potentialCustomerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingPotentialCustomerList(userContext, retailStoreCityServiceCenterId,  potentialCustomerIds, tokensExpr);


			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
			synchronized(retailStoreCityServiceCenter){
				//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreCityServiceCenterDaoOf(userContext).planToRemovePotentialCustomerList(retailStoreCityServiceCenter, potentialCustomerIds, allTokens());
				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
				deleteRelationListInGraph(userContext, retailStoreCityServiceCenter.getPotentialCustomerList());
				return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingPotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String potentialCustomerId, int potentialCustomerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfPotentialCustomer(potentialCustomerId);
		checkerOf(userContext).checkVersionOfPotentialCustomer(potentialCustomerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removePotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String potentialCustomerId, int potentialCustomerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingPotentialCustomer(userContext,retailStoreCityServiceCenterId, potentialCustomerId, potentialCustomerVersion,tokensExpr);

		PotentialCustomer potentialCustomer = createIndexedPotentialCustomer(potentialCustomerId, potentialCustomerVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.removePotentialCustomer( potentialCustomer );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
			deleteRelationInGraph(userContext, potentialCustomer);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingPotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String potentialCustomerId, int potentialCustomerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfPotentialCustomer(potentialCustomerId);
		checkerOf(userContext).checkVersionOfPotentialCustomer(potentialCustomerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter copyPotentialCustomerFrom(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String potentialCustomerId, int potentialCustomerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingPotentialCustomer(userContext,retailStoreCityServiceCenterId, potentialCustomerId, potentialCustomerVersion,tokensExpr);

		PotentialCustomer potentialCustomer = createIndexedPotentialCustomer(potentialCustomerId, potentialCustomerVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			potentialCustomer.updateLastUpdateTime(userContext.now());

			retailStoreCityServiceCenter.copyPotentialCustomerFrom( potentialCustomer );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
			
			userContext.getManagerGroup().getPotentialCustomerManager().onNewInstanceCreated(userContext, (PotentialCustomer)retailStoreCityServiceCenter.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingPotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String potentialCustomerId, int potentialCustomerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfPotentialCustomer(potentialCustomerId);
		checkerOf(userContext).checkVersionOfPotentialCustomer(potentialCustomerVersion);
		

		if(PotentialCustomer.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfPotentialCustomer(parseString(newValueExpr));
		
		}
		
		if(PotentialCustomer.MOBILE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMobileOfPotentialCustomer(parseString(newValueExpr));
		
		}
		
		if(PotentialCustomer.DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDescriptionOfPotentialCustomer(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}

	public  RetailStoreCityServiceCenter updatePotentialCustomer(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String potentialCustomerId, int potentialCustomerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingPotentialCustomer(userContext, retailStoreCityServiceCenterId, potentialCustomerId, potentialCustomerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withPotentialCustomerList().searchPotentialCustomerListWith(PotentialCustomer.ID_PROPERTY, "eq", potentialCustomerId).done();



		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, loadTokens);

		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreCityServiceCenter.removePotentialCustomer( potentialCustomer );
			//make changes to AcceleraterAccount.
			PotentialCustomer potentialCustomerIndex = createIndexedPotentialCustomer(potentialCustomerId, potentialCustomerVersion);

			PotentialCustomer potentialCustomer = retailStoreCityServiceCenter.findThePotentialCustomer(potentialCustomerIndex);
			if(potentialCustomer == null){
				throw new RetailStoreCityServiceCenterManagerException(potentialCustomer+" is NOT FOUND" );
			}

			potentialCustomer.changeProperty(property, newValueExpr);
			potentialCustomer.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withPotentialCustomerList().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String description,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);

		
		checkerOf(userContext).checkNameOfCityEvent(name);
		
		checkerOf(userContext).checkMobileOfCityEvent(mobile);
		
		checkerOf(userContext).checkDescriptionOfCityEvent(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


	}
	public  RetailStoreCityServiceCenter addCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String mobile, String description, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingCityEvent(userContext,retailStoreCityServiceCenterId,name, mobile, description,tokensExpr);

		CityEvent cityEvent = createCityEvent(userContext,name, mobile, description);

		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, emptyOptions());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.addCityEvent( cityEvent );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityEventList().done());
			
			userContext.getManagerGroup().getCityEventManager().onNewInstanceCreated(userContext, cityEvent);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingCityEventProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,String id,String name,String mobile,String description,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityEvent(id);

		checkerOf(userContext).checkNameOfCityEvent( name);
		checkerOf(userContext).checkMobileOfCityEvent( mobile);
		checkerOf(userContext).checkDescriptionOfCityEvent( description);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter updateCityEventProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String id,String name,String mobile,String description, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCityEventProperties(userContext,retailStoreCityServiceCenterId,id,name,mobile,description,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withCityEventListList()
				.searchCityEventListWith(CityEvent.ID_PROPERTY, "is", id).done();

		RetailStoreCityServiceCenter retailStoreCityServiceCenterToUpdate = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, options);

		if(retailStoreCityServiceCenterToUpdate.getCityEventList().isEmpty()){
			throw new RetailStoreCityServiceCenterManagerException("CityEvent is NOT FOUND with id: '"+id+"'");
		}

		CityEvent item = retailStoreCityServiceCenterToUpdate.getCityEventList().first();

		item.updateName( name );
		item.updateMobile( mobile );
		item.updateDescription( description );


		//checkParamsForAddingCityEvent(userContext,retailStoreCityServiceCenterId,name, code, used,tokensExpr);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterToUpdate, tokens().withCityEventList().done());
		synchronized(retailStoreCityServiceCenter){
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}


	protected CityEvent createCityEvent(RetailscmUserContext userContext, String name, String mobile, String description) throws Exception{

		CityEvent cityEvent = new CityEvent();
		
		
		cityEvent.setName(name);		
		cityEvent.setMobile(mobile);		
		cityEvent.setDescription(description);		
		cityEvent.setLastUpdateTime(userContext.now());
	
		
		return cityEvent;


	}

	protected CityEvent createIndexedCityEvent(String id, int version){

		CityEvent cityEvent = new CityEvent();
		cityEvent.setId(id);
		cityEvent.setVersion(version);
		return cityEvent;

	}

	protected void checkParamsForRemovingCityEventList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String cityEventIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		for(String cityEventIdItem: cityEventIds){
			checkerOf(userContext).checkIdOfCityEvent(cityEventIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeCityEventList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String cityEventIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingCityEventList(userContext, retailStoreCityServiceCenterId,  cityEventIds, tokensExpr);


			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
			synchronized(retailStoreCityServiceCenter){
				//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveCityEventList(retailStoreCityServiceCenter, cityEventIds, allTokens());
				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityEventList().done());
				deleteRelationListInGraph(userContext, retailStoreCityServiceCenter.getCityEventList());
				return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityEventId, int cityEventVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityEvent(cityEventId);
		checkerOf(userContext).checkVersionOfCityEvent(cityEventVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityEventId, int cityEventVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingCityEvent(userContext,retailStoreCityServiceCenterId, cityEventId, cityEventVersion,tokensExpr);

		CityEvent cityEvent = createIndexedCityEvent(cityEventId, cityEventVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.removeCityEvent( cityEvent );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityEventList().done());
			deleteRelationInGraph(userContext, cityEvent);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityEventId, int cityEventVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityEvent(cityEventId);
		checkerOf(userContext).checkVersionOfCityEvent(cityEventVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter copyCityEventFrom(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String cityEventId, int cityEventVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingCityEvent(userContext,retailStoreCityServiceCenterId, cityEventId, cityEventVersion,tokensExpr);

		CityEvent cityEvent = createIndexedCityEvent(cityEventId, cityEventVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			cityEvent.updateLastUpdateTime(userContext.now());

			retailStoreCityServiceCenter.copyCityEventFrom( cityEvent );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityEventList().done());
			
			userContext.getManagerGroup().getCityEventManager().onNewInstanceCreated(userContext, (CityEvent)retailStoreCityServiceCenter.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String cityEventId, int cityEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfCityEvent(cityEventId);
		checkerOf(userContext).checkVersionOfCityEvent(cityEventVersion);
		

		if(CityEvent.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfCityEvent(parseString(newValueExpr));
		
		}
		
		if(CityEvent.MOBILE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMobileOfCityEvent(parseString(newValueExpr));
		
		}
		
		if(CityEvent.DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDescriptionOfCityEvent(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}

	public  RetailStoreCityServiceCenter updateCityEvent(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String cityEventId, int cityEventVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingCityEvent(userContext, retailStoreCityServiceCenterId, cityEventId, cityEventVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withCityEventList().searchCityEventListWith(CityEvent.ID_PROPERTY, "eq", cityEventId).done();



		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, loadTokens);

		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreCityServiceCenter.removeCityEvent( cityEvent );
			//make changes to AcceleraterAccount.
			CityEvent cityEventIndex = createIndexedCityEvent(cityEventId, cityEventVersion);

			CityEvent cityEvent = retailStoreCityServiceCenter.findTheCityEvent(cityEventIndex);
			if(cityEvent == null){
				throw new RetailStoreCityServiceCenterManagerException(cityEvent+" is NOT FOUND" );
			}

			cityEvent.changeProperty(property, newValueExpr);
			cityEvent.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withCityEventList().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String telephone, String owner, String retailStoreCountryCenterId, String creationId, String investmentInvitationId, String franchisingId, String decorationId, String openingId, String closingId, Date founded, BigDecimal latitude, BigDecimal longitude, String description,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);

		
		checkerOf(userContext).checkNameOfRetailStore(name);
		
		checkerOf(userContext).checkTelephoneOfRetailStore(telephone);
		
		checkerOf(userContext).checkOwnerOfRetailStore(owner);
		
		checkerOf(userContext).checkRetailStoreCountryCenterIdOfRetailStore(retailStoreCountryCenterId);
		
		checkerOf(userContext).checkCreationIdOfRetailStore(creationId);
		
		checkerOf(userContext).checkInvestmentInvitationIdOfRetailStore(investmentInvitationId);
		
		checkerOf(userContext).checkFranchisingIdOfRetailStore(franchisingId);
		
		checkerOf(userContext).checkDecorationIdOfRetailStore(decorationId);
		
		checkerOf(userContext).checkOpeningIdOfRetailStore(openingId);
		
		checkerOf(userContext).checkClosingIdOfRetailStore(closingId);
		
		checkerOf(userContext).checkFoundedOfRetailStore(founded);
		
		checkerOf(userContext).checkLatitudeOfRetailStore(latitude);
		
		checkerOf(userContext).checkLongitudeOfRetailStore(longitude);
		
		checkerOf(userContext).checkDescriptionOfRetailStore(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);


	}
	public  RetailStoreCityServiceCenter addRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String name, String telephone, String owner, String retailStoreCountryCenterId, String creationId, String investmentInvitationId, String franchisingId, String decorationId, String openingId, String closingId, Date founded, BigDecimal latitude, BigDecimal longitude, String description, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingRetailStore(userContext,retailStoreCityServiceCenterId,name, telephone, owner, retailStoreCountryCenterId, creationId, investmentInvitationId, franchisingId, decorationId, openingId, closingId, founded, latitude, longitude, description,tokensExpr);

		RetailStore retailStore = createRetailStore(userContext,name, telephone, owner, retailStoreCountryCenterId, creationId, investmentInvitationId, franchisingId, decorationId, openingId, closingId, founded, latitude, longitude, description);

		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, emptyOptions());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.addRetailStore( retailStore );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
			
			userContext.getManagerGroup().getRetailStoreManager().onNewInstanceCreated(userContext, retailStore);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingRetailStoreProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,String id,String name,String telephone,String owner,Date founded,BigDecimal latitude,BigDecimal longitude,String description,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfRetailStore(id);

		checkerOf(userContext).checkNameOfRetailStore( name);
		checkerOf(userContext).checkTelephoneOfRetailStore( telephone);
		checkerOf(userContext).checkOwnerOfRetailStore( owner);
		checkerOf(userContext).checkFoundedOfRetailStore( founded);
		checkerOf(userContext).checkLatitudeOfRetailStore( latitude);
		checkerOf(userContext).checkLongitudeOfRetailStore( longitude);
		checkerOf(userContext).checkDescriptionOfRetailStore( description);

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter updateRetailStoreProperties(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String id,String name,String telephone,String owner,Date founded,BigDecimal latitude,BigDecimal longitude,String description, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingRetailStoreProperties(userContext,retailStoreCityServiceCenterId,id,name,telephone,owner,founded,latitude,longitude,description,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withRetailStoreListList()
				.searchRetailStoreListWith(RetailStore.ID_PROPERTY, "is", id).done();

		RetailStoreCityServiceCenter retailStoreCityServiceCenterToUpdate = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, options);

		if(retailStoreCityServiceCenterToUpdate.getRetailStoreList().isEmpty()){
			throw new RetailStoreCityServiceCenterManagerException("RetailStore is NOT FOUND with id: '"+id+"'");
		}

		RetailStore item = retailStoreCityServiceCenterToUpdate.getRetailStoreList().first();

		item.updateName( name );
		item.updateTelephone( telephone );
		item.updateOwner( owner );
		item.updateFounded( founded );
		item.updateLatitude( latitude );
		item.updateLongitude( longitude );
		item.updateDescription( description );


		//checkParamsForAddingRetailStore(userContext,retailStoreCityServiceCenterId,name, code, used,tokensExpr);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterToUpdate, tokens().withRetailStoreList().done());
		synchronized(retailStoreCityServiceCenter){
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}
	}


	protected RetailStore createRetailStore(RetailscmUserContext userContext, String name, String telephone, String owner, String retailStoreCountryCenterId, String creationId, String investmentInvitationId, String franchisingId, String decorationId, String openingId, String closingId, Date founded, BigDecimal latitude, BigDecimal longitude, String description) throws Exception{

		RetailStore retailStore = new RetailStore();
		
		
		retailStore.setName(name);		
		retailStore.setTelephone(telephone);		
		retailStore.setOwner(owner);		
		RetailStoreCountryCenter  retailStoreCountryCenter = new RetailStoreCountryCenter();
		retailStoreCountryCenter.setId(retailStoreCountryCenterId);		
		retailStore.setRetailStoreCountryCenter(retailStoreCountryCenter);		
		RetailStoreCreation  creation = new RetailStoreCreation();
		creation.setId(creationId);		
		retailStore.setCreation(creation);		
		RetailStoreInvestmentInvitation  investmentInvitation = new RetailStoreInvestmentInvitation();
		investmentInvitation.setId(investmentInvitationId);		
		retailStore.setInvestmentInvitation(investmentInvitation);		
		RetailStoreFranchising  franchising = new RetailStoreFranchising();
		franchising.setId(franchisingId);		
		retailStore.setFranchising(franchising);		
		RetailStoreDecoration  decoration = new RetailStoreDecoration();
		decoration.setId(decorationId);		
		retailStore.setDecoration(decoration);		
		RetailStoreOpening  opening = new RetailStoreOpening();
		opening.setId(openingId);		
		retailStore.setOpening(opening);		
		RetailStoreClosing  closing = new RetailStoreClosing();
		closing.setId(closingId);		
		retailStore.setClosing(closing);		
		retailStore.setFounded(founded);		
		retailStore.setLatitude(latitude);		
		retailStore.setLongitude(longitude);		
		retailStore.setDescription(description);		
		retailStore.setLastUpdateTime(userContext.now());
	
		
		return retailStore;


	}

	protected RetailStore createIndexedRetailStore(String id, int version){

		RetailStore retailStore = new RetailStore();
		retailStore.setId(id);
		retailStore.setVersion(version);
		return retailStore;

	}

	protected void checkParamsForRemovingRetailStoreList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String retailStoreIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		for(String retailStoreIdItem: retailStoreIds){
			checkerOf(userContext).checkIdOfRetailStore(retailStoreIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeRetailStoreList(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
			String retailStoreIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingRetailStoreList(userContext, retailStoreCityServiceCenterId,  retailStoreIds, tokensExpr);


			RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
			synchronized(retailStoreCityServiceCenter){
				//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				retailStoreCityServiceCenterDaoOf(userContext).planToRemoveRetailStoreList(retailStoreCityServiceCenter, retailStoreIds, allTokens());
				retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
				deleteRelationListInGraph(userContext, retailStoreCityServiceCenter.getRetailStoreList());
				return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String retailStoreId, int retailStoreVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfRetailStore(retailStoreId);
		checkerOf(userContext).checkVersionOfRetailStore(retailStoreVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter removeRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String retailStoreId, int retailStoreVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingRetailStore(userContext,retailStoreCityServiceCenterId, retailStoreId, retailStoreVersion,tokensExpr);

		RetailStore retailStore = createIndexedRetailStore(retailStoreId, retailStoreVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			retailStoreCityServiceCenter.removeRetailStore( retailStore );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
			deleteRelationInGraph(userContext, retailStore);
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String retailStoreId, int retailStoreVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter( retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfRetailStore(retailStoreId);
		checkerOf(userContext).checkVersionOfRetailStore(retailStoreVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}
	public  RetailStoreCityServiceCenter copyRetailStoreFrom(RetailscmUserContext userContext, String retailStoreCityServiceCenterId,
		String retailStoreId, int retailStoreVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingRetailStore(userContext,retailStoreCityServiceCenterId, retailStoreId, retailStoreVersion,tokensExpr);

		RetailStore retailStore = createIndexedRetailStore(retailStoreId, retailStoreVersion);
		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, allTokens());
		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			retailStore.updateLastUpdateTime(userContext.now());

			retailStoreCityServiceCenter.copyRetailStoreFrom( retailStore );
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
			
			userContext.getManagerGroup().getRetailStoreManager().onNewInstanceCreated(userContext, (RetailStore)retailStoreCityServiceCenter.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String retailStoreId, int retailStoreVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfRetailStoreCityServiceCenter(retailStoreCityServiceCenterId);
		checkerOf(userContext).checkIdOfRetailStore(retailStoreId);
		checkerOf(userContext).checkVersionOfRetailStore(retailStoreVersion);
		

		if(RetailStore.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfRetailStore(parseString(newValueExpr));
		
		}
		
		if(RetailStore.TELEPHONE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTelephoneOfRetailStore(parseString(newValueExpr));
		
		}
		
		if(RetailStore.OWNER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkOwnerOfRetailStore(parseString(newValueExpr));
		
		}
		
		if(RetailStore.FOUNDED_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkFoundedOfRetailStore(parseDate(newValueExpr));
		
		}
		
		if(RetailStore.LATITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLatitudeOfRetailStore(parseBigDecimal(newValueExpr));
		
		}
		
		if(RetailStore.LONGITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLongitudeOfRetailStore(parseBigDecimal(newValueExpr));
		
		}
		
		if(RetailStore.DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDescriptionOfRetailStore(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(RetailStoreCityServiceCenterManagerException.class);

	}

	public  RetailStoreCityServiceCenter updateRetailStore(RetailscmUserContext userContext, String retailStoreCityServiceCenterId, String retailStoreId, int retailStoreVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingRetailStore(userContext, retailStoreCityServiceCenterId, retailStoreId, retailStoreVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withRetailStoreList().searchRetailStoreListWith(RetailStore.ID_PROPERTY, "eq", retailStoreId).done();



		RetailStoreCityServiceCenter retailStoreCityServiceCenter = loadRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenterId, loadTokens);

		synchronized(retailStoreCityServiceCenter){
			//Will be good when the retailStoreCityServiceCenter loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//retailStoreCityServiceCenter.removeRetailStore( retailStore );
			//make changes to AcceleraterAccount.
			RetailStore retailStoreIndex = createIndexedRetailStore(retailStoreId, retailStoreVersion);

			RetailStore retailStore = retailStoreCityServiceCenter.findTheRetailStore(retailStoreIndex);
			if(retailStore == null){
				throw new RetailStoreCityServiceCenterManagerException(retailStore+" is NOT FOUND" );
			}

			retailStore.changeProperty(property, newValueExpr);
			retailStore.updateLastUpdateTime(userContext.now());
			retailStoreCityServiceCenter = saveRetailStoreCityServiceCenter(userContext, retailStoreCityServiceCenter, tokens().withRetailStoreList().done());
			return present(userContext,retailStoreCityServiceCenter, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(RetailscmUserContext userContext, RetailStoreCityServiceCenter newCreated) throws Exception{
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
		//   RetailStoreCityServiceCenter newRetailStoreCityServiceCenter = this.createRetailStoreCityServiceCenter(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newRetailStoreCityServiceCenter
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, RetailStoreCityServiceCenter.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<RetailStoreCityServiceCenter> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<RetailStoreProvinceCenter> belongsToList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, RetailStoreProvinceCenter.class);
		userContext.getDAOGroup().enhanceList(belongsToList, RetailStoreProvinceCenter.class);


    }
	
	public Object listByBelongsTo(RetailscmUserContext userContext,String belongsToId) throws Exception {
		return listPageByBelongsTo(userContext, belongsToId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByBelongsTo(RetailscmUserContext userContext,String belongsToId, int start, int count) throws Exception {
		SmartList<RetailStoreCityServiceCenter> list = retailStoreCityServiceCenterDaoOf(userContext).findRetailStoreCityServiceCenterByBelongsTo(belongsToId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(RetailStoreCityServiceCenter.class);
		page.setContainerObject(RetailStoreProvinceCenter.withId(belongsToId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("双链小超城市服务中心列表");
		page.setRequestName("listByBelongsTo");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByBelongsTo/%s/",  getBeanName(), belongsToId)));

		page.assemblerContent(userContext, "listByBelongsTo");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String retailStoreCityServiceCenterId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getRetailStoreCityServiceCenterDetailScope().clone();
		RetailStoreCityServiceCenter merchantObj = (RetailStoreCityServiceCenter) this.view(userContext, retailStoreCityServiceCenterId);
    String merchantObjId = retailStoreCityServiceCenterId;
    String linkToUrl =	"retailStoreCityServiceCenterManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "双链小超城市服务中心"+"详情";
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
				MapUtil.put("id", "2-name")
				    .put("fieldName", "name")
				    .put("label", "名称")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("name", merchantObj.getName());

		propList.add(
				MapUtil.put("id", "3-founded")
				    .put("fieldName", "founded")
				    .put("label", "成立")
				    .put("type", "date")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("founded", merchantObj.getFounded());

		propList.add(
				MapUtil.put("id", "4-belongsTo")
				    .put("fieldName", "belongsTo")
				    .put("label", "属于")
				    .put("type", "auto")
				    .put("linkToUrl", "retailStoreProvinceCenterManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"founded\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("belongsTo", merchantObj.getBelongsTo());

		propList.add(
				MapUtil.put("id", "5-lastUpdateTime")
				    .put("fieldName", "lastUpdateTime")
				    .put("label", "最后更新时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("lastUpdateTime", merchantObj.getLastUpdateTime());

		//处理 sectionList

		//处理Section：cityPartnerListSection
		Map cityPartnerListSection = ListofUtils.buildSection(
		    "cityPartnerListSection",
		    "城市的合作伙伴名单",
		    null,
		    "",
		    "__no_group",
		    "cityPartnerManager/listByCityServiceCenter/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(cityPartnerListSection);

		result.put("cityPartnerListSection", ListofUtils.toShortList(merchantObj.getCityPartnerList(), "cityPartner"));
		vscope.field("cityPartnerListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( CityPartner.class.getName(), null));

		//处理Section：potentialCustomerListSection
		Map potentialCustomerListSection = ListofUtils.buildSection(
		    "potentialCustomerListSection",
		    "潜在客户列表",
		    null,
		    "",
		    "__no_group",
		    "potentialCustomerManager/listByCityServiceCenter/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(potentialCustomerListSection);

		result.put("potentialCustomerListSection", ListofUtils.toShortList(merchantObj.getPotentialCustomerList(), "potentialCustomer"));
		vscope.field("potentialCustomerListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( PotentialCustomer.class.getName(), null));

		//处理Section：cityEventListSection
		Map cityEventListSection = ListofUtils.buildSection(
		    "cityEventListSection",
		    "城市事件列表",
		    null,
		    "",
		    "__no_group",
		    "cityEventManager/listByCityServiceCenter/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(cityEventListSection);

		result.put("cityEventListSection", ListofUtils.toShortList(merchantObj.getCityEventList(), "cityEvent"));
		vscope.field("cityEventListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( CityEvent.class.getName(), null));

		//处理Section：retailStoreListSection
		Map retailStoreListSection = ListofUtils.buildSection(
		    "retailStoreListSection",
		    "零售门店列表",
		    null,
		    "",
		    "__no_group",
		    "retailStoreManager/listByCityServiceCenter/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(retailStoreListSection);

		result.put("retailStoreListSection", ListofUtils.toShortList(merchantObj.getRetailStoreList(), "retailStore"));
		vscope.field("retailStoreListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( RetailStore.class.getName(), null));

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


