
package com.doublechaintech.retailscm.skilltype;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import com.terapico.caf.baseelement.CandidateQuery;
import com.terapico.utils.TextUtil;

import com.doublechaintech.retailscm.RetailscmBaseDAOImpl;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.AccessKey;
import com.doublechaintech.retailscm.DateKey;
import com.doublechaintech.retailscm.StatsInfo;
import com.doublechaintech.retailscm.StatsItem;

import com.doublechaintech.retailscm.MultipleAccessKey;
import com.doublechaintech.retailscm.RetailscmUserContext;


import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.employeeskill.EmployeeSkill;

import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenterDAO;
import com.doublechaintech.retailscm.employeeskill.EmployeeSkillDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class SkillTypeJDBCTemplateDAO extends RetailscmBaseDAOImpl implements SkillTypeDAO{

	protected RetailStoreCountryCenterDAO retailStoreCountryCenterDAO;
	public void setRetailStoreCountryCenterDAO(RetailStoreCountryCenterDAO retailStoreCountryCenterDAO){
 	
 		if(retailStoreCountryCenterDAO == null){
 			throw new IllegalStateException("Do not try to set retailStoreCountryCenterDAO to null.");
 		}
	 	this.retailStoreCountryCenterDAO = retailStoreCountryCenterDAO;
 	}
 	public RetailStoreCountryCenterDAO getRetailStoreCountryCenterDAO(){
 		if(this.retailStoreCountryCenterDAO == null){
 			throw new IllegalStateException("The retailStoreCountryCenterDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.retailStoreCountryCenterDAO;
 	}	

	protected EmployeeSkillDAO employeeSkillDAO;
	public void setEmployeeSkillDAO(EmployeeSkillDAO employeeSkillDAO){
 	
 		if(employeeSkillDAO == null){
 			throw new IllegalStateException("Do not try to set employeeSkillDAO to null.");
 		}
	 	this.employeeSkillDAO = employeeSkillDAO;
 	}
 	public EmployeeSkillDAO getEmployeeSkillDAO(){
 		if(this.employeeSkillDAO == null){
 			throw new IllegalStateException("The employeeSkillDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.employeeSkillDAO;
 	}	

	
	/*
	protected SkillType load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalSkillType(accessKey, options);
	}
	*/
	
	public SmartList<SkillType> loadAll() {
	    return this.loadAll(getSkillTypeMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public SkillType load(String id,Map<String,Object> options) throws Exception{
		return loadInternalSkillType(SkillTypeTable.withId(id), options);
	}
	
	
	
	public SkillType save(SkillType skillType,Map<String,Object> options){
		
		String methodName="save(SkillType skillType,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(skillType, methodName, "skillType");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalSkillType(skillType,options);
	}
	public SkillType clone(String skillTypeId, Map<String,Object> options) throws Exception{
	
		return clone(SkillTypeTable.withId(skillTypeId),options);
	}
	
	protected SkillType clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String skillTypeId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		SkillType newSkillType = loadInternalSkillType(accessKey, options);
		newSkillType.setVersion(0);
		
		
 		
 		if(isSaveEmployeeSkillListEnabled(options)){
 			for(EmployeeSkill item: newSkillType.getEmployeeSkillList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalSkillType(newSkillType,options);
		
		return newSkillType;
	}
	
	
	
	

	protected void throwIfHasException(String skillTypeId,int version,int count) throws Exception{
		if (count == 1) {
			throw new SkillTypeVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new SkillTypeNotFoundException(
					"The " + this.getTableName() + "(" + skillTypeId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String skillTypeId, int version) throws Exception{
	
		String methodName="delete(String skillTypeId, int version)";
		assertMethodArgumentNotNull(skillTypeId, methodName, "skillTypeId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{skillTypeId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(skillTypeId,version);
		}
		
	
	}
	
	
	
	
	

	public SkillType disconnectFromAll(String skillTypeId, int version) throws Exception{
	
		
		SkillType skillType = loadInternalSkillType(SkillTypeTable.withId(skillTypeId), emptyOptions());
		skillType.clearFromAll();
		this.saveSkillType(skillType);
		return skillType;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return SkillTypeTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "skill_type";
	}
	@Override
	protected String getBeanName() {
		
		return "skillType";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return SkillTypeTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractCompanyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SkillTypeTokens.COMPANY);
 	}

 	protected boolean isSaveCompanyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SkillTypeTokens.COMPANY);
 	}
 	

 	
 
		
	
	protected boolean isExtractEmployeeSkillListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SkillTypeTokens.EMPLOYEE_SKILL_LIST);
 	}
 	protected boolean isAnalyzeEmployeeSkillListEnabled(Map<String,Object> options){		 		
 		return SkillTypeTokens.of(options).analyzeEmployeeSkillListEnabled();
 	}
	
	protected boolean isSaveEmployeeSkillListEnabled(Map<String,Object> options){
		return checkOptions(options, SkillTypeTokens.EMPLOYEE_SKILL_LIST);
		
 	}
 	
		

	

	protected SkillTypeMapper getSkillTypeMapper(){
		return new SkillTypeMapper();
	}

	
	
	protected SkillType extractSkillType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			SkillType skillType = loadSingleObject(accessKey, getSkillTypeMapper());
			return skillType;
		}catch(EmptyResultDataAccessException e){
			throw new SkillTypeNotFoundException("SkillType("+accessKey+") is not found!");
		}

	}

	
	

	protected SkillType loadInternalSkillType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		SkillType skillType = extractSkillType(accessKey, loadOptions);
 	
 		if(isExtractCompanyEnabled(loadOptions)){
	 		extractCompany(skillType, loadOptions);
 		}
 
		
		if(isExtractEmployeeSkillListEnabled(loadOptions)){
	 		extractEmployeeSkillList(skillType, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeEmployeeSkillListEnabled(loadOptions)){
	 		analyzeEmployeeSkillList(skillType, loadOptions);
 		}
 		
		
		return skillType;
		
	}

	 

 	protected SkillType extractCompany(SkillType skillType, Map<String,Object> options) throws Exception{

		if(skillType.getCompany() == null){
			return skillType;
		}
		String companyId = skillType.getCompany().getId();
		if( companyId == null){
			return skillType;
		}
		RetailStoreCountryCenter company = getRetailStoreCountryCenterDAO().load(companyId,options);
		if(company != null){
			skillType.setCompany(company);
		}
		
 		
 		return skillType;
 	}
 		
 
		
	protected void enhanceEmployeeSkillList(SmartList<EmployeeSkill> employeeSkillList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SkillType extractEmployeeSkillList(SkillType skillType, Map<String,Object> options){
		
		
		if(skillType == null){
			return null;
		}
		if(skillType.getId() == null){
			return skillType;
		}

		
		
		SmartList<EmployeeSkill> employeeSkillList = getEmployeeSkillDAO().findEmployeeSkillBySkillType(skillType.getId(),options);
		if(employeeSkillList != null){
			enhanceEmployeeSkillList(employeeSkillList,options);
			skillType.setEmployeeSkillList(employeeSkillList);
		}
		
		return skillType;
	
	}	
	
	protected SkillType analyzeEmployeeSkillList(SkillType skillType, Map<String,Object> options){
		
		
		if(skillType == null){
			return null;
		}
		if(skillType.getId() == null){
			return skillType;
		}

		
		
		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();
		if(employeeSkillList != null){
			getEmployeeSkillDAO().analyzeEmployeeSkillBySkillType(employeeSkillList, skillType.getId(), options);
			
		}
		
		return skillType;
	
	}	
	
		
		
  	
 	public SmartList<SkillType> findSkillTypeByCompany(String retailStoreCountryCenterId,Map<String,Object> options){
 	
  		SmartList<SkillType> resultList = queryWith(SkillTypeTable.COLUMN_COMPANY, retailStoreCountryCenterId, options, getSkillTypeMapper());
		// analyzeSkillTypeByCompany(resultList, retailStoreCountryCenterId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SkillType> findSkillTypeByCompany(String retailStoreCountryCenterId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SkillType> resultList =  queryWithRange(SkillTypeTable.COLUMN_COMPANY, retailStoreCountryCenterId, options, getSkillTypeMapper(), start, count);
 		//analyzeSkillTypeByCompany(resultList, retailStoreCountryCenterId, options);
 		return resultList;
 		
 	}
 	public void analyzeSkillTypeByCompany(SmartList<SkillType> resultList, String retailStoreCountryCenterId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countSkillTypeByCompany(String retailStoreCountryCenterId,Map<String,Object> options){

 		return countWith(SkillTypeTable.COLUMN_COMPANY, retailStoreCountryCenterId, options);
 	}
 	@Override
	public Map<String, Integer> countSkillTypeByCompanyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SkillTypeTable.COLUMN_COMPANY, ids, options);
	}
 	
 	
		
		
		

	

	protected SkillType saveSkillType(SkillType  skillType){
		
		if(!skillType.isChanged()){
			return skillType;
		}
		
		
		String SQL=this.getSaveSkillTypeSQL(skillType);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveSkillTypeParameters(skillType);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		skillType.incVersion();
		return skillType;
	
	}
	public SmartList<SkillType> saveSkillTypeList(SmartList<SkillType> skillTypeList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitSkillTypeList(skillTypeList);
		
		batchSkillTypeCreate((List<SkillType>)lists[CREATE_LIST_INDEX]);
		
		batchSkillTypeUpdate((List<SkillType>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(SkillType skillType:skillTypeList){
			if(skillType.isChanged()){
				skillType.incVersion();
			}
			
		
		}
		
		
		return skillTypeList;
	}

	public SmartList<SkillType> removeSkillTypeList(SmartList<SkillType> skillTypeList,Map<String,Object> options){
		
		
		super.removeList(skillTypeList, options);
		
		return skillTypeList;
		
		
	}
	
	protected List<Object[]> prepareSkillTypeBatchCreateArgs(List<SkillType> skillTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SkillType skillType:skillTypeList ){
			Object [] parameters = prepareSkillTypeCreateParameters(skillType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareSkillTypeBatchUpdateArgs(List<SkillType> skillTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SkillType skillType:skillTypeList ){
			if(!skillType.isChanged()){
				continue;
			}
			Object [] parameters = prepareSkillTypeUpdateParameters(skillType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchSkillTypeCreate(List<SkillType> skillTypeList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareSkillTypeBatchCreateArgs(skillTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchSkillTypeUpdate(List<SkillType> skillTypeList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareSkillTypeBatchUpdateArgs(skillTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitSkillTypeList(List<SkillType> skillTypeList){
		
		List<SkillType> skillTypeCreateList=new ArrayList<SkillType>();
		List<SkillType> skillTypeUpdateList=new ArrayList<SkillType>();
		
		for(SkillType skillType: skillTypeList){
			if(isUpdateRequest(skillType)){
				skillTypeUpdateList.add( skillType);
				continue;
			}
			skillTypeCreateList.add(skillType);
		}
		
		return new Object[]{skillTypeCreateList,skillTypeUpdateList};
	}
	
	protected boolean isUpdateRequest(SkillType skillType){
 		return skillType.getVersion() > 0;
 	}
 	protected String getSaveSkillTypeSQL(SkillType skillType){
 		if(isUpdateRequest(skillType)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveSkillTypeParameters(SkillType skillType){
 		if(isUpdateRequest(skillType) ){
 			return prepareSkillTypeUpdateParameters(skillType);
 		}
 		return prepareSkillTypeCreateParameters(skillType);
 	}
 	protected Object[] prepareSkillTypeUpdateParameters(SkillType skillType){
 		Object[] parameters = new Object[6];
 
 		
 		parameters[0] = skillType.getCode();
 		 	
 		if(skillType.getCompany() != null){
 			parameters[1] = skillType.getCompany().getId();
 		}
 
 		
 		parameters[2] = skillType.getDescription();
 				
 		parameters[3] = skillType.nextVersion();
 		parameters[4] = skillType.getId();
 		parameters[5] = skillType.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareSkillTypeCreateParameters(SkillType skillType){
		Object[] parameters = new Object[4];
		String newSkillTypeId=getNextId();
		skillType.setId(newSkillTypeId);
		parameters[0] =  skillType.getId();
 
 		
 		parameters[1] = skillType.getCode();
 		 	
 		if(skillType.getCompany() != null){
 			parameters[2] = skillType.getCompany().getId();
 		
 		}
 		
 		
 		parameters[3] = skillType.getDescription();
 				
 				
 		return parameters;
 	}
 	
	protected SkillType saveInternalSkillType(SkillType skillType, Map<String,Object> options){
		
		saveSkillType(skillType);
 	
 		if(isSaveCompanyEnabled(options)){
	 		saveCompany(skillType, options);
 		}
 
		
		if(isSaveEmployeeSkillListEnabled(options)){
	 		saveEmployeeSkillList(skillType, options);
	 		//removeEmployeeSkillList(skillType, options);
	 		//Not delete the record
	 		
 		}		
		
		return skillType;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected SkillType saveCompany(SkillType skillType, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(skillType.getCompany() == null){
 			return skillType;//do nothing when it is null
 		}
 		
 		getRetailStoreCountryCenterDAO().save(skillType.getCompany(),options);
 		return skillType;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public SkillType planToRemoveEmployeeSkillList(SkillType skillType, String employeeSkillIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EmployeeSkill.SKILL_TYPE_PROPERTY, skillType.getId());
		key.put(EmployeeSkill.ID_PROPERTY, employeeSkillIds);
		
		SmartList<EmployeeSkill> externalEmployeeSkillList = getEmployeeSkillDAO().
				findEmployeeSkillWithKey(key, options);
		if(externalEmployeeSkillList == null){
			return skillType;
		}
		if(externalEmployeeSkillList.isEmpty()){
			return skillType;
		}
		
		for(EmployeeSkill employeeSkillItem: externalEmployeeSkillList){

			employeeSkillItem.clearFromAll();
		}
		
		
		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();		
		employeeSkillList.addAllToRemoveList(externalEmployeeSkillList);
		return skillType;	
	
	}


	//disconnect SkillType with employee in EmployeeSkill
	public SkillType planToRemoveEmployeeSkillListWithEmployee(SkillType skillType, String employeeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EmployeeSkill.SKILL_TYPE_PROPERTY, skillType.getId());
		key.put(EmployeeSkill.EMPLOYEE_PROPERTY, employeeId);
		
		SmartList<EmployeeSkill> externalEmployeeSkillList = getEmployeeSkillDAO().
				findEmployeeSkillWithKey(key, options);
		if(externalEmployeeSkillList == null){
			return skillType;
		}
		if(externalEmployeeSkillList.isEmpty()){
			return skillType;
		}
		
		for(EmployeeSkill employeeSkillItem: externalEmployeeSkillList){
			employeeSkillItem.clearEmployee();
			employeeSkillItem.clearSkillType();
			
		}
		
		
		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();		
		employeeSkillList.addAllToRemoveList(externalEmployeeSkillList);
		return skillType;
	}
	
	public int countEmployeeSkillListWithEmployee(String skillTypeId, String employeeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EmployeeSkill.SKILL_TYPE_PROPERTY, skillTypeId);
		key.put(EmployeeSkill.EMPLOYEE_PROPERTY, employeeId);
		
		int count = getEmployeeSkillDAO().countEmployeeSkillWithKey(key, options);
		return count;
	}
	

		
	protected SkillType saveEmployeeSkillList(SkillType skillType, Map<String,Object> options){
		
		
		
		
		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();
		if(employeeSkillList == null){
			//null list means nothing
			return skillType;
		}
		SmartList<EmployeeSkill> mergedUpdateEmployeeSkillList = new SmartList<EmployeeSkill>();
		
		
		mergedUpdateEmployeeSkillList.addAll(employeeSkillList); 
		if(employeeSkillList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateEmployeeSkillList.addAll(employeeSkillList.getToRemoveList());
			employeeSkillList.removeAll(employeeSkillList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getEmployeeSkillDAO().saveEmployeeSkillList(mergedUpdateEmployeeSkillList,options);
		
		if(employeeSkillList.getToRemoveList() != null){
			employeeSkillList.removeAll(employeeSkillList.getToRemoveList());
		}
		
		
		return skillType;
	
	}
	
	protected SkillType removeEmployeeSkillList(SkillType skillType, Map<String,Object> options){
	
	
		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();
		if(employeeSkillList == null){
			return skillType;
		}	
	
		SmartList<EmployeeSkill> toRemoveEmployeeSkillList = employeeSkillList.getToRemoveList();
		
		if(toRemoveEmployeeSkillList == null){
			return skillType;
		}
		if(toRemoveEmployeeSkillList.isEmpty()){
			return skillType;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getEmployeeSkillDAO().removeEmployeeSkillList(toRemoveEmployeeSkillList,options);
		
		return skillType;
	
	}
	
	

 	
 	
	
	
	
		

	public SkillType present(SkillType skillType,Map<String, Object> options){
	
		presentEmployeeSkillList(skillType,options);

		return skillType;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected SkillType presentEmployeeSkillList(
			SkillType skillType,
			Map<String, Object> options) {

		SmartList<EmployeeSkill> employeeSkillList = skillType.getEmployeeSkillList();		
				SmartList<EmployeeSkill> newList= presentSubList(skillType.getId(),
				employeeSkillList,
				options,
				getEmployeeSkillDAO()::countEmployeeSkillBySkillType,
				getEmployeeSkillDAO()::findEmployeeSkillBySkillType
				);

		
		skillType.setEmployeeSkillList(newList);
		

		return skillType;
	}			
		

	
    public SmartList<SkillType> requestCandidateSkillTypeForEmployeeSkill(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SkillTypeTable.COLUMN_CODE, SkillTypeTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getSkillTypeMapper());
    }
		

	protected String getTableName(){
		return SkillTypeTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<SkillType> skillTypeList) {		
		this.enhanceListInternal(skillTypeList, this.getSkillTypeMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:EmployeeSkill的skillType的EmployeeSkillList
	public SmartList<EmployeeSkill> loadOurEmployeeSkillList(RetailscmUserContext userContext, List<SkillType> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EmployeeSkill.SKILL_TYPE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<EmployeeSkill> loadedObjs = userContext.getDAOGroup().getEmployeeSkillDAO().findEmployeeSkillWithKey(key, options);
		Map<String, List<EmployeeSkill>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSkillType().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<EmployeeSkill> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<EmployeeSkill> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setEmployeeSkillList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<SkillType> skillTypeList = ownerEntity.collectRefsWithType(SkillType.INTERNAL_TYPE);
		this.enhanceList(skillTypeList);
		
	}
	
	@Override
	public SmartList<SkillType> findSkillTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getSkillTypeMapper());

	}
	@Override
	public int countSkillTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countSkillTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<SkillType> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getSkillTypeMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateSkillType executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateSkillType result = new CandidateSkillType();
		int pageNo = Math.max(1, query.getPageNo());
		result.setOwnerClass(TextUtil.toCamelCase(query.getOwnerType()));
		result.setOwnerId(query.getOwnerId());
		result.setFilterKey(query.getFilterKey());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName(TextUtil.uncapFirstChar(TextUtil.toCamelCase("displayName")));
		result.setGroupByFieldName(TextUtil.uncapFirstChar(TextUtil.toCamelCase(query.getGroupBy())));

		SmartList candidateList = queryList(sql, parmeters);
		this.alias(candidateList);
		result.setCandidates(candidateList);
		int offSet = (pageNo - 1 ) * query.getPageSize();
		if (candidateList.size() > query.getPageSize()) {
			result.setTotalPage(pageNo+1);
		}else {
			result.setTotalPage(pageNo);
		}
		return result;
	}
	
	

}


