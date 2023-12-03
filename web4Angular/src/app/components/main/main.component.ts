import { Component, OnInit, ElementRef, Renderer2, ViewChild, AfterViewInit } from '@angular/core';
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
export class MainComponent implements OnInit, AfterViewInit {

  modelList: MyModel[] = [];
  values: string[] = [];
  filteredValuesX: any[] = [];
  filteredValuesR: any[] = [];
  isTextVisible = false;
  errorMessage = "";
  data: Data = new Data();
  element: Element = new Element();

  @ViewChild('svgElement') svgElement!: ElementRef<SVGSVGElement>;

  ngAfterViewInit(): void {
    this.setupSvgClickEvent();
  }

  private setupSvgClickEvent(): void {
    this.renderer.listen(this.svgElement.nativeElement, 'click', (event: MouseEvent) => {
      const R_button = this.element.r;
      const x = event.offsetX;
      const y = event.offsetY;
      const R = R_button;

      if (this.isTextVisible == false && R != "" && Number(R) > 0){
        const normalizedX = (((x - 200) * 2 * Number(R)) / 300).toFixed(2);
        const normalizedY = (((200 - y) * 2 * Number(R)) / 300).toFixed(2);

        const point = document.createElementNS('http://www.w3.org/2000/svg', 'circle');

        point.setAttribute('cx', String(x));
        point.setAttribute('cy', String(y));
        point.setAttribute('r', '3');
        point.setAttribute('class', 'graph-point');

        const checkStatus = this.checker(Number(normalizedX), Number(normalizedY), Number(R));

        if (checkStatus) {
          point.setAttribute('fill', 'white');
        } else {
          point.setAttribute('fill', '#e42575');
        }

        this.renderer.appendChild(this.svgElement.nativeElement, point);

        console.log(`x: ${x}, normX: ${normalizedX}`);
        console.log(`y: ${y}, normY: ${normalizedY}`);
        console.log(Number(R));
      }else{
        console.log("R problem");
      }
    });
  }

  private checker(x: number, y: number, r: number): boolean {
    let resultF = false;

    if (x <= 0 && y >= 0) {
      if (y <= r/2 && x >= -1 * r) {
        resultF = true;
      }
    }

    if (x <= 0 && y <= 0) {
      if (x >= -r && y >= -r && Number(x) + Number(y) >= -r) {
        resultF = true;
      }
    }

    if (x >= 0 && y >= 0) {
      if (x <= r / 2 && y <= r / 2 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2))) {
        resultF = true;
      }
    }

    return resultF;
  }
  constructor(private dataService: DataService, private elementService: ElementService, private renderer: Renderer2) {
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
      this.elementService.addElement(this.element).subscribe(
        (response) => {
          console.log('Data sent successfully', response);
        },
        (error) => {
          console.error('Error sending data', error);
        }
      );
    }else{
      console.log("error");
    }
  }

}
