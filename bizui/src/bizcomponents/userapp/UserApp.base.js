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
import styles from './UserApp.base.less'
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



const menuData = {menuName: window.trans('user_app'), menuFor: "userApp",
  		subItems: [
  {name: 'quickLinkList', displayName: window.mtrans('quick_link','user_app.quick_link_list',false), type:'quickLink',icon:'link',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'listAccessList', displayName: window.mtrans('list_access','user_app.list_access_list',false), type:'listAccess',icon:'list',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'objectAccessList', displayName: window.mtrans('object_access','user_app.object_access_list',false), type:'objectAccess',icon:'accessible-icon',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('user_app'), menuFor: "userApp",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('user_app.id'),
  title: window.trans('user_app.title'),
  secUser: window.trans('user_app.sec_user'),
  appIcon: window.trans('user_app.app_icon'),
  fullAccess: window.trans('user_app.full_access'),
  permission: window.trans('user_app.permission'),
  objectType: window.trans('user_app.object_type'),
  objectId: window.trans('user_app.object_id'),
  location: window.trans('user_app.location'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'userApp') , sorter: true },
  { title: fieldLabels.title, debugtype: 'string', dataIndex: 'title', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.secUser, dataIndex: 'secUser', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.appIcon, debugtype: 'string', dataIndex: 'appIcon', width: '13',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.fullAccess, dataIndex: 'fullAccess', render: (text, record) =>renderBooleanCell(text, record), sorter:true },
  { title: fieldLabels.permission, debugtype: 'string', dataIndex: 'permission', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.objectType, debugtype: 'string', dataIndex: 'objectType', width: '31',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.objectId, debugtype: 'string', dataIndex: 'objectId', width: '14',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.location, debugtype: 'string', dataIndex: 'location', width: '16',render: (text, record)=>renderTextCell(text,record)},

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

const renderReferenceItem=(value, targetComponent)=>{
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
	
	return <Tag color='blue' title={`${value.displayName}()`}>{leftChars(value.displayName)}</Tag>
	
	
	
	
}
const renderItemOfList=(userApp, targetComponent, columCount)=>{
  
  if(!userApp){
  	return null
  }
  if(!userApp.id){
  	return null
  }
  
  
  const displayColumnsCount = columCount || 4
  const userContext = null
  return (
    <Card key={`userApp-${userApp.id}`} style={{marginTop:"10px"}}>
		
	<Col span={4}>
		<Avatar size={90} style={{ backgroundColor: genColor(), verticalAlign: 'middle' }}>
			{leftChars(userApp.displayName)}
		</Avatar>
	</Col>
	<Col span={20}>
	  
	  
	 
	
      <DescriptionList  key={userApp.id} size="small" col={displayColumnsCount} >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{userApp.id}</Description> 
        <Description term={fieldLabels.title} style={{wordBreak: 'break-all'}}>{userApp.title}</Description> 
        <Description term={fieldLabels.secUser}>{renderReferenceItem(userApp.secUser)}</Description>

        <Description term={fieldLabels.appIcon} style={{wordBreak: 'break-all'}}>{userApp.appIcon}</Description> 
        <Description term={fieldLabels.permission} style={{wordBreak: 'break-all'}}>{userApp.permission}</Description> 
        <Description term={fieldLabels.objectType} style={{wordBreak: 'break-all'}}>{userApp.objectType}</Description> 
        <Description term={fieldLabels.objectId} style={{wordBreak: 'break-all'}}>{userApp.objectId}</Description> 
        <Description term={fieldLabels.location} style={{wordBreak: 'break-all'}}>{userApp.location}</Description> 
	
        
      </DescriptionList>
     </Col>
    </Card>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {title, appIcon, permission, objectType, objectId, location, secUserId} = formValuesToPack
	const secUser = {id: secUserId, version: 2^31}
	const data = {title, appIcon, permission, objectType, objectId, location, secUser}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {title, appIcon, permission, objectType, objectId, location, secUser} = objectToUnpack
	const secUserId = secUser ? secUser.id : null
	const data = {title, appIcon, permission, objectType, objectId, location, secUserId}
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
const UserAppBase={menuData,settingMenuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default UserAppBase

