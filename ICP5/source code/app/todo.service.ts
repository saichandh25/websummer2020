import { Injectable } from '@angular/core';
import { todoItem } from './todo.model';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private _todoItems: todoItem[] = [
    {
      id: 1,
      value: 'item1'
    },
    {
      id: 2,
      value: 'item2'
    },
    {
      id: 3,
      value: 'item3'
    },
    {
      id: 4,
      value: 'item4'
    },
    {
      id: 5,
      value: 'item5'
    },
    {
      id: 6,
      value: 'item6'
    },
  ]

  constructor() { }

  get todoItems() : todoItem[] {
    return this._todoItems;
  }


  addItem () {
    //get the last item id
    const lastItemID = this._todoItems.sort((item1, item2) => {
      return item1.id - item2.id
    }).slice(-1)[0]?.id;

    this.todoItems.push({
      // if last id is undefined setting id to 0 otherwiser increment the id value by one for new todo list item
      id:  lastItemID ? lastItemID + 1 : 0,
      value: `item${lastItemID ? lastItemID + 1 : 0}` 
    });
  }


  deleteTodoItem(todoId: number) {
    // removing todo item fromt the array by using filter
    this._todoItems = this._todoItems.filter(({id}) => id !== todoId);
  }

}
