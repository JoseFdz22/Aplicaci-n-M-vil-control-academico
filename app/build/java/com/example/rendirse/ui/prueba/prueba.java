package com.example.rendirse.ui.prueba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rendirse.databinding.FragmentGalleryBinding;
import com.example.rendirse.databinding.FragmentPruebaBinding;
import com.example.rendirse.ui.gallery.GalleryViewModel;


public class prueba extends Fragment {

    private FragmentPruebaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PruebaViewModel pruebaViewModel =
                new ViewModelProvider(this).get(PruebaViewModel.class);

        binding = FragmentPruebaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPrueba;
        pruebaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}