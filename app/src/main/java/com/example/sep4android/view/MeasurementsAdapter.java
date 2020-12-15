package com.example.sep4android.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.R;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.RecyclerItemMeasurementsBinding;

import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.MeasurementViewHolder> {

    private RecyclerItemMeasurementsBinding binding;

    private List<Measurements> measurementsList;

    public MeasurementsAdapter(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;
    }

    @NonNull
    @Override
    public MeasurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item_measurements, parent, false);
        MeasurementViewHolder measurementViewHolder= new MeasurementViewHolder();
        return measurementViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder holder, int position) {
        // binding.setMeasurements(measurementsList.get(position));
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return measurementsList==null?0:measurementsList.size();
    }

    class MeasurementViewHolder extends RecyclerView.ViewHolder{

        public MeasurementViewHolder() {
            super(binding.getRoot());
        }
    }

    public void intentToDetail(){
        
    }
}
