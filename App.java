import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

public class App {
	private final OutputStream output;

	public App(final OutputStream output) {
		this.output = output;
	}

	public static void main(final String... args) {
		new App(System.out).exec();
	}

	public void exec() {
		try (final PrintStream out = new PrintStream(output)) {
			final PerfectNumbersCalculator pnc = new PerfectNumbersCalculator();
			pnc.calculate(20_000);

			out.println("Deficient: " + pnc.deficientCount());
			out.println("Perfect: " + pnc.perfectCount());
			out.println("Abundant: " + pnc.abundantCount());

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}

class PerfectNumbersCalculator {
	private List<Integer> deficients;
	private List<Integer> perfects;
	private List<Integer> abundants;
	private List<Integer> divisors;

	public PerfectNumbersCalculator() {
		deficients = new ArrayList<>();
		perfects = new ArrayList<>();
		abundants = new ArrayList<>();
		divisors = new ArrayList<>();
	}

	public int deficientCount() {
		return deficients.size();
	}

	public int perfectCount() {
		return perfects.size();
	}

	public int abundantCount() {
		return abundants.size();
	}

	private void findDivisors(final int number) {
		for (int i = 1; i < number / 2 + 1; i++) {
			if (number % i == 0) {
				divisors.add(i);
			}
		}
	}

	public void calculate(final int number) {
		for (int i = 1; i <= number; i++) {
			findDivisors(i);
			int sum = 0;
			for (Integer divisor: divisors) {
				sum += divisor;
			}
			if (sum < i) {
				deficients.add(i);
			}
			if (sum == i) {
				perfects.add(i);
			}
			if (sum > i) {
				abundants.add(i);
			}
			divisors.clear();
		}

	}

}
