
package com.doublechaintech.retailscm.provincecenteremployee;
import com.doublechaintech.retailscm.CommonTokens;
import java.util.Map;
public class ProvinceCenterEmployeeTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="provinceCenterEmployee";
	
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
	protected ProvinceCenterEmployeeTokens(){
		//ensure not initialized outside the class
	}
	public  static  ProvinceCenterEmployeeTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ProvinceCenterEmployeeTokens tokens = new ProvinceCenterEmployeeTokens(options);
		return tokens;
		
	}
	protected ProvinceCenterEmployeeTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ProvinceCenterEmployeeTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ProvinceCenterEmployeeTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ProvinceCenterEmployeeTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static ProvinceCenterEmployeeTokens start(){
		return new ProvinceCenterEmployeeTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public ProvinceCenterEmployeeTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static ProvinceCenterEmployeeTokens allTokens(){
		
		return start()
			.withDepartment()
			.withProvinceCenter();
	
	}
	public static ProvinceCenterEmployeeTokens withoutListsTokens(){
		
		return start()
			.withDepartment()
			.withProvinceCenter();
	
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
	
	public ProvinceCenterEmployeeTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String DEPARTMENT = "department";
	public String getDepartment(){
		return DEPARTMENT;
	}
	public ProvinceCenterEmployeeTokens withDepartment(){		
		addSimpleOptions(DEPARTMENT);
		return this;
	}
	
	
	protected static final String PROVINCECENTER = "provinceCenter";
	public String getProvinceCenter(){
		return PROVINCECENTER;
	}
	public ProvinceCenterEmployeeTokens withProvinceCenter(){		
		addSimpleOptions(PROVINCECENTER);
		return this;
	}
	
	
	
	public  ProvinceCenterEmployeeTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

