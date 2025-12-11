import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  standalone: false,

  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {


  username: string = '';
  password: string = '';
  username11: string = '';
  role: string = ''; // Admin or User
  message: string = ''; // Message to display login status
  showErrorModal: boolean = false;
  modalMessage: string = '';


  constructor(private loginService: UserService, private router: Router) { }
  ngOnInit(): void {
    history.pushState(null, '');
  }

  onSubmit(): void {
    const isAdmin = this.role === 'admin'; // Convert role to boolean: true for admin, false for user

    this.loginService.login(this.username, this.password, this.role).subscribe(
      (response: any) => {
        console.log(response);
        this.username11 = response.underwriterName;

        if (response.result === 'Login successful: Admin') {
          sessionStorage?.setItem('username', response.underwriterId);
          sessionStorage?.setItem('user', response.underwriterName);
          this.router.navigate(['/adminPanel']); // Navigate to Admin Panel
        } else if (response.result === 'Login successful: User') {
          sessionStorage?.setItem('username', response.underwriterId);
          sessionStorage?.setItem('user', response.underwriterName);
          this.router.navigate(['/userWriter']); // Navigate to User Panel
        } else if (response.result === 'Update your password (default password detected)') {
          this.openErrorModal(response.result);
        } else {
          this.message = response.result; // Show error message (already JSON-parsed)
        }
      },
      (error) => {
        console.error('Login error:', error);
        this.message = 'An error occurred during login. Please try again.';
      }
    );
  }

  // Open error modal
  openErrorModal(message: string) {
    this.modalMessage = message;
    this.showErrorModal = true;
  }

  // Close modals
  closeModal() {
    this.showErrorModal = false;
    console.log(this.username);
    console.log(this.username11);
    sessionStorage?.setItem('username', this.username);
    sessionStorage?.setItem('user', this.username11);
    this.router.navigate(['/updatePassword']);
  }
}
