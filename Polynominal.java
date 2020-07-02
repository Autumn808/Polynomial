package Project2;

/*
Autumn Capasso
UMGC
CMSC 350
PROJECT 2
 */

import java.util.Scanner;
import java.util.Comparator;
import java.util.Iterator;

class Polynomial implements Iterable<Polynomial.Poly>, Comparable<Polynomial> {

    //this interface compares polys
    Comparator<Polynomial> compare;
    public Poly head;

    //reads files using scanner class
    public Polynomial(String fromFile) {

        //constructor
        compare = Polynomial::comparePoly;

        //head is begining of the linkedlist
        head = null;


        //scanner reads file
        Scanner termScanner = new Scanner(fromFile);
        try {
            while (termScanner.hasNext()) {
                addTerm(termScanner.nextDouble(), termScanner.nextInt());
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new InvalidPolynomialSyntax("Input error");
        }
    }

    //this method adds term
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) {
            throw new InvalidPolynomialSyntax("Cannot process negative polynomials");
        }
        Poly current = head;
        if (current == null) {
            head = new Poly(coefficient, exponent);
            head.next = null;
            //will continue to look for  the end of the list with transverse
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Poly(coefficient, exponent);
        }

    }

    //this override method will compare the polynominals
    //this method compares exponents then the coefficients.
    @Override
    public int compareTo(Polynomial poly3) {
        Poly thisCurrent = this.head;
        Poly otherCurrent = poly3.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
                    return +1;
                }
            }
            // reset  values outside of  loop each time
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        //if both are null,they are equal.
        if (thisCurrent == null && otherCurrent == null) {
            return 0;
        }
        if (thisCurrent == null) {
            return -1;
        } else {
            return +1;
        }
    }

    //This method compares the two exponents
    public int comparePoly(Polynomial poly2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = poly2.head;
        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }
        if (thisPolyTerm == null && otherPolyTerm == null) {
            return 0;
        }
        if (otherPolyTerm == null) {
            return +1;
        } else {
            return -1;
        }
    }



    public Polynomial(Comparator<Polynomial> compare) { this.compare = compare;
    }

    @Override
    public Iterator<Poly> iterator() {
        return null;
    }

    public static class Poly {
        public double coefficient;
        public int exponent;
        public Poly next;
        private Poly head;

        public Poly(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;
        }

        public int getExponent() {
            return this.exponent;
        }

        public double getCoefficient() {
            return this.coefficient;
        }

        public Poly getNext() {
            return next;
        }

        @Override
        public String toString() {
            String termString = String.format("", Math.abs(coefficient));
            if (exponent == 0) {
                return termString;
            } else if (exponent == 1) {
                return termString + "x";
            } else {
                return termString + "x^" + exponent;
            }
        }

        public Poly getHead() {
            return head;
        }
    }

    //this override method iterates and implements to control the flow of the linked list
    @Override
    public Iterator<Poly> iterator() {
        return new Iterator<Poly>() {

            public Poly current = head.getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Poly next() {
                Poly data = current;
                current = current.next;
                return data;
            }
        };
    }

    //This method is a tostring method that adds to the head
    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();

        if (head.coefficient > 0) {
            expressionBuilder.append(head.toString());
        } else {
            expressionBuilder.append(" - ").append(head.toString());
        }
        // this checks the other nodes to see if they are not null
        for (Poly tmp = head.next; tmp != null; tmp = tmp.next) {
            if (tmp.coefficient < 0) {
                expressionBuilder.append(" - ").append(tmp.toString());
            } else {
                expressionBuilder.append(" + ").append(tmp.toString());
            }
        }
        return expressionBuilder.toString();

    }


}
