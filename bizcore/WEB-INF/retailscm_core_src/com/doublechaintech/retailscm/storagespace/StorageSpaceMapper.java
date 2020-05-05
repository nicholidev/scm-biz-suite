
package com.doublechaintech.retailscm.storagespace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.warehouse.Warehouse;

public class StorageSpaceMapper extends BaseRowMapper<StorageSpace>{
	
	protected StorageSpace internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		StorageSpace storageSpace = getStorageSpace();		
		 		
 		setId(storageSpace, rs, rowNumber); 		
 		setLocation(storageSpace, rs, rowNumber); 		
 		setContactNumber(storageSpace, rs, rowNumber); 		
 		setTotalArea(storageSpace, rs, rowNumber); 		
 		setWarehouse(storageSpace, rs, rowNumber); 		
 		setLatitude(storageSpace, rs, rowNumber); 		
 		setLongitude(storageSpace, rs, rowNumber); 		
 		setLastUpdateTime(storageSpace, rs, rowNumber); 		
 		setVersion(storageSpace, rs, rowNumber);

		return storageSpace;
	}
	
	protected StorageSpace getStorageSpace(){
		return new StorageSpace();
	}		
		
	protected void setId(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(StorageSpaceTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setId(id);
	}
		
	protected void setLocation(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String location = rs.getString(StorageSpaceTable.COLUMN_LOCATION);
		
		if(location == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setLocation(location);
	}
		
	protected void setContactNumber(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String contactNumber = rs.getString(StorageSpaceTable.COLUMN_CONTACT_NUMBER);
		
		if(contactNumber == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setContactNumber(contactNumber);
	}
		
	protected void setTotalArea(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String totalArea = rs.getString(StorageSpaceTable.COLUMN_TOTAL_AREA);
		
		if(totalArea == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setTotalArea(totalArea);
	}
		 		
 	protected void setWarehouse(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
 		String warehouseId = rs.getString(StorageSpaceTable.COLUMN_WAREHOUSE);
 		if( warehouseId == null){
 			return;
 		}
 		if( warehouseId.isEmpty()){
 			return;
 		}
 		Warehouse warehouse = storageSpace.getWarehouse();
 		if( warehouse != null ){
 			//if the root object 'storageSpace' already have the property, just set the id for it;
 			warehouse.setId(warehouseId);
 			
 			return;
 		}
 		storageSpace.setWarehouse(createEmptyWarehouse(warehouseId));
 	}
 	
	protected void setLatitude(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal latitude = rs.getBigDecimal(StorageSpaceTable.COLUMN_LATITUDE);
		
		if(latitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setLatitude(latitude);
	}
		
	protected void setLongitude(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal longitude = rs.getBigDecimal(StorageSpaceTable.COLUMN_LONGITUDE);
		
		if(longitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setLongitude(longitude);
	}
		
	protected void setLastUpdateTime(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date lastUpdateTime = rs.getTimestamp(StorageSpaceTable.COLUMN_LAST_UPDATE_TIME);
		
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		
	protected void setVersion(StorageSpace storageSpace, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(StorageSpaceTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		storageSpace.setVersion(version);
	}
		
		

 	protected Warehouse  createEmptyWarehouse(String warehouseId){
 		Warehouse warehouse = new Warehouse();
 		warehouse.setId(warehouseId);
 		warehouse.setVersion(Integer.MAX_VALUE);
 		return warehouse;
 	}
 	
}


