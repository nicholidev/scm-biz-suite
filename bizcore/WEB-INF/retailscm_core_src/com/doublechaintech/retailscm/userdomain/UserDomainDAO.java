
package com.doublechaintech.retailscm.userdomain;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.doublechaintech.retailscm.BaseDAO;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.MultipleAccessKey;
import com.doublechaintech.retailscm.RetailscmUserContext;

import com.doublechaintech.retailscm.secuser.SecUser;
import com.doublechaintech.retailscm.userallowlist.UserAllowList;
import com.doublechaintech.retailscm.publickeytype.PublicKeyType;

import com.doublechaintech.retailscm.secuser.SecUserDAO;
import com.doublechaintech.retailscm.publickeytype.PublicKeyTypeDAO;
import com.doublechaintech.retailscm.userallowlist.UserAllowListDAO;


public interface UserDomainDAO extends BaseDAO{

	public SmartList<UserDomain> loadAll();
	public Stream<UserDomain> loadAllAsStream();
	public UserDomain load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<UserDomain> userDomainList);
	public void collectAndEnhance(BaseEntity ownerEntity);

	public void alias(List<BaseEntity> entityList);


	

	public UserDomain present(UserDomain userDomain,Map<String,Object> options) throws Exception;
	public UserDomain clone(String id, Map<String,Object> options) throws Exception;

	

	public UserDomain save(UserDomain userDomain,Map<String,Object> options);
	public SmartList<UserDomain> saveUserDomainList(SmartList<UserDomain> userDomainList,Map<String,Object> options);
	public SmartList<UserDomain> removeUserDomainList(SmartList<UserDomain> userDomainList,Map<String,Object> options);
	public SmartList<UserDomain> findUserDomainWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserDomainWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserDomainWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userDomainId, int version) throws Exception;
	public UserDomain disconnectFromAll(String userDomainId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserAllowListDAO getUserAllowListDAO();
		
	public SecUserDAO getSecUserDAO();
		
	public PublicKeyTypeDAO getPublicKeyTypeDAO();
		
	
 	public SmartList<UserDomain> requestCandidateUserDomainForUserAllowList(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<UserDomain> requestCandidateUserDomainForSecUser(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<UserDomain> requestCandidateUserDomainForPublicKeyType(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public UserDomain planToRemoveUserAllowListList(UserDomain userDomain, String userAllowListIds[], Map<String,Object> options)throws Exception;


	public UserDomain planToRemoveSecUserList(UserDomain userDomain, String secUserIds[], Map<String,Object> options)throws Exception;


	public UserDomain planToRemovePublicKeyTypeList(UserDomain userDomain, String publicKeyTypeIds[], Map<String,Object> options)throws Exception;



	public SmartList<UserDomain> queryList(String sql, Object ... parmeters);
	public Stream<UserDomain> queryStream(String sql, Object... parameters) ;
	public int count(String sql, Object ... parmeters);
	public CandidateUserDomain executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;

	// 需要一个加载引用我的对象的enhance方法:UserAllowList的domain的UserAllowListList
	public SmartList<UserAllowList> loadOurUserAllowListList(RetailscmUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:SecUser的domain的SecUserList
	public SmartList<SecUser> loadOurSecUserList(RetailscmUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PublicKeyType的domain的PublicKeyTypeList
	public SmartList<PublicKeyType> loadOurPublicKeyTypeList(RetailscmUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
}


