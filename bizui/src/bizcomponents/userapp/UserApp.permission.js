

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './UserApp.profile.less'
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
const internalSummaryOf = (userApp,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{userApp.id}</Description> 
<Description term="标题">{userApp.title}</Description> 
<Description term="图标">{userApp.appIcon}</Description> 
<Description term="权限">{userApp.permission}</Description> 
<Description term="对象类型">{userApp.appType}</Description> 
<Description term="对象ID">{userApp.appId}</Description> 
<Description term="上下文类型">{userApp.ctxType}</Description> 
<Description term="上下文类型">{userApp.ctxId}</Description> 
<Description term="位置">{userApp.location}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = userApp => {
  const {UserAppBase} = GlobalComponents
  return <PermissionSetting targetObject={userApp}  targetObjectMeta={UserAppBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class UserAppPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  userApp = this.props.userApp
    const { id,displayName, quickLinkCount, listAccessCount } = userApp
    const  returnURL = `/userApp/${id}/workbench`
    const cardsData = {cardsName:"应用",cardsFor: "userApp",cardsSource: userApp,displayName,returnURL,
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
  userApp: state._userApp,
}))(Form.create()(UserAppPermission))

