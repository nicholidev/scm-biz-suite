
import ImagePreview from '../../components/ImagePreview'
import { Link } from 'dva/router'
import moment from 'moment'
import appLocaleName from '../../common/Locale.tool'

import { Icon } from 'antd';

const menuData = {menuName:"员工教育", menuFor: "employeeEducation",
  		subItems: [
  
  		],
}

const renderTextCell=(value, record)=>{
	const userContext = null
	if(!value){
		return '';
	}
	if(value==null){
		return '';
	}
	if(value.length>15){
		return value.substring(0,15)+"...("+value.length+appLocaleName(userContext,"Chars")+")"
	}
	return value
	
}

const renderIdentifier=(value, record, targtObjectType)=>{

	return (<Link to={`/${targtObjectType}/${value}/dashboard`}>{value}</Link>)
	
}

const renderDateCell=(value, record)=>{
	return moment(value).format('YYYY-MM-DD');
}
const renderDateTimeCell=(value, record)=>{
	return moment(value).format('YYYY-MM-DD HH:mm');	
}

const renderImageCell=(value, record, title)=>{
	return (<ImagePreview imageTitle={title} imageLocation={value} />)	
}


const formatMoney=(amount)=>{
	const options={style: 'decimal',minimumFractionDigits: 2,maximumFractionDigits:2}
    const moneyFormat = new Intl.NumberFormat('en-US',options);
	return moneyFormat.format(amount)
	
}

const renderMoneyCell=(value, record)=>{
	const userContext = null
	if(!value){
		return appLocaleName(userContext,"Empty")
	}
	if(value == null){
		return appLocaleName(userContext,"Empty")
	}
	return (`${appLocaleName(userContext,"Currency")}${formatMoney(value)}`)
}

const renderBooleanCell=(value, record)=>{
	const userContext = null

	return  (value? appLocaleName(userContext,"Yes") : appLocaleName(userContext,"No"))

}

const renderReferenceCell=(value, record)=>{
	const userContext = null
	return (value ? <span style={{fontWeight:"bold"}} title={`${value.id} - ${value.displayName}`} >{value.displayName}</span> : appLocaleName(userContext,"NotAssigned")) 

}

const displayColumns = [
  { title: '序号', debugtype: 'string', dataIndex: 'id', width: '20',render: (text, record)=>renderTextCell(text,record) },
  { title: '员工', dataIndex: 'employee', render: (text, record) => renderReferenceCell(text, record)},
  { title: '完成时间', dataIndex: 'completeTime', render: (text, record) =>renderDateCell(text,record) },
  { title: '类型', debugtype: 'string', dataIndex: 'type', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: '备注', debugtype: 'string', dataIndex: 'remark', width: '13',render: (text, record)=>renderTextCell(text,record) },

]

const fieldLabels = {
  id: '序号',
  employee: '员工',
  completeTime: '完成时间',
  type: '类型',
  remark: '备注',

}


const EmployeeEducationBase={menuData,displayColumns,fieldLabels}
export default EmployeeEducationBase



