import { Button } from "@mui/material";
import { useEffect } from "react";
import { useLocalStorage } from "./utils/useLocalState"


interface LogOutProps {
    logout: (jwt: string) => void;
}

export default function Logout(props: LogOutProps) {

    
    function removeJwt(jwt: string) {
        return props.logout(jwt);
    }

    return (
        <Button variant="contained" color="error" onClick={() => removeJwt("")} >
            Logout
        </Button>
    )
}