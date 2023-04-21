import { FormEvent } from "react";
import { getPosts } from "../../api-client/blogPosts";
import { ChannelPost, RequestError, isRequestError } from "../../types/types";

function isTextInputNumber(area: string) {
    return (typeof area === "string" && !isNaN(area as any))
}

export async function makeRequest(event: FormEvent<HTMLFormElement>,
    endpoint: string,
    area: string,
    errorCallback: (error: RequestError) => void,
    bpostCallback: (posts: ChannelPost[]) => void,
    categoryValue?: string,
    filterValue?: string,
) {
    event.preventDefault();

    //get filtered posts
    if (isTextInputNumber(area)) {
        const convertedArea = parseInt(area);
        const result = await getPosts(endpoint, convertedArea, categoryValue, filterValue);
        if (isRequestError(result)) {
            return errorCallback(result);
        }
        bpostCallback(result);
    }
}