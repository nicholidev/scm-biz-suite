
package com.doublechaintech.retailscm.employeeskill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.skilltype.SkillType;
import com.doublechaintech.retailscm.employee.Employee;

public class EmployeeSkillMapper extends BaseRowMapper<EmployeeSkill>{
	
	protected EmployeeSkill internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EmployeeSkill employeeSkill = getEmployeeSkill();		
		 		
 		setId(employeeSkill, rs, rowNumber); 		
 		setEmployee(employeeSkill, rs, rowNumber); 		
 		setSkillType(employeeSkill, rs, rowNumber); 		
 		setDescription(employeeSkill, rs, rowNumber); 		
 		setVersion(employeeSkill, rs, rowNumber);

		return employeeSkill;
	}
	
	protected EmployeeSkill getEmployeeSkill(){
		return new EmployeeSkill();
	}		
		
	protected void setId(EmployeeSkill employeeSkill, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EmployeeSkillTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeSkill.setId(id);
	}
		 		
 	protected void setEmployee(EmployeeSkill employeeSkill, ResultSet rs, int rowNumber) throws SQLException{
 		String employeeId = rs.getString(EmployeeSkillTable.COLUMN_EMPLOYEE);
 		if( employeeId == null){
 			return;
 		}
 		if( employeeId.isEmpty()){
 			return;
 		}
 		Employee employee = employeeSkill.getEmployee();
 		if( employee != null ){
 			//if the root object 'employeeSkill' already have the property, just set the id for it;
 			employee.setId(employeeId);
 			
 			return;
 		}
 		employeeSkill.setEmployee(createEmptyEmployee(employeeId));
 	}
 	 		
 	protected void setSkillType(EmployeeSkill employeeSkill, ResultSet rs, int rowNumber) throws SQLException{
 		String skillTypeId = rs.getString(EmployeeSkillTable.COLUMN_SKILL_TYPE);
 		if( skillTypeId == null){
 			return;
 		}
 		if( skillTypeId.isEmpty()){
 			return;
 		}
 		SkillType skillType = employeeSkill.getSkillType();
 		if( skillType != null ){
 			//if the root object 'employeeSkill' already have the property, just set the id for it;
 			skillType.setId(skillTypeId);
 			
 			return;
 		}
 		employeeSkill.setSkillType(createEmptySkillType(skillTypeId));
 	}
 	
	protected void setDescription(EmployeeSkill employeeSkill, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String description = rs.getString(EmployeeSkillTable.COLUMN_DESCRIPTION);
		
		if(description == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeSkill.setDescription(description);
	}
		
	protected void setVersion(EmployeeSkill employeeSkill, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EmployeeSkillTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		employeeSkill.setVersion(version);
	}
		
		

 	protected Employee  createEmptyEmployee(String employeeId){
 		Employee employee = new Employee();
 		employee.setId(employeeId);
 		employee.setVersion(Integer.MAX_VALUE);
 		return employee;
 	}
 	
 	protected SkillType  createEmptySkillType(String skillTypeId){
 		SkillType skillType = new SkillType();
 		skillType.setId(skillTypeId);
 		skillType.setVersion(Integer.MAX_VALUE);
 		return skillType;
 	}
 	
}


