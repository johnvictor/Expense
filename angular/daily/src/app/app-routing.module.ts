import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExpenseComponent } from './expense/expense.component';

const routes: Routes = [
	{
		path: 'dashboard',
		component: DashboardComponent
	},
	{
		path: 'expense',
		component: ExpenseComponent
	},
	{
		path: '**',
		redirectTo: '/dashboard',
		pathMatch: 'full'
	}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
