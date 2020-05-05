
package com.doublechaintech.retailscm.retailstorecountrycenter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;

public class RetailStoreCountryCenterMapper extends BaseRowMapper<RetailStoreCountryCenter>{
	
	protected RetailStoreCountryCenter internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		RetailStoreCountryCenter retailStoreCountryCenter = getRetailStoreCountryCenter();		
		 		
 		setId(retailStoreCountryCenter, rs, rowNumber); 		
 		setName(retailStoreCountryCenter, rs, rowNumber); 		
 		setServiceNumber(retailStoreCountryCenter, rs, rowNumber); 		
 		setFounded(retailStoreCountryCenter, rs, rowNumber); 		
 		setWebSite(retailStoreCountryCenter, rs, rowNumber); 		
 		setAddress(retailStoreCountryCenter, rs, rowNumber); 		
 		setOperatedBy(retailStoreCountryCenter, rs, rowNumber); 		
 		setLegalRepresentative(retailStoreCountryCenter, rs, rowNumber); 		
 		setDescription(retailStoreCountryCenter, rs, rowNumber); 		
 		setVersion(retailStoreCountryCenter, rs, rowNumber);

		return retailStoreCountryCenter;
	}
	
	protected RetailStoreCountryCenter getRetailStoreCountryCenter(){
		return new RetailStoreCountryCenter();
	}		
		
	protected void setId(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(RetailStoreCountryCenterTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setId(id);
	}
		
	protected void setName(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(RetailStoreCountryCenterTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setName(name);
	}
		
	protected void setServiceNumber(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String serviceNumber = rs.getString(RetailStoreCountryCenterTable.COLUMN_SERVICE_NUMBER);
		
		if(serviceNumber == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setServiceNumber(serviceNumber);
	}
		
	protected void setFounded(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date founded = rs.getDate(RetailStoreCountryCenterTable.COLUMN_FOUNDED);
		
		if(founded == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setFounded(founded);
	}
		
	protected void setWebSite(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String webSite = rs.getString(RetailStoreCountryCenterTable.COLUMN_WEB_SITE);
		
		if(webSite == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setWebSite(webSite);
	}
		
	protected void setAddress(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String address = rs.getString(RetailStoreCountryCenterTable.COLUMN_ADDRESS);
		
		if(address == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setAddress(address);
	}
		
	protected void setOperatedBy(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String operatedBy = rs.getString(RetailStoreCountryCenterTable.COLUMN_OPERATED_BY);
		
		if(operatedBy == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setOperatedBy(operatedBy);
	}
		
	protected void setLegalRepresentative(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String legalRepresentative = rs.getString(RetailStoreCountryCenterTable.COLUMN_LEGAL_REPRESENTATIVE);
		
		if(legalRepresentative == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setLegalRepresentative(legalRepresentative);
	}
		
	protected void setDescription(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String description = rs.getString(RetailStoreCountryCenterTable.COLUMN_DESCRIPTION);
		
		if(description == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setDescription(description);
	}
		
	protected void setVersion(RetailStoreCountryCenter retailStoreCountryCenter, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(RetailStoreCountryCenterTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		retailStoreCountryCenter.setVersion(version);
	}
		
		

}


