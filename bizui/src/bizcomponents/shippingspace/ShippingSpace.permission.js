

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './ShippingSpace.profile.less'
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
const internalSummaryOf = (shippingSpace,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{shippingSpace.id}</Description> 
<Description term="位置">{shippingSpace.location}</Description> 
<Description term="联系电话">{shippingSpace.contactNumber}</Description> 
<Description term="总面积">{shippingSpace.totalArea}</Description> 
<Description term="纬度">{shippingSpace.latitude}</Description> 
<Description term="经度">{shippingSpace.longitude}</Description> 
<Description term="描述">{shippingSpace.description}</Description> 
<Description term="更新于">{ moment(shippingSpace.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = shippingSpace => {
  const {ShippingSpaceBase} = GlobalComponents
  return <PermissionSetting targetObject={shippingSpace}  targetObjectMeta={ShippingSpaceBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class ShippingSpacePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  shippingSpace = this.props.shippingSpace
    const { id,displayName, goodsCount } = shippingSpace
    const  returnURL = `/shippingSpace/${id}/workbench`
    const cardsData = {cardsName:"发货区",cardsFor: "shippingSpace",cardsSource: shippingSpace,displayName,returnURL,
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
  shippingSpace: state._shippingSpace,
}))(Form.create()(ShippingSpacePermission))

