<<<<<<< HEAD

import ImagePreview from '../../components/ImagePreview'
import { Link } from 'dva/router'
import moment from 'moment'


=======
import React from 'react'
import { Icon,Divider } from 'antd'

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
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854


const menuData = {menuName:"二级分类", menuFor: "levelTwoCategory",
  		subItems: [
<<<<<<< HEAD
  {name: 'levelThreeCategoryList', displayName:'三级分类', icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false},
=======
  {name: 'levelThreeCategoryList', displayName:'三级分类', icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854
  
  		],
}

<<<<<<< HEAD
const renderTextCell=(value, record)=>{

	if(!value){
		return '';
	}
	if(value==null){
		return '';
	}
	if(value.length>15){
		return value.substring(0,15)+"...("+value.length+"字)"
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

const renderMoneyCell=(value, record)=>{
	if(!value){
		return '空'
	}
	if(value == null){
		return '空'
	}
	return (`￥${value.toFixed(2)}`)
}

const renderBooleanCell=(value, record)=>{

	return  (value? '是' : '否')

}

const renderReferenceCell=(value, record)=>{

	return (value ? value.displayName : '暂无') 
=======

const settingMenuData = {menuName:"二级分类", menuFor: "levelTwoCategory",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: '序号',
  parentCategory: '父类',
  name: '名称',
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854

}

const displayColumns = [
<<<<<<< HEAD
  { title: '序号', debugtype: 'string', dataIndex: 'id', width: '20', render: (text, record)=>renderTextCell(text,record,'levelTwoCategory') },
  { title: '父类', dataIndex: 'parentCategory', render: (text, record) => renderReferenceCell(text, record)},
  { title: '名称', debugtype: 'string', dataIndex: 'name', width: '8',render: (text, record)=>renderTextCell(text,record) },

]

const fieldLabels = {
  id: '序号',
  parentCategory: '父类',
  name: '名称',

}


const LevelTwoCategoryBase={menuData,displayColumns,fieldLabels,displayColumns}
=======
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'levelTwoCategory') , sorter: true },
  { title: fieldLabels.parentCategory, dataIndex: 'parentCategory', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '8',render: (text, record)=>renderTextCell(text,record)},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(levelTwoCategory,targetComponent)=>{

  const userContext = null
  return (
    <div key={levelTwoCategory.id}>
	
      <DescriptionList  key={levelTwoCategory.id} size="small" col="4">
        <Description term="序号">{levelTwoCategory.id}</Description> 
        <Description term="父类"><div>{levelTwoCategory.parentCategory==null?appLocaleName(userContext,"NotAssigned"):`${levelTwoCategory.parentCategory.displayName}(${levelTwoCategory.parentCategory.id})`}
        </div></Description>
        <Description term="名称">{levelTwoCategory.name}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	



const LevelTwoCategoryBase={menuData,displayColumns,fieldLabels,renderItemOfList}
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854
export default LevelTwoCategoryBase



