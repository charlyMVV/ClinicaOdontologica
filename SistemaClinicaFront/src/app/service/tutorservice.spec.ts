import { TestBed } from '@angular/core/testing';

import { Tutorservice } from './tutorservice';

describe('Tutorservice', () => {
  let service: Tutorservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Tutorservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
