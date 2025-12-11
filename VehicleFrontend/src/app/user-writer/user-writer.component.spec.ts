import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserWriterComponent } from './user-writer.component';

describe('UserWriterComponent', () => {
  let component: UserWriterComponent;
  let fixture: ComponentFixture<UserWriterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserWriterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserWriterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
