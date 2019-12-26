import React from 'react'
<<<<<<< HEAD
import { Icon,Divider } from 'antd'
=======
import { Icon,Divider, Avata, Card, Col} from 'antd'
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList
<<<<<<< HEAD
=======

>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
<<<<<<< HEAD
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
=======
	defaultRenderAvatarCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
	defaultSearchLocalData,
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
<<<<<<< HEAD
=======
const renderAvatarCell=defaultRenderAvatarCell
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell


<<<<<<< HEAD
const menuData = {menuName:"活动的参与情况", menuFor: "eventAttendance",
=======

const menuData = {menuName: window.trans('event_attendance'), menuFor: "eventAttendance",
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
  		subItems: [
  
  		],
}

<<<<<<< HEAD
=======

const settingMenuData = {menuName: window.trans('event_attendance'), menuFor: "eventAttendance",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('event_attendance.id'),
  name: window.trans('event_attendance.name'),
  potentialCustomer: window.trans('event_attendance.potential_customer'),
  cityEvent: window.trans('event_attendance.city_event'),
  description: window.trans('event_attendance.description'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'eventAttendance') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '13',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.potentialCustomer, dataIndex: 'potentialCustomer', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cityEvent, dataIndex: 'cityEvent', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.description, debugtype: 'string', dataIndex: 'description', width: '13',render: (text, record)=>renderTextCell(text,record)},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(eventAttendance,targetComponent)=>{

  const userContext = null
  return (
    <div key={eventAttendance.id}>
	
      <DescriptionList  key={eventAttendance.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{eventAttendance.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{eventAttendance.name}</Description> 
        <Description term={fieldLabels.potentialCustomer}><div>{eventAttendance.potentialCustomer==null?appLocaleName(userContext,"NotAssigned"):`${eventAttendance.potentialCustomer.displayName}(${eventAttendance.potentialCustomer.id})`}
        </div></Description>
        <Description term={fieldLabels.cityEvent}><div>{eventAttendance.cityEvent==null?appLocaleName(userContext,"NotAssigned"):`${eventAttendance.cityEvent.displayName}(${eventAttendance.cityEvent.id})`}
        </div></Description>
        <Description term={fieldLabels.description} style={{wordBreak: 'break-all'}}>{eventAttendance.description}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe

const settingMenuData = {menuName:"活动的参与情况", menuFor: "eventAttendance",
  		subItems: [
  
  		],
}
<<<<<<< HEAD

const fieldLabels = {
  id: '序号',
  name: '名称',
  potentialCustomer: '潜在的客户',
  cityEvent: '城市活动',
  description: '描述',

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'eventAttendance') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '13',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.potentialCustomer, dataIndex: 'potentialCustomer', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cityEvent, dataIndex: 'cityEvent', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.description, debugtype: 'string', dataIndex: 'description', width: '13',render: (text, record)=>renderTextCell(text,record)},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(eventAttendance,targetComponent)=>{

  const userContext = null
  return (
    <div key={eventAttendance.id}>
	
      <DescriptionList  key={eventAttendance.id} size="small" col="4">
        <Description term="序号">{eventAttendance.id}</Description> 
        <Description term="名称">{eventAttendance.name}</Description> 
        <Description term="潜在的客户"><div>{eventAttendance.potentialCustomer==null?appLocaleName(userContext,"NotAssigned"):`${eventAttendance.potentialCustomer.displayName}(${eventAttendance.potentialCustomer.id})`}
        </div></Description>
        <Description term="城市活动"><div>{eventAttendance.cityEvent==null?appLocaleName(userContext,"NotAssigned"):`${eventAttendance.cityEvent.displayName}(${eventAttendance.cityEvent.id})`}
        </div></Description>
        <Description term="描述">{eventAttendance.description}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, description, potentialCustomerId, cityEventId} = formValuesToPack
	const potentialCustomer = {id: potentialCustomerId, version: 2^31}
	const cityEvent = {id: cityEventId, version: 2^31}
	const data = {name, description, potentialCustomer, cityEvent}
	return data
}
=======
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, description, potentialCustomerId, cityEventId} = formValuesToPack
	const potentialCustomer = {id: potentialCustomerId, version: 2^31}
	const cityEvent = {id: cityEventId, version: 2^31}
	const data = {name, description, potentialCustomer, cityEvent}
	return data
}
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, description, potentialCustomer, cityEvent} = objectToUnpack
	const potentialCustomerId = potentialCustomer ? potentialCustomer.id : null
	const cityEventId = cityEvent ? cityEvent.id : null
	const data = {name, description, potentialCustomerId, cityEventId}
	return data
}
<<<<<<< HEAD

const EventAttendanceBase={menuData,displayColumns,fieldLabels,renderItemOfList,packFormValuesToObject,unpackObjectToFormValues}
=======
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
const EventAttendanceBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
export default EventAttendanceBase



