package com.example.datacalc;

public class UnitMeasure {
    public String name;
    public long value;

    public UnitMeasure(String name, long value) {
        this.name = name;
        this.value = value;
    }

    static UnitMeasure[] units = new UnitMeasure[] {
            new UnitMeasure("bit", 1),
            new UnitMeasure("bajt", 8),
            new UnitMeasure("kilobit", 1024),
            new UnitMeasure("kilobajt", 8 * 1024),
            new UnitMeasure("megabit", 1024 * 1024),
            new UnitMeasure("megabajt", 8 * 1024 * 1024),
            new UnitMeasure("gigabit", 1024L * 1024 * 1024),
            new UnitMeasure("gigabajt", 8L * 1024 * 1024 * 1024),
            new UnitMeasure("terabit", 1024L * 1024 * 1024 * 1024),
            new UnitMeasure("terabajt", 8L * 1024 * 1024 * 1024 * 1024)
    };
}
