
package com.doublechaintech.retailscm.transportfleet;

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


import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.transporttruck.TransportTruck;
import com.doublechaintech.retailscm.truckdriver.TruckDriver;
import com.doublechaintech.retailscm.transporttask.TransportTask;

import com.doublechaintech.retailscm.retailstorecountrycenter.CandidateRetailStoreCountryCenter;

import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.transporttruck.TransportTruck;
import com.doublechaintech.retailscm.transportfleet.TransportFleet;
import com.doublechaintech.retailscm.truckdriver.TruckDriver;






public class TransportFleetManagerImpl extends CustomRetailscmCheckerManager implements TransportFleetManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(RetailscmUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = TransportFleetTokens.start().withTokenFromListName(listName).done();
		TransportFleet  transportFleet = (TransportFleet) this.loadTransportFleet(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = transportFleet.collectRefercencesFromLists();
		transportFleetDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, transportFleet, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new TransportFleetGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "TransportFleet";
	@Override
	public TransportFleetDAO daoOf(RetailscmUserContext userContext) {
		return transportFleetDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws TransportFleetManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new TransportFleetManagerException(message);

	}



 	protected TransportFleet saveTransportFleet(RetailscmUserContext userContext, TransportFleet transportFleet, String [] tokensExpr) throws Exception{	
 		//return getTransportFleetDAO().save(transportFleet, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveTransportFleet(userContext, transportFleet, tokens);
 	}
 	
 	protected TransportFleet saveTransportFleetDetail(RetailscmUserContext userContext, TransportFleet transportFleet) throws Exception{	

 		
 		return saveTransportFleet(userContext, transportFleet, allTokens());
 	}
 	
 	public TransportFleet loadTransportFleet(RetailscmUserContext userContext, String transportFleetId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).throwExceptionIfHasErrors( TransportFleetManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		TransportFleet transportFleet = loadTransportFleet( userContext, transportFleetId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,transportFleet, tokens);
 	}
 	
 	
 	 public TransportFleet searchTransportFleet(RetailscmUserContext userContext, String transportFleetId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).throwExceptionIfHasErrors( TransportFleetManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		TransportFleet transportFleet = loadTransportFleet( userContext, transportFleetId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,transportFleet, tokens);
 	}
 	
 	

 	protected TransportFleet present(RetailscmUserContext userContext, TransportFleet transportFleet, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,transportFleet,tokens);
		
		
		TransportFleet  transportFleetToPresent = transportFleetDaoOf(userContext).present(transportFleet, tokens);
		
		List<BaseEntity> entityListToNaming = transportFleetToPresent.collectRefercencesFromLists();
		transportFleetDaoOf(userContext).alias(entityListToNaming);
		
		return  transportFleetToPresent;
		
		
	}
 
 	
 	
 	public TransportFleet loadTransportFleetDetail(RetailscmUserContext userContext, String transportFleetId) throws Exception{	
 		TransportFleet transportFleet = loadTransportFleet( userContext, transportFleetId, allTokens());
 		return present(userContext,transportFleet, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String transportFleetId) throws Exception{	
 		TransportFleet transportFleet = loadTransportFleet( userContext, transportFleetId, viewTokens());
 		return present(userContext,transportFleet, allTokens());
		
 	}
 	protected TransportFleet saveTransportFleet(RetailscmUserContext userContext, TransportFleet transportFleet, Map<String,Object>tokens) throws Exception{	
 		return transportFleetDaoOf(userContext).save(transportFleet, tokens);
 	}
 	protected TransportFleet loadTransportFleet(RetailscmUserContext userContext, String transportFleetId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).throwExceptionIfHasErrors( TransportFleetManagerException.class);

 
 		return transportFleetDaoOf(userContext).load(transportFleetId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, TransportFleet transportFleet, Map<String, Object> tokens){
		super.addActions(userContext, transportFleet, tokens);
		
		addAction(userContext, transportFleet, tokens,"@create","createTransportFleet","createTransportFleet/","main","primary");
		addAction(userContext, transportFleet, tokens,"@update","updateTransportFleet","updateTransportFleet/"+transportFleet.getId()+"/","main","primary");
		addAction(userContext, transportFleet, tokens,"@copy","cloneTransportFleet","cloneTransportFleet/"+transportFleet.getId()+"/","main","primary");
		
		addAction(userContext, transportFleet, tokens,"transport_fleet.transfer_to_owner","transferToAnotherOwner","transferToAnotherOwner/"+transportFleet.getId()+"/","main","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.addTransportTruck","addTransportTruck","addTransportTruck/"+transportFleet.getId()+"/","transportTruckList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.removeTransportTruck","removeTransportTruck","removeTransportTruck/"+transportFleet.getId()+"/","transportTruckList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.updateTransportTruck","updateTransportTruck","updateTransportTruck/"+transportFleet.getId()+"/","transportTruckList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.copyTransportTruckFrom","copyTransportTruckFrom","copyTransportTruckFrom/"+transportFleet.getId()+"/","transportTruckList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.addTruckDriver","addTruckDriver","addTruckDriver/"+transportFleet.getId()+"/","truckDriverList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.removeTruckDriver","removeTruckDriver","removeTruckDriver/"+transportFleet.getId()+"/","truckDriverList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.updateTruckDriver","updateTruckDriver","updateTruckDriver/"+transportFleet.getId()+"/","truckDriverList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.copyTruckDriverFrom","copyTruckDriverFrom","copyTruckDriverFrom/"+transportFleet.getId()+"/","truckDriverList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.addTransportTask","addTransportTask","addTransportTask/"+transportFleet.getId()+"/","transportTaskList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.removeTransportTask","removeTransportTask","removeTransportTask/"+transportFleet.getId()+"/","transportTaskList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.updateTransportTask","updateTransportTask","updateTransportTask/"+transportFleet.getId()+"/","transportTaskList","primary");
		addAction(userContext, transportFleet, tokens,"transport_fleet.copyTransportTaskFrom","copyTransportTaskFrom","copyTransportTaskFrom/"+transportFleet.getId()+"/","transportTaskList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, TransportFleet transportFleet, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public TransportFleet createTransportFleet(RetailscmUserContext userContext, String name,String contactNumber,String ownerId) throws Exception
	//public TransportFleet createTransportFleet(RetailscmUserContext userContext,String name, String contactNumber, String ownerId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfTransportFleet(name);
		checkerOf(userContext).checkContactNumberOfTransportFleet(contactNumber);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);


		TransportFleet transportFleet=createNewTransportFleet();	

		transportFleet.setName(name);
		transportFleet.setContactNumber(contactNumber);
			
		RetailStoreCountryCenter owner = loadRetailStoreCountryCenter(userContext, ownerId,emptyOptions());
		transportFleet.setOwner(owner);
		
		
		transportFleet.setLastUpdateTime(userContext.now());

		transportFleet = saveTransportFleet(userContext, transportFleet, emptyOptions());
		
		onNewInstanceCreated(userContext, transportFleet);
		return transportFleet;


	}
	protected TransportFleet createNewTransportFleet()
	{

		return new TransportFleet();
	}

	protected void checkParamsForUpdatingTransportFleet(RetailscmUserContext userContext,String transportFleetId, int transportFleetVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkVersionOfTransportFleet( transportFleetVersion);
		

		if(TransportFleet.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfTransportFleet(parseString(newValueExpr));
		
			
		}
		if(TransportFleet.CONTACT_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkContactNumberOfTransportFleet(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);


	}



	public TransportFleet clone(RetailscmUserContext userContext, String fromTransportFleetId) throws Exception{

		return transportFleetDaoOf(userContext).clone(fromTransportFleetId, this.allTokens());
	}

	public TransportFleet internalSaveTransportFleet(RetailscmUserContext userContext, TransportFleet transportFleet) throws Exception
	{
		return internalSaveTransportFleet(userContext, transportFleet, allTokens());

	}
	public TransportFleet internalSaveTransportFleet(RetailscmUserContext userContext, TransportFleet transportFleet, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingTransportFleet(userContext, transportFleetId, transportFleetVersion, property, newValueExpr, tokensExpr);


		synchronized(transportFleet){
			//will be good when the transportFleet loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TransportFleet.
			if (transportFleet.isChanged()){
			transportFleet.updateLastUpdateTime(userContext.now());
			}
			transportFleet = saveTransportFleet(userContext, transportFleet, options);
			return transportFleet;

		}

	}

	public TransportFleet updateTransportFleet(RetailscmUserContext userContext,String transportFleetId, int transportFleetVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTransportFleet(userContext, transportFleetId, transportFleetVersion, property, newValueExpr, tokensExpr);



		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		if(transportFleet.getVersion() != transportFleetVersion){
			String message = "The target version("+transportFleet.getVersion()+") is not equals to version("+transportFleetVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(transportFleet){
			//will be good when the transportFleet loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TransportFleet.
			transportFleet.updateLastUpdateTime(userContext.now());
			transportFleet.changeProperty(property, newValueExpr);
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().done());
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
			//return saveTransportFleet(userContext, transportFleet, tokens().done());
		}

	}

	public TransportFleet updateTransportFleetProperty(RetailscmUserContext userContext,String transportFleetId, int transportFleetVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTransportFleet(userContext, transportFleetId, transportFleetVersion, property, newValueExpr, tokensExpr);

		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		if(transportFleet.getVersion() != transportFleetVersion){
			String message = "The target version("+transportFleet.getVersion()+") is not equals to version("+transportFleetVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(transportFleet){
			//will be good when the transportFleet loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to TransportFleet.

			transportFleet.changeProperty(property, newValueExpr);
			transportFleet.updateLastUpdateTime(userContext.now());
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().done());
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
			//return saveTransportFleet(userContext, transportFleet, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected TransportFleetTokens tokens(){
		return TransportFleetTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return TransportFleetTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTransportTruckListWith("id","desc")
		.sortTruckDriverListWith("id","desc")
		.sortTransportTaskListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return TransportFleetTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherOwner(RetailscmUserContext userContext, String transportFleetId, String anotherOwnerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
 		checkerOf(userContext).checkIdOfRetailStoreCountryCenter(anotherOwnerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

 	}
 	public TransportFleet transferToAnotherOwner(RetailscmUserContext userContext, String transportFleetId, String anotherOwnerId) throws Exception
 	{
 		checkParamsForTransferingAnotherOwner(userContext, transportFleetId,anotherOwnerId);
 
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());	
		synchronized(transportFleet){
			//will be good when the transportFleet loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreCountryCenter owner = loadRetailStoreCountryCenter(userContext, anotherOwnerId, emptyOptions());		
			transportFleet.updateOwner(owner);		
			transportFleet = saveTransportFleet(userContext, transportFleet, emptyOptions());
			
			return present(userContext,transportFleet, allTokens());
			
		}

 	}

	


	public CandidateRetailStoreCountryCenter requestCandidateOwner(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<RetailStoreCountryCenter> candidateList = retailStoreCountryCenterDaoOf(userContext).requestCandidateRetailStoreCountryCenterForTransportFleet(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected RetailStoreCountryCenter loadRetailStoreCountryCenter(RetailscmUserContext userContext, String newOwnerId, Map<String,Object> options) throws Exception
 	{

 		return retailStoreCountryCenterDaoOf(userContext).load(newOwnerId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String transportFleetId, int transportFleetVersion) throws Exception {
		//deleteInternal(userContext, transportFleetId, transportFleetVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String transportFleetId, int transportFleetVersion) throws Exception{

		transportFleetDaoOf(userContext).delete(transportFleetId, transportFleetVersion);
	}

	public TransportFleet forgetByAll(RetailscmUserContext userContext, String transportFleetId, int transportFleetVersion) throws Exception {
		return forgetByAllInternal(userContext, transportFleetId, transportFleetVersion);
	}
	protected TransportFleet forgetByAllInternal(RetailscmUserContext userContext,
			String transportFleetId, int transportFleetVersion) throws Exception{

		return transportFleetDaoOf(userContext).disconnectFromAll(transportFleetId, transportFleetVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new TransportFleetManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return transportFleetDaoOf(userContext).deleteAll();
	}


	//disconnect TransportFleet with end in TransportTask
	protected TransportFleet breakWithTransportTaskByEnd(RetailscmUserContext userContext, String transportFleetId, String endId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());

			synchronized(transportFleet){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				transportFleetDaoOf(userContext).planToRemoveTransportTaskListWithEnd(transportFleet, endId, this.emptyOptions());

				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
				return transportFleet;
			}
	}
	//disconnect TransportFleet with driver in TransportTask
	protected TransportFleet breakWithTransportTaskByDriver(RetailscmUserContext userContext, String transportFleetId, String driverId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());

			synchronized(transportFleet){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				transportFleetDaoOf(userContext).planToRemoveTransportTaskListWithDriver(transportFleet, driverId, this.emptyOptions());

				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
				return transportFleet;
			}
	}
	//disconnect TransportFleet with truck in TransportTask
	protected TransportFleet breakWithTransportTaskByTruck(RetailscmUserContext userContext, String transportFleetId, String truckId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());

			synchronized(transportFleet){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				transportFleetDaoOf(userContext).planToRemoveTransportTaskListWithTruck(transportFleet, truckId, this.emptyOptions());

				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
				return transportFleet;
			}
	}






	protected void checkParamsForAddingTransportTruck(RetailscmUserContext userContext, String transportFleetId, String name, String plateNumber, String contactNumber, String vehicleLicenseNumber, String engineNumber, Date makeDate, String mileage, String bodyColor,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);

		
		checkerOf(userContext).checkNameOfTransportTruck(name);
		
		checkerOf(userContext).checkPlateNumberOfTransportTruck(plateNumber);
		
		checkerOf(userContext).checkContactNumberOfTransportTruck(contactNumber);
		
		checkerOf(userContext).checkVehicleLicenseNumberOfTransportTruck(vehicleLicenseNumber);
		
		checkerOf(userContext).checkEngineNumberOfTransportTruck(engineNumber);
		
		checkerOf(userContext).checkMakeDateOfTransportTruck(makeDate);
		
		checkerOf(userContext).checkMileageOfTransportTruck(mileage);
		
		checkerOf(userContext).checkBodyColorOfTransportTruck(bodyColor);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);


	}
	public  TransportFleet addTransportTruck(RetailscmUserContext userContext, String transportFleetId, String name, String plateNumber, String contactNumber, String vehicleLicenseNumber, String engineNumber, Date makeDate, String mileage, String bodyColor, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingTransportTruck(userContext,transportFleetId,name, plateNumber, contactNumber, vehicleLicenseNumber, engineNumber, makeDate, mileage, bodyColor,tokensExpr);

		TransportTruck transportTruck = createTransportTruck(userContext,name, plateNumber, contactNumber, vehicleLicenseNumber, engineNumber, makeDate, mileage, bodyColor);

		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, emptyOptions());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.addTransportTruck( transportTruck );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTruckList().done());
			
			userContext.getManagerGroup().getTransportTruckManager().onNewInstanceCreated(userContext, transportTruck);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTransportTruckProperties(RetailscmUserContext userContext, String transportFleetId,String id,String name,String plateNumber,String contactNumber,String vehicleLicenseNumber,String engineNumber,Date makeDate,String mileage,String bodyColor,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTransportTruck(id);

		checkerOf(userContext).checkNameOfTransportTruck( name);
		checkerOf(userContext).checkPlateNumberOfTransportTruck( plateNumber);
		checkerOf(userContext).checkContactNumberOfTransportTruck( contactNumber);
		checkerOf(userContext).checkVehicleLicenseNumberOfTransportTruck( vehicleLicenseNumber);
		checkerOf(userContext).checkEngineNumberOfTransportTruck( engineNumber);
		checkerOf(userContext).checkMakeDateOfTransportTruck( makeDate);
		checkerOf(userContext).checkMileageOfTransportTruck( mileage);
		checkerOf(userContext).checkBodyColorOfTransportTruck( bodyColor);

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet updateTransportTruckProperties(RetailscmUserContext userContext, String transportFleetId, String id,String name,String plateNumber,String contactNumber,String vehicleLicenseNumber,String engineNumber,Date makeDate,String mileage,String bodyColor, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTransportTruckProperties(userContext,transportFleetId,id,name,plateNumber,contactNumber,vehicleLicenseNumber,engineNumber,makeDate,mileage,bodyColor,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTransportTruckListList()
				.searchTransportTruckListWith(TransportTruck.ID_PROPERTY, "is", id).done();

		TransportFleet transportFleetToUpdate = loadTransportFleet(userContext, transportFleetId, options);

		if(transportFleetToUpdate.getTransportTruckList().isEmpty()){
			throw new TransportFleetManagerException("TransportTruck is NOT FOUND with id: '"+id+"'");
		}

		TransportTruck item = transportFleetToUpdate.getTransportTruckList().first();

		item.updateName( name );
		item.updatePlateNumber( plateNumber );
		item.updateContactNumber( contactNumber );
		item.updateVehicleLicenseNumber( vehicleLicenseNumber );
		item.updateEngineNumber( engineNumber );
		item.updateMakeDate( makeDate );
		item.updateMileage( mileage );
		item.updateBodyColor( bodyColor );


		//checkParamsForAddingTransportTruck(userContext,transportFleetId,name, code, used,tokensExpr);
		TransportFleet transportFleet = saveTransportFleet(userContext, transportFleetToUpdate, tokens().withTransportTruckList().done());
		synchronized(transportFleet){
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}


	protected TransportTruck createTransportTruck(RetailscmUserContext userContext, String name, String plateNumber, String contactNumber, String vehicleLicenseNumber, String engineNumber, Date makeDate, String mileage, String bodyColor) throws Exception{

		TransportTruck transportTruck = new TransportTruck();
		
		
		transportTruck.setName(name);		
		transportTruck.setPlateNumber(plateNumber);		
		transportTruck.setContactNumber(contactNumber);		
		transportTruck.setVehicleLicenseNumber(vehicleLicenseNumber);		
		transportTruck.setEngineNumber(engineNumber);		
		transportTruck.setMakeDate(makeDate);		
		transportTruck.setMileage(mileage);		
		transportTruck.setBodyColor(bodyColor);
	
		
		return transportTruck;


	}

	protected TransportTruck createIndexedTransportTruck(String id, int version){

		TransportTruck transportTruck = new TransportTruck();
		transportTruck.setId(id);
		transportTruck.setVersion(version);
		return transportTruck;

	}

	protected void checkParamsForRemovingTransportTruckList(RetailscmUserContext userContext, String transportFleetId,
			String transportTruckIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		for(String transportTruckIdItem: transportTruckIds){
			checkerOf(userContext).checkIdOfTransportTruck(transportTruckIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTransportTruckList(RetailscmUserContext userContext, String transportFleetId,
			String transportTruckIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingTransportTruckList(userContext, transportFleetId,  transportTruckIds, tokensExpr);


			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
			synchronized(transportFleet){
				//Will be good when the transportFleet loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				transportFleetDaoOf(userContext).planToRemoveTransportTruckList(transportFleet, transportTruckIds, allTokens());
				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTruckList().done());
				deleteRelationListInGraph(userContext, transportFleet.getTransportTruckList());
				return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingTransportTruck(RetailscmUserContext userContext, String transportFleetId,
		String transportTruckId, int transportTruckVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTransportTruck(transportTruckId);
		checkerOf(userContext).checkVersionOfTransportTruck(transportTruckVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTransportTruck(RetailscmUserContext userContext, String transportFleetId,
		String transportTruckId, int transportTruckVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingTransportTruck(userContext,transportFleetId, transportTruckId, transportTruckVersion,tokensExpr);

		TransportTruck transportTruck = createIndexedTransportTruck(transportTruckId, transportTruckVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.removeTransportTruck( transportTruck );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTruckList().done());
			deleteRelationInGraph(userContext, transportTruck);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingTransportTruck(RetailscmUserContext userContext, String transportFleetId,
		String transportTruckId, int transportTruckVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTransportTruck(transportTruckId);
		checkerOf(userContext).checkVersionOfTransportTruck(transportTruckVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet copyTransportTruckFrom(RetailscmUserContext userContext, String transportFleetId,
		String transportTruckId, int transportTruckVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingTransportTruck(userContext,transportFleetId, transportTruckId, transportTruckVersion,tokensExpr);

		TransportTruck transportTruck = createIndexedTransportTruck(transportTruckId, transportTruckVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			transportFleet.copyTransportTruckFrom( transportTruck );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTruckList().done());
			
			userContext.getManagerGroup().getTransportTruckManager().onNewInstanceCreated(userContext, (TransportTruck)transportFleet.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingTransportTruck(RetailscmUserContext userContext, String transportFleetId, String transportTruckId, int transportTruckVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTransportTruck(transportTruckId);
		checkerOf(userContext).checkVersionOfTransportTruck(transportTruckVersion);
		

		if(TransportTruck.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.PLATE_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkPlateNumberOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.CONTACT_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkContactNumberOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.VEHICLE_LICENSE_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkVehicleLicenseNumberOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.ENGINE_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkEngineNumberOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.MAKE_DATE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMakeDateOfTransportTruck(parseDate(newValueExpr));
		
		}
		
		if(TransportTruck.MILEAGE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMileageOfTransportTruck(parseString(newValueExpr));
		
		}
		
		if(TransportTruck.BODY_COLOR_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBodyColorOfTransportTruck(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}

	public  TransportFleet updateTransportTruck(RetailscmUserContext userContext, String transportFleetId, String transportTruckId, int transportTruckVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingTransportTruck(userContext, transportFleetId, transportTruckId, transportTruckVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withTransportTruckList().searchTransportTruckListWith(TransportTruck.ID_PROPERTY, "eq", transportTruckId).done();



		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, loadTokens);

		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//transportFleet.removeTransportTruck( transportTruck );
			//make changes to AcceleraterAccount.
			TransportTruck transportTruckIndex = createIndexedTransportTruck(transportTruckId, transportTruckVersion);

			TransportTruck transportTruck = transportFleet.findTheTransportTruck(transportTruckIndex);
			if(transportTruck == null){
				throw new TransportFleetManagerException(transportTruck+" is NOT FOUND" );
			}

			transportTruck.changeProperty(property, newValueExpr);
			
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTruckList().done());
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingTruckDriver(RetailscmUserContext userContext, String transportFleetId, String name, String driverLicenseNumber, String contactNumber,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);

		
		checkerOf(userContext).checkNameOfTruckDriver(name);
		
		checkerOf(userContext).checkDriverLicenseNumberOfTruckDriver(driverLicenseNumber);
		
		checkerOf(userContext).checkContactNumberOfTruckDriver(contactNumber);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);


	}
	public  TransportFleet addTruckDriver(RetailscmUserContext userContext, String transportFleetId, String name, String driverLicenseNumber, String contactNumber, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingTruckDriver(userContext,transportFleetId,name, driverLicenseNumber, contactNumber,tokensExpr);

		TruckDriver truckDriver = createTruckDriver(userContext,name, driverLicenseNumber, contactNumber);

		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, emptyOptions());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.addTruckDriver( truckDriver );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTruckDriverList().done());
			
			userContext.getManagerGroup().getTruckDriverManager().onNewInstanceCreated(userContext, truckDriver);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTruckDriverProperties(RetailscmUserContext userContext, String transportFleetId,String id,String name,String driverLicenseNumber,String contactNumber,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTruckDriver(id);

		checkerOf(userContext).checkNameOfTruckDriver( name);
		checkerOf(userContext).checkDriverLicenseNumberOfTruckDriver( driverLicenseNumber);
		checkerOf(userContext).checkContactNumberOfTruckDriver( contactNumber);

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet updateTruckDriverProperties(RetailscmUserContext userContext, String transportFleetId, String id,String name,String driverLicenseNumber,String contactNumber, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTruckDriverProperties(userContext,transportFleetId,id,name,driverLicenseNumber,contactNumber,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTruckDriverListList()
				.searchTruckDriverListWith(TruckDriver.ID_PROPERTY, "is", id).done();

		TransportFleet transportFleetToUpdate = loadTransportFleet(userContext, transportFleetId, options);

		if(transportFleetToUpdate.getTruckDriverList().isEmpty()){
			throw new TransportFleetManagerException("TruckDriver is NOT FOUND with id: '"+id+"'");
		}

		TruckDriver item = transportFleetToUpdate.getTruckDriverList().first();

		item.updateName( name );
		item.updateDriverLicenseNumber( driverLicenseNumber );
		item.updateContactNumber( contactNumber );


		//checkParamsForAddingTruckDriver(userContext,transportFleetId,name, code, used,tokensExpr);
		TransportFleet transportFleet = saveTransportFleet(userContext, transportFleetToUpdate, tokens().withTruckDriverList().done());
		synchronized(transportFleet){
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}


	protected TruckDriver createTruckDriver(RetailscmUserContext userContext, String name, String driverLicenseNumber, String contactNumber) throws Exception{

		TruckDriver truckDriver = new TruckDriver();
		
		
		truckDriver.setName(name);		
		truckDriver.setDriverLicenseNumber(driverLicenseNumber);		
		truckDriver.setContactNumber(contactNumber);
	
		
		return truckDriver;


	}

	protected TruckDriver createIndexedTruckDriver(String id, int version){

		TruckDriver truckDriver = new TruckDriver();
		truckDriver.setId(id);
		truckDriver.setVersion(version);
		return truckDriver;

	}

	protected void checkParamsForRemovingTruckDriverList(RetailscmUserContext userContext, String transportFleetId,
			String truckDriverIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		for(String truckDriverIdItem: truckDriverIds){
			checkerOf(userContext).checkIdOfTruckDriver(truckDriverIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTruckDriverList(RetailscmUserContext userContext, String transportFleetId,
			String truckDriverIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingTruckDriverList(userContext, transportFleetId,  truckDriverIds, tokensExpr);


			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
			synchronized(transportFleet){
				//Will be good when the transportFleet loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				transportFleetDaoOf(userContext).planToRemoveTruckDriverList(transportFleet, truckDriverIds, allTokens());
				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTruckDriverList().done());
				deleteRelationListInGraph(userContext, transportFleet.getTruckDriverList());
				return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingTruckDriver(RetailscmUserContext userContext, String transportFleetId,
		String truckDriverId, int truckDriverVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTruckDriver(truckDriverId);
		checkerOf(userContext).checkVersionOfTruckDriver(truckDriverVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTruckDriver(RetailscmUserContext userContext, String transportFleetId,
		String truckDriverId, int truckDriverVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingTruckDriver(userContext,transportFleetId, truckDriverId, truckDriverVersion,tokensExpr);

		TruckDriver truckDriver = createIndexedTruckDriver(truckDriverId, truckDriverVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.removeTruckDriver( truckDriver );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTruckDriverList().done());
			deleteRelationInGraph(userContext, truckDriver);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingTruckDriver(RetailscmUserContext userContext, String transportFleetId,
		String truckDriverId, int truckDriverVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTruckDriver(truckDriverId);
		checkerOf(userContext).checkVersionOfTruckDriver(truckDriverVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet copyTruckDriverFrom(RetailscmUserContext userContext, String transportFleetId,
		String truckDriverId, int truckDriverVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingTruckDriver(userContext,transportFleetId, truckDriverId, truckDriverVersion,tokensExpr);

		TruckDriver truckDriver = createIndexedTruckDriver(truckDriverId, truckDriverVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			transportFleet.copyTruckDriverFrom( truckDriver );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTruckDriverList().done());
			
			userContext.getManagerGroup().getTruckDriverManager().onNewInstanceCreated(userContext, (TruckDriver)transportFleet.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingTruckDriver(RetailscmUserContext userContext, String transportFleetId, String truckDriverId, int truckDriverVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTruckDriver(truckDriverId);
		checkerOf(userContext).checkVersionOfTruckDriver(truckDriverVersion);
		

		if(TruckDriver.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfTruckDriver(parseString(newValueExpr));
		
		}
		
		if(TruckDriver.DRIVER_LICENSE_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDriverLicenseNumberOfTruckDriver(parseString(newValueExpr));
		
		}
		
		if(TruckDriver.CONTACT_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkContactNumberOfTruckDriver(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}

	public  TransportFleet updateTruckDriver(RetailscmUserContext userContext, String transportFleetId, String truckDriverId, int truckDriverVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingTruckDriver(userContext, transportFleetId, truckDriverId, truckDriverVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withTruckDriverList().searchTruckDriverListWith(TruckDriver.ID_PROPERTY, "eq", truckDriverId).done();



		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, loadTokens);

		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//transportFleet.removeTruckDriver( truckDriver );
			//make changes to AcceleraterAccount.
			TruckDriver truckDriverIndex = createIndexedTruckDriver(truckDriverId, truckDriverVersion);

			TruckDriver truckDriver = transportFleet.findTheTruckDriver(truckDriverIndex);
			if(truckDriver == null){
				throw new TransportFleetManagerException(truckDriver+" is NOT FOUND" );
			}

			truckDriver.changeProperty(property, newValueExpr);
			
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTruckDriverList().done());
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingTransportTask(RetailscmUserContext userContext, String transportFleetId, String name, String start, Date beginTime, String endId, String driverId, String truckId, BigDecimal latitude, BigDecimal longitude,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);

		
		checkerOf(userContext).checkNameOfTransportTask(name);
		
		checkerOf(userContext).checkStartOfTransportTask(start);
		
		checkerOf(userContext).checkBeginTimeOfTransportTask(beginTime);
		
		checkerOf(userContext).checkEndIdOfTransportTask(endId);
		
		checkerOf(userContext).checkDriverIdOfTransportTask(driverId);
		
		checkerOf(userContext).checkTruckIdOfTransportTask(truckId);
		
		checkerOf(userContext).checkLatitudeOfTransportTask(latitude);
		
		checkerOf(userContext).checkLongitudeOfTransportTask(longitude);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);


	}
	public  TransportFleet addTransportTask(RetailscmUserContext userContext, String transportFleetId, String name, String start, Date beginTime, String endId, String driverId, String truckId, BigDecimal latitude, BigDecimal longitude, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingTransportTask(userContext,transportFleetId,name, start, beginTime, endId, driverId, truckId, latitude, longitude,tokensExpr);

		TransportTask transportTask = createTransportTask(userContext,name, start, beginTime, endId, driverId, truckId, latitude, longitude);

		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, emptyOptions());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.addTransportTask( transportTask );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
			
			userContext.getManagerGroup().getTransportTaskManager().onNewInstanceCreated(userContext, transportTask);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTransportTaskProperties(RetailscmUserContext userContext, String transportFleetId,String id,String name,String start,Date beginTime,BigDecimal latitude,BigDecimal longitude,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTransportTask(id);

		checkerOf(userContext).checkNameOfTransportTask( name);
		checkerOf(userContext).checkStartOfTransportTask( start);
		checkerOf(userContext).checkBeginTimeOfTransportTask( beginTime);
		checkerOf(userContext).checkLatitudeOfTransportTask( latitude);
		checkerOf(userContext).checkLongitudeOfTransportTask( longitude);

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet updateTransportTaskProperties(RetailscmUserContext userContext, String transportFleetId, String id,String name,String start,Date beginTime,BigDecimal latitude,BigDecimal longitude, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTransportTaskProperties(userContext,transportFleetId,id,name,start,beginTime,latitude,longitude,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTransportTaskListList()
				.searchTransportTaskListWith(TransportTask.ID_PROPERTY, "is", id).done();

		TransportFleet transportFleetToUpdate = loadTransportFleet(userContext, transportFleetId, options);

		if(transportFleetToUpdate.getTransportTaskList().isEmpty()){
			throw new TransportFleetManagerException("TransportTask is NOT FOUND with id: '"+id+"'");
		}

		TransportTask item = transportFleetToUpdate.getTransportTaskList().first();

		item.updateName( name );
		item.updateStart( start );
		item.updateBeginTime( beginTime );
		item.updateLatitude( latitude );
		item.updateLongitude( longitude );


		//checkParamsForAddingTransportTask(userContext,transportFleetId,name, code, used,tokensExpr);
		TransportFleet transportFleet = saveTransportFleet(userContext, transportFleetToUpdate, tokens().withTransportTaskList().done());
		synchronized(transportFleet){
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}
	}


	protected TransportTask createTransportTask(RetailscmUserContext userContext, String name, String start, Date beginTime, String endId, String driverId, String truckId, BigDecimal latitude, BigDecimal longitude) throws Exception{

		TransportTask transportTask = new TransportTask();
		
		
		transportTask.setName(name);		
		transportTask.setStart(start);		
		transportTask.setBeginTime(beginTime);		
		RetailStore  end = new RetailStore();
		end.setId(endId);		
		transportTask.setEnd(end);		
		TruckDriver  driver = new TruckDriver();
		driver.setId(driverId);		
		transportTask.setDriver(driver);		
		TransportTruck  truck = new TransportTruck();
		truck.setId(truckId);		
		transportTask.setTruck(truck);		
		transportTask.setLatitude(latitude);		
		transportTask.setLongitude(longitude);
	
		
		return transportTask;


	}

	protected TransportTask createIndexedTransportTask(String id, int version){

		TransportTask transportTask = new TransportTask();
		transportTask.setId(id);
		transportTask.setVersion(version);
		return transportTask;

	}

	protected void checkParamsForRemovingTransportTaskList(RetailscmUserContext userContext, String transportFleetId,
			String transportTaskIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		for(String transportTaskIdItem: transportTaskIds){
			checkerOf(userContext).checkIdOfTransportTask(transportTaskIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTransportTaskList(RetailscmUserContext userContext, String transportFleetId,
			String transportTaskIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingTransportTaskList(userContext, transportFleetId,  transportTaskIds, tokensExpr);


			TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
			synchronized(transportFleet){
				//Will be good when the transportFleet loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				transportFleetDaoOf(userContext).planToRemoveTransportTaskList(transportFleet, transportTaskIds, allTokens());
				transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
				deleteRelationListInGraph(userContext, transportFleet.getTransportTaskList());
				return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingTransportTask(RetailscmUserContext userContext, String transportFleetId,
		String transportTaskId, int transportTaskVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTransportTask(transportTaskId);
		checkerOf(userContext).checkVersionOfTransportTask(transportTaskVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet removeTransportTask(RetailscmUserContext userContext, String transportFleetId,
		String transportTaskId, int transportTaskVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingTransportTask(userContext,transportFleetId, transportTaskId, transportTaskVersion,tokensExpr);

		TransportTask transportTask = createIndexedTransportTask(transportTaskId, transportTaskVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			transportFleet.removeTransportTask( transportTask );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
			deleteRelationInGraph(userContext, transportTask);
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingTransportTask(RetailscmUserContext userContext, String transportFleetId,
		String transportTaskId, int transportTaskVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTransportFleet( transportFleetId);
		checkerOf(userContext).checkIdOfTransportTask(transportTaskId);
		checkerOf(userContext).checkVersionOfTransportTask(transportTaskVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}
	public  TransportFleet copyTransportTaskFrom(RetailscmUserContext userContext, String transportFleetId,
		String transportTaskId, int transportTaskVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingTransportTask(userContext,transportFleetId, transportTaskId, transportTaskVersion,tokensExpr);

		TransportTask transportTask = createIndexedTransportTask(transportTaskId, transportTaskVersion);
		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, allTokens());
		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			transportFleet.copyTransportTaskFrom( transportTask );
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
			
			userContext.getManagerGroup().getTransportTaskManager().onNewInstanceCreated(userContext, (TransportTask)transportFleet.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingTransportTask(RetailscmUserContext userContext, String transportFleetId, String transportTaskId, int transportTaskVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTransportFleet(transportFleetId);
		checkerOf(userContext).checkIdOfTransportTask(transportTaskId);
		checkerOf(userContext).checkVersionOfTransportTask(transportTaskVersion);
		

		if(TransportTask.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfTransportTask(parseString(newValueExpr));
		
		}
		
		if(TransportTask.START_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkStartOfTransportTask(parseString(newValueExpr));
		
		}
		
		if(TransportTask.BEGIN_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBeginTimeOfTransportTask(parseDate(newValueExpr));
		
		}
		
		if(TransportTask.LATITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLatitudeOfTransportTask(parseBigDecimal(newValueExpr));
		
		}
		
		if(TransportTask.LONGITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLongitudeOfTransportTask(parseBigDecimal(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TransportFleetManagerException.class);

	}

	public  TransportFleet updateTransportTask(RetailscmUserContext userContext, String transportFleetId, String transportTaskId, int transportTaskVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingTransportTask(userContext, transportFleetId, transportTaskId, transportTaskVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withTransportTaskList().searchTransportTaskListWith(TransportTask.ID_PROPERTY, "eq", transportTaskId).done();



		TransportFleet transportFleet = loadTransportFleet(userContext, transportFleetId, loadTokens);

		synchronized(transportFleet){
			//Will be good when the transportFleet loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//transportFleet.removeTransportTask( transportTask );
			//make changes to AcceleraterAccount.
			TransportTask transportTaskIndex = createIndexedTransportTask(transportTaskId, transportTaskVersion);

			TransportTask transportTask = transportFleet.findTheTransportTask(transportTaskIndex);
			if(transportTask == null){
				throw new TransportFleetManagerException(transportTask+" is NOT FOUND" );
			}

			transportTask.changeProperty(property, newValueExpr);
			
			transportFleet = saveTransportFleet(userContext, transportFleet, tokens().withTransportTaskList().done());
			return present(userContext,transportFleet, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(RetailscmUserContext userContext, TransportFleet newCreated) throws Exception{
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
		//   TransportFleet newTransportFleet = this.createTransportFleet(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newTransportFleet
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, TransportFleet.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<TransportFleet> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<RetailStoreCountryCenter> ownerList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, RetailStoreCountryCenter.class);
		userContext.getDAOGroup().enhanceList(ownerList, RetailStoreCountryCenter.class);


    }
	
	public Object listByOwner(RetailscmUserContext userContext,String ownerId) throws Exception {
		return listPageByOwner(userContext, ownerId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByOwner(RetailscmUserContext userContext,String ownerId, int start, int count) throws Exception {
		SmartList<TransportFleet> list = transportFleetDaoOf(userContext).findTransportFleetByOwner(ownerId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(TransportFleet.class);
		page.setContainerObject(RetailStoreCountryCenter.withId(ownerId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("运输车队列表");
		page.setRequestName("listByOwner");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByOwner/%s/",  getBeanName(), ownerId)));

		page.assemblerContent(userContext, "listByOwner");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String transportFleetId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getTransportFleetDetailScope().clone();
		TransportFleet merchantObj = (TransportFleet) this.view(userContext, transportFleetId);
    String merchantObjId = transportFleetId;
    String linkToUrl =	"transportFleetManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "运输车队"+"详情";
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
				MapUtil.put("id", "3-contactNumber")
				    .put("fieldName", "contactNumber")
				    .put("label", "联系电话")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("contactNumber", merchantObj.getContactNumber());

		propList.add(
				MapUtil.put("id", "4-owner")
				    .put("fieldName", "owner")
				    .put("label", "业主")
				    .put("type", "auto")
				    .put("linkToUrl", "retailStoreCountryCenterManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"description\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("owner", merchantObj.getOwner());

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

		//处理Section：transportTruckListSection
		Map transportTruckListSection = ListofUtils.buildSection(
		    "transportTruckListSection",
		    "运输卡车列表",
		    null,
		    "",
		    "__no_group",
		    "transportTruckManager/listByOwner/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(transportTruckListSection);

		result.put("transportTruckListSection", ListofUtils.toShortList(merchantObj.getTransportTruckList(), "transportTruck"));
		vscope.field("transportTruckListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( TransportTruck.class.getName(), null));

		//处理Section：truckDriverListSection
		Map truckDriverListSection = ListofUtils.buildSection(
		    "truckDriverListSection",
		    "卡车司机列表",
		    null,
		    "",
		    "__no_group",
		    "truckDriverManager/listByBelongsTo/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(truckDriverListSection);

		result.put("truckDriverListSection", ListofUtils.toShortList(merchantObj.getTruckDriverList(), "truckDriver"));
		vscope.field("truckDriverListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( TruckDriver.class.getName(), null));

		//处理Section：transportTaskListSection
		Map transportTaskListSection = ListofUtils.buildSection(
		    "transportTaskListSection",
		    "运输任务列表",
		    null,
		    "",
		    "__no_group",
		    "transportTaskManager/listByBelongsTo/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(transportTaskListSection);

		result.put("transportTaskListSection", ListofUtils.toShortList(merchantObj.getTransportTaskList(), "transportTask"));
		vscope.field("transportTaskListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( TransportTask.class.getName(), null));

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


