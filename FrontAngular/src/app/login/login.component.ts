import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "express";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  public loginForm!: FormGroup;

  constructor(private fb :FormBuilder,
              private authService : AuthService ,private router:Router) {
  }

ngOnInit() {
    this.loginForm=this.fb.group({
      username:this.fb.control(''),
    pasword : this.fb.control('')
    })
}

  login() {
    let username =this.loginForm.value.username;
    let password=this.loginForm.value.password;
    let auth:boolean=this.authService.login(username,password);
    if(auth==true){

    }
  }
}
