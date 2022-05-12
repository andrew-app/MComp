package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab2.databinding.FragmentFirstBinding;
import com.example.lab2.databinding.FragmentSecondBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding task_one;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        task_one = FragmentFirstBinding.inflate(inflater, container, false);
        return task_one.getRoot();

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        task_one.buttonFirst.setEnabled(false);
        task_one.switch1.setChecked(false);
        task_one.buttonFirst.setOnClickListener(View -> task_one.textView.setText(task_one.CustomMessage.getText()));
        task_one.switch1.setOnClickListener(View -> toggleSwitch());
        task_one.next.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        task_one = null;
    }

    public void toggleSwitch(){
        if (task_one.switch1.isChecked()){
            task_one.buttonFirst.setEnabled(true);
            task_one.textView.setText("Empty Message");
        }

        else{
            task_one.buttonFirst.setEnabled(false);
            task_one.textView.setText("Hello from Mobile Computing");
        }
    }

}