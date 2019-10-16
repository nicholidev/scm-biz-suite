
import React, { PureComponent } from 'react'
import { connect } from 'dva'
import Result from '../../components/Result'
import { Row, Col, Card, Form, Input, Select, Icon, Button, Dropdown, Menu, InputNumber, DatePicker, Modal, message,Alert } from 'antd';
import GlobalComponents from '../../custcomponents'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './LevelThreeCategory.search.less'
import ListViewTool from '../../common/ListView.tool'
import PermissionSettingService from '../../permission/PermissionSetting.service'
<<<<<<< HEAD
=======
import appLocaleName from '../../common/Locale.tool'

import { Link, Route, Redirect} from 'dva/router'

>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854
const  {  hasCreatePermission,hasExecutionPermission,hasDeletePermission,hasUpdatePermission,hasReadPermission } = PermissionSettingService


const {handleSelectRows,handleStandardTableChange,
  showDeletionDialog,handleUpdate,handleDeletionModalVisible,
  handleElementCreate,toggleAssociateModalVisible,handleCloseAlert}=ListViewTool


<<<<<<< HEAD
const buttonMenuFor =(targetComponent, internalName, localeName)=> (
  <Menu >
    <Menu.Item key="1" onClick={()=>toggleAssociateModalVisible(targetComponent,internalName)}>新建{localeName}</Menu.Item>
    <Menu.Item key="2">合并{localeName}</Menu.Item>
   
  </Menu>
);
=======
const buttonMenuFor =(targetComponent, internalName, localeName)=> {
  const userContext = null
  return (
   <Menu >
     <Menu.Item key="1" onClick={()=>toggleAssociateModalVisible(targetComponent,internalName)}>{appLocaleName(userContext,"New")}{localeName}</Menu.Item>
     <Menu.Item key="2">{appLocaleName(userContext,"Merge")}{localeName}</Menu.Item>
    </Menu>
  )

}
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854


 
const showListActionBar = (targetComponent)=>{

  const {selectedRows} = targetComponent.state
  const {metaInfo} = targetComponent.props
  const disable = (selectedRows.length === 0)
<<<<<<< HEAD

  return (<div className={styles.tableListOperator}>
        
 
              {hasCreatePermission(metaInfo)&&<Button icon="plus" type="primary" onClick={() => handleElementCreate(targetComponent)}>新建</Button>}
              
 {hasDeletePermission(metaInfo)&&<Button onClick={(event)=>handleDeletionModalVisible(event,targetComponent)} type="danger" icon="delete" disabled={disable}>批量删除</Button>}
               

               {hasUpdatePermission(metaInfo)&&<Button onClick={()=>handleUpdate(targetComponent)} icon="update" disabled={disable}>批量更新</Button>}
 
 	
    
               
	</div> )
=======
  const userContext = null
  return (<div className={styles.tableListOperator}>
  

    {hasCreatePermission(metaInfo)&&<Button icon="plus" type="primary" onClick={() => handleElementCreate(targetComponent)}>{appLocaleName(userContext,"New")}</Button>}


    {hasUpdatePermission(metaInfo)&&<Button onClick={()=>handleUpdate(targetComponent)} icon="edit" disabled={disable}>{appLocaleName(userContext,"BatchUpdate")}</Button>}
 
 
    {hasDeletePermission(metaInfo)&&<Button onClick={(event)=>handleDeletionModalVisible(event,targetComponent)} type="danger" icon="delete" disabled={disable}>{appLocaleName(userContext,"BatchDelete")}</Button>}

</div> )
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854


}


const showAssociateDialog = (targetComponent) => {
  const {data, owner, visible,onCancel,onCreate} = targetComponent.props
  const {currentAssociateModal} = targetComponent.state
  
  const {selectedRows} = targetComponent.state
  
  const { LevelTwoCategoryAssociateForm } = GlobalComponents


  return (
  <div>
  
   
  
    <LevelTwoCategoryAssociateForm 
	visible={currentAssociateModal==='parentCategory'} 
	data={{levelThreeCategoryList:selectedRows}} owner={owner}  
	onCancel={()=>toggleAssociateModalVisible(targetComponent,'parentCategory')} 
	onCreate={()=>toggleAssociateModalVisible(targetComponent,'parentCategory')}/> 
 


    </div>
    
    
    
    )
}


class LevelThreeCategorySearch extends PureComponent {
  state = {
    deletionModalVisible: false,
    selectedRows: [],
    showDeleteResult: false,
    currentAssociateModal: null,
  }

  render(){
    const { data, loading, count, currentPage, owner,partialList } = this.props;
    const {displayName} = owner.ref
    const { showDeleteResult, selectedRows, deletionModalVisible, showAssociatePaymentForm } = this.state;
    const {LevelThreeCategoryTable} = GlobalComponents;
    const {LevelThreeCategorySearchForm} = GlobalComponents;
    const {LevelThreeCategoryModalTable} = GlobalComponents;
    
<<<<<<< HEAD
    
  
    return (
      <PageHeaderLayout title={`${displayName}:${this.props.name}列表`}>
=======
    const userContext = null
    
    const renderTitle=()=>{
      const {returnURL} = this.props
      
      const linkComp=returnURL?<Link to={returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
      return (<div>{linkComp}{`${displayName}:${this.props.name}${appLocaleName(userContext,"List")}`}</div>);
    }
  
    return (
      <PageHeaderLayout title={renderTitle()}>
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <LevelThreeCategorySearchForm {...this.props} />
            </div>
            <div className={styles.tableListOperator}>
           
   
              {showListActionBar(this)}
              {partialList&&(
              <div className={styles.searchAlert}>
<<<<<<< HEAD
                	<Alert message="下面显示最近更新结果，关闭显示全部" type="success" closable  afterClose={()=>handleCloseAlert(displayName, this)}/>
=======
                	<Alert message={appLocaleName(userContext,"CloseToShowAll")} type="success" closable  afterClose={()=>handleCloseAlert(displayName, this)}/>
>>>>>>> 502e8b8dfc403300a992b5083e79c722e85d1854
              </div>  	
              )}
              
            </div>
            <LevelThreeCategoryTable
              selectedRows={selectedRows}
              loading={loading}
              data={data}
              count={count}
              current={currentPage}
              onSelectRow={(selectedRows)=>handleSelectRows(selectedRows,this)}
              onChange={(pagination, filtersArg, sorter)=>handleStandardTableChange(pagination, filtersArg, sorter,this)}
              owner={owner}
              {...this.props}
            />
          </div>
        </Card>
        {showDeletionDialog(this,LevelThreeCategoryModalTable,"levelThreeCategoryIds")}
        {showAssociateDialog(this)}
      </PageHeaderLayout>
    )
  }
}

export default Form.create()(LevelThreeCategorySearch)


