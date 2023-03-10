

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './TruckDriver.profile.less'
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
const internalSummaryOf = (truckDriver,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{truckDriver.id}</Description> 
<Description term="名称">{truckDriver.name}</Description> 
<Description term="驾驶执照号码">{truckDriver.driverLicenseNumber}</Description> 
<Description term="联系电话">{truckDriver.contactNumber}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = truckDriver => {
  const {TruckDriverBase} = GlobalComponents
  return <PermissionSetting targetObject={truckDriver}  targetObjectMeta={TruckDriverBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class TruckDriverPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  truckDriver = this.props.truckDriver
    const { id,displayName, transportTaskCount } = truckDriver
    const  returnURL = `/truckDriver/${id}/workbench`
    const cardsData = {cardsName:"卡车司机",cardsFor: "truckDriver",cardsSource: truckDriver,displayName,returnURL,
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
  truckDriver: state._truckDriver,
}))(Form.create()(TruckDriverPermission))

