import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import SelectObject from '../../components/SelectObject'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './SupplyOrder.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import SupplyOrderBase from './SupplyOrder.base'
import appLocaleName from '../../common/Locale.tool'
const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input
<<<<<<< HEAD

=======
const {fieldLabels} = SupplyOrderBase
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
const testValues = {};
/*
const testValues = {
  title: '双链给供货商下的订单',
<<<<<<< HEAD
  totalAmount: '2644544512.00',
  buyerId: 'RSCC000001',
  sellerId: 'GS000001',
=======
  totalAmount: '2792529920.00',
  buyerId: 'RSCC000001',
  sellerId: 'GS000001',
  confirmationId: 'SOC000001',
  approvalId: 'SOA000001',
  processingId: 'SOP000001',
  pickingId: 'SOP000001',
  shipmentId: 'SOS000001',
  deliveryId: 'SOD000001',
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
}
*/

const imageKeys = [
]


class SupplyOrderCreateFormBody extends Component {
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
    const { form, dispatch, submitting, role } = this.props
    const { convertedImagesValues } = this.state
	const userContext = null
    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
<<<<<<< HEAD
    const {fieldLabels} = SupplyOrderBase
=======
    
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
    const {SupplyOrderService} = GlobalComponents
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    const tryinit  = (fieldName) => {
      const { owner } = this.props
      if(!owner){
      	return null
      }
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
      if(!owner){
      	return true
      }
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

      labelCol: { span: 6 },
      wrapperCol: { span: 12 },

    }
    
    const internalRenderTitle = () =>{
      const linkComp=<a onClick={goback}  > <Icon type="double-left" style={{marginRight:"10px"}} /> </a>
<<<<<<< HEAD
      return (<div>{linkComp}{appLocaleName(userContext,"CreateNew")}供应订单</div>)
=======
      return (<div>{linkComp}{appLocaleName(userContext,"CreateNew")}{window.trans('supply_order')}</div>)
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
    }
	
	return (
      <div>
        <Card title={!this.props.hideTitle&&appLocaleName(userContext,"BasicInfo")} className={styles.card} bordered={false}>
          <Form >
          	<Row gutter={16}>
           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.title} {...formItemLayout}>
                  {getFieldDecorator('title', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
<<<<<<< HEAD
                    <Input size="large" placeholder="头衔" />
=======
                    <Input size="large"  placeHolder={fieldLabels.title} />
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.totalAmount} {...formItemLayout}>
                  {getFieldDecorator('totalAmount', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
<<<<<<< HEAD
                    <Input size="large" prefix={`${appLocaleName(userContext,"Currency")}`} placeholder="总金额" />
=======
                    <Input size="large" prefix={`${appLocaleName(userContext,"Currency")}`} placeHolder={fieldLabels.totalAmount} />
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
                  )}
                </Form.Item>
              </Col>


       
 
              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.buyer} {...formItemLayout}>
                  {getFieldDecorator('buyerId', {
                  	initialValue: tryinit('buyer'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('buyer')}
                    targetType={"buyer"} 
                    requestFunction={SupplyOrderService.requestCandidateBuyer}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.seller} {...formItemLayout}>
                  {getFieldDecorator('sellerId', {
                  	initialValue: tryinit('seller'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('seller')}
                    targetType={"seller"} 
                    requestFunction={SupplyOrderService.requestCandidateSeller}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

<<<<<<< HEAD
=======
              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.confirmation} {...formItemLayout}>
                  {getFieldDecorator('confirmationId', {
                  	initialValue: tryinit('confirmation'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('confirmation')}
                    targetType={"confirmation"} 
                    requestFunction={SupplyOrderService.requestCandidateConfirmation}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.approval} {...formItemLayout}>
                  {getFieldDecorator('approvalId', {
                  	initialValue: tryinit('approval'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('approval')}
                    targetType={"approval"} 
                    requestFunction={SupplyOrderService.requestCandidateApproval}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.processing} {...formItemLayout}>
                  {getFieldDecorator('processingId', {
                  	initialValue: tryinit('processing'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('processing')}
                    targetType={"processing"} 
                    requestFunction={SupplyOrderService.requestCandidateProcessing}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.picking} {...formItemLayout}>
                  {getFieldDecorator('pickingId', {
                  	initialValue: tryinit('picking'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('picking')}
                    targetType={"picking"} 
                    requestFunction={SupplyOrderService.requestCandidatePicking}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.shipment} {...formItemLayout}>
                  {getFieldDecorator('shipmentId', {
                  	initialValue: tryinit('shipment'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('shipment')}
                    targetType={"shipment"} 
                    requestFunction={SupplyOrderService.requestCandidateShipment}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.delivery} {...formItemLayout}>
                  {getFieldDecorator('deliveryId', {
                  	initialValue: tryinit('delivery'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('delivery')}
                    targetType={"delivery"} 
                    requestFunction={SupplyOrderService.requestCandidateDelivery}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe


			 </Row>
          </Form>
        </Card>








      
       </div>
    )
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(SupplyOrderCreateFormBody))





