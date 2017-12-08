package market.android.cryptocurrency.com.cryptocurrencymarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import market.android.cryptocurrency.com.cryptocurrencymarket.R;
import market.android.cryptocurrency.com.cryptocurrencymarket.data.CryptoData;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.MyViewHolder> {

    private List<CryptoData> cryptoDataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        public TextView txtName;

        @BindView(R.id.txtSymbol)
        public TextView txtSymbol;

        @BindView(R.id.txtPrice)
        public TextView txtPrice;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public void changeList(List<CryptoData> cryptoDataList) {
        this.cryptoDataList = cryptoDataList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crypto_recyclerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CryptoData cryptoData = cryptoDataList.get(position);
        holder.txtName.setText(cryptoData.name);
        holder.txtSymbol.setText(cryptoData.symbol);
        holder.txtPrice.setText(String.valueOf(cryptoData.price_usd));
    }

    @Override
    public int getItemCount() {
        return cryptoDataList.size();
    }
}
