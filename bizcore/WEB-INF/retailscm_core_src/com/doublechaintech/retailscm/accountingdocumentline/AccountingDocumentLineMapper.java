
package com.doublechaintech.retailscm.accountingdocumentline;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.accountingdocument.AccountingDocument;
import com.doublechaintech.retailscm.accountingsubject.AccountingSubject;

public class AccountingDocumentLineMapper extends BaseRowMapper<AccountingDocumentLine>{
	
	protected AccountingDocumentLine internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		AccountingDocumentLine accountingDocumentLine = getAccountingDocumentLine();		
		 		
 		setId(accountingDocumentLine, rs, rowNumber); 		
 		setName(accountingDocumentLine, rs, rowNumber); 		
 		setCode(accountingDocumentLine, rs, rowNumber); 		
 		setDirect(accountingDocumentLine, rs, rowNumber); 		
 		setAmount(accountingDocumentLine, rs, rowNumber); 		
 		setBelongsTo(accountingDocumentLine, rs, rowNumber); 		
 		setAccountingSubject(accountingDocumentLine, rs, rowNumber); 		
 		setVersion(accountingDocumentLine, rs, rowNumber);

		return accountingDocumentLine;
	}
	
	protected AccountingDocumentLine getAccountingDocumentLine(){
		return new AccountingDocumentLine();
	}		
		
	protected void setId(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(AccountingDocumentLineTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setId(id);
	}
		
	protected void setName(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(AccountingDocumentLineTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setName(name);
	}
		
	protected void setCode(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String code = rs.getString(AccountingDocumentLineTable.COLUMN_CODE);
		
		if(code == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setCode(code);
	}
		
	protected void setDirect(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String direct = rs.getString(AccountingDocumentLineTable.COLUMN_DIRECT);
		
		if(direct == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setDirect(direct);
	}
		
	protected void setAmount(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal amount = rs.getBigDecimal(AccountingDocumentLineTable.COLUMN_AMOUNT);
		
		if(amount == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setAmount(amount);
	}
		 		
 	protected void setBelongsTo(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
 		String accountingDocumentId = rs.getString(AccountingDocumentLineTable.COLUMN_BELONGS_TO);
 		if( accountingDocumentId == null){
 			return;
 		}
 		if( accountingDocumentId.isEmpty()){
 			return;
 		}
 		AccountingDocument accountingDocument = accountingDocumentLine.getBelongsTo();
 		if( accountingDocument != null ){
 			//if the root object 'accountingDocumentLine' already have the property, just set the id for it;
 			accountingDocument.setId(accountingDocumentId);
 			
 			return;
 		}
 		accountingDocumentLine.setBelongsTo(createEmptyBelongsTo(accountingDocumentId));
 	}
 	 		
 	protected void setAccountingSubject(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
 		String accountingSubjectId = rs.getString(AccountingDocumentLineTable.COLUMN_ACCOUNTING_SUBJECT);
 		if( accountingSubjectId == null){
 			return;
 		}
 		if( accountingSubjectId.isEmpty()){
 			return;
 		}
 		AccountingSubject accountingSubject = accountingDocumentLine.getAccountingSubject();
 		if( accountingSubject != null ){
 			//if the root object 'accountingDocumentLine' already have the property, just set the id for it;
 			accountingSubject.setId(accountingSubjectId);
 			
 			return;
 		}
 		accountingDocumentLine.setAccountingSubject(createEmptyAccountingSubject(accountingSubjectId));
 	}
 	
	protected void setVersion(AccountingDocumentLine accountingDocumentLine, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(AccountingDocumentLineTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountingDocumentLine.setVersion(version);
	}
		
		

 	protected AccountingDocument  createEmptyBelongsTo(String accountingDocumentId){
 		AccountingDocument accountingDocument = new AccountingDocument();
 		accountingDocument.setId(accountingDocumentId);
 		accountingDocument.setVersion(Integer.MAX_VALUE);
 		return accountingDocument;
 	}
 	
 	protected AccountingSubject  createEmptyAccountingSubject(String accountingSubjectId){
 		AccountingSubject accountingSubject = new AccountingSubject();
 		accountingSubject.setId(accountingSubjectId);
 		accountingSubject.setVersion(Integer.MAX_VALUE);
 		return accountingSubject;
 	}
 	
}


