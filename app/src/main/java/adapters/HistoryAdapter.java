package adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artemka.ConversionHistory;
import com.example.artemka.R;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<ConversionHistory> historyList;

    public HistoryAdapter(List<ConversionHistory> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        ConversionHistory item = historyList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        holder.tvFrom.setText(String.format("%.2f %s", item.getAmount(), item.getFromCurrency()));
        holder.tvTo.setText(String.format("%.2f %s", item.getResult(), item.getToCurrency()));
        holder.tvDate.setText(sdf.format(item.getDate()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvFrom, tvTo, tvDate;

        HistoryViewHolder(View itemView) {
            super(itemView);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvTo = itemView.findViewById(R.id.tvTo);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}

