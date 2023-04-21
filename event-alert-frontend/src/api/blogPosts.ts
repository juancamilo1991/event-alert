import axios, { AxiosResponse } from 'axios';
import { ChannelPost, RequestError } from '../types/types';
import { client } from './config/axios';


export const ALLPOSTS_ENDPOINT = 'blog/channel-posts';
export const FILTERED_POSTS_ENDPOINT = 'blog/posts';

export async function getPosts(endpoint: string, area_code: number, category?: string, order?: string): Promise<ChannelPost[] | RequestError> {
   try {
      const result = await client.get(endpoint, {
         params: {
            area: area_code,
            category: category,
            order: order
         }
      });
      return result.data;
   } catch (error) {
         if (axios.isAxiosError(error)) {
            console.log(error);
            const axiosError: RequestError = {
               myStatus: error.response?.status,
               message: error.response?.data
            }
            return axiosError;
         }
        return error as RequestError;
   }
}



