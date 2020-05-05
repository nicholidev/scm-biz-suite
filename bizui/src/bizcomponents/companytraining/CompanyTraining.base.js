import React from 'react'
import { Icon,Divider, Avatar, Card, Col, Tag} from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList
import styles from './CompanyTraining.base.less'
const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
	defaultRenderAvatarCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
	defaultSearchLocalData,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderAvatarCell=defaultRenderAvatarCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell



const menuData = {menuName: window.trans('company_training'), menuFor: "companyTraining",
  		subItems: [
  {name: 'employeeCompanyTrainingList', displayName: window.mtrans('employee_company_training','company_training.employee_company_training_list',false), type:'employeeCompanyTraining',icon:'om',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('company_training'), menuFor: "companyTraining",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('company_training.id'),
  title: window.trans('company_training.title'),
  company: window.trans('company_training.company'),
  instructor: window.trans('company_training.instructor'),
  trainingCourseType: window.trans('company_training.training_course_type'),
  timeStart: window.trans('company_training.time_start'),
  durationHours: window.trans('company_training.duration_hours'),
  lastUpdateTime: window.trans('company_training.last_update_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '6', render: (text, record)=>renderTextCell(text,record,'companyTraining') , sorter: true },
  { title: fieldLabels.title, debugtype: 'string', dataIndex: 'title', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.company, dataIndex: 'company', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.instructor, dataIndex: 'instructor', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.trainingCourseType, dataIndex: 'trainingCourseType', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.timeStart, dataIndex: 'timeStart', render: (text, record) =>renderDateCell(text,record), sorter: true },
  { title: fieldLabels.durationHours, dataIndex: 'durationHours', className:'money', render: (text, record) => renderTextCell(text, record), sorter: true  },
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)
const colorList = ['#f56a00', '#7265e6', '#ffbf00', '#00a2ae'];
let counter = 0;
const genColor=()=>{
	counter++;
	return colorList[counter%colorList.length];
}
const followColor=()=>{
	return 'green';
	// return colorList[counter%colorList.length];
}
const leftChars=(value, left)=>{
	const chars = left || 4
	if(!value){
		return "N/A"
	}
	return value.substring(0,chars);
}

const renderTextItem=(value, label, targetComponent)=>{
	const userContext = null
	if(!value){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(!value.id){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(!value.displayName){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	
	return <Tag color='blue' title={`${value.displayName}(${value.id})`}>{leftChars(value.displayName)}</Tag>
}
const renderImageItem=(value,label, targetComponent)=>{
	const userContext = null
	if(!value){
		return appLocaleName(userContext,"NotAssigned")
	}
	
	return <ImagePreview title={label} imageLocation={value}/>
}

const renderDateItem=(value, label,targetComponent)=>{
	const userContext = null
	if(!value){
		return appLocaleName(userContext,"NotAssigned")
	}
	return moment(value).format('YYYY-MM-DD');
}

const renderDateTimeItem=(value,label, targetComponent)=>{
	const userContext = window.userContext
	if(!value){
		return appLocaleName(userContext,"NotAssigned")
	}
	return  moment(value).format('YYYY-MM-DD HH:mm')
}


const renderReferenceItem=(value,label, targetComponent)=>{
	const userContext = null
	if(!value){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(!value.id){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(!value.displayName){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	
	return <Tag color='blue' title={`${value.displayName}(${value.id})`}>{leftChars(value.displayName)}</Tag>
}

const renderItemOfList=(companyTraining, targetComponent, columCount, listName)=>{
  
  if(!companyTraining){
  	return null
  }
  if(!companyTraining.id){
  	return null
  }
  
  
  const displayColumnsCount = columCount || 4
  const userContext = null
  return (
    <Card key={`${listName}-${companyTraining.id}`} style={{marginTop:"10px"}}>
		
	<Col span={4}>
		<Avatar size={90} style={{ backgroundColor: genColor(), verticalAlign: 'middle' }}>
			{leftChars(companyTraining.displayName)}
		</Avatar>
	</Col>
	<Col span={20}>
	  
	  
	 
	
      <DescriptionList  key={companyTraining.id} size="small" col={displayColumnsCount} >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{companyTraining.id}</Description> 
        <Description term={fieldLabels.title} style={{wordBreak: 'break-all'}}>{companyTraining.title}</Description> 
        <Description term={fieldLabels.instructor}>{renderReferenceItem(companyTraining.instructor)}</Description>

        <Description term={fieldLabels.trainingCourseType}>{renderReferenceItem(companyTraining.trainingCourseType)}</Description>

        <Description term={fieldLabels.timeStart}><div>{ moment(companyTraining.timeStart).format('YYYY-MM-DD')}</div></Description> 
        <Description term={fieldLabels.durationHours}><div style={{"color":"red"}}>{companyTraining.durationHours}</div></Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(companyTraining.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
     </Col>
    </Card>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {title, timeStart, durationHours, companyId, instructorId, trainingCourseTypeId} = formValuesToPack
	const company = {id: companyId, version: 2^31}
	const instructor = {id: instructorId, version: 2^31}
	const trainingCourseType = {id: trainingCourseTypeId, version: 2^31}
	const data = {title, timeStart:moment(timeStart).valueOf(), durationHours, company, instructor, trainingCourseType}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {title, timeStart, durationHours, company, instructor, trainingCourseType} = objectToUnpack
	const companyId = company ? company.id : null
	const instructorId = instructor ? instructor.id : null
	const trainingCourseTypeId = trainingCourseType ? trainingCourseType.id : null
	const data = {title, timeStart:moment(timeStart), durationHours, companyId, instructorId, trainingCourseTypeId}
	return data
}
const stepOf=(targetComponent, title, content, position, index)=>{
	return {
		title,
		content,
		position,
		packFunction: packFormValuesToObject,
		unpackFunction: unpackObjectToFormValues,
		index,
      }
}
const CompanyTrainingBase={menuData,settingMenuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default CompanyTrainingBase

