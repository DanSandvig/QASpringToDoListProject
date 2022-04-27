"use strict"

//Variables

const baseURL = "http://localhost:8080/todolist";
const NEW_TODO_FORM = document.getElementById("newtodo");
const DYNAMIC_TABLE = document.getElementById("dynamicTable");

//Post - Create

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
        .then((res) => { 
            console.log(res); 
            
            //Updates Table
            getAllEntries();
        })
        .catch((err) => { console.log(err); });

    NEW_TODO_FORM.reset();
    NEW_TODO_FORM.priority.focus();
}

//Get - Read

const createNewRow = (id, priority, title, description, complete) => {
    
    //Creates new row
    const newRow = document.createElement("tr");

    //Creates id as correctly scoped header for formatting
    const idCell = document.createElement("th");
    idCell.setAttribute("scope", "row");

    //Creates remaining elements as standard table data cells
    const priorityCell = document.createElement("td");
    const titleCell = document.createElement("td");
    const descriptionCell = document.createElement("td");
    const completeCell = document.createElement("td");

    //Creates text nodes from parameters
    const thisId = document.createTextNode(id);
    const thisPriority = document.createTextNode(priority);
    const thisTitle = document.createTextNode(title);
    const thisDescription = document.createTextNode(description);
    const thisComplete = document.createTextNode(complete);

    //Appends elemements appropriately
    idCell.appendChild(thisId);
    priorityCell.appendChild(thisPriority);
    titleCell.appendChild(thisTitle);
    descriptionCell.appendChild(thisDescription);
    completeCell.appendChild(thisComplete);

    newRow.append(idCell, priorityCell, titleCell, descriptionCell, completeCell);

    DYNAMIC_TABLE.appendChild(newRow);
}

const getAllEntries = () => {
    axios.get(`${baseURL}/getall`)
        .then((res) => {
            const alltodos = res.data;
            
            //Clears current table
            DYNAMIC_TABLE.innerHTML ="";

            //Recreates table from scratch (presumably there's a better way?)
            alltodos.forEach(todoentry => 
                createNewRow(todoentry.id, todoentry.priority, todoentry.title, todoentry.description, todoentry.complete))
        })
        .catch((err) => { console.log(err); });
}




//Event Listeners

NEW_TODO_FORM.addEventListener("submit", (e) => {
    e.preventDefault();     //Prevents normal submission
    addNewToDo();           //Starts axios post request
});