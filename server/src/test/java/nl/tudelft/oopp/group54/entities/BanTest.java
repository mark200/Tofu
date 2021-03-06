package nl.tudelft.oopp.group54.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BanTest {

    static Ban ban;
    static Ban banDuplicate;
    static BanKey key;
    static BanKey emptyKey;
    static Ban emptyBan;
    static BanKey keyDuplicate;
    static Ban ban1;
    static BanKey key2;

    /**
     * Init.
     */
    @BeforeEach
    public void init() {
        emptyBan = new Ban();
        emptyKey = new BanKey();
        key = new BanKey("192.158.1.38", 321);
        key2 =  new BanKey("192.158.1.3", 321);
        keyDuplicate = new BanKey("192.158.1.38", 321);
        ban = new Ban(key);
        banDuplicate = new Ban(keyDuplicate);
        ban1 = new Ban(new BanKey("192.158.1.20", 302));

    }

    @Test
    public void testEmptyBanKey() {
        assertNotNull(emptyKey);
    }

    @Test
    public void testEmptyBan() {
        assertNotNull(emptyBan);
    }

    @Test
    public void testSetPrimaryKey() {
        ban.setPrimaryKey(new BanKey("255.255.255.0", 3));
        assertEquals(new BanKey("255.255.255.0", 3), ban.getPrimaryKey());
    }

    @Test
    public void testBanHashCode() {
        assertEquals(500887137, ban.hashCode());
    }

    @Test
    public void testBanToString() {
        assertEquals("Ban{primaryKey=nl.tudelft.oopp.group54.entities.BanKey@1ddaee42}", ban.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(ban, banDuplicate);
        assertEquals(ban, ban);
        assertNotEquals(ban, null);
        assertNotEquals(ban1, ban);
        assertNotEquals(ban, "123");
    }

    @Test
    public void testBanKeySetBannedIP() {
        key.setBanned_ip("1.1.1.1");
        assertEquals("1.1.1.1", key.getBannedIP());
    }

    @Test
    public void testBanKeySetLectureID() {
        key.setLecture_id(1876);
        assertEquals(1876, key.getLectureID());
    }

    @Test
    public void testBanKeyCompareTo() {
        assertEquals(0, key.compareTo(keyDuplicate));
    }

    @Test
    public void testKeyEquals() {
        assertEquals(key, key);
        assertEquals(key, keyDuplicate);
        assertFalse(key.equals(null));
        assertFalse(key.equals(key2));
        assertFalse(key.equals("123"));
    }
}
