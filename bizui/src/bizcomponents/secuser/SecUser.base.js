import React from 'react'
import { Icon,Divider, Avata, Card, Col} from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList

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



const menuData = {menuName: window.trans('sec_user'), menuFor: "secUser",
  		subItems: [
  {name: 'userAppList', displayName: window.mtrans('user_app','sec_user.user_app_list',false), type:'userApp',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'loginHistoryList', displayName: window.mtrans('login_history','sec_user.login_history_list',false), type:'loginHistory',icon:'history',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('sec_user'), menuFor: "secUser",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('sec_user.id'),
  login: window.trans('sec_user.login'),
  mobile: window.trans('sec_user.mobile'),
  email: window.trans('sec_user.email'),
  pwd: window.trans('sec_user.pwd'),
  weixinOpenid: window.trans('sec_user.weixin_openid'),
  weixinAppid: window.trans('sec_user.weixin_appid'),
  accessToken: window.trans('sec_user.access_token'),
  verificationCode: window.trans('sec_user.verification_code'),
  verificationCodeExpire: window.trans('sec_user.verification_code_expire'),
  lastLoginTime: window.trans('sec_user.last_login_time'),
  domain: window.trans('sec_user.domain'),
  blocking: window.trans('sec_user.blocking'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'secUser') , sorter: true },
  { title: fieldLabels.login, debugtype: 'string', dataIndex: 'login', width: '9',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.email, debugtype: 'string_email', dataIndex: 'email', width: '23',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.pwd, debugtype: 'string_password', dataIndex: 'pwd', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.weixinOpenid, debugtype: 'string', dataIndex: 'weixinOpenid', width: '29',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.weixinAppid, debugtype: 'string', dataIndex: 'weixinAppid', width: '23',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.accessToken, debugtype: 'string', dataIndex: 'accessToken', width: '22',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.verificationCode, debugtype: 'int', dataIndex: 'verificationCode', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.verificationCodeExpire, dataIndex: 'verificationCodeExpire', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.lastLoginTime, dataIndex: 'lastLoginTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.domain, dataIndex: 'domain', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.blocking, dataIndex: 'blocking', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(secUser,targetComponent)=>{

  const userContext = null
  return (
    <div key={secUser.id}>
	
      <DescriptionList  key={secUser.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{secUser.id}</Description> 
        <Description term={fieldLabels.login} style={{wordBreak: 'break-all'}}>{secUser.login}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{secUser.mobile}</Description> 
        <Description term={fieldLabels.email} style={{wordBreak: 'break-all'}}>{secUser.email}</Description> 
        <Description term={fieldLabels.pwd} style={{wordBreak: 'break-all'}}>{secUser.pwd}</Description> 
        <Description term={fieldLabels.weixinOpenid} style={{wordBreak: 'break-all'}}>{secUser.weixinOpenid}</Description> 
        <Description term={fieldLabels.weixinAppid} style={{wordBreak: 'break-all'}}>{secUser.weixinAppid}</Description> 
        <Description term={fieldLabels.accessToken} style={{wordBreak: 'break-all'}}>{secUser.accessToken}</Description> 
        <Description term={fieldLabels.verificationCode}><div style={{"color":"red"}}>{secUser.verificationCode}</div></Description> 
        <Description term={fieldLabels.verificationCodeExpire}><div>{ moment(secUser.verificationCodeExpire).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.lastLoginTime}><div>{ moment(secUser.lastLoginTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime, domainId, blockingId} = formValuesToPack
	const domain = {id: domainId, version: 2^31}
	const blocking = {id: blockingId, version: 2^31}
	const data = {login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime, domain, blocking}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime, domain, blocking} = objectToUnpack
	const domainId = domain ? domain.id : null
	const blockingId = blocking ? blocking.id : null
	const data = {login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime, domainId, blockingId}
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
const SecUserBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default SecUserBase



