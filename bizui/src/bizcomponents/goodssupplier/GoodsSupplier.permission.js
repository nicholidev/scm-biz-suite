

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './GoodsSupplier.profile.less'
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
const internalSummaryOf = (goodsSupplier,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{goodsSupplier.id}</Description> 
<Description term="名称">{goodsSupplier.name}</Description> 
<Description term="供应产品">{goodsSupplier.supplyProduct}</Description> 
<Description term="联系电话">{goodsSupplier.contactNumber}</Description> 
<Description term="描述">{goodsSupplier.description}</Description> 
<Description term="更新于">{ moment(goodsSupplier.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = goodsSupplier => {
  const {GoodsSupplierBase} = GlobalComponents
  return <PermissionSetting targetObject={goodsSupplier}  targetObjectMeta={GoodsSupplierBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class GoodsSupplierPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  goodsSupplier = this.props.goodsSupplier
    const { id,displayName, supplierProductCount, supplyOrderCount, accountSetCount } = goodsSupplier
    const  returnURL = `/goodsSupplier/${id}/workbench`
    const cardsData = {cardsName:"产品供应商",cardsFor: "goodsSupplier",cardsSource: goodsSupplier,displayName,returnURL,
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
  goodsSupplier: state._goodsSupplier,
}))(Form.create()(GoodsSupplierPermission))

