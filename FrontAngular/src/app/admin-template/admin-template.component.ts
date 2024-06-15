import { Component } from '@angular/core';
import {HomeComponent} from "../home/home.component";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent {
  constructor(public authService:AuthService) {
  }

  logout() {
    this.authService.logout();
  }
}
