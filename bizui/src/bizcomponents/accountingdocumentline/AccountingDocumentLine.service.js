
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountingDocumentLineManager/view/${targetObjectId}/`,
  })
}
const analyze = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountingDocumentLineManager/analyze/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}accountingDocumentLineManager/loadAccountingDocumentLine/${targetObjectId}/${parametersExpr}/`,
  })
}


const queryCandidates = ({scenarioCode,ownerType,ownerId,listType,groupBy,filterKey,targetType}) => {
  
  const url = `${PREFIX}accountingDocumentLineManager/queryCandidates/`
  const data = JSON.stringify({scenarioCode,ownerType,ownerId,listType,groupBy,targetType,filterKey})
  console.log("requestParameters",data)
  return put({url,data})
} 


const requestCandidateBelongsTo = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountingDocumentLineManager/requestCandidateBelongsTo/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherBelongsTo = (id, parameters) => {
  const url = `${PREFIX}accountingDocumentLineManager/transferToAnotherBelongsTo/id/anotherBelongsToId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateAccountingSubject = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountingDocumentLineManager/requestCandidateAccountingSubject/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAccountingSubject = (id, parameters) => {
  const url = `${PREFIX}accountingDocumentLineManager/transferToAnotherAccountingSubject/id/anotherAccountingSubjectId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}accountingDocumentLineService/listFunctions/`,
  })
}


const  initRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentLineService/init/`,
    data,
  })
}

const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentLineService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}accountingDocumentLineService/process/`,
    data,
  })
}

const AccountingDocumentLineService = { view,
  load,
  analyze,
  requestCandidateBelongsTo,
  requestCandidateAccountingSubject,
  transferToAnotherBelongsTo,
  transferToAnotherAccountingSubject, listFunctions, saveRequest,initRequest, processRequest, queryCandidates}
export default AccountingDocumentLineService

