
import React from 'react'
import pathToRegexp from 'path-to-regexp'
import { routerRedux } from 'dva/router'
import { notification } from 'antd'
import GlobalComponents from '../../custcomponents';
import appLocaleName from '../../common/Locale.tool'
import modeltool from '../../utils/modeltool'
const {setupModel,hasError,handleClientError,handleServerError,keepValueWithKeySuffix}=modeltool

const notifySuccess=(userContext)=>{

	notification.success({
        message: appLocaleName(userContext,'Success'),
        description: appLocaleName(userContext,'Success'),
      })

}


export default {

  namespace: '_supplyOrder',

  state: {},

  subscriptions: {
    
    setup({ dispatch, history }) { 
      history.listen((location) => {
      	const modelName = 'supplyOrder'
      	const parameter = {dispatch,history,location,modelName}
        //console.log("setupModel",setupModel,typeof(setupModel))
      	setupModel(parameter)

      })
    },
  },
  effects: {
    *view({ payload }, { call, put, select }) { 
    
      const cachedData = yield select(state => state._supplyOrder)
      //if the data in the cache, just show it, there is no delay
      const link = payload.pathname
      //if the data in the cache, just show it, there is no delay
      if(cachedData.class){
        //yield put({ type: 'breadcrumb/gotoLink', payload: { displayName:cachedData.displayName,link }} )
        yield put({ type: 'updateState', payload: cachedData })
        
        if(payload.useCache){
        	return //use cache for returning page
        }
        
      }else{
        yield put({ type: 'showLoading', payload })
      }
      
      const {SupplyOrderService} = GlobalComponents;
      const data = yield call(SupplyOrderService.view, payload.id)
      
      const displayName = payload.displayName||data.displayName
      
      
      yield put({ type: 'breadcrumb/gotoLink', payload: { displayName,link }} )
      

      yield put({ type: 'updateState', payload: data })
    },
    *load({ payload }, { call, put }) { 
      const {SupplyOrderService} = GlobalComponents;
      //yield put({ type: 'showLoading', payload })
      const data = yield call(SupplyOrderService.load, payload.id, payload.parameters)
      const newPlayload = { ...payload, ...data }
      
      console.log('this is the data id: ', data.id)
      yield put({ type: 'updateState', payload: newPlayload })
    },
    
    *doJob({ payload }, { call, put }) { 
      const userContext = null
      const {TaskService} = GlobalComponents;
      //yield put({ type: 'showLoading', payload })      
      const {serviceNameToCall, id, parameters} = payload;
      if(!serviceNameToCall){
      	handleClientError(appLocaleName(userContext,'ServiceNotRegistered'))
      	return;
      }
      "react/dva_object_model.jsp"
      
      const data = yield call(serviceNameToCall, id, parameters)
      if(handleServerError(data)){
      	return
      }
      const newPlayload = { ...payload, ...data }
      
      console.log('this is the data id: ', data.id)
      yield put({ type: 'updateState', payload: newPlayload })
    },
       
    
    
    *gotoCreateForm({ payload }, { put }) {
      const { id, role } = payload
      yield put(routerRedux.push(`/supplyOrder/${id}/list/${role}CreateForm`))
    },
    *gotoUpdateForm({ payload }, { put }) {
      const { id, role, selectedRows, currentUpdateIndex } = payload
      const state = { id, role, selectedRows, currentUpdateIndex }
      const location = { pathname: `/supplyOrder/${id}/list/${role}UpdateForm`, state }
      yield put(routerRedux.push(location))
    },
    *goback({ payload }, { put }) {
      const { id, type,listName } = payload
      yield put(routerRedux.push(`/supplyOrder/${id}/list/${type}List/${listName}`))
    },




    *addSupplyOrderLineItem({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;

      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.addSupplyOrderLineItem, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }
      yield put({ type: 'updateState', payload: newPlayload })
      // yield put(routerRedux.push(`/supplyOrder/${id}/list/${role}CreateForm'))
      notifySuccess(userContext)
      if (continueNext) {
        return
      }
      const partialList = true
      const newState = {...data, partialList}
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderLineItemList/Supply Order Line Item+${appLocaleName(userContext,'List')}`, state: newState }
      yield put(routerRedux.push(location))
    },
    *updateSupplyOrderLineItem({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;      
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.updateSupplyOrderLineItem, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const partialList = true
      
      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex,partialList }
      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
      
      if (continueNext) {
        return
      }
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderLineItemList/Supply Order Line Item列表`, state: newPlayload }
      yield put(routerRedux.push(location))
    },
    *gotoNextSupplyOrderLineItemUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex }
      yield put({ type: 'updateState', payload: newPlayload })
    },
    *removeSupplyOrderLineItemList({ payload }, { call, put }) {
     const userContext = null
      const {SupplyOrderService} = GlobalComponents; 
      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.removeSupplyOrderLineItemList, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }

      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
    },




    *addSupplyOrderShippingGroup({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;

      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.addSupplyOrderShippingGroup, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }
      yield put({ type: 'updateState', payload: newPlayload })
      // yield put(routerRedux.push(`/supplyOrder/${id}/list/${role}CreateForm'))
      notifySuccess(userContext)
      if (continueNext) {
        return
      }
      const partialList = true
      const newState = {...data, partialList}
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderShippingGroupList/Supply Order Shipping Group+${appLocaleName(userContext,'List')}`, state: newState }
      yield put(routerRedux.push(location))
    },
    *updateSupplyOrderShippingGroup({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;      
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.updateSupplyOrderShippingGroup, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const partialList = true
      
      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex,partialList }
      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
      
      if (continueNext) {
        return
      }
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderShippingGroupList/Supply Order Shipping Group列表`, state: newPlayload }
      yield put(routerRedux.push(location))
    },
    *gotoNextSupplyOrderShippingGroupUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex }
      yield put({ type: 'updateState', payload: newPlayload })
    },
    *removeSupplyOrderShippingGroupList({ payload }, { call, put }) {
     const userContext = null
      const {SupplyOrderService} = GlobalComponents; 
      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.removeSupplyOrderShippingGroupList, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }

      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
    },




    *addSupplyOrderPaymentGroup({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;

      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.addSupplyOrderPaymentGroup, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }
      yield put({ type: 'updateState', payload: newPlayload })
      // yield put(routerRedux.push(`/supplyOrder/${id}/list/${role}CreateForm'))
      notifySuccess(userContext)
      if (continueNext) {
        return
      }
      const partialList = true
      const newState = {...data, partialList}
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderPaymentGroupList/Supply Order Payment Group+${appLocaleName(userContext,'List')}`, state: newState }
      yield put(routerRedux.push(location))
    },
    *updateSupplyOrderPaymentGroup({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;      
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.updateSupplyOrderPaymentGroup, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const partialList = true
      
      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex,partialList }
      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
      
      if (continueNext) {
        return
      }
      const location = { pathname: `/supplyOrder/${id}/list/SupplyOrderPaymentGroupList/Supply Order Payment Group列表`, state: newPlayload }
      yield put(routerRedux.push(location))
    },
    *gotoNextSupplyOrderPaymentGroupUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex }
      yield put({ type: 'updateState', payload: newPlayload })
    },
    *removeSupplyOrderPaymentGroupList({ payload }, { call, put }) {
     const userContext = null
      const {SupplyOrderService} = GlobalComponents; 
      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.removeSupplyOrderPaymentGroupList, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }

      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
    },




    *addGoods({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;

      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.addGoods, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }
      yield put({ type: 'updateState', payload: newPlayload })
      // yield put(routerRedux.push(`/supplyOrder/${id}/list/${role}CreateForm'))
      notifySuccess(userContext)
      if (continueNext) {
        return
      }
      const partialList = true
      const newState = {...data, partialList}
      const location = { pathname: `/supplyOrder/${id}/list/GoodsList/Goods+${appLocaleName(userContext,'List')}`, state: newState }
      yield put(routerRedux.push(location))
    },
    *updateGoods({ payload }, { call, put }) {
      const userContext = null
      const {SupplyOrderService} = GlobalComponents;      
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.updateGoods, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const partialList = true
      
      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex,partialList }
      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
      
      if (continueNext) {
        return
      }
      const location = { pathname: `/supplyOrder/${id}/list/GoodsList/Goods列表`, state: newPlayload }
      yield put(routerRedux.push(location))
    },
    *gotoNextGoodsUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex }
      yield put({ type: 'updateState', payload: newPlayload })
    },
    *removeGoodsList({ payload }, { call, put }) {
     const userContext = null
      const {SupplyOrderService} = GlobalComponents; 
      const { id, role, parameters, continueNext } = payload
      console.log('get form parameters', parameters)
      const data = yield call(SupplyOrderService.removeGoodsList, id, parameters)
      if (hasError(data)) {
        handleServerError(data)
        return
      }
      const newPlayload = { ...payload, ...data }

      yield put({ type: 'updateState', payload: newPlayload })
      notifySuccess(userContext)
    },

  },
  
  reducers: {
    updateState(state, action) {
      const payload = { ...action.payload, loading: true }
      const valueToKeep = keepValueWithKeySuffix(state,"Parameters") 
      return { ...valueToKeep, ...payload}
    },
    showLoading(state, action) {
      // const loading=true
      const payload = { ...action.payload, loading: true }
      const valueToKeep = keepValueWithKeySuffix(state,"Parameters") 
      return { ...valueToKeep, ...payload}
    },
  },
}

