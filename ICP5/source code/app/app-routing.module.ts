import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TodoItemsComponent } from './todo-items/todo-items.component';
import { CounterComponent } from './counter/counter.component';


const routes: Routes = [
  {
    path: '*',
    redirectTo: 'todo'
  },
  {
    path: 'todo',
    component: TodoItemsComponent
  },
  {
    path: 'counter',
    component: CounterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
