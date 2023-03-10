

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './EmployeeAttendance.profile.less'
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
const internalSummaryOf = (employeeAttendance,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{employeeAttendance.id}</Description> 
<Description term="进入时间">{ moment(employeeAttendance.enterTime).format('YYYY-MM-DD')}</Description> 
<Description term="离开的时候">{ moment(employeeAttendance.leaveTime).format('YYYY-MM-DD')}</Description> 
<Description term="持续时间">{employeeAttendance.durationHours}</Description> 
<Description term="备注">{employeeAttendance.remark}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = employeeAttendance => {
  const {EmployeeAttendanceBase} = GlobalComponents
  return <PermissionSetting targetObject={employeeAttendance}  targetObjectMeta={EmployeeAttendanceBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class EmployeeAttendancePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  employeeAttendance = this.props.employeeAttendance
    const { id,displayName,  } = employeeAttendance
    const  returnURL = `/employeeAttendance/${id}/workbench`
    const cardsData = {cardsName:"员工考勤",cardsFor: "employeeAttendance",cardsSource: employeeAttendance,displayName,returnURL,
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
  employeeAttendance: state._employeeAttendance,
}))(Form.create()(EmployeeAttendancePermission))

