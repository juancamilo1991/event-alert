import { useState, useEffect } from "react";
import { ChannelPost, RequestError, SearchAreaProps } from "../types/types";

export function useHandleApiResponse(props: SearchAreaProps) {

    const [incomingPosts, setIncomingPosts] = useState<ChannelPost[]>([]);

    useEffect(() => {
        incomingPosts !== undefined ? props.displayPosts(incomingPosts) : null;
      }, [incomingPosts])

      function handleIncomingPosts(result: ChannelPost[]): void {
          setIncomingPosts(result);
        }

      return [handleIncomingPosts]
}

