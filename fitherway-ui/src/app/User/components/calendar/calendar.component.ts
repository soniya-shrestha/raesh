import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.scss'
})
export class CalendarComponent implements OnInit {
  currentDate = new Date();
  weeks: Array<Array<Date | null>> = [];
  monthName: string = '';
  year: number = 0;

  ngOnInit(): void {
    this.generateCalendar(this.currentDate);
  }

  generateCalendar(date: Date) {
    this.weeks = [];

    const year = date.getFullYear();
    const month = date.getMonth();

    this.monthName = date.toLocaleString('default', { month: 'long' });
    this.year = year;

    const firstDayOfMonth = new Date(year, month, 1);
    const startingDay = firstDayOfMonth.getDay(); // 0 (Sun) - 6 (Sat)
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    let week: Array<Date | null> = [];

    // Fill empty slots before 1st of the month
    for (let i = 0; i < startingDay; i++) {
      week.push(null);
    }

    // Fill actual days
    for (let day = 1; day <= daysInMonth; day++) {
      const thisDate = new Date(year, month, day);
      week.push(thisDate);

      if (week.length === 7) {
        this.weeks.push(week);
        week = [];
      }
    }

    // Push last week if incomplete
    if (week.length > 0) {
      while (week.length < 7) week.push(null);
      this.weeks.push(week);
    }
  }

  isToday(date: Date | null): boolean {
    if (!date) return false;
    const today = new Date();
    return date.getDate() === today.getDate() &&
           date.getMonth() === today.getMonth() &&
           date.getFullYear() === today.getFullYear();
  } 

    get flattenedDays(): (Date | null)[] {
    return this.weeks.flat();
  }
}