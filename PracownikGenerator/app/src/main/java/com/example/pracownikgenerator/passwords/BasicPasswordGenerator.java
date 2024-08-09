package com.example.pracownikgenerator.passwords;

import java.util.List;

public class BasicPasswordGenerator implements PasswordGenerator{

    @Override
    public String generatePassword(List<PasswordRule> rules, int length) {
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = PasswordCharacterSets.randomFrom(PasswordCharacterSets.letters);
        }
        if (rules.contains(PasswordRule.HAS_CAPITAL) && length > 0){
            password[0] = Character.toUpperCase(PasswordCharacterSets.randomFrom(PasswordCharacterSets.letters));
        }
        if (rules.contains(PasswordRule.HAS_NUMBER) && length > 1){
            password[1] = PasswordCharacterSets.randomFrom(PasswordCharacterSets.numbers);
        }
        if (rules.contains(PasswordRule.HAS_SPECIAL) && length > 2){
            password[2] = PasswordCharacterSets.randomFrom(PasswordCharacterSets.special);
        }

        return new String(password);
    }
}
