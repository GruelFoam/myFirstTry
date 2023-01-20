import authApi from './modules/auth';

const APIS = {
    ...authApi
}

const getConfig = apiName => APIS[apiName];
export default getConfig;