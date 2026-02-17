package org.apache.commons.collections4;

public class Coverage {

    public static final boolean[] coverage_array  = new boolean[600]; // IDs 0..199
    private static boolean endPrint = false;

    public static void hit(boolean[] arr, int id) {
        // Mark your
        arr[id] = true;

        if (!endPrint) {
            endPrint = true;
            Runtime.getRuntime().addShutdownHook(new Thread(Coverage::reportAll, "DIYCoverageReport"));
        }
    }

    private static void reportAll() {
        System.out.println("\n==== BRANCH COVERAGE ====");

        System.out.print("HIT: ");
        for (int i = 0; i < coverage_array.length; i++) {
            if (coverage_array[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println("=============================\n");
    }
}
