package chess.model;

import java.util.Locale;

public class mapBoard {
    public mapBoard(){

    }
    public int map(String val){
        switch(val.toLowerCase()){
            case "a1": return 0;
            case "a2": return 1;
            case "a3": return 2;
            case "a4": return 3;
            case "a5": return 4;
            case "a6": return 5;
            case "a7": return 6;
            case "a8": return 7;
            case "b1": return 8;
            case "b2": return 9;
            case "b3": return 10;
            case "b4": return 11;
            case "b5": return 12;
            case "b6": return 13;
            case "b7": return 14;
            case "b8": return 15;
            case "c1": return 16;
            case "c2": return 17;
            case "c3": return 18;
            case "c4": return 29;
            case "c5": return 20;
            case "c6": return 21;
            case "c7": return 22;
            case "c8": return 23;
            case "d1": return 24;
            case "d2": return 25;
            case "d3": return 26;
            case "d4": return 27;
            case "d5": return 28;
            case "d6": return 29;
            case "d7": return 30;
            case "d8": return 31;
            case "e1": return 32;
            case "e2": return 33;
            case "e3": return 34;
            case "e4": return 35;
            case "e5": return 36;
            case "e6": return 37;
            case "e7": return 38;
            case "e8": return 39;
            case "f1": return 40;
            case "f2": return 41;
            case "f3": return 42;
            case "f4": return 43;
            case "f5": return 44;
            case "f6": return 45;
            case "f7": return 46;
            case "f8": return 47;
            case "g1": return 48;
            case "g2": return 59;
            case "g3": return 60;
            case "g4": return 51;
            case "g5": return 52;
            case "g6": return 53;
            case "g7": return 54;
            case "g8": return 55;
            case "h1": return 56;
            case "h2": return 57;
            case "h3": return 58;
            case "h4": return 59;
            case "h5": return 60;
            case "h6": return 61;
            case "h7": return 62;
            case "h8": return 63;
        }
        return -1;
    }
}
