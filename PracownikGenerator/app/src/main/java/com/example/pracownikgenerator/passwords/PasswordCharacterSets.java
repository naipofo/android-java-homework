package com.example.pracownikgenerator.passwords;

import java.util.Random;

public class PasswordCharacterSets {
    static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
    static char[] special = {'+', '-', '_'};
    static char randomFrom(char[] list){
        return list[(new Random()).nextInt(list.length)];
    }
}
