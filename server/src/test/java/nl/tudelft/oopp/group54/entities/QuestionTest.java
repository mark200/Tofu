package nl.tudelft.oopp.group54.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuestionTest {

    private static Question question;
    private static Question questionDuplicate;
    private static Question question1;
    private static QuestionKey key;
    private static QuestionKey keyDuplicate;
    private static QuestionKey key2;

    /**
     * Executes before all tests.
     */
    @BeforeEach
    public void init() {
        key = new QuestionKey(123, 321);
        keyDuplicate = new QuestionKey(123, 321);
        key2 = new QuestionKey(123, 32);
        Date date = new Date();
        question = new Question(key, 321, "192.158.1.38", "231", 231, true, date);
        questionDuplicate = new Question(keyDuplicate, 321, "192.158.1.38", "231", 231, true, date);
        question1 = new Question(new QuestionKey(000, 111), 3, "192.158.2.38", "32", 1, false, date);
    }

    @Test
    public void testKeyEmptyConstructor() {
        QuestionKey newKey = new QuestionKey();
        assertNotNull(newKey);
    }

    @Test
    public void testKeySetID() {
        key.setId(9476);
        assertEquals(9476, key.getId());
    }

    @Test
    public void testKeySetLectureID() {
        key.setLectureId(976);
        assertEquals(976, key.getLectureId());
    }

    @Test
    public void testKeyCompareTo() {
        assertEquals(0, key.compareTo(key));
    }

    @Test
    public void testKeyEquals() {
        assertEquals(key, key);
        assertNotEquals(key, null);
        assertNotEquals(key, key2);
        assertFalse(key.equals(null));
        assertFalse(key.equals("123"));
    }

    @Test
    public void testKeyHashCode() {
        assertEquals(5095, key.hashCode());
    }

    @Test
    public void testQuestionEmptyConstructor() {
        Question newQuestion = new Question();
        assertNotNull(newQuestion);
    }

    @Test
    public void testEquals() {
        assertEquals(question, questionDuplicate);
        assertEquals(question, question);
        assertNotEquals(question, null);
        assertNotEquals(question, question1);
        assertNotEquals(question1, "123");
    }

    @Test
    public void testSetPrimaryKey() {
        question.setPrimaryKey(new QuestionKey(5, 6));
        assertEquals(5, question.getPrimaryKey().getId());
        assertEquals(6, question.getPrimaryKey().getLectureId());
    }

    @Test
    public void testSetStudentID() {
        question.setStudentId(39574);
        assertEquals(39574, question.getStudentId());
    }

    @Test
    public void testSetContent() {
        question.setContent("new content here");
        assertEquals("new content here", question.getContent());
    }

    @Test
    public void testSetVoteCounter() {
        question.setVoteCounter(45);
        assertEquals(45, question.getVoteCounter());
    }

    @Test
    public void testSetAnswered() {
        question.setAnswered(false);
        assertFalse(question.getAnswered());
    }

    @Test
    public void testSetCreatedAt() {
        Date newDate = new Date();
        question.setCreatedAt(newDate);
        assertEquals(newDate, question.getCreatedAt());
    }

    @Test
    public void testSetAnswerText() {
        question.setAnswerText("I am answering your question.");
        assertEquals("I am answering your question.", question.getAnswerText());
    }

    @Test
    public void testSetStudentIP() {
        question.setStudentIp("254.325.44.67");
        assertEquals("254.325.44.67", question.getStudentIp());
    }

    @Test
    public void testQuestionHashCode() {
        question = new Question(key, 321, "192.158.1.38", "231", 231, true, new Date(0));
        assertEquals(-2107025453, question.hashCode());
    }

    @Test
    public void testQuestionToString() {
        Date newDate = new Date();
        Question newQuestion = new Question(key, 321, "192.158.1.38", "231", 231, true, newDate);
        newQuestion.setAnswerText("answer");
        String expected = "Question{primaryKey=nl.tudelft.oopp"
                + ".group54.entities.QuestionKey@13e7,"
                + " student_id=321, studentIp='192.158.1.38',"
                + " content='231', vote_counter=231, answered=true,"
                + " created_at=" + newDate.toString() + ","
                + " answerText='answer'}";
        assertEquals(expected, newQuestion.toString());
    }
}
