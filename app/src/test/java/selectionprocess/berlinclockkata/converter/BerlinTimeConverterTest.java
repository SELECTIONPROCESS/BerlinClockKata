package selectionprocess.berlinclockkata.converter;

import org.junit.Assert;
import org.junit.Test;

public class BerlinTimeConverterTest {

    private BerlinTimeConverter berlinTimeConverter = new BerlinTimeConverter();

    @Test
    public void isValidDigitalTime() {
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("00:00:00"));
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("08:23:00"));
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("23:59:59"));
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("12:32:00"));
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("12:34:00"));
        Assert.assertTrue(berlinTimeConverter.isValidDigitalTime("12:35:00"));

        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("000000"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("24:00:00"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("2:22:22"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("11:60:11"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("22:22:99"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("33:33:33"));
        Assert.assertFalse(berlinTimeConverter.isValidDigitalTime("22:22:22.222"));
    }

    @Test
    public void getQuotient() {
        Assert.assertEquals(0, berlinTimeConverter.getQuotient(0, 5));
        Assert.assertEquals(0, berlinTimeConverter.getQuotient(2, 5));
        Assert.assertEquals(1, berlinTimeConverter.getQuotient(5, 5));
        Assert.assertEquals(4, berlinTimeConverter.getQuotient(20, 5));
        Assert.assertEquals(11, berlinTimeConverter.getQuotient(59, 5));
        Assert.assertEquals(13, berlinTimeConverter.getQuotient(66, 5));
    }

    @Test
    public void getRemainder() {
        Assert.assertEquals(0, berlinTimeConverter.getRemainder(0, 5));
        Assert.assertEquals(2, berlinTimeConverter.getRemainder(2, 5));
        Assert.assertEquals(0, berlinTimeConverter.getRemainder(5, 5));
        Assert.assertEquals(0, berlinTimeConverter.getRemainder(20, 5));
        Assert.assertEquals(4, berlinTimeConverter.getRemainder(59, 5));
        Assert.assertEquals(1, berlinTimeConverter.getRemainder(66, 5));
    }

    @Test
    public void getIntFromStringInRange() {
        Assert.assertEquals(0, berlinTimeConverter.getIntFromStringInRange("00:00:00", 0, 2));
        Assert.assertEquals(59, berlinTimeConverter.getIntFromStringInRange("23:59:59", 3, 5));
        Assert.assertEquals(11, berlinTimeConverter.getIntFromStringInRange("23:59:11", 6, 8));
    }

    @Test
    public void convert() {
        Assert.assertEquals("YOOOOOOOOOOOOOOOOOOOOOOO", berlinTimeConverter.convert("00:00:00"));
        Assert.assertEquals("ORRRRRRROYYRYYRYYRYYYYYY", berlinTimeConverter.convert("23:59:59"));
        Assert.assertEquals("ORRRRRRROYYRYYRYYRYYYYYY", berlinTimeConverter.convert("23:59:01"));
        Assert.assertEquals("YRRROROOOYYRYYRYYRYOOOOO", berlinTimeConverter.convert("16:50:06"));
        Assert.assertEquals("ORROOROOOYYRYYRYOOOOYYOO", berlinTimeConverter.convert("11:37:01"));
        Assert.assertEquals("ORROOROOOYYRYYRYOOOOYYOO", berlinTimeConverter.convert("11:37:01"));

        Assert.assertEquals("", berlinTimeConverter.convert("24:00:00"));
        Assert.assertEquals("", berlinTimeConverter.convert("000000"));
        Assert.assertEquals("", berlinTimeConverter.convert("24:00:00"));
        Assert.assertEquals("", berlinTimeConverter.convert("2:22:22"));
        Assert.assertEquals("", berlinTimeConverter.convert("11:60:11"));
        Assert.assertEquals("", berlinTimeConverter.convert("22:22:99"));
        Assert.assertEquals("", berlinTimeConverter.convert("33:33:33"));
        Assert.assertEquals("", berlinTimeConverter.convert("22:22:22.222"));
    }
}