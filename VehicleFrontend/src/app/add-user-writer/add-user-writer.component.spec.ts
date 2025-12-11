import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserWriterComponent } from './add-user-writer.component';

describe('AddUserWriterComponent', () => {
  let component: AddUserWriterComponent;
  let fixture: ComponentFixture<AddUserWriterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddUserWriterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUserWriterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
