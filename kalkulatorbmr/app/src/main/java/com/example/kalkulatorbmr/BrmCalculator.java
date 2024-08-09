package com.example.kalkulatorbmr;

public class BrmCalculator {
    static double calculate(boolean isMale, double weight, double height, double age){
        return (isMale ? 88.362 : 447.593) +
                (isMale ? 13.397 : 9.247) * weight +
                (isMale ? 4.799 : 3.098) * height -
                (isMale ? 5.677 : 4.330) * age;
    }
}
