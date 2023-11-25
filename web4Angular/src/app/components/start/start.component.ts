import {Component, OnInit} from '@angular/core'

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css'
})

export class StartComponent implements OnInit{

  activeForm: 'login' | 'register';

  ngOnInit(): void {
    this.updateTime();
    setInterval(() => this.updateTime(), 9000);
  }
  constructor() {
    this.activeForm = 'login';
  }
  formActivation(formType: 'login' | 'register'): void {
    this.activeForm = formType;
  }

  updateTime(): void {
    const now: Date = new Date();
    const clock: HTMLElement | null = document.getElementById("clock");

    if (clock) {
      clock.innerHTML = now.toLocaleTimeString() + " " + now.toLocaleDateString();
    }
  }


}
