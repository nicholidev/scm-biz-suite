
package com.doublechaintech.retailscm.employeequalifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.employee.Employee;

public class EmployeeQualifierMapper extends BaseRowMapper<EmployeeQualifier>{
	
	protected EmployeeQualifier internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EmployeeQualifier employeeQualifier = getEmployeeQualifier();		
		 		
 		setId(employeeQualifier, rs, rowNumber); 		
 		setEmployee(employeeQualifier, rs, rowNumber); 		
 		setQualifiedTime(employeeQualifier, rs, rowNumber); 		
 		setType(employeeQualifier, rs, rowNumber); 		
 		setLevel(employeeQualifier, rs, rowNumber); 		
 		setRemark(employeeQualifier, rs, rowNumber); 		
 		setVersion(employeeQualifier, rs, rowNumber);

		return employeeQualifier;
	}
	
	protected EmployeeQualifier getEmployeeQualifier(){
		return new EmployeeQualifier();
	}		
		
	protected void setId(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EmployeeQualifierTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setId(id);
	}
		 		
 	protected void setEmployee(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
 		String employeeId = rs.getString(EmployeeQualifierTable.COLUMN_EMPLOYEE);
 		if( employeeId == null){
 			return;
 		}
 		if( employeeId.isEmpty()){
 			return;
 		}
 		Employee employee = employeeQualifier.getEmployee();
 		if( employee != null ){
 			//if the root object 'employeeQualifier' already have the property, just set the id for it;
 			employee.setId(employeeId);
 			
 			return;
 		}
 		employeeQualifier.setEmployee(createEmptyEmployee(employeeId));
 	}
 	
	protected void setQualifiedTime(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date qualifiedTime = rs.getDate(EmployeeQualifierTable.COLUMN_QUALIFIED_TIME);
		
		if(qualifiedTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setQualifiedTime(qualifiedTime);
	}
		
	protected void setType(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String type = rs.getString(EmployeeQualifierTable.COLUMN_TYPE);
		
		if(type == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setType(type);
	}
		
	protected void setLevel(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String level = rs.getString(EmployeeQualifierTable.COLUMN_LEVEL);
		
		if(level == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setLevel(level);
	}
		
	protected void setRemark(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String remark = rs.getString(EmployeeQualifierTable.COLUMN_REMARK);
		
		if(remark == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setRemark(remark);
	}
		
	protected void setVersion(EmployeeQualifier employeeQualifier, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EmployeeQualifierTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeQualifier.setVersion(version);
	}
		
		

 	protected Employee  createEmptyEmployee(String employeeId){
 		Employee employee = new Employee();
 		employee.setId(employeeId);
 		employee.setVersion(Integer.MAX_VALUE);
 		return employee;
 	}
 	
}


