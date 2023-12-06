import {Component, OnInit} from '@angular/core'
import {Data} from "../../data";
import {DataService} from "../../data.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})

export class RegistrationComponent {
  loginValue: string = '';
  passwordValue: string = '';
  dataRegister: Data = new Data();
  isTextVisible: boolean = false;
  errorMessage: string = '';

  constructor(private dataService: DataService) {
  }
  checkInput() {
    const containsRussian = /[а-яА-Я]/.test(<string>this.dataRegister.username) || /[а-яА-Я]/.test(<string>this.dataRegister.password);
    // @ts-ignore
    const loginLen = this.dataRegister.username.length > 10;
    this.isTextVisible = containsRussian || loginLen;

    if (containsRussian){
      this.errorMessage = 'Only english letters in login and password!';
    }
    if (loginLen){
      this.errorMessage = 'Max login length is 10';
    }
  }

  tryRegistration(){
    console.log(this.dataRegister);
    this.dataService.registerUser(this.dataRegister).subscribe(
      (response) => {
        this.isTextVisible = false;
        localStorage.setItem('username', this.dataRegister.username)
        localStorage.setItem('sessionId', response.jwt);
        console.log(localStorage.getItem("sessionId"));
        console.log('Data sent successfully', response);
      },
      (error) => {
        this.errorMessage = 'Something went wrong, try again.';
        this.isTextVisible = true;
      }
    );
  }

}
