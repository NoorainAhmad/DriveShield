import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-panel',
  standalone: false,
  
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent {


  adminUpdate():void{
    sessionStorage.setItem(
      "Click","clickUsingAdmin"
    )
    // this.router.navigate(['/adminPanel']); 

  }



  pages = [
    { name: 'Add Registration', link: '/userRegister' },
    { name: 'Delete UserWriter', link: '/searchUser' },
    { name: 'Update Password', link: '/updatePassword' }
  ];
}
