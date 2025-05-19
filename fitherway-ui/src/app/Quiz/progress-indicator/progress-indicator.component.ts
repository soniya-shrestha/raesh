import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-progress-indicator',
  templateUrl: './progress-indicator.component.html',
  styleUrl: './progress-indicator.component.scss'
})
export class ProgressIndicatorComponent {

  @Input() step = 0;
  @Input() totalSteps = 0;
}
