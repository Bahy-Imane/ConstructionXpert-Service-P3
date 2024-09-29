import { Routes } from '@angular/router';

import {TaskeListComponent} from "./component/task/taske-list/taske-list.component";
import {TaskeFormComponent} from "./component/task/taske-form/taske-form.component";
import {ResourceListComponent} from "./component/resource/resource-list/resource-list.component";
import {ResourceFormComponent} from "./component/resource/resource-form/resource-form.component";

import {HomeComponent} from "./component/home/home.component";
import {LoginComponent} from "./component/login/login.component";
import {Role} from "./core/enums/role";
import {RoleGuard} from "./core/services/role-guard";
import {AuthGuard} from "./core/services/auth-guard";
import {CustomerDashboardComponent} from "./component/customer-dashboard/customer-dashboard.component";

import {SignUpComponent} from "./component/sign-up/sign-up.component";
import {ProjectListComponent} from "./component/project/project-list/project-list.component";
import {ProjectFormComponent} from "./component/project/project-form/project-form.component";
import {ResourceTestComponent} from "./component/resource/resource-test/resource-test.component";
import {AdminDashboardComponent} from "./component/admin-Dashbord/admin-Dashboard.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";


export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent},
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: Role.ADMIN },
    children:[
      {path: 'dashboard' , component : DashboardComponent},
      {path:'projects', component:ProjectListComponent},
      {path:'add', component:ProjectFormComponent},
      {path:'edit/:id', component:ProjectFormComponent},


      {path:'tasks', component:TaskeListComponent},
      {path:'addTask', component:TaskeFormComponent},
      {path:'editTask/:id', component:TaskeFormComponent},
      //{path:'', component:TaskeListComponent},

      {path:'resources', component:ResourceListComponent},
      {path:'addResource', component:ResourceFormComponent},
      {path:'editResource/:id', component:ResourceFormComponent},
      //{path:'res', component:ResourceListComponent},


      {path:'test', component:ResourceTestComponent},
    ]
  },
  {
    path: 'customer-dashboard',
    component: CustomerDashboardComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: Role.CUSTOMER }
  },



];
