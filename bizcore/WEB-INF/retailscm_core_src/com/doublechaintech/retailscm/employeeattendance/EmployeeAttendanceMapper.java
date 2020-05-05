
package com.doublechaintech.retailscm.employeeattendance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.employee.Employee;

public class EmployeeAttendanceMapper extends BaseRowMapper<EmployeeAttendance>{
	
	protected EmployeeAttendance internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EmployeeAttendance employeeAttendance = getEmployeeAttendance();		
		 		
 		setId(employeeAttendance, rs, rowNumber); 		
 		setEmployee(employeeAttendance, rs, rowNumber); 		
 		setEnterTime(employeeAttendance, rs, rowNumber); 		
 		setLeaveTime(employeeAttendance, rs, rowNumber); 		
 		setDurationHours(employeeAttendance, rs, rowNumber); 		
 		setRemark(employeeAttendance, rs, rowNumber); 		
 		setVersion(employeeAttendance, rs, rowNumber);

		return employeeAttendance;
	}
	
	protected EmployeeAttendance getEmployeeAttendance(){
		return new EmployeeAttendance();
	}		
		
	protected void setId(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EmployeeAttendanceTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setId(id);
	}
		 		
 	protected void setEmployee(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
 		String employeeId = rs.getString(EmployeeAttendanceTable.COLUMN_EMPLOYEE);
 		if( employeeId == null){
 			return;
 		}
 		if( employeeId.isEmpty()){
 			return;
 		}
 		Employee employee = employeeAttendance.getEmployee();
 		if( employee != null ){
 			//if the root object 'employeeAttendance' already have the property, just set the id for it;
 			employee.setId(employeeId);
 			
 			return;
 		}
 		employeeAttendance.setEmployee(createEmptyEmployee(employeeId));
 	}
 	
	protected void setEnterTime(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date enterTime = rs.getDate(EmployeeAttendanceTable.COLUMN_ENTER_TIME);
		
		if(enterTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setEnterTime(enterTime);
	}
		
	protected void setLeaveTime(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date leaveTime = rs.getDate(EmployeeAttendanceTable.COLUMN_LEAVE_TIME);
		
		if(leaveTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setLeaveTime(leaveTime);
	}
		
	protected void setDurationHours(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer durationHours = rs.getInt(EmployeeAttendanceTable.COLUMN_DURATION_HOURS);
		
		if(durationHours == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setDurationHours(durationHours);
	}
		
	protected void setRemark(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String remark = rs.getString(EmployeeAttendanceTable.COLUMN_REMARK);
		
		if(remark == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setRemark(remark);
	}
		
	protected void setVersion(EmployeeAttendance employeeAttendance, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EmployeeAttendanceTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeAttendance.setVersion(version);
	}
		
		

 	protected Employee  createEmptyEmployee(String employeeId){
 		Employee employee = new Employee();
 		employee.setId(employeeId);
 		employee.setVersion(Integer.MAX_VALUE);
 		return employee;
 	}
 	
}


