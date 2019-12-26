
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}terminationManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}terminationManager/loadTermination/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateReason = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}terminationManager/requestCandidateReason/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherReason = (id, parameters) => {
  const url = `${PREFIX}terminationManager/transferToAnotherReason/id/anotherReasonId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}terminationManager/requestCandidateType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherType = (id, parameters) => {
  const url = `${PREFIX}terminationManager/transferToAnotherType/id/anotherTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addEmployee = (targetObjectId, parameters) => {
  const url = `${PREFIX}terminationManager/addEmployee/terminationId/companyId/title/departmentId/familyName/givenName/email/city/address/cellPhone/occupationId/responsibleForId/currentSalaryGradeId/salaryAccount/jobApplicationId/professionInterviewId/hrInterviewId/offerApprovalId/offerAcceptanceId/employeeBoardingId/tokensExpr/`
  const terminationId = targetObjectId
  const requestParameters = { ...parameters, terminationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateEmployee = (targetObjectId, parameters) => {
  const url = `${PREFIX}terminationManager/updateEmployeeProperties/terminationId/id/title/familyName/givenName/email/city/address/cellPhone/salaryAccount/tokensExpr/`
  const terminationId = targetObjectId
  const requestParameters = { ...parameters, terminationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeEmployeeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}terminationManager/removeEmployeeList/terminationId/employeeIds/tokensExpr/`
  const requestParameters = { ...parameters, terminationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}terminationService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}terminationService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}terminationService/process/`,
    data,
  })
}

const TerminationService = { view,
  load,
  addEmployee,
  updateEmployee,
  removeEmployeeList,
  requestCandidateReason,
  requestCandidateType,
  transferToAnotherReason,
  transferToAnotherType, listFunctions, saveRequest, processRequest}
export default TerminationService

