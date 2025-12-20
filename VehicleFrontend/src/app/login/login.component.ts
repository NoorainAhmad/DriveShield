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
    // Clear any existing tokens on login page load
    this.loginService.logout();
  }

  onSubmit(): void {
    // Use new JWT login
    this.loginService.jwtLogin(this.username, this.password).subscribe(
      (response: any) => {
        console.log('JWT Login response:', response);

        if (response.success && response.token) {
          // Store JWT token
          this.loginService.saveToken(response.token);

          // Store user data
          this.loginService.saveUserData(
            response.username,
            response.role,
            response.underwriterId
          );

          // Also store in sessionStorage for backward compatibility
          sessionStorage?.setItem('username', response.underwriterId);
          sessionStorage?.setItem('user', response.username);

          // Navigate based on role
          if (response.role === 'admin') {
            this.router.navigate(['/adminPanel']);
          } else {
            this.router.navigate(['/userWriter']);
          }
        } else {
          // Login failed
          this.message = response.message || 'Invalid username or password';
        }
      },
      (error) => {
        console.error('Login error:', error);
        if (error.status === 401) {
          this.message = 'Invalid username or password';
        } else {
          this.message = 'An error occurred during login. Please try again.';
        }
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

