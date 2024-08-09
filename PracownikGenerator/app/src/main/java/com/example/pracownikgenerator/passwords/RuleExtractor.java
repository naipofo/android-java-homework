package com.example.pracownikgenerator.passwords;

import android.widget.CheckBox;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RuleExtractor {
    List<PasswordRule> rules;

    @IdRes
    List<Integer> ids;
    RuleExtractor(List<PasswordRule> rules, List<Integer> ids){
        this.ids = ids;
        this.rules = rules;
    }

    public List<PasswordRule> extract(AppCompatActivity activity){
        ArrayList<PasswordRule> extracted = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            if (((CheckBox) activity.findViewById(ids.get(i))).isChecked()){
                extracted.add(rules.get(i));
            }
        }
        return extracted;
    }
}
