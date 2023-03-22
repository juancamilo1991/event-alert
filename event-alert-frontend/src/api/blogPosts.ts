import axios, { AxiosResponse } from 'axios';
import { ChannelPost, RequestError } from '../types/types';
import { client } from './config/axios';


export const ALLPOSTS_ENDPOINT = 'blog/channel-posts';
export const FILTERED_POSTS_ENDPOINT = 'blog/posts';

export async function getPosts(endpoint: string, area_code: number, category?: string, order?: string): Promise<ChannelPost[] | RequestError> {
   try {
      return await client.get(endpoint, {
         params: {
            area: area_code,
            category: category,
            order: order
         }
      });
   } catch (error) {
      return new Promise<RequestError>((resolve, reject) => {
         if (axios.isAxiosError(error)) {
            const axiosError: RequestError = {
               status: error.status as number,
               message: error.message
            }
            resolve(axiosError)  
         }
         else {
            resolve(error as RequestError);
         }
      })
   }
}



