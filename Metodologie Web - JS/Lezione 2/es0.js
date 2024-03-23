/*
* Crea un nuovo progetto che, per ogni stringa in un array, 
* ritorni una nuova stringa composta dai primi due
* e dagli ultimi due caratteri della stringa originale. 
* La nuova stringa dovrà rimpiazzare quella vecchia nello
* stesso array.
*
* Se la stringa è più corta di due caratteri, ritornare la stringa vuota.
*/

"use strict"

let words = ['Something', 'Cool', 'In', 'Here', 'I', 'Guess'];

for(let i = 0; i < words.length; i++) {
    let word = words[i];
    let len = word.length;

    if(len > 2) { words[i] = word[0] + word[1] + word[len - 2] + word[len - 1] }
    else if(len == 2) { words[i] = word[0] + word[1] }
    else { words[i] = '' }
}

console.log(words);