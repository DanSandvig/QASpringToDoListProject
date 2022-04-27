"use strict"

const baseURL = "http://localhost:8080/todolist";
const NEW_TODO_FORM = document.getElementById("newtodo");

const addNewToDo = () => {
    //Creates data from current form values
    const data = {
        priority: NEW_TODO_FORM.priority.value,
        title: NEW_TODO_FORM.title.value,
        description: NEW_TODO_FORM.description.value,
        complete: false
    }

    //Submits to api using axios post request
    axios.post(`${baseURL}/create`, data)
        .then((res) => {console.log(res);})
        .catch((err) => {console.log(err);});

    NEW_TODO_FORM.reset();
    NEW_TODO_FORM.priority.focus();
}

NEW_TODO_FORM.addEventListener("submit", (e) => {
    e.preventDefault();     //Prevents normal submission
    addNewToDo();           //Starts axios post request
});