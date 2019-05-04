import {Component, HostListener, OnInit} from '@angular/core';

@Component({
  selector: 'publicacoes-view',
  templateUrl: './publicacoes-view.component.html',
  styleUrls: ['./publicacoes-view.component.scss']
})
export class PublicacoesViewComponent implements OnInit {

  isPadding : any;

  constructor() { }

  ngOnInit() {
    this.activatePadding();
  }

  activatePadding() {
    if (window.innerWidth <= 1280) {
      this.isPadding = false;
    } else {
      this.isPadding = true;
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.activatePadding();
  }

}
