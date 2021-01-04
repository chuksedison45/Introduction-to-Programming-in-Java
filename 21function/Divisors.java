public class Divisors {

    // Returns the greatest common divisor of a and b.
    public static int gcd(int a, int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);

        if (a == 0) {
            return y;
        } else if (b == 0) {
            return x;
        } else {
            if (y > x) {
                int temp = x;
                x = y;
                y = temp;
            }
			while (y != 0) {
            int temp = x;
            x = y;
            y = temp % y;
			}
			return x;
		}
		

        
    }

    // Returns the least common multiple of a and b.
    public static int lcm(int a, int b) {
		
        int x = Math.abs(a);
        int y = Math.abs(b);
        if (a == 0 && b == 0) {
            return 0;
        }
        else {
            return x /gcd(a, b) * y;
        }

    }

    // Returns true if a and b are relatively prime; false otherwise.
    public static boolean areRelativelyPrime(int a, int b) {
        if (gcd(a, b) == 1) {
            return true;
        } else
            return false;
    }

    // Returns the number of integers between 1 and n that are
    // relatively prime with n.
    public static int totient(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (areRelativelyPrime(i, n)) {
                count++;
            }
        }
        return count;
    }

    // Takes two integer command-line arguments a and b and prints
    // each function, evaluated in the format (and order) given below.
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        StdOut.println("gcd(" + x + "," + y + ") = " + gcd(x, y));
        StdOut.println("lcm(" + x + "," + y + ") = " + lcm(x, y));
        StdOut.println("areRelativelyPrime(" + x + "," + y + ") = " + areRelativelyPrime(x, y));
        StdOut.println("totient(" + x + ") = " + totient(x));
        StdOut.println("totient(" + y + ") = " + totient(y));
    }
}
