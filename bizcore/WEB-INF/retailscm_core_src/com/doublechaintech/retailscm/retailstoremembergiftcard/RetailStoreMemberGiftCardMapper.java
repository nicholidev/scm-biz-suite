
package com.doublechaintech.retailscm.retailstoremembergiftcard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.retailstoremember.RetailStoreMember;

public class RetailStoreMemberGiftCardMapper extends BaseRowMapper<RetailStoreMemberGiftCard>{
	
	protected RetailStoreMemberGiftCard internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		RetailStoreMemberGiftCard retailStoreMemberGiftCard = getRetailStoreMemberGiftCard();		
		 		
 		setId(retailStoreMemberGiftCard, rs, rowNumber); 		
 		setName(retailStoreMemberGiftCard, rs, rowNumber); 		
 		setOwner(retailStoreMemberGiftCard, rs, rowNumber); 		
 		setNumber(retailStoreMemberGiftCard, rs, rowNumber); 		
 		setRemain(retailStoreMemberGiftCard, rs, rowNumber); 		
 		setVersion(retailStoreMemberGiftCard, rs, rowNumber);

		return retailStoreMemberGiftCard;
	}
	
	protected RetailStoreMemberGiftCard getRetailStoreMemberGiftCard(){
		return new RetailStoreMemberGiftCard();
	}		
		
	protected void setId(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(RetailStoreMemberGiftCardTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreMemberGiftCard.setId(id);
	}
		
	protected void setName(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(RetailStoreMemberGiftCardTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreMemberGiftCard.setName(name);
	}
		 		
 	protected void setOwner(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
 		String retailStoreMemberId = rs.getString(RetailStoreMemberGiftCardTable.COLUMN_OWNER);
 		if( retailStoreMemberId == null){
 			return;
 		}
 		if( retailStoreMemberId.isEmpty()){
 			return;
 		}
 		RetailStoreMember retailStoreMember = retailStoreMemberGiftCard.getOwner();
 		if( retailStoreMember != null ){
 			//if the root object 'retailStoreMemberGiftCard' already have the property, just set the id for it;
 			retailStoreMember.setId(retailStoreMemberId);
 			
 			return;
 		}
 		retailStoreMemberGiftCard.setOwner(createEmptyOwner(retailStoreMemberId));
 	}
 	
	protected void setNumber(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String number = rs.getString(RetailStoreMemberGiftCardTable.COLUMN_NUMBER);
		
		if(number == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreMemberGiftCard.setNumber(number);
	}
		
	protected void setRemain(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal remain = rs.getBigDecimal(RetailStoreMemberGiftCardTable.COLUMN_REMAIN);
		
		if(remain == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreMemberGiftCard.setRemain(remain);
	}
		
	protected void setVersion(RetailStoreMemberGiftCard retailStoreMemberGiftCard, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(RetailStoreMemberGiftCardTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreMemberGiftCard.setVersion(version);
	}
		
		

 	protected RetailStoreMember  createEmptyOwner(String retailStoreMemberId){
 		RetailStoreMember retailStoreMember = new RetailStoreMember();
 		retailStoreMember.setId(retailStoreMemberId);
 		retailStoreMember.setVersion(Integer.MAX_VALUE);
 		return retailStoreMember;
 	}
 	
}


