export interface NutritionPlan {
  id?: number;
  title: string;
  description: string;
  type: string;
  imageUrl?: string;
  caloriesPerDay: number;
  status: boolean;
  bmiLevel: 'UNDERWEIGHT' | 'NORMAL' | 'OVERWEIGHT' | 'OBESE';
  goalType: string;
  createdBy?: string;
  createdAt?: string;
}


