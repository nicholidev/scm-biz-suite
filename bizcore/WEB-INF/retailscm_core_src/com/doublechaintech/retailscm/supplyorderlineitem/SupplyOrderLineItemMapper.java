
package com.doublechaintech.retailscm.supplyorderlineitem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.supplyorder.SupplyOrder;

public class SupplyOrderLineItemMapper extends BaseRowMapper<SupplyOrderLineItem>{
	
	protected SupplyOrderLineItem internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		SupplyOrderLineItem supplyOrderLineItem = getSupplyOrderLineItem();		
		 		
 		setId(supplyOrderLineItem, rs, rowNumber); 		
 		setBizOrder(supplyOrderLineItem, rs, rowNumber); 		
 		setSkuId(supplyOrderLineItem, rs, rowNumber); 		
 		setSkuName(supplyOrderLineItem, rs, rowNumber); 		
 		setAmount(supplyOrderLineItem, rs, rowNumber); 		
 		setQuantity(supplyOrderLineItem, rs, rowNumber); 		
 		setUnitOfMeasurement(supplyOrderLineItem, rs, rowNumber); 		
 		setVersion(supplyOrderLineItem, rs, rowNumber);

		return supplyOrderLineItem;
	}
	
	protected SupplyOrderLineItem getSupplyOrderLineItem(){
		return new SupplyOrderLineItem();
	}		
		
	protected void setId(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(SupplyOrderLineItemTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setId(id);
	}
		 		
 	protected void setBizOrder(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
 		String supplyOrderId = rs.getString(SupplyOrderLineItemTable.COLUMN_BIZ_ORDER);
 		if( supplyOrderId == null){
 			return;
 		}
 		if( supplyOrderId.isEmpty()){
 			return;
 		}
 		SupplyOrder supplyOrder = supplyOrderLineItem.getBizOrder();
 		if( supplyOrder != null ){
 			//if the root object 'supplyOrderLineItem' already have the property, just set the id for it;
 			supplyOrder.setId(supplyOrderId);
 			
 			return;
 		}
 		supplyOrderLineItem.setBizOrder(createEmptyBizOrder(supplyOrderId));
 	}
 	
	protected void setSkuId(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String skuId = rs.getString(SupplyOrderLineItemTable.COLUMN_SKU_ID);
		
		if(skuId == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setSkuId(skuId);
	}
		
	protected void setSkuName(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String skuName = rs.getString(SupplyOrderLineItemTable.COLUMN_SKU_NAME);
		
		if(skuName == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setSkuName(skuName);
	}
		
	protected void setAmount(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal amount = rs.getBigDecimal(SupplyOrderLineItemTable.COLUMN_AMOUNT);
		
		if(amount == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setAmount(amount);
	}
		
	protected void setQuantity(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer quantity = rs.getInt(SupplyOrderLineItemTable.COLUMN_QUANTITY);
		
		if(quantity == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setQuantity(quantity);
	}
		
	protected void setUnitOfMeasurement(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String unitOfMeasurement = rs.getString(SupplyOrderLineItemTable.COLUMN_UNIT_OF_MEASUREMENT);
		
		if(unitOfMeasurement == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setUnitOfMeasurement(unitOfMeasurement);
	}
		
	protected void setVersion(SupplyOrderLineItem supplyOrderLineItem, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(SupplyOrderLineItemTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		supplyOrderLineItem.setVersion(version);
	}
		
		

 	protected SupplyOrder  createEmptyBizOrder(String supplyOrderId){
 		SupplyOrder supplyOrder = new SupplyOrder();
 		supplyOrder.setId(supplyOrderId);
 		supplyOrder.setVersion(Integer.MAX_VALUE);
 		return supplyOrder;
 	}
 	
}


