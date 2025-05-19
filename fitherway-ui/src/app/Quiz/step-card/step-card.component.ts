import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-step-card',
  templateUrl: './step-card.component.html',
  styleUrl: './step-card.component.scss'
})
export class StepCardComponent {

//   @Input() stepData: any;
//   @Output() stepCompleted = new EventEmitter<any>();
//   selectedOption: string = '';
//   height: number = 0;
//   weight: number = 0;

//   submitStep() {
//     if (this.stepData.isForm) {
//       const bmi = this.weight / Math.pow(this.height / 100, 2);
//       this.stepCompleted.emit({ height: this.height, weight: this.weight, bmi: bmi.toFixed(2) });
//     } else {
//       this.stepCompleted.emit({ [this.stepData.title]: this.selectedOption });
//     }
//   }
}
