import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

export interface Time {
  days: number;
  hours: number;
  minutes: number;
  seconds: number;
}

@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.scss'],
})
export class CounterComponent implements OnInit {

  timeDistance: Time = {
    days: 0,
    hours: 0,
    minutes: 0,
    seconds: 0
  };
  eventDate = new Date('12/10/2020').getTime();

  constructor() { }

  ngOnInit(): void {
    let i = 0;
    setInterval(() => {
      const currentTime = new Date();
      this.timeDistance = this.formatDHMS((this.eventDate - currentTime.getTime()) / 1000);
    }, 1000)
  }


  formatDHMS(t): Time {
    let days, hours, minutes, seconds;
    days = Math.floor(t / 86400);
    t -= days * 86400;
    hours = Math.floor(t / 3600) % 24;
    t -= hours * 3600;
    minutes = Math.floor(t / 60) % 60;
    t -= minutes * 60;
    seconds = Math.ceil(t % 60);
    return {
      days,
      hours,
      minutes,
      seconds,
    };
  }

}
