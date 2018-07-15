import { BrowserModule } from '@angular/platform-browser';
import { NgModule,  } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';


import { AppRoutingModule } from './app-routing.module';
import { MaterialDesignModule } from './material-design/material-design.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExpenseComponent } from './expense/expense.component';
import { environment } from '../environments/environment';
import { SpinnerComponent } from './spinner/spinner.component';
import { SpinnerService } from './spinner.service';
import { UserInfoService } from './user-info.service';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ExpenseComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialDesignModule,
    FormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule
  ],
  providers: [SpinnerService, UserInfoService],
  bootstrap: [AppComponent]
})
export class AppModule { }