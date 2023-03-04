import axios from "axios";

// axios instance with default configuration
const client = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: {'Content-Type': 'application/json'}
})

export {client};