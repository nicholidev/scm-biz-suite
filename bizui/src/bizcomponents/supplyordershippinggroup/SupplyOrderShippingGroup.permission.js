

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './SupplyOrderShippingGroup.profile.less'
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
const internalSummaryOf = (supplyOrderShippingGroup,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{supplyOrderShippingGroup.id}</Description> 
<Description term="名称">{supplyOrderShippingGroup.name}</Description> 
<Description term="金额">{supplyOrderShippingGroup.amount}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = supplyOrderShippingGroup => {
  const {SupplyOrderShippingGroupBase} = GlobalComponents
  return <PermissionSetting targetObject={supplyOrderShippingGroup}  targetObjectMeta={SupplyOrderShippingGroupBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class SupplyOrderShippingGroupPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  supplyOrderShippingGroup = this.props.supplyOrderShippingGroup
    const { id,displayName,  } = supplyOrderShippingGroup
    const  returnURL = `/supplyOrderShippingGroup/${id}/workbench`
    const cardsData = {cardsName:"供应订单送货分组",cardsFor: "supplyOrderShippingGroup",cardsSource: supplyOrderShippingGroup,displayName,returnURL,
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
  supplyOrderShippingGroup: state._supplyOrderShippingGroup,
}))(Form.create()(SupplyOrderShippingGroupPermission))

