import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { AppComponent } from './app.component';
import { StartComponent} from "./components/start/start.component";
import { HeaderComponent} from "./components/header/header.component";
import { LoginComponent} from "./components/login/login.component";
import { RegistrationComponent} from "./components/registration/registration.component";
import { MainComponent} from "./components/main/main.component";
import {RouterModule, Routes} from "@angular/router";

const appRoutes: Routes = [
  { path: '', component: StartComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'main', component: MainComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    HeaderComponent,
    LoginComponent,
    MainComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    DropdownModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
