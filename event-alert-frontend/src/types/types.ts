type Author = {
    username: string
}

export interface ChannelPost {
    title: string,
    text: string,
    category: string,
    likesCount: number,
    publicationDate: string,
    user: Author,
}

export interface LoginData {
    username: string,
    password: string,
}

interface EmailData {
    email: string,
}

export interface RequestError {
    myStatus: number | undefined,
    message: number | undefined
}

export function isRequestError(object: unknown): object is RequestError {
    return Object.prototype.hasOwnProperty.call(object, "myStatus")
        && Object.prototype.hasOwnProperty.call(object, "message");
}

export type RegistrationData = LoginData & EmailData;