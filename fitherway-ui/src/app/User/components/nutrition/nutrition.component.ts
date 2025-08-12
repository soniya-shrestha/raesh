import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-nutrition',
  templateUrl: './nutrition.component.html',
  styleUrl: './nutrition.component.scss'
})
export class NutritionComponent implements OnInit {
 nutritionPlans: any[] = [];

  // You can set default values or get these dynamically from user inputs/selectors
  selectedGoalType: string = 'Lose Weight';  
  selectedBmiLevel: string = 'NORMAL';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getNutrition();
  }

  getNutrition(): void {
    this.userService.getUserNutrition(this.selectedGoalType, this.selectedBmiLevel).subscribe({
      next: (res) => {
        this.nutritionPlans = Array.isArray(res) ? res : [];
      },
      error: (err) => {
        console.error('Error fetching nutrition:', err);
      }
    });
  }
}