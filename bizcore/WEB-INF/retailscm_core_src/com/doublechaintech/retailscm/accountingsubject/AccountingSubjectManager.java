
package com.doublechaintech.retailscm.accountingsubject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.retailscm.RetailscmUserContext;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.BaseManager;
import com.doublechaintech.retailscm.SmartList;

public interface AccountingSubjectManager extends BaseManager{

		

	public AccountingSubject createAccountingSubject(RetailscmUserContext userContext, String accountingSubjectCode,String accountingSubjectName,int accountingSubjectClassCode,String accountingSubjectClassName,String accountSetId) throws Exception;
	public AccountingSubject updateAccountingSubject(RetailscmUserContext userContext,String accountingSubjectId, int accountingSubjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public AccountingSubject loadAccountingSubject(RetailscmUserContext userContext, String accountingSubjectId, String [] tokensExpr) throws Exception;
	public AccountingSubject internalSaveAccountingSubject(RetailscmUserContext userContext, AccountingSubject accountingSubject) throws Exception;
	public AccountingSubject internalSaveAccountingSubject(RetailscmUserContext userContext, AccountingSubject accountingSubject,Map<String,Object>option) throws Exception;

	public AccountingSubject transferToAnotherAccountSet(RetailscmUserContext userContext, String accountingSubjectId, String anotherAccountSetId)  throws Exception;
 

	public void delete(RetailscmUserContext userContext, String accountingSubjectId, int version) throws Exception;
	public int deleteAll(RetailscmUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(RetailscmUserContext userContext, AccountingSubject newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  AccountingDocumentLineManager getAccountingDocumentLineManager(RetailscmUserContext userContext, String accountingSubjectId, String name, String code, String direct, BigDecimal amount, String belongsToId ,String [] tokensExpr)  throws Exception;

	public  AccountingSubject addAccountingDocumentLine(RetailscmUserContext userContext, String accountingSubjectId, String name, String code, String direct, BigDecimal amount, String belongsToId , String [] tokensExpr)  throws Exception;
	public  AccountingSubject removeAccountingDocumentLine(RetailscmUserContext userContext, String accountingSubjectId, String accountingDocumentLineId, int accountingDocumentLineVersion,String [] tokensExpr)  throws Exception;
	public  AccountingSubject updateAccountingDocumentLine(RetailscmUserContext userContext, String accountingSubjectId, String accountingDocumentLineId, int accountingDocumentLineVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByAccountSet(RetailscmUserContext userContext,String accountSetId) throws Exception;
	public Object listPageByAccountSet(RetailscmUserContext userContext,String accountSetId, int start, int count) throws Exception;
  

}


