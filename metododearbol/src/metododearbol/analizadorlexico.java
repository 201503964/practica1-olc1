/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metododearbol;

import java.util.ArrayList;

/**
 *
 * @author luiss
 */
public class analizadorlexico {
    
    ArrayList<lexemas> listadetokens=new ArrayList<lexemas>();
    ArrayList<errores> listaerrores=new ArrayList<errores>();
     

        
    boolean analisislexico(String entrada)
    {
    char caracteres[]=entrada.toCharArray();
    int ascii=0;
    int estado=0;
    int indice=0;

    int x=0;
    int y=0;
    String token="";

    
    boolean ejecutar=true;
    while(ejecutar)
        
    {
    switch (estado)         
    {
        case 0:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            estado=1;
            }
            else 
            {
            return true;
            }
        break;
        
        case 1:// estado inicial inician todas las expresiones regulares
            if (ascii==123)//////////detecto inicio llave
            {   
                token=String.valueOf(caracteres[indice]);
                
                lexemas nuevo=new lexemas(x,y,token,"llave abre");
                listadetokens.add(nuevo);
                indice++;
                estado=0;
                
                
            }
            else if(ascii==125)
            {
                token=String.valueOf(caracteres[indice]);
                lexemas nuevo=new lexemas(x,y,token,"llave cierra");
                listadetokens.add(nuevo);
                indice++;
                estado=0;
                
            }
            else if( ascii==32)// espacion en blanco o saltos de linea
            {
                indice++;
                x++;
                estado=0;
            }
            else if(ascii==10)// espacion en blanco o saltos de linea
            {
                indice++;
                x=0;
                y++;
                estado=0;
            }
           else if(ascii==67 || ascii==99) ///inicio palabra reservada conj
            {
                token=token+String.valueOf(caracteres[indice]);
                x++;
                indice++;
                estado=2;
            }                       
            else /// si no viene ninguno de los anteriores es un error
            {
                token=String.valueOf(caracteres[indice]);
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            }
        break;
        case 2:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
            return true;
            }
            
        break;
    }
    }
    
    
        
        return true;
    }
    
    
    public void imprimir()
    {
    for(int x=0;x<listadetokens.size();x++)
    {
        System.out.print("posx:"+listadetokens.get(x).posx+"  ");
         System.out.print("posy:"+listadetokens.get(x).posy+"  ");
          System.out.print("lexema:"+listadetokens.get(x).lexem+"  ");
           System.out.println("token:"+listadetokens.get(x).toke);
    }
    }
}
