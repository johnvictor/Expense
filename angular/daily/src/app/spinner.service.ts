import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {
	private spinnerSubject = new Subject<boolean>();

  	constructor() { }

  	spinnerSubject$ = this.spinnerSubject.asObservable();

  	startSpinner() {
		this.spinnerSubject.next(true);
  	}

  	stopSpinner() {
  		this.spinnerSubject.next(false);
  	}
}
