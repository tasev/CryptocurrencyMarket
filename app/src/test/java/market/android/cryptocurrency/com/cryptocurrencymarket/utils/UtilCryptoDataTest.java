package market.android.cryptocurrency.com.cryptocurrencymarket.utils;

import org.junit.Test;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by tasev on 12/9/17.
 */
public class UtilCryptoDataTest {

    @Test
    public void testGetPriceInValWhenCryptoData() {

        //given
        CryptoData cryptoData = mock(CryptoData.class);
        cryptoData.price_eur = 63.05;
        String val = "EUR";
        double expectedResult = 63.05;

        //when
        double result = UtilCryptoData.getPriceInVal(cryptoData, val);

        //then
        assertEquals(expectedResult, result, 0);
    }

    @Test
    public void testGetPriceInValWhenNull() {

        //given
        CryptoData cryptoData = mock(CryptoData.class);
        String val = "EUR";
        double expectedResult = 0;

        //when
        double result = UtilCryptoData.getPriceInVal(cryptoData, val);

        //then
        assertEquals(expectedResult, result, 0);
    }

    @Test
    public void testGetMarketCapInValWhenNull() {

        //given
        CryptoData cryptoData = mock(CryptoData.class);
        String val = "EUR";
        double expectedResult = 0;

        //when
        double result = UtilCryptoData.getMarketCapInVal(cryptoData, val);

        //then
        assertEquals(expectedResult, result, 0);
    }

    @Test
    public void testGetMarketCapInValWhenCryptoData() {

        //given
        CryptoData cryptoData = mock(CryptoData.class);
        cryptoData.market_cap_eur = 63.05;
        String val = "EUR";
        double expectedResult = 63.05;

        //when
        double result = UtilCryptoData.getMarketCapInVal(cryptoData, val);

        //then
        assertEquals(expectedResult, result, 0);
    }

}