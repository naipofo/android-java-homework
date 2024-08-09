package com.example.pracownikgenerator.passwords;

import android.widget.CheckBox;

import androidx.annotation.IdRes;

import com.example.pracownikgenerator.R;

import java.util.ArrayList;
import java.util.List;

public class RuleExtractorBuilder {
    List<PasswordRule> rules = new ArrayList<>();
    List<Integer> boxes = new ArrayList<>();

    public RuleExtractorBuilder checkBox(@IdRes int id, PasswordRule rule){
        rules.add(rule);
        boxes.add(id);
        return this;
    }

    public RuleExtractor build(){
        return new RuleExtractor(rules, boxes);
    }
}
