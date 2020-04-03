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
import styles from './UserApp.app.less'
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




class UserAppBizApp extends React.PureComponent {
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
      return ['/userApp/']
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
               <Link to={`/userApp/${this.props.userApp.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getQuickLinkSearch = () => {
    const {QuickLinkSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('quick_link','user_app.quick_link_list',false),
      role: "quickLink",
      data: state._userApp.quickLinkList,
      metaInfo: state._userApp.quickLinkListMetaInfo,
      count: state._userApp.quickLinkCount,
      returnURL: `/userApp/${state._userApp.id}/dashboard`,
      currentPage: state._userApp.quickLinkCurrentPageNumber,
      searchFormParameters: state._userApp.quickLinkSearchFormParameters,
      searchParameters: {...state._userApp.searchParameters},
      expandForm: state._userApp.expandForm,
      loading: state._userApp.loading,
      partialList: state._userApp.partialList,
      owner: { type: '_userApp', id: state._userApp.id, 
      referenceName: 'app', 
      listName: 'quickLinkList', ref:state._userApp, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuickLinkSearch)
  }
  
  getQuickLinkCreateForm = () => {
   	const {QuickLinkCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "quickLink",
      data: state._userApp.quickLinkList,
      metaInfo: state._userApp.quickLinkListMetaInfo,
      count: state._userApp.quickLinkCount,
      returnURL: `/userApp/${state._userApp.id}/list`,
      currentPage: state._userApp.quickLinkCurrentPageNumber,
      searchFormParameters: state._userApp.quickLinkSearchFormParameters,
      loading: state._userApp.loading,
      owner: { type: '_userApp', id: state._userApp.id, referenceName: 'app', listName: 'quickLinkList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(QuickLinkCreateForm)
  }
  
  getQuickLinkUpdateForm = () => {
    const userContext = null
  	const {QuickLinkUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._userApp.selectedRows,
      role: "quickLink",
      currentUpdateIndex: state._userApp.currentUpdateIndex,
      owner: { type: '_userApp', id: state._userApp.id, listName: 'quickLinkList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuickLinkUpdateForm)
  }

  getListAccessSearch = () => {
    const {ListAccessSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('list_access','user_app.list_access_list',false),
      role: "listAccess",
      data: state._userApp.listAccessList,
      metaInfo: state._userApp.listAccessListMetaInfo,
      count: state._userApp.listAccessCount,
      returnURL: `/userApp/${state._userApp.id}/dashboard`,
      currentPage: state._userApp.listAccessCurrentPageNumber,
      searchFormParameters: state._userApp.listAccessSearchFormParameters,
      searchParameters: {...state._userApp.searchParameters},
      expandForm: state._userApp.expandForm,
      loading: state._userApp.loading,
      partialList: state._userApp.partialList,
      owner: { type: '_userApp', id: state._userApp.id, 
      referenceName: 'app', 
      listName: 'listAccessList', ref:state._userApp, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ListAccessSearch)
  }
  
  getListAccessCreateForm = () => {
   	const {ListAccessCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "listAccess",
      data: state._userApp.listAccessList,
      metaInfo: state._userApp.listAccessListMetaInfo,
      count: state._userApp.listAccessCount,
      returnURL: `/userApp/${state._userApp.id}/list`,
      currentPage: state._userApp.listAccessCurrentPageNumber,
      searchFormParameters: state._userApp.listAccessSearchFormParameters,
      loading: state._userApp.loading,
      owner: { type: '_userApp', id: state._userApp.id, referenceName: 'app', listName: 'listAccessList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ListAccessCreateForm)
  }
  
  getListAccessUpdateForm = () => {
    const userContext = null
  	const {ListAccessUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._userApp.selectedRows,
      role: "listAccess",
      currentUpdateIndex: state._userApp.currentUpdateIndex,
      owner: { type: '_userApp', id: state._userApp.id, listName: 'listAccessList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ListAccessUpdateForm)
  }

  getObjectAccessSearch = () => {
    const {ObjectAccessSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('object_access','user_app.object_access_list',false),
      role: "objectAccess",
      data: state._userApp.objectAccessList,
      metaInfo: state._userApp.objectAccessListMetaInfo,
      count: state._userApp.objectAccessCount,
      returnURL: `/userApp/${state._userApp.id}/dashboard`,
      currentPage: state._userApp.objectAccessCurrentPageNumber,
      searchFormParameters: state._userApp.objectAccessSearchFormParameters,
      searchParameters: {...state._userApp.searchParameters},
      expandForm: state._userApp.expandForm,
      loading: state._userApp.loading,
      partialList: state._userApp.partialList,
      owner: { type: '_userApp', id: state._userApp.id, 
      referenceName: 'app', 
      listName: 'objectAccessList', ref:state._userApp, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ObjectAccessSearch)
  }
  
  getObjectAccessCreateForm = () => {
   	const {ObjectAccessCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "objectAccess",
      data: state._userApp.objectAccessList,
      metaInfo: state._userApp.objectAccessListMetaInfo,
      count: state._userApp.objectAccessCount,
      returnURL: `/userApp/${state._userApp.id}/list`,
      currentPage: state._userApp.objectAccessCurrentPageNumber,
      searchFormParameters: state._userApp.objectAccessSearchFormParameters,
      loading: state._userApp.loading,
      owner: { type: '_userApp', id: state._userApp.id, referenceName: 'app', listName: 'objectAccessList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ObjectAccessCreateForm)
  }
  
  getObjectAccessUpdateForm = () => {
    const userContext = null
  	const {ObjectAccessUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._userApp.selectedRows,
      role: "objectAccess",
      currentUpdateIndex: state._userApp.currentUpdateIndex,
      owner: { type: '_userApp', id: state._userApp.id, listName: 'objectAccessList', ref:state._userApp, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ObjectAccessUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '双链小超全流程供应链系统'
    return title
  }
 
  buildRouters = () =>{
  	const {UserAppDashboard} = GlobalComponents
  	const {UserAppPermission} = GlobalComponents
  	const {UserAppProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/userApp/:id/dashboard", component: UserAppDashboard},
  	{path:"/userApp/:id/profile", component: UserAppProfile},
  	{path:"/userApp/:id/permission", component: UserAppPermission},
  	
  	
  	
  	{path:"/userApp/:id/list/quickLinkList", component: this.getQuickLinkSearch()},
  	{path:"/userApp/:id/list/quickLinkCreateForm", component: this.getQuickLinkCreateForm()},
  	{path:"/userApp/:id/list/quickLinkUpdateForm", component: this.getQuickLinkUpdateForm()},
   	
  	{path:"/userApp/:id/list/listAccessList", component: this.getListAccessSearch()},
  	{path:"/userApp/:id/list/listAccessCreateForm", component: this.getListAccessCreateForm()},
  	{path:"/userApp/:id/list/listAccessUpdateForm", component: this.getListAccessUpdateForm()},
   	
  	{path:"/userApp/:id/list/objectAccessList", component: this.getObjectAccessSearch()},
  	{path:"/userApp/:id/list/objectAccessCreateForm", component: this.getObjectAccessCreateForm()},
  	{path:"/userApp/:id/list/objectAccessUpdateForm", component: this.getObjectAccessUpdateForm()},
     	
 	 
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

    const {searchLocalData}=GlobalComponents.UserAppBase
	
    
     
     
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.userApp,this.state.searchKeyword)}</div>

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
         
         {this.getNavMenuItems(this.props.userApp,"inline","dark")}
       
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
  userApp: state._userApp,
  ...state,
}))(UserAppBizApp)



