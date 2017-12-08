package market.android.cryptocurrency.com.cryptocurrencymarket.api;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tasev on 12/8/17.
 */

public interface ApiService {

    //get criptoDatas
    @GET("ticker")
    Call<List<CryptoData>> getCryptoDatas(@Query("convert") String convertVal, @Query("limit") int limit);

    //get criptoCurrencyDetails
    @GET("ticker/{id}")
    Call<List<CryptoData>> getCryptoCurrencyDetails(@Path("id") String cryptoId, @Query("convert") String convertVal);
}
