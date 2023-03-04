import {RegistrationData, LoginData} from '../types/types';
import { client } from './config/axios';

const REGISTER_ENDPOINT = 'users/register';
const LOGIN_ENDPOINT = 'login'

export async function registerUser(credentials: RegistrationData): Promise<string> {
        return await client.post(REGISTER_ENDPOINT, credentials);
 }

export async function loginUser(credentials: LoginData) {
        return await client.post(LOGIN_ENDPOINT, credentials);        
}