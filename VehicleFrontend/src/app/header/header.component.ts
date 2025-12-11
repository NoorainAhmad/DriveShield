import { Component, Input, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: false,

  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  encapsulation: ViewEncapsulation.None, // Disable encapsulation
})
export class HeaderComponent {
  @Input() pages: { name: string; link: string }[] = [];
  isLoggedIn: boolean = false;
  userName: string = 'User';
  userInitial: string = 'U';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Simulate user login state and details (Replace with actual logic)
    const user = sessionStorage.getItem('user');
    console.log(user);
    if (user) {
      this.isLoggedIn = true;
      this.userName = JSON.parse(user).name;
      this.userInitial = this.userName.charAt(0).toUpperCase();
    }
  }

  logout(): void {
    sessionStorage.clear();
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }
}
