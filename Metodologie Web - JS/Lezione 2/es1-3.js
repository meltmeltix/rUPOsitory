"use strict"

const rs = require('readline-sync') 

/*
* Object containing tasks' properties
* @param importante     Represents whether said task
*                       is important (true) or not (false)
* @param condivisione   Represents whether said task
*                       is shared (true) or private (false)
*/
function task(
    description = "", 
    isImportant = false, 
    isShared = false, 
    expiryDate
) {
    this.description = description
    this.isImportant = isImportant
    this.isShared = isShared
    this.expiryDate = expiryDate
}

let tasks = []
let selection

do {
    console.log("\n\n----- TODO LIST -----")
    console.log("1. Inserire una nuova task")
    console.log("2. Rimuovere una task")
    console.log("3. Mostrare tutte le task esistenti, in ordine alfabetico")
    console.log("4. Eliminare tutte le task tramite data")
    console.log("0. Chiudere il programma")
    selection = rs.questionInt("Input [0-4] ")

    switch(selection) {
        case 1:
            let newTask = addTask()
            tasks.push(newTask)

            // TODO Shows after ending program
            if(!isNaN(newTask.expiryDate) && newTask.expiryDate instanceof Date) {
                setTimeout(() => {
                    tasks = tasks.filter((it) => it.description != newTask.description);
                    console.log(`Task "${newTask.description}" scaduto`);
                }, newTask.expiryDate.getTime() - new Date().getTime());
            }

            break 

        case 2:
            let desc = rs.question("\nInserire la descrizione della task: ")
            tasks = deleteTask(tasks, desc, true)
            break 

        case 3:
            tasks.sort((a, b) => {
                const descA = a.description
                const descB = b.description
    
                if (descA < descB) return -1
                if (descB > descA) return 1
                return 0
            }) 
        
            console.log("\n----- Tasks -----")
            for(let i = 0; i < tasks.length; i++) {
                console.log(
                    tasks[i].description + " | " +
                    (tasks[i].isImportant ? "Importante" : "Non importante") + " | " +
                    (tasks[i].isShared ? "Condiviso" : "Privato") + " | " +
                    (tasks[i].expiryDate ? tasks[i].expiryDate : "")
                )
            }
            console.log("-----------------")

            break 

        case 4:
            if(tasks.length != 0) {
                let input = inputDate(true)
                tasks = deleteTask(tasks, input, false)
            } else { console.log("Non ci sono tasks nella lista.") }
            
            break

        case 0: break 
        default: console.log("Opzione inesistente, riprova") 
    }
} while(selection != 0)

console.log("-----------------")

function addTask() {
    let description = "" 
    while(description.length == 0) { description = rs.question("\nInserire descrizione (obbligatorio): ") }

    let isImportant = false
    if(rs.keyInYNStrict("La task e' importante? ")) { isImportant = true }

    let isShared = false
    if(rs.keyInYNStrict("La task e' condivisibile? ")) { isShared = true }

    let expiryDate
    if(rs.keyInYN("\nInserire scadenza? ")) { expiryDate = inputDate() }

    return new task(description, isImportant, isShared, expiryDate) 
}

function deleteTask(tasks, input, deleteByDesc) {
    let editedTasks = Array.from(tasks)

    if(deleteByDesc) {
        return editedTasks
    }

    for(let i = 0; i < editedTasks.length; i++) {
        let taskDate = editedTasks[i].expiryDate

        if(!isNaN(taskDate) && taskDate instanceof Date) {
            if(
                taskDate.getFullYear() == input.getFullYear() && 
                taskDate.getMonth() == input.getMonth() &&
                taskDate.getDate() == input.getDate()
            ) { editedTasks.splice(i, 1) }
        }
    }

    return editedTasks 
}

function inputDate(skipDate = false) {
    const regex = [/^\d{2}$/, /^\d{4}$/]

    function inputRegex(regex, type) {
        let input;

        while(!regex.test(input)) {
            input = rs.question("\nInserire " + type)
            if(regex.test(input)) { 
                return parseInt(input)
            } else {
                console.log("Formato sbagliato. Riprovare")
            }
        }
    }

    let date = inputRegex(regex[0], "giorno (DD): ")
    let month = inputRegex(regex[0], "mese (MM): ") - 1
    let year = inputRegex(regex[1], "anno (YYYY): ")

    if(!skipDate && rs.keyInYNStrict("\nInserire anche ora? ")) {
        let hour = inputRegex(regex[0], "ora (hh): ")
        let minutes = inputRegex(regex[0], "minuti (mm): ")

        return new Date(year, month, date, hour, minutes)
    }

    return new Date(year, month, date)
}