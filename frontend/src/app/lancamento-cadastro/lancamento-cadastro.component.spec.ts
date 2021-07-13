import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LancamentoCadastroComponent } from './lancamento-cadastro.component';

describe('LancamentoCadastroComponent', () => {
  let component: LancamentoCadastroComponent;
  let fixture: ComponentFixture<LancamentoCadastroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LancamentoCadastroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LancamentoCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
