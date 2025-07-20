import { TestBed } from '@angular/core/testing';

import { Nopatologicos } from './nopatologicosService';

describe('Nopatologicos', () => {
  let service: Nopatologicos;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Nopatologicos);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
