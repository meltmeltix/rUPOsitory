/*
    1. Definire un array con tutti i voti, in ordine cronologico
    2. Copiare l’array ed eliminare i due voti più bassi
    3. Aggiungere due nuovi voti, alla fine del nuovo array, 
        uguali al “duale” dei voti eliminati
    4. Stampare a schermo entrami gli array e confrontate i voti prima 
        e dopo i “miglioramenti”, mostrando anche la media (arrotondata) 
        in entrambi i casi

*/
"use strict";

let results = [23, 24, 25, 26, 18, 19, 20, 30];
results.sort();

// Copiare l’array ed eliminare i due voti più bassi
let newResults = Array.from(results);
for (let i = 0; i < 2; i++) {
    newResults.push(30 - (newResults.shift() - 18));
}

// Media primo array
let sum_original = 0;
results.forEach( result => {
    sum_original += result;
} )

console.log(
    "Array di voti originale: " + results +
    "\t Media: " + Math.round(sum_original / results.length)
);

// Media secondo array
let sum_new = 0;
newResults.forEach( result => {
    sum_new += result;
} )

console.log(
    "Array di voti modificato: " + newResults +
    "\t Media: " + Math.round(sum_new / newResults.length)
);