import React, { PureComponent } from 'react'
import { connect } from 'dva'
import {
  Row,
  Col,
  Card,
  Form,
  Input,
  Select,
  Icon,
  Button,
  Dropdown,
  Menu,
  InputNumber,
  DatePicker,
  Modal,
  message,
} from 'antd'

import styles from './VehicleServiceCompanyEmployee.search.less'

const FormItem = Form.Item
const { Option } = Select
const getValue = obj =>
  Object.keys(obj)
    .map(key => obj[key])
    .join(',')

@Form.create()
export default class VehicleServiceCompanyEmployeeSearchForm extends PureComponent {
  state = {
    // addInputValue: '',
    // modalVisible: false,
    expandForm: false,
    // selectedRows: [],
    // formValues: {},
  }
  componentDidMount() {
    // const { dispatch } = this.props
    // console.log(this.props)
    // const { getFieldDecorator, setFieldsValue } = this.props.form
    const { setFieldsValue } = this.props.form
    const { searchFormParameters } = this.props
    if (!searchFormParameters) {
      return
    }
    // console.log("searchFormParameters", searchFormParameters)
    setFieldsValue(searchFormParameters)
  }
  toggleForm = () => {
    this.setState({
      expandForm: !this.state.expandForm,
    })
  }
  handleFormReset = () => {
    const { form, dispatch } = this.props
    form.resetFields()
    dispatch({
      type: 'rule/fetch',
      payload: {},
    })
  }
  buildStringSearchParameters = (formValues, fieldName) => {
    const fieldValue = formValues[fieldName]
    if (!fieldValue) {
      console.log('NO VALUE')
      return {}
    }
    return {
      vehicleServiceCompanyEmployeeList: 1,
      'vehicleServiceCompanyEmployeeList.searchField': fieldName,
      'vehicleServiceCompanyEmployeeList.searchVerb': 'startsWith',
      'vehicleServiceCompanyEmployeeList.searchValue': fieldValue,
    }
  }
  handleSearch = e => {
    e.preventDefault()
    const { dispatch, form } = this.props
    form.validateFields((err, fieldsValue) => {
      if (err) return
      const params = {
        ...this.buildStringSearchParameters(fieldsValue, 'id'),
        ...this.buildStringSearchParameters(fieldsValue, 'employeeName'),
        ...this.buildStringSearchParameters(fieldsValue, 'gender'),
        ...this.buildStringSearchParameters(fieldsValue, 'availableState'),
        ...this.buildStringSearchParameters(fieldsValue, 'identityCardNumber'),
      }
      const { owner } = this.props
      dispatch({
        type: `${owner.type}/load`,
        payload: {
          id: owner.id,
          parameters: params,
          vehicleServiceCompanyEmployeeSearchFormParameters: fieldsValue,
        },
      })
    })
  }

  renderSimpleForm() {
    const { getFieldDecorator } = this.props.form
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="??????">
              {getFieldDecorator('id')(<Input placeholder="???????????????" />)}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="???????????????">
              {getFieldDecorator('employeeName')(
                <Input placeholder="????????????????????????" />
              )}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <span className={styles.submitButtons}>
              <Button type="primary" htmlType="submit">
                ??????
              </Button>
              <Button style={{ marginLeft: 8 }} onClick={this.handleFormReset}>
                ??????
              </Button>
              <a style={{ marginLeft: 8 }} onClick={this.toggleForm}>
                {' '}
                ?????? <Icon type="down" />{' '}
              </a>
            </span>
          </Col>
        </Row>
      </Form>
    )
  }
  renderAdvancedForm() {
    const { getFieldDecorator } = this.props.form
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="??????">
              {getFieldDecorator('id')(<Input placeholder="???????????????" />)}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="???????????????">
              {getFieldDecorator('employeeName')(
                <Input placeholder="????????????????????????" />
              )}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="??????">
              {getFieldDecorator('gender')(<Input placeholder="???????????????" />)}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="????????????">
              {getFieldDecorator('availableState')(
                <Input placeholder="?????????????????????" />
              )}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="???????????????">
              {getFieldDecorator('identityCardNumber')(
                <Input placeholder="????????????????????????" />
              )}
            </FormItem>
          </Col>
        </Row>
        <div style={{ overflow: 'hidden' }}>
          <span style={{ float: 'right', marginBottom: 24 }}>
            <Button type="primary" htmlType="submit">
              ??????
            </Button>
            <Button style={{ marginLeft: 8 }} onClick={this.handleFormReset}>
              ??????
            </Button>
            <a style={{ marginLeft: 8 }} onClick={this.toggleForm}>
              ?????? <Icon type="up" />
            </a>
          </span>
        </div>
      </Form>
    )
  }

  render() {
    return this.state.expandForm
      ? this.renderAdvancedForm()
      : this.renderSimpleForm()
  }
}
