<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>



<style>
	
	table{
	  border: 1px solid black;
	  border-collapse: collapse;
	  width: 100%;
	  font-size: 12px;
	  padding: 5px;
	}
	th {
	  border: 1px solid black;
	  border-collapse: collapse;
	  font-size: 12px;
	  padding: 5px;
	  text-align: center;
	  background-color: lightgray;
	}
	td {
	  border: 1px solid black;
	  border-collapse: collapse;
	  
	  font-size: 10px;
	  padding-left: 5px;
	}
	
	/**/
	.table ,.tbody {
		all:unset;
		border: 1px solid black;
		
		width:100%;
	}

	.tr {
		all:unset;
		
		width: 25%;
	}

	.td {
		border: 1px solid red;
		border:initial;
		all:unset;
		text-align: justify;
		
		outline: none;
		font-size: 10px;
	  	padding: 5px;
		background-color: darkblue;
		word-break: break-all;
		
	}
	.th {
		width:"80px";
		background-color: lightgray;

		font-size: 10px;
		font-family: "Gill Sans Extrabold", sans-serif;
		padding: 5px;
		text-align: left;
		word-break: break-all;		
	}
	
</style>
<section><h3>收货区(${result.id})</h3><table >
<tr><td class='th'>位置</td><td >${result.location}</td><td class='th'>联系电话</td><td >${result.contactNumber}</td></tr><tr><td class='th'>描述</td><td >${result.description}</td><td class='th'>总面积</td><td >${result.totalArea}</td></tr><tr><td class='th'>仓库</td><td >${result.warehouse.displayName}</td><td class='th'>纬度</td><td >${result.latitude}</td></tr><tr><td class='th'>经度</td><td >${result.longitude}</td><td class='th'>更新于</td><td ><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${result.lastUpdateTime}" /></td></tr></table>
</section>
<section><c:if test="${not empty result.goodsList}">
	<c:forEach items="${result.goodsList}" var="item">
<section><h3>货物(${item.id})</h3><table >
<tr><td class='th'>名称</td><td >${item.name}</td><td class='th'>RFID</td><td >${item.rfid}</td></tr><tr><td class='th'>计量单位</td><td >${item.uom}</td><td class='th'>最大包装</td><td >${item.maxPackage}</td></tr><tr><td class='th'>到期时间</td><td ><fmt:formatDate pattern="yyyy-MM-dd" value="${item.expireTime}" /></td><td class='th'>SKU</td><td >${item.sku.displayName}</td></tr><tr><td class='th'>收货区</td><td >${item.receivingSpace.displayName}</td><td class='th'>货位</td><td >${item.goodsAllocation.displayName}</td></tr><tr><td class='th'>智能托盘</td><td >${item.smartPallet.displayName}</td><td class='th'>发货区</td><td >${item.shippingSpace.displayName}</td></tr><tr><td class='th'>运输任务</td><td >${item.transportTask.displayName}</td><td class='th'>双链小超</td><td >${item.retailStore.displayName}</td></tr><tr><td class='th'>订单</td><td >${item.bizOrder.displayName}</td><td class='th'>生超的订单</td><td >${item.retailStoreOrder.displayName}</td></tr></table>
</section>
</c:forEach></c:if></section>
