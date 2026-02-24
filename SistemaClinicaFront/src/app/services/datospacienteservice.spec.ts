import { TestBed } from '@angular/core/testing';

import { Datospacienteservice } from './datospacienteservice';

describe('Datospacienteservice', () => {
  let service: Datospacienteservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Datospacienteservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
