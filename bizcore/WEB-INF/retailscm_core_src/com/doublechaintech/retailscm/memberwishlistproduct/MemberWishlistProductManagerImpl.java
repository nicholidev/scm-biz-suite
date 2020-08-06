
package com.doublechaintech.retailscm.memberwishlistproduct;

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



import com.doublechaintech.retailscm.memberwishlist.MemberWishlist;

import com.doublechaintech.retailscm.memberwishlist.CandidateMemberWishlist;







public class MemberWishlistProductManagerImpl extends CustomRetailscmCheckerManager implements MemberWishlistProductManager, BusinessHandler{

	// Only some of ods have such function
	




  


	private static final String SERVICE_TYPE = "MemberWishlistProduct";
	@Override
	public MemberWishlistProductDAO daoOf(RetailscmUserContext userContext) {
		return memberWishlistProductDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws MemberWishlistProductManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new MemberWishlistProductManagerException(message);

	}



 	protected MemberWishlistProduct saveMemberWishlistProduct(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, String [] tokensExpr) throws Exception{	
 		//return getMemberWishlistProductDAO().save(memberWishlistProduct, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveMemberWishlistProduct(userContext, memberWishlistProduct, tokens);
 	}
 	
 	protected MemberWishlistProduct saveMemberWishlistProductDetail(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct) throws Exception{	

 		
 		return saveMemberWishlistProduct(userContext, memberWishlistProduct, allTokens());
 	}
 	
 	public MemberWishlistProduct loadMemberWishlistProduct(RetailscmUserContext userContext, String memberWishlistProductId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfMemberWishlistProduct(memberWishlistProductId);
		checkerOf(userContext).throwExceptionIfHasErrors( MemberWishlistProductManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct( userContext, memberWishlistProductId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,memberWishlistProduct, tokens);
 	}
 	
 	
 	 public MemberWishlistProduct searchMemberWishlistProduct(RetailscmUserContext userContext, String memberWishlistProductId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfMemberWishlistProduct(memberWishlistProductId);
		checkerOf(userContext).throwExceptionIfHasErrors( MemberWishlistProductManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText(tokens().startsWith(), textToSearch).initWithArray(tokensExpr);
 		
 		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct( userContext, memberWishlistProductId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,memberWishlistProduct, tokens);
 	}
 	
 	

 	protected MemberWishlistProduct present(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,memberWishlistProduct,tokens);
		
		
		MemberWishlistProduct  memberWishlistProductToPresent = memberWishlistProductDaoOf(userContext).present(memberWishlistProduct, tokens);
		
		List<BaseEntity> entityListToNaming = memberWishlistProductToPresent.collectRefercencesFromLists();
		memberWishlistProductDaoOf(userContext).alias(entityListToNaming);
		
		
		renderActionForList(userContext,memberWishlistProduct,tokens);
		
		return  memberWishlistProductToPresent;
		
		
	}
 
 	
 	
 	public MemberWishlistProduct loadMemberWishlistProductDetail(RetailscmUserContext userContext, String memberWishlistProductId) throws Exception{	
 		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct( userContext, memberWishlistProductId, allTokens());
 		return present(userContext,memberWishlistProduct, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String memberWishlistProductId) throws Exception{	
 		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct( userContext, memberWishlistProductId, viewTokens());
 		return present(userContext,memberWishlistProduct, allTokens());
		
 	}
 	protected MemberWishlistProduct saveMemberWishlistProduct(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, Map<String,Object>tokens) throws Exception{	
 		return memberWishlistProductDaoOf(userContext).save(memberWishlistProduct, tokens);
 	}
 	protected MemberWishlistProduct loadMemberWishlistProduct(RetailscmUserContext userContext, String memberWishlistProductId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfMemberWishlistProduct(memberWishlistProductId);
		checkerOf(userContext).throwExceptionIfHasErrors( MemberWishlistProductManagerException.class);

 
 		return memberWishlistProductDaoOf(userContext).load(memberWishlistProductId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, Map<String, Object> tokens){
		super.addActions(userContext, memberWishlistProduct, tokens);
		
		addAction(userContext, memberWishlistProduct, tokens,"@create","createMemberWishlistProduct","createMemberWishlistProduct/","main","primary");
		addAction(userContext, memberWishlistProduct, tokens,"@update","updateMemberWishlistProduct","updateMemberWishlistProduct/"+memberWishlistProduct.getId()+"/","main","primary");
		addAction(userContext, memberWishlistProduct, tokens,"@copy","cloneMemberWishlistProduct","cloneMemberWishlistProduct/"+memberWishlistProduct.getId()+"/","main","primary");
		
		addAction(userContext, memberWishlistProduct, tokens,"member_wishlist_product.transfer_to_owner","transferToAnotherOwner","transferToAnotherOwner/"+memberWishlistProduct.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public MemberWishlistProduct createMemberWishlistProduct(RetailscmUserContext userContext, String name,String ownerId) throws Exception
	//public MemberWishlistProduct createMemberWishlistProduct(RetailscmUserContext userContext,String name, String ownerId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfMemberWishlistProduct(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(MemberWishlistProductManagerException.class);


		MemberWishlistProduct memberWishlistProduct=createNewMemberWishlistProduct();	

		memberWishlistProduct.setName(name);
			
		MemberWishlist owner = loadMemberWishlist(userContext, ownerId,emptyOptions());
		memberWishlistProduct.setOwner(owner);
		
		

		memberWishlistProduct = saveMemberWishlistProduct(userContext, memberWishlistProduct, emptyOptions());
		
		onNewInstanceCreated(userContext, memberWishlistProduct);
		return memberWishlistProduct;


	}
	protected MemberWishlistProduct createNewMemberWishlistProduct()
	{

		return new MemberWishlistProduct();
	}

	protected void checkParamsForUpdatingMemberWishlistProduct(RetailscmUserContext userContext,String memberWishlistProductId, int memberWishlistProductVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfMemberWishlistProduct(memberWishlistProductId);
		checkerOf(userContext).checkVersionOfMemberWishlistProduct( memberWishlistProductVersion);
		

		if(MemberWishlistProduct.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfMemberWishlistProduct(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(MemberWishlistProductManagerException.class);


	}



	public MemberWishlistProduct clone(RetailscmUserContext userContext, String fromMemberWishlistProductId) throws Exception{

		return memberWishlistProductDaoOf(userContext).clone(fromMemberWishlistProductId, this.allTokens());
	}

	public MemberWishlistProduct internalSaveMemberWishlistProduct(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct) throws Exception
	{
		return internalSaveMemberWishlistProduct(userContext, memberWishlistProduct, allTokens());

	}
	public MemberWishlistProduct internalSaveMemberWishlistProduct(RetailscmUserContext userContext, MemberWishlistProduct memberWishlistProduct, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingMemberWishlistProduct(userContext, memberWishlistProductId, memberWishlistProductVersion, property, newValueExpr, tokensExpr);


		synchronized(memberWishlistProduct){
			//will be good when the memberWishlistProduct loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to MemberWishlistProduct.
			if (memberWishlistProduct.isChanged()){
			
			}
			memberWishlistProduct = saveMemberWishlistProduct(userContext, memberWishlistProduct, options);
			return memberWishlistProduct;

		}

	}

	public MemberWishlistProduct updateMemberWishlistProduct(RetailscmUserContext userContext,String memberWishlistProductId, int memberWishlistProductVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingMemberWishlistProduct(userContext, memberWishlistProductId, memberWishlistProductVersion, property, newValueExpr, tokensExpr);



		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct(userContext, memberWishlistProductId, allTokens());
		if(memberWishlistProduct.getVersion() != memberWishlistProductVersion){
			String message = "The target version("+memberWishlistProduct.getVersion()+") is not equals to version("+memberWishlistProductVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(memberWishlistProduct){
			//will be good when the memberWishlistProduct loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to MemberWishlistProduct.
			
			memberWishlistProduct.changeProperty(property, newValueExpr);
			memberWishlistProduct = saveMemberWishlistProduct(userContext, memberWishlistProduct, tokens().done());
			return present(userContext,memberWishlistProduct, mergedAllTokens(tokensExpr));
			//return saveMemberWishlistProduct(userContext, memberWishlistProduct, tokens().done());
		}

	}

	public MemberWishlistProduct updateMemberWishlistProductProperty(RetailscmUserContext userContext,String memberWishlistProductId, int memberWishlistProductVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingMemberWishlistProduct(userContext, memberWishlistProductId, memberWishlistProductVersion, property, newValueExpr, tokensExpr);

		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct(userContext, memberWishlistProductId, allTokens());
		if(memberWishlistProduct.getVersion() != memberWishlistProductVersion){
			String message = "The target version("+memberWishlistProduct.getVersion()+") is not equals to version("+memberWishlistProductVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(memberWishlistProduct){
			//will be good when the memberWishlistProduct loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to MemberWishlistProduct.

			memberWishlistProduct.changeProperty(property, newValueExpr);
			
			memberWishlistProduct = saveMemberWishlistProduct(userContext, memberWishlistProduct, tokens().done());
			return present(userContext,memberWishlistProduct, mergedAllTokens(tokensExpr));
			//return saveMemberWishlistProduct(userContext, memberWishlistProduct, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected MemberWishlistProductTokens tokens(){
		return MemberWishlistProductTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return MemberWishlistProductTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return MemberWishlistProductTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherOwner(RetailscmUserContext userContext, String memberWishlistProductId, String anotherOwnerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfMemberWishlistProduct(memberWishlistProductId);
 		checkerOf(userContext).checkIdOfMemberWishlist(anotherOwnerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(MemberWishlistProductManagerException.class);

 	}
 	public MemberWishlistProduct transferToAnotherOwner(RetailscmUserContext userContext, String memberWishlistProductId, String anotherOwnerId) throws Exception
 	{
 		checkParamsForTransferingAnotherOwner(userContext, memberWishlistProductId,anotherOwnerId);
 
		MemberWishlistProduct memberWishlistProduct = loadMemberWishlistProduct(userContext, memberWishlistProductId, allTokens());	
		synchronized(memberWishlistProduct){
			//will be good when the memberWishlistProduct loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			MemberWishlist owner = loadMemberWishlist(userContext, anotherOwnerId, emptyOptions());		
			memberWishlistProduct.updateOwner(owner);		
			memberWishlistProduct = saveMemberWishlistProduct(userContext, memberWishlistProduct, emptyOptions());
			
			return present(userContext,memberWishlistProduct, allTokens());
			
		}

 	}

	


	public CandidateMemberWishlist requestCandidateOwner(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateMemberWishlist result = new CandidateMemberWishlist();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<MemberWishlist> candidateList = memberWishlistDaoOf(userContext).requestCandidateMemberWishlistForMemberWishlistProduct(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected MemberWishlist loadMemberWishlist(RetailscmUserContext userContext, String newOwnerId, Map<String,Object> options) throws Exception
 	{

 		return memberWishlistDaoOf(userContext).load(newOwnerId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String memberWishlistProductId, int memberWishlistProductVersion) throws Exception {
		//deleteInternal(userContext, memberWishlistProductId, memberWishlistProductVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String memberWishlistProductId, int memberWishlistProductVersion) throws Exception{

		memberWishlistProductDaoOf(userContext).delete(memberWishlistProductId, memberWishlistProductVersion);
	}

	public MemberWishlistProduct forgetByAll(RetailscmUserContext userContext, String memberWishlistProductId, int memberWishlistProductVersion) throws Exception {
		return forgetByAllInternal(userContext, memberWishlistProductId, memberWishlistProductVersion);
	}
	protected MemberWishlistProduct forgetByAllInternal(RetailscmUserContext userContext,
			String memberWishlistProductId, int memberWishlistProductVersion) throws Exception{

		return memberWishlistProductDaoOf(userContext).disconnectFromAll(memberWishlistProductId, memberWishlistProductVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new MemberWishlistProductManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return memberWishlistProductDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(RetailscmUserContext userContext, MemberWishlistProduct newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

  public void sendAllItems(RetailscmUserContext ctx) throws Exception{
    memberWishlistProductDaoOf(ctx).loadAllAsStream().forEach(
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
		//   MemberWishlistProduct newMemberWishlistProduct = this.createMemberWishlistProduct(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newMemberWishlistProduct
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, MemberWishlistProduct.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<MemberWishlistProduct> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<MemberWishlist> ownerList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, MemberWishlist.class);
		userContext.getDAOGroup().enhanceList(ownerList, MemberWishlist.class);


    }
	
	public Object listByOwner(RetailscmUserContext userContext,String ownerId) throws Exception {
		return listPageByOwner(userContext, ownerId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByOwner(RetailscmUserContext userContext,String ownerId, int start, int count) throws Exception {
		SmartList<MemberWishlistProduct> list = memberWishlistProductDaoOf(userContext).findMemberWishlistProductByOwner(ownerId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(MemberWishlistProduct.class);
		page.setContainerObject(MemberWishlist.withId(ownerId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("会员收藏产品列表");
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
 	public Object wxappview(RetailscmUserContext userContext, String memberWishlistProductId) throws Exception{
	  SerializeScope vscope = RetailscmViewScope.getInstance().getMemberWishlistProductDetailScope().clone();
		MemberWishlistProduct merchantObj = (MemberWishlistProduct) this.view(userContext, memberWishlistProductId);
    String merchantObjId = memberWishlistProductId;
    String linkToUrl =	"memberWishlistProductManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "会员收藏产品"+"详情";
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
				MapUtil.put("id", "3-owner")
				    .put("fieldName", "owner")
				    .put("label", "业主")
				    .put("type", "auto")
				    .put("linkToUrl", "memberWishlistManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("owner", merchantObj.getOwner());

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


