package ir.unary.zhin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterNavAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    Context mContext;
    ArrayList<String> buffer = new ArrayList<>();

    private ArrayList<Boolean> chechedArr = new ArrayList<>();

    public FilterNavAdapter(Context mContext) {
        this.mContext = mContext;
        fillBuffer();
        cheched_mth(0);
    }

    private void fillBuffer() {

        buffer.add("مطب");
        buffer.add("آزمایشگاه");
        buffer.add("سونوگرافی");
        buffer.add("بیمارستان");
        buffer.add("کمپ ترک اعتیاد");
        buffer.add("سایر");

        for (int i = 0; i < buffer.size(); i++) {
            chechedArr.add(false);
        }
        chechedArr.set(0,true);


    }


    private void cheched_mth(int k) {


        if (!chechedArr.get(k)) {
            chechedArr.set(k, true);

            notifyItemChanged(k);

            for (int i = 0; i < chechedArr.size(); i++) {
                if (i != k && chechedArr.get(i)) {
                    chechedArr.set(i, false);
                    notifyItemChanged(i);
                }
            }

        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.filter_nav_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_filter_title.setText(buffer.get(position));


        if (!chechedArr.get(position)) {
            ((ViewHolder) holder).cv_filter_title.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.filterNavColorNotSelected));
        } else {
            ((ViewHolder) holder).cv_filter_title.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.filterNavColorSelected));
        }
    }


    @Override
    public int getItemCount() {
        return buffer.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_filter_title;
        CardView cv_filter_title;


        ViewHolder(View itemView) {
            super(itemView);
            tv_filter_title=itemView.findViewById(R.id.tv_filter_title);
            cv_filter_title=itemView.findViewById(R.id.cv_filter_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            cheched_mth(getAdapterPosition());
        }
    }
}
