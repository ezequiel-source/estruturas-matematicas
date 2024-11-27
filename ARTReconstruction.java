/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author user
 */
import java.util.Arrays;

public class ARTReconstruction {

    public static void main(String[] args) {
        // Example: 4x4 image grid, 8 projections
        int n = 4; // image size (n x n)
        int m = 8; // number of projections
        double[][] A = {
            {1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0}
        };
        
        double[] b = {2, 3, 2, 1, 3, 3, 2, 1}; // example projection data
        
        // Initialize image estimate with zeros
        double[] x = new double[n * n];
        Arrays.fill(x, 0.0);
        
        // ART iteration
        int maxIterations = 100;
        double relaxationFactor = 0.1;
        
        for (int iter = 0; iter < maxIterations; iter++) {
            for (int i = 0; i < m; i++) {
                double aiDotX = 0.0;
                for (int j = 0; j < n * n; j++) {
                    aiDotX += A[i][j] * x[j];
                }
                double error = b[i] - aiDotX;
                for (int j = 0; j < n * n; j++) {
                    x[j] += relaxationFactor * error * A[i][j];
                }
            }
        }
        
        // Display the reconstructed image
        System.out.println("Reconstructed Image:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%.4f ", x[i]);
            if ((i + 1) % n == 0) {
                System.out.println();
            }
        }
    }
}

