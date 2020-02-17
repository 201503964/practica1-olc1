/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metododearbol;

/**
 *
 * @author luiss
 */
public class analizadorlexico {
    
    
    boolean analisislexico(String entrada)
    {
    char caracteres[]=entrada.toCharArray();
    int ascii=0;
    int estado=0;
    int indice=0;
    
    boolean ejecutar=true;
    while(ejecutar)
        
    {
    switch (estado)         
    {
        case 0:
            ascii=caracteres[indice];
            estado=1;
        break;
        
        case 1:
            
        break;
    }
    }
    
    
        
        return true;
    }
}
