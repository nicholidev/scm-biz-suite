
package com.doublechaintech.retailscm.salarygrade;

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
import com.doublechaintech.retailscm.employee.Employee;
import com.doublechaintech.retailscm.employeesalarysheet.EmployeeSalarySheet;

import com.doublechaintech.retailscm.retailstorecountrycenter.CandidateRetailStoreCountryCenter;

import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.employee.Employee;
import com.doublechaintech.retailscm.payingoff.PayingOff;
import com.doublechaintech.retailscm.levelthreedepartment.LevelThreeDepartment;
import com.doublechaintech.retailscm.responsibilitytype.ResponsibilityType;
import com.doublechaintech.retailscm.occupationtype.OccupationType;
import com.doublechaintech.retailscm.salarygrade.SalaryGrade;






public class SalaryGradeManagerImpl extends CustomRetailscmCheckerManager implements SalaryGradeManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(RetailscmUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = SalaryGradeTokens.start().withTokenFromListName(listName).done();
		SalaryGrade  salaryGrade = (SalaryGrade) this.loadSalaryGrade(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = salaryGrade.collectRefercencesFromLists();
		salaryGradeDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, salaryGrade, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new SalaryGradeGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "SalaryGrade";
	@Override
	public SalaryGradeDAO daoOf(RetailscmUserContext userContext) {
		return salaryGradeDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws SalaryGradeManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new SalaryGradeManagerException(message);

	}



 	protected SalaryGrade saveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, String [] tokensExpr) throws Exception{	
 		//return getSalaryGradeDAO().save(salaryGrade, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSalaryGrade(userContext, salaryGrade, tokens);
 	}
 	
 	protected SalaryGrade saveSalaryGradeDetail(RetailscmUserContext userContext, SalaryGrade salaryGrade) throws Exception{	

 		
 		return saveSalaryGrade(userContext, salaryGrade, allTokens());
 	}
 	
 	public SalaryGrade loadSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,salaryGrade, tokens);
 	}
 	
 	
 	 public SalaryGrade searchSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,salaryGrade, tokens);
 	}
 	
 	

 	protected SalaryGrade present(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,salaryGrade,tokens);
		
		
		SalaryGrade  salaryGradeToPresent = salaryGradeDaoOf(userContext).present(salaryGrade, tokens);
		
		List<BaseEntity> entityListToNaming = salaryGradeToPresent.collectRefercencesFromLists();
		salaryGradeDaoOf(userContext).alias(entityListToNaming);
		
		return  salaryGradeToPresent;
		
		
	}
 
 	
 	
 	public SalaryGrade loadSalaryGradeDetail(RetailscmUserContext userContext, String salaryGradeId) throws Exception{	
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, allTokens());
 		return present(userContext,salaryGrade, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String salaryGradeId) throws Exception{	
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, viewTokens());
 		return present(userContext,salaryGrade, allTokens());
		
 	}
 	protected SalaryGrade saveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String,Object>tokens) throws Exception{	
 		return salaryGradeDaoOf(userContext).save(salaryGrade, tokens);
 	}
 	protected SalaryGrade loadSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 
 		return salaryGradeDaoOf(userContext).load(salaryGradeId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens){
		super.addActions(userContext, salaryGrade, tokens);
		
		addAction(userContext, salaryGrade, tokens,"@create","createSalaryGrade","createSalaryGrade/","main","primary");
		addAction(userContext, salaryGrade, tokens,"@update","updateSalaryGrade","updateSalaryGrade/"+salaryGrade.getId()+"/","main","primary");
		addAction(userContext, salaryGrade, tokens,"@copy","cloneSalaryGrade","cloneSalaryGrade/"+salaryGrade.getId()+"/","main","primary");
		
		addAction(userContext, salaryGrade, tokens,"salary_grade.transfer_to_company","transferToAnotherCompany","transferToAnotherCompany/"+salaryGrade.getId()+"/","main","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.addEmployee","addEmployee","addEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.removeEmployee","removeEmployee","removeEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.updateEmployee","updateEmployee","updateEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.copyEmployeeFrom","copyEmployeeFrom","copyEmployeeFrom/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.addEmployeeSalarySheet","addEmployeeSalarySheet","addEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.removeEmployeeSalarySheet","removeEmployeeSalarySheet","removeEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.updateEmployeeSalarySheet","updateEmployeeSalarySheet","updateEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.copyEmployeeSalarySheetFrom","copyEmployeeSalarySheetFrom","copyEmployeeSalarySheetFrom/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SalaryGrade createSalaryGrade(RetailscmUserContext userContext, String code,String companyId,String name,String detailDescription) throws Exception
	//public SalaryGrade createSalaryGrade(RetailscmUserContext userContext,String code, String companyId, String name, String detailDescription) throws Exception
	{

		

		

		checkerOf(userContext).checkCodeOfSalaryGrade(code);
		checkerOf(userContext).checkNameOfSalaryGrade(name);
		checkerOf(userContext).checkDetailDescriptionOfSalaryGrade(detailDescription);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);


		SalaryGrade salaryGrade=createNewSalaryGrade();	

		salaryGrade.setCode(code);
			
		RetailStoreCountryCenter company = loadRetailStoreCountryCenter(userContext, companyId,emptyOptions());
		salaryGrade.setCompany(company);
		
		
		salaryGrade.setName(name);
		salaryGrade.setDetailDescription(detailDescription);

		salaryGrade = saveSalaryGrade(userContext, salaryGrade, emptyOptions());
		
		onNewInstanceCreated(userContext, salaryGrade);
		return salaryGrade;


	}
	protected SalaryGrade createNewSalaryGrade()
	{

		return new SalaryGrade();
	}

	protected void checkParamsForUpdatingSalaryGrade(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkVersionOfSalaryGrade( salaryGradeVersion);
		

		if(SalaryGrade.CODE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCodeOfSalaryGrade(parseString(newValueExpr));
		
			
		}		

		
		if(SalaryGrade.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfSalaryGrade(parseString(newValueExpr));
		
			
		}
		if(SalaryGrade.DETAIL_DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDetailDescriptionOfSalaryGrade(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);


	}



	public SalaryGrade clone(RetailscmUserContext userContext, String fromSalaryGradeId) throws Exception{

		return salaryGradeDaoOf(userContext).clone(fromSalaryGradeId, this.allTokens());
	}

	public SalaryGrade internalSaveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade) throws Exception
	{
		return internalSaveSalaryGrade(userContext, salaryGrade, allTokens());

	}
	public SalaryGrade internalSaveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);


		synchronized(salaryGrade){
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.
			if (salaryGrade.isChanged()){
			
			}
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, options);
			return salaryGrade;

		}

	}

	public SalaryGrade updateSalaryGrade(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);



		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		if(salaryGrade.getVersion() != salaryGradeVersion){
			String message = "The target version("+salaryGrade.getVersion()+") is not equals to version("+salaryGradeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(salaryGrade){
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.
			
			salaryGrade.changeProperty(property, newValueExpr);
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			//return saveSalaryGrade(userContext, salaryGrade, tokens().done());
		}

	}

	public SalaryGrade updateSalaryGradeProperty(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);

		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		if(salaryGrade.getVersion() != salaryGradeVersion){
			String message = "The target version("+salaryGrade.getVersion()+") is not equals to version("+salaryGradeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(salaryGrade){
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.

			salaryGrade.changeProperty(property, newValueExpr);
			
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			//return saveSalaryGrade(userContext, salaryGrade, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected SalaryGradeTokens tokens(){
		return SalaryGradeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SalaryGradeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortEmployeeListWith("id","desc")
		.sortEmployeeSalarySheetListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SalaryGradeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherCompany(RetailscmUserContext userContext, String salaryGradeId, String anotherCompanyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
 		checkerOf(userContext).checkIdOfRetailStoreCountryCenter(anotherCompanyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

 	}
 	public SalaryGrade transferToAnotherCompany(RetailscmUserContext userContext, String salaryGradeId, String anotherCompanyId) throws Exception
 	{
 		checkParamsForTransferingAnotherCompany(userContext, salaryGradeId,anotherCompanyId);
 
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());	
		synchronized(salaryGrade){
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreCountryCenter company = loadRetailStoreCountryCenter(userContext, anotherCompanyId, emptyOptions());		
			salaryGrade.updateCompany(company);		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, emptyOptions());
			
			return present(userContext,salaryGrade, allTokens());
			
		}

 	}

	


	public CandidateRetailStoreCountryCenter requestCandidateCompany(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<RetailStoreCountryCenter> candidateList = retailStoreCountryCenterDaoOf(userContext).requestCandidateRetailStoreCountryCenterForSalaryGrade(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected RetailStoreCountryCenter loadRetailStoreCountryCenter(RetailscmUserContext userContext, String newCompanyId, Map<String,Object> options) throws Exception
 	{

 		return retailStoreCountryCenterDaoOf(userContext).load(newCompanyId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String salaryGradeId, int salaryGradeVersion) throws Exception {
		//deleteInternal(userContext, salaryGradeId, salaryGradeVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String salaryGradeId, int salaryGradeVersion) throws Exception{

		salaryGradeDaoOf(userContext).delete(salaryGradeId, salaryGradeVersion);
	}

	public SalaryGrade forgetByAll(RetailscmUserContext userContext, String salaryGradeId, int salaryGradeVersion) throws Exception {
		return forgetByAllInternal(userContext, salaryGradeId, salaryGradeVersion);
	}
	protected SalaryGrade forgetByAllInternal(RetailscmUserContext userContext,
			String salaryGradeId, int salaryGradeVersion) throws Exception{

		return salaryGradeDaoOf(userContext).disconnectFromAll(salaryGradeId, salaryGradeVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SalaryGradeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return salaryGradeDaoOf(userContext).deleteAll();
	}


	//disconnect SalaryGrade with company in Employee
	protected SalaryGrade breakWithEmployeeByCompany(RetailscmUserContext userContext, String salaryGradeId, String companyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithCompany(salaryGrade, companyId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with department in Employee
	protected SalaryGrade breakWithEmployeeByDepartment(RetailscmUserContext userContext, String salaryGradeId, String departmentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithDepartment(salaryGrade, departmentId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with occupation in Employee
	protected SalaryGrade breakWithEmployeeByOccupation(RetailscmUserContext userContext, String salaryGradeId, String occupationId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithOccupation(salaryGrade, occupationId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with responsible_for in Employee
	protected SalaryGrade breakWithEmployeeByResponsibleFor(RetailscmUserContext userContext, String salaryGradeId, String responsibleForId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithResponsibleFor(salaryGrade, responsibleForId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with employee in EmployeeSalarySheet
	protected SalaryGrade breakWithEmployeeSalarySheetByEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetListWithEmployee(salaryGrade, employeeId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with paying_off in EmployeeSalarySheet
	protected SalaryGrade breakWithEmployeeSalarySheetByPayingOff(RetailscmUserContext userContext, String salaryGradeId, String payingOffId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetListWithPayingOff(salaryGrade, payingOffId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				return salaryGrade;
			}
	}






	protected void checkParamsForAddingEmployee(RetailscmUserContext userContext, String salaryGradeId, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);

		
		checkerOf(userContext).checkCompanyIdOfEmployee(companyId);
		
		checkerOf(userContext).checkTitleOfEmployee(title);
		
		checkerOf(userContext).checkDepartmentIdOfEmployee(departmentId);
		
		checkerOf(userContext).checkFamilyNameOfEmployee(familyName);
		
		checkerOf(userContext).checkGivenNameOfEmployee(givenName);
		
		checkerOf(userContext).checkEmailOfEmployee(email);
		
		checkerOf(userContext).checkCityOfEmployee(city);
		
		checkerOf(userContext).checkAddressOfEmployee(address);
		
		checkerOf(userContext).checkCellPhoneOfEmployee(cellPhone);
		
		checkerOf(userContext).checkOccupationIdOfEmployee(occupationId);
		
		checkerOf(userContext).checkResponsibleForIdOfEmployee(responsibleForId);
		
		checkerOf(userContext).checkSalaryAccountOfEmployee(salaryAccount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);


	}
	public  SalaryGrade addEmployee(RetailscmUserContext userContext, String salaryGradeId, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingEmployee(userContext,salaryGradeId,companyId, title, departmentId, familyName, givenName, email, city, address, cellPhone, occupationId, responsibleForId, salaryAccount,tokensExpr);

		Employee employee = createEmployee(userContext,companyId, title, departmentId, familyName, givenName, email, city, address, cellPhone, occupationId, responsibleForId, salaryAccount);

		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, emptyOptions());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.addEmployee( employee );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			
			userContext.getManagerGroup().getEmployeeManager().onNewInstanceCreated(userContext, employee);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingEmployeeProperties(RetailscmUserContext userContext, String salaryGradeId,String id,String title,String familyName,String givenName,String email,String city,String address,String cellPhone,String salaryAccount,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(id);

		checkerOf(userContext).checkTitleOfEmployee( title);
		checkerOf(userContext).checkFamilyNameOfEmployee( familyName);
		checkerOf(userContext).checkGivenNameOfEmployee( givenName);
		checkerOf(userContext).checkEmailOfEmployee( email);
		checkerOf(userContext).checkCityOfEmployee( city);
		checkerOf(userContext).checkAddressOfEmployee( address);
		checkerOf(userContext).checkCellPhoneOfEmployee( cellPhone);
		checkerOf(userContext).checkSalaryAccountOfEmployee( salaryAccount);

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade updateEmployeeProperties(RetailscmUserContext userContext, String salaryGradeId, String id,String title,String familyName,String givenName,String email,String city,String address,String cellPhone,String salaryAccount, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEmployeeProperties(userContext,salaryGradeId,id,title,familyName,givenName,email,city,address,cellPhone,salaryAccount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withEmployeeListList()
				.searchEmployeeListWith(Employee.ID_PROPERTY, "is", id).done();

		SalaryGrade salaryGradeToUpdate = loadSalaryGrade(userContext, salaryGradeId, options);

		if(salaryGradeToUpdate.getEmployeeList().isEmpty()){
			throw new SalaryGradeManagerException("Employee is NOT FOUND with id: '"+id+"'");
		}

		Employee item = salaryGradeToUpdate.getEmployeeList().first();

		item.updateTitle( title );
		item.updateFamilyName( familyName );
		item.updateGivenName( givenName );
		item.updateEmail( email );
		item.updateCity( city );
		item.updateAddress( address );
		item.updateCellPhone( cellPhone );
		item.updateSalaryAccount( salaryAccount );


		//checkParamsForAddingEmployee(userContext,salaryGradeId,name, code, used,tokensExpr);
		SalaryGrade salaryGrade = saveSalaryGrade(userContext, salaryGradeToUpdate, tokens().withEmployeeList().done());
		synchronized(salaryGrade){
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}


	protected Employee createEmployee(RetailscmUserContext userContext, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount) throws Exception{

		Employee employee = new Employee();
		
		
		RetailStoreCountryCenter  company = new RetailStoreCountryCenter();
		company.setId(companyId);		
		employee.setCompany(company);		
		employee.setTitle(title);		
		LevelThreeDepartment  department = new LevelThreeDepartment();
		department.setId(departmentId);		
		employee.setDepartment(department);		
		employee.setFamilyName(familyName);		
		employee.setGivenName(givenName);		
		employee.setEmail(email);		
		employee.setCity(city);		
		employee.setAddress(address);		
		employee.setCellPhone(cellPhone);		
		OccupationType  occupation = new OccupationType();
		occupation.setId(occupationId);		
		employee.setOccupation(occupation);		
		ResponsibilityType  responsibleFor = new ResponsibilityType();
		responsibleFor.setId(responsibleForId);		
		employee.setResponsibleFor(responsibleFor);		
		employee.setSalaryAccount(salaryAccount);		
		employee.setLastUpdateTime(userContext.now());
	
		
		return employee;


	}

	protected Employee createIndexedEmployee(String id, int version){

		Employee employee = new Employee();
		employee.setId(id);
		employee.setVersion(version);
		return employee;

	}

	protected void checkParamsForRemovingEmployeeList(RetailscmUserContext userContext, String salaryGradeId,
			String employeeIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		for(String employeeIdItem: employeeIds){
			checkerOf(userContext).checkIdOfEmployee(employeeIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade removeEmployeeList(RetailscmUserContext userContext, String salaryGradeId,
			String employeeIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingEmployeeList(userContext, salaryGradeId,  employeeIds, tokensExpr);


			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
			synchronized(salaryGrade){
				//Will be good when the salaryGrade loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				salaryGradeDaoOf(userContext).planToRemoveEmployeeList(salaryGrade, employeeIds, allTokens());
				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				deleteRelationListInGraph(userContext, salaryGrade.getEmployeeList());
				return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingEmployee(RetailscmUserContext userContext, String salaryGradeId,
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade removeEmployee(RetailscmUserContext userContext, String salaryGradeId,
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingEmployee(userContext,salaryGradeId, employeeId, employeeVersion,tokensExpr);

		Employee employee = createIndexedEmployee(employeeId, employeeVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.removeEmployee( employee );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			deleteRelationInGraph(userContext, employee);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingEmployee(RetailscmUserContext userContext, String salaryGradeId,
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade copyEmployeeFrom(RetailscmUserContext userContext, String salaryGradeId,
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingEmployee(userContext,salaryGradeId, employeeId, employeeVersion,tokensExpr);

		Employee employee = createIndexedEmployee(employeeId, employeeVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			employee.updateLastUpdateTime(userContext.now());

			salaryGrade.copyEmployeeFrom( employee );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			
			userContext.getManagerGroup().getEmployeeManager().onNewInstanceCreated(userContext, (Employee)salaryGrade.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId, int employeeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		

		if(Employee.TITLE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTitleOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.FAMILY_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkFamilyNameOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.GIVEN_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkGivenNameOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.EMAIL_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkEmailOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.CITY_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCityOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.ADDRESS_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAddressOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.CELL_PHONE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCellPhoneOfEmployee(parseString(newValueExpr));
		
		}
		
		if(Employee.SALARY_ACCOUNT_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSalaryAccountOfEmployee(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}

	public  SalaryGrade updateEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId, int employeeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingEmployee(userContext, salaryGradeId, employeeId, employeeVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withEmployeeList().searchEmployeeListWith(Employee.ID_PROPERTY, "eq", employeeId).done();



		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, loadTokens);

		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//salaryGrade.removeEmployee( employee );
			//make changes to AcceleraterAccount.
			Employee employeeIndex = createIndexedEmployee(employeeId, employeeVersion);

			Employee employee = salaryGrade.findTheEmployee(employeeIndex);
			if(employee == null){
				throw new SalaryGradeManagerException(employee+" is NOT FOUND" );
			}

			employee.changeProperty(property, newValueExpr);
			employee.updateLastUpdateTime(userContext.now());
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);

		
		checkerOf(userContext).checkEmployeeIdOfEmployeeSalarySheet(employeeId);
		
		checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet(baseSalary);
		
		checkerOf(userContext).checkBonusOfEmployeeSalarySheet(bonus);
		
		checkerOf(userContext).checkRewardOfEmployeeSalarySheet(reward);
		
		checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet(personalTax);
		
		checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet(socialSecurity);
		
		checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet(housingFound);
		
		checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet(jobInsurance);
		
		checkerOf(userContext).checkPayingOffIdOfEmployeeSalarySheet(payingOffId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);


	}
	public  SalaryGrade addEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingEmployeeSalarySheet(userContext,salaryGradeId,employeeId, baseSalary, bonus, reward, personalTax, socialSecurity, housingFound, jobInsurance, payingOffId,tokensExpr);

		EmployeeSalarySheet employeeSalarySheet = createEmployeeSalarySheet(userContext,employeeId, baseSalary, bonus, reward, personalTax, socialSecurity, housingFound, jobInsurance, payingOffId);

		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, emptyOptions());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.addEmployeeSalarySheet( employeeSalarySheet );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			
			userContext.getManagerGroup().getEmployeeSalarySheetManager().onNewInstanceCreated(userContext, employeeSalarySheet);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingEmployeeSalarySheetProperties(RetailscmUserContext userContext, String salaryGradeId,String id,BigDecimal baseSalary,BigDecimal bonus,BigDecimal reward,BigDecimal personalTax,BigDecimal socialSecurity,BigDecimal housingFound,BigDecimal jobInsurance,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(id);

		checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet( baseSalary);
		checkerOf(userContext).checkBonusOfEmployeeSalarySheet( bonus);
		checkerOf(userContext).checkRewardOfEmployeeSalarySheet( reward);
		checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet( personalTax);
		checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet( socialSecurity);
		checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet( housingFound);
		checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet( jobInsurance);

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade updateEmployeeSalarySheetProperties(RetailscmUserContext userContext, String salaryGradeId, String id,BigDecimal baseSalary,BigDecimal bonus,BigDecimal reward,BigDecimal personalTax,BigDecimal socialSecurity,BigDecimal housingFound,BigDecimal jobInsurance, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEmployeeSalarySheetProperties(userContext,salaryGradeId,id,baseSalary,bonus,reward,personalTax,socialSecurity,housingFound,jobInsurance,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withEmployeeSalarySheetListList()
				.searchEmployeeSalarySheetListWith(EmployeeSalarySheet.ID_PROPERTY, "is", id).done();

		SalaryGrade salaryGradeToUpdate = loadSalaryGrade(userContext, salaryGradeId, options);

		if(salaryGradeToUpdate.getEmployeeSalarySheetList().isEmpty()){
			throw new SalaryGradeManagerException("EmployeeSalarySheet is NOT FOUND with id: '"+id+"'");
		}

		EmployeeSalarySheet item = salaryGradeToUpdate.getEmployeeSalarySheetList().first();

		item.updateBaseSalary( baseSalary );
		item.updateBonus( bonus );
		item.updateReward( reward );
		item.updatePersonalTax( personalTax );
		item.updateSocialSecurity( socialSecurity );
		item.updateHousingFound( housingFound );
		item.updateJobInsurance( jobInsurance );


		//checkParamsForAddingEmployeeSalarySheet(userContext,salaryGradeId,name, code, used,tokensExpr);
		SalaryGrade salaryGrade = saveSalaryGrade(userContext, salaryGradeToUpdate, tokens().withEmployeeSalarySheetList().done());
		synchronized(salaryGrade){
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}


	protected EmployeeSalarySheet createEmployeeSalarySheet(RetailscmUserContext userContext, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId) throws Exception{

		EmployeeSalarySheet employeeSalarySheet = new EmployeeSalarySheet();
		
		
		Employee  employee = new Employee();
		employee.setId(employeeId);		
		employeeSalarySheet.setEmployee(employee);		
		employeeSalarySheet.setBaseSalary(baseSalary);		
		employeeSalarySheet.setBonus(bonus);		
		employeeSalarySheet.setReward(reward);		
		employeeSalarySheet.setPersonalTax(personalTax);		
		employeeSalarySheet.setSocialSecurity(socialSecurity);		
		employeeSalarySheet.setHousingFound(housingFound);		
		employeeSalarySheet.setJobInsurance(jobInsurance);		
		PayingOff  payingOff = new PayingOff();
		payingOff.setId(payingOffId);		
		employeeSalarySheet.setPayingOff(payingOff);
	
		
		return employeeSalarySheet;


	}

	protected EmployeeSalarySheet createIndexedEmployeeSalarySheet(String id, int version){

		EmployeeSalarySheet employeeSalarySheet = new EmployeeSalarySheet();
		employeeSalarySheet.setId(id);
		employeeSalarySheet.setVersion(version);
		return employeeSalarySheet;

	}

	protected void checkParamsForRemovingEmployeeSalarySheetList(RetailscmUserContext userContext, String salaryGradeId,
			String employeeSalarySheetIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		for(String employeeSalarySheetIdItem: employeeSalarySheetIds){
			checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade removeEmployeeSalarySheetList(RetailscmUserContext userContext, String salaryGradeId,
			String employeeSalarySheetIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingEmployeeSalarySheetList(userContext, salaryGradeId,  employeeSalarySheetIds, tokensExpr);


			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
			synchronized(salaryGrade){
				//Will be good when the salaryGrade loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetList(salaryGrade, employeeSalarySheetIds, allTokens());
				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				deleteRelationListInGraph(userContext, salaryGrade.getEmployeeSalarySheetList());
				return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId,
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade removeEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId,
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingEmployeeSalarySheet(userContext,salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion,tokensExpr);

		EmployeeSalarySheet employeeSalarySheet = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.removeEmployeeSalarySheet( employeeSalarySheet );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			deleteRelationInGraph(userContext, employeeSalarySheet);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId,
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}
	public  SalaryGrade copyEmployeeSalarySheetFrom(RetailscmUserContext userContext, String salaryGradeId,
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingEmployeeSalarySheet(userContext,salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion,tokensExpr);

		EmployeeSalarySheet employeeSalarySheet = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			salaryGrade.copyEmployeeSalarySheetFrom( employeeSalarySheet );
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			
			userContext.getManagerGroup().getEmployeeSalarySheetManager().onNewInstanceCreated(userContext, (EmployeeSalarySheet)salaryGrade.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeSalarySheetId, int employeeSalarySheetVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		

		if(EmployeeSalarySheet.BASE_SALARY_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.BONUS_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBonusOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.REWARD_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkRewardOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.PERSONAL_TAX_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.SOCIAL_SECURITY_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.HOUSING_FOUND_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
		if(EmployeeSalarySheet.JOB_INSURANCE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	}

	public  SalaryGrade updateEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeSalarySheetId, int employeeSalarySheetVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingEmployeeSalarySheet(userContext, salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withEmployeeSalarySheetList().searchEmployeeSalarySheetListWith(EmployeeSalarySheet.ID_PROPERTY, "eq", employeeSalarySheetId).done();



		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, loadTokens);

		synchronized(salaryGrade){
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//salaryGrade.removeEmployeeSalarySheet( employeeSalarySheet );
			//make changes to AcceleraterAccount.
			EmployeeSalarySheet employeeSalarySheetIndex = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);

			EmployeeSalarySheet employeeSalarySheet = salaryGrade.findTheEmployeeSalarySheet(employeeSalarySheetIndex);
			if(employeeSalarySheet == null){
				throw new SalaryGradeManagerException(employeeSalarySheet+" is NOT FOUND" );
			}

			employeeSalarySheet.changeProperty(property, newValueExpr);
			
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(RetailscmUserContext userContext, SalaryGrade newCreated) throws Exception{
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
		//   SalaryGrade newSalaryGrade = this.createSalaryGrade(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newSalaryGrade
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, SalaryGrade.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<SalaryGrade> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<RetailStoreCountryCenter> companyList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, RetailStoreCountryCenter.class);
		userContext.getDAOGroup().enhanceList(companyList, RetailStoreCountryCenter.class);


    }
	
	public Object listByCompany(RetailscmUserContext userContext,String companyId) throws Exception {
		return listPageByCompany(userContext, companyId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByCompany(RetailscmUserContext userContext,String companyId, int start, int count) throws Exception {
		SmartList<SalaryGrade> list = salaryGradeDaoOf(userContext).findSalaryGradeByCompany(companyId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(SalaryGrade.class);
		page.setContainerObject(RetailStoreCountryCenter.withId(companyId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("工资等级列表");
		page.setRequestName("listByCompany");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByCompany/%s/",  getBeanName(), companyId)));

		page.assemblerContent(userContext, "listByCompany");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String salaryGradeId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getSalaryGradeDetailScope().clone();
		SalaryGrade merchantObj = (SalaryGrade) this.view(userContext, salaryGradeId);
    String merchantObjId = salaryGradeId;
    String linkToUrl =	"salaryGradeManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "工资等级"+"详情";
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
				MapUtil.put("id", "2-code")
				    .put("fieldName", "code")
				    .put("label", "代码")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("code", merchantObj.getCode());

		propList.add(
				MapUtil.put("id", "3-company")
				    .put("fieldName", "company")
				    .put("label", "公司")
				    .put("type", "auto")
				    .put("linkToUrl", "retailStoreCountryCenterManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"description\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("company", merchantObj.getCompany());

		propList.add(
				MapUtil.put("id", "4-name")
				    .put("fieldName", "name")
				    .put("label", "名称")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("name", merchantObj.getName());

		propList.add(
				MapUtil.put("id", "5-detailDescription")
				    .put("fieldName", "detailDescription")
				    .put("label", "详细描述")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("detailDescription", merchantObj.getDetailDescription());

		//处理 sectionList

		//处理Section：employeeListSection
		Map employeeListSection = ListofUtils.buildSection(
		    "employeeListSection",
		    "员工列表",
		    null,
		    "",
		    "__no_group",
		    "employeeManager/listByCurrentSalaryGrade/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(employeeListSection);

		result.put("employeeListSection", ListofUtils.toShortList(merchantObj.getEmployeeList(), "employee"));
		vscope.field("employeeListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( Employee.class.getName(), null));

		//处理Section：employeeSalarySheetListSection
		Map employeeSalarySheetListSection = ListofUtils.buildSection(
		    "employeeSalarySheetListSection",
		    "员工工资表",
		    null,
		    "",
		    "__no_group",
		    "employeeSalarySheetManager/listByCurrentSalaryGrade/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(employeeSalarySheetListSection);

		result.put("employeeSalarySheetListSection", ListofUtils.toShortList(merchantObj.getEmployeeSalarySheetList(), "employeeSalarySheet"));
		vscope.field("employeeSalarySheetListSection", RetailscmListOfViewScope.getInstance()
					.getListOfViewScope( EmployeeSalarySheet.class.getName(), null));

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


