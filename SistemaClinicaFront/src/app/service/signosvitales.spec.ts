import { TestBed } from '@angular/core/testing';

import { Signosvitales } from './signosvitales';

describe('Signosvitales', () => {
  let service: Signosvitales;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Signosvitales);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
