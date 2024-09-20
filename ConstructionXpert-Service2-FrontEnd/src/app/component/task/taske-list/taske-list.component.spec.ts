import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskeListComponent } from './taske-list.component';

describe('TaskeListComponent', () => {
  let component: TaskeListComponent;
  let fixture: ComponentFixture<TaskeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskeListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TaskeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
