import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { BaseComponent } from './layout/base/base.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WorkoutsComponent } from './components/workouts/workouts.component';
import { ProgressComponent } from './components/progress/progress.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NutritionComponent } from './components/nutrition/nutrition.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { NgChartsModule } from 'ng2-charts';




@NgModule({
  declarations: [
    BaseComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    DashboardComponent,
    WorkoutsComponent,
    ProgressComponent,
    ProfileComponent,
    NutritionComponent,
    CalendarComponent,
 
   
    // SettingsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NgChartsModule
  ]
})
export class UserModule { }
