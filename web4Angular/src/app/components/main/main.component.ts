import { Component, OnInit } from '@angular/core';

interface AutoCompleteCompleteEvent {
  originalEvent: Event;
  query: string;
}

interface MyModel {
  result: string;
  x: string;
  y: string;
  r: string;
  time: string;
  scriptTime: string;
}

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  modelList: MyModel[] = [];
  values: string[] = [];
  // @ts-ignore
  selectedValueX: string = "";
  // @ts-ignore
  selectedValueY: string = "";
  // @ts-ignore
  selectedValueR: string = "";
  filteredValuesX: any[] = [];
  filteredValuesR: any[] = [];
  isTextVisible = false;
  errorMessage = "";

  constructor() {
    this.modelList = [
      {
        result: "Result 1",
        x: "X 1",
        y: "Y 1",
        r: "R 1",
        time: "Time 1",
        scriptTime: "Script Time 1",
      },
      {
        result: "Result 2",
        x: "X 2",
        y: "Y 2",
        r: "R 2",
        time: "Time 2",
        scriptTime: "Script Time 2",
      },
    ];

    this.values = ['-2', '-1.5', '-1', '-0.5', '0', '0.5', '1.0', '1.5', '2.0'];
  }

  ngOnInit() {
  }

  filterValX(event: AutoCompleteCompleteEvent) {
    let filtered: any[] = [];
    let query = event.query;

    for (let i = 0; i < this.values.length; i++) {
      let value = this.values[i];
      if (value.indexOf(query.toLowerCase()) == 0) {
        filtered.push(value);
      }
    }
    this.filteredValuesX = filtered;
  }

  filterValR(event: AutoCompleteCompleteEvent) {
    let filtered: any[] = [];
    let query = event.query;

    for (let i = 0; i < this.values.length; i++) {
      let value = this.values[i];
      if (value.indexOf(query.toLowerCase()) == 0) {
        filtered.push(value);
      }
    }
    this.filteredValuesR = filtered;
  }

  checkInput() {
    const valueX = parseFloat(this.selectedValueX);
    const valueY = parseFloat(this.selectedValueY);
    const valueR = parseFloat(this.selectedValueR);
    if (this.selectedValueX.length > 10 || this.selectedValueY.length > 10 || this.selectedValueR.length > 10){
      this.errorMessage = "Max length is 10!";
      this.isTextVisible = true;
    }else if((isNaN(valueX) || valueX < -2 || valueX > 2) && this.selectedValueX != ""){
      this.errorMessage = "Something wrong with X!";
      this.isTextVisible = true;
    }else if((isNaN(valueY) || valueY < -5 || valueY > 3) && this.selectedValueY != ""){
      this.errorMessage = "Something wrong with Y!";
      this.isTextVisible = true;
    }else if((isNaN(valueR) || valueR < -2 || valueR > 2) && this.selectedValueR != ""){
      this.errorMessage = "Something wrong with R!";
      this.isTextVisible = true;
    }else {
      this.isTextVisible = false;
    }
  }


  onSubmit(){}

}
