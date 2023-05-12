import { FormEvent } from "react";
import { makeApiRequest } from "../../api-client/blogPosts";
import { ChannelPost, AuthHeaders, RequestError, isRequestError } from "../../types/types";

function isTextInputNumber(area: string) {
    return (typeof area === "string" && !isNaN(area as any))
}

export async function makeRequest(
    endpoint: string,
    area: string,
    errorCallback: (error: RequestError) => void,
    bpostCallback: (posts: ChannelPost[]) => void,
    method?: string,
    categoryValue?: string,
    filterValue?: string,
    title?: string,
    text?: string,
    headers?: AuthHeaders
) {
    //get filtered posts
    if (isTextInputNumber(area)) {
        const convertedArea = parseInt(area);
        let result: ChannelPost[] | RequestError;
        if (method === 'POST') {
            result = await makeApiRequest(endpoint, convertedArea, method, categoryValue, '', title, text, headers);
        }
        else {
            result = await makeApiRequest(endpoint, convertedArea, method, categoryValue, filterValue);
        }
        if (isRequestError(result)) {
            return errorCallback(result);
        }
        bpostCallback(result);
    }
}