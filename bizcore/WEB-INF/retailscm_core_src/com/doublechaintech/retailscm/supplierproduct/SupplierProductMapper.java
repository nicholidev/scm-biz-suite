
package com.doublechaintech.retailscm.supplierproduct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.goodssupplier.GoodsSupplier;

public class SupplierProductMapper extends BaseRowMapper<SupplierProduct>{
	
	protected SupplierProduct internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		SupplierProduct supplierProduct = getSupplierProduct();		
		 		
 		setId(supplierProduct, rs, rowNumber); 		
 		setProductName(supplierProduct, rs, rowNumber); 		
 		setProductDescription(supplierProduct, rs, rowNumber); 		
 		setProductUnit(supplierProduct, rs, rowNumber); 		
 		setSupplier(supplierProduct, rs, rowNumber); 		
 		setVersion(supplierProduct, rs, rowNumber);

		return supplierProduct;
	}
	
	protected SupplierProduct getSupplierProduct(){
		return new SupplierProduct();
	}		
		
	protected void setId(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(SupplierProductTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplierProduct.setId(id);
	}
		
	protected void setProductName(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String productName = rs.getString(SupplierProductTable.COLUMN_PRODUCT_NAME);
		
		if(productName == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplierProduct.setProductName(productName);
	}
		
	protected void setProductDescription(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String productDescription = rs.getString(SupplierProductTable.COLUMN_PRODUCT_DESCRIPTION);
		
		if(productDescription == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplierProduct.setProductDescription(productDescription);
	}
		
	protected void setProductUnit(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String productUnit = rs.getString(SupplierProductTable.COLUMN_PRODUCT_UNIT);
		
		if(productUnit == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplierProduct.setProductUnit(productUnit);
	}
		 		
 	protected void setSupplier(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
 		String goodsSupplierId = rs.getString(SupplierProductTable.COLUMN_SUPPLIER);
 		if( goodsSupplierId == null){
 			return;
 		}
 		if( goodsSupplierId.isEmpty()){
 			return;
 		}
 		GoodsSupplier goodsSupplier = supplierProduct.getSupplier();
 		if( goodsSupplier != null ){
 			//if the root object 'supplierProduct' already have the property, just set the id for it;
 			goodsSupplier.setId(goodsSupplierId);
 			
 			return;
 		}
 		supplierProduct.setSupplier(createEmptySupplier(goodsSupplierId));
 	}
 	
	protected void setVersion(SupplierProduct supplierProduct, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(SupplierProductTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplierProduct.setVersion(version);
	}
		
		

 	protected GoodsSupplier  createEmptySupplier(String goodsSupplierId){
 		GoodsSupplier goodsSupplier = new GoodsSupplier();
 		goodsSupplier.setId(goodsSupplierId);
 		goodsSupplier.setVersion(Integer.MAX_VALUE);
 		return goodsSupplier;
 	}
 	
}


