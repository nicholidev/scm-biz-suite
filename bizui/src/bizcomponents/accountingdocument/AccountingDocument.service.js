
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountingDocumentManager/view/${targetObjectId}/`,
  })
}
const analyze = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountingDocumentManager/analyze/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}accountingDocumentManager/loadAccountingDocument/${targetObjectId}/${parametersExpr}/`,
  })
}


const queryCandidates = ({scenarioCode,ownerType,ownerId,listType,groupBy,filterKey,targetType}) => {
  
  const url = `${PREFIX}accountingDocumentManager/queryCandidates/`
  const data = JSON.stringify({scenarioCode,ownerType,ownerId,listType,groupBy,targetType,filterKey})
  console.log("requestParameters",data)
  return put({url,data})
} 


const requestCandidateAccountingPeriod = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountingDocumentManager/requestCandidateAccountingPeriod/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAccountingPeriod = (id, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/transferToAnotherAccountingPeriod/id/anotherAccountingPeriodId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateDocumentType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountingDocumentManager/requestCandidateDocumentType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherDocumentType = (id, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/transferToAnotherDocumentType/id/anotherDocumentTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addOriginalVoucher = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/addOriginalVoucher/accountingDocumentId/title/madeBy/receivedBy/voucherType/voucherImage/tokensExpr/`
  const accountingDocumentId = targetObjectId
  const requestParameters = { ...parameters, accountingDocumentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateOriginalVoucher = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/updateOriginalVoucherProperties/accountingDocumentId/id/title/madeBy/receivedBy/voucherType/voucherImage/tokensExpr/`
  const accountingDocumentId = targetObjectId
  const requestParameters = { ...parameters, accountingDocumentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeOriginalVoucherList = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/removeOriginalVoucherList/accountingDocumentId/originalVoucherIds/tokensExpr/`
  const requestParameters = { ...parameters, accountingDocumentId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addAccountingDocumentLine = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/addAccountingDocumentLine/accountingDocumentId/name/code/direct/amount/accountingSubjectId/tokensExpr/`
  const accountingDocumentId = targetObjectId
  const requestParameters = { ...parameters, accountingDocumentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateAccountingDocumentLine = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/updateAccountingDocumentLineProperties/accountingDocumentId/id/name/code/direct/amount/tokensExpr/`
  const accountingDocumentId = targetObjectId
  const requestParameters = { ...parameters, accountingDocumentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeAccountingDocumentLineList = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountingDocumentManager/removeAccountingDocumentLineList/accountingDocumentId/accountingDocumentLineIds/tokensExpr/`
  const requestParameters = { ...parameters, accountingDocumentId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}accountingDocumentService/listFunctions/`,
  })
}


const  initRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentService/init/`,
    data,
  })
}

const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentService/process/`,
    data,
  })
}

const AccountingDocumentService = { view,
  load,
  analyze,
  addOriginalVoucher,
  addAccountingDocumentLine,
  updateOriginalVoucher,
  updateAccountingDocumentLine,
  removeOriginalVoucherList,
  removeAccountingDocumentLineList,
  requestCandidateAccountingPeriod,
  requestCandidateDocumentType,
  transferToAnotherAccountingPeriod,
  transferToAnotherDocumentType, listFunctions, saveRequest,initRequest, processRequest, queryCandidates}
export default AccountingDocumentService

