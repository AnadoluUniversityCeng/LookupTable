package edu.eskisehir;

public class Record {

    private final double  Z,F,L;

    @Override
    public String toString() {
        return "Record{" +
                "Z=" + Z +
                ", F=" + F +
                ", L=" + L +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (Double.compare(record.F, F) != 0) return false;
        if (Double.compare(record.Z, Z) != 0) return false;
        return Double.compare(record.L, L) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(F);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(L);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * # Z	F(Z)	L(Z)

     *
     * @param z
     * @param l
     */
    public Record(double z, double f, double l) {

        F = f;
        Z = z;
        L = l;
    }

    public double getF() {
        return F;
    }

    public double getZ() {
        return Z;
    }

    public double getL() {
        return L;
    }

    static Record fromString(String line) {
        String[] parts = line.split("\\s+");
        return new Record(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]));

    }
}
