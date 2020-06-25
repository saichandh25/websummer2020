import { Component, OnInit, ChangeDetectionStrategy, Input, Output, EventEmitter } from '@angular/core';
import { todoItem } from '../todo.model';

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TodoItemComponent{
  @Input() todoItem: todoItem;
  @Output() deleteItem: EventEmitter<number> = new EventEmitter();

  deleteItemHandler() {
    // emitting event when delete icon is clicked
    this.deleteItem.emit(this.todoItem.id);
  }
}
