

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './TransportTask.profile.less'
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
const internalSummaryOf = (transportTask,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{transportTask.id}</Description> 
<Description term="名称">{transportTask.name}</Description> 
<Description term="开始">{transportTask.start}</Description> 
<Description term="开始时间">{ moment(transportTask.beginTime).format('YYYY-MM-DD')}</Description> 
<Description term="纬度">{transportTask.latitude}</Description> 
<Description term="经度">{transportTask.longitude}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = transportTask => {
  const {TransportTaskBase} = GlobalComponents
  return <PermissionSetting targetObject={transportTask}  targetObjectMeta={TransportTaskBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class TransportTaskPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  transportTask = this.props.transportTask
    const { id,displayName, goodsCount, transportTaskTrackCount } = transportTask
    const  returnURL = `/transportTask/${id}/workbench`
    const cardsData = {cardsName:"运输任务",cardsFor: "transportTask",cardsSource: transportTask,displayName,returnURL,
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
  transportTask: state._transportTask,
}))(Form.create()(TransportTaskPermission))

