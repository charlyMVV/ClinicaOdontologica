import { TestBed } from '@angular/core/testing';

import { Diagnosticotratamientoservice } from './diagnosticotratamientoservice';

describe('Diagnosticotratamientoservice', () => {
  let service: Diagnosticotratamientoservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Diagnosticotratamientoservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
