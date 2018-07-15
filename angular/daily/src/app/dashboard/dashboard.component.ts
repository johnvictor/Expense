import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireDatabase } from 'angularfire2/database';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { SpinnerService } from '../spinner.service';
import { UserInfoService } from '../user-info.service'; 
import { CookieService } from '../cookie.service'; 

export interface UserData {
  name: string;
  phone: string;
  house_id: string;
}

var userDataArray: UserData[];

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	displayedColumns: string[] = ['name', 'phone', 'house_id'];
	dataSource: MatTableDataSource<UserData>;

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	showTable: boolean = true;

	constructor(private router: Router, private db: AngularFireDatabase, private spinnerService: SpinnerService,
				private userInfoService: UserInfoService, private cookieService: CookieService) { 
		spinnerService.startSpinner();
	}

	applyFilter(filterValue: string) {
	    this.dataSource.filter = filterValue.trim().toLowerCase();

	    if (this.dataSource.paginator) {
	      this.dataSource.paginator.firstPage();
	    }
	}

	ngOnInit() {
		const shirtsRef = this.db.list('users').valueChanges();

		shirtsRef.subscribe((val) => {
			
			if(val.length === 0) {
				this.showTable = false;
			} else {
				this.showTable = true;
				userDataArray = val as UserData[];
			    // Assign the data to the data source for the table to render
			    this.dataSource = new MatTableDataSource(userDataArray);
			    this.dataSource.paginator = this.paginator;
	    		this.dataSource.sort = this.sort;
			}


    		this.spinnerService.stopSpinner();
		});
	
	}

	expenseInfo(user) {
		this.cookieService.setCookie('user', JSON.stringify(user));
		this.router.navigate(['/expense'], { queryParams: { 'id': user.phone } });
	}
}