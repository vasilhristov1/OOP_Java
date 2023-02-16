package hw7.problem1;

public class MilesPerGallon {
    private double milesDriven;
    private double gallonsUsed;

    public MilesPerGallon() {
        setMilesDriven(0.0);
        setGallonsUsed(0.0);
    }

    public MilesPerGallon(double milesDriven, double gallonsUsed) {
        setMilesDriven(milesDriven);
        setGallonsUsed(gallonsUsed);
    }

    public MilesPerGallon(MilesPerGallon mpg) {
        setMilesDriven(mpg.getMilesDriven());
        setGallonsUsed(mpg.getGallonsUsed());
    }

    public void setMilesDriven(double milesDriven) {
        this.milesDriven = milesDriven;
    }

    public void setGallonsUsed(double gallonsUsed) {
        this.gallonsUsed = gallonsUsed;
    }

    public double getMilesDriven() {
        return milesDriven;
    }

    public double getGallonsUsed() {
        return gallonsUsed;
    }

    public double calculate() {
        double milesPerGallon = 0.0;

        milesPerGallon = getMilesDriven() / getGallonsUsed();

        return milesPerGallon;
    }
}
