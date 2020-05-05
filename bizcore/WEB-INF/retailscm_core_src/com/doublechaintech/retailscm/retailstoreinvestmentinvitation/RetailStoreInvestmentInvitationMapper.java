
package com.doublechaintech.retailscm.retailstoreinvestmentinvitation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;

public class RetailStoreInvestmentInvitationMapper extends BaseRowMapper<RetailStoreInvestmentInvitation>{
	
	protected RetailStoreInvestmentInvitation internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		RetailStoreInvestmentInvitation retailStoreInvestmentInvitation = getRetailStoreInvestmentInvitation();		
		 		
 		setId(retailStoreInvestmentInvitation, rs, rowNumber); 		
 		setComment(retailStoreInvestmentInvitation, rs, rowNumber); 		
 		setVersion(retailStoreInvestmentInvitation, rs, rowNumber);

		return retailStoreInvestmentInvitation;
	}
	
	protected RetailStoreInvestmentInvitation getRetailStoreInvestmentInvitation(){
		return new RetailStoreInvestmentInvitation();
	}		
		
	protected void setId(RetailStoreInvestmentInvitation retailStoreInvestmentInvitation, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(RetailStoreInvestmentInvitationTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreInvestmentInvitation.setId(id);
	}
		
	protected void setComment(RetailStoreInvestmentInvitation retailStoreInvestmentInvitation, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String comment = rs.getString(RetailStoreInvestmentInvitationTable.COLUMN_COMMENT);
		
		if(comment == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreInvestmentInvitation.setComment(comment);
	}
		
	protected void setVersion(RetailStoreInvestmentInvitation retailStoreInvestmentInvitation, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(RetailStoreInvestmentInvitationTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreInvestmentInvitation.setVersion(version);
	}
		
		

}


