
package com.doublechaintech.retailscm.consumerorderpaymentgroup;

import java.util.*;
import java.math.BigDecimal;
import com.terapico.caf.baseelement.PlainText;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;
import com.terapico.utils.MapUtil;
import com.terapico.utils.ListofUtils;
import com.terapico.utils.TextUtil;
import com.terapico.caf.BlobObject;
import com.terapico.caf.viewpage.SerializeScope;

import com.doublechaintech.retailscm.*;
import com.doublechaintech.retailscm.utils.ModelAssurance;
import com.doublechaintech.retailscm.tree.*;
import com.doublechaintech.retailscm.treenode.*;
import com.doublechaintech.retailscm.RetailscmUserContextImpl;
import com.doublechaintech.retailscm.iamservice.*;
import com.doublechaintech.retailscm.services.IamService;
import com.doublechaintech.retailscm.secuser.SecUser;
import com.doublechaintech.retailscm.userapp.UserApp;
import com.doublechaintech.retailscm.BaseViewPage;
import com.terapico.uccaf.BaseUserContext;



import com.doublechaintech.retailscm.consumerorder.ConsumerOrder;

import com.doublechaintech.retailscm.consumerorder.CandidateConsumerOrder;







public class ConsumerOrderPaymentGroupManagerImpl extends CustomRetailscmCheckerManager implements ConsumerOrderPaymentGroupManager, BusinessHandler{

	// Only some of ods have such function
	




  


	private static final String SERVICE_TYPE = "ConsumerOrderPaymentGroup";
	@Override
	public ConsumerOrderPaymentGroupDAO daoOf(RetailscmUserContext userContext) {
		return consumerOrderPaymentGroupDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ConsumerOrderPaymentGroupManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ConsumerOrderPaymentGroupManagerException(message);

	}



 	protected ConsumerOrderPaymentGroup saveConsumerOrderPaymentGroup(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, String [] tokensExpr) throws Exception{	
 		//return getConsumerOrderPaymentGroupDAO().save(consumerOrderPaymentGroup, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, tokens);
 	}
 	
 	protected ConsumerOrderPaymentGroup saveConsumerOrderPaymentGroupDetail(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup) throws Exception{	

 		
 		return saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, allTokens());
 	}
 	
 	public ConsumerOrderPaymentGroup loadConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderPaymentGroupManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup( userContext, consumerOrderPaymentGroupId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,consumerOrderPaymentGroup, tokens);
 	}
 	
 	
 	 public ConsumerOrderPaymentGroup searchConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderPaymentGroupManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText(tokens().startsWith(), textToSearch).initWithArray(tokensExpr);
 		
 		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup( userContext, consumerOrderPaymentGroupId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,consumerOrderPaymentGroup, tokens);
 	}
 	
 	

 	protected ConsumerOrderPaymentGroup present(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,consumerOrderPaymentGroup,tokens);
		
		
		ConsumerOrderPaymentGroup  consumerOrderPaymentGroupToPresent = consumerOrderPaymentGroupDaoOf(userContext).present(consumerOrderPaymentGroup, tokens);
		
		List<BaseEntity> entityListToNaming = consumerOrderPaymentGroupToPresent.collectRefercencesFromLists();
		consumerOrderPaymentGroupDaoOf(userContext).alias(entityListToNaming);
		
		
		renderActionForList(userContext,consumerOrderPaymentGroup,tokens);
		
		return  consumerOrderPaymentGroupToPresent;
		
		
	}
 
 	
 	
 	public ConsumerOrderPaymentGroup loadConsumerOrderPaymentGroupDetail(RetailscmUserContext userContext, String consumerOrderPaymentGroupId) throws Exception{	
 		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup( userContext, consumerOrderPaymentGroupId, allTokens());
 		return present(userContext,consumerOrderPaymentGroup, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String consumerOrderPaymentGroupId) throws Exception{	
 		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup( userContext, consumerOrderPaymentGroupId, viewTokens());
 		return present(userContext,consumerOrderPaymentGroup, allTokens());
		
 	}
 	protected ConsumerOrderPaymentGroup saveConsumerOrderPaymentGroup(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, Map<String,Object>tokens) throws Exception{	
 		return consumerOrderPaymentGroupDaoOf(userContext).save(consumerOrderPaymentGroup, tokens);
 	}
 	protected ConsumerOrderPaymentGroup loadConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderPaymentGroupManagerException.class);

 
 		return consumerOrderPaymentGroupDaoOf(userContext).load(consumerOrderPaymentGroupId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, Map<String, Object> tokens){
		super.addActions(userContext, consumerOrderPaymentGroup, tokens);
		
		addAction(userContext, consumerOrderPaymentGroup, tokens,"@create","createConsumerOrderPaymentGroup","createConsumerOrderPaymentGroup/","main","primary");
		addAction(userContext, consumerOrderPaymentGroup, tokens,"@update","updateConsumerOrderPaymentGroup","updateConsumerOrderPaymentGroup/"+consumerOrderPaymentGroup.getId()+"/","main","primary");
		addAction(userContext, consumerOrderPaymentGroup, tokens,"@copy","cloneConsumerOrderPaymentGroup","cloneConsumerOrderPaymentGroup/"+consumerOrderPaymentGroup.getId()+"/","main","primary");
		
		addAction(userContext, consumerOrderPaymentGroup, tokens,"consumer_order_payment_group.transfer_to_biz_order","transferToAnotherBizOrder","transferToAnotherBizOrder/"+consumerOrderPaymentGroup.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ConsumerOrderPaymentGroup createConsumerOrderPaymentGroup(RetailscmUserContext userContext, String name,String bizOrderId,String cardNumber) throws Exception
	//public ConsumerOrderPaymentGroup createConsumerOrderPaymentGroup(RetailscmUserContext userContext,String name, String bizOrderId, String cardNumber) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfConsumerOrderPaymentGroup(name);
		checkerOf(userContext).checkCardNumberOfConsumerOrderPaymentGroup(cardNumber);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderPaymentGroupManagerException.class);


		ConsumerOrderPaymentGroup consumerOrderPaymentGroup=createNewConsumerOrderPaymentGroup();	

		consumerOrderPaymentGroup.setName(name);
			
		ConsumerOrder bizOrder = loadConsumerOrder(userContext, bizOrderId,emptyOptions());
		consumerOrderPaymentGroup.setBizOrder(bizOrder);
		
		
		consumerOrderPaymentGroup.setCardNumber(cardNumber);

		consumerOrderPaymentGroup = saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, emptyOptions());
		
		onNewInstanceCreated(userContext, consumerOrderPaymentGroup);
		return consumerOrderPaymentGroup;


	}
	protected ConsumerOrderPaymentGroup createNewConsumerOrderPaymentGroup()
	{

		return new ConsumerOrderPaymentGroup();
	}

	protected void checkParamsForUpdatingConsumerOrderPaymentGroup(RetailscmUserContext userContext,String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderPaymentGroup( consumerOrderPaymentGroupVersion);
		

		if(ConsumerOrderPaymentGroup.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfConsumerOrderPaymentGroup(parseString(newValueExpr));
		
			
		}		

		
		if(ConsumerOrderPaymentGroup.CARD_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCardNumberOfConsumerOrderPaymentGroup(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderPaymentGroupManagerException.class);


	}



	public ConsumerOrderPaymentGroup clone(RetailscmUserContext userContext, String fromConsumerOrderPaymentGroupId) throws Exception{

		return consumerOrderPaymentGroupDaoOf(userContext).clone(fromConsumerOrderPaymentGroupId, this.allTokens());
	}

	public ConsumerOrderPaymentGroup internalSaveConsumerOrderPaymentGroup(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup) throws Exception
	{
		return internalSaveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, allTokens());

	}
	public ConsumerOrderPaymentGroup internalSaveConsumerOrderPaymentGroup(RetailscmUserContext userContext, ConsumerOrderPaymentGroup consumerOrderPaymentGroup, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion, property, newValueExpr, tokensExpr);


		synchronized(consumerOrderPaymentGroup){
			//will be good when the consumerOrderPaymentGroup loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrderPaymentGroup.
			if (consumerOrderPaymentGroup.isChanged()){
			
			}
			consumerOrderPaymentGroup = saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, options);
			return consumerOrderPaymentGroup;

		}

	}

	public ConsumerOrderPaymentGroup updateConsumerOrderPaymentGroup(RetailscmUserContext userContext,String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion, property, newValueExpr, tokensExpr);



		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, allTokens());
		if(consumerOrderPaymentGroup.getVersion() != consumerOrderPaymentGroupVersion){
			String message = "The target version("+consumerOrderPaymentGroup.getVersion()+") is not equals to version("+consumerOrderPaymentGroupVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(consumerOrderPaymentGroup){
			//will be good when the consumerOrderPaymentGroup loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrderPaymentGroup.
			
			consumerOrderPaymentGroup.changeProperty(property, newValueExpr);
			consumerOrderPaymentGroup = saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, tokens().done());
			return present(userContext,consumerOrderPaymentGroup, mergedAllTokens(tokensExpr));
			//return saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, tokens().done());
		}

	}

	public ConsumerOrderPaymentGroup updateConsumerOrderPaymentGroupProperty(RetailscmUserContext userContext,String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion, property, newValueExpr, tokensExpr);

		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, allTokens());
		if(consumerOrderPaymentGroup.getVersion() != consumerOrderPaymentGroupVersion){
			String message = "The target version("+consumerOrderPaymentGroup.getVersion()+") is not equals to version("+consumerOrderPaymentGroupVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(consumerOrderPaymentGroup){
			//will be good when the consumerOrderPaymentGroup loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrderPaymentGroup.

			consumerOrderPaymentGroup.changeProperty(property, newValueExpr);
			
			consumerOrderPaymentGroup = saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, tokens().done());
			return present(userContext,consumerOrderPaymentGroup, mergedAllTokens(tokensExpr));
			//return saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ConsumerOrderPaymentGroupTokens tokens(){
		return ConsumerOrderPaymentGroupTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ConsumerOrderPaymentGroupTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ConsumerOrderPaymentGroupTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherBizOrder(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, String anotherBizOrderId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
 		checkerOf(userContext).checkIdOfConsumerOrder(anotherBizOrderId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderPaymentGroupManagerException.class);

 	}
 	public ConsumerOrderPaymentGroup transferToAnotherBizOrder(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, String anotherBizOrderId) throws Exception
 	{
 		checkParamsForTransferingAnotherBizOrder(userContext, consumerOrderPaymentGroupId,anotherBizOrderId);
 
		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = loadConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroupId, allTokens());	
		synchronized(consumerOrderPaymentGroup){
			//will be good when the consumerOrderPaymentGroup loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ConsumerOrder bizOrder = loadConsumerOrder(userContext, anotherBizOrderId, emptyOptions());		
			consumerOrderPaymentGroup.updateBizOrder(bizOrder);		
			consumerOrderPaymentGroup = saveConsumerOrderPaymentGroup(userContext, consumerOrderPaymentGroup, emptyOptions());
			
			return present(userContext,consumerOrderPaymentGroup, allTokens());
			
		}

 	}

	


	public CandidateConsumerOrder requestCandidateBizOrder(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateConsumerOrder result = new CandidateConsumerOrder();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("title");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ConsumerOrder> candidateList = consumerOrderDaoOf(userContext).requestCandidateConsumerOrderForConsumerOrderPaymentGroup(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ConsumerOrder loadConsumerOrder(RetailscmUserContext userContext, String newBizOrderId, Map<String,Object> options) throws Exception
 	{

 		return consumerOrderDaoOf(userContext).load(newBizOrderId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion) throws Exception {
		//deleteInternal(userContext, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion) throws Exception{

		consumerOrderPaymentGroupDaoOf(userContext).delete(consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
	}

	public ConsumerOrderPaymentGroup forgetByAll(RetailscmUserContext userContext, String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion) throws Exception {
		return forgetByAllInternal(userContext, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
	}
	protected ConsumerOrderPaymentGroup forgetByAllInternal(RetailscmUserContext userContext,
			String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion) throws Exception{

		return consumerOrderPaymentGroupDaoOf(userContext).disconnectFromAll(consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ConsumerOrderPaymentGroupManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return consumerOrderPaymentGroupDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(RetailscmUserContext userContext, ConsumerOrderPaymentGroup newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

  public void sendAllItems(RetailscmUserContext ctx) throws Exception{
    consumerOrderPaymentGroupDaoOf(ctx).loadAllAsStream().forEach(
          event -> sendInitEvent(ctx, event)
    );
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
		//   ConsumerOrderPaymentGroup newConsumerOrderPaymentGroup = this.createConsumerOrderPaymentGroup(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newConsumerOrderPaymentGroup
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ConsumerOrderPaymentGroup.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<ConsumerOrderPaymentGroup> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<ConsumerOrder> bizOrderList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, ConsumerOrder.class);
		userContext.getDAOGroup().enhanceList(bizOrderList, ConsumerOrder.class);


    }
	
	public Object listByBizOrder(RetailscmUserContext userContext,String bizOrderId) throws Exception {
		return listPageByBizOrder(userContext, bizOrderId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByBizOrder(RetailscmUserContext userContext,String bizOrderId, int start, int count) throws Exception {
		SmartList<ConsumerOrderPaymentGroup> list = consumerOrderPaymentGroupDaoOf(userContext).findConsumerOrderPaymentGroupByBizOrder(bizOrderId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(ConsumerOrderPaymentGroup.class);
		page.setContainerObject(ConsumerOrder.withId(bizOrderId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("消费者订单付款组列表");
		page.setRequestName("listByBizOrder");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByBizOrder/%s/",  getBeanName(), bizOrderId)));

		page.assemblerContent(userContext, "listByBizOrder");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String consumerOrderPaymentGroupId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getConsumerOrderPaymentGroupDetailScope().clone();
		ConsumerOrderPaymentGroup merchantObj = (ConsumerOrderPaymentGroup) this.view(userContext, consumerOrderPaymentGroupId);
    String merchantObjId = consumerOrderPaymentGroupId;
    String linkToUrl =	"consumerOrderPaymentGroupManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "消费者订单付款组"+"详情";
		Map result = new HashMap();
		List propList = new ArrayList();
		List sections = new ArrayList();
 
		propList.add(
				MapUtil.put("id", "1-id")
				    .put("fieldName", "id")
				    .put("label", "ID")
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
				MapUtil.put("id", "3-bizOrder")
				    .put("fieldName", "bizOrder")
				    .put("label", "订单")
				    .put("type", "auto")
				    .put("linkToUrl", "consumerOrderManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"title\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("bizOrder", merchantObj.getBizOrder());

		propList.add(
				MapUtil.put("id", "4-cardNumber")
				    .put("fieldName", "cardNumber")
				    .put("label", "卡号码")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("cardNumber", merchantObj.getCardNumber());

		//处理 sectionList

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


