import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './layout/base/base.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NutritionPlansComponent } from './components/nutritional-management/nutrition-plans/nutrition-plans.component';
import { WorkoutsComponent } from './components/workout-management/workouts/workouts.component';
import { ProgressComponent } from './components/reports/progress/progress.component';
import { UsersComponent } from './components/user-management/users/users.component';
import { AddWorkoutsComponent } from './components/workout-management/add-workouts/add-workouts.component';
import { EditWorkoutsComponent } from './components/workout-management/edit-workouts/edit-workouts.component';
import { AddNutritionComponent } from './components/nutritional-management/add-nutrition/add-nutrition.component';
import { EditNutritionComponent } from './components/nutritional-management/edit-nutrition/edit-nutrition.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },

      //Users list
      { path: 'user', component: UsersComponent },

      //Workout list
      { path: 'workout', component: WorkoutsComponent },

      { path: 'add-workout', component: AddWorkoutsComponent },

      // { path: 'edit-workout/:id', component: EditWorkoutsComponent}, 


      //nutrition
      { path: 'nutrition', component: NutritionPlansComponent },
      {
        path: 'add-nutrition', component: AddNutritionComponent
      },
      {
        path: 'edit-nutrition/:id', component: EditNutritionComponent
      },

      //Reports
      { path: 'progress', component: ProgressComponent },

      //Settings
      // { path: 'settings', component: SettingsComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
