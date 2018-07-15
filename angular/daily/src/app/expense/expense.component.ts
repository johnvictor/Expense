import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { AngularFireDatabase } from 'angularfire2/database';
import { SpinnerService } from '../spinner.service'; 
import { UserInfoService } from '../user-info.service'; 
import { CookieService } from '../cookie.service'; 
import { Router } from '@angular/router';

export interface ExpenseData {
	// name: string,
	// phone: string,
	date: string;
	stationary: string;
	dress: string;
	hospital: string;
	transport: string;
	furniture: string;
	recharge: string;
	vegetables: string;
	petrol: string;
	electricity: string;
	
}

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})

export class ExpenseComponent implements OnInit {

	displayedColumns: string[] = ['date',
									'stationary',
									'dress',
									'hospital',
									'transport',
									'furniture',
									'recharge',
									'vegetables',
									'petrol',
									'electricity'
									];

	dataSource: MatTableDataSource<ExpenseData>;

 	@ViewChild(MatPaginator) paginator: MatPaginator;
 	@ViewChild(MatSort) sort: MatSort;

 	user: {
 		house_id: string,
 		name: string,
 		phone: string
 	};
 	showTable: boolean = true;

	constructor(private activateRoute: ActivatedRoute, private db: AngularFireDatabase, private spinnerService: SpinnerService,
				private userInfoService: UserInfoService, private cookieService: CookieService, private router: Router) {
		var user = this.cookieService.getCookie('user');

		var user = this.cookieService.getCookie('user');
		if(user === "") {
			this.router.navigate(['/dashboard']);
		} else {
			this.user = JSON.parse(user);
			spinnerService.startSpinner();
		}

	}

	ngOnInit() {
		var phoneNo = this.activateRoute.snapshot.queryParams.id;
		const shirtsRef = this.db.list('expense/' + this.user.phone).valueChanges();

		shirtsRef.subscribe((val) => {

			if(val.length > 0) {
				var tableData = val.map((item) => {
					var expense = item['expense'];
					expense.date = item['createdDate'];
					return expense;
				});

				this.dataSource = new MatTableDataSource(tableData as ExpenseData[]);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
				this.showTable = true;
			} else {
				this.showTable = false;
			}

			this.spinnerService.stopSpinner();

			
		}, (err) => {
			console.log(err);
		});
	}

	applyFilter(filterValue: string) {
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}
}