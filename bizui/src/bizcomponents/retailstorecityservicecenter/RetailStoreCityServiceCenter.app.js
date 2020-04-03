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
  AutoComplete,Row, Col,
  Input,Button
} from 'antd'
import TopMenu from '../../launcher/TopMenu'
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
import appLocaleName from '../../common/Locale.tool'
import BizAppTool from '../../common/BizApp.tool'

const { Header, Sider, Content } = Layout
const { SubMenu } = Menu
const {
  defaultFilteredNoGroupMenuItems,
  defaultFilteredMenuItemsGroup,
  defaultRenderMenuItem,

} = BizAppTool


const filteredNoGroupMenuItems = defaultFilteredNoGroupMenuItems
const filteredMenuItemsGroup = defaultFilteredMenuItemsGroup
const renderMenuItem=defaultRenderMenuItem

const userBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


const searchBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 12,
  xl: 12,
  
};


const naviBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


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
     this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
      showSearch: false,
      searchKeyword:''
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
  
 getNavMenuItems = (targetObject, style, customTheme) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
    const mode =style || "inline"
    const theme = customTheme || "light" 
	const {objectId}=targetApp;
  	const userContext = null
    return (
	  <Menu
        theme="dark"
        mode="inline"
        
        onOpenChange={this.handleOpenChange}
        defaultOpenKeys={['firstOne']}
        
       >
           

             <Menu.Item key="dashboard">
               <Link to={`/retailStoreCityServiceCenter/${this.props.retailStoreCityServiceCenter.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
             </Menu.Item>
           
        {filteredNoGroupMenuItems(targetObject,this).map((item)=>(renderMenuItem(item)))}  
        {filteredMenuItemsGroup(targetObject,this).map((groupedMenuItem,index)=>{
          return(
    <SubMenu key={`vg${index}`} title={<span><Icon type="folder" style={{marginRight:"20px"}} /><span>{`${groupedMenuItem.viewGroup}`}</span></span>} >
      {groupedMenuItem.subItems.map((item)=>(renderMenuItem(item)))}  
    </SubMenu>

        )}
        )}

       		
        
           </Menu>
    )
  }
  



  getCityPartnerSearch = () => {
    const {CityPartnerSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('city_partner','retail_store_city_service_center.city_partner_list',false),
      role: "cityPartner",
      data: state._retailStoreCityServiceCenter.cityPartnerList,
      metaInfo: state._retailStoreCityServiceCenter.cityPartnerListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityPartnerCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/dashboard`,
      currentPage: state._retailStoreCityServiceCenter.cityPartnerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityPartnerSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CityPartnerSearch)
  }
  
  getCityPartnerCreateForm = () => {
   	const {CityPartnerCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "cityPartner",
      data: state._retailStoreCityServiceCenter.cityPartnerList,
      metaInfo: state._retailStoreCityServiceCenter.cityPartnerListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityPartnerCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/list`,
      currentPage: state._retailStoreCityServiceCenter.cityPartnerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityPartnerSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(CityPartnerCreateForm)
  }
  
  getCityPartnerUpdateForm = () => {
    const userContext = null
  	const {CityPartnerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "cityPartner",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'cityPartnerList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CityPartnerUpdateForm)
  }

  getPotentialCustomerSearch = () => {
    const {PotentialCustomerSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('potential_customer','retail_store_city_service_center.potential_customer_list',false),
      role: "potentialCustomer",
      data: state._retailStoreCityServiceCenter.potentialCustomerList,
      metaInfo: state._retailStoreCityServiceCenter.potentialCustomerListMetaInfo,
      count: state._retailStoreCityServiceCenter.potentialCustomerCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/dashboard`,
      currentPage: state._retailStoreCityServiceCenter.potentialCustomerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.potentialCustomerSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PotentialCustomerSearch)
  }
  
  getPotentialCustomerCreateForm = () => {
   	const {PotentialCustomerCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "potentialCustomer",
      data: state._retailStoreCityServiceCenter.potentialCustomerList,
      metaInfo: state._retailStoreCityServiceCenter.potentialCustomerListMetaInfo,
      count: state._retailStoreCityServiceCenter.potentialCustomerCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/list`,
      currentPage: state._retailStoreCityServiceCenter.potentialCustomerCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.potentialCustomerSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(PotentialCustomerCreateForm)
  }
  
  getPotentialCustomerUpdateForm = () => {
    const userContext = null
  	const {PotentialCustomerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "potentialCustomer",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'potentialCustomerList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(PotentialCustomerUpdateForm)
  }

  getCityEventSearch = () => {
    const {CityEventSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('city_event','retail_store_city_service_center.city_event_list',false),
      role: "cityEvent",
      data: state._retailStoreCityServiceCenter.cityEventList,
      metaInfo: state._retailStoreCityServiceCenter.cityEventListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityEventCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/dashboard`,
      currentPage: state._retailStoreCityServiceCenter.cityEventCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityEventSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CityEventSearch)
  }
  
  getCityEventCreateForm = () => {
   	const {CityEventCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "cityEvent",
      data: state._retailStoreCityServiceCenter.cityEventList,
      metaInfo: state._retailStoreCityServiceCenter.cityEventListMetaInfo,
      count: state._retailStoreCityServiceCenter.cityEventCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/list`,
      currentPage: state._retailStoreCityServiceCenter.cityEventCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.cityEventSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(CityEventCreateForm)
  }
  
  getCityEventUpdateForm = () => {
    const userContext = null
  	const {CityEventUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "cityEvent",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'cityEventList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CityEventUpdateForm)
  }

  getRetailStoreSearch = () => {
    const {RetailStoreSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('retail_store','retail_store_city_service_center.retail_store_list',false),
      role: "retailStore",
      data: state._retailStoreCityServiceCenter.retailStoreList,
      metaInfo: state._retailStoreCityServiceCenter.retailStoreListMetaInfo,
      count: state._retailStoreCityServiceCenter.retailStoreCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/dashboard`,
      currentPage: state._retailStoreCityServiceCenter.retailStoreCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.retailStoreSearchFormParameters,
      searchParameters: {...state._retailStoreCityServiceCenter.searchParameters},
      expandForm: state._retailStoreCityServiceCenter.expandForm,
      loading: state._retailStoreCityServiceCenter.loading,
      partialList: state._retailStoreCityServiceCenter.partialList,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, 
      referenceName: 'cityServiceCenter', 
      listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(RetailStoreSearch)
  }
  
  getRetailStoreCreateForm = () => {
   	const {RetailStoreCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "retailStore",
      data: state._retailStoreCityServiceCenter.retailStoreList,
      metaInfo: state._retailStoreCityServiceCenter.retailStoreListMetaInfo,
      count: state._retailStoreCityServiceCenter.retailStoreCount,
      returnURL: `/retailStoreCityServiceCenter/${state._retailStoreCityServiceCenter.id}/list`,
      currentPage: state._retailStoreCityServiceCenter.retailStoreCurrentPageNumber,
      searchFormParameters: state._retailStoreCityServiceCenter.retailStoreSearchFormParameters,
      loading: state._retailStoreCityServiceCenter.loading,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, referenceName: 'cityServiceCenter', listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(RetailStoreCreateForm)
  }
  
  getRetailStoreUpdateForm = () => {
    const userContext = null
  	const {RetailStoreUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreCityServiceCenter.selectedRows,
      role: "retailStore",
      currentUpdateIndex: state._retailStoreCityServiceCenter.currentUpdateIndex,
      owner: { type: '_retailStoreCityServiceCenter', id: state._retailStoreCityServiceCenter.id, listName: 'retailStoreList', ref:state._retailStoreCityServiceCenter, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(RetailStoreUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '双链小超全流程供应链系统'
    return title
  }
 
  buildRouters = () =>{
  	const {RetailStoreCityServiceCenterDashboard} = GlobalComponents
  	const {RetailStoreCityServiceCenterPermission} = GlobalComponents
  	const {RetailStoreCityServiceCenterProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/retailStoreCityServiceCenter/:id/dashboard", component: RetailStoreCityServiceCenterDashboard},
  	{path:"/retailStoreCityServiceCenter/:id/profile", component: RetailStoreCityServiceCenterProfile},
  	{path:"/retailStoreCityServiceCenter/:id/permission", component: RetailStoreCityServiceCenterPermission},
  	
  	
  	
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
   
   toggleSwitchText=()=>{
    const { collapsed } = this.props
    if(collapsed){
      return "打开菜单"
    }
    return "关闭菜单"

   }
   
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     
  
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
     const userContext = null
     const renderBreadcrumbText=(value)=>{
     	if(value==null){
     		return "..."
     	}
     	if(value.length < 10){
     		return value
     	}
     
     	return value.substring(0,10)+"..."
     	
     	
     }
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const renderBreadcrumbMenuItem=(breadcrumbMenuItem)=>{

      return (
      <Menu.Item key={breadcrumbMenuItem.link}>
      <Link key={breadcrumbMenuItem.link} to={`${breadcrumbMenuItem.link}`} className={styles.breadcrumbLink}>
        <Icon type="heart" style={{marginRight:"10px",color:"red"}} />
        {renderBreadcrumbText(breadcrumbMenuItem.name)}
      </Link></Menu.Item>)

     }
     const breadcrumbMenu=()=>{
      const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
      return ( <Menu mode="vertical"> 
      {currentBreadcrumb.map(item => renderBreadcrumbMenuItem(item))}
      </Menu>)
  

     }
     const breadcrumbBar=()=>{
      const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
      return ( <div mode="vertical"> 
      {currentBreadcrumb.map(item => renderBreadcrumbBarItem(item))}
      </div>)
  

     }


	const jumpToBreadcrumbLink=(breadcrumbMenuItem)=>{
      const { dispatch} = this.props
      const {name,link} = breadcrumbMenuItem
      dispatch({ type: 'breadcrumb/jumpToLink', payload: {name, link }} )
	
     }  

	 const removeBreadcrumbLink=(breadcrumbMenuItem)=>{
      const { dispatch} = this.props
      const {link} = breadcrumbMenuItem
      dispatch({ type: 'breadcrumb/removeLink', payload: { link }} )
	
     }

     const renderBreadcrumbBarItem=(breadcrumbMenuItem)=>{

      return (
     <Tag 
      	key={breadcrumbMenuItem.link} color={breadcrumbMenuItem.selected?"#108ee9":"grey"} 
      	style={{marginRight:"1px",marginBottom:"1px"}} closable onClose={()=>removeBreadcrumbLink(breadcrumbMenuItem)} >
        <span onClick={()=>jumpToBreadcrumbLink(breadcrumbMenuItem)}>
        	{renderBreadcrumbText(breadcrumbMenuItem.name)}
        </span>
      </Tag>)

     }
     
     
     
     const { Search } = Input;
     const showSearchResult=()=>{

        this.setState({showSearch:true})

     }
     const searchChange=(evt)=>{

      this.setState({searchKeyword :evt.target.value})

    }
    const hideSearchResult=()=>{

      this.setState({showSearch:false})

    }

    const {searchLocalData}=GlobalComponents.RetailStoreCityServiceCenterBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
             <a  className={styles.menuLink} onClick={()=>this.toggle()}>
                <Icon type="unordered-list" style={{fontSize:"20px", marginRight:"10px"}}/> 
                {this.toggleSwitchText()}
              </a>          
            
        </Col>
        <Col  className={styles.searchBox} {...searchBarResponsiveStyle}  > 
          
          <Search size="default" placeholder="请输入搜索条件, 查找功能，数据和词汇解释，关闭请点击搜索结果空白处" 
            enterButton onFocus={()=>showSearchResult()} onChange={(evt)=>searchChange(evt)}
           	
            style={{ marginLeft:"10px",marginTop:"7px",width:"100%"}} />  
            
            
          </Col>
          <Col  {...userBarResponsiveStyle}  > 
            <Dropdown overlay= { <TopMenu {...this.props} />} className={styles.right}>
                <a  className={styles.menuLink}>
                  <Icon type="user" style={{fontSize:"20px",marginRight:"10px"}}/> 账户
                </a>
            </Dropdown>
            
           </Col>  
         
         </Row>
        </Header>
       <Layout style={{  marginTop: 44 }}>
        
       
       <Layout>
      
      {this.state.showSearch&&(

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.retailStoreCityServiceCenter,this.state.searchKeyword)}</div>

      )}
       </Layout>
        
         
         <Layout>
       <Sider
          trigger={null}
          collapsible
          collapsed={collapsed}
          breakpoint="md"
          onCollapse={() => this.onCollapse(collapsed)}
          collapsedWidth={40}
          className={styles.sider}
        >
         
         {this.getNavMenuItems(this.props.retailStoreCityServiceCenter,"inline","dark")}
       
        </Sider>
        
         <Layout>
         <Layout><Row type="flex" justify="start" align="bottom">{breadcrumbBar()} </Row></Layout>
        
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
           </Content>
          </Layout>
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



