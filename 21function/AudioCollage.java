public class AudioCollage {
    // Returns a new array that rescales a[] by a multiplicative factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = alpha * a[i];
        }
        return result;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        int n = a.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = a[n - (i + 1)];
        }
        return result;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        int n = a.length;
        int m = b.length;
        double[] result = new double[n + m];

        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                result[i] = a[i];
            } else
                result[i] = b[i - n];
        }
        return result;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter arrays with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        int n = a.length;
        int m = b.length;
        if (m > n) {
            double[] result = new double[m];
            for (int i = 0; i < m; i++) {
                if (i < n) {
                    result[i]  = a[i] + b[i];
                }
                else {
                    result[i] = b[i];
                }

            }
            return result;
        } else if (m < n) {
            double[] result = new double[n];
            for (int i = 0; i < n; i++) {
                if (i < m) {
                    result[i] = b[i] + a[i];
                } else
                    result[i] = a[i];
            }
            return result;
        }
        else {
            double[] result = new double[m];
            for (int i = 0; i < m; i++) {
                result[i] = a[i] + b[i];
            }
            return result;
        }

    }

    // Returns a new array that changes the speed by the given factor.
    public static double[] changeSpeed(double[] a, double alpha) {
        int n = (int) (a.length/alpha);
        double[] result = new double[n];
        for (int i = 0; i < n; i++) result[i] = a[(int) (i * alpha)];
        return result;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
        double alpha = 0.25;
        double duration = 8.0;

        int n = (int) (StdAudio.SAMPLE_RATE * duration);


        double [] a = StdAudio.read("beatbox.wav");
        double[] b = new double[n+1];
        int m = a.length;
        for (int i = 0; i <= n; i++) {
            b[i] = a[i % m];
        }
        StdAudio.play(b);
        double[] amplifyA = amplify(b, alpha);
        StdAudio.play(amplifyA);
        double [] c = reverse(b);
        StdAudio.play(c);
        double[] bmergeA = merge(StdAudio.read("buzzer.wav"), StdAudio.read("cow.wav"));
        for (int i = 0; i <= n; i++) {
            b[i] = bmergeA[i % bmergeA.length];
        }
        StdAudio.play(b);
        double[] bmixA = mix(StdAudio.read("dialup.wav"), StdAudio.read("chimes.wav"));
        for (int i = 0; i <= n; i++) {
            b[i] = bmixA[i % bmixA.length];
        }
        StdAudio.play(b);
        double[] speedChange = changeSpeed(StdAudio.read("cow.wav"), alpha);
        for (int i = 0; i <= n; i++) {
            b[i] = speedChange[i % speedChange.length];
        }
        StdAudio.play(b);
    }
}
