
import {CustomerDto} from "../Dto/customer-dto";
import {User} from "../model/user.model";
import {AdminDto} from "../Dto/admin-dto";
import {LoginUserDto} from "../Dto/login-user-dto";
import {LoginResponse} from "../Dto/login-response-dto";
import {Role} from "../enums/role";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private baseUrl = 'http://localhost:8888/USER-SERVICE/api/auth';

  constructor(private http: HttpClient) { }


  CostumerSignUp(customerDTO: CustomerDto): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/signup`, customerDTO);
  }


  adminSignUp(adminDTO: AdminDto): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/add-admin`, adminDTO);
  }

  login(loginUserDto: LoginUserDto): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, loginUserDto);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getUserRole(): Role | null {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
      return payload.role as Role;
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
