import { TestBed } from '@angular/core/testing';

import { Evolucion } from './models/evolucion';

describe('Evolucion', () => {
  let service: Evolucion;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Evolucion);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
