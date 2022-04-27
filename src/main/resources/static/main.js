"use strict"

const NEW_TODO_FORM = document.getElementById("newtodo");

NEW_TODO_FORM.addEventListener("submit", (e) => {
    e.preventDefault();

    console.log("Hey There!");
});