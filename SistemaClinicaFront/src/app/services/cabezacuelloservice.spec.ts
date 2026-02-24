import { TestBed } from '@angular/core/testing';

import { Cabezacuelloservice } from './cabezacuelloservice';

describe('Cabezacuelloservice', () => {
  let service: Cabezacuelloservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Cabezacuelloservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
