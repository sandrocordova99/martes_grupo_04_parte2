import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet ,Router} from '@angular/router';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [CommonModule,RouterOutlet,RouterModule,FormsModule],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent {

  constructor(private router : Router){}


  logout():void {
    localStorage.removeItem('user');
    this.router.navigate(['/LoginUsuario']);
  }

}
