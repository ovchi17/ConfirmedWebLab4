import {Component, OnInit} from '@angular/core'

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})

export class RegistrationComponent {
  loginValue: string = '';
  passwordValue: string = '';
  isTextVisible: boolean = false;
  errorMessage: string = '';

  checkInput() {
    const containsRussian = /[а-яА-Я]/.test(this.loginValue) || /[а-яА-Я]/.test(this.passwordValue);
    const loginLen = this.loginValue.length > 10;
    this.isTextVisible = containsRussian || loginLen;
    if (containsRussian){
      this.errorMessage = 'Only english letters in login and password!';
    }
    if (loginLen){
      this.errorMessage = 'Max login length is 10';
    }
  }
}
