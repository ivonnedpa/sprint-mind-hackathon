import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintMind } from './sprint-mind';

describe('SprintMind', () => {
  let component: SprintMind;
  let fixture: ComponentFixture<SprintMind>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintMind],
    }).compileComponents();

    fixture = TestBed.createComponent(SprintMind);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
