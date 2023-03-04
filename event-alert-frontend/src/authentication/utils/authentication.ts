import { useLocalStorage } from "./useLocalState";

export function isAuthenticated(): boolean {
    const [jwt, setJwt] = useLocalStorage("jwt", "");

    return jwt !== "";
}