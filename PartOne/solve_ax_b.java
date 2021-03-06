package PartOne;

import base.Vector;
import base.Matrix;

/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * ideas from http://zurb.com/forrst/posts/Taking_a_matrix_and_reducing_it_to_reduced_row_e-5fk
 */
public class solve_ax_b {

    private Matrix reduced;
    private Vector answer;

    public solve_ax_b (Matrix augmented) {
        this.reduced = reduced(forwardElimination(augmented));

        double[] arr = new double[reduced.getRowDimension()];
        for (int row = 0; row < reduced.getRowDimension(); row++) {
            arr[row] = reduced.get(row, reduced.getColumnDimension() - 1);
        }
        this.answer = new Vector(arr);
    }


    private Matrix forwardElimination(Matrix A) {
        int currentCol = 0;

        //iterate through all the columns of the augmented matrix
        for(int k = 0; k < A.getColumnDimension() - 1 && currentCol < A.getColumnDimension() - 1; k++, currentCol++) {
            //while a pivot is 0, attempt a row swap. Don't attempt to do a row swap if there are no rows under the k we're looking at.
            while(currentCol < A.getColumnDimension() - 1 && k < A.getRowDimension() && A.get(k,currentCol) == 0) {
                //find a row with a non-zero element in the same column
                for(int l = k; l <= A.getRowDimension(); l++) {
                    //we looked through every row and couldn't find a non-zero pivot, so this column should be marked free. Move on to the next column.
                    if(l == A.getRowDimension()) {
                        currentCol++;
                        break;
                    }

                    //we've found a row with a non-zero element; now do a row swap
                    if(A.get(l,k) != 0) {
                        //swap the two rows, row l and row k
                        for(int a = 0; a < A.getColumnDimension(); a++) {
                            double temp = A.get(l, a);
                            A.set(l, a, A.get(k, a));
                            A.set(k, a, temp);
                        }

                        break;
                    }
                }
            }

            //go through all the rows beneath row k. Row k stays the same.
            for(int i = k+1; i < A.getRowDimension(); i++) {
                if(A.get(k, currentCol) != 0) {
                    double multiplier = (A.get(i, currentCol)/A.get(k, currentCol));

                    //for any values underneath and to the right of the pivot, do elimination
                    for(int j = currentCol; j < A.getColumnDimension(); j++) {
                        double value = A.get(i, j) - (multiplier*A.get(k, j));
                        A.set(i, j, value);
                    }
                }
            }

        }
        return A;
    }

    private Matrix reduced(Matrix U) {

        //iterate through all the columns of the augmented matrix, but now backwards
        for(int k = U.getColumnDimension() - 2; k >= 0; k--) {

            for(int i = U.getRowDimension() - 1; i >= 0; i--) {
                //pivot marks the location of the column where the pivot occurs
                int pivot = -1;
                double pivotValue = 0;
                boolean foundPivot = false;


                //for each row, iterate through the columns to find the first non-zero element. That must be the pivot.
                for(int p = 0; p < U.getColumnDimension(); p++) {
                    //if we've found the pivot, mark its location
                    if(!foundPivot && U.get(i, p) !=0) {
                        pivot = p;
                        pivotValue = U.get(i, pivot);
                        foundPivot = true;
                    }

                    //Divide the row by the pivot's value to make the pivot equal to 1
                    if(foundPivot) {
                        double quotient = (U.get(i, p) / pivotValue );
                        //System.out.println(quotient);
                        U.set(i, p, quotient);
                    }

                }

				/* if the entire column is full of 0s, we just continue iterating over every column and do nothing.
				 * We check if the value for d is 0. If it is, we will have a solution to the matrix, and we then move on to the next row.
				 * If not, we return null and let a higher-up method handle what to do next.
				 */
                if(pivot == -1) {
                    if(U.get(i, U.getColumnDimension() - 1) == 0)
                        continue;
                    else {
                        //System.out.println("There is no solution for the system.");
                        //return null;
                    }
                }


                for (int l = i - 1; l >= 0; l--) {
                    double multiplier = (U.get(l, pivot)/U.get(i, pivot));

                    for(int j = pivot; j < U.getColumnDimension(); j++) {
                        double value = U.get(l, j) - (multiplier*U.get(i, j));
                        U.set(l, j, value);
                    }
                }
            }
        }
        return U;
    }

    public Matrix getReducedEchelon() {
        return reduced;
    }

    public Vector getAnswer() {
        return answer;
    }
}
