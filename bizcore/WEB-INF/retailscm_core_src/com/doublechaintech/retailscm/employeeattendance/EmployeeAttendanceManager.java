
package com.doublechaintech.retailscm.employeeattendance;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.retailscm.RetailscmUserContext;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.BaseManager;
import com.doublechaintech.retailscm.SmartList;

public interface EmployeeAttendanceManager extends BaseManager{

		

	public EmployeeAttendance createEmployeeAttendance(RetailscmUserContext userContext, String employeeId,Date enterTime,Date leaveTime,int durationHours,String remark) throws Exception;
	public EmployeeAttendance updateEmployeeAttendance(RetailscmUserContext userContext,String employeeAttendanceId, int employeeAttendanceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public EmployeeAttendance loadEmployeeAttendance(RetailscmUserContext userContext, String employeeAttendanceId, String [] tokensExpr) throws Exception;
	public EmployeeAttendance internalSaveEmployeeAttendance(RetailscmUserContext userContext, EmployeeAttendance employeeAttendance) throws Exception;
	public EmployeeAttendance internalSaveEmployeeAttendance(RetailscmUserContext userContext, EmployeeAttendance employeeAttendance,Map<String,Object>option) throws Exception;

	public EmployeeAttendance transferToAnotherEmployee(RetailscmUserContext userContext, String employeeAttendanceId, String anotherEmployeeId)  throws Exception;
 

	public void delete(RetailscmUserContext userContext, String employeeAttendanceId, int version) throws Exception;
	public int deleteAll(RetailscmUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(RetailscmUserContext userContext, EmployeeAttendance newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByEmployee(RetailscmUserContext userContext,String employeeId) throws Exception;
	public Object listPageByEmployee(RetailscmUserContext userContext,String employeeId, int start, int count) throws Exception;
  

}


