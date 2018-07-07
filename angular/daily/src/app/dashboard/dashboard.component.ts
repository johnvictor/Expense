import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	userNames: string[] = ["User 1", "User 2", "User 3", "User 4", "User 5", "User 6", "User 7", "User 8", "User 9", "User 10"];

	// "User 11", "User 12", "User 13", "User 14",
	// 	"User 15", "User 16", "User 17", "User 18", "User 19", "User 20"

	constructor(private router: Router) { }

	ngOnInit() {
	}

	expenseInfo(user) {
		this.router.navigate(['/expense']);
	}

}
