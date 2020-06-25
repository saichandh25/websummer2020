import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { TodoService } from '../todo.service';
import { todoItem } from '../todo.model';

@Component({
  selector: 'app-todo-items',
  templateUrl: './todo-items.component.html',
  styleUrls: ['./todo-items.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TodoItemsComponent implements OnInit {

  todoItems: todoItem[];
  // Injecting todo service into TodoItemsComponent
  constructor(private todoService: TodoService) {
  }

  ngOnInit(): void {
    this.getTodoItems();
  }

  deleteItem(itemId: number) {
    this.todoService.deleteTodoItem(itemId);
    this.getTodoItems();
  }

  addItem() {
    this.todoService.addItem();
    this.getTodoItems();
  }

  getTodoItems() {
    this.todoItems = this.todoService.todoItems;
  }

}
