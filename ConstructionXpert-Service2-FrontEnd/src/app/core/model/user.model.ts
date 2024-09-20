import {Role} from "../enums/role";

export class User {
  userId !: number;
  fullName !: string;
  username !: string;
  password !: string;
  email !: string;
  role !: Role;
}
