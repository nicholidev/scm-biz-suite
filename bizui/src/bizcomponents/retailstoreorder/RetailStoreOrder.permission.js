

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './RetailStoreOrder.profile.less'
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
const internalSummaryOf = (retailStoreOrder,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{retailStoreOrder.id}</Description> 
<Description term="头衔">{retailStoreOrder.title}</Description> 
<Description term="总金额">{retailStoreOrder.totalAmount}</Description> 
<Description term="更新于">{ moment(retailStoreOrder.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = retailStoreOrder => {
  const {RetailStoreOrderBase} = GlobalComponents
  return <PermissionSetting targetObject={retailStoreOrder}  targetObjectMeta={RetailStoreOrderBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class RetailStoreOrderPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  retailStoreOrder = this.props.retailStoreOrder
    const { id,displayName, retailStoreOrderLineItemCount, retailStoreOrderShippingGroupCount, retailStoreOrderPaymentGroupCount, goodsCount } = retailStoreOrder
    const  returnURL = `/retailStoreOrder/${id}/workbench`
    const cardsData = {cardsName:"生超的订单",cardsFor: "retailStoreOrder",cardsSource: retailStoreOrder,displayName,returnURL,
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
  retailStoreOrder: state._retailStoreOrder,
}))(Form.create()(RetailStoreOrderPermission))

