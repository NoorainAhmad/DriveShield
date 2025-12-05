import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-insurance-history',
  standalone: false,
  
  templateUrl: './insurance-history.component.html',
  styleUrl: './insurance-history.component.css'
})
export class InsuranceHistoryComponent {

  VehicleNo: String = '';
  user:any;
  message: string='';
  isSuccess: boolean=false;
  deletionPerformed: boolean=false;

  constructor(private userService:UserService,private location: Location){}
  pages = [
    { name: 'Add Insurance', link: '/addInsurance' },
    { name: 'Search Insurance', link: '/searchInsurance' },
    { name: 'Search Insurance History', link: '/searchHistory' },
    { name: 'Update Password', link: '/updatePassword' }
  ];


  goBack(): void {
    this.location.back();
  }


  searchInsurance(){
    if(this.VehicleNo){
      this.userService.getInsurancehistory(this.VehicleNo).subscribe(
        (response) => {
          if (response) {
              this.user = response; // Assign the response to `user`
              this.message = '';
              this.isSuccess = false;
              this.deletionPerformed = false;
          } else {
              // Handle null response
              this.message = 'User not found. Please check the UserID.';
              this.isSuccess = false;
              this.user = null;
              this.deletionPerformed = false;
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

  /**
   * Check if the end date is before the current date
   * @param endDate - The end date of the insurance
   * @returns true if the end date is in the past, false otherwise
   */
  isInactive(endDate: string): boolean {
    const today = new Date();
    const end = new Date(endDate);
    return end < today; // Returns true if the end date is before today
  }
}


