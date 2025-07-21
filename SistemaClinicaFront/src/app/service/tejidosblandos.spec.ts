import { TestBed } from '@angular/core/testing';

import { Tejidosblandos } from './tejidosblandos';

describe('Tejidosblandos', () => {
  let service: Tejidosblandos;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Tejidosblandos);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
