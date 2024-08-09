package com.example.pracownikgenerator.passwords;

import java.util.List;

public interface PasswordGenerator {
    String generatePassword(List<PasswordRule> rules, int length);
}
