package rvt;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for AVL Tree implementation.
 */
public class AVLTreeTest {
    private AVLTree tree;

    @Before
    public void setUp() {
        tree = new AVLTree();
    }

    @Test
    public void testEmptyTree() {
        assertTrue("New tree should be empty", tree.isEmpty());
        assertEquals("Empty tree height should be 0", 0, tree.getHeight());
        assertNull("Empty tree root should be null", tree.getRoot());
    }

    @Test
    public void testSingleInsertion() {
        tree.insert(10);
        assertFalse("Tree should not be empty after insertion", tree.isEmpty());
        assertEquals("Tree height should be 1", 1, tree.getHeight());
        assertTrue("Search should find inserted element", tree.search(10));
    }

    @Test
    public void testMultipleInsertions() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        
        assertTrue("Should find 10", tree.search(10));
        assertTrue("Should find 20", tree.search(20));
        assertTrue("Should find 30", tree.search(30));
        assertFalse("Should not find 40", tree.search(40));
    }

    @Test
    public void testDuplicateInsertion() {
        tree.insert(10);
        tree.insert(10);
        
        assertEquals("Height should be 1 after duplicate insertion", 1, tree.getHeight());
        assertTrue("Should find 10", tree.search(10));
    }

    @Test
    public void testLeftLeftRotation() {
        // Insert in descending order to trigger left-left rotation
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        
        assertEquals("Tree should be balanced with height 2", 2, tree.getHeight());
        assertEquals("Root should be 20 after rotation", 20, tree.getRoot().getKey());
        assertTrue("All elements should be searchable", 
            tree.search(10) && tree.search(20) && tree.search(30));
    }

    @Test
    public void testRightRightRotation() {
        // Insert in ascending order to trigger right-right rotation
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        
        assertEquals("Tree should be balanced with height 2", 2, tree.getHeight());
        assertEquals("Root should be 20 after rotation", 20, tree.getRoot().getKey());
        assertTrue("All elements should be searchable", 
            tree.search(10) && tree.search(20) && tree.search(30));
    }

    @Test
    public void testLeftRightRotation() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        
        assertEquals("Tree should be balanced", 2, tree.getHeight());
        assertEquals("Root should be 20 after rotation", 20, tree.getRoot().getKey());
    }

    @Test
    public void testRightLeftRotation() {
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
        
        assertEquals("Tree should be balanced", 2, tree.getHeight());
        assertEquals("Root should be 20 after rotation", 20, tree.getRoot().getKey());
    }

    @Test
    public void testComplexBalancing() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
        
        assertEquals("Tree should maintain balanced height", 3, tree.getHeight());
        assertTrue("All inserted elements should be searchable",
            tree.search(10) && tree.search(20) && tree.search(25) && 
            tree.search(30) && tree.search(40) && tree.search(50));
    }

    @Test
    public void testDeleteLeafNode() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        
        tree.delete(10);
        assertFalse("Deleted element should not be found", tree.search(10));
        assertTrue("Other elements should still exist", tree.search(20) && tree.search(30));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(25);
        
        tree.delete(30);
        assertFalse("Deleted element should not be found", tree.search(30));
        assertTrue("Remaining elements should exist", 
            tree.search(20) && tree.search(10) && tree.search(25));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(25);
        tree.insert(40);
        
        tree.delete(30);
        assertFalse("Deleted element should not be found", tree.search(30));
        assertTrue("Remaining elements should exist", 
            tree.search(20) && tree.search(10) && tree.search(25) && tree.search(40));
    }

    @Test
    public void testDeleteRoot() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        
        tree.delete(20);
        assertFalse("Deleted root should not be found", tree.search(20));
        assertTrue("Other elements should still exist", tree.search(10) && tree.search(30));
        assertFalse("Tree should not be empty", tree.isEmpty());
    }

    @Test
    public void testDeleteNonExistentElement() {
        tree.insert(20);
        tree.insert(10);
        
        tree.delete(30); // Element doesn't exist
        assertTrue("Existing elements should remain", tree.search(20) && tree.search(10));
    }

    @Test
    public void testDeleteFromEmptyTree() {
        tree.delete(10);
        assertTrue("Tree should remain empty", tree.isEmpty());
    }

    @Test
    public void testDeleteAllElements() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        
        tree.delete(20);
        tree.delete(10);
        tree.delete(30);
        
        assertTrue("Tree should be empty after deleting all elements", tree.isEmpty());
    }

    @Test
    public void testBalanceAfterDeletion() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
        
        tree.delete(40);
        
        // Tree should remain balanced after deletion
        assertTrue("Height should remain logarithmic", tree.getHeight() <= 4);
        assertTrue("Remaining elements should be searchable",
            tree.search(10) && tree.search(20) && tree.search(25) && 
            tree.search(30) && tree.search(50));
    }

    @Test
    public void testSearchInEmptyTree() {
        assertFalse("Search in empty tree should return false", tree.search(10));
    }

    @Test
    public void testLargeNumberOfInsertions() {
        // Insert 100 elements
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }
        
        // Height should be O(log n), for 100 elements it should be around 7-8
        assertTrue("Height should be logarithmic", tree.getHeight() <= 10);
        
        // All elements should be searchable
        for (int i = 1; i <= 100; i++) {
            assertTrue("Element " + i + " should be found", tree.search(i));
        }
    }

    @Test
    public void testRandomInsertions() {
        int[] values = {15, 25, 10, 30, 5, 20, 35, 1, 12, 28};
        
        for (int value : values) {
            tree.insert(value);
        }
        
        for (int value : values) {
            assertTrue("Value " + value + " should be found", tree.search(value));
        }
        
        assertTrue("Height should be balanced", tree.getHeight() <= 5);
    }
}
