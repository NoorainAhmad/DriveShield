import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { UserWriter } from '../user-writer.model';
import { jsPDF } from 'jspdf';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-user-writer',
  standalone: false,
  templateUrl: './add-user-writer.component.html',
  styleUrl: './add-user-writer.component.css',
})
export class AddUserWriterComponent {
  userwriter: UserWriter = new UserWriter();
  responseMessage: string = '';
  userwriterResponse: UserWriter | null = null;
  isSuccess: boolean = false;
  maxdob: Date = new Date();
  ngOnInIt(){
    const today = new Date();
    this.maxdob = new Date(
      today.getFullYear() - 18,
      today.getMonth(),
      today.getDate()
    );
  }
  constructor(private userService: UserService, private router: Router,private location: Location) {
    

  }
  pages = [
    { name: 'Add Registration', link: '/userRegister' },
    { name: 'Delete UserWriter', link: '/searchUser' },
    { name: 'Update Password', link: '/updatePassword' }
  ];

  onSubmitUserWriterForm(form: any): void {
    if (form.valid) {
      this.userService.registerUser(this.userwriter).subscribe(
        (data) => {
          this.responseMessage = 'User registered successfully!';
          this.userwriterResponse = data; // Store the user details
          this.isSuccess = true;
          form.resetForm();
          this.userwriter = new UserWriter(); // Reset the form
        },
        (error) => {
          this.responseMessage = 'Failed to register the user.';
          this.isSuccess = false;
          this.userwriterResponse = null;
          console.error('Error registering user:', error);
        }
      );
    }
  }

  goBack(): void {
    this.location.back();
  }

  // Generate PDF for Registered User Details
  downloadUserDetailsPDF(): void {
    if (!this.userwriterResponse) {
      alert('No user details available to download.');
      return;
    }

    const doc = new jsPDF();
    const { underwriterId, name, date_of_birth, gender, address, dateOfJoin, isAdmin } = this.userwriterResponse;

    doc.text('Registered User Details', 10, 10);
    doc.text(`ID: ${underwriterId}`, 10, 20);
    doc.text(`Name: ${name}`, 10, 30);
    doc.text(`Date of Birth: ${date_of_birth}`, 10, 40);
    doc.text(`Gender: ${gender}`, 10, 50);
    doc.text(`Address: ${address}`, 10, 60);
    doc.text(`Date of Joining: ${dateOfJoin}`, 10, 70);
    doc.text(`Is Admin: ${isAdmin ? 'Yes' : 'No'}`, 10, 80);
    doc.save('Registered_User_Details.pdf');
  }
}