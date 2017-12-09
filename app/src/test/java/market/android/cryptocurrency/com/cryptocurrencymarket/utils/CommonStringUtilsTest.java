package market.android.cryptocurrency.com.cryptocurrencymarket.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tasev on 12/9/17.
 */
public class CommonStringUtilsTest {

    @Test
    public void testGetNonNullStringWhenNotNull() {
        //given
        String text = "text";
        String expectedResult = "text";

        //when
        String result = CommonStringUtils.getNonNullString(text);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNonNullStringWhenNull() {
        //given
        String expectedResult = "";

        //when
        String result = CommonStringUtils.getNonNullString(null);

        //then
        assertEquals(expectedResult, result);
    }

}