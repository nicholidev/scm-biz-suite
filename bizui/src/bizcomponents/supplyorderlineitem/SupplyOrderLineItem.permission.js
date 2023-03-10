

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './SupplyOrderLineItem.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

const internalRenderTitle = (cardsData,targetComponent) =>{
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}
const internalSummaryOf = (supplyOrderLineItem,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{supplyOrderLineItem.id}</Description> 
<Description term="产品ID">{supplyOrderLineItem.skuId}</Description> 
<Description term="产品名称">{supplyOrderLineItem.skuName}</Description> 
<Description term="金额">{supplyOrderLineItem.amount}</Description> 
<Description term="数量">{supplyOrderLineItem.quantity}</Description> 
<Description term="测量单位">{supplyOrderLineItem.unitOfMeasurement}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = supplyOrderLineItem => {
  const {SupplyOrderLineItemBase} = GlobalComponents
  return <PermissionSetting targetObject={supplyOrderLineItem}  targetObjectMeta={SupplyOrderLineItemBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class SupplyOrderLineItemPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  supplyOrderLineItem = this.props.supplyOrderLineItem
    const { id,displayName,  } = supplyOrderLineItem
    const  returnURL = `/supplyOrderLineItem/${id}/workbench`
    const cardsData = {cardsName:"供应订单行项目",cardsFor: "supplyOrderLineItem",cardsSource: supplyOrderLineItem,displayName,returnURL,
  		subItems: [
    
      	],
  	};
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const summaryOf = this.props.summaryOf || internalSummaryOf
   
    return (

      <PageHeaderLayout
        title={internalRenderTitle(cardsData,this)}
       
        wrapperClassName={styles.advancedForm}
      >
      
      {renderPermissionSetting(cardsData.cardsSource)}
      
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  supplyOrderLineItem: state._supplyOrderLineItem,
}))(Form.create()(SupplyOrderLineItemPermission))

