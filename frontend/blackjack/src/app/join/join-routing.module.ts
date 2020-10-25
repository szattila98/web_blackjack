import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JoinComponent } from './join.component';
import { JoinGuard } from '../core/guards/join.guard';

const routes: Routes = [
  {
    path: '',
    component: JoinComponent,
    canActivate: [JoinGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class JoinRoutingModule { }
