import { TestBed } from '@angular/core/testing';

import { Estomatognaticoservice } from './estomatognaticoservice';

describe('Estomatognaticoservice', () => {
  let service: Estomatognaticoservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Estomatognaticoservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
