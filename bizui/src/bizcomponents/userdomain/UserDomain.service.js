
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}userDomainManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}userDomainManager/loadUserDomain/${targetObjectId}/${parametersExpr}/`,
  })
}







const addUserWhiteList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDomainManager/addUserWhiteList/userDomainId/userIdentity/userSpecialFunctions/tokensExpr/`
  const userDomainId = targetObjectId
  const requestParameters = { ...parameters, userDomainId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateUserWhiteList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDomainManager/updateUserWhiteListProperties/userDomainId/id/userIdentity/userSpecialFunctions/tokensExpr/`
  const userDomainId = targetObjectId
  const requestParameters = { ...parameters, userDomainId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeUserWhiteListList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDomainManager/removeUserWhiteListList/userDomainId/userWhiteListIds/tokensExpr/`
  const requestParameters = { ...parameters, userDomainId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addSecUser = (targetObjectId, parameters) => {
<<<<<<< HEAD
  const url = `${PREFIX}userDomainManager/addSecUser/userDomainId/login/mobile/email/pwd/weixinOpenid/weixinAppid/accessToken/verificationCode/verificationCodeExpire/lastLoginTime/tokensExpr/`
=======
  const url = `${PREFIX}userDomainManager/addSecUser/userDomainId/login/mobile/email/pwd/weixinOpenid/weixinAppid/accessToken/verificationCode/verificationCodeExpire/lastLoginTime/blockingId/tokensExpr/`
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
  const userDomainId = targetObjectId
  const requestParameters = { ...parameters, userDomainId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSecUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDomainManager/updateSecUserProperties/userDomainId/id/login/mobile/email/pwd/weixinOpenid/weixinAppid/accessToken/verificationCode/verificationCodeExpire/lastLoginTime/tokensExpr/`
  const userDomainId = targetObjectId
  const requestParameters = { ...parameters, userDomainId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSecUserList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDomainManager/removeSecUserList/userDomainId/secUserIds/tokensExpr/`
  const requestParameters = { ...parameters, userDomainId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}userDomainService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}userDomainService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}userDomainService/process/`,
    data,
  })
}

const UserDomainService = { view,
  load,
  addUserWhiteList,
  addSecUser,
  updateUserWhiteList,
  updateSecUser,
  removeUserWhiteListList,
  removeSecUserList, listFunctions, saveRequest, processRequest}
export default UserDomainService

