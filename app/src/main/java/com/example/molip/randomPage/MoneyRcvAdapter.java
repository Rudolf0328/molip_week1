package com.example.molip.randomPage;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.molip.R;

import java.util.ArrayList;
import java.util.List;

public class MoneyRcvAdapter extends RecyclerView.Adapter<MoneyRcvAdapter.ViewHolder> {
    private List<String> etList;
    private List<String> tvList = new ArrayList<>();
    Context context;

//    public void submitList(List<String> tvs) {
//        rcvList = tvs;
//        notifyDataSetChanged();
//    }

    public MoneyRcvAdapter(List<String> etList, List<String> tvList) {
        this.etList = etList;
        this.tvList = tvList;
//        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.money_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, new MyCustomEditTextListener());
        System.out.println("on create view holder");
        return viewHolder;
    }

    public int getItemCount() {
        return etList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llMoney;
        EditText etName;
        TextView tvMoney;
        MyCustomEditTextListener myCustomEditTextListener;

        public ViewHolder(View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);
            llMoney = (LinearLayout) itemView.findViewById(R.id.money_ll);
            etName = (EditText) itemView.findViewById(R.id.money_item_et_name);
            tvMoney = (TextView) itemView.findViewById(R.id.money_item_tv_money);
            System.out.println("view holder");
            this.myCustomEditTextListener = myCustomEditTextListener;
            this.etName.addTextChangedListener(myCustomEditTextListener);
        }

        void onBind(String money) {
//            System.out.println(ll);
            System.out.println(money);
            tvMoney.setHint(money);
            System.out.println("on bind");
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println(etList + " " + holder.getAdapterPosition() + " " + holder);
        System.out.println(holder.etName);
        System.out.println(holder.tvMoney);
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.etName.setHint(etList.get(position));
        holder.tvMoney.setText(tvList.get(position));
        System.out.println(holder.etName.getText());
        System.out.println(holder.tvMoney.getText());
        System.out.println("here?");

        System.out.println("on bind view holder");
//        holder.
//        position += 1;
//        holder.etName.setHint(etList.get(position));
//        [holder.getAdapterPosition()]
//        holder.onBind(rcvList.get(position));
    }

//    private void removeItemView(int position) {
//        rcvList.remove(position);
//        notifyDataSetChanged();
//    }

//    public void deleteList(TextView tv, )

//    private void addItemView(LinearLayout ll) {
//        moneyList.add(ll);
//        notifyDataSetChanged();
//    }
    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            System.out.println("herehere??");
            etList.add(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }
}
