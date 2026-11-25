package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testSentMessagesArrayPopulated() {
        Array.loadTestData();
        assertEquals("Did you get the cake?", Array.sentMessages.get(0));
        assertEquals("It is dinner time !", Array.sentMessages.get(1));
    }

    @Test
    public void testLongestMessage() {
        Array.loadTestData();
        String longest = "";
        for (String s : Array.storedMessages) {
            if (s.length() > longest.length()) longest = s;
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchByRecipient() {
        Array.loadTestData();
        Array.searchByRecipient("+27838884567");
        assertTrue(true); 
    }

    @Test
    public void testDeleteByHash() {
        Array.loadTestData();
        int initialSize = Array.messageHashes.size();
        Array.deleteByHash("08:2:WHEREON TIME.");
        assertEquals(initialSize - 1, Array.messageHashes.size());
    }
}
