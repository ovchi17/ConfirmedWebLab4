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
          this.isTextVisible = false;
          localStorage.setItem('username', this.dataLogin.username);
          localStorage.setItem('sessionId', response.headers.get('jwt'));
          console.log('Data sent successfully', response);
        },
        (error) => {
          this.errorMessage = 'Something went wrong, try again.';
          this.isTextVisible = true;
        }
    );
  }

}
