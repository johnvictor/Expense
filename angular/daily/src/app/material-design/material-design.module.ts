import { NgModule } from '@angular/core';
import { MatListModule,
		MatCardModule,
		MatDividerModule,
		MatToolbarModule,
		MatCheckboxModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule
 } from '@angular/material';

@NgModule({
  imports: [
    MatListModule,
    MatCardModule,
    MatDividerModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule
  ],
  exports: [
  	MatListModule,
  	MatCardModule,
  	MatDividerModule,
  	MatToolbarModule,
  	MatCheckboxModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule
  ],
  declarations: []
})
export class MaterialDesignModule { }
