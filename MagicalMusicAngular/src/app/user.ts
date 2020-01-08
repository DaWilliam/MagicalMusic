import { Track } from './track';

export class User {

    id : number;
    name : string;
    password : string;
    email : string;
    image : string;

    tracks : Track[];
    imageFile : File;
    
}
