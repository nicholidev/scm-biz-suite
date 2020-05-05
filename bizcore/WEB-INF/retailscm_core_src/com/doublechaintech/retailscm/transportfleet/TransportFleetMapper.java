
package com.doublechaintech.retailscm.transportfleet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;

public class TransportFleetMapper extends BaseRowMapper<TransportFleet>{
	
	protected TransportFleet internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		TransportFleet transportFleet = getTransportFleet();		
		 		
 		setId(transportFleet, rs, rowNumber); 		
 		setName(transportFleet, rs, rowNumber); 		
 		setContactNumber(transportFleet, rs, rowNumber); 		
 		setOwner(transportFleet, rs, rowNumber); 		
 		setLastUpdateTime(transportFleet, rs, rowNumber); 		
 		setVersion(transportFleet, rs, rowNumber);

		return transportFleet;
	}
	
	protected TransportFleet getTransportFleet(){
		return new TransportFleet();
	}		
		
	protected void setId(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(TransportFleetTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		transportFleet.setId(id);
	}
		
	protected void setName(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(TransportFleetTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		transportFleet.setName(name);
	}
		
	protected void setContactNumber(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String contactNumber = rs.getString(TransportFleetTable.COLUMN_CONTACT_NUMBER);
		
		if(contactNumber == null){
			//do nothing when nothing found in database
			return;
		}
		
		transportFleet.setContactNumber(contactNumber);
	}
		 		
 	protected void setOwner(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
 		String retailStoreCountryCenterId = rs.getString(TransportFleetTable.COLUMN_OWNER);
 		if( retailStoreCountryCenterId == null){
 			return;
 		}
 		if( retailStoreCountryCenterId.isEmpty()){
 			return;
 		}
 		RetailStoreCountryCenter retailStoreCountryCenter = transportFleet.getOwner();
 		if( retailStoreCountryCenter != null ){
 			//if the root object 'transportFleet' already have the property, just set the id for it;
 			retailStoreCountryCenter.setId(retailStoreCountryCenterId);
 			
 			return;
 		}
 		transportFleet.setOwner(createEmptyOwner(retailStoreCountryCenterId));
 	}
 	
	protected void setLastUpdateTime(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date lastUpdateTime = rs.getTimestamp(TransportFleetTable.COLUMN_LAST_UPDATE_TIME);
		
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		transportFleet.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		
	protected void setVersion(TransportFleet transportFleet, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(TransportFleetTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		transportFleet.setVersion(version);
	}
		
		

 	protected RetailStoreCountryCenter  createEmptyOwner(String retailStoreCountryCenterId){
 		RetailStoreCountryCenter retailStoreCountryCenter = new RetailStoreCountryCenter();
 		retailStoreCountryCenter.setId(retailStoreCountryCenterId);
 		retailStoreCountryCenter.setVersion(Integer.MAX_VALUE);
 		return retailStoreCountryCenter;
 	}
 	
}


