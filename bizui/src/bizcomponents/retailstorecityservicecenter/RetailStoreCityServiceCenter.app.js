import React from 'react'
import PropTypes from 'prop-types'
import {
  Layout,
  Menu,
  Icon,
  Avatar,
  Dropdown,
  Tag,
  message,
  Spin,
  Breadcrumb,
  AutoComplete,
  Input,Button
} from 'antd'
import DocumentTitle from 'react-document-title'
import { connect } from 'dva'
import { Link, Route, Redirect, Switch } from 'dva/router'
import moment from 'moment'
import groupBy from 'lodash/groupBy'
import { ContainerQuery } from 'react-container-query'
import classNames from 'classnames'
import styles from './RetailStoreCityServiceCenter.app.less'
import {sessionObject} from '../../utils/utils'

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';


import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service'

const  {  filterForMenuPermission } = PermissionSettingService

const isMenuItemForDisplay = (item, targetObject, targetComponent) => {
  return true
}

const filteredMenuItems = (targetObject, targetComponent) => {
    const menuData = sessionObject('menuData')
    const isMenuItemForDisplayFunc = targetComponent.props.isMenuItemForDisplayFunc||isMenuItemForDisplay
    return menuData.subItems.filter(item=>filterForMenuPermission(item,targetObject,targetComponent)).filter(item=>isMenuItemForDisplayFunc(item,targetObject,targetComponent))
}



const { Header, Sider, Content } = Layout
const { SubMenu } = Menu

const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
}




class RetailStoreCityServiceCenterBizApp extends React.PureComponent {
  constructor(props) {
    super(props)
    // 把一级 Layout 的 children 作为菜单项
    // this.menus = getNavData().reduce((arr, current) => arr.concat(current.children), [])
    this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
    }
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout)
  }
  onCollapse = (collapsed) => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    })
  }

  getDefaultCollapsedSubMenus = (props) => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)]
    currentMenuSelectedKeys.splice(-1, 1)
    if (currentMenuSelectedKeys.length === 0) {
      return ['/retailStoreCityServiceCenter/']
    }
    return currentMenuSelectedKeys
  }
  getCurrentMenuSelectedKeys = (props) => {
    const { location: { pathname } } = props || this.props
    const keys = pathname.split('/').slice(1)
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key]
    }
    return keys
  }
  
  getNavMenuItems = (targetObject) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
	const {objectId}=targetApp;
  
    return (
      
		  <Menu
             theme="dark"
             mode="inline"
            
             
             onOpenChange={this.handleOpenChange}
            
             defaultOpenKeys={['firstOne']}
             style={{ margin: '16px 0', width: '100%' }}
           >
           

             <Menu.Item key="dashboard">
               <Link to={`/retailStoreCityServiceCenter/${this.props.retailStoreCityServiceCenter.id}/dashboard`}><Icon type="dashboard" /><span>仪表板</span></Link>
             </Menu.Item>
             
		 <Menu.Item key="homepage">
               <Link to={"/home"}><Icon type="home" /><span>回到主页</span></Link>
             </Menu.Item>
             
             
         {filteredMenuItems(targetObject,this).map((item)=>(<Menu.Item key={item.name}>
          <Link to={`/${menuData.menuFor}/${objectId}/list/${item.name}/${item.displayName}列表`}>
          <Icon type="bars" /><span>{item.displayName}</span>
          </Link>
        </Menu.Item>))}
       
       <Menu.Item key="preference">
               <Link to={`/retailStoreCityServiceCenter/${this.props.retailStoreCityServiceCenter.id}/preference`}><Icon type="setting" /><span>设置</span></Link>
             </Menu.Item>
      
           </Menu>
    )
  }
  



  getCityPartnerSearch = () => {
    const {CityPartnerSearch} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      name: "城市合伙人",
      role: "cityPartner",
      data: state._retailStoreCityServiceCenter.cityPartnerList,
      metaInfo: state._retailStoreCityServiceCenter.cityPartnerListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityPartnerCount,
      currentPage: state._retailStoreCityServiceCenter.cityPartnerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityPartnerSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: '城市合伙人列表' }, // this is for model namespace and
    }))(CityPartnerSearch)
  }
  getCityPartnerCreateForm = () => {
   	const {CityPartnerCreateForm} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      role: "cityPartner",
      data: state._retailStoreCityServiceCenter.cityPartnerList,
      metaInfo: state._retailStoreCityServiceCenter.cityPartnerListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityPartnerCount,
      currentPage: state._retailStoreCityServiceCenter.cityPartnerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityPartnerSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, listDisplayName: '城市合伙人列表'}, // this is for model namespace and
    }))(CityPartnerCreateForm)
  }
  
  getCityPartnerUpdateForm = () => {
  	const {CityPartnerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "cityPartner",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, listDisplayName: '城市合伙人列表' }, // this is for model namespace and
    }))(CityPartnerUpdateForm)
  }

  getPotentialCustomerSearch = () => {
    const {PotentialCustomerSearch} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      name: "潜在的客户",
      role: "potentialCustomer",
      data: state._retailStoreCityServiceCenter.potentialCustomerList,
      metaInfo: state._retailStoreCityServiceCenter.potentialCustomerListMetaInfo,
      count: state._retailStoreCityServiceCenter.potentialCustomerCount,
      currentPage: state._retailStoreCityServiceCenter.potentialCustomerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.potentialCustomerSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: '潜在的客户列表' }, // this is for model namespace and
    }))(PotentialCustomerSearch)
  }
  getPotentialCustomerCreateForm = () => {
   	const {PotentialCustomerCreateForm} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      role: "potentialCustomer",
      data: state._retailStoreCityServiceCenter.potentialCustomerList,
      metaInfo: state._retailStoreCityServiceCenter.potentialCustomerListMetaInfo,
      count: state._retailStoreCityServiceCenter.potentialCustomerCount,
      currentPage: state._retailStoreCityServiceCenter.potentialCustomerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.potentialCustomerSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, listDisplayName: '潜在的客户列表'}, // this is for model namespace and
    }))(PotentialCustomerCreateForm)
  }
  
  getPotentialCustomerUpdateForm = () => {
  	const {PotentialCustomerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "potentialCustomer",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, listDisplayName: '潜在的客户列表' }, // this is for model namespace and
    }))(PotentialCustomerUpdateForm)
  }

  getCityEventSearch = () => {
    const {CityEventSearch} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      name: "城市活动",
      role: "cityEvent",
      data: state._retailStoreCityServiceCenter.cityEventList,
      metaInfo: state._retailStoreCityServiceCenter.cityEventListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityEventCount,
      currentPage: state._retailStoreCityServiceCenter.cityEventCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityEventSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: '城市活动列表' }, // this is for model namespace and
    }))(CityEventSearch)
  }
  getCityEventCreateForm = () => {
   	const {CityEventCreateForm} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      role: "cityEvent",
      data: state._retailStoreCityServiceCenter.cityEventList,
      metaInfo: state._retailStoreCityServiceCenter.cityEventListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityEventCount,
      currentPage: state._retailStoreCityServiceCenter.cityEventCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityEventSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, listDisplayName: '城市活动列表'}, // this is for model namespace and
    }))(CityEventCreateForm)
  }
  
  getCityEventUpdateForm = () => {
  	const {CityEventUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "cityEvent",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, listDisplayName: '城市活动列表' }, // this is for model namespace and
    }))(CityEventUpdateForm)
  }

  getRetailStoreSearch = () => {
    const {RetailStoreSearch} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      name: "双链小超",
      role: "retailStore",
      data: state._retailStoreCityServiceCenter.retailStoreList,
      metaInfo: state._retailStoreCityServiceCenter.retailStoreListMetaInfo,
      count: state._retailStoreCityServiceCenter.retailStoreCount,
      currentPage: state._retailStoreCityServiceCenter.retailStoreCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.retailStoreSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: '双链小超列表' }, // this is for model namespace and
    }))(RetailStoreSearch)
  }
  getRetailStoreCreateForm = () => {
   	const {RetailStoreCreateForm} = GlobalComponents;
    return connect(state => ({
      rule: state.rule,
      role: "retailStore",
      data: state._retailStoreCityServiceCenter.retailStoreList,
      metaInfo: state._retailStoreCityServiceCenter.retailStoreListMetaInfo,
      count: state._retailStoreCityServiceCenter.retailStoreCount,
      currentPage: state._retailStoreCityServiceCenter.retailStoreCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.retailStoreSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, listDisplayName: '双链小超列表'}, // this is for model namespace and
    }))(RetailStoreCreateForm)
  }
  
  getRetailStoreUpdateForm = () => {
  	const {RetailStoreUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "retailStore",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, listDisplayName: '双链小超列表' }, // this is for model namespace and
    }))(RetailStoreUpdateForm)
  }


  
  buildRouters = () =>{
  	const {RetailStoreCityServiceCenterDashboard} = GlobalComponents
  	const {RetailStoreCityServiceCenterPreference} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/retailStoreCityServiceCenter/:id/dashboard", component: RetailStoreCityServiceCenterDashboard},
  	{path:"/retailStoreCityServiceCenter/:id/preference", component: RetailStoreCityServiceCenterPreference},
  	
  	
  	
  	{path:"/retailStoreCityServiceCenter/:id/list/cityPartnerList", component: this.getCityPartnerSearch()},
  	{path:"/retailStoreCityServiceCenter/:id/list/cityPartnerCreateForm", component: this.getCityPartnerCreateForm()},
  	{path:"/retailStoreCityServiceCenter/:id/list/cityPartnerUpdateForm", component: this.getCityPartnerUpdateForm()},
   	
  	{path:"/retailStoreCityServiceCenter/:id/list/potentialCustomerList", component: this.getPotentialCustomerSearch()},
  	{path:"/retailStoreCityServiceCenter/:id/list/potentialCustomerCreateForm", component: this.getPotentialCustomerCreateForm()},
  	{path:"/retailStoreCityServiceCenter/:id/list/potentialCustomerUpdateForm", component: this.getPotentialCustomerUpdateForm()},
   	
  	{path:"/retailStoreCityServiceCenter/:id/list/cityEventList", component: this.getCityEventSearch()},
  	{path:"/retailStoreCityServiceCenter/:id/list/cityEventCreateForm", component: this.getCityEventCreateForm()},
  	{path:"/retailStoreCityServiceCenter/:id/list/cityEventUpdateForm", component: this.getCityEventUpdateForm()},
   	
  	{path:"/retailStoreCityServiceCenter/:id/list/retailStoreList", component: this.getRetailStoreSearch()},
  	{path:"/retailStoreCityServiceCenter/:id/list/retailStoreCreateForm", component: this.getRetailStoreCreateForm()},
  	{path:"/retailStoreCityServiceCenter/:id/list/retailStoreUpdateForm", component: this.getRetailStoreUpdateForm()},
     	
  	
  	]
  	
  	const {extraRoutesFunc} = this.props;
	const extraRoutes = extraRoutesFunc?extraRoutesFunc():[]
    const finalRoutes = routers.concat(extraRoutes)
    
  	return (<Switch>
             {finalRoutes.map((item)=>(<Route key={item.path} path={item.path} component={item.component} />))}    
  	  	</Switch>)
  	
  
  }
 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '双链小超全流程供应链系统'
    return title
  }
 
  handleOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1)
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    })
  }
   toggle = () => {
     const { collapsed } = this.props
     this.props.dispatch({
       type: 'global/changeLayoutCollapsed',
       payload: !collapsed,
     })
   }
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     const { breadcrumb }  = this.props

     //const {RetailStoreCityServiceCenterEditDetail} = GlobalComponents
     //const {RetailStoreCityServiceCenterViewDetail} = GlobalComponents
     
     
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =sessionObject(targetApp.id)
     
     
     // Don't show popup menu when it is been collapsed
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const layout = (
     <Layout>
        <Header>
          
          <div className={styles.left}>
          <img
            src="./favicon.png"
            alt="logo"
            onClick={this.toggle}
            className={styles.logo}
          />
          {currentBreadcrumb.map((item)=>{
            return (<Link  key={item.link} to={`${item.link}`} className={styles.breadcrumbLink}> &gt;{item.name}</Link>)

          })}
         </div>
          <div className={styles.right}  >
          <Button type="primary"  icon="logout" onClick={()=>this.logout()}>
          退出</Button>
          </div>
          
        </Header>
       <Layout>
         <Sider
           trigger={null}
           collapsible
           collapsed={collapsed}
           breakpoint="md"
           onCollapse={()=>this.onCollapse(collapsed)}
           collapsedWidth={56}
           className={styles.sider}
         >

		 {this.getNavMenuItems(this.props.retailStoreCityServiceCenter)}
		 
         </Sider>
         <Layout>
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
 
             
             
           </Content>
          </Layout>
        </Layout>
      </Layout>
     )
     return (
       <DocumentTitle title={this.getPageTitle()}>
         <ContainerQuery query={query}>
           {params => <div className={classNames(params)}>{layout}</div>}
         </ContainerQuery>
       </DocumentTitle>
     )
   }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  retailStoreCityServiceCenter: state._retailStoreCityServiceCenter,
  ...state,
}))(RetailStoreCityServiceCenterBizApp)



