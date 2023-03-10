

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './ResponsibilityType.profile.less'
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
const internalSummaryOf = (responsibilityType,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{responsibilityType.id}</Description> 
<Description term="代码">{responsibilityType.code}</Description> 
<Description term="基本描述">{responsibilityType.baseDescription}</Description> 
<Description term="详细描述">{responsibilityType.detailDescription}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = responsibilityType => {
  const {ResponsibilityTypeBase} = GlobalComponents
  return <PermissionSetting targetObject={responsibilityType}  targetObjectMeta={ResponsibilityTypeBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class ResponsibilityTypePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  responsibilityType = this.props.responsibilityType
    const { id,displayName, employeeCount } = responsibilityType
    const  returnURL = `/responsibilityType/${id}/workbench`
    const cardsData = {cardsName:"责任类型",cardsFor: "responsibilityType",cardsSource: responsibilityType,displayName,returnURL,
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
  responsibilityType: state._responsibilityType,
}))(Form.create()(ResponsibilityTypePermission))

