import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskeFormComponent } from './taske-form.component';

describe('TaskeFormComponent', () => {
  let component: TaskeFormComponent;
  let fixture: ComponentFixture<TaskeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskeFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TaskeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
