import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-search-user',
  standalone: false,
  templateUrl: './search-user.component.html',
  styleUrl: './search-user.component.css'

})
export class SearchUserComponent {

  underwriterId: String = '';
  user:any;
  message: string='';
  isSuccess: boolean=false;
  deletionPerformed: boolean=false;

  constructor(private userService:UserService,private location: Location){}

  pages = [
    { name: 'Add Registration', link: '/userRegister' },
    { name: 'Delete UserWriter', link: '/searchUser' },
    { name: 'Update Password', link: '/updatePassword' }
  ];

  deleteUser(underwriterId: string): void {
    if (confirm(`Are you sure you want to delete the user with ID ${underwriterId}?`)) {
        this.userService.deleteUser(underwriterId).subscribe(
            (response) => {
                if (response === true) {
                  this.message = 'User deleted successfully!';
                  this.isSuccess = true;
                  this.deletionPerformed = true;
                    
                } else if(response==null) {
                    this.message = 'Failed to delete the user.';
                    this.isSuccess = false;
                }
            },
            (error) => {
                console.error('Error deleting user:', error);
                this.message = 'Failed to delete the user.';
                this.isSuccess = false;
            }
        );
    }
  }

  goBack(): void {
    this.location.back();
  }


  searchUser(){
    if(this.underwriterId){
      this.userService.getUserById(this.underwriterId).subscribe(
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
}
