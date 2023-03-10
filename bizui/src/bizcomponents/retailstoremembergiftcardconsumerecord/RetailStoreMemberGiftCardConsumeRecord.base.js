import React from 'react'
import { Icon,Divider, Avatar, Card, Col, Row, Tag, Button,Table} from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList
import styles from './RetailStoreMemberGiftCardConsumeRecord.base.less'
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
	defaultRenderNumberCell,
	defaultFormatNumber,
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
const renderNumberCell=defaultRenderNumberCell
const formatNumber = defaultFormatNumber

const renderImageListCell=(imageList, record)=>{
	const userContext = null;
	if(!imageList){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(imageList.length === 0){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}

	return (<span>{
		imageList.map(item=>(<img width="40px" key={item.id} title={item.title} src={item.imageUrl}/>))
		}</span>)
}




const menuData = {menuName: window.trans('retail_store_member_gift_card_consume_record'), menuFor: "retailStoreMemberGiftCardConsumeRecord",  internalName: "retail_store_member_gift_card_consume_record",
  		subItems: [

  		],
}


const settingMenuData = {menuName: window.trans('retail_store_member_gift_card_consume_record'), menuFor: "retailStoreMemberGiftCardConsumeRecord",  internalName: "retail_store_member_gift_card_consume_record",
  		subItems: [

  		],
}


const mergedSubItems=()=>{

    const result = []
    menuData.subItems.forEach(item=>{
        result.push({...item, for: "menu"})
    })
    settingMenuData.subItems.forEach(item=>{
        result.push({...item, for: "setting"})
    })
    return result
}
const universalMenuData = {...menuData, subItems: mergedSubItems()}



const fieldLabels = {
  id: window.trans('retail_store_member_gift_card_consume_record.id'),
  occureTime: window.trans('retail_store_member_gift_card_consume_record.occure_time'),
  owner: window.trans('retail_store_member_gift_card_consume_record.owner'),
  bizOrder: window.trans('retail_store_member_gift_card_consume_record.biz_order'),
  number: window.trans('retail_store_member_gift_card_consume_record.number'),
  amount: window.trans('retail_store_member_gift_card_consume_record.amount'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '6', render: (text, record)=>renderTextCell(text,record,'retailStoreMemberGiftCardConsumeRecord') , sorter: true },
  { title: fieldLabels.occureTime, dataIndex: 'occureTime', render: (text, record) =>renderDateCell(text,record), sorter: true },
  { title: fieldLabels.owner, dataIndex: 'owner', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.bizOrder, dataIndex: 'bizOrder', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.number, debugtype: 'string', dataIndex: 'number', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.amount, dataIndex: 'amount', className:'money', render: (text, record) => renderMoneyCell(text, record), sorter: true  },

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(universalMenuData,targetObject,searchTerm)
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


const renderImageList=(imageList,label, targetComponent)=>{
	const userContext = null
	if(!imageList){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	if(imageList.length === 0){
		return <Tag color='red'>{appLocaleName(userContext,"NotAssigned")}</Tag>
	}
	// return JSON.stringify(imageList)
/*
	the data looks like this
	{"id":"1601","title":"cover_images01",
	"imageUrl":"https://demo.doublechaintech.com/demodata/imageManager/genImage/cover_images010016/400/200/grey/"},
	{"id":"1602","title":"cover_images02",
	"imageUrl":"https://demo.doublechaintech.com/demodata/imageManager/genImage/cover_images020016/400/200/grey/"}
*/
	return (<span>{
		imageList.map(item=>(<img width="40px" key={item.id} title={item.title} src={item.imageUrl}/>))
		}</span>)

}


const renderActionList=(retailStoreMemberGiftCardConsumeRecord, targetObject, columCount, listName)=>{

	if(!retailStoreMemberGiftCardConsumeRecord){
		return null
	}
	if(!retailStoreMemberGiftCardConsumeRecord.actionList){
		return null
	}
	if(retailStoreMemberGiftCardConsumeRecord.actionList.length === 0){
		return null
	}
	return (
		<div className={styles.overlay}>

			<div className={styles.overlayContent}>
			{retailStoreMemberGiftCardConsumeRecord.actionList.map(action=>(<Link key={action.id} to={{pathname: action.actionPath.substring(1), state: {ownerId:targetObject.id,action,selectedRows:[retailStoreMemberGiftCardConsumeRecord]}}} >
				<span className={styles.overlayText}>{action.actionName}</span>
				</Link> ))}
			</div>

		</div>
		)

}

const renderItemOfList=(retailStoreMemberGiftCardConsumeRecord, targetObject, columCount, listName)=>{

  if(!retailStoreMemberGiftCardConsumeRecord){
  	return null
  }
  if(!retailStoreMemberGiftCardConsumeRecord.id){
  	return null
  }


  const displayColumnsCount = columCount || 4
  const userContext = null
  return (
     <Row key={`${listName}-${retailStoreMemberGiftCardConsumeRecord.id}`} className={styles.itemDesc}>

	<Col span={4}>
		<Avatar size={90} className={styles.avarta} style={{ backgroundColor: genColor()}}>
			{leftChars(retailStoreMemberGiftCardConsumeRecord.displayName)}
		</Avatar>
	</Col>
	<Col span={20}>
	  



      <DescriptionList  key={retailStoreMemberGiftCardConsumeRecord.id} size="small" col={displayColumnsCount} >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{retailStoreMemberGiftCardConsumeRecord.id}</Description> 
        <Description term={fieldLabels.occureTime}><div>{ moment(retailStoreMemberGiftCardConsumeRecord.occureTime).format('YYYY-MM-DD')}</div></Description> 
        <Description term={fieldLabels.owner}>{renderReferenceItem(retailStoreMemberGiftCardConsumeRecord.owner)}</Description>

        <Description term={fieldLabels.bizOrder}>{renderReferenceItem(retailStoreMemberGiftCardConsumeRecord.bizOrder)}</Description>

        <Description term={fieldLabels.number} style={{wordBreak: 'break-all'}}>{retailStoreMemberGiftCardConsumeRecord.number}</Description> 
        <Description term={fieldLabels.amount}><div style={{"color":"red"}}>{formatNumber(retailStoreMemberGiftCardConsumeRecord.amount,2)}</div></Description> 


      </DescriptionList>
     </Col>
      {renderActionList(retailStoreMemberGiftCardConsumeRecord,targetObject)}
    </Row>
	)

}

const packFormValuesToObject = ( formValuesToPack )=>{
	const {occureTime, ownerId, bizOrderId, number, amount} = formValuesToPack
	const owner = {id: ownerId, version: 2^31}
	const bizOrder = {id: bizOrderId, version: 2^31}
	const data = {occureTime:moment(occureTime).valueOf(), owner, bizOrder, number, amount}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {occureTime, owner, bizOrder, number, amount} = objectToUnpack
	const ownerId = owner ? owner.id : null
	const bizOrderId = bizOrder ? bizOrder.id : null
	const data = {occureTime:moment(occureTime), ownerId, bizOrderId, number, amount}
	return data
}


const stepOf=(targetComponent, title, content, position, index, initValue)=>{
	const isMultipleEvent=false
	return {
		title,
		content,
		position,
		packFunction: packFormValuesToObject,
		unpackFunction: unpackObjectToFormValues,
		index,
		initValue,
		isMultipleEvent,
      }
}



const RetailStoreMemberGiftCardConsumeRecordBase={unpackObjectToFormValues, menuData,settingMenuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default RetailStoreMemberGiftCardConsumeRecordBase

