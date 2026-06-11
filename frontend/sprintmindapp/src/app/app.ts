import { Component } from '@angular/core';
import { SprintMind } from './sprint-mind/sprint-mind';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SprintMind],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {}