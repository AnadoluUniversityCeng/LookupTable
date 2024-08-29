package edu.eskisehir;

import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import org.apache.commons.math3.distribution.NormalDistribution;

import static org.apache.commons.math3.distribution.NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY;

@Command(name = "baeldung-cli", description = "Baeldung Airline Tutorial")
public class CommandLine {

    private transient final NormalDistribution normalDistribution = new NormalDistribution();

    @Option(name = {"-u", "--unit"}, description = "A unit cost is the total expenditure incurred by a company to produce, store, and sell one unit of a particular product or service.")
    private int unitCost = 20;

    @Option(name = {"-p", "--penalty"}, description = "Penalty cost is the cost that is associated with factors such as late or early deliveries and bid adjustment factors")
    private int penaltyCost = 20;

    @Option(name = {"-s", "--setup"}, description = "Setup costs refer to all of the costs associated with actually ordering the inventory, such as the costs of packaging, delivery, shipping, and handling.")
    private int setupCost = 100;

    @Option(name = {"-i", "--interest"}, description = "The interest rate is the amount lenders charge borrowers and is a percentage of the principal. It is also the amount earned from deposit accounts.")
    private int interestRate = 25;


    /*
    @Option(name = {"-h", "--holding"}, description = "Holding costs refer to all the costs associated with holding additional inventory on hand.
     Those costs include warehousing and logistical costs, insurance costs, material handling costs, inventory write-offs, and depreciation.")
    private double holdingCost = ((double) (unitCost * interestRate)) / 100D;
    */

    /**
     * Holding costs refer to all the costs associated with holding additional inventory on hand.
     * Those costs include warehousing and logistical costs, insurance costs, material handling costs, inventory write-offs, and depreciation.
     *
     * @return the holding cost per unit per year (H).
     */
    double holdingCost() {
        return ((double) (unitCost * interestRate)) / 100D;
    }


    /**
     * The lead demand (also called lead time demand) is the total demand between now and the anticipated time for the delivery after the next one
     * if a reorder is made now to replenish the inventory. This delay is named the lead time. Since lead demand is a future demand (not yet observed),
     * this value is typically anticipated/estimated or forecasted.
     */

    @Option(name = {"-d", "--demand"}, description = "the time between initially placing a replenishment order, and the replenishment stock hitting the shelf.")
    private int leadDemand = 500;

    @Option(name = {"-t", "--time"}, description = "the time [in months] between initially placing a replenishment order, and the replenishment stock hitting the shelf.")
    private int leadTime = 4;

    /**
     * @return the annual demand (D),
     */
    private double annualDemand() {
        return (12D / leadTime) * (double) leadDemand;
    }

    @Option(name = {"-v", "--variance"}, description = "Standard deviation is a statistic that measures the dispersion of a dataset relative to its mean and is calculated as the square root of the variance.")
    private int standardDeviation = 100;

    /**
     * Economic order quantity (EOQ) is a formula that helps you calculate the optimal amount of inventory to order for each product.
     * You need to have the following data for each product:
     * the annual demand (D),
     * the fixed cost per order (S),
     * the holding cost per unit per year (H).
     * Then, you can use the following formula to calculate the EOQ: =SQRT(2*D*S/H).
     *
     * @return Economic Order Quantity
     */
    double economicOrderQuantity() {
        return Math.sqrt(2D * annualDemand() * setupCost / holdingCost());
    }

    @Override
    public String toString() {
        return "CommandLine{" +
                "unitCost=" + unitCost +
                ", penaltyCost=" + penaltyCost +
                ", setupCost=" + setupCost +
                ", interestRate=" + interestRate +
                ", leadDemand=" + leadDemand +
                ", leadTime=" + leadTime +
                ", standard deviation=" + standardDeviation +
                ", annual demand=" + annualDemand() +
                ", holding cost=" + holdingCost() +
                ", EOQ=Q_0=" + economicOrderQuantity() +
                ", F(R)=" + fR(economicOrderQuantity()) +
                ", z_0=" + z(economicOrderQuantity()) +
                ", R_0=" + (leadDemand + z(economicOrderQuantity()) * standardDeviation) +
                ", total cost=" + totalCost() +
                ", Q_1=" + Q(z(economicOrderQuantity())) +
                ", R_1=" + (leadDemand + z(Q(z(economicOrderQuantity()))) * standardDeviation) +
                '}';
    }

    /**
     * You can also create a table with the EOQ and the total cost for different order quantities to compare and choose the best option.
     * The total cost is calculated as: =D/Q*S+Q/2*H, where Q is the order quantity.
     *
     * @param Q is the order quantity
     * @return the total cost
     */
    double totalCost(double Q) {
        return annualDemand() / Q * setupCost + Q / 2 * holdingCost();
    }

    double totalCost() {
        return totalCost(economicOrderQuantity());
    }

    double fR(double Q) {
        return 1 - (Q * holdingCost()) / (penaltyCost * annualDemand());
    }

    double z(double Q) {
        return normalDistribution.inverseCumulativeProbability(fR(Q));
    }

    /**
     * The expected number of lost sales as a fraction of the standard deviation.
     *
     * @param z z-value
     * @return the standard loss function
     */
    private double L(double z) {
        double CDF = normalDistribution.cumulativeProbability(z);
        double PDF = normalDistribution.density(z);

        // the standard loss function
        return PDF - z * (1 - CDF);
    }

    /**
     * Hence, the lost sales = L(Z) x lead standard deviation of demand
     *
     * @param z z-value
     * @return the lost sales
     */
    double lostSales(double z) {
        return L(z) * standardDeviation;
    }

    double Q(double z) {
        return Math.sqrt(
                (2 * annualDemand() * (setupCost + penaltyCost * lostSales(z))) / holdingCost()
        );
    }


    public static void premain(String[] args) {
        Cli<Runnable> cli = new Cli<>(CommandLine.class);
        Runnable cmd = cli.parse(args);
        cmd.run();
    }

    public static void main(String[] args) {
        SingleCommand<CommandLine> parser = SingleCommand.singleCommand(CommandLine.class);
        CommandLine cmd = parser.parse(args);
        System.out.println(cmd.toString());
        cmd.solve();
    }

    public void solve() {
        double Q_previous = economicOrderQuantity();
        double z_previous = z(Q_previous);
        double R_previous = leadDemand + z_previous * standardDeviation;

        System.out.println("i=0\tQ=" + Q_previous + "\tR=" + R_previous + "\tz=" + z_previous + "\ttotalCost=" + totalCost());

        int i = 1;
        while (true) {
            double Q = Q(z_previous);
            double z = z(Q);
            double R = leadDemand + z * standardDeviation;

            System.out.println("i=" + i + "\tQ=" + Q + "\tR=" + R + "\tz=" + z + "\ttotalCost=" + totalCost(Q));
            if (Math.abs(Q - Q_previous) < DEFAULT_INVERSE_ABSOLUTE_ACCURACY && Math.abs(R - R_previous) < DEFAULT_INVERSE_ABSOLUTE_ACCURACY)
                break;

            Q_previous = Q;
            R_previous = R;
            z_previous = z;
            i++;
        }
    }
}
