
package com.doublechaintech.retailscm.employeeleave;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.employee.Employee;
import com.doublechaintech.retailscm.leavetype.LeaveType;

public class EmployeeLeaveMapper extends BaseRowMapper<EmployeeLeave>{
	
	protected EmployeeLeave internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EmployeeLeave employeeLeave = getEmployeeLeave();		
		 		
 		setId(employeeLeave, rs, rowNumber); 		
 		setWho(employeeLeave, rs, rowNumber); 		
 		setType(employeeLeave, rs, rowNumber); 		
 		setLeaveDurationHour(employeeLeave, rs, rowNumber); 		
 		setRemark(employeeLeave, rs, rowNumber); 		
 		setVersion(employeeLeave, rs, rowNumber);

		return employeeLeave;
	}
	
	protected EmployeeLeave getEmployeeLeave(){
		return new EmployeeLeave();
	}		
		
	protected void setId(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EmployeeLeaveTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeLeave.setId(id);
	}
		 		
 	protected void setWho(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
 		String employeeId = rs.getString(EmployeeLeaveTable.COLUMN_WHO);
 		if( employeeId == null){
 			return;
 		}
 		if( employeeId.isEmpty()){
 			return;
 		}
 		Employee employee = employeeLeave.getWho();
 		if( employee != null ){
 			//if the root object 'employeeLeave' already have the property, just set the id for it;
 			employee.setId(employeeId);
 			
 			return;
 		}
 		employeeLeave.setWho(createEmptyWho(employeeId));
 	}
 	 		
 	protected void setType(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
 		String leaveTypeId = rs.getString(EmployeeLeaveTable.COLUMN_TYPE);
 		if( leaveTypeId == null){
 			return;
 		}
 		if( leaveTypeId.isEmpty()){
 			return;
 		}
 		LeaveType leaveType = employeeLeave.getType();
 		if( leaveType != null ){
 			//if the root object 'employeeLeave' already have the property, just set the id for it;
 			leaveType.setId(leaveTypeId);
 			
 			return;
 		}
 		employeeLeave.setType(createEmptyType(leaveTypeId));
 	}
 	
	protected void setLeaveDurationHour(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer leaveDurationHour = rs.getInt(EmployeeLeaveTable.COLUMN_LEAVE_DURATION_HOUR);
		
		if(leaveDurationHour == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeLeave.setLeaveDurationHour(leaveDurationHour);
	}
		
	protected void setRemark(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String remark = rs.getString(EmployeeLeaveTable.COLUMN_REMARK);
		
		if(remark == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeLeave.setRemark(remark);
	}
		
	protected void setVersion(EmployeeLeave employeeLeave, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EmployeeLeaveTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeLeave.setVersion(version);
	}
		
		

 	protected Employee  createEmptyWho(String employeeId){
 		Employee employee = new Employee();
 		employee.setId(employeeId);
 		employee.setVersion(Integer.MAX_VALUE);
 		return employee;
 	}
 	
 	protected LeaveType  createEmptyType(String leaveTypeId){
 		LeaveType leaveType = new LeaveType();
 		leaveType.setId(leaveTypeId);
 		leaveType.setVersion(Integer.MAX_VALUE);
 		return leaveType;
 	}
 	
}


