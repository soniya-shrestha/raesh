import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { BaseComponent } from './layout/base/base.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProgressComponent } from './components/reports/progress/progress.component';
import { WorkoutsComponent } from './components/workout-management/workouts/workouts.component';
import { AddWorkoutsComponent } from './components/workout-management/add-workouts/add-workouts.component';
import { EditWorkoutsComponent } from './components/workout-management/edit-workouts/edit-workouts.component';
import { AddNutritionComponent } from './components/nutritional-management/add-nutrition/add-nutrition.component';
import { NutritionPlansComponent } from './components/nutritional-management/nutrition-plans/nutrition-plans.component';
import { EditNutritionComponent } from './components/nutritional-management/edit-nutrition/edit-nutrition.component';
import { UsersComponent } from './components/user-management/users/users.component';
import { NgChartsModule } from 'ng2-charts';




@NgModule({
  declarations: [
    BaseComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    DashboardComponent,
    ProgressComponent,
    WorkoutsComponent,
    AddWorkoutsComponent,
    EditWorkoutsComponent,
    AddNutritionComponent,
   NutritionPlansComponent,
   EditNutritionComponent,
   UsersComponent
   
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule, 
    NgChartsModule,
  ]
})
export class AdminModule { }
