import { Component, OnInit } from '@angular/core';
import { SpinnerService } from '../spinner.service';
import { Subscription }   from 'rxjs';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent implements OnInit {

	subscription: Subscription;
	spinnerCount: number = 0;
	showSpinner: boolean = true;

 	constructor(private spinnerService: SpinnerService) { 
 		this.subscription = spinnerService.spinnerSubject$.subscribe(
      		showSpinner => {
      			if(showSpinner) {
      				this.spinnerCount = this.spinnerCount++;
      				this.showSpinner = true;
      			} else {
      				if(this.spinnerCount > 0) {
      					this.spinnerCount = this.spinnerCount--;	
      				} else {
      					this.showSpinner = false;
      				}
      				
      			}
    	});
 	}

  	ngOnInit() {

  	}

}
