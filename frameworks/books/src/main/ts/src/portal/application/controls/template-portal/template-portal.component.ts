import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'template-portal',
    templateUrl: 'template-portal.component.html',
    styleUrls: ['./template-portal.component.scss']
})
export class TemplatePortalComponent implements OnInit{


    @Input()
    public overflow: boolean;
    /**
     * 
     * @param dialogRef 
     * @param data 
     */
    constructor(
    ) {
        
    }

    ngOnInit(): void {
    }

}
