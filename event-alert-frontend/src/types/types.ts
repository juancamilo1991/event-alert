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
    area: number
}

export type AuthHeaders = {
    'Content-Type': string,
    'Authorization': string
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
    message: string | undefined
}

export interface SuccessMessage {
    message: string | undefined;
}
export interface SearchAreaProps {
    displayError: (error: RequestError) => void,
    displayPosts: (posts: ChannelPost[]) => void,
}

export interface WriteProps {
    isLoggedIn: string;
    area: string;
    category: string;
    isDeleteInput: boolean;
  }
export interface SideNavProps {
    isActive: boolean,
    closeSideNav: () => void,
}

export type RegistrationData = LoginData & EmailData;
export type SideBarProps = SideNavProps & SearchAreaProps;
export type WritePostProps = WriteProps & SearchAreaProps;
export type SearchProps = SearchAreaProps & SideNavProps & WriteProps;

export function isRequestError(object: unknown): object is RequestError {
    return Object.prototype.hasOwnProperty.call(object, "myStatus")
        && Object.prototype.hasOwnProperty.call(object, "message");
}

