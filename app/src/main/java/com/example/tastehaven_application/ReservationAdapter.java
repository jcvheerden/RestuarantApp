package com.example.tastehaven_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private ArrayList<Reservation> reservationList;

    public ReservationAdapter(ArrayList<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.customerName.setText(reservation.getUserName());
        holder.reservationDate.setText(reservation.getReservationDate());
        holder.reservationTime.setText(reservation.getReservationTime());
        holder.numberOfGuests.setText(String.valueOf(reservation.getNumberOfGuests()));
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView customerName, reservationDate, reservationTime, numberOfGuests;

        public ReservationViewHolder(View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerName);
            reservationDate = itemView.findViewById(R.id.reservationDate);
            reservationTime = itemView.findViewById(R.id.reservationTime);
            numberOfGuests = itemView.findViewById(R.id.numberOfGuests);
        }
    }
}
