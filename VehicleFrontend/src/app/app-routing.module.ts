import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AddUserWriterComponent } from './add-user-writer/add-user-writer.component';
import { SearchUserComponent } from './search-user/search-user.component';
import { UserWriterComponent } from './user-writer/user-writer.component';
import { AddInsuranceComponent } from './add-insurance/add-insurance.component';
import { InsuranceSearchComponent } from './insurance-search/insurance-search.component';
import { InsuranceHistoryComponent } from './insurance-history/insurance-history.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'adminPanel', component: AdminPanelComponent },
  { path: 'userRegister', component: AddUserWriterComponent },
  { path: 'searchInsurance', component: InsuranceSearchComponent },
  { path: 'searchUser', component: SearchUserComponent },
  { path: 'userWriter', component: UserWriterComponent },
  { path: 'addInsurance', component: AddInsuranceComponent },
  { path: 'searchHistory', component: InsuranceHistoryComponent },
  { path: 'updatePassword', component: UpdatePasswordComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
