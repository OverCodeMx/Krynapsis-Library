package mx.overcode.krynapsislibrary.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatrixHelpers {

    public String MatrixToString(Integer[][] matrix){
        String stringMatrix = "[";
        List<String> rows = new ArrayList<>();
        for (Integer[] r: matrix) {
            rows.add(TextUtils.join(",", r));
        }

        stringMatrix += TextUtils.join("][", rows);
        stringMatrix += "]";

        return stringMatrix;
    }

    public Integer[][] StringToMatrix(String string){
        Pattern p = Pattern.compile("\\[([^]]+)\\]");
        Matcher m = p.matcher(string);
        List<String> tokens = new LinkedList<>();
        while(m.find())
        {
            String token = m.group( 1 );
            tokens.add(token);
        }

        Integer[][] key = new Integer [tokens.size()][tokens.size()];

        for(int i = 0; i < tokens.size(); i++){
            String[] parts = tokens.get(i).split(",");
            for(int j = 0; j < parts.length; j++){
                key[i][j] = Integer.valueOf(parts[j]);
            }
        }

        return key;
    }

    public Integer[][] generarMatriz(int size, int modulo, int maxNum){

        Integer k3[][] = new Integer [size][size];
        Integer k[][] = new Integer [size][size];
        int k1[][] = new int [size][size];

        //Limpiando matriz
        for(int a=0; a<size; a++){
            for(int b=0; b<size; b++){
                k[a][b]=0;
                k1[a][b]=0;
                k3[a][b]=0;
            }
        }

        //Creando la matriz identidad con ceros y unos
        for(int a=0; a<size; a++){
            for(int b=0; b<size; b++){
                if(a==b){
                    k1[a][b] = 1;
                }else{
                    k1[a][b]=0;
                }
            }
        }

        //Se llenan por primera vez la matriz con numeros aleatorios de 0-30
        for(int a=0; a<size; a++){
            for(int b=0; b<size; b++){
                k[a][b] = (int)(Math.random()*maxNum);
                k3[a][b] = k[a][b];
            }
        }


        //Se ejecuta hasta que encuentre una matriz valida
        while(!compruebaInversa(k, size,modulo)){
            for(int a=0; a<size; a++){
                for(int b=0; b<size; b++){
                    k[a][b] = (int)(Math.random()*100);
                    k3[a][b] = k[a][b];
                }
            }
        }

        //Se ejecuta hasta que encuentre la inversa modular correcta
        while(((inversaModular(k, size,modulo)) == null)){

            while(!compruebaInversa(k, size,modulo)){

                for(int a=0; a<size; a++){
                    for(int b=0; b<size; b++){
                        k[a][b] = (int)(Math.random()*100);
                        k3[a][b] = k[a][b];
                    }
                }
            }

            for(int a=0; a<size; a++){
                for(int b=0; b<size; b++){
                    if(a==b){
                        k1[a][b] = 1;
                    }else{
                        k1[a][b]=0;
                    }
                }
            }
        }
        return k3;
    }

    private int determinante(Integer matriz[][], int size){
        int i,j,k,l,m,n ;
        n=size;

        float a[][] = new float[size+1][size+1];

        for(int c=0; c<size; c++){
            for(int d=0; d<size; d++){
                a[c+1][d+1] = matriz[c][d];
            }
        }

        m=n-1;
        float det =0;
        det=a[1][1];
        for(k=1;k<=m;k++)
        { l=k+1;
            for(i=l;i<=n;i++)
            { for(j=l;j<=n;j++)
                a[i][j] = ( a[k][k]*a[i][j]-a[k][j]*a[i][k] )/a[k][k]; }
            det=det*a[k+1][k+1];
        }

        if(det<0){
            det = det * (-1);
        }

        return (int)det;
    }//Fin determinante

    private Boolean compruebaInversa(Integer k[][], int size, int modulo){

        int deter = determinante(k, size);
        int divisoresDeter =  0;
        int divisoresModulo = 0;
        boolean inversa = false;

        if(deter != 0){

            for(int a=1; a<modulo; a++){
                if((modulo % a) == 0){

                    divisoresModulo++;
                    if((deter % a) == 0){
                        divisoresDeter++;
                        //cout<<a<<endl;
                    }
                }
            }

            if(divisoresDeter > 1){
                inversa =false;
            }else{
                inversa = true;
            }

        }else{
            inversa = false;
        }
        //cout<<endl<<deter<<endl;
        return inversa;
    }//Fin compruebaInversa

    public Integer[][] inversaModular(Integer k[][], int size, int modulo){

        Integer[][] k1 = new Integer[size][size];

        for(int a=0; a<size; a++){
            for(int b=0; b<size; b++){
                if(a==b){
                    k1[a][b] = 1;
                }else{
                    k1[a][b]=0;
                }
            }
        }


        /************REDUCIENDO A CERO DE ARRIBA HACIA ABAJO************/
        for(int a=0; a<size; a++){

            //cout<<endl<<endl;
            //imprimirMatriz(k,size);

            reducirUno(k, k1, a, size, modulo);

            for(int b=a; b<(size-1); b++){
                sumarRenglones(k,k1, b, size, a, false);
                comprobarNumerosNegativos(k,k1, b, size, modulo,false);
            }
        }

        /*  IMPRIMIENTO ARREGLOS K Y K1  */
        //imprimirMatriz(k,size);
        //imprimirMatriz(k1,size);

        /* REDUCIENDO A CERO DE ABAJO HACIA ARRIBA */
        for(int a=(size-1); a>0; a--){
            //cout<<"Reduciendo a cero numeros: "<<endl;
            for(int b=a-1; b>=0; b--){
                sumarRenglones(k,k1, b, size, a, true);
                comprobarNumerosNegativos(k,k1, b, size, modulo, true);
            }
        }

        /*  IMPRIMIENTO ARREGLOS K Y K1 */
	/*
	imprimirMatriz(k,size);
	imprimirMatriz(k1,size);
	*/

        for(int a=0; a<size; a++){
            for(int b=0; b<size; b++){

                if(a != b){
                    if(k[a][b] != 0){
                        return null;
                    }
                }else{
                    if(k[a][b] != 1){
                        return null;
                    }
                }
            }
        }
        return k1;
    }

    private void sumarRenglones(Integer k[][], Integer k1[][], int indicador, int size,int  indicadorRenglon, boolean orientacion){

        /*
         *	x = numero por el que se multiplica para poder hacer 0 el numero
         *	indicadorRenglon = es el numero en el renglon que se encuentra
         *	indicador = es el numero el cual recorre la columna en base al indicadorRenglon
         */
        if(!orientacion){
            //ARRIBA HACIA ABAJO
            int x = k[indicador+1][indicadorRenglon];
            //cout<<endl<<endl<<x<<endl<<endl;

            for(int b=0; b<size; b++){
                k[indicador+1][b]=k[indicador+1][b]  - (x * k[indicadorRenglon][b]);
                k1[indicador+1][b]=k1[indicador+1][b]  - (x * k1[indicadorRenglon][b]);
            }

            //cout<<"Reng: "<<indicador+1<<" "<<k[indicador+1][0]<<"	"<<k[indicador+1][1]<<"	"<<k[indicador+1][2]<<"	"<<k[indicador+1][3]<<"	"<<k[indicador+1][4]<<"	"<<endl;

        }else{
            //ABAJO HACIA ARRIBA

            // K[2][2]
            // K[1][2]
            // K[0][2]

            int x = k[indicador][indicadorRenglon];

            //cout<<endl<<endl<<"D-U	"<<x<<endl<<endl;


            for(int b=0; b<size; b++){
                k[indicador][b]=k[indicador][b]  - (x * k[indicadorRenglon][b]);

                k1[indicador][b]=k1[indicador][b]  - (x * k1[indicadorRenglon][b]);
            }


        }

    }

    private void comprobarNumerosNegativos(Integer k[][], Integer k1[][], int indicador, int size, int modulo,boolean orientacion){

        /*
         *Indicador es el renglon en el cual se encuentra segun el for
         *Orientacion solo indica si es de arriba-abajo(false) o de abajo-arriba(true)
         */

        if(!orientacion){
            for(int b=0; b<size; b++){
                if(k[indicador+1][b]<0){
                    k[indicador+1][b]= moduloEuclidiano(modulo,k[indicador+1][b]);
                }
                if(k1[indicador+1][b]<0){
                    k1[indicador+1][b]= moduloEuclidiano(modulo,k1[indicador+1][b]);
                }

            }
            //cout<<"NumNega	"<<k[indicador+1][0]<<"	"<<k[indicador+1][1]<<"	"<<k[indicador+1][2]<<"	"<<k[indicador+1][3]<<"	"<<k[indicador+1][4]<<"	";
        }else{
            for(int b=0; b<size; b++){

                if(k[indicador][b]<0){
                    k[indicador][b]= moduloEuclidiano(modulo,k[indicador][b]);
                }

                if(k1[indicador][b]<0){
                    k1[indicador][b]= moduloEuclidiano(modulo,k1[indicador][b]);
                }

            }
        }

    }

    public int moduloEuclidiano(double modulo, double numero){
        double x=0;
        double total;
        double total1;


        if(numero<0){
            //Caso que numero sea negativo

            if ( numero > (-modulo)){
                x = numero + modulo;
            }else{
                total = (numero/modulo);
                total1 = total + (- (int)total);
                x = (total1*modulo)+modulo;

            }
        }else if(numero==0){
            x=0;
        }else{
            //Caso que numero sea positivo
            if ( numero < modulo){
                x = numero;
            }else{
                total = (numero/modulo);

                total1 = total - (int)total;
                x = total1*modulo;
            }
        }
        return Math.round((float)x);
    }

    private int encuentraX(double modulo, double numero){
        int x=0;
        //cout<<"Modulo: "<<modulo<<"	Numero: "<<numero<<endl;



        if(numero!=1){
            for(int a=1; a<1000; a++){
                double total=0;

                total = ((modulo*a)+1) / numero;
                //cout<<"Numero: "<<a<<" - "<<total<<"	";

                if(total-(int)total == 0){

                    x = (int)total;
                    //cout<<a<<endl;
                    break;

                }
            }

        }else{
            x= (int)numero;
        }

        return x;
    }

    private void reducirUno(Integer k[][], Integer k1[][], int indicador, int size, int modulo){
        //indicador es el numero que contine el numero de renglon


        int x = encuentraX(modulo, k[indicador][indicador]);

        //cout<<endl<<"X = "<<x<<endl;

        for (int b=0; b<size; b++){
            k[indicador][b] = moduloEuclidiano(modulo,(k[indicador][b] * x));

            k1[indicador][b] = moduloEuclidiano(modulo,(k1[indicador][b] * x));
        }
    }
}
