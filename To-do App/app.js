const todoInput = document.querySelector(".todo-input");
const todoButton = document.querySelector(".todo-button");
const todoList = document.querySelector(".todo-list");

todoButton.addEventListener("click", addTodo);
todoList.addEventListener("click", deleteCheck);

function addTodo(event) {
  event.preventDefault();
  //console.log("hello");
  //todo div
  const todoDiv = document.createElement("div");
  todoDiv.classList.add("todo");
  //create li
  const newTodo = document.createElement("li");
  newTodo.innerText = todoInput.value;
  newTodo.classList.add("todo-item");
  todoDiv.appendChild(newTodo);
  //complete button//del button
  const completeButton = document.createElement("button");
  completeButton.innerHTML = '<i class="fas fa-check" ></i>';
  completeButton.classList.add("complete-btn");
  todoDiv.appendChild(completeButton);
  //del button
  const deleteButton = document.createElement("button");
  deleteButton.innerHTML = '<i class="fas fa-trash"></i>';
  deleteButton.classList.add("trash-btn");
  todoDiv.appendChild(deleteButton);

  //append to list
  todoList.appendChild(todoDiv);

  //clear todo input value
  todoInput.value = "";
}

function deleteCheck(e) {
  const item = e.target;

  if (item.classList[0] === "trash-btn") {
    const todo = item.parentElement;
    todo.remove();
  }
}
