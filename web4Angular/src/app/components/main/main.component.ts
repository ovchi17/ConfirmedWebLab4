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
  values: { name: string }[] = [];
  selectedValueX: any;
  selectedValueY: string | number;
  selectedValueR: any;
  filteredValuesX: any[] = [];
  filteredValuesR: any[] = [];

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

    this.values = [
      { name: '-2' },
      { name: '-1.5' },
      { name: '-1' },
      { name: '-0.5' },
      { name: '0' },
      { name: '0.5' },
      { name: '1.0' },
      { name: '1.5' },
      { name: '2.0' },
    ];
    this.selectedValueY = 0;
  }

  ngOnInit() {
  }

  filterValX(event: AutoCompleteCompleteEvent) {
    let filtered: any[] = [];
    let query = event.query;

    for (let i = 0; i < this.values.length; i++) {
      let value = this.values[i];
      if (value.name.indexOf(query.toLowerCase()) == 0) {
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
      if (value.name.indexOf(query.toLowerCase()) == 0) {
        filtered.push(value);
      }
    }
    this.filteredValuesR = filtered;
  }
}
