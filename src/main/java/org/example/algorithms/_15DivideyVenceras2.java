package org.example.algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2 {

    private static final int IZQ = 0;
    private static final int DER = 1;

    //método para iniciar el algoritmo
    public static int[] divideyVenceras2(int vec1[], int vec2[])
    {

        if ( vec1.length <= 100 || vec2.length <= 100 )
        {
            return multi( vec1, vec2 );
        }
        int tam = tamanio (vec1, vec2);
        vec1 = completar (vec1, tam, IZQ);
        vec2 = completar (vec2, tam, IZQ);
        int w [] = splitArray ( vec1 , IZQ);
        int x [] = splitArray ( vec1 , DER);
        int y [] = splitArray ( vec2 , IZQ);
        int z [] = splitArray ( vec2 , DER);
        int m1 [] = divideyVenceras2(w, y);
        int m3 [] = divideyVenceras2(x, z);
        int r [] = divideyVenceras2(sumaArray( w, x ),sumaArray( y, z ));
        int m2 [] = restaArray( r, sumaArray( m1, m3 ));
        return sumaArray(sumaArray(completar(m1,m1.length+tam,DER),
                completar(m2,m2.length + tam/2, DER )) , m3);
    }

    //método para completar el array
    public static int[] completar(int vec[], int tam, int id)
    {
        int res[];
        if (tam > vec.length)
        {
            int cant = tam - vec.length;
            res = new int[tam];
            if (id == IZQ)
            {
                for (int i = 0; i < res.length; i++)
                {
                    if (i<cant) res[i] = 0;
                    else res[i] = vec [i - cant];
                }
                return res;
            }
            else
            {
                for (int i = 0; i < res.length; i++)
                {
                    if (i<vec.length) res[i] = vec [i];
                    else res[i] = 0;
                }
                return res;
            }
        }
        return vec;
    }

    //método para restar el array de ceros
    public static int[] restaArray(int vec[], int vec2[])
    {
        int res[]=new int [vec.length];
        int k,j=vec2.length-1;
        int bvec = base ( vec ), bvec2 = base ( vec2 );
        for(int i=vec.length-1; i>=bvec; i--)
        {
            if(j>=bvec2)
            {
                if( vec[i] >= vec2[j])
                    res[i]= vec[i] - vec2[j];
                else
                {
                    k=i;
                    vec[k]+=10;
                    while( vec[k-1]==0 && k>bvec)
                    {
                        vec[k-1]=9;
                        k--;
                    }
                    vec[k-1]--;
                    res[i]= vec[i] - vec2[j];
                }
                j--;
            }
            else
                res[i]=vec[i];
        }
        return res;
    }

    //mpetodo para obtener la base2
    public static int base(int v[]) {
        int pos=0;
        while (v [pos]==0 && pos < v.length -1)
        {
            pos++;
        }
        if(pos>2)
            pos--;
        return pos ;
    }


    //método para partir el array
    public static int[] splitArray(int vec[], int id) {
        int tam = vec.length;
        int res[]=new int[tam/2];
        if (id == IZQ)
        {
            for (int i = 0; i < tam/2; i++)
                res[i] = vec [i];
        }else
        {
            for (int i = tam/2; i <tam; i++)
                res[i-tam/2] = vec [i];
        }
        return res;
    }

    //método para sumar los arrays resultantes
    public static int[] sumaArray(int v1[], int v2[]) {
        int res[];
        int mayor[]=v1 ;
        int menor[]= v2 ;
        if (v2.length>v1.length)
        {
            mayor = v2;
            menor = v1;
        }
        res = new int [mayor.length+1];
        int j = menor.length - 1 ;
        for(int i = res.length-1; i>0; i--)
        {
            if( j >= 0)
            {
                int temp= mayor[i-1]+menor[j];
                res[i]+=temp;
                res[i-1] =res[i]/10;
                res[i]=res[i]%10;
                j--;
            }
            else
            {
                res[i]+=mayor[i-1];
                res[i-1] =res[i]/10;
                res[i]=res[i]%10;
            }
        }
        return res;
    }

    //método para definir el tamaño
    private static int tamanio(int[] vec1, int[] vec2) {
        int max = Math.max(vec1.length, vec2.length);
        int tam = 1;
        while (tam < max) {
            tam <<= 1;
        }
        return tam;
    }

    //método para solucionar el caso base
    private static int[] multi(int arreglo1[], int arreglo2[] ){
        int resultado []= new int [arreglo1.length + arreglo2.length];
        for ( int i = arreglo2.length-1 ; i >= 0; i-- ){
            for ( int j = arreglo1.length-1; j >= 0 ; j-- ){
                resultado [ i + j + 1 ] += arreglo1 [ j ] * arreglo2 [ i ];
                resultado [ i + j ] += (resultado [ i + j + 1 ] / 10);
                resultado [ i + j + 1 ] %= 10;
            }
        }
        return resultado;
    }

}
