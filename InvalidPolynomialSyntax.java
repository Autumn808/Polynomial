/*
Autumn Capasso
UMGC
CMSC 350
PROJECT 2
 */

package Project2;

//This method  is a throws a exception if an invalid polynominal is entered
public class InvalidPolynomialSyntax extends RuntimeException {
    InvalidPolynomialSyntax(String message){super(message);
    }
}