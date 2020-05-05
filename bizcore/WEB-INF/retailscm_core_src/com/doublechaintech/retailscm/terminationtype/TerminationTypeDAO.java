
package com.doublechaintech.retailscm.terminationtype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.doublechaintech.retailscm.BaseDAO;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.MultipleAccessKey;
import com.doublechaintech.retailscm.RetailscmUserContext;

import com.doublechaintech.retailscm.termination.Termination;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;

import com.doublechaintech.retailscm.termination.TerminationDAO;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenterDAO;


public interface TerminationTypeDAO extends BaseDAO{

	public SmartList<TerminationType> loadAll();
	public TerminationType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<TerminationType> terminationTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public TerminationType present(TerminationType terminationType,Map<String,Object> options) throws Exception;
	public TerminationType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public TerminationType save(TerminationType terminationType,Map<String,Object> options);
	public SmartList<TerminationType> saveTerminationTypeList(SmartList<TerminationType> terminationTypeList,Map<String,Object> options);
	public SmartList<TerminationType> removeTerminationTypeList(SmartList<TerminationType> terminationTypeList,Map<String,Object> options);
	public SmartList<TerminationType> findTerminationTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTerminationTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTerminationTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String terminationTypeId, int version) throws Exception;
	public TerminationType disconnectFromAll(String terminationTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TerminationDAO getTerminationDAO();
		
	
 	public SmartList<TerminationType> requestCandidateTerminationTypeForTermination(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public TerminationType planToRemoveTerminationList(TerminationType terminationType, String terminationIds[], Map<String,Object> options)throws Exception;


	//disconnect TerminationType with reason in Termination
	public TerminationType planToRemoveTerminationListWithReason(TerminationType terminationType, String reasonId, Map<String,Object> options)throws Exception;
	public int countTerminationListWithReason(String terminationTypeId, String reasonId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<TerminationType> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateTerminationType executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<TerminationType> findTerminationTypeByCompany(String retailStoreCountryCenterId, Map<String,Object> options);
 	public int countTerminationTypeByCompany(String retailStoreCountryCenterId, Map<String,Object> options);
 	public Map<String, Integer> countTerminationTypeByCompanyIds(String[] ids, Map<String,Object> options);
 	public SmartList<TerminationType> findTerminationTypeByCompany(String retailStoreCountryCenterId, int start, int count, Map<String,Object> options);
 	public void analyzeTerminationTypeByCompany(SmartList<TerminationType> resultList, String retailStoreCountryCenterId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Termination的type的TerminationList
	public SmartList<Termination> loadOurTerminationList(RetailscmUserContext userContext, List<TerminationType> us, Map<String,Object> options) throws Exception;
	
}


