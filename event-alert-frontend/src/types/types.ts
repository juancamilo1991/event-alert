type Author = {
    username: string
}

interface ChannelPost {
    title: string,
    text: string,
    category: string,
    likesCount: number,
    publicationDate: string,
    user: Author,
}

interface LoginData {
    username: string,
    password: string,
}

interface EmailData {
    email: string,
}

type RegistrationData = LoginData & EmailData;

export type {ChannelPost, RegistrationData, LoginData}