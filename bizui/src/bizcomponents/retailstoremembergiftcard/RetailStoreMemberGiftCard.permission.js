

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './RetailStoreMemberGiftCard.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

const internalRenderTitle = (cardsData,targetComponent) =>{
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}
const internalSummaryOf = (retailStoreMemberGiftCard,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号">{retailStoreMemberGiftCard.id}</Description> 
<Description term="名称">{retailStoreMemberGiftCard.name}</Description> 
<Description term="数">{retailStoreMemberGiftCard.number}</Description> 
<Description term="保持">{retailStoreMemberGiftCard.remain}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = retailStoreMemberGiftCard => {
  const {RetailStoreMemberGiftCardBase} = GlobalComponents
  return <PermissionSetting targetObject={retailStoreMemberGiftCard}  targetObjectMeta={RetailStoreMemberGiftCardBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class RetailStoreMemberGiftCardPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  retailStoreMemberGiftCard = this.props.retailStoreMemberGiftCard
    const { id,displayName, retailStoreMemberGiftCardConsumeRecordCount } = retailStoreMemberGiftCard
    const  returnURL = `/retailStoreMemberGiftCard/${id}/dashboard`
    const cardsData = {cardsName:"零售店会员礼品卡",cardsFor: "retailStoreMemberGiftCard",cardsSource: retailStoreMemberGiftCard,displayName,returnURL,
  		subItems: [
    
      	],
  	};
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const summaryOf = this.props.summaryOf || internalSummaryOf
   
    return (

      <PageHeaderLayout
        title={internalRenderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
      {renderPermissionSetting(cardsData.cardsSource)}
      
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  retailStoreMemberGiftCard: state._retailStoreMemberGiftCard,
}))(Form.create()(RetailStoreMemberGiftCardPermission))

