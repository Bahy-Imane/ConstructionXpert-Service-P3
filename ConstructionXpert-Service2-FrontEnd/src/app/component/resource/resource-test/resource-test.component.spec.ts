import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceTestComponent } from './resource-test.component';

describe('ResourceTestComponent', () => {
  let component: ResourceTestComponent;
  let fixture: ComponentFixture<ResourceTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResourceTestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResourceTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
