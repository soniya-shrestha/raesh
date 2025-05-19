import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QuizComponent } from './Quiz/quiz/quiz.component';
import { StepCardComponent } from './Quiz/step-card/step-card.component';
import { ProgressIndicatorComponent } from './Quiz/progress-indicator/progress-indicator.component';
import { BaseComponent } from './home/base/base.component';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
