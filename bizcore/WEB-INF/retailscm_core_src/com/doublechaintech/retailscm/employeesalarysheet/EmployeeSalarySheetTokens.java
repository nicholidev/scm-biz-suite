
package com.doublechaintech.retailscm.employeesalarysheet;
import com.doublechaintech.retailscm.CommonTokens;
import java.util.Map;
public class EmployeeSalarySheetTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="employeeSalarySheet";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected EmployeeSalarySheetTokens(){
		//ensure not initialized outside the class
	}
	public  static  EmployeeSalarySheetTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		EmployeeSalarySheetTokens tokens = new EmployeeSalarySheetTokens(options);
		return tokens;
		
	}
	protected EmployeeSalarySheetTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public EmployeeSalarySheetTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static EmployeeSalarySheetTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected EmployeeSalarySheetTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static EmployeeSalarySheetTokens start(){
		return new EmployeeSalarySheetTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public EmployeeSalarySheetTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static EmployeeSalarySheetTokens allTokens(){
		
		return start()
			.withEmployee()
			.withCurrentSalaryGrade()
			.withPayingOff();
	
	}
	public static EmployeeSalarySheetTokens withoutListsTokens(){
		
		return start()
			.withEmployee()
			.withCurrentSalaryGrade()
			.withPayingOff();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public EmployeeSalarySheetTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String EMPLOYEE = "employee";
	public String getEmployee(){
		return EMPLOYEE;
	}
	public EmployeeSalarySheetTokens withEmployee(){		
		addSimpleOptions(EMPLOYEE);
		return this;
	}
	
	
	protected static final String CURRENTSALARYGRADE = "currentSalaryGrade";
	public String getCurrentSalaryGrade(){
		return CURRENTSALARYGRADE;
	}
	public EmployeeSalarySheetTokens withCurrentSalaryGrade(){		
		addSimpleOptions(CURRENTSALARYGRADE);
		return this;
	}
	
	
	protected static final String PAYINGOFF = "payingOff";
	public String getPayingOff(){
		return PAYINGOFF;
	}
	public EmployeeSalarySheetTokens withPayingOff(){		
		addSimpleOptions(PAYINGOFF);
		return this;
	}
	
	
	
	public  EmployeeSalarySheetTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

