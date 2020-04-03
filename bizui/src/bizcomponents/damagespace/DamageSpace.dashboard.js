

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Button, Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './DamageSpace.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,defaultRenderAnalytics,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers,
  defaultQuickFunctions, defaultRenderSubjectList,
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(damageSpace)=>{return [
	 ]}

const internalImageListOf = (damageSpace) =>defaultImageListOf(damageSpace,imageList)

const optionList =(damageSpace)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (damageSpace) =>defaultSettingListOf(damageSpace, optionList)
const internalLargeTextOf = (damageSpace) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const renderSettingDropDown = (cardsData,targetComponent)=>{

  return (<div style={{float: 'right'}} >
        <Dropdown overlay={renderSettingMenu(cardsData,targetComponent)} placement="bottomRight" >
       
        <Button>
        <Icon type="setting" theme="filled" twoToneColor="#00b" style={{color:'#3333b0'}}/> 设置  <Icon type="down"/>
      </Button>
      </Dropdown></div>)

}

const renderSettingMenuItem = (item,cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu.Item key={item.name}>
      <Link to={`/damageSpace/${targetComponent.props.damageSpace.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/damageSpace/${targetComponent.props.damageSpace.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (damageSpace,targetComponent) =>{
	
	
	const {DamageSpaceService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号" style={{wordBreak: 'break-all'}}>{damageSpace.id}</Description> 
<Description term="位置" style={{wordBreak: 'break-all'}}>{damageSpace.location}</Description> 
<Description term="联系电话" style={{wordBreak: 'break-all'}}>{damageSpace.contactNumber}</Description> 
<Description term="总面积" style={{wordBreak: 'break-all'}}>{damageSpace.totalArea}</Description> 
<Description term="纬度" style={{wordBreak: 'break-all'}}>{damageSpace.latitude}</Description> 
<Description term="经度" style={{wordBreak: 'break-all'}}>{damageSpace.longitude}</Description> 
<Description term="仓库">{damageSpace.warehouse==null?appLocaleName(userContext,"NotAssigned"):`${damageSpace.warehouse.displayName}(${damageSpace.warehouse.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"仓库","warehouse",DamageSpaceService.requestCandidateWarehouse,
	      DamageSpaceService.transferToAnotherWarehouse,"anotherWarehouseId",damageSpace.warehouse?damageSpace.warehouse.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="最后更新时间">{ moment(damageSpace.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(damageSpace,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class DamageSpaceDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'damageSpace'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, goodsShelfListMetaInfo, goodsShelfCount } = this.props.damageSpace
    if(!this.props.damageSpace.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:window.trans('damage_space'),cardsFor: "damageSpace",
    	cardsSource: this.props.damageSpace,returnURL,displayName,
  		subItems: [
{name: 'goodsShelfList', displayName: window.mtrans('goods_shelf','damage_space.goods_shelf_list',false) ,viewGroup:'__no_group', type:'goodsShelf',count:goodsShelfCount,addFunction: true, role: 'goodsShelf', metaInfo: goodsShelfListMetaInfo, renderItem: GlobalComponents.GoodsShelfBase.renderItemOfList},
    
      	],
   		subSettingItems: [
    
      	],     	
      	
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList
    
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
       
        {renderExtraHeader(cardsData.cardsSource)}
        
        {quickFunctions(cardsData)} 
        {imageListOf(cardsData.cardsSource)}  
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {renderSubjectList(cardsData)}       
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
  		
      </PageHeaderLayout>
    
    )
  }
}

export default connect(state => ({
  damageSpace: state._damageSpace,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(DamageSpaceDashboard))

