

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './MemberRewardPointRedemption.profile.less'
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
const internalSummaryOf = (memberRewardPointRedemption,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{memberRewardPointRedemption.id}</Description> 
<Description term="名称">{memberRewardPointRedemption.name}</Description> 
<Description term="点">{memberRewardPointRedemption.point}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = memberRewardPointRedemption => {
  const {MemberRewardPointRedemptionBase} = GlobalComponents
  return <PermissionSetting targetObject={memberRewardPointRedemption}  targetObjectMeta={MemberRewardPointRedemptionBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class MemberRewardPointRedemptionPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  memberRewardPointRedemption = this.props.memberRewardPointRedemption
    const { id,displayName,  } = memberRewardPointRedemption
    const  returnURL = `/memberRewardPointRedemption/${id}/workbench`
    const cardsData = {cardsName:"会员奖励点赎回",cardsFor: "memberRewardPointRedemption",cardsSource: memberRewardPointRedemption,displayName,returnURL,
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
  memberRewardPointRedemption: state._memberRewardPointRedemption,
}))(Form.create()(MemberRewardPointRedemptionPermission))

