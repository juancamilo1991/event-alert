import axios, { AxiosResponse } from 'axios';
import { ChannelPost, AuthHeaders, RequestError } from '../types/types';
import { client } from './config/axios';


export const ALLPOSTS_ENDPOINT = 'blog/channel-posts';
export const FILTERED_POSTS_ENDPOINT = 'blog/posts';
export const POST_BLOG_ENDPOINT = 'blog/create-post';


export async function makeApiRequest(endpoint: string, area_code: number, method?: string, category?: string, order?: string, title?: string, text?: string, headers?: AuthHeaders): Promise<ChannelPost[] | RequestError> {
   try {
      if (method === 'POST') {
         const postResult = await client.post(endpoint, {
            title: title,
            text: text,
            category: category,
            area: area_code
         }, {
            headers: headers
         });
         return postResult.data;
      }
      const getResult = await client.get(endpoint, {
         params: {
            area: area_code,
            category: category,
            order: order
         }
      });
      return getResult.data;
   } catch (error) {
         if (axios.isAxiosError(error)) {
            const axiosError: RequestError = {
               myStatus: error.response?.status,
               message: error.response?.data
            }
            return axiosError;
         }
        return error as RequestError;
   }
}



