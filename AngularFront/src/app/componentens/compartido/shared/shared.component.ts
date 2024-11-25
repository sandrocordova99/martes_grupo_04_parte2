import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'app-shared',
  standalone: true,
  imports: [],
  templateUrl: './shared.component.html',
  styleUrl: './shared.component.css'
})
export class SharedComponent {
  constructor(private router : Router){}


  logout():void {
    
    localStorage.removeItem('user');
    this.router.navigate(['/LoginUsuario']);
  }

}
