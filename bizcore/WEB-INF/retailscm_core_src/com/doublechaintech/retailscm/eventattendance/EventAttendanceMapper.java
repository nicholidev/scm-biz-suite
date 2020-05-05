
package com.doublechaintech.retailscm.eventattendance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.potentialcustomer.PotentialCustomer;
import com.doublechaintech.retailscm.cityevent.CityEvent;

public class EventAttendanceMapper extends BaseRowMapper<EventAttendance>{
	
	protected EventAttendance internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EventAttendance eventAttendance = getEventAttendance();		
		 		
 		setId(eventAttendance, rs, rowNumber); 		
 		setName(eventAttendance, rs, rowNumber); 		
 		setPotentialCustomer(eventAttendance, rs, rowNumber); 		
 		setCityEvent(eventAttendance, rs, rowNumber); 		
 		setDescription(eventAttendance, rs, rowNumber); 		
 		setVersion(eventAttendance, rs, rowNumber);

		return eventAttendance;
	}
	
	protected EventAttendance getEventAttendance(){
		return new EventAttendance();
	}		
		
	protected void setId(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EventAttendanceTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventAttendance.setId(id);
	}
		
	protected void setName(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(EventAttendanceTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventAttendance.setName(name);
	}
		 		
 	protected void setPotentialCustomer(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
 		String potentialCustomerId = rs.getString(EventAttendanceTable.COLUMN_POTENTIAL_CUSTOMER);
 		if( potentialCustomerId == null){
 			return;
 		}
 		if( potentialCustomerId.isEmpty()){
 			return;
 		}
 		PotentialCustomer potentialCustomer = eventAttendance.getPotentialCustomer();
 		if( potentialCustomer != null ){
 			//if the root object 'eventAttendance' already have the property, just set the id for it;
 			potentialCustomer.setId(potentialCustomerId);
 			
 			return;
 		}
 		eventAttendance.setPotentialCustomer(createEmptyPotentialCustomer(potentialCustomerId));
 	}
 	 		
 	protected void setCityEvent(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
 		String cityEventId = rs.getString(EventAttendanceTable.COLUMN_CITY_EVENT);
 		if( cityEventId == null){
 			return;
 		}
 		if( cityEventId.isEmpty()){
 			return;
 		}
 		CityEvent cityEvent = eventAttendance.getCityEvent();
 		if( cityEvent != null ){
 			//if the root object 'eventAttendance' already have the property, just set the id for it;
 			cityEvent.setId(cityEventId);
 			
 			return;
 		}
 		eventAttendance.setCityEvent(createEmptyCityEvent(cityEventId));
 	}
 	
	protected void setDescription(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String description = rs.getString(EventAttendanceTable.COLUMN_DESCRIPTION);
		
		if(description == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventAttendance.setDescription(description);
	}
		
	protected void setVersion(EventAttendance eventAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EventAttendanceTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventAttendance.setVersion(version);
	}
		
		

 	protected PotentialCustomer  createEmptyPotentialCustomer(String potentialCustomerId){
 		PotentialCustomer potentialCustomer = new PotentialCustomer();
 		potentialCustomer.setId(potentialCustomerId);
 		potentialCustomer.setVersion(Integer.MAX_VALUE);
 		return potentialCustomer;
 	}
 	
 	protected CityEvent  createEmptyCityEvent(String cityEventId){
 		CityEvent cityEvent = new CityEvent();
 		cityEvent.setId(cityEventId);
 		cityEvent.setVersion(Integer.MAX_VALUE);
 		return cityEvent;
 	}
 	
}


