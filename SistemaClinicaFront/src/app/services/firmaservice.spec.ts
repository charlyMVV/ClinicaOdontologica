import { TestBed } from '@angular/core/testing';

import { Firmaservice } from './firmaservice';

describe('Firmaservice', () => {
  let service: Firmaservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Firmaservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
