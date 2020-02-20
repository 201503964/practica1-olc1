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
            token="";
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
            else if(ascii==125)//////////llave cierra
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
           else  if(ascii==58) //////////dos puntos
            {  
               token=String.valueOf(caracteres[indice]);
               lexemas nuevo1=new lexemas(x,y,token,"dos puntos");
               listadetokens.add(nuevo1);
               x++;
               indice++;
               estado=0;
            }
             else  if(ascii==59)/////////////////punto y coma
            {  
               token=String.valueOf(caracteres[indice]);
               lexemas nuevo1=new lexemas(x,y,token,"punto y coma");
               listadetokens.add(nuevo1);
               x++;
               indice++;
               estado=0;
            }
           
           else if(ascii==45)///////////////flecha
           {
               token=token+String.valueOf(caracteres[indice]);
                x++;
                indice++;
                estado=7;
           }
           else if(ascii==34)/////////////////////cadena
           {               
                x++;
                indice++;
                estado=9;
           }
            else if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122))///////////////identificador y rango
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=8;
            }
            else if((ascii>=48&& ascii<=57))///////////////identificador y rango
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=12;
            }
            else if(ascii==47)///////////////comentario de una linea
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=13;
            }
             else if(ascii==60)///////////////comentario multilinea
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=15;
            }
             else if(ascii==37)///////////////porcentajes
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=18;
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
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            
            if(ascii==111 || ascii==79)
            {
            token=token+String.valueOf(caracteres[indice]);
                x++;
                indice++;
                estado=3;
            }
        break;
        case 3:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
            lexemas nuevo=new lexemas(x,y,token,"identificador");
            listadetokens.add(nuevo);
            return true;
            }
            if(ascii==110 || ascii==78)
            {
            token=token+String.valueOf(caracteres[indice]);
                x++;
                indice++;
                estado=4;
            }
        break;
        case 4:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                lexemas nuevo=new lexemas(x,y,token,"identificador");
                listadetokens.add(nuevo);
            return true;
            }
            
            if(ascii==106 || ascii==74)
            {
               token=token+String.valueOf(caracteres[indice]);
                x++;
                indice++;
                estado=5;
            }
        break;
        case 5:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                lexemas nuevo=new lexemas(x,y,token,"palabra reservada");
                listadetokens.add(nuevo);
            return true;
            }
            
            if(ascii==58)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"palabra reservada");
               listadetokens.add(nuevo);
               
               token=String.valueOf(caracteres[indice]);
               lexemas nuevo1=new lexemas(x,y,token,"dos puntos");
               listadetokens.add(nuevo1);
               x++;
               indice++;
               estado=0;
            }
            else if(ascii==32)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"palabra reservada");
               listadetokens.add(nuevo);
               x++;
               indice++;
               estado=0;
            }
            else if(ascii==45)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"palabra reservada");
               listadetokens.add(nuevo);
               estado=0;
            }
            else if(ascii==10)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"palabra reservada");
               listadetokens.add(nuevo);
               x=0;
               y++;
               indice++;
               estado=0;
            }
            else if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=6;
            }
        break;
        case 6:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                lexemas nuevo=new lexemas(x,y,token,"identificador");
                listadetokens.add(nuevo);
            return true;
            }
            
            if(ascii==58)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"identificador");
               listadetokens.add(nuevo);
               
               token=String.valueOf(caracteres[indice]);
               lexemas nuevo1=new lexemas(x,y,token,"dos puntos");
               listadetokens.add(nuevo1);
               x++;
               indice++;
               estado=0;
            }
            else if(ascii==32)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"identificador");
               listadetokens.add(nuevo);
               x++;
               indice++;
               estado=0;
            }
            else if(ascii==45)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"identificador");
               listadetokens.add(nuevo);
               estado=0;
            }
            else if(ascii==10)
            {
               lexemas nuevo=new lexemas((x-2),y,token,"identificador");
               listadetokens.add(nuevo);
               x=0;
               y++;
               indice++;
               estado=0;
            }
            else if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=6;
            }
        break;
        
        case 8:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
             if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=6;
            }
            else if(ascii==126)
            {
            token=token+String.valueOf(caracteres[indice]);
            x++;
            indice++;
            estado=11;
            }
            else if(ascii==44)
            {
            token=token+String.valueOf(caracteres[indice]);
            x++;
            indice++;
            estado=19;
            }

             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
                estado=0;
             }
        break;
        case 7:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
            errores nuevo=new errores(x,y,token,"error lexico");
            listaerrores.add(nuevo);
            return true;
            }
            if(ascii==62)
            {
            token=token+String.valueOf(caracteres[indice]);
            lexemas nuevo=new lexemas(x,y,token,"puntero");
            listadetokens.add(nuevo);
                x++;
                indice++;
                estado=0;
            }
            else
            {
            errores nuevo=new errores(x,y,token,"error lexico");
            listaerrores.add(nuevo);
            estado=0;
            }
        break;
        case 9:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            
             if(ascii==34||ascii==10)
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
               estado=0;
            }
             else
             {
             token=token+String.valueOf(caracteres[indice]);
             estado=10;
             x++;
             indice++;
             }
        break;
        case 10:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            
             if(ascii==10)
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
               estado=0;
               y++;
               x=0;
            }
             else if(ascii==34)
             {
             lexemas nuevo=new lexemas(x,y,token,"cadena");
             listadetokens.add(nuevo);
             estado=0;
             x++;
             indice++;
             }
             else 
             {
             token=token+String.valueOf(caracteres[indice]);
             estado=10;
             x++;
             indice++;
             }
        break;
        
        case 11:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               lexemas nuevo=new lexemas(x,y,token,"rango");
                listadetokens.add(nuevo);
               x++;
               indice++;
               estado=0;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             x++;
             }
        break;
        
         case 12:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==126)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=11;
            }
            else if(ascii==44)
            {
            token=token+String.valueOf(caracteres[indice]);
            x++;
            indice++;
            estado=19;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 13:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==47)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=14;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 14:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                lexemas nuevo=new lexemas(x,y,token,"comentario de una linea");
                listadetokens.add(nuevo);
            return true;
            }
            if(ascii==10)
            {
                 lexemas nuevo=new lexemas(x,y,token,"comentario de una linea");
                listadetokens.add(nuevo);
                x=0;
                y++;
                indice++;
                estado=0;
            }
             else
             {
             token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=14;
             }
        break;
        
         case 15:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==33)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=16;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 16:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==33)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=17;
            }
             else
             {
             token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=16;
             }
        break;
        case 17:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==62)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=0;
               lexemas nuevo=new lexemas(x,y,token,"comentario multi linea");
                listadetokens.add(nuevo);
            }
             else
             {
             token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=16;
             }
        break;
          case 18:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if(ascii==37)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=0;
               lexemas nuevo=new lexemas(x,y,token,"doble porcentaje");
                listadetokens.add(nuevo);
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
               estado=0;
             }
        break;
        case 19:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                errores nuevo=new errores(x,y,token,"error lexico");
                listaerrores.add(nuevo);
            return true;
            }
            if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=20;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 20:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            }
            else 
            {
                lexemas nuevo=new lexemas(x,y,token,"lista caracteres");
                listadetokens.add(nuevo);
            return true;
            }
            if(ascii==44)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=19;
            }
             else
             {
             lexemas nuevo=new lexemas(x,y,token,"lista caracteres");
             listadetokens.add(nuevo);
             estado=0;
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
