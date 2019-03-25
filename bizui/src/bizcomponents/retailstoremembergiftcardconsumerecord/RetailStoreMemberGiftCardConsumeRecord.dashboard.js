

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from 'components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './RetailStoreMemberGiftCardConsumeRecord.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(retailStoreMemberGiftCardConsumeRecord)=>{return [
	 ]}

const internalImageListOf = (retailStoreMemberGiftCardConsumeRecord) =>defaultImageListOf(retailStoreMemberGiftCardConsumeRecord,imageList)

const optionList =(retailStoreMemberGiftCardConsumeRecord)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (retailStoreMemberGiftCardConsumeRecord) =>defaultSettingListOf(retailStoreMemberGiftCardConsumeRecord, optionList)
const internalLargeTextOf = (retailStoreMemberGiftCardConsumeRecord) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (retailStoreMemberGiftCardConsumeRecord,targetComponent) =>{
	
	
	const {RetailStoreMemberGiftCardConsumeRecordService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号">{retailStoreMemberGiftCardConsumeRecord.id}</Description> 
<Description term="发生时间">{ moment(retailStoreMemberGiftCardConsumeRecord.occureTime).format('YYYY-MM-DD')}</Description> 
<Description term="业主">{retailStoreMemberGiftCardConsumeRecord.owner==null?appLocaleName(userContext,"NotAssigned"):`${retailStoreMemberGiftCardConsumeRecord.owner.displayName}(${retailStoreMemberGiftCardConsumeRecord.owner.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"业主","retailStoreMemberGiftCard",RetailStoreMemberGiftCardConsumeRecordService.requestCandidateOwner,
	      RetailStoreMemberGiftCardConsumeRecordService.transferToAnotherOwner,"anotherOwnerId",retailStoreMemberGiftCardConsumeRecord.owner?retailStoreMemberGiftCardConsumeRecord.owner.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="订单">{retailStoreMemberGiftCardConsumeRecord.bizOrder==null?appLocaleName(userContext,"NotAssigned"):`${retailStoreMemberGiftCardConsumeRecord.bizOrder.displayName}(${retailStoreMemberGiftCardConsumeRecord.bizOrder.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"订单","consumerOrder",RetailStoreMemberGiftCardConsumeRecordService.requestCandidateBizOrder,
	      RetailStoreMemberGiftCardConsumeRecordService.transferToAnotherBizOrder,"anotherBizOrderId",retailStoreMemberGiftCardConsumeRecord.bizOrder?retailStoreMemberGiftCardConsumeRecord.bizOrder.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="数">{retailStoreMemberGiftCardConsumeRecord.number}</Description> 
<Description term="金额">{retailStoreMemberGiftCardConsumeRecord.amount}</Description> 
	
        {buildTransferModal(retailStoreMemberGiftCardConsumeRecord,targetComponent)}
      </DescriptionList>
	)

}


class RetailStoreMemberGiftCardConsumeRecordDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'retailStoreMemberGiftCardConsumeRecord'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.retailStoreMemberGiftCardConsumeRecord
    if(!this.props.retailStoreMemberGiftCardConsumeRecord.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"零售商店会员卡消费记录",cardsFor: "retailStoreMemberGiftCardConsumeRecord",
    	cardsSource: this.props.retailStoreMemberGiftCardConsumeRecord,returnURL,displayName,
  		subItems: [
    
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
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
        <div>
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}
        {subListsOf(cardsData)} 
        {largeTextOf(cardsData.cardsSource)}
          
        </div>
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  retailStoreMemberGiftCardConsumeRecord: state._retailStoreMemberGiftCardConsumeRecord,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(RetailStoreMemberGiftCardConsumeRecordDashboard))

