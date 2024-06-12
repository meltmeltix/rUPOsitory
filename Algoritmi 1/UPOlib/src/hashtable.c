/* vim: set tabstop=4 expandtab shiftwidth=4 softtabstop=4: */

/*
 * Copyright 2015 University of Piemonte Orientale, Computer Science Institute
 *
 * This file is part of UPOalglib.
 *
 * UPOalglib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UPOalglib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UPOalglib.  If not, see <http://www.gnu.org/licenses/>.
 */

#include <assert.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <upo/error.h>
#include <upo/utility.h>

#include "hashtable_private.h"

/*** EXERCISE #1 - BEGIN of HASH TABLE with SEPARATE CHAINING ***/

upo_ht_sepchain_t upo_ht_sepchain_create(size_t m, upo_ht_hasher_t key_hash, upo_ht_comparator_t key_cmp) {    upo_ht_sepchain_t ht = NULL;
    size_t i = 0;

    /* preconditions */
    assert(key_hash != NULL);
    assert(key_cmp != NULL);

    /* Allocate memory for the hash table type */
    ht = malloc(sizeof(struct upo_ht_sepchain_s));
    if (ht == NULL) {
        perror(
            "Unable to allocate memory for Hash Table with Separate Chaining");
        abort();
    }

    if (m > 0) {
        /* Allocate memory for the array of slots */
        ht->slots = malloc(m * sizeof(upo_ht_sepchain_slot_t));
        if (ht->slots == NULL) {
            perror(
                "Unable to allocate memory for slots of the Hash Table with "
                "Separate Chaining");
            abort();
        }

        /* Initialize slots */
        for (i = 0; i < m; ++i) {
            ht->slots[i].head = NULL;
        }
    } else {
        ht->slots = NULL;
    }

    /* Initialize the other fields */
    ht->capacity = m;
    ht->size = 0;
    ht->key_hash = key_hash;
    ht->key_cmp = key_cmp;

    return ht;
}

void upo_ht_sepchain_destroy(upo_ht_sepchain_t ht, int destroy_data) {
    if (ht != NULL) {
        upo_ht_sepchain_clear(ht, destroy_data);
        free(ht->slots);
        free(ht);
    }
}

void upo_ht_sepchain_clear(upo_ht_sepchain_t ht, int destroy_data) {
    if (ht != NULL && ht->slots != NULL) {
        size_t i = 0;

        /* For each slot, clear the associated list of collisions */
        for (i = 0; i < ht->capacity; ++i) {
            upo_ht_sepchain_list_node_t *list = NULL;

            list = ht->slots[i].head;
            while (list != NULL) {
                upo_ht_sepchain_list_node_t *node = list;

                list = list->next;

                if (destroy_data) {
                    free(node->key);
                    free(node->value);
                }

                free(node);
            }
            ht->slots[i].head = NULL;
        }
        ht->size = 0;
    }
}

void *upo_ht_sepchain_put(upo_ht_sepchain_t ht, void *key, void *value) {
    void *old_value = NULL;

    if (ht != NULL && ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);
        upo_ht_sepchain_list_node_t *n = ht->slots[h].head;

        while (n != NULL && ht->key_cmp(key, n->key) != 0)
            n = n->next;

        if (n == NULL) {
            n = malloc(sizeof(struct upo_ht_sepchain_list_node_s));

            if (n == NULL) {
                perror("Unable to allocate space for new node of HashTable");
                abort();
            } else {
                n->key = key;
                n->value = value;
                n->next = ht->slots[h].head;
                ht->slots[h].head = n;
                ht->size++;
            }
        } else {
            old_value = n->value;
            n->value = value;
        }
    }

    return old_value;
}

void upo_ht_sepchain_insert(upo_ht_sepchain_t ht, void *key, void *value) {
    if (ht != NULL && ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);
        upo_ht_sepchain_list_node_t *n = ht->slots[h].head;

        while (n != NULL && ht->key_cmp(key, n->key) != 0)
            n = n->next;

        if (n == NULL) {
            n = malloc(sizeof(struct upo_ht_sepchain_list_node_s));

            if (n == NULL) {
                perror("Unable to allocate space for new node of HashTable");
                abort();
            } else {
                n->key = key;
                n->value = value;
                n->next = ht->slots[h].head;
                ht->slots[h].head = n;
                ht->size++;
            }
        }
    }
}

void *upo_ht_sepchain_get(const upo_ht_sepchain_t ht, const void *key) {
    if (ht != NULL || ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);
        upo_ht_sepchain_list_node_t *n = ht->slots[h].head;

        while (n != NULL && ht->key_cmp(key, n->key) != 0)
            n = n->next;

        if (n != NULL)
            return n->value;
    }

    return NULL;
}

int upo_ht_sepchain_contains(const upo_ht_sepchain_t ht, const void *key) {
    if (ht != NULL || ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);
        upo_ht_sepchain_list_node_t *n = ht->slots[h].head;

        while (n != NULL && ht->key_cmp(key, n->key) != 0)
            n = n->next;

        if (n != NULL)
            return 1;
    }

    return 0;
}

void upo_ht_sepchain_delete(upo_ht_sepchain_t ht, const void *key, int destroy_data) {
    if (ht != NULL || ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);
        upo_ht_sepchain_list_node_t *n = ht->slots[h].head;
        upo_ht_sepchain_list_node_t *p = NULL;

        while (n != NULL && ht->key_cmp(key, n->key)) {
            p = n;
            n = n->next;
        }

        if (n != NULL) {
            if (p == NULL)
                ht->slots[h].head = n->next;
            else
                p->next = n->next;

            if (destroy_data) {
                free(n->key);
                free(n->value);
            }
            ht->size--;
            free(n);
        }
    }
}

size_t upo_ht_sepchain_size(const upo_ht_sepchain_t ht) {
    size_t size = 0;

    if (ht != NULL && ht->slots != NULL) {
        for (size_t i = 0; i < ht->capacity; i++) {
            upo_ht_sepchain_list_node_t *node = ht->slots[i].head;
            while (node != NULL) {
                size++;
                node = node->next;
            }
        }
    }

    return size;
}

int upo_ht_sepchain_is_empty(const upo_ht_sepchain_t ht) {
    return upo_ht_sepchain_size(ht) == 0 ? 1 : 0;
}

size_t upo_ht_sepchain_capacity(const upo_ht_sepchain_t ht) {
    return (ht != NULL) ? ht->capacity : 0;
}

double upo_ht_sepchain_load_factor(const upo_ht_sepchain_t ht) {
    return upo_ht_sepchain_size(ht) / (double)upo_ht_sepchain_capacity(ht);
}

upo_ht_comparator_t upo_ht_sepchain_get_comparator(const upo_ht_sepchain_t ht) {
    return ht->key_cmp;
}

upo_ht_hasher_t upo_ht_sepchain_get_hasher(const upo_ht_sepchain_t ht) {
    return ht->key_hash;
}

/*** EXERCISE #1 - END of HASH TABLE with SEPARATE CHAINING ***/

/*** EXERCISE #2 - BEGIN of HASH TABLE with LINEAR PROBING ***/

upo_ht_linprob_t upo_ht_linprob_create(size_t m, upo_ht_hasher_t key_hash, upo_ht_comparator_t key_cmp) {
    upo_ht_linprob_t ht = NULL;
    size_t i = 0;

    /* preconditions */
    assert(key_hash != NULL);
    assert(key_cmp != NULL);

    /* Allocate memory for the hash table type */
    ht = malloc(sizeof(struct upo_ht_linprob_s));
    if (ht == NULL) {
        perror("Unable to allocate memory for Hash Table with Linear Probing");
        abort();
    }

    /* Allocate memory for the array of slots */
    if (m > 0) {
        ht->slots = malloc(m * sizeof(upo_ht_linprob_slot_t));
        if (ht->slots == NULL) {
            perror(
                "Unable to allocate memory for slots of the Hash Table with "
                "Separate Chaining");
            abort();
        }

        /* Initialize the slots */
        for (i = 0; i < m; ++i) {
            ht->slots[i].key = NULL;
            ht->slots[i].value = NULL;
            ht->slots[i].tombstone = 0;
        }
    } else {
        ht->slots = NULL;
    }

    /* Initialize the other fields */
    ht->capacity = m;
    ht->size = 0;
    ht->key_hash = key_hash;
    ht->key_cmp = key_cmp;

    return ht;
}

void upo_ht_linprob_destroy(upo_ht_linprob_t ht, int destroy_data) {
    if (ht != NULL) {
        upo_ht_linprob_clear(ht, destroy_data);
        free(ht->slots);
        free(ht);
    }
}

void upo_ht_linprob_clear(upo_ht_linprob_t ht, int destroy_data) {
    if (ht != NULL && ht->slots != NULL) {
        size_t i = 0;

        /* For each slot, clear the associated list of collisions */
        for (i = 0; i < ht->capacity; ++i) {
            if (ht->slots[i].key != NULL) {
                if (destroy_data) {
                    free(ht->slots[i].key);
                    free(ht->slots[i].value);
                }
                ht->slots[i].key = NULL;
                ht->slots[i].value = NULL;
                ht->slots[i].tombstone = 0;
            }
        }
        ht->size = 0;
    }
}

void *upo_ht_linprob_put(upo_ht_linprob_t ht, void *key, void *value) {
    void *old_value = NULL;

    if (ht != NULL && ht->slots != NULL) {
        if (upo_ht_linprob_load_factor(ht) >= 0.5)
            upo_ht_linprob_resize(ht, ht->capacity * 2);

        size_t h = ht->key_hash(key, ht->capacity);
        int found_tomb = 0;
        size_t h_tomb;
        
        while (ht->slots[h].key != NULL && (ht->key_cmp(key, ht->slots[h].key) != 0 || ht->slots[h].tombstone)) {
            if (ht->slots[h].tombstone && !found_tomb) {
                found_tomb = 1;
                h_tomb = h;
            }

            h = (h + 1) % ht->capacity;
        }

        if (ht->slots[h].key == NULL) {
            if (found_tomb) h = h_tomb;

            ht->slots[h].key = key;
            ht->slots[h].value = value;
            ht->slots[h].tombstone = 0;
            ht->size++;
        } else {
            old_value = ht->slots[h].value;
            ht->slots[h].value = value;
        }
    }

    return old_value;
}

void upo_ht_linprob_insert(upo_ht_linprob_t ht, void *key, void *value) {
    if (ht != NULL && ht->slots != NULL) {
        if (upo_ht_linprob_load_factor(ht) >= 0.5)
            upo_ht_linprob_resize(ht, ht->capacity * 2);

        size_t h = ht->key_hash(key, ht->capacity);
        int found_tomb = 0;
        size_t h_tomb;
        
        while (ht->slots[h].key != NULL && (ht->key_cmp(key, ht->slots[h].key) != 0 || ht->slots[h].tombstone)) {
            if (ht->slots[h].tombstone && !found_tomb) {
                found_tomb = 1;
                h_tomb = h;
            }

            h = (h + 1) % ht->capacity;
        }

        if (ht->slots[h].key == NULL) {
            if (found_tomb) h = h_tomb;

            ht->slots[h].key = key;
            ht->slots[h].value = value;
            ht->slots[h].tombstone = 0;
            ht->size++;
        }
    }
}

void *upo_ht_linprob_get(const upo_ht_linprob_t ht, const void *key) {
    if (ht != NULL && ht->slots != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);

        while (ht->slots[h].key != NULL && (ht->key_cmp(key, ht->slots[h].key) != 0 || ht->slots[h].tombstone))
            h = (h + 1) % ht->capacity;

        if (ht->slots[h].key != NULL)
            return ht->slots[h].value;
    }

    return NULL;
}

int upo_ht_linprob_contains(const upo_ht_linprob_t ht, const void *key) {
    if (ht != NULL) {
        size_t h = ht->key_hash(key, ht->capacity);

        while (ht->slots[h].key != NULL && (ht->key_cmp(key, ht->slots[h].key) != 0 || ht->slots[h].tombstone))
            h = (h + 1) % ht->capacity;

        if (ht->slots[h].key != NULL)
            return 1;
    }

    return 0;
}

void upo_ht_linprob_delete(upo_ht_linprob_t ht, const void *key, int destroy_data) {
    if (ht != NULL && ht->slots != NULL && ht->size != 0) {
        size_t h = ht->key_hash(key, ht->capacity);

        while ((ht->slots[h].key != NULL && ht->key_cmp(key, ht->slots[h].key) != 0) || ht->slots[h].tombstone)
            h = (h + 1) % ht->capacity;

        if (ht->slots[h].key != NULL) {
            if (destroy_data) {
                free(ht->slots[h].key);
                free(ht->slots[h].value);
            }

            ht->size--;
            ht->slots[h].key = NULL;
            ht->slots[h].value = NULL;
            ht->slots[h].tombstone = 1;
            
            if (upo_ht_linprob_load_factor(ht) <= 0.125 && ht->size != 0)
                upo_ht_linprob_resize(ht, ht->capacity / 2);
        }
    }
}

size_t upo_ht_linprob_size(const upo_ht_linprob_t ht) {
    if (ht != NULL)
        return ht->size;
    else
        return 0;
}

int upo_ht_linprob_is_empty(const upo_ht_linprob_t ht) {
    return upo_ht_linprob_size(ht) == 0 ? 1 : 0;
}

size_t upo_ht_linprob_capacity(const upo_ht_linprob_t ht) {
    return (ht != NULL) ? ht->capacity : 0;
}

double upo_ht_linprob_load_factor(const upo_ht_linprob_t ht) {
    return upo_ht_linprob_size(ht) / (double)upo_ht_linprob_capacity(ht);
}

void upo_ht_linprob_resize(upo_ht_linprob_t ht, size_t n) {
    /* preconditions */
    assert(n > 0);

    if (ht != NULL) {
        /* The hash table must be rebuilt from scratch since the hash value of
         * keys will be in general different (due to the change in the
         * capacity). */

        size_t i = 0;
        upo_ht_linprob_t new_ht = NULL;

        /* Create a new temporary hash table */
        new_ht = upo_ht_linprob_create(n, ht->key_hash, ht->key_cmp);
        if (new_ht == NULL) {
            perror(
                "Unable to allocate memory for slots of the Hash Table with "
                "Separate Chaining");
            abort();
        }

        /* Put in the temporary hash table the key-value pairs stored in the
         * hash table to resize.
         * Note: by calling function 'put' we are also rehashing the keys
         * according to the new capacity. */
        for (i = 0; i < ht->capacity; ++i) {
            if (ht->slots[i].key != NULL) {
                upo_ht_linprob_put(new_ht, ht->slots[i].key, ht->slots[i].value);
            }
        }

        /* Copy the new slots in the old hash table.
         * To do so we use a trick that avoids to loop for each key-value pair:
         * swap the array of slots, the size and the capacity between new and
         * old hash tables.
         * This way the memory that was allocated for ht is moved to new_ht and
         * is deallocated when new_ht is destroyed. */
        upo_swap(&ht->slots, &new_ht->slots, sizeof ht->slots);
        upo_swap(&ht->capacity, &new_ht->capacity, sizeof ht->capacity);
        upo_swap(&ht->size, &new_ht->size, sizeof ht->size);

        /* Destroy temporary hash table */
        upo_ht_linprob_destroy(new_ht, 0);
    }
}

/*** EXERCISE #2 - END of HASH TABLE with LINEAR PROBING ***/

/*** EXERCISE #3 - BEGIN of HASH TABLE - EXTRA OPERATIONS ***/

upo_ht_key_list_t upo_ht_sepchain_keys(const upo_ht_sepchain_t ht) {
    upo_ht_key_list_t list = NULL;

    if (!upo_ht_sepchain_is_empty(ht)) {
        for (size_t i = 0; i < ht->capacity; i++) {
            upo_ht_sepchain_list_node_t *node = NULL;

            for (node = ht->slots[i].head; node != NULL; node = node->next) {
                upo_ht_key_list_node_t *list_node = malloc(sizeof(struct upo_ht_key_list_node_s));

                if (list_node == NULL) {
                    perror ("Unable to allocate space for list node");
                    abort();
                }

                list_node->key = node->key;
                list_node->next = list;
                list = list_node;
            }
        }
    }

    return list;
}

void upo_ht_sepchain_traverse(const upo_ht_sepchain_t ht, upo_ht_visitor_t visit, void *visit_context) {
    if (!upo_ht_sepchain_is_empty(ht) && visit != NULL) {
        for (size_t i = 0; i < ht->capacity; i++) {
            upo_ht_sepchain_list_node_t *node = ht->slots[i].head;

            while (node != NULL) {
                visit(node->key, node->value, visit_context);
                node = node->next;
            }
        }
    }
}

upo_ht_key_list_t upo_ht_linprob_keys(const upo_ht_linprob_t ht) {
    upo_ht_key_list_t list = NULL;

    if (!upo_ht_linprob_is_empty(ht)) {
        for (size_t i = 0; i < ht->capacity; i++) {
            if (ht->slots[i].key != NULL && !ht->slots[i].tombstone) {
                upo_ht_key_list_t new_node = malloc(sizeof(struct upo_ht_key_list_node_s));
                
                if (new_node == NULL) {
                    perror("Unable to allocate memory for a new key list node");
                    abort();
                }

                new_node->key = ht->slots[i].key;
                new_node->next = list;
                list = new_node;
            }
        }
    }

    return list;
}

void upo_ht_linprob_traverse(const upo_ht_linprob_t ht, upo_ht_visitor_t visit, void *visit_context) {
    if (!upo_ht_linprob_is_empty(ht) && visit != NULL) {
        for (size_t i = 0; i < ht->capacity; i++) {
            if (ht->slots[i].key != NULL)
                visit (ht->slots[i].key, ht->slots[i].value, visit_context);
        }
    }
}

/*** EXERCISE #3 - END of HASH TABLE - EXTRA OPERATIONS ***/

/*** BEGIN of HASH FUNCTIONS ***/

size_t upo_ht_hash_int_div(const void *x, size_t m) {
    /* preconditions */
    assert(x != NULL);
    assert(m > 0);

    return *((int *)x) % m;
}

size_t upo_ht_hash_int_mult(const void *x, double a, size_t m) {
    /* preconditions */
    assert(x != NULL);
    assert(a > 0 && a < 1);
    assert(m > 0);

    return floor(m * fmod(a * *((int *)x), 1.0));
}

size_t upo_ht_hash_int_mult_knuth(const void *x, size_t m) {
    return upo_ht_hash_int_mult(x, 0.5 * (sqrt(5) - 1), m);
}

size_t upo_ht_hash_str(const void *x, size_t h0, size_t a, size_t m) {
    const char *s = NULL;
    size_t h = h0;

    /* preconditions */
    assert(x != NULL);
    assert(m > 0);
    /*
        assert( a < m );
    */
    assert(h0 < m);

    s = *((const char **)x);
    for (; *s; ++s) {
        h = (a * h + *s) % m;
    }

    return h;
}

size_t upo_ht_hash_str_djb2(const void *x, size_t m) {
    return upo_ht_hash_str(x, 5381U, 33U, m);
}

size_t upo_ht_hash_str_djb2a(const void *x, size_t m) {
    const char *s = NULL;
    size_t h = 5381U;

    /* preconditions */
    assert(x != NULL);
    assert(m > 0);

    s = *((const char **)x);
    for (; *s; ++s) {
        h = (33U * h ^ *s) % m;
    }

    return h;
}

size_t upo_ht_hash_str_java(const void *x, size_t m) {
    return upo_ht_hash_str(x, 0U, 31U, m);
}

size_t upo_ht_hash_str_kr2e(const void *x, size_t m) {
    return upo_ht_hash_str(x, 0U, 31U, m);
}

size_t upo_ht_hash_str_sgistl(const void *x, size_t m) {
    return upo_ht_hash_str(x, 0U, 5U, m);
}

size_t upo_ht_hash_str_stlport(const void *x, size_t m) {
    return upo_ht_hash_str(x, 0U, 33U, m);
}

/*** END of HASH FUNCTIONS ***/
