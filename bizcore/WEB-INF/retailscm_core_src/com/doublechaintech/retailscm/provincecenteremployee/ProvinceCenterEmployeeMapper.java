
package com.doublechaintech.retailscm.provincecenteremployee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.provincecenterdepartment.ProvinceCenterDepartment;
import com.doublechaintech.retailscm.retailstoreprovincecenter.RetailStoreProvinceCenter;

public class ProvinceCenterEmployeeMapper extends BaseRowMapper<ProvinceCenterEmployee>{
	
	protected ProvinceCenterEmployee internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ProvinceCenterEmployee provinceCenterEmployee = getProvinceCenterEmployee();		
		 		
 		setId(provinceCenterEmployee, rs, rowNumber); 		
 		setName(provinceCenterEmployee, rs, rowNumber); 		
 		setMobile(provinceCenterEmployee, rs, rowNumber); 		
 		setEmail(provinceCenterEmployee, rs, rowNumber); 		
 		setFounded(provinceCenterEmployee, rs, rowNumber); 		
 		setDepartment(provinceCenterEmployee, rs, rowNumber); 		
 		setProvinceCenter(provinceCenterEmployee, rs, rowNumber); 		
 		setVersion(provinceCenterEmployee, rs, rowNumber);

		return provinceCenterEmployee;
	}
	
	protected ProvinceCenterEmployee getProvinceCenterEmployee(){
		return new ProvinceCenterEmployee();
	}		
		
	protected void setId(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ProvinceCenterEmployeeTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setId(id);
	}
		
	protected void setName(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(ProvinceCenterEmployeeTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setName(name);
	}
		
	protected void setMobile(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String mobile = rs.getString(ProvinceCenterEmployeeTable.COLUMN_MOBILE);
		
		if(mobile == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setMobile(mobile);
	}
		
	protected void setEmail(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String email = rs.getString(ProvinceCenterEmployeeTable.COLUMN_EMAIL);
		
		if(email == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setEmail(email);
	}
		
	protected void setFounded(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date founded = rs.getDate(ProvinceCenterEmployeeTable.COLUMN_FOUNDED);
		
		if(founded == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setFounded(founded);
	}
		 		
 	protected void setDepartment(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
 		String provinceCenterDepartmentId = rs.getString(ProvinceCenterEmployeeTable.COLUMN_DEPARTMENT);
 		if( provinceCenterDepartmentId == null){
 			return;
 		}
 		if( provinceCenterDepartmentId.isEmpty()){
 			return;
 		}
 		ProvinceCenterDepartment provinceCenterDepartment = provinceCenterEmployee.getDepartment();
 		if( provinceCenterDepartment != null ){
 			//if the root object 'provinceCenterEmployee' already have the property, just set the id for it;
 			provinceCenterDepartment.setId(provinceCenterDepartmentId);
 			
 			return;
 		}
 		provinceCenterEmployee.setDepartment(createEmptyDepartment(provinceCenterDepartmentId));
 	}
 	 		
 	protected void setProvinceCenter(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
 		String retailStoreProvinceCenterId = rs.getString(ProvinceCenterEmployeeTable.COLUMN_PROVINCE_CENTER);
 		if( retailStoreProvinceCenterId == null){
 			return;
 		}
 		if( retailStoreProvinceCenterId.isEmpty()){
 			return;
 		}
 		RetailStoreProvinceCenter retailStoreProvinceCenter = provinceCenterEmployee.getProvinceCenter();
 		if( retailStoreProvinceCenter != null ){
 			//if the root object 'provinceCenterEmployee' already have the property, just set the id for it;
 			retailStoreProvinceCenter.setId(retailStoreProvinceCenterId);
 			
 			return;
 		}
 		provinceCenterEmployee.setProvinceCenter(createEmptyProvinceCenter(retailStoreProvinceCenterId));
 	}
 	
	protected void setVersion(ProvinceCenterEmployee provinceCenterEmployee, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ProvinceCenterEmployeeTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		provinceCenterEmployee.setVersion(version);
	}
		
		

 	protected ProvinceCenterDepartment  createEmptyDepartment(String provinceCenterDepartmentId){
 		ProvinceCenterDepartment provinceCenterDepartment = new ProvinceCenterDepartment();
 		provinceCenterDepartment.setId(provinceCenterDepartmentId);
 		provinceCenterDepartment.setVersion(Integer.MAX_VALUE);
 		return provinceCenterDepartment;
 	}
 	
 	protected RetailStoreProvinceCenter  createEmptyProvinceCenter(String retailStoreProvinceCenterId){
 		RetailStoreProvinceCenter retailStoreProvinceCenter = new RetailStoreProvinceCenter();
 		retailStoreProvinceCenter.setId(retailStoreProvinceCenterId);
 		retailStoreProvinceCenter.setVersion(Integer.MAX_VALUE);
 		return retailStoreProvinceCenter;
 	}
 	
}


