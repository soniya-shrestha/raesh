import { Component, OnInit } from '@angular/core';
import { QuizService } from '../service/quiz.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit{
 constructor(private quizService: QuizService, private route: Router) {}
step = 0;
  totalSteps = 14;

  goals: any[] = [];
  experience: any[] = [];
  ages: any[] = [];
  shapes: any[] = [];
  dreams: any[] = [];
  exercises: any[] = [];
  bestShape: any[] = [];
  preferDays: any[] = [];
  preferTimes: any[] = [];
  preferDayTimes: any[] = [];
  equipments: any[] = [];

  userData: any = {
    actualAge: '',
    goal: '',
    experience: '',
    bmi: '', 
    equipment:'',
    shape:'',
    dream:'',
    exercise:'',
    bestShape:'',
    preferDay:'',
    preferTime:'',
    preferDayTime:''  
  };

  bmi: any = {
    weight: '',
    weightUnit: 'kg',
    height: '',
    heightFeet: '',
    heightInch: '',
    heightUnit: 'cm',
    result: ''
  };

  bmiLevel: string = '';
  bmiMessage: string = '';
  showBmiResult: boolean = false;
  showBmiInfo: boolean = false;

  ngOnInit(): void {
    this.loadQuizOptions();
  }

  loadQuizOptions() {
    this.quizService.getGoals().subscribe(data => this.goals = data);
    this.quizService.getExperience().subscribe(data => this.experience = data);
    this.quizService.getAges().subscribe(data => this.ages = data);
    this.quizService.getShapes().subscribe(data => this.shapes = data);
    this.quizService.getDreams().subscribe(data => this.dreams = data);
    this.quizService.getExercises().subscribe(data => this.exercises = data);
    this.quizService.getBestShapes().subscribe(data => this.bestShape = data);
    this.quizService.getPreferDays().subscribe(data => this.preferDays = data);
    this.quizService.getPreferTimes().subscribe(data => this.preferTimes = data);
    this.quizService.getPreferDayTimes().subscribe(data => this.preferDayTimes = data);
    this.quizService.getEquipments().subscribe(data => this.equipments = data);
  }

  nextStep(value?: any) {
    if (this.step === 1) this.userData.goal = value?.label; 
    if (this.step === 2) this.userData.shape = value?.label; 
    if (this.step === 3) this.userData.dream = value?.label; 
    if (this.step === 4) this.userData.experience = value?.label;  
    if (this.step === 5) this.userData.exercise = value?.label; 
    if (this.step === 6) this.userData.bestShape = value?.label; 
    if (this.step === 7) this.userData.preferDay = value?.label; 
    if (this.step === 8) this.userData.preferTime = value?.label; 
    if (this.step === 9) this.userData.preferDayTime = value?.label;   
    if (this.step === 10) this.userData.equipment = value?.label; 

    this.step++;
  }

  prevStep() {
    this.step--;
  }

  isActualAgeValid(): boolean {
    const age = Number(this.userData.actualAge);
    return age >= 10 && age <= 99;
  }

  isWeightValid(): boolean {
    const weight = parseFloat(this.bmi.weight);
    return weight > 20 && weight < 300;
  }

  isHeightValid(): boolean {
    if (this.bmi.heightUnit === 'cm') {
      const height = parseFloat(this.bmi.height);
      return height >= 90 && height <= 250;
    } else {
      const feet = parseInt(this.bmi.heightFeet);
      const inch = parseInt(this.bmi.heightInch);
      return feet >= 3 && feet <= 8 && inch >= 0 && inch < 12;
    }
  }

  onInputFocus() {
    this.showBmiInfo = true;
  }

  onInputBlur() {
    this.showBmiInfo = false;
  }

  calculateBMI() {
    if (!this.isHeightValid()) return;

    const payload = {
      weight: this.bmi.weight,
      weightUnit: this.bmi.weightUnit,
      height: this.bmi.height,
      heightFeet: this.bmi.heightFeet,
      heightInch: this.bmi.heightInch,
      heightUnit: this.bmi.heightUnit
    };

    this.quizService.calculateBMI(payload).subscribe({
      next: (response) => {
        this.bmi.result = response.bmi;
        this.bmiLevel = response.level;
        this.bmiMessage = response.message;
        this.showBmiResult = true;
        this.userData.bmi = this.bmi.result;
        this.nextStep();
      },
      error: (err) => {
        alert('Failed to calculate BMI.');
        console.error(err);
      }
    });
  }

  getFormattedHeight(): string {
    return this.bmi.heightUnit === 'cm'
      ? `${this.bmi.height} cm`
      : `${this.bmi.heightFeet} ft ${this.bmi.heightInch} in`;
  }

  getBmiPosition(): number {
    const bmi = parseFloat(this.bmi.result);
    if (isNaN(bmi)) return 0;
    if (bmi < 18.5) return 15;
    if (bmi < 25) return 35;
    if (bmi < 30) return 65;
    return 90;
  }

  submitForm() {
    console.log('Submitting user data:', this.userData);
    this.quizService.submitQuizResponse(this.userData).subscribe({
      next: () => {
        this.route.navigate(['/auth/register']);
      },
      error: (err) => {
        alert('Something went wrong during submission.');
        console.error(err);
      }
    });
  }
}