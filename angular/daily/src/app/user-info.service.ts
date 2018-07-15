import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService {

	private user: {};

  	constructor() { }

	setUserInfo(user: {}) {
	 	this.user = user;
	}

	getUserInfo() {
		return this.user
	}
}
