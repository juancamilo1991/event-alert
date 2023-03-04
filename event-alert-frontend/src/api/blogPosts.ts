import axios, { AxiosResponse } from 'axios';
import { ChannelPost } from '../types/types';
import { client } from './config/axios';


const ALLPOSTS_ENDPOINT = 'blog/channel-posts';
const FILTERED_POSTS_ENDPOINT = 'blog/posts';

export async function getAllPosts(area_code: number): Promise<ChannelPost[]> {
   const result = getPosts(ALLPOSTS_ENDPOINT, area_code);
   return (await result).data
   /* try {
      const result = await client.get(ALLPOSTS_ENDPOINT, { params: { area: area_code } });
      return result.data;
   } catch (error) {
      return error as Promise<ChannelPost[]>;
   } */
};

export async function getFilteredPosts() {

}

async function getPosts(endpoint: string, area_code: number, category?: string, order?: string ): Promise<AxiosResponse<any, any>> {
   try {
      return await client.get(endpoint, { params: { area: area_code,  category: category, order: order} });
   } catch (error) {
      return error as Promise<AxiosResponse<any, any>>;
   }
}




