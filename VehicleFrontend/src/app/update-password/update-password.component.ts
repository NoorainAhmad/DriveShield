import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-password',
  standalone: false,

  templateUrl: './update-password.component.html',
  styleUrl: './update-password.component.css'
})
export class UpdatePasswordComponent implements OnInit {
  updatePasswordForm: FormGroup;
  name: string | null = null;
  userId: string | null = null;
  showSuccessModal: boolean = false;
  showErrorModal: boolean = false;
  modalMessage: string = '';

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router,private location: Location) {
    // Initialize the form with validators
    this.updatePasswordForm = this.fb.group({
      oldPassword: ['', [Validators.required, Validators.minLength(6)]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmNewPassword: ['', Validators.required],
    });
  }


  goBack(): void {
    this.location.back();
  }



  pages = [
    { name: 'Add Insurance', link: '/addInsurance' },
    { name: 'Search Insurance', link: '/searchInsurance' },
    { name: 'Search Insurance History', link: '/searchHistory' },
    { name: 'Update Password', link: '/updatePassword' }
  ];

  ngOnInit() {
    // Retrieve session data
    this.userId = sessionStorage.getItem('username');
    this.name = sessionStorage.getItem('user');
    console.log(this.userId);
    console.log(this.name);
    history.pushState(null, '');
    if (!this.userId || !this.name) {
      this.openErrorModal('Session expired. Please log in again.');
    }
  }

  // Submit form and update password
  onSubmit() {
    if (this.updatePasswordForm.invalid) {
      this.openErrorModal('Please fill in all fields correctly.');
      return;
    }

    const { oldPassword, newPassword, confirmNewPassword } =
      this.updatePasswordForm.value;

    if (newPassword !== confirmNewPassword) {
      this.openErrorModal('New passwords do not match.');
      return;
    }

    const payload = {
      underwriterId: this.userId!,
      address: oldPassword,
      password: newPassword,
    };

    this.userService.updatePassword(payload).subscribe(
      (response) => {
        if (response === "Password Updated Successfully") {
          this.openSuccessModal(response);
        } else if (response === "Current Password You Entered is Incorrect") {
          this.openErrorModal(response);
        } else {
          this.openErrorModal(response);
        }

      },
      (error) => {
        this.openErrorModal(error.error.message || 'Failed to update password.');
      }
    );
  }

  // Open success modal
  openSuccessModal(message: string) {
    this.modalMessage = message;
    this.showSuccessModal = true;
  }

  // Open error modal
  openErrorModal(message: string) {
    this.modalMessage = message;
    this.showErrorModal = true;
  }

  // Close modals
  closeModal() {
    if (this.showSuccessModal == true) {
      this.showSuccessModal = false;
      sessionStorage.clear();
      this.updatePasswordForm.reset();
      this.router.navigate(['/login']);
    } else {
      this.showErrorModal = false;
    }
  }
}
