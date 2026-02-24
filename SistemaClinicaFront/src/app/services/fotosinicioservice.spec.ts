import { TestBed } from '@angular/core/testing';

import { Fotosinicioservice } from './fotosinicioservice';

describe('Fotosinicioservice', () => {
  let service: Fotosinicioservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Fotosinicioservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
