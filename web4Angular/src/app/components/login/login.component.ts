import {Component, OnInit} from '@angular/core'
import {Data} from "../../data";
import {DataService} from "../../data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  loginValue: string = '';
  passwordValue: string = '';
  dataLogin: Data = new Data();
  isTextVisible: boolean = false;
  errorMessage: string = '';

  constructor(private dataService: DataService) {
  }
  checkInput() {
    const containsRussian = /[а-яА-Я]/.test(<string>this.dataLogin.username) || /[а-яА-Я]/.test(<string>this.dataLogin.password);
    // @ts-ignore
    const loginLen = this.dataLogin.username.length > 10;
    this.isTextVisible = containsRussian || loginLen;
    if (containsRussian){
      this.errorMessage = 'Only english letters in login and password!';
    }
    if (loginLen){
      this.errorMessage = 'Max login length is 10';
    }
  }

  tryLogin(){
    console.log(this.dataLogin);
    this.dataService.loginUser(this.dataLogin).subscribe(
      (response) => {
        localStorage.setItem('username', this.loginValue) // в пасс закидываем ключ
        console.log('Data sent successfully', response);
      },
      (error) => {
        console.error('Error sending data', error);
      }
    );
  }

}
