

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
import styles from './AccountingSubject.dashboard.less'
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


const imageList =(accountingSubject)=>{return [
	 ]}

const internalImageListOf = (accountingSubject) =>defaultImageListOf(accountingSubject,imageList)

const optionList =(accountingSubject)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (accountingSubject) =>defaultSettingListOf(accountingSubject, optionList)
const internalLargeTextOf = (accountingSubject) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (accountingSubject,targetComponent) =>{
	
	
	const {AccountingSubjectService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号">{accountingSubject.id}</Description> 
<Description term="会计科目代码">{accountingSubject.accountingSubjectCode}</Description> 
<Description term="会计科目名称">{accountingSubject.accountingSubjectName}</Description> 
<Description term="会计科目类别代码">{accountingSubject.accountingSubjectClassCode}</Description> 
<Description term="会计科目类别名称">{accountingSubject.accountingSubjectClassName}</Description> 
<Description term="账套">{accountingSubject.accountSet==null?appLocaleName(userContext,"NotAssigned"):`${accountingSubject.accountSet.displayName}(${accountingSubject.accountSet.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"账套","accountSet",AccountingSubjectService.requestCandidateAccountSet,
	      AccountingSubjectService.transferToAnotherAccountSet,"anotherAccountSetId",accountingSubject.accountSet?accountingSubject.accountSet.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(accountingSubject,targetComponent)}
      </DescriptionList>
	)

}


class AccountingSubjectDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'accountingSubject'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, accountingDocumentLineListMetaInfo, accountingDocumentLineCount } = this.props.accountingSubject
    if(!this.props.accountingSubject.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"会计科目",cardsFor: "accountingSubject",
    	cardsSource: this.props.accountingSubject,returnURL,displayName,
  		subItems: [
{name: 'accountingDocumentLineList', displayName:'会计凭证行',type:'accountingDocumentLine',count:accountingDocumentLineCount,addFunction: true, role: 'accountingDocumentLine', metaInfo: accountingDocumentLineListMetaInfo},
    
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
  accountingSubject: state._accountingSubject,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(AccountingSubjectDashboard))

