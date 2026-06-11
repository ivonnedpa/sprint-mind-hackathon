import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sprint-mind',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sprint-mind.html',
  styleUrl: './sprint-mind.css'
})
export class SprintMind {

  description = '';
  files: File[] = [];
  loading = false;
  generated = false;

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      this.files = Array.from(input.files);
    }
  }

  generate(): void {
    this.loading = true;
    this.generated = false;

    setTimeout(() => {
      this.loading = false;
      this.generated = true;
    }, 1500);
  }
}