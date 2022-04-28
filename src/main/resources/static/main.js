"use strict"

//Variables

const baseURL = "http://localhost:8080/todolist";
const NEW_TODO_FORM = document.getElementById("newtodo");
const DYNAMIC_TABLE = document.getElementById("dynamicTable");
const FIND_TODO_ID = document.getElementById("findtodobyid");
const FIND_TODO_TITLE = document.getElementById("findtodobytitle");

//Non-REST methods

//Allows in table editing of text fields
const changeToTextInput = (tdElement, toDoEntry, changeValue) => {

    //Create the new input element
    const newInputField = document.createElement("input");
    newInputField.setAttribute("type", "text");
    newInputField.setAttribute("maxlength", 255);
    newInputField.setAttribute("placeholder", tdElement.innerText);
    newInputField.classList.add("form-control");

    //Blank out current text
    tdElement.innerText = "";

    //Append new element to the table cell
    tdElement.appendChild(newInputField);

    newInputField.focus();

    newInputField.addEventListener("change", () => {

        if (changeValue === "title") {
            toDoEntry.title = newInputField.value;
        } else if (changeValue === "description") {
            toDoEntry.description = newInputField.value;
        }

        updateToDo(toDoEntry);
    });
}

//Allows in table editing of priority field
const changeToSelectInput = (tdElement, toDoEntry) => {
    //Creates new select field
    const newSelectField = document.createElement("select");
    newSelectField.classList.add("form-select");
    newSelectField.innerHTML = "<option>1</option><option>2</option><option>3</option>";

    //Blanks out existing text
    tdElement.innerText = "";

    tdElement.appendChild(newSelectField);

    newSelectField.focus();

    //Updates priority when value altered
    newSelectField.addEventListener("change", () => {
        toDoEntry.priority = newSelectField.value;

        updateToDo(toDoEntry);
    });
}

//Takes a todo entry and splits it out into the table
const createNewRow = (toDoEntry) => {
    
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
    const deleteCell = document.createElement("td");

    //Creates text nodes from parameters
    const thisId = document.createTextNode(toDoEntry.id);
    const thisPriority = document.createTextNode(toDoEntry.priority);
    const thisTitle = document.createTextNode(toDoEntry.title);
    const thisDescription = document.createTextNode(toDoEntry.description);

    //Create checkbox for complete status
    const thisComplete = document.createElement("input");
    thisComplete.classList.add("form-check-input");
    thisComplete.setAttribute("type", "checkbox");
    thisComplete.checked = toDoEntry.complete;
    if (toDoEntry.complete) {
        titleCell.style.textDecoration = "line-through";
        descriptionCell.style.textDecoration = "line-through";
    }
    thisComplete.addEventListener("change", () => {
        toDoEntry.complete = thisComplete.checked;
        updateToDo(toDoEntry);
    });

    //Create button for delete function
    const thisDeleteButton = document.createElement("button");
    thisDeleteButton.innerText = "Delete the thing!";
    thisDeleteButton.classList.add("btn", "btn-danger", "btn-sm");
    thisDeleteButton.addEventListener("click", () => deleteToDo(toDoEntry.id));

    //Appends elemements appropriately
    idCell.appendChild(thisId);
    priorityCell.appendChild(thisPriority);
    titleCell.appendChild(thisTitle);
    descriptionCell.appendChild(thisDescription);
    completeCell.appendChild(thisComplete);
    deleteCell.appendChild(thisDeleteButton);

    //Adds listeners to enable in-table editing
    priorityCell.addEventListener("click", () => changeToSelectInput(priorityCell, toDoEntry));
    titleCell.addEventListener("click", () => changeToTextInput(titleCell, toDoEntry, "title"));
    descriptionCell.addEventListener("click", () => changeToTextInput(descriptionCell, toDoEntry, "description"));

    newRow.append(idCell, priorityCell, titleCell, descriptionCell, completeCell, deleteCell);

    DYNAMIC_TABLE.appendChild(newRow);
}

//HTTP requests and such

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

const getAllEntries = () => {
    axios.get(`${baseURL}/getall`)
        .then((res) => {
            const alltodos = res.data;
            
            //Clears current table
            DYNAMIC_TABLE.innerHTML ="";

            //Recreates table from scratch (presumably there's a better way?)
            alltodos.forEach(toDoEntry => 
                createNewRow(toDoEntry))
        })
        .catch((err) => { console.log(err); });
}

const getSingleToDo = (searchType) => {
    //Gets sets search value depending on get type
    
    let searchValue;

    if (searchType === "getbyid") {
        searchValue = FIND_TODO_ID.id.value;
    } else if (searchType === "getbytitle") {
        searchValue = FIND_TODO_TITLE.title.value;
    }

    //Searches by given endpoint using given value
    axios.get(`${baseURL}/${searchType}/${searchValue}`)
    .then((res) => {
        const todoentry = res.data;
        
        //Clears current table
        DYNAMIC_TABLE.innerHTML ="";

        //Replaces table with a single entry
        createNewRow(todoentry)
    })
    .catch((err) => { console.log(err); });

    if (searchType === "getbyid") {
        FIND_TODO_ID.reset();
        FIND_TODO_ID.id.focus();
    } else if (searchType === "getbytitle") {
        FIND_TODO_TITLE.reset();
        FIND_TODO_TITLE.title.focus();
    }   
}

// Put - Update

const updateToDo = (updatedToDo) => {
    axios.put(`${baseURL}/update/${updatedToDo.id}`, updatedToDo)
        .then((res) => {
            getAllEntries();
            console.log(res);
        })
        .catch((err) => { console.log(err); });
}

// Delete - Delete

const deleteToDo = (id) => {
    axios.delete(`${baseURL}/delete/${id}`)
        .then((res) => {
            getAllEntries();
            console.log(res);
        })
        .catch((err) => { console.log(err); });
}

//Event Listeners

window.onload = () => getAllEntries();

NEW_TODO_FORM.addEventListener("submit", (e) => {
    e.preventDefault();             //Prevents normal submission
    addNewToDo();                   //Starts axios post request
});

FIND_TODO_ID.addEventListener("submit", (e) => {
    e.preventDefault();             //Prevents normal submission
    getSingleToDo("getbyid");       //Starts axios get request
});

FIND_TODO_TITLE.addEventListener("submit", (e) => {
    e.preventDefault();             //Prevents normal submission
    getSingleToDo("getbytitle");    //Starts axios get request
});