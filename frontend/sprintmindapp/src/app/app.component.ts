import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [],
  template: `
    <main style="font-family: Arial, sans-serif; padding: 2rem;">
      <h1>Angular + Spring Boot</h1>
      <button (click)="loadMessage()">Call API</button>
      <p>{{ message }}</p>
    </main>
  `
})
export class AppComponent implements OnInit {
  message = 'Click the button to call the backend.';
  constructor(private http: HttpClient) {}
  ngOnInit() {}
  loadMessage() {
    this.http.get<{ message: string }>('/api/hello').subscribe({
      next: (res) => this.message = res.message,
      error: () => this.message = 'Request failed'
    });
  }
}