
package com.doublechaintech.retailscm.retailstorefranchising;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;

public class RetailStoreFranchisingMapper extends BaseRowMapper<RetailStoreFranchising>{
	
	protected RetailStoreFranchising internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		RetailStoreFranchising retailStoreFranchising = getRetailStoreFranchising();		
		 		
 		setId(retailStoreFranchising, rs, rowNumber); 		
 		setComment(retailStoreFranchising, rs, rowNumber); 		
 		setVersion(retailStoreFranchising, rs, rowNumber);

		return retailStoreFranchising;
	}
	
	protected RetailStoreFranchising getRetailStoreFranchising(){
		return new RetailStoreFranchising();
	}		
		
	protected void setId(RetailStoreFranchising retailStoreFranchising, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(RetailStoreFranchisingTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreFranchising.setId(id);
	}
		
	protected void setComment(RetailStoreFranchising retailStoreFranchising, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String comment = rs.getString(RetailStoreFranchisingTable.COLUMN_COMMENT);
		
		if(comment == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreFranchising.setComment(comment);
	}
		
	protected void setVersion(RetailStoreFranchising retailStoreFranchising, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(RetailStoreFranchisingTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreFranchising.setVersion(version);
	}
		
		

}


