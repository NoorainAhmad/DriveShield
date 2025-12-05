import { Component } from '@angular/core';
import { Insurance } from '../insurance.model';
import { UserService } from '../user.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-add-insurance',
  standalone: false,

  templateUrl: './add-insurance.component.html',
  styleUrl: './add-insurance.component.css'
})
export class AddInsuranceComponent {
  insurance: Insurance = new Insurance();
  message: string = ''; // Message for success or failure
  isSuccess: boolean = false; // Success flag
  returnedData: any = null; // Data from the backend on success
  constructor(private userService: UserService,private location: Location) { }
  ResultId: string = '';

  StartDate :string =new Date().toISOString().split('T')[0];

  name: string | null = null;
  userId: string | null = null;
  insuranceUser: any;
  ngOnInit(): void {
    // StartDate :string =new Date().toISOString().split('T')[0];
    // Retrieve session data
    this.userId = sessionStorage?.getItem('username');
    this.name = sessionStorage?.getItem('user');

    if (!this.userId || !this.name) {
      alert('Session expired. Please log in again.');
    }
  }

  goBack(): void {
    this.location.back();
  }


  onSubmit(form: any): void {
    if (form.valid) {
      this.userService.addInsurance(this.insurance).subscribe(
        (response) => {
          const result = response; // Parse the response
          if (result === 'Failed to add insurance') {
            this.isSuccess = false;
            this.message = 'Failed to add insurance.';
          } else {
            this.isSuccess = true;
            this.message = `Insurance added successfully, Generated insuranceID: ${result}`;
            this.ResultId = response;
            this.userService.getInsuranceById(this.ResultId).subscribe(
              (data) => {
                this.insuranceUser = data;
              }
            )
          }
          form.resetForm(); // Reset form on submission
        },
        (error) => {
          console.error('Error adding insurance:', error);
          this.message = 'An unexpected error occurred. Please try again.';
          this.isSuccess = false;
        }
      );
    }
  }

  pages = [
    { name: 'Add Insurance', link: '/addInsurance' },
    { name: 'Search Insurance', link: '/searchInsurance' },
    { name: 'Search Insurance History', link: '/searchHistory' },
    { name: 'Update Password', link: '/updatePassword' }
  ];

  validateEndDate(): void {
    if (new Date(this.insurance.startDate) >= new Date(this.insurance.endDate)) {
      this.insurance.startDate = new Date();
      this.insurance.endDate = new Date();
      alert("End Date must be greater than Start Date.");
    }
  }

  // validateStartDate():void{
  //   if (new Date(this.insurance.startDate) < new Date() ) {
  //     this.insurance.startDate = new Date();
  //     alert("Start date must be current date");
  //   }
  // }
}
