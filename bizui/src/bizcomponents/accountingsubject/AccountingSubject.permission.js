

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './AccountingSubject.profile.less'
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
const internalSummaryOf = (accountingSubject,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{accountingSubject.id}</Description> 
<Description term="会计科目代码">{accountingSubject.accountingSubjectCode}</Description> 
<Description term="会计科目名称">{accountingSubject.accountingSubjectName}</Description> 
<Description term="会计科目类别代码">{accountingSubject.accountingSubjectClassCode}</Description> 
<Description term="会计科目类别名称">{accountingSubject.accountingSubjectClassName}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = accountingSubject => {
  const {AccountingSubjectBase} = GlobalComponents
  return <PermissionSetting targetObject={accountingSubject}  targetObjectMeta={AccountingSubjectBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class AccountingSubjectPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  accountingSubject = this.props.accountingSubject
    const { id,displayName, accountingDocumentLineCount } = accountingSubject
    const  returnURL = `/accountingSubject/${id}/workbench`
    const cardsData = {cardsName:"会计科目",cardsFor: "accountingSubject",cardsSource: accountingSubject,displayName,returnURL,
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
  accountingSubject: state._accountingSubject,
}))(Form.create()(AccountingSubjectPermission))

