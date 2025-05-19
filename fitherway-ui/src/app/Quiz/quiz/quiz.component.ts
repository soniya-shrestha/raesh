import { Component } from '@angular/core';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent {

  step = 0;
  totalSteps = 13; 
 
 bmi = {
  weight: null as number | null,
  weightUnit: 'kg',
  height: null as number | null,
  heightFeet: null as number | null,
  heightInch: null as number | null,
  heightUnit: 'cm',
  result: null as number | null
};

  bmiLevel: string = '';
  userData: any = {};

  goals = [
    { label: 'Lose weight', emoji: '🔥' },
    { label: 'Build Muscle', emoji: '💪' },
    { label: 'General Fitness', emoji: '🧘‍♀️' }
  ];

  experience = [
    { label: 'Beginner', emoji: '🌱' },
    { label: 'Intermediate', emoji: '🌿' },
    { label: 'Advanced', emoji: '🌳' }
  ];

  ages = [
    { label: '18-29', emoji: '🧒' },
    { label: '30-39', emoji: '👩' },
    { label: '40-49', emoji: '👩‍🦳' },
    { label: '50+', emoji: '👵' }
  ];

  shapes = [
    { label: 'Slim', emoji: '' },
    { label: 'Average', emoji: '' },
    { label: 'Heavy', emoji: '' }
  ];

  dreams = [
    { label: 'Fit', emoji: '' },
    { label: 'Muscular', emoji: '' },
    { label: 'Bodybuilding', emoji: '' }
  ];

  exercises = [
    { label: 'Daily', emoji: '' },
    { label: 'Several times per week', emoji: '' },
    { label: 'Several times per month', emoji: '' },
    { label: 'In the past', emoji: '' },
    { label: 'Never', emoji: '' }
  ];

  bestShape = [
    { label: 'Less than a year ago', emoji: '' },
    { label: '1 to 3 years ago' },
    { label: 'More than 3 years ago' },
    { label: 'Never' }
  ];

  preferDays = [
    { label: '1 x per week' },
    { label: '2 x per week' },
    { label: '3 x per week' },
    { label: '4 x per week' },
    { label: '5 x per week' },
    { label: '6 x per week' }
  ];

  preferTimes = [
    { label: '30 min' },
    { label: '40 min' },
    { label: '60 min' }
  ];

  preferDayTimes = [
    { label: 'Morning', emoji: '🕖' },
    { label: 'Afternoon', emoji: '🕒' },
    { label: 'Evening', emoji: '🕕' },
    { label: 'At different times', emoji: '🕖 🕒 🕕' }
  ];

  equipments = [
    {
      label: 'Bodyweight Only',
      sublabel: 'Work out anywhere without equipment'
    },
    {
      label: 'Minimal Equipment',
      sublabel: 'Dumbbells, pull-up bar & resistance bands'
    },
    {
      label: 'Home Gym',
      sublabel: 'Barbell, squat rack, bench, dumbbells & pull-up bar'
    }
  ];

  nextStep(selection?: any) {
    switch (this.step) {
      case 0: this.userData.age = selection.label; break;
      case 1: this.userData.goal = selection.label; break;
      case 2: this.userData.shape = selection.label; break;
      case 3: this.userData.dream = selection.label; break;
      case 4: this.userData.experience = selection.label; break;
      case 5: this.userData.exercise = selection.label; break;
      case 6: this.userData.bestShape = selection.label; break;
      case 7: this.userData.preferDays = selection.label; break;
      case 8: this.userData.preferTimes = selection.label; break;
      case 9: this.userData.preferDayTimes = selection.label; break;
      case 10: this.userData.equipment = selection.label; break;
      // Step 11: weight (manual input)
      // Step 12: height (manual input)
    }
    this.step++;
  }

  prevStep() {
    if (this.step > 0) {
      this.step--;
    }
  }

 calculateBMI() {
  let weight = this.bmi.weight;
  let height: number | null = null;

  // Validate weight input
  if (weight == null) {
    alert('Please enter your weight.');
    return;
  }

  // Convert weight if in lb
  if (this.bmi.weightUnit === 'lb') {
    weight = weight * 0.453592;
  }

  // Convert and validate height
  if (this.bmi.heightUnit === 'cm') {
    if (this.bmi.height == null) {
      alert('Please enter your height in cm.');
      return;
    }
    height = this.bmi.height / 100;
  } else if (this.bmi.heightUnit === 'ft') {
    const ft = this.bmi.heightFeet || 0;
    const inch = this.bmi.heightInch || 0;

    if (ft === 0 && inch === 0) {
      alert('Please enter your height in ft/in.');
      return;
    }

    height = (ft * 12 + inch) * 0.0254;
  }

  // Final validation
  if (height === 0 || height == null) {
    alert('Height must be greater than zero.');
    return;
  }

  // Calculate BMI
  const bmiValue = weight! / (height * height);
  this.bmi.result = parseFloat(bmiValue.toFixed(1));
  this.userData.bmi = this.bmi.result;

  // Determine BMI category
  if (bmiValue < 18.5) {
    this.bmiLevel = 'underweight';
  } else if (bmiValue >= 18.5 && bmiValue < 24.9) {
    this.bmiLevel = 'healthy';
  } else if (bmiValue  >= 24.9 && bmiValue < 30) {
    this.bmiLevel = 'overweight';
  } else {
    this.bmiLevel = 'obese';
  }

  this.nextStep();
}


  submitForm() {
    console.log('User Data:', this.userData);
    alert('🎉 Thank you for completing the quiz!');
  }
}
