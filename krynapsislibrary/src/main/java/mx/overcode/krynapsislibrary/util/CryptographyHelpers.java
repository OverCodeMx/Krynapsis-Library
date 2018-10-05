package mx.overcode.krynapsislibrary.util;

public class CryptographyHelpers {

    private MatrixHelpers matrixHelpers = new MatrixHelpers();

    private void asignarNumerosLetras(char valores[], int modulo, String texto, int textoNumeros[]){

        char textoSinAce[]= texto.toCharArray();
        //Quita los acentos
        for(int a=0; a<texto.length(); a++){
            if(textoSinAce[a] =='á'){
                textoSinAce[a]='a';
            }else if(textoSinAce[a]=='é'){
                textoSinAce[a]='e';
            }else if(textoSinAce[a]=='í'){
                textoSinAce[a]='i';
            }else if(textoSinAce[a]=='ó'){
                textoSinAce[a]='o';
            }else if(textoSinAce[a]=='ú'){
                textoSinAce[a]='u';
            }
        }
        texto = String.valueOf(textoSinAce);

        for(int a=0; a<texto.length(); a++){
            for(int b=0; b<modulo; b++){
                if( texto.charAt(a) == valores[b]){
                    textoNumeros[a]=b;
                    break;
                }
            }
        }
    }

    public String encriptarPalabra(String palabraEncriptar, int[][] key, char[] valores, int size){
        String palabraEncriptada="";
        int noComodines=0;
        int sizeTotal=0;

        if(Math.IEEEremainder(palabraEncriptar.length(),size)  != 0){
            noComodines = size - (palabraEncriptar.length()-((palabraEncriptar.length() / size)*size));
        }

        sizeTotal = (palabraEncriptar.length()+ noComodines);
        int passNum[] = new int[sizeTotal];
        for(int a=palabraEncriptar.length(); a<sizeTotal; a++){
            palabraEncriptar += '^';
        }

        asignarNumerosLetras(valores, valores.length ,palabraEncriptar,passNum);
        palabraEncriptada = encriptarTexto(key, size, valores.length ,passNum,sizeTotal,valores);

        return palabraEncriptada;
    }

    private String encriptarTexto(int k3[][],int size, int modulo, int textoNumeros[], int sizeTexto,char valores[]){
        int textoEncriptado [] = new int [sizeTexto];
        int iterador =0;
        for(int a=0; a<sizeTexto;){
            for(int b=0; b<size; b++){
                //For que multiplica columnas de la matriz por los numeros del texto
                for(int c=0, d=iterador; c<size; c++,d++){

                    int total = k3[b][c] * textoNumeros[d];
                    textoEncriptado[a]=textoEncriptado[a]+total;
                }
                a++;
            }
            if(((a)%size) == 0){
                //cout<<endl<<"hola"<<endl;
                iterador =iterador+size;
            }
        }
        for(int a=0; a<sizeTexto; a++){
            textoEncriptado[a] = matrixHelpers.moduloEuclidiano(modulo,textoEncriptado[a]);
        }
        char textoEncriptadoCompleto [] = new char [sizeTexto];

        for(int a=0; a<sizeTexto; a++){
            for(int b=0; b<modulo; b++){
                if(textoEncriptado[a] == b){
                    textoEncriptadoCompleto[a]=valores[b];
                    break;
                }
            }
        }
        return String.valueOf(textoEncriptadoCompleto);
    }

}
