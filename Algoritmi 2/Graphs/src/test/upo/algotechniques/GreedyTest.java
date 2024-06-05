package upo.algotechniques;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreedyTest {
    @Test
    void getMooreMaxJobs() {
        Integer[] duration = { 8, 7, 3, 4, 11 };
        Integer[] deadline = { 11, 10, 17, 14, 22 };
        Integer[] expectedResults = { 1, 3, 2 };

        assertArrayEquals(expectedResults, Greedy.getMooreMaxJobs(duration, deadline));
    }
    
}