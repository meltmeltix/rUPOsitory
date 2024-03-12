/*
    1. Definire i nomi dei corsi, come una lista separata da virgole
    2. Creare un array che contenga i nomi, un nome per posizione
    3. Creare un secondo array calcolando gli acronimi dei corsi
    4. Stampare la lista risultante di acronimi e nomi
*/

let lessons_string = 
    "Metodologie di programmazione per il Web, Reti 1, Paradigmi di programmazione, " +
    "Sistemi operativi, Basi di dati e sistemi informativi";

let lessons_array = lessons_string.split(", ");

let lessons_acronyms = [];
lessons_array.forEach( lesson => {
    let word_split = lesson.split(" ");
    let acronym = "";

    word_split.forEach( word => 
        acronym += word[0]
    )

    lessons_acronyms.push(acronym);
} )

for(let i = 0; i < lessons_array.length; i++) {
    console.log(lessons_acronyms[i] + " - " + lessons_array[i]);
}