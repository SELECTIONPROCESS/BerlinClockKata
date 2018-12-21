package selectionprocess.berlinclockkata.converter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalTimeConverterTest {

    private DigitalTimeConverter digitalTimeConverter = new DigitalTimeConverter();

    @Test
    public void convert() {
        Assert.assertEquals("00:00:00", digitalTimeConverter.convert("YOOOOOOOOOOOOOOOOOOOOOOO"));
        Assert.assertEquals("23:59:01", digitalTimeConverter.convert("ORRRRRRROYYRYYRYYRYYYYYY"));
        Assert.assertEquals("16:50:00", digitalTimeConverter.convert("YRRROROOOYYRYYRYYRYOOOOO"));
        Assert.assertEquals("11:37:01", digitalTimeConverter.convert("ORROOROOOYYRYYRYOOOOYYOO"));
    }
}