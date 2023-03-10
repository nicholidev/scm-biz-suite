

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './PotentialCustomerContactPerson.profile.less'
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
const internalSummaryOf = (potentialCustomerContactPerson,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{potentialCustomerContactPerson.id}</Description> 
<Description term="名称">{potentialCustomerContactPerson.name}</Description> 
<Description term="手机">{potentialCustomerContactPerson.mobile}</Description> 
<Description term="描述">{potentialCustomerContactPerson.description}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = potentialCustomerContactPerson => {
  const {PotentialCustomerContactPersonBase} = GlobalComponents
  return <PermissionSetting targetObject={potentialCustomerContactPerson}  targetObjectMeta={PotentialCustomerContactPersonBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class PotentialCustomerContactPersonPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  potentialCustomerContactPerson = this.props.potentialCustomerContactPerson
    const { id,displayName, potentialCustomerContactCount } = potentialCustomerContactPerson
    const  returnURL = `/potentialCustomerContactPerson/${id}/workbench`
    const cardsData = {cardsName:"潜在客户联络人",cardsFor: "potentialCustomerContactPerson",cardsSource: potentialCustomerContactPerson,displayName,returnURL,
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
  potentialCustomerContactPerson: state._potentialCustomerContactPerson,
}))(Form.create()(PotentialCustomerContactPersonPermission))

