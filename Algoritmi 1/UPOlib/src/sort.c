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

/**
 * - base: pointer to the first element of the array
 * - n: length of the array
 * - size: size of the array's elements
 * - cmp: pointer to comparator function
 */

#include <assert.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "sort_private.h"

void upo_insertion_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp) {
    // TODO
}

void upo_merge_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp) {
    assert(base != NULL);
    upo_merge_sort_rec(base, 0, n - 1, size, cmp);
}

void upo_merge_sort_rec(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp) {
    size_t mid;
    
    if (lo >= hi) return;

    mid = lo+(hi-lo)/2;
    upo_merge_sort_rec(base, lo, mid, size, cmp);
    upo_merge_sort_rec(base, mid + 1, hi, size, cmp);
    upo_merge(base, lo, mid, hi, size, cmp);
}

void upo_merge(void *base, size_t lo, size_t mid, size_t hi, size_t size, upo_sort_comparator_t cmp) {
    unsigned char *ptr = base;
    unsigned char *aux = NULL;

    size_t n = hi-lo+1;
    size_t i = 0;
    size_t j = mid+1-lo;
    size_t k;

    aux = malloc(n*size);
    if (aux == NULL) {
        perror("Unable to allocate memory for auxiliary vector");
        abort();
    }
    memcpy(aux, ptr+lo*size, n*size);

    for (k = lo; k <= hi; ++k) {
        if (i > (mid-lo)) {
            memcpy(ptr+k*size, aux+j*size, size);
            ++j;
        } else if (j > (hi-lo)) {
            memcpy(ptr+k*size, aux+i*size, size);
            ++i;
        } else if (cmp(aux+j*size, aux+i*size) < 0) {
            memcpy(ptr+k*size, aux+j*size, size);
            ++j;
        } else {
            memcpy(ptr+k*size, aux+i*size, size);
            ++i;
        }
    }

    free(aux);
}

void upo_quick_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp) {
    // TODO
}
