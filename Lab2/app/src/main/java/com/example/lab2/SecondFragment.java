package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.lab2.databinding.FragmentSecondBinding;

import java.lang.reflect.Array;

public class SecondFragment extends Fragment {
    StringBuilder viewValue = new StringBuilder();

    int prevState;


    String currentValue;

    private FragmentSecondBinding task_two;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        task_two = FragmentSecondBinding.inflate(inflater, container, false);
        return task_two.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        task_two.decimal.setOnClickListener(View -> stateDecimal());
        task_two.binary.setOnClickListener(View -> stateBinary());
        task_two.hexadecimal.setOnClickListener(View -> stateHex());

    }

    public void setNumbers(){
        task_two.btnTwo.setEnabled(true);
        task_two.btnThree.setEnabled(true);
        task_two.btnFour.setEnabled(true);
        task_two.btnFive.setEnabled(true);
        task_two.btnSix.setEnabled(true);
        task_two.btnSeven.setEnabled(true);
        task_two.btnEight.setEnabled(true);
        task_two.btnNine.setEnabled(true);
        task_two.btnTwo.setVisibility(View.VISIBLE);
        task_two.btnThree.setVisibility(View.VISIBLE);
        task_two.btnFour.setVisibility(View.VISIBLE);
        task_two.btnFive.setVisibility(View.VISIBLE);
        task_two.btnSix.setVisibility(View.VISIBLE);
        task_two.btnSeven.setVisibility(View.VISIBLE);
        task_two.btnEight.setVisibility(View.VISIBLE);
        task_two.btnNine.setVisibility(View.VISIBLE);

        task_two.btnOne.setOnClickListener(view->task_two.textView2.setText(viewValue.append("1")));
        task_two.btnTwo.setOnClickListener(view->task_two.textView2.setText(viewValue.append("2")));
        task_two.btnThree.setOnClickListener(view->task_two.textView2.setText(viewValue.append("3")));
        task_two.btnFour.setOnClickListener(view->task_two.textView2.setText(viewValue.append("4")));
        task_two.btnFive.setOnClickListener(view->task_two.textView2.setText(viewValue.append("5")));
        task_two.btnSix.setOnClickListener(view->task_two.textView2.setText(viewValue.append("6")));
        task_two.btnSeven.setOnClickListener(view->task_two.textView2.setText(viewValue.append("7")));
        task_two.btnEight.setOnClickListener(view->task_two.textView2.setText(viewValue.append("8")));
        task_two.btnNine.setOnClickListener(view->task_two.textView2.setText(viewValue.append("9")));
        task_two.btnZero.setOnClickListener(view->task_two.textView2.setText(viewValue.append("0")));

    }

    public void disableAlpha(){
        task_two.btnA.setEnabled(false);
        task_two.btnB.setEnabled(false);
        task_two.btnC.setEnabled(false);
        task_two.btnD.setEnabled(false);
        task_two.btnE.setEnabled(false);
        task_two.btnF.setEnabled(false);
        task_two.btnA.setVisibility(View.INVISIBLE);
        task_two.btnB.setVisibility(View.INVISIBLE);
        task_two.btnC.setVisibility(View.INVISIBLE);
        task_two.btnD.setVisibility(View.INVISIBLE);
        task_two.btnE.setVisibility(View.INVISIBLE);
        task_two.btnF.setVisibility(View.INVISIBLE);
//
    }

    public void setAlpha(){
        task_two.btnA.setEnabled(true);
        task_two.btnB.setEnabled(true);
        task_two.btnC.setEnabled(true);
        task_two.btnD.setEnabled(true);
        task_two.btnE.setEnabled(true);
        task_two.btnF.setEnabled(true);
        task_two.btnA.setVisibility(View.VISIBLE);
        task_two.btnB.setVisibility(View.VISIBLE);
        task_two.btnC.setVisibility(View.VISIBLE);
        task_two.btnD.setVisibility(View.VISIBLE);
        task_two.btnE.setVisibility(View.VISIBLE);
        task_two.btnF.setVisibility(View.VISIBLE);
    }

    public void setRemove(){
        task_two.btnClear.setOnClickListener(view->task_two.textView2.setText(viewValue.delete(0,viewValue.length())));
        task_two.btnDelete.setOnClickListener(view->task_two.textView2.setText(deleteVal()));
    }


    public void stateDecimal(){

        if (prevState == 2 & viewValue.length() != 0){
            currentValue = Integer.toString(Integer.parseInt(viewValue.toString(),2));
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }

        if (prevState == 3 & viewValue.length() != 0){
            currentValue = Integer.toString(Integer.parseInt(viewValue.toString(),16));
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }

        setNumbers();
        setRemove();
        disableAlpha();
        prevState = 1;


    }
    public void stateBinary(){

        if (prevState == 1 & viewValue.length() != 0){
            currentValue = Integer.toBinaryString(Integer.parseInt(viewValue.toString()));
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }

        if (prevState == 3 & viewValue.length() != 0){
            currentValue = Integer.toBinaryString(Integer.parseInt(viewValue.toString(),16));
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }
        task_two.btnTwo.setEnabled(false);
        task_two.btnThree.setEnabled(false);
        task_two.btnFour.setEnabled(false);
        task_two.btnFive.setEnabled(false);
        task_two.btnSix.setEnabled(false);
        task_two.btnSeven.setEnabled(false);
        task_two.btnEight.setEnabled(false);
        task_two.btnNine.setEnabled(false);
        task_two.btnTwo.setVisibility(View.INVISIBLE);
        task_two.btnThree.setVisibility(View.INVISIBLE);
        task_two.btnFour.setVisibility(View.INVISIBLE);
        task_two.btnFive.setVisibility(View.INVISIBLE);
        task_two.btnSix.setVisibility(View.INVISIBLE);
        task_two.btnSeven.setVisibility(View.INVISIBLE);
        task_two.btnEight.setVisibility(View.INVISIBLE);
        task_two.btnNine.setVisibility(View.INVISIBLE);
        disableAlpha();
        setRemove();

        task_two.btnOne.setOnClickListener(view->task_two.textView2.setText(viewValue.append("1")));
        task_two.btnZero.setOnClickListener(view->task_two.textView2.setText(viewValue.append("0")));
        prevState = 2;

    }

    public void stateHex(){
        if (prevState == 1 & viewValue.length() != 0){
            currentValue = Integer.toHexString(Integer.parseInt(viewValue.toString())).toUpperCase();
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }

        if (prevState == 2 & viewValue.length() != 0){
            currentValue = Integer.toHexString(Integer.parseInt(viewValue.toString(),2)).toUpperCase();
            viewValue.replace(0,viewValue.length(),currentValue);
            task_two.textView2.setText(viewValue);
        }

        setAlpha();
        task_two.btnA.setOnClickListener(view->task_two.textView2.setText(viewValue.append("A")));
        task_two.btnB.setOnClickListener(view->task_two.textView2.setText(viewValue.append("B")));
        task_two.btnC.setOnClickListener(view->task_two.textView2.setText(viewValue.append("C")));
        task_two.btnD.setOnClickListener(view->task_two.textView2.setText(viewValue.append("D")));
        task_two.btnE.setOnClickListener(view->task_two.textView2.setText(viewValue.append("E")));
        task_two.btnF.setOnClickListener(view->task_two.textView2.setText(viewValue.append("F")));
        setNumbers();
        setRemove();
        prevState = 3;
    }

    public String deleteVal(){
        if(viewValue.length() != 0){
            viewValue.deleteCharAt(viewValue.length()-1);
        }

        return viewValue.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        task_two = null;
    }

}