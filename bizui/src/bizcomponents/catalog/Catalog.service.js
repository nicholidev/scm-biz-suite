
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}catalogManager/view/${targetObjectId}/`,
  })
}
const analyze = (targetObjectId) => {
  return get({
    url: `${PREFIX}catalogManager/analyze/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}catalogManager/loadCatalog/${targetObjectId}/${parametersExpr}/`,
  })
}


const queryCandidates = ({scenarioCode,ownerType,ownerId,listType,groupBy,filterKey,targetType}) => {
  
  const url = `${PREFIX}catalogManager/queryCandidates/`
  const data = JSON.stringify({scenarioCode,ownerType,ownerId,listType,groupBy,targetType,filterKey})
  console.log("requestParameters",data)
  return put({url,data})
} 


const requestCandidateOwner = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}catalogManager/requestCandidateOwner/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherOwner = (id, parameters) => {
  const url = `${PREFIX}catalogManager/transferToAnotherOwner/id/anotherOwnerId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addLevelOneCategory = (targetObjectId, parameters) => {
  const url = `${PREFIX}catalogManager/addLevelOneCategory/catalogId/name/tokensExpr/`
  const catalogId = targetObjectId
  const requestParameters = { ...parameters, catalogId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateLevelOneCategory = (targetObjectId, parameters) => {
  const url = `${PREFIX}catalogManager/updateLevelOneCategoryProperties/catalogId/id/name/tokensExpr/`
  const catalogId = targetObjectId
  const requestParameters = { ...parameters, catalogId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeLevelOneCategoryList = (targetObjectId, parameters) => {
  const url = `${PREFIX}catalogManager/removeLevelOneCategoryList/catalogId/levelOneCategoryIds/tokensExpr/`
  const requestParameters = { ...parameters, catalogId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}catalogService/listFunctions/`,
  })
}


const  initRequest = (data) => {

  return put({
    url: `${PREFIX}catalogService/init/`,
    data,
  })
}

const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}catalogService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}catalogService/process/`,
    data,
  })
}

const CatalogService = { view,
  load,
  analyze,
  addLevelOneCategory,
  updateLevelOneCategory,
  removeLevelOneCategoryList,
  requestCandidateOwner,
  transferToAnotherOwner, listFunctions, saveRequest,initRequest, processRequest, queryCandidates}
export default CatalogService

