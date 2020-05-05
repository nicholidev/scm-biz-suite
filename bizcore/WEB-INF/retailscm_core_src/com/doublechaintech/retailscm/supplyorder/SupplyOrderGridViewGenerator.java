
package com.doublechaintech.retailscm.supplyorder;
import com.doublechaintech.retailscm.AccessKey;

import com.doublechaintech.retailscm.BaseGridViewGenerator;

public class SupplyOrderGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for SupplyOrder", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(SupplyOrder.SUPPLY_ORDER_LINE_ITEM_LIST.equals(listName)){
			return new String[]{"id","biz_order","sku_id","sku_name","amount","quantity","unit_of_measurement","version"};
		}
		if(SupplyOrder.SUPPLY_ORDER_SHIPPING_GROUP_LIST.equals(listName)){
			return new String[]{"id","name","biz_order","amount","version"};
		}
		if(SupplyOrder.SUPPLY_ORDER_PAYMENT_GROUP_LIST.equals(listName)){
			return new String[]{"id","name","biz_order","card_number","version"};
		}
		if(SupplyOrder.GOODS_LIST.equals(listName)){
			return new String[]{"id","name","rfid","uom","max_package","expire_time","sku","receiving_space","goods_allocation","smart_pallet","shipping_space","transport_task","retail_store","biz_order","retail_store_order","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(SupplyOrder.SUPPLY_ORDER_LINE_ITEM_LIST.equals(listName)){
			return "supply_order_line_item";
		}
		if(SupplyOrder.SUPPLY_ORDER_SHIPPING_GROUP_LIST.equals(listName)){
			return "supply_order_shipping_group";
		}
		if(SupplyOrder.SUPPLY_ORDER_PAYMENT_GROUP_LIST.equals(listName)){
			return "supply_order_payment_group";
		}
		if(SupplyOrder.GOODS_LIST.equals(listName)){
			return "goods";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





