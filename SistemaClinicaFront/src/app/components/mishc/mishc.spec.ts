import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Mishc } from './mishc';

describe('Mishc', () => {
  let component: Mishc;
  let fixture: ComponentFixture<Mishc>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Mishc]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Mishc);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
