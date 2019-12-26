import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch,Modal } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './AccountingDocument.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import AccountingDocumentBase from './AccountingDocument.base'
import SelectObject from '../../components/SelectObject'
import appLocaleName from '../../common/Locale.tool'

const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input

const testValues = {};
/*
const testValues = {
  name: '记账凭证',
<<<<<<< HEAD
  accountingDocumentDate: '2018-07-30',
=======
  accountingDocumentDate: '2018-11-02',
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
  accountingPeriodId: 'AP000001',
  documentTypeId: 'ADT000001',
  creationId: 'ADC000001',
  confirmationId: 'ADC000001',
  auditingId: 'ADA000001',
  postingId: 'ADP000001',
}
*/


const imageKeys = [
]


class AccountingDocumentAssociateForm extends Component {
  state = {
    previewVisible: false,
    previewImage: '',
    convertedImagesValues: {},
  }

  componentDidMount() {
 
    
    
    
  }

  handlePreview = (file) => {
    console.log('preview file', file)
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    })
  }

  



  handleChange = (event, source) => {
    console.log('get file list from change in update change:', source)

    const { fileList } = event
    const { convertedImagesValues } = this.state

    convertedImagesValues[source] = fileList
    this.setState({ convertedImagesValues })
    console.log('/get file list from change in update change:', source)
  }
	
  

  render() {
	const { form, dispatch, submitting, role,data,owner,toggleAssociatePaymentVisible,visible,onCancel, onCreate } = this.props
    const { convertedImagesValues } = this.state
    const {AccountingDocumentService} = GlobalComponents
    const userContext = null
    
 const {OriginalVoucherModalTable} = GlobalComponents;
 const {AccountingDocumentLineModalTable} = GlobalComponents;


    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
    const {fieldLabels} = AccountingDocumentBase
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    

    
    
    const tryinit  = (fieldName, candidates) => {
      
      if(candidates&&candidates.length==1){
          return candidates[0].id
      }
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return true
      }
      return false
    
    }
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
    }
    const switchFormItemLayout = {
      labelCol: { span: 14 },
      wrapperCol: { span: 4 },
    }
   
    return (
 <Modal
          title={appLocaleName(userContext,"CreateNew")}
          visible={visible}
          onOk={onCancel}
          onCancel={onCancel}
          width={920}
          style={{ top: 40}}
        >
        <Card title={appLocaleName(userContext,"BasicInfo")}  className={styles.card} style={{ backgroundColor:"#eee" }}>
          <Form >
            <Row gutter={16}>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.name} {...formItemLayout}>
                  {getFieldDecorator('name', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
<<<<<<< HEAD
                    <Input size="large" placeholder="名称" />
=======
                    <Input size="large"  placeHolder={fieldLabels.name} />
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.accountingDocumentDate} {...formItemLayout}>
                  {getFieldDecorator('accountingDocumentDate', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
<<<<<<< HEAD
                    <DatePicker size="large" format="YYYY-MM-DD" placeholder="会计凭证日期" />
=======
                    <DatePicker size="large" format="YYYY-MM-DD"  placeHolder={fieldLabels.accountingDocumentDate}/>
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
                  )}
                </Form.Item>
              </Col>

            </Row>


       
        









       
            <Row gutter={16}>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.accountingPeriod} {...formItemLayout}>
                  {getFieldDecorator('accountingPeriodId', {
                  	initialValue: tryinit('accountingPeriod'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('accountingPeriod')}
                    targetType={"accountingPeriod"} 
                    requestFunction={AccountingDocumentService.requestCandidateAccountingPeriod}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.documentType} {...formItemLayout}>
                  {getFieldDecorator('documentTypeId', {
                  	initialValue: tryinit('documentType'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('documentType')}
                    targetType={"documentType"} 
                    requestFunction={AccountingDocumentService.requestCandidateDocumentType}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.creation} {...formItemLayout}>
                  {getFieldDecorator('creationId', {
                  	initialValue: tryinit('creation'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('creation')}
                    targetType={"creation"} 
                    requestFunction={AccountingDocumentService.requestCandidateCreation}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.confirmation} {...formItemLayout}>
                  {getFieldDecorator('confirmationId', {
                  	initialValue: tryinit('confirmation'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('confirmation')}
                    targetType={"confirmation"} 
                    requestFunction={AccountingDocumentService.requestCandidateConfirmation}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.auditing} {...formItemLayout}>
                  {getFieldDecorator('auditingId', {
                  	initialValue: tryinit('auditing'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('auditing')}
                    targetType={"auditing"} 
                    requestFunction={AccountingDocumentService.requestCandidateAuditing}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.posting} {...formItemLayout}>
                  {getFieldDecorator('postingId', {
                  	initialValue: tryinit('posting'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('posting')}
                    targetType={"posting"} 
                    requestFunction={AccountingDocumentService.requestCandidatePosting}/>
  
                  )}
                </Form.Item>
              </Col>

            </Row>
         
       

			</Form>
			
			
			
			
        </Card>
        
	<OriginalVoucherModalTable data={data.originalVoucherList} owner={owner} />
	<AccountingDocumentLineModalTable data={data.accountingDocumentLineList} owner={owner} />
        
        
        
      </Modal>)
    
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(AccountingDocumentAssociateForm))




