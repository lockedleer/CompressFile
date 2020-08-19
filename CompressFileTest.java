import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CompressFileTest {

    CompressFile cf = new CompressFile();

    @org.junit.jupiter.api.Test
    void createList() {
        String testStr = "dog, cat, parrot";
        List<String> testList = new ArrayList<>();
        testList.add("dog");
        testList.add("cat");
        testList.add("parrot");

        assertThat(testList, is(cf.createList(testStr)));

        assertThat(cf.createList(testStr), hasItems(testList.get(1)));

        assertThat(cf.createList(testStr).size(), is(3));

        assertEquals(testList, cf.createList(testStr), "createList method must make a string value into a list.");

    }

    @org.junit.jupiter.api.Test
    void removeDuplicates() {
        List<String> testList1 = new ArrayList<>();
        testList1.add("dog");
        testList1.add("cat");
        testList1.add("parrot");

        List<String> testList2 = new ArrayList<>();
        testList2.add("dog");
        testList2.add("dog");
        testList2.add("cat");
        testList2.add("parrot");

        assertThat(cf.sortAlphabetically(testList1), is(cf.sortAlphabetically(cf.removeDuplicates(testList2))));

        assertThat(cf.removeDuplicates(testList1), hasItems(testList2.get(1)));

        assertThat(cf.removeDuplicates(testList2).size(), is(3));

        assertEquals(cf.sortAlphabetically(testList1), cf.sortAlphabetically(cf.removeDuplicates(testList2)), "removeDuplicates must remove duplicate values within list.");
    }

    @org.junit.jupiter.api.Test
    void sortAlphabetically() {
        List<String> testList1 = new ArrayList<>();
        testList1.add("cat");
        testList1.add("dog");
        testList1.add("parrot");

        List<String> testList2 = new ArrayList<>();
        testList2.add("parrot");
        testList2.add("dog");
        testList2.add("cat");

        assertThat(testList1, is(cf.sortAlphabetically(testList2)));

        assertThat(cf.sortAlphabetically(testList1), hasItems(testList2.get(1)));

        assertThat(cf.sortAlphabetically(testList2).size(), is(3));

        assertEquals(cf.sortAlphabetically(testList1), cf.sortAlphabetically(testList2), "sortAlphabetically must sort list in ascending order.");

    }
}