import { Component } from '@angular/core';

@Component({
  selector: 'app-user-writer',
  standalone: false,
  
  templateUrl: './user-writer.component.html',
  styleUrl: './user-writer.component.css'
})
export class UserWriterComponent {
  pages = [
    { name: 'Add Insurance', link: '/addInsurance' },
    { name: 'Search Insurance', link: '/searchInsurance' },
    { name: 'Search Insurance History', link: '/searchHistory' },
    { name: 'Update Password', link: '/updatePassword' }
  ];
}
