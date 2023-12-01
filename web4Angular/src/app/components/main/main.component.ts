import { Component, OnInit } from '@angular/core';
import {DataService} from "../../data.service";
import {ElementService} from "../../element.service";
import {Data} from "../../data";
import {Element} from "../../element";

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
  filteredValuesX: any[] = [];
  filteredValuesR: any[] = [];
  isTextVisible = false;
  errorMessage = "";
  data: Data = new Data();
  element: Element = new Element();

  constructor(private dataService: DataService, private elementService: ElementService) {
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
    const valueX = parseFloat(this.element.x);
    const valueY = parseFloat(this.element.y);
    const valueR = parseFloat(this.element.r);
    if (this.element.x.length > 10 || this.element.y.length > 10 || this.element.r.length > 10){
      this.errorMessage = "Max length is 10!";
      this.isTextVisible = true;
    }else if((isNaN(valueX) || valueX < -2 || valueX > 2) && this.element.x != ""){
      this.errorMessage = "Something wrong with X!";
      this.isTextVisible = true;
    }else if((isNaN(valueY) || valueY < -5 || valueY > 3) && this.element.y != ""){
      this.errorMessage = "Something wrong with Y!";
      this.isTextVisible = true;
    }else if((isNaN(valueR) || valueR < -2 || valueR > 2) && this.element.r != ""){
      this.errorMessage = "Something wrong with R!";
      this.isTextVisible = true;
    }else {
      this.isTextVisible = false;
    }
  }

  clearModel(){
    this.modelList.splice(0, this.modelList.length);
  }
  onSubmit(){
    if (!this.isTextVisible){
      console.log(this.element);
      this.elementService.addElement(this.element);
    }else{
      console.log("error");
    }
  }

}
