import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-insurance-search',
  standalone: false,
  
  templateUrl: './insurance-search.component.html',
  styleUrl: './insurance-search.component.css'
})
export class InsuranceSearchComponent {

  pages = [
    { name: 'Add Insurance', link: '/addInsurance' },
    { name: 'Search Insurance', link: '/searchInsurance' },
    { name: 'Search Insurance History', link: '/searchHistory' },
    { name: 'Update Password', link: '/updatePassword' }
  ];
  VehicleNo: String = '';
  user:any;
  message: string='';
  isSuccess: boolean=false;

  constructor(private userService:UserService,private location: Location){}

  goBack(): void {
    this.location.back();
  }


  searchUser(){
    if(this.VehicleNo){
      this.userService.getInsuranceById(this.VehicleNo).subscribe(
        (response) => {
          if (response) {
              this.user = response; // Assign the response to `user`
              this.message = '';
              this.isSuccess = false;
          } else {
              // Handle null response
              this.message = 'User not found. Please check the UserID.';
              this.isSuccess = false;
              this.user = null;
          }
      },
        (error)=>{
          console.error('Error fetching user data :', error );
        }
      );
    }else{
      alert('Please enter a valid User Id');
    }
  }
}
