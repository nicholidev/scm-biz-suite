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
<section><h3>产品(${result.id})</h3><table >
<tr><td class='th'>名称</td><td >${result.name}</td><td class='th'>父类</td><td >${result.parentCategory.displayName}</td></tr><tr><td class='th'>产地</td><td >${result.origin}</td><td class='th'>备注</td><td >${result.remark}</td></tr><tr><td class='th'>品牌</td><td >${result.brand}</td><td class='th'>图片</td><td >${result.picture}</td></tr><tr><td class='th'>更新于</td><td ><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${result.lastUpdateTime}" /></td><td class='th'></td><td ></td></tr></table>
</section>
<section><c:if test="${not empty result.skuList}">
	<c:forEach items="${result.skuList}" var="item">
<section><h3>SKU(${item.id})</h3><table >
<tr><td class='th'>名称</td><td >${item.name}</td><td class='th'>大小</td><td >${item.size}</td></tr><tr><td class='th'>产品</td><td >${item.product.displayName}</td><td class='th'>条码</td><td >${item.barcode}</td></tr><tr><td class='th'>包装类型</td><td >${item.packageType}</td><td class='th'>净含量</td><td >${item.netContent}</td></tr><tr><td class='th'>价格</td><td >${item.price}</td><td class='th'>图片</td><td >${item.picture}</td></tr></table>
</section>
</c:forEach></c:if></section>
