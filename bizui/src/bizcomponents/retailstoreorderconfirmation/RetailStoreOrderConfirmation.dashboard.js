

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
import styles from './RetailStoreOrderConfirmation.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'


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


const imageList =(retailStoreOrderConfirmation)=>{return [
	 ]}

const internalImageListOf = (retailStoreOrderConfirmation) =>defaultImageListOf(retailStoreOrderConfirmation,imageList)

const optionList =(retailStoreOrderConfirmation)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (retailStoreOrderConfirmation) =>defaultSettingListOf(retailStoreOrderConfirmation, optionList)
const internalLargeTextOf = (retailStoreOrderConfirmation) =>{

	return null
	

}







const internalRenderExtraHeader = defaultRenderExtraHeader




const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf

const internalSummaryOf = (retailStoreOrderConfirmation,targetComponent) =>{
	
	
	const {RetailStoreOrderConfirmationService} = GlobalComponents
	
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号">{retailStoreOrderConfirmation.id}</Description> 
<Description term="谁">{retailStoreOrderConfirmation.who}</Description> 
<Description term="确认时间">{ moment(retailStoreOrderConfirmation.confirmTime).format('YYYY-MM-DD')}</Description> 
	
        {buildTransferModal(retailStoreOrderConfirmation,targetComponent)}
      </DescriptionList>
	)

}


class RetailStoreOrderConfirmationDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"城市",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'retailStoreOrderConfirmation'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, retailStoreOrderListMetaInfo, retailStoreOrderCount } = this.props.retailStoreOrderConfirmation
    if(!this.props.retailStoreOrderConfirmation.class){
      return null
    }
    const cardsData = {cardsName:"生超订单确认",cardsFor: "retailStoreOrderConfirmation",cardsSource: this.props.retailStoreOrderConfirmation,
  		subItems: [
{name: 'retailStoreOrderList', displayName:'生超的订单',type:'retailStoreOrder',count:retailStoreOrderCount,addFunction: true, role: 'retailStoreOrder', metaInfo: retailStoreOrderListMetaInfo},
    
      	],
  	};
    //下面各个渲染方法都可以定制，只要在每个模型的里面的_features="custom"就可以得到定制的例子
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    return (

      <PageHeaderLayout
        title={`${cardsData.cardsName}: ${displayName}`}
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
  retailStoreOrderConfirmation: state._retailStoreOrderConfirmation,
}))(Form.create()(RetailStoreOrderConfirmationDashboard))

