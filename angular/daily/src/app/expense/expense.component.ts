import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';

export interface UserData {
	// name: string,
	// phone: string,
	date: string;
	stationary: string;
	dress: string;
	hospital: string;
	travel: string;
	furniture: string;
	recharge: string;
	vegetables: string;
	petrol: string;
	electricity: string;
	
}

/** Constants used to fill up our data base. */
const COLORS: string[] = ['maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple',
  'fuchsia', 'lime', 'teal', 'aqua', 'blue', 'navy', 'black', 'gray'];
const NAMES: string[] = ['Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack',
  'Charlotte', 'Theodore', 'Isla', 'Oliver', 'Isabella', 'Jasper',
  'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'];

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
									'travel',
									'furniture',
									'recharge',
									'vegetables',
									'petrol',
									'electricity'
									];
	  dataSource: MatTableDataSource<UserData>;

	  @ViewChild(MatPaginator) paginator: MatPaginator;
	  @ViewChild(MatSort) sort: MatSort;

	constructor() {
		// Create 100 users
		const users = Array.from({length: 100}, (_, k) => createNewUser(k + 1));

		// Assign the data to the data source for the table to render
		this.dataSource = new MatTableDataSource(users);
	}

	ngOnInit() {
		this.dataSource.paginator = this.paginator;
		this.dataSource.sort = this.sort;
	}

	applyFilter(filterValue: string) {
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}
}

/** Builds and returns a new User. */
function createNewUser(id: number): UserData {
	return {
		// name: 'John Victor',
		// phone: '959595959',
		date: id + '-06-2018',
		stationary: '2000',
		dress: "2000",
		hospital: "2000",
		travel: "2000",
		furniture: "2000",
		recharge: "2000",
		vegetables: "2000",
		petrol: "2000",
		electricity: "2000"
	};
}