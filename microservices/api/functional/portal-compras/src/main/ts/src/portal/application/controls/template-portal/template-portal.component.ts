import {Component, Input} from '@angular/core';

@Component({
  selector: 'template-portal',
  templateUrl: 'template-portal.component.html',
  styleUrls: ['./template-portal.component.scss']
})
export class TemplatePortalComponent {

  /**
   *
   */
  @Input()
  public overflow: boolean;

}
