
package com.doublechaintech.retailscm.product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.levelthreecategory.LevelThreeCategory;

public class ProductMapper extends BaseRowMapper<Product>{
	
	protected Product internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Product product = getProduct();		
		 		
 		setId(product, rs, rowNumber); 		
 		setName(product, rs, rowNumber); 		
 		setParentCategory(product, rs, rowNumber); 		
 		setOrigin(product, rs, rowNumber); 		
 		setRemark(product, rs, rowNumber); 		
 		setBrand(product, rs, rowNumber); 		
 		setPicture(product, rs, rowNumber); 		
 		setLastUpdateTime(product, rs, rowNumber); 		
 		setVersion(product, rs, rowNumber);

		return product;
	}
	
	protected Product getProduct(){
		return new Product();
	}		
		
	protected void setId(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ProductTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setId(id);
	}
		
	protected void setName(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(ProductTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setName(name);
	}
		 		
 	protected void setParentCategory(Product product, ResultSet rs, int rowNumber) throws SQLException{
 		String levelThreeCategoryId = rs.getString(ProductTable.COLUMN_PARENT_CATEGORY);
 		if( levelThreeCategoryId == null){
 			return;
 		}
 		if( levelThreeCategoryId.isEmpty()){
 			return;
 		}
 		LevelThreeCategory levelThreeCategory = product.getParentCategory();
 		if( levelThreeCategory != null ){
 			//if the root object 'product' already have the property, just set the id for it;
 			levelThreeCategory.setId(levelThreeCategoryId);
 			
 			return;
 		}
 		product.setParentCategory(createEmptyParentCategory(levelThreeCategoryId));
 	}
 	
	protected void setOrigin(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String origin = rs.getString(ProductTable.COLUMN_ORIGIN);
		
		if(origin == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setOrigin(origin);
	}
		
	protected void setRemark(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String remark = rs.getString(ProductTable.COLUMN_REMARK);
		
		if(remark == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setRemark(remark);
	}
		
	protected void setBrand(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String brand = rs.getString(ProductTable.COLUMN_BRAND);
		
		if(brand == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setBrand(brand);
	}
		
	protected void setPicture(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String picture = rs.getString(ProductTable.COLUMN_PICTURE);
		
		if(picture == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setPicture(picture);
	}
		
	protected void setLastUpdateTime(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date lastUpdateTime = rs.getTimestamp(ProductTable.COLUMN_LAST_UPDATE_TIME);
		
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		
	protected void setVersion(Product product, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ProductTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		product.setVersion(version);
	}
		
		

 	protected LevelThreeCategory  createEmptyParentCategory(String levelThreeCategoryId){
 		LevelThreeCategory levelThreeCategory = new LevelThreeCategory();
 		levelThreeCategory.setId(levelThreeCategoryId);
 		levelThreeCategory.setVersion(Integer.MAX_VALUE);
 		return levelThreeCategory;
 	}
 	
}


