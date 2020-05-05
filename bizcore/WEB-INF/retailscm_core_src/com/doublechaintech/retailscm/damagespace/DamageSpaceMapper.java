
package com.doublechaintech.retailscm.damagespace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.warehouse.Warehouse;

public class DamageSpaceMapper extends BaseRowMapper<DamageSpace>{
	
	protected DamageSpace internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		DamageSpace damageSpace = getDamageSpace();		
		 		
 		setId(damageSpace, rs, rowNumber); 		
 		setLocation(damageSpace, rs, rowNumber); 		
 		setContactNumber(damageSpace, rs, rowNumber); 		
 		setTotalArea(damageSpace, rs, rowNumber); 		
 		setLatitude(damageSpace, rs, rowNumber); 		
 		setLongitude(damageSpace, rs, rowNumber); 		
 		setWarehouse(damageSpace, rs, rowNumber); 		
 		setLastUpdateTime(damageSpace, rs, rowNumber); 		
 		setVersion(damageSpace, rs, rowNumber);

		return damageSpace;
	}
	
	protected DamageSpace getDamageSpace(){
		return new DamageSpace();
	}		
		
	protected void setId(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(DamageSpaceTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setId(id);
	}
		
	protected void setLocation(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String location = rs.getString(DamageSpaceTable.COLUMN_LOCATION);
		
		if(location == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setLocation(location);
	}
		
	protected void setContactNumber(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String contactNumber = rs.getString(DamageSpaceTable.COLUMN_CONTACT_NUMBER);
		
		if(contactNumber == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setContactNumber(contactNumber);
	}
		
	protected void setTotalArea(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String totalArea = rs.getString(DamageSpaceTable.COLUMN_TOTAL_AREA);
		
		if(totalArea == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setTotalArea(totalArea);
	}
		
	protected void setLatitude(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal latitude = rs.getBigDecimal(DamageSpaceTable.COLUMN_LATITUDE);
		
		if(latitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setLatitude(latitude);
	}
		
	protected void setLongitude(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal longitude = rs.getBigDecimal(DamageSpaceTable.COLUMN_LONGITUDE);
		
		if(longitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setLongitude(longitude);
	}
		 		
 	protected void setWarehouse(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
 		String warehouseId = rs.getString(DamageSpaceTable.COLUMN_WAREHOUSE);
 		if( warehouseId == null){
 			return;
 		}
 		if( warehouseId.isEmpty()){
 			return;
 		}
 		Warehouse warehouse = damageSpace.getWarehouse();
 		if( warehouse != null ){
 			//if the root object 'damageSpace' already have the property, just set the id for it;
 			warehouse.setId(warehouseId);
 			
 			return;
 		}
 		damageSpace.setWarehouse(createEmptyWarehouse(warehouseId));
 	}
 	
	protected void setLastUpdateTime(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date lastUpdateTime = rs.getTimestamp(DamageSpaceTable.COLUMN_LAST_UPDATE_TIME);
		
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		
	protected void setVersion(DamageSpace damageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(DamageSpaceTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		damageSpace.setVersion(version);
	}
		
		

 	protected Warehouse  createEmptyWarehouse(String warehouseId){
 		Warehouse warehouse = new Warehouse();
 		warehouse.setId(warehouseId);
 		warehouse.setVersion(Integer.MAX_VALUE);
 		return warehouse;
 	}
 	
}


