import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../../service/admin.service';
import { NutritionPlan } from '../../../model/nutrition-plan';

@Component({
  selector: 'app-nutrition-plans',
  templateUrl: './nutrition-plans.component.html',
  styleUrl: './nutrition-plans.component.scss'
})
export class NutritionPlansComponent implements OnInit {
  plans: NutritionPlan[] = []; 
  selectedPlan: NutritionPlan | null = null;

  constructor(private service: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.service.getAll().subscribe({
      next: (response) => {
      console.log('API Response:', response); // (Optional) to debug
      this.plans = response;   // ✅ Fix: assign only the array
    },
    error: (err) => {
      console.error('Error loading workouts:', err);
    }
  });
  } 

  toggleMenu(plan: NutritionPlan) {
  this.selectedPlan = this.selectedPlan === plan ? null : plan;
} 

addPlan(){
  this.router.navigate(['/admin/add-nutrition']);
}

  edit(id: number) {
    this.router.navigate(['/admin/edit-nutrition', id]);
  }

  delete(id: number) {
    if (confirm('Are you sure?')) {
      this.service.delete(id).subscribe(() => {
        this.plans = this.plans.filter(p => p.id !== id);
      });
    }
  }
}
