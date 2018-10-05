package mx.overcode.krynapsislibrary;

import android.content.Context;

import mx.overcode.krynapsislibrary.util.CryptographyHelpers;
import mx.overcode.krynapsislibrary.util.MatrixHelpers;

public class Krynapsis {

    private static int[][] matrix;

    private static MatrixHelpers matrixHelpers = new MatrixHelpers();
    private static CryptographyHelpers cryptographyHelpers = new CryptographyHelpers();

    private static int size = 3;

    private static char valores[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '^','0','1','2','3','4','5','6','7','8','9','@','#',
            '$','_','&','-','+','(',')','/','*','"',':',';','!',
            '?',',','.',' ','}'};


    public static void init(String key){
        matrix = matrixHelpers.StringToMatrix(key);
    }

    public static void init(Context context){
        matrix = matrixHelpers.generarMatriz(size, valores.length, 50);
    }


    public static String encrypt(String phrase){
        return cryptographyHelpers.encriptarPalabra(phrase, matrix, valores, size);
    }

    public static String decrypt(String phrase){
        int[][] inversa = matrixHelpers.inversaModular(matrix, valores.length, size);
        return cryptographyHelpers.encriptarPalabra(phrase, inversa, valores, size);
    }
}
