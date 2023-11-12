package com.lazarev.springsolid.java;


public class OC { }

class OC_Wrong{
    static class Circle {
        void print(){}
    }

    static class Square{
        void print(){}
    }

    static class Printer{
        void printFigure(Object figure){
            if(figure instanceof Circle circle){
                circle.print();
            }
            else if (figure instanceof Square square){
                square.print();
            }
            throw new IllegalArgumentException("Wrong figure - " + figure);
        }
    }
}

class OC_Correct {
    static class Figure{
        void print(){}
    }

    static class Circle extends Figure {
        void print(){}
    }

    static class Square extends Figure {
        void print(){}
    }

    static class Printer{
        void printFigure(Figure figure){
            figure.print();
        }
    }
}
