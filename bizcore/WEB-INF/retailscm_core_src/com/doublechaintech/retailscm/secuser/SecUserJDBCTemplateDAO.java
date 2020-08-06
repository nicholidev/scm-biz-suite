
package com.doublechaintech.retailscm.secuser;

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


import com.doublechaintech.retailscm.wechatworkappidentity.WechatWorkappIdentity;
import com.doublechaintech.retailscm.keypairidentity.KeypairIdentity;
import com.doublechaintech.retailscm.userapp.UserApp;
import com.doublechaintech.retailscm.wechatminiappidentity.WechatMiniappIdentity;
import com.doublechaintech.retailscm.userdomain.UserDomain;
import com.doublechaintech.retailscm.loginhistory.LoginHistory;

import com.doublechaintech.retailscm.wechatworkappidentity.WechatWorkappIdentityDAO;
import com.doublechaintech.retailscm.wechatminiappidentity.WechatMiniappIdentityDAO;
import com.doublechaintech.retailscm.userdomain.UserDomainDAO;
import com.doublechaintech.retailscm.userapp.UserAppDAO;
import com.doublechaintech.retailscm.loginhistory.LoginHistoryDAO;
import com.doublechaintech.retailscm.keypairidentity.KeypairIdentityDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import java.util.stream.Stream;

public class SecUserJDBCTemplateDAO extends RetailscmBaseDAOImpl implements SecUserDAO{

	protected UserDomainDAO userDomainDAO;
	public void setUserDomainDAO(UserDomainDAO userDomainDAO){
 	
 		if(userDomainDAO == null){
 			throw new IllegalStateException("Do not try to set userDomainDAO to null.");
 		}
	 	this.userDomainDAO = userDomainDAO;
 	}
 	public UserDomainDAO getUserDomainDAO(){
 		if(this.userDomainDAO == null){
 			throw new IllegalStateException("The userDomainDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userDomainDAO;
 	}	

	protected UserAppDAO userAppDAO;
	public void setUserAppDAO(UserAppDAO userAppDAO){
 	
 		if(userAppDAO == null){
 			throw new IllegalStateException("Do not try to set userAppDAO to null.");
 		}
	 	this.userAppDAO = userAppDAO;
 	}
 	public UserAppDAO getUserAppDAO(){
 		if(this.userAppDAO == null){
 			throw new IllegalStateException("The userAppDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userAppDAO;
 	}	

	protected LoginHistoryDAO loginHistoryDAO;
	public void setLoginHistoryDAO(LoginHistoryDAO loginHistoryDAO){
 	
 		if(loginHistoryDAO == null){
 			throw new IllegalStateException("Do not try to set loginHistoryDAO to null.");
 		}
	 	this.loginHistoryDAO = loginHistoryDAO;
 	}
 	public LoginHistoryDAO getLoginHistoryDAO(){
 		if(this.loginHistoryDAO == null){
 			throw new IllegalStateException("The loginHistoryDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.loginHistoryDAO;
 	}	

	protected WechatWorkappIdentityDAO wechatWorkappIdentityDAO;
	public void setWechatWorkappIdentityDAO(WechatWorkappIdentityDAO wechatWorkappIdentityDAO){
 	
 		if(wechatWorkappIdentityDAO == null){
 			throw new IllegalStateException("Do not try to set wechatWorkappIdentityDAO to null.");
 		}
	 	this.wechatWorkappIdentityDAO = wechatWorkappIdentityDAO;
 	}
 	public WechatWorkappIdentityDAO getWechatWorkappIdentityDAO(){
 		if(this.wechatWorkappIdentityDAO == null){
 			throw new IllegalStateException("The wechatWorkappIdentityDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.wechatWorkappIdentityDAO;
 	}	

	protected WechatMiniappIdentityDAO wechatMiniappIdentityDAO;
	public void setWechatMiniappIdentityDAO(WechatMiniappIdentityDAO wechatMiniappIdentityDAO){
 	
 		if(wechatMiniappIdentityDAO == null){
 			throw new IllegalStateException("Do not try to set wechatMiniappIdentityDAO to null.");
 		}
	 	this.wechatMiniappIdentityDAO = wechatMiniappIdentityDAO;
 	}
 	public WechatMiniappIdentityDAO getWechatMiniappIdentityDAO(){
 		if(this.wechatMiniappIdentityDAO == null){
 			throw new IllegalStateException("The wechatMiniappIdentityDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.wechatMiniappIdentityDAO;
 	}	

	protected KeypairIdentityDAO keypairIdentityDAO;
	public void setKeypairIdentityDAO(KeypairIdentityDAO keypairIdentityDAO){
 	
 		if(keypairIdentityDAO == null){
 			throw new IllegalStateException("Do not try to set keypairIdentityDAO to null.");
 		}
	 	this.keypairIdentityDAO = keypairIdentityDAO;
 	}
 	public KeypairIdentityDAO getKeypairIdentityDAO(){
 		if(this.keypairIdentityDAO == null){
 			throw new IllegalStateException("The keypairIdentityDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.keypairIdentityDAO;
 	}	


	/*
	protected SecUser load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalSecUser(accessKey, options);
	}
	*/

	public SmartList<SecUser> loadAll() {
	    return this.loadAll(getSecUserMapper());
	}

  public Stream<SecUser> loadAllAsStream() {
      return this.loadAllAsStream(getSecUserMapper());
  }


	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}

	public SecUser load(String id,Map<String,Object> options) throws Exception{
		return loadInternalSecUser(SecUserTable.withId(id), options);
	}

	

	public SecUser loadByLogin(String login,Map<String,Object> options) throws Exception{
		return loadInternalSecUser(SecUserTable.withLogin( login), options);
	}
	

	public SecUser loadByEmail(String email,Map<String,Object> options) throws Exception{
		return loadInternalSecUser(SecUserTable.withEmail( email), options);
	}
	

	public SecUser loadByMobile(String mobile,Map<String,Object> options) throws Exception{
		return loadInternalSecUser(SecUserTable.withMobile( mobile), options);
	}
	

	public SecUser save(SecUser secUser,Map<String,Object> options){

		String methodName="save(SecUser secUser,Map<String,Object> options)";

		assertMethodArgumentNotNull(secUser, methodName, "secUser");
		assertMethodArgumentNotNull(options, methodName, "options");

		return saveInternalSecUser(secUser,options);
	}
	public SecUser clone(String secUserId, Map<String,Object> options) throws Exception{

		return clone(SecUserTable.withId(secUserId),options);
	}

	protected SecUser clone(AccessKey accessKey, Map<String,Object> options) throws Exception{

		String methodName="clone(String secUserId,Map<String,Object> options)";

		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");

		SecUser newSecUser = loadInternalSecUser(accessKey, options);
		newSecUser.setVersion(0);
		
		
 		
 		if(isSaveUserAppListEnabled(options)){
 			for(UserApp item: newSecUser.getUserAppList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveLoginHistoryListEnabled(options)){
 			for(LoginHistory item: newSecUser.getLoginHistoryList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWechatWorkappIdentityListEnabled(options)){
 			for(WechatWorkappIdentity item: newSecUser.getWechatWorkappIdentityList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWechatMiniappIdentityListEnabled(options)){
 			for(WechatMiniappIdentity item: newSecUser.getWechatMiniappIdentityList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveKeypairIdentityListEnabled(options)){
 			for(KeypairIdentity item: newSecUser.getKeypairIdentityList()){
 				item.setVersion(0);
 			}
 		}
		


		saveInternalSecUser(newSecUser,options);

		return newSecUser;
	}

	

	public SecUser cloneByLogin(String login,Map<String,Object> options) throws Exception{
		return clone(SecUserTable.withLogin( login), options);
	}
	

	public SecUser cloneByEmail(String email,Map<String,Object> options) throws Exception{
		return clone(SecUserTable.withEmail( email), options);
	}
	

	public SecUser cloneByMobile(String mobile,Map<String,Object> options) throws Exception{
		return clone(SecUserTable.withMobile( mobile), options);
	}
	



	protected void throwIfHasException(String secUserId,int version,int count) throws Exception{
		if (count == 1) {
			throw new SecUserVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new SecUserNotFoundException(
					"The " + this.getTableName() + "(" + secUserId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}


	public void delete(String secUserId, int version) throws Exception{

		String methodName="delete(String secUserId, int version)";
		assertMethodArgumentNotNull(secUserId, methodName, "secUserId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");


		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{secUserId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(secUserId,version);
		}


	}






	public SecUser disconnectFromAll(String secUserId, int version) throws Exception{


		SecUser secUser = loadInternalSecUser(SecUserTable.withId(secUserId), emptyOptions());
		secUser.clearFromAll();
		this.saveSecUser(secUser);
		return secUser;


	}

	@Override
	protected String[] getNormalColumnNames() {

		return SecUserTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {

		return "sec_user";
	}
	@Override
	protected String getBeanName() {

		return "secUser";
	}

	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return SecUserTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractDomainEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SecUserTokens.DOMAIN);
 	}

 	protected boolean isSaveDomainEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SecUserTokens.DOMAIN);
 	}
 	

 	
 
		
	
	protected boolean isExtractUserAppListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SecUserTokens.USER_APP_LIST);
 	}
 	protected boolean isAnalyzeUserAppListEnabled(Map<String,Object> options){		 		
 		return SecUserTokens.of(options).analyzeUserAppListEnabled();
 	}
	
	protected boolean isSaveUserAppListEnabled(Map<String,Object> options){
		return checkOptions(options, SecUserTokens.USER_APP_LIST);
		
 	}
 	
		
	
	protected boolean isExtractLoginHistoryListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SecUserTokens.LOGIN_HISTORY_LIST);
 	}
 	protected boolean isAnalyzeLoginHistoryListEnabled(Map<String,Object> options){		 		
 		return SecUserTokens.of(options).analyzeLoginHistoryListEnabled();
 	}
	
	protected boolean isSaveLoginHistoryListEnabled(Map<String,Object> options){
		return checkOptions(options, SecUserTokens.LOGIN_HISTORY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWechatWorkappIdentityListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SecUserTokens.WECHAT_WORKAPP_IDENTITY_LIST);
 	}
 	protected boolean isAnalyzeWechatWorkappIdentityListEnabled(Map<String,Object> options){		 		
 		return SecUserTokens.of(options).analyzeWechatWorkappIdentityListEnabled();
 	}
	
	protected boolean isSaveWechatWorkappIdentityListEnabled(Map<String,Object> options){
		return checkOptions(options, SecUserTokens.WECHAT_WORKAPP_IDENTITY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWechatMiniappIdentityListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SecUserTokens.WECHAT_MINIAPP_IDENTITY_LIST);
 	}
 	protected boolean isAnalyzeWechatMiniappIdentityListEnabled(Map<String,Object> options){		 		
 		return SecUserTokens.of(options).analyzeWechatMiniappIdentityListEnabled();
 	}
	
	protected boolean isSaveWechatMiniappIdentityListEnabled(Map<String,Object> options){
		return checkOptions(options, SecUserTokens.WECHAT_MINIAPP_IDENTITY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractKeypairIdentityListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SecUserTokens.KEYPAIR_IDENTITY_LIST);
 	}
 	protected boolean isAnalyzeKeypairIdentityListEnabled(Map<String,Object> options){		 		
 		return SecUserTokens.of(options).analyzeKeypairIdentityListEnabled();
 	}
	
	protected boolean isSaveKeypairIdentityListEnabled(Map<String,Object> options){
		return checkOptions(options, SecUserTokens.KEYPAIR_IDENTITY_LIST);
		
 	}
 	
		

	

	protected SecUserMapper getSecUserMapper(){
		return new SecUserMapper();
	}

	
	
	protected SecUser extractSecUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			SecUser secUser = loadSingleObject(accessKey, getSecUserMapper());
			return secUser;
		}catch(EmptyResultDataAccessException e){
			throw new SecUserNotFoundException("SecUser("+accessKey+") is not found!");
		}

	}

	
	

	protected SecUser loadInternalSecUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		SecUser secUser = extractSecUser(accessKey, loadOptions);
 	
 		if(isExtractDomainEnabled(loadOptions)){
	 		extractDomain(secUser, loadOptions);
 		}
 
		
		if(isExtractUserAppListEnabled(loadOptions)){
	 		extractUserAppList(secUser, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeUserAppListEnabled(loadOptions)){
	 		analyzeUserAppList(secUser, loadOptions);
 		}
 		
		
		if(isExtractLoginHistoryListEnabled(loadOptions)){
	 		extractLoginHistoryList(secUser, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeLoginHistoryListEnabled(loadOptions)){
	 		analyzeLoginHistoryList(secUser, loadOptions);
 		}
 		
		
		if(isExtractWechatWorkappIdentityListEnabled(loadOptions)){
	 		extractWechatWorkappIdentityList(secUser, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeWechatWorkappIdentityListEnabled(loadOptions)){
	 		analyzeWechatWorkappIdentityList(secUser, loadOptions);
 		}
 		
		
		if(isExtractWechatMiniappIdentityListEnabled(loadOptions)){
	 		extractWechatMiniappIdentityList(secUser, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeWechatMiniappIdentityListEnabled(loadOptions)){
	 		analyzeWechatMiniappIdentityList(secUser, loadOptions);
 		}
 		
		
		if(isExtractKeypairIdentityListEnabled(loadOptions)){
	 		extractKeypairIdentityList(secUser, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeKeypairIdentityListEnabled(loadOptions)){
	 		analyzeKeypairIdentityList(secUser, loadOptions);
 		}
 		
		
		return secUser;
		
	}

	 

 	protected SecUser extractDomain(SecUser secUser, Map<String,Object> options) throws Exception{

		if(secUser.getDomain() == null){
			return secUser;
		}
		String domainId = secUser.getDomain().getId();
		if( domainId == null){
			return secUser;
		}
		UserDomain domain = getUserDomainDAO().load(domainId,options);
		if(domain != null){
			secUser.setDomain(domain);
		}
		
 		
 		return secUser;
 	}
 		
 
		
	protected void enhanceUserAppList(SmartList<UserApp> userAppList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SecUser extractUserAppList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<UserApp> userAppList = getUserAppDAO().findUserAppBySecUser(secUser.getId(),options);
		if(userAppList != null){
			enhanceUserAppList(userAppList,options);
			secUser.setUserAppList(userAppList);
		}
		
		return secUser;
	
	}	
	
	protected SecUser analyzeUserAppList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<UserApp> userAppList = secUser.getUserAppList();
		if(userAppList != null){
			getUserAppDAO().analyzeUserAppBySecUser(userAppList, secUser.getId(), options);
			
		}
		
		return secUser;
	
	}	
	
		
	protected void enhanceLoginHistoryList(SmartList<LoginHistory> loginHistoryList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SecUser extractLoginHistoryList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<LoginHistory> loginHistoryList = getLoginHistoryDAO().findLoginHistoryBySecUser(secUser.getId(),options);
		if(loginHistoryList != null){
			enhanceLoginHistoryList(loginHistoryList,options);
			secUser.setLoginHistoryList(loginHistoryList);
		}
		
		return secUser;
	
	}	
	
	protected SecUser analyzeLoginHistoryList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<LoginHistory> loginHistoryList = secUser.getLoginHistoryList();
		if(loginHistoryList != null){
			getLoginHistoryDAO().analyzeLoginHistoryBySecUser(loginHistoryList, secUser.getId(), options);
			
		}
		
		return secUser;
	
	}	
	
		
	protected void enhanceWechatWorkappIdentityList(SmartList<WechatWorkappIdentity> wechatWorkappIdentityList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SecUser extractWechatWorkappIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = getWechatWorkappIdentityDAO().findWechatWorkappIdentityBySecUser(secUser.getId(),options);
		if(wechatWorkappIdentityList != null){
			enhanceWechatWorkappIdentityList(wechatWorkappIdentityList,options);
			secUser.setWechatWorkappIdentityList(wechatWorkappIdentityList);
		}
		
		return secUser;
	
	}	
	
	protected SecUser analyzeWechatWorkappIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = secUser.getWechatWorkappIdentityList();
		if(wechatWorkappIdentityList != null){
			getWechatWorkappIdentityDAO().analyzeWechatWorkappIdentityBySecUser(wechatWorkappIdentityList, secUser.getId(), options);
			
		}
		
		return secUser;
	
	}	
	
		
	protected void enhanceWechatMiniappIdentityList(SmartList<WechatMiniappIdentity> wechatMiniappIdentityList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SecUser extractWechatMiniappIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = getWechatMiniappIdentityDAO().findWechatMiniappIdentityBySecUser(secUser.getId(),options);
		if(wechatMiniappIdentityList != null){
			enhanceWechatMiniappIdentityList(wechatMiniappIdentityList,options);
			secUser.setWechatMiniappIdentityList(wechatMiniappIdentityList);
		}
		
		return secUser;
	
	}	
	
	protected SecUser analyzeWechatMiniappIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = secUser.getWechatMiniappIdentityList();
		if(wechatMiniappIdentityList != null){
			getWechatMiniappIdentityDAO().analyzeWechatMiniappIdentityBySecUser(wechatMiniappIdentityList, secUser.getId(), options);
			
		}
		
		return secUser;
	
	}	
	
		
	protected void enhanceKeypairIdentityList(SmartList<KeypairIdentity> keypairIdentityList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SecUser extractKeypairIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<KeypairIdentity> keypairIdentityList = getKeypairIdentityDAO().findKeypairIdentityBySecUser(secUser.getId(),options);
		if(keypairIdentityList != null){
			enhanceKeypairIdentityList(keypairIdentityList,options);
			secUser.setKeypairIdentityList(keypairIdentityList);
		}
		
		return secUser;
	
	}	
	
	protected SecUser analyzeKeypairIdentityList(SecUser secUser, Map<String,Object> options){
		
		
		if(secUser == null){
			return null;
		}
		if(secUser.getId() == null){
			return secUser;
		}

		
		
		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();
		if(keypairIdentityList != null){
			getKeypairIdentityDAO().analyzeKeypairIdentityBySecUser(keypairIdentityList, secUser.getId(), options);
			
		}
		
		return secUser;
	
	}	
	
		
		
  	
 	public SmartList<SecUser> findSecUserByDomain(String userDomainId,Map<String,Object> options){
 	
  		SmartList<SecUser> resultList = queryWith(SecUserTable.COLUMN_DOMAIN, userDomainId, options, getSecUserMapper());
		// analyzeSecUserByDomain(resultList, userDomainId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SecUser> findSecUserByDomain(String userDomainId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SecUser> resultList =  queryWithRange(SecUserTable.COLUMN_DOMAIN, userDomainId, options, getSecUserMapper(), start, count);
 		//analyzeSecUserByDomain(resultList, userDomainId, options);
 		return resultList;
 		
 	}
 	public void analyzeSecUserByDomain(SmartList<SecUser> resultList, String userDomainId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SecUser.DOMAIN_PROPERTY, userDomainId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem verificationCodeExpireStatsItem = new StatsItem();
		//SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY
		verificationCodeExpireStatsItem.setDisplayName("安全用户");
		verificationCodeExpireStatsItem.setInternalName(formatKeyForDateLine(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY));
		verificationCodeExpireStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY),filterKey,emptyOptions));
		info.addItem(verificationCodeExpireStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSecUserByDomain(String userDomainId,Map<String,Object> options){

 		return countWith(SecUserTable.COLUMN_DOMAIN, userDomainId, options);
 	}
 	@Override
	public Map<String, Integer> countSecUserByDomainIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SecUserTable.COLUMN_DOMAIN, ids, options);
	}
 	
 	
		
		
		

	

	protected SecUser saveSecUser(SecUser  secUser){
		
		if(!secUser.isChanged()){
			return secUser;
		}
		

		String SQL=this.getSaveSecUserSQL(secUser);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveSecUserParameters(secUser);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}

		secUser.incVersion();
		return secUser;

	}
	public SmartList<SecUser> saveSecUserList(SmartList<SecUser> secUserList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitSecUserList(secUserList);

		batchSecUserCreate((List<SecUser>)lists[CREATE_LIST_INDEX]);

		batchSecUserUpdate((List<SecUser>)lists[UPDATE_LIST_INDEX]);


		//update version after the list successfully saved to database;
		for(SecUser secUser:secUserList){
			if(secUser.isChanged()){
				secUser.incVersion();
			}


		}


		return secUserList;
	}

	public SmartList<SecUser> removeSecUserList(SmartList<SecUser> secUserList,Map<String,Object> options){


		super.removeList(secUserList, options);

		return secUserList;


	}

	protected List<Object[]> prepareSecUserBatchCreateArgs(List<SecUser> secUserList){

		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SecUser secUser:secUserList ){
			Object [] parameters = prepareSecUserCreateParameters(secUser);
			parametersList.add(parameters);

		}
		return parametersList;

	}
	protected List<Object[]> prepareSecUserBatchUpdateArgs(List<SecUser> secUserList){

		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SecUser secUser:secUserList ){
			if(!secUser.isChanged()){
				continue;
			}
			Object [] parameters = prepareSecUserUpdateParameters(secUser);
			parametersList.add(parameters);

		}
		return parametersList;

	}
	protected void batchSecUserCreate(List<SecUser> secUserList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareSecUserBatchCreateArgs(secUserList);

		int affectedNumbers[] = batchUpdate(SQL, args);

	}


	protected void batchSecUserUpdate(List<SecUser> secUserList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareSecUserBatchUpdateArgs(secUserList);

		int affectedNumbers[] = batchUpdate(SQL, args);



	}



	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;

	protected Object[] splitSecUserList(List<SecUser> secUserList){

		List<SecUser> secUserCreateList=new ArrayList<SecUser>();
		List<SecUser> secUserUpdateList=new ArrayList<SecUser>();

		for(SecUser secUser: secUserList){
			if(isUpdateRequest(secUser)){
				secUserUpdateList.add( secUser);
				continue;
			}
			secUserCreateList.add(secUser);
		}

		return new Object[]{secUserCreateList,secUserUpdateList};
	}

	protected boolean isUpdateRequest(SecUser secUser){
 		return secUser.getVersion() > 0;
 	}
 	protected String getSaveSecUserSQL(SecUser secUser){
 		if(isUpdateRequest(secUser)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}

 	protected Object[] getSaveSecUserParameters(SecUser secUser){
 		if(isUpdateRequest(secUser) ){
 			return prepareSecUserUpdateParameters(secUser);
 		}
 		return prepareSecUserCreateParameters(secUser);
 	}
 	protected Object[] prepareSecUserUpdateParameters(SecUser secUser){
 		Object[] parameters = new Object[14];
 
 		
 		parameters[0] = secUser.getLogin();
 		
 		
 		parameters[1] = secUser.getMobile();
 		
 		
 		parameters[2] = secUser.getEmail();
 		
 		
 		parameters[3] = secUser.getPwd();
 		
 		
 		parameters[4] = secUser.getWeixinOpenid();
 		
 		
 		parameters[5] = secUser.getWeixinAppid();
 		
 		
 		parameters[6] = secUser.getAccessToken();
 		
 		
 		parameters[7] = secUser.getVerificationCode();
 		
 		
 		parameters[8] = secUser.getVerificationCodeExpire();
 		
 		
 		parameters[9] = secUser.getLastLoginTime();
 		
 		if(secUser.getDomain() != null){
 			parameters[10] = secUser.getDomain().getId();
 		}
 
 		parameters[11] = secUser.nextVersion();
 		parameters[12] = secUser.getId();
 		parameters[13] = secUser.getVersion();

 		return parameters;
 	}
 	protected Object[] prepareSecUserCreateParameters(SecUser secUser){
		Object[] parameters = new Object[12];
        if(secUser.getId() == null){
          String newSecUserId=getNextId();
          secUser.setId(newSecUserId);
        }
		parameters[0] =  secUser.getId();
 
 		
 		parameters[1] = secUser.getLogin();
 		
 		
 		parameters[2] = secUser.getMobile();
 		
 		
 		parameters[3] = secUser.getEmail();
 		
 		
 		parameters[4] = secUser.getPwd();
 		
 		
 		parameters[5] = secUser.getWeixinOpenid();
 		
 		
 		parameters[6] = secUser.getWeixinAppid();
 		
 		
 		parameters[7] = secUser.getAccessToken();
 		
 		
 		parameters[8] = secUser.getVerificationCode();
 		
 		
 		parameters[9] = secUser.getVerificationCodeExpire();
 		
 		
 		parameters[10] = secUser.getLastLoginTime();
 		
 		if(secUser.getDomain() != null){
 			parameters[11] = secUser.getDomain().getId();

 		}
 		

 		return parameters;
 	}

	protected SecUser saveInternalSecUser(SecUser secUser, Map<String,Object> options){

		saveSecUser(secUser);

 		if(isSaveDomainEnabled(options)){
	 		saveDomain(secUser, options);
 		}
 
		
		if(isSaveUserAppListEnabled(options)){
	 		saveUserAppList(secUser, options);
	 		//removeUserAppList(secUser, options);
	 		//Not delete the record

 		}
		
		if(isSaveLoginHistoryListEnabled(options)){
	 		saveLoginHistoryList(secUser, options);
	 		//removeLoginHistoryList(secUser, options);
	 		//Not delete the record

 		}
		
		if(isSaveWechatWorkappIdentityListEnabled(options)){
	 		saveWechatWorkappIdentityList(secUser, options);
	 		//removeWechatWorkappIdentityList(secUser, options);
	 		//Not delete the record

 		}
		
		if(isSaveWechatMiniappIdentityListEnabled(options)){
	 		saveWechatMiniappIdentityList(secUser, options);
	 		//removeWechatMiniappIdentityList(secUser, options);
	 		//Not delete the record

 		}
		
		if(isSaveKeypairIdentityListEnabled(options)){
	 		saveKeypairIdentityList(secUser, options);
	 		//removeKeypairIdentityList(secUser, options);
	 		//Not delete the record

 		}
		
		return secUser;

	}



	//======================================================================================
	

 	protected SecUser saveDomain(SecUser secUser, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(secUser.getDomain() == null){
 			return secUser;//do nothing when it is null
 		}

 		getUserDomainDAO().save(secUser.getDomain(),options);
 		return secUser;

 	}





 

	
	public SecUser planToRemoveUserAppList(SecUser secUser, String userAppIds[], Map<String,Object> options)throws Exception{

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.ID_PROPERTY, userAppIds);

		SmartList<UserApp> externalUserAppList = getUserAppDAO().
				findUserAppWithKey(key, options);
		if(externalUserAppList == null){
			return secUser;
		}
		if(externalUserAppList.isEmpty()){
			return secUser;
		}

		for(UserApp userAppItem: externalUserAppList){

			userAppItem.clearFromAll();
		}


		SmartList<UserApp> userAppList = secUser.getUserAppList();
		userAppList.addAllToRemoveList(externalUserAppList);
		return secUser;

	}


	public SecUser planToRemoveLoginHistoryList(SecUser secUser, String loginHistoryIds[], Map<String,Object> options)throws Exception{

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(LoginHistory.SEC_USER_PROPERTY, secUser.getId());
		key.put(LoginHistory.ID_PROPERTY, loginHistoryIds);

		SmartList<LoginHistory> externalLoginHistoryList = getLoginHistoryDAO().
				findLoginHistoryWithKey(key, options);
		if(externalLoginHistoryList == null){
			return secUser;
		}
		if(externalLoginHistoryList.isEmpty()){
			return secUser;
		}

		for(LoginHistory loginHistoryItem: externalLoginHistoryList){

			loginHistoryItem.clearFromAll();
		}


		SmartList<LoginHistory> loginHistoryList = secUser.getLoginHistoryList();
		loginHistoryList.addAllToRemoveList(externalLoginHistoryList);
		return secUser;

	}


	public SecUser planToRemoveWechatWorkappIdentityList(SecUser secUser, String wechatWorkappIdentityIds[], Map<String,Object> options)throws Exception{

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatWorkappIdentity.SEC_USER_PROPERTY, secUser.getId());
		key.put(WechatWorkappIdentity.ID_PROPERTY, wechatWorkappIdentityIds);

		SmartList<WechatWorkappIdentity> externalWechatWorkappIdentityList = getWechatWorkappIdentityDAO().
				findWechatWorkappIdentityWithKey(key, options);
		if(externalWechatWorkappIdentityList == null){
			return secUser;
		}
		if(externalWechatWorkappIdentityList.isEmpty()){
			return secUser;
		}

		for(WechatWorkappIdentity wechatWorkappIdentityItem: externalWechatWorkappIdentityList){

			wechatWorkappIdentityItem.clearFromAll();
		}


		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = secUser.getWechatWorkappIdentityList();
		wechatWorkappIdentityList.addAllToRemoveList(externalWechatWorkappIdentityList);
		return secUser;

	}


	public SecUser planToRemoveWechatMiniappIdentityList(SecUser secUser, String wechatMiniappIdentityIds[], Map<String,Object> options)throws Exception{

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatMiniappIdentity.SEC_USER_PROPERTY, secUser.getId());
		key.put(WechatMiniappIdentity.ID_PROPERTY, wechatMiniappIdentityIds);

		SmartList<WechatMiniappIdentity> externalWechatMiniappIdentityList = getWechatMiniappIdentityDAO().
				findWechatMiniappIdentityWithKey(key, options);
		if(externalWechatMiniappIdentityList == null){
			return secUser;
		}
		if(externalWechatMiniappIdentityList.isEmpty()){
			return secUser;
		}

		for(WechatMiniappIdentity wechatMiniappIdentityItem: externalWechatMiniappIdentityList){

			wechatMiniappIdentityItem.clearFromAll();
		}


		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = secUser.getWechatMiniappIdentityList();
		wechatMiniappIdentityList.addAllToRemoveList(externalWechatMiniappIdentityList);
		return secUser;

	}


	public SecUser planToRemoveKeypairIdentityList(SecUser secUser, String keypairIdentityIds[], Map<String,Object> options)throws Exception{

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(KeypairIdentity.SEC_USER_PROPERTY, secUser.getId());
		key.put(KeypairIdentity.ID_PROPERTY, keypairIdentityIds);

		SmartList<KeypairIdentity> externalKeypairIdentityList = getKeypairIdentityDAO().
				findKeypairIdentityWithKey(key, options);
		if(externalKeypairIdentityList == null){
			return secUser;
		}
		if(externalKeypairIdentityList.isEmpty()){
			return secUser;
		}

		for(KeypairIdentity keypairIdentityItem: externalKeypairIdentityList){

			keypairIdentityItem.clearFromAll();
		}


		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();
		keypairIdentityList.addAllToRemoveList(externalKeypairIdentityList);
		return secUser;

	}


	//disconnect SecUser with key_type in KeypairIdentity
	public SecUser planToRemoveKeypairIdentityListWithKeyType(SecUser secUser, String keyTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(KeypairIdentity.SEC_USER_PROPERTY, secUser.getId());
		key.put(KeypairIdentity.KEY_TYPE_PROPERTY, keyTypeId);

		SmartList<KeypairIdentity> externalKeypairIdentityList = getKeypairIdentityDAO().
				findKeypairIdentityWithKey(key, options);
		if(externalKeypairIdentityList == null){
			return secUser;
		}
		if(externalKeypairIdentityList.isEmpty()){
			return secUser;
		}

		for(KeypairIdentity keypairIdentityItem: externalKeypairIdentityList){
			keypairIdentityItem.clearKeyType();
			keypairIdentityItem.clearSecUser();

		}


		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();
		keypairIdentityList.addAllToRemoveList(externalKeypairIdentityList);
		return secUser;
	}

	public int countKeypairIdentityListWithKeyType(String secUserId, String keyTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(KeypairIdentity.SEC_USER_PROPERTY, secUserId);
		key.put(KeypairIdentity.KEY_TYPE_PROPERTY, keyTypeId);

		int count = getKeypairIdentityDAO().countKeypairIdentityWithKey(key, options);
		return count;
	}
	

		
	protected SecUser saveUserAppList(SecUser secUser, Map<String,Object> options){




		SmartList<UserApp> userAppList = secUser.getUserAppList();
		if(userAppList == null){
			//null list means nothing
			return secUser;
		}
		SmartList<UserApp> mergedUpdateUserAppList = new SmartList<UserApp>();


		mergedUpdateUserAppList.addAll(userAppList);
		if(userAppList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserAppList.addAll(userAppList.getToRemoveList());
			userAppList.removeAll(userAppList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance

		getUserAppDAO().saveUserAppList(mergedUpdateUserAppList,options);

		if(userAppList.getToRemoveList() != null){
			userAppList.removeAll(userAppList.getToRemoveList());
		}


		return secUser;

	}

	protected SecUser removeUserAppList(SecUser secUser, Map<String,Object> options){


		SmartList<UserApp> userAppList = secUser.getUserAppList();
		if(userAppList == null){
			return secUser;
		}

		SmartList<UserApp> toRemoveUserAppList = userAppList.getToRemoveList();

		if(toRemoveUserAppList == null){
			return secUser;
		}
		if(toRemoveUserAppList.isEmpty()){
			return secUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list

		getUserAppDAO().removeUserAppList(toRemoveUserAppList,options);

		return secUser;

	}








		
	protected SecUser saveLoginHistoryList(SecUser secUser, Map<String,Object> options){




		SmartList<LoginHistory> loginHistoryList = secUser.getLoginHistoryList();
		if(loginHistoryList == null){
			//null list means nothing
			return secUser;
		}
		SmartList<LoginHistory> mergedUpdateLoginHistoryList = new SmartList<LoginHistory>();


		mergedUpdateLoginHistoryList.addAll(loginHistoryList);
		if(loginHistoryList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateLoginHistoryList.addAll(loginHistoryList.getToRemoveList());
			loginHistoryList.removeAll(loginHistoryList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance

		getLoginHistoryDAO().saveLoginHistoryList(mergedUpdateLoginHistoryList,options);

		if(loginHistoryList.getToRemoveList() != null){
			loginHistoryList.removeAll(loginHistoryList.getToRemoveList());
		}


		return secUser;

	}

	protected SecUser removeLoginHistoryList(SecUser secUser, Map<String,Object> options){


		SmartList<LoginHistory> loginHistoryList = secUser.getLoginHistoryList();
		if(loginHistoryList == null){
			return secUser;
		}

		SmartList<LoginHistory> toRemoveLoginHistoryList = loginHistoryList.getToRemoveList();

		if(toRemoveLoginHistoryList == null){
			return secUser;
		}
		if(toRemoveLoginHistoryList.isEmpty()){
			return secUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list

		getLoginHistoryDAO().removeLoginHistoryList(toRemoveLoginHistoryList,options);

		return secUser;

	}








		
	protected SecUser saveWechatWorkappIdentityList(SecUser secUser, Map<String,Object> options){




		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = secUser.getWechatWorkappIdentityList();
		if(wechatWorkappIdentityList == null){
			//null list means nothing
			return secUser;
		}
		SmartList<WechatWorkappIdentity> mergedUpdateWechatWorkappIdentityList = new SmartList<WechatWorkappIdentity>();


		mergedUpdateWechatWorkappIdentityList.addAll(wechatWorkappIdentityList);
		if(wechatWorkappIdentityList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWechatWorkappIdentityList.addAll(wechatWorkappIdentityList.getToRemoveList());
			wechatWorkappIdentityList.removeAll(wechatWorkappIdentityList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance

		getWechatWorkappIdentityDAO().saveWechatWorkappIdentityList(mergedUpdateWechatWorkappIdentityList,options);

		if(wechatWorkappIdentityList.getToRemoveList() != null){
			wechatWorkappIdentityList.removeAll(wechatWorkappIdentityList.getToRemoveList());
		}


		return secUser;

	}

	protected SecUser removeWechatWorkappIdentityList(SecUser secUser, Map<String,Object> options){


		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = secUser.getWechatWorkappIdentityList();
		if(wechatWorkappIdentityList == null){
			return secUser;
		}

		SmartList<WechatWorkappIdentity> toRemoveWechatWorkappIdentityList = wechatWorkappIdentityList.getToRemoveList();

		if(toRemoveWechatWorkappIdentityList == null){
			return secUser;
		}
		if(toRemoveWechatWorkappIdentityList.isEmpty()){
			return secUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list

		getWechatWorkappIdentityDAO().removeWechatWorkappIdentityList(toRemoveWechatWorkappIdentityList,options);

		return secUser;

	}








		
	protected SecUser saveWechatMiniappIdentityList(SecUser secUser, Map<String,Object> options){




		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = secUser.getWechatMiniappIdentityList();
		if(wechatMiniappIdentityList == null){
			//null list means nothing
			return secUser;
		}
		SmartList<WechatMiniappIdentity> mergedUpdateWechatMiniappIdentityList = new SmartList<WechatMiniappIdentity>();


		mergedUpdateWechatMiniappIdentityList.addAll(wechatMiniappIdentityList);
		if(wechatMiniappIdentityList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWechatMiniappIdentityList.addAll(wechatMiniappIdentityList.getToRemoveList());
			wechatMiniappIdentityList.removeAll(wechatMiniappIdentityList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance

		getWechatMiniappIdentityDAO().saveWechatMiniappIdentityList(mergedUpdateWechatMiniappIdentityList,options);

		if(wechatMiniappIdentityList.getToRemoveList() != null){
			wechatMiniappIdentityList.removeAll(wechatMiniappIdentityList.getToRemoveList());
		}


		return secUser;

	}

	protected SecUser removeWechatMiniappIdentityList(SecUser secUser, Map<String,Object> options){


		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = secUser.getWechatMiniappIdentityList();
		if(wechatMiniappIdentityList == null){
			return secUser;
		}

		SmartList<WechatMiniappIdentity> toRemoveWechatMiniappIdentityList = wechatMiniappIdentityList.getToRemoveList();

		if(toRemoveWechatMiniappIdentityList == null){
			return secUser;
		}
		if(toRemoveWechatMiniappIdentityList.isEmpty()){
			return secUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list

		getWechatMiniappIdentityDAO().removeWechatMiniappIdentityList(toRemoveWechatMiniappIdentityList,options);

		return secUser;

	}








		
	protected SecUser saveKeypairIdentityList(SecUser secUser, Map<String,Object> options){




		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();
		if(keypairIdentityList == null){
			//null list means nothing
			return secUser;
		}
		SmartList<KeypairIdentity> mergedUpdateKeypairIdentityList = new SmartList<KeypairIdentity>();


		mergedUpdateKeypairIdentityList.addAll(keypairIdentityList);
		if(keypairIdentityList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateKeypairIdentityList.addAll(keypairIdentityList.getToRemoveList());
			keypairIdentityList.removeAll(keypairIdentityList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance

		getKeypairIdentityDAO().saveKeypairIdentityList(mergedUpdateKeypairIdentityList,options);

		if(keypairIdentityList.getToRemoveList() != null){
			keypairIdentityList.removeAll(keypairIdentityList.getToRemoveList());
		}


		return secUser;

	}

	protected SecUser removeKeypairIdentityList(SecUser secUser, Map<String,Object> options){


		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();
		if(keypairIdentityList == null){
			return secUser;
		}

		SmartList<KeypairIdentity> toRemoveKeypairIdentityList = keypairIdentityList.getToRemoveList();

		if(toRemoveKeypairIdentityList == null){
			return secUser;
		}
		if(toRemoveKeypairIdentityList.isEmpty()){
			return secUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list

		getKeypairIdentityDAO().removeKeypairIdentityList(toRemoveKeypairIdentityList,options);

		return secUser;

	}








		

	public SecUser present(SecUser secUser,Map<String, Object> options){
	
		presentUserAppList(secUser,options);
		presentLoginHistoryList(secUser,options);
		presentWechatWorkappIdentityList(secUser,options);
		presentWechatMiniappIdentityList(secUser,options);
		presentKeypairIdentityList(secUser,options);

		return secUser;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected SecUser presentUserAppList(
			SecUser secUser,
			Map<String, Object> options) {

		SmartList<UserApp> userAppList = secUser.getUserAppList();		
				SmartList<UserApp> newList= presentSubList(secUser.getId(),
				userAppList,
				options,
				getUserAppDAO()::countUserAppBySecUser,
				getUserAppDAO()::findUserAppBySecUser
				);

		
		secUser.setUserAppList(newList);
		

		return secUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SecUser presentLoginHistoryList(
			SecUser secUser,
			Map<String, Object> options) {

		SmartList<LoginHistory> loginHistoryList = secUser.getLoginHistoryList();		
				SmartList<LoginHistory> newList= presentSubList(secUser.getId(),
				loginHistoryList,
				options,
				getLoginHistoryDAO()::countLoginHistoryBySecUser,
				getLoginHistoryDAO()::findLoginHistoryBySecUser
				);

		
		secUser.setLoginHistoryList(newList);
		

		return secUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SecUser presentWechatWorkappIdentityList(
			SecUser secUser,
			Map<String, Object> options) {

		SmartList<WechatWorkappIdentity> wechatWorkappIdentityList = secUser.getWechatWorkappIdentityList();		
				SmartList<WechatWorkappIdentity> newList= presentSubList(secUser.getId(),
				wechatWorkappIdentityList,
				options,
				getWechatWorkappIdentityDAO()::countWechatWorkappIdentityBySecUser,
				getWechatWorkappIdentityDAO()::findWechatWorkappIdentityBySecUser
				);

		
		secUser.setWechatWorkappIdentityList(newList);
		

		return secUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SecUser presentWechatMiniappIdentityList(
			SecUser secUser,
			Map<String, Object> options) {

		SmartList<WechatMiniappIdentity> wechatMiniappIdentityList = secUser.getWechatMiniappIdentityList();		
				SmartList<WechatMiniappIdentity> newList= presentSubList(secUser.getId(),
				wechatMiniappIdentityList,
				options,
				getWechatMiniappIdentityDAO()::countWechatMiniappIdentityBySecUser,
				getWechatMiniappIdentityDAO()::findWechatMiniappIdentityBySecUser
				);

		
		secUser.setWechatMiniappIdentityList(newList);
		

		return secUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SecUser presentKeypairIdentityList(
			SecUser secUser,
			Map<String, Object> options) {

		SmartList<KeypairIdentity> keypairIdentityList = secUser.getKeypairIdentityList();		
				SmartList<KeypairIdentity> newList= presentSubList(secUser.getId(),
				keypairIdentityList,
				options,
				getKeypairIdentityDAO()::countKeypairIdentityBySecUser,
				getKeypairIdentityDAO()::findKeypairIdentityBySecUser
				);

		
		secUser.setKeypairIdentityList(newList);
		

		return secUser;
	}			
		

	
    public SmartList<SecUser> requestCandidateSecUserForUserApp(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SecUserTable.COLUMN_LOGIN, SecUserTable.COLUMN_DOMAIN, filterKey, pageNo, pageSize, getSecUserMapper());
    }
		
    public SmartList<SecUser> requestCandidateSecUserForLoginHistory(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SecUserTable.COLUMN_LOGIN, SecUserTable.COLUMN_DOMAIN, filterKey, pageNo, pageSize, getSecUserMapper());
    }
		
    public SmartList<SecUser> requestCandidateSecUserForWechatWorkappIdentity(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SecUserTable.COLUMN_LOGIN, SecUserTable.COLUMN_DOMAIN, filterKey, pageNo, pageSize, getSecUserMapper());
    }
		
    public SmartList<SecUser> requestCandidateSecUserForWechatMiniappIdentity(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SecUserTable.COLUMN_LOGIN, SecUserTable.COLUMN_DOMAIN, filterKey, pageNo, pageSize, getSecUserMapper());
    }
		
    public SmartList<SecUser> requestCandidateSecUserForKeypairIdentity(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SecUserTable.COLUMN_LOGIN, SecUserTable.COLUMN_DOMAIN, filterKey, pageNo, pageSize, getSecUserMapper());
    }
		

	protected String getTableName(){
		return SecUserTable.TABLE_NAME;
	}



	public void enhanceList(List<SecUser> secUserList) {
		this.enhanceListInternal(secUserList, this.getSecUserMapper());
	}

	
	// 需要一个加载引用我的对象的enhance方法:UserApp的secUser的UserAppList
	public SmartList<UserApp> loadOurUserAppList(RetailscmUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<UserApp> loadedObjs = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, options);
		Map<String, List<UserApp>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSecUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<UserApp> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<UserApp> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserAppList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:LoginHistory的secUser的LoginHistoryList
	public SmartList<LoginHistory> loadOurLoginHistoryList(RetailscmUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(LoginHistory.SEC_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<LoginHistory> loadedObjs = userContext.getDAOGroup().getLoginHistoryDAO().findLoginHistoryWithKey(key, options);
		Map<String, List<LoginHistory>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSecUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<LoginHistory> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<LoginHistory> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setLoginHistoryList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:WechatWorkappIdentity的secUser的WechatWorkappIdentityList
	public SmartList<WechatWorkappIdentity> loadOurWechatWorkappIdentityList(RetailscmUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatWorkappIdentity.SEC_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatWorkappIdentity> loadedObjs = userContext.getDAOGroup().getWechatWorkappIdentityDAO().findWechatWorkappIdentityWithKey(key, options);
		Map<String, List<WechatWorkappIdentity>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSecUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WechatWorkappIdentity> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WechatWorkappIdentity> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWechatWorkappIdentityList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:WechatMiniappIdentity的secUser的WechatMiniappIdentityList
	public SmartList<WechatMiniappIdentity> loadOurWechatMiniappIdentityList(RetailscmUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatMiniappIdentity.SEC_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatMiniappIdentity> loadedObjs = userContext.getDAOGroup().getWechatMiniappIdentityDAO().findWechatMiniappIdentityWithKey(key, options);
		Map<String, List<WechatMiniappIdentity>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSecUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WechatMiniappIdentity> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WechatMiniappIdentity> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWechatMiniappIdentityList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:KeypairIdentity的secUser的KeypairIdentityList
	public SmartList<KeypairIdentity> loadOurKeypairIdentityList(RetailscmUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(KeypairIdentity.SEC_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<KeypairIdentity> loadedObjs = userContext.getDAOGroup().getKeypairIdentityDAO().findKeypairIdentityWithKey(key, options);
		Map<String, List<KeypairIdentity>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSecUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<KeypairIdentity> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<KeypairIdentity> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setKeypairIdentityList(loadedSmartList);
		});
		return loadedObjs;
	}
	

	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<SecUser> secUserList = ownerEntity.collectRefsWithType(SecUser.INTERNAL_TYPE);
		this.enhanceList(secUserList);

	}

	@Override
	public SmartList<SecUser> findSecUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {

  		return queryWith(key, options, getSecUserMapper());

	}
	@Override
	public int countSecUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {

  		return countWith(key, options);

	}
	public Map<String, Integer> countSecUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {

  		return countWithGroup(groupKey, filterKey, options);

	}

	@Override
	public SmartList<SecUser> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getSecUserMapper());
	}

  @Override
  public Stream<SecUser> queryStream(String sql, Object... parameters) {
    return this.queryForStream(sql, parameters, this.getSecUserMapper());
  }

	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateSecUser executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateSecUser result = new CandidateSecUser();
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


