

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './MemberRewardPoint.profile.less'
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
const internalSummaryOf = (memberRewardPoint,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{memberRewardPoint.id}</Description> 
<Description term="名称">{memberRewardPoint.name}</Description> 
<Description term="点">{memberRewardPoint.point}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = memberRewardPoint => {
  const {MemberRewardPointBase} = GlobalComponents
  return <PermissionSetting targetObject={memberRewardPoint}  targetObjectMeta={MemberRewardPointBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class MemberRewardPointPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  memberRewardPoint = this.props.memberRewardPoint
    const { id,displayName,  } = memberRewardPoint
    const  returnURL = `/memberRewardPoint/${id}/workbench`
    const cardsData = {cardsName:"会员奖励点",cardsFor: "memberRewardPoint",cardsSource: memberRewardPoint,displayName,returnURL,
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
  memberRewardPoint: state._memberRewardPoint,
}))(Form.create()(MemberRewardPointPermission))

