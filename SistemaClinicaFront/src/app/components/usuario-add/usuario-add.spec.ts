import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioAdd } from './usuario-add';

describe('UsuarioAdd', () => {
  let component: UsuarioAdd;
  let fixture: ComponentFixture<UsuarioAdd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsuarioAdd]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioAdd);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
