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
     
    ArrayList<nodolistaexpresiones> listaexpre=new ArrayList<nodolistaexpresiones>(); 
        
    boolean analisislexico(String entrada)
    {
        listadetokens.clear();
        listaerrores.clear();
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
             else if(ascii==46||ascii==42||ascii==43||ascii==124||ascii==63)///////////////expresion regular
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=22;
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
        case 22:///////////inicio de expresion regular
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
            if(ascii==34)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=23;
            }
            else if(ascii==123)
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=25;
            }
            else if(ascii==46||ascii==42||ascii==43||ascii==124||ascii==63)///////////////expresion regular
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=22;
            }
            else if(ascii==10)
            {
                lexemas nuevo=new lexemas(x,y,token,"expresion regular");
                listadetokens.add(nuevo);
                x=0;
                y++;
                indice++;
                estado=0;
            }
            else if(ascii==32)
            {
                x++;
                indice++;
                estado=22;
            }
            else if(ascii==59)
            {
                lexemas nuevo=new lexemas(x,y,token,"expresion regular");
                listadetokens.add(nuevo);
                x++;
                estado=0;
            }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 23:
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
             estado=24;
             x++;
             indice++;
             }
        break;
        case 24:
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
             token=token+String.valueOf(caracteres[indice]);
             estado=22;
             x++;
             indice++;
             }
             else 
             {
             token=token+String.valueOf(caracteres[indice]);
             estado=24;
             x++;
             indice++;
             }
        break;
        case 25:
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
             if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122))
            {
               token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
               estado=26;
            }
            
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 26:
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
               estado=27;
            }
            
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
             estado=0;
             }
        break;
        case 27:
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
               estado=27;
            }
             else if(ascii==125)
             {
                 token=token+String.valueOf(caracteres[indice]);
               x++;
               indice++;
             estado=22;
             }
             else
             {
             errores nuevo=new errores(x,y,token,"error lexico");
             listaerrores.add(nuevo);
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

        public void recorrertokens()
    {
    for(int x=0;x<listadetokens.size();x++)
    {
        
        if(listadetokens.get(x).toke.equals("expresion regular"))
        {
            String ide=listadetokens.get(x-2).lexem;
            String exp=listadetokens.get(x).lexem;
        insertarexpresion(exp,ide);
        }
    }
    }

    
public void insertarexpresion(String expre, String identifi)
{
    ArrayList<String> pila=new ArrayList<String>();
    char caracteres[]=expre.toCharArray();
    int ascii=0;
    int estado=0;
    int indice=0;
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
            ejecutar=false;
            }
        break;
        
        case 1:// estado inicial inician todas las expresiones regulares
                if (ascii==123)//////////detecto inicio llave
                {  
                    indice++;
                    estado=25;


                }
           else if(ascii==34)/////////////////////cadena
           {  
               token=token+"\\"+String.valueOf(caracteres[indice]);
                indice++;
                estado=23;
           }
                else if(ascii==46||ascii==42||ascii==43||ascii==124||ascii==63)///////////////expresion regular
            {
               token=String.valueOf(caracteres[indice]);
               pila.add(token);
               indice++;
               estado=0;
            }

        break;
        case 23:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
             token=token+String.valueOf(caracteres[indice]);
             estado=24;
             indice++;
            }
            else
            {
            ejecutar=false;
            }
            
        break;
        case 24:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            if(ascii==34)
             {
            token=token+"\\"+String.valueOf(caracteres[indice]);
             pila.add(token);
             estado=0;
             indice++;
             }
             else 
             {
             token=token+String.valueOf(caracteres[indice]);
             estado=24;
             indice++;
             }
            } 
            else
            {
            ejecutar=false;
            }
        break;
        case 25:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            
               token=token+String.valueOf(caracteres[indice]);
               indice++;
               estado=26;
            }
                        else
            {
            ejecutar=false;
            }
            
        break;
        case 26:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
               token=token+String.valueOf(caracteres[indice]);
               
               indice++;
               estado=27;
            }
            else
            {
            ejecutar=false;
            }
            
        break;
        case 27:
            if(indice<=caracteres.length-1)
            {
            ascii=caracteres[indice];
            
             if((ascii>=65&& ascii<=90)||(ascii>=97 && ascii<=122)||(ascii>=48 && ascii<=57))
            {
               token=token+String.valueOf(caracteres[indice]);
               indice++;
               estado=27;
            }
             else if(ascii==125)
             {
             pila.add(token);
             indice++;
             estado=0;
             }
            }
                        else
            {
            ejecutar=false;
            } 
        break;
        
    }
    }
    arboldeexpresiones hijoder=new arboldeexpresiones("#",null,null);
    arboldeexpresiones rais=new arboldeexpresiones(".",hijoder,null);
    rais.nografica=10000;
    hijoder.nografica=10001;
    hijoder.padre=rais;
    rais.padre=null;
    
    arboldeexpresiones subrais=new arboldeexpresiones(pila.get(0),null,null);
    arboldeexpresiones auxiliar=subrais;
    int index=1;
    
    while(index<pila.size())
    {
       
        //System.out.println(pila.get(index));
        if((auxiliar.datos.equals("?")||auxiliar.datos.equals("*")||auxiliar.datos.equals("+"))&& auxiliar.hijoizquierdo!=null)
        {
        auxiliar=auxiliar.padre;
        }
        else{
        if(pila.get(index).equals("."))
        {
            if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones(".",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            auxiliar=nuevo;
            index++;
            }
            else if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones(".",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=nuevo;
            index++;
            }
            else
            {
            auxiliar=auxiliar.padre;
            }
        }
        else if(pila.get(index).equals("|"))
        {
            if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("|",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("|",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=nuevo;
            index++;
            }
            else
            {
            auxiliar=auxiliar.padre;
            }
        }
        else if(pila.get(index).equals("*"))
        {

            if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("*",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("*",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else
            {
            auxiliar=auxiliar.padre;
            }
        }
        else if(pila.get(index).equals("?"))
        {
            if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("?",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("?",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=nuevo;
            index++;
            }
            else
            {
            auxiliar=auxiliar.padre;
            }
        }
        else if(pila.get(index).equals("+"))
        {
           if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("+",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else if(auxiliar.hijoderecho==null&& auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones("+",null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=nuevo;
            index++;
            }
           else
            {
            auxiliar=auxiliar.padre;
            }
        }
        else
        {
            if(auxiliar.hijoderecho==null && auxiliar.hijoizquierdo==null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones(pila.get(index),null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoizquierdo=nuevo;
            index++;
            }
            else if(auxiliar.hijoderecho==null && auxiliar.hijoizquierdo!=null)
            {
            arboldeexpresiones nuevo=new arboldeexpresiones(pila.get(index),null,null);
            nuevo.padre=auxiliar;
            nuevo.nografica=index;
            auxiliar.hijoderecho=nuevo;
            auxiliar=auxiliar.padre;
            index++;
            }
            else
            {
            auxiliar=auxiliar.padre;
            }
        }
        }
    }
    
    rais.hijoizquierdo=subrais;
    subrais.padre=rais;
    
    nodolistaexpresiones nuevaexpre=new nodolistaexpresiones(identifi,rais);
    listaexpre.add(nuevaexpre);
}


int idenramas=0;
public void modificararbol()
    {
        
        for(int x=0;x<listaexpre.size();x++)
    {
        idenramas=1;
         agregaridentificador(listaexpre.get(x).raiz);
         anulable(listaexpre.get(x).raiz);
         primeros(listaexpre.get(x).raiz);
         siguientes(listaexpre.get(x).raiz);
   }
    }

     public void agregaridentificador(arboldeexpresiones raiz)
    {
        if(raiz.hijoderecho==null && raiz.hijoizquierdo==null)
        {
        raiz.identificador=idenramas;
        idenramas++;
        }
        if(raiz.hijoizquierdo!=null)
        {
            agregaridentificador(raiz.hijoizquierdo);
        }
        if(raiz.hijoderecho!=null)
        {
            agregaridentificador(raiz.hijoderecho);
        }
    }
     
      public void anulable(arboldeexpresiones raiz)
    {
       
        if(raiz.hijoizquierdo!=null)
        {
            anulable(raiz.hijoizquierdo);
        }
        if(raiz.hijoderecho!=null)
        {
            anulable(raiz.hijoderecho);
        }
        
         if(raiz.datos.equals("*"))
        {
        raiz.anulable='a';
        }
         else if(raiz.datos.equals("|"))
        {
        if(raiz.hijoderecho.anulable=='a'||raiz.hijoizquierdo.anulable=='a')
        {
        raiz.anulable='a';
        }
        else
        {
        raiz.anulable='n';
        }
        }
         else if(raiz.datos.equals("."))
        {
        if(raiz.hijoderecho.anulable=='a'&& raiz.hijoizquierdo.anulable=='a')
        {
        raiz.anulable='a';
        }
        else
        {
        raiz.anulable='n';
        }
        }
         else if(raiz.datos.equals("?"))
        {
        raiz.anulable='a';
        }
         else if(raiz.datos.equals("+"))
        {
        raiz.anulable='n';
        }
         else
        {
        raiz.anulable='n';
        }
    }
      
      public void primeros(arboldeexpresiones raiz)
    {
       
        if(raiz.hijoizquierdo!=null)
        {
            primeros(raiz.hijoizquierdo);
        }
        if(raiz.hijoderecho!=null)
        {
            primeros(raiz.hijoderecho);
        }
        
         if(raiz.datos.equals("*"))
        {
        raiz.primeros=raiz.hijoizquierdo.primeros;
        }
         else if(raiz.datos.equals("|"))
        {
        raiz.primeros=raiz.hijoizquierdo.primeros+","+raiz.hijoderecho.primeros;
        }
         else if(raiz.datos.equals("."))
        {
            if(raiz.hijoizquierdo.anulable=='a')
            {
            raiz.primeros=raiz.hijoizquierdo.primeros+","+raiz.hijoderecho.primeros;
            }
            else
            {
            raiz.primeros=raiz.hijoizquierdo.primeros;
            }
        }
         else if(raiz.datos.equals("?"))
        {
        raiz.primeros=raiz.hijoizquierdo.primeros;
        }
         else if(raiz.datos.equals("+"))
        {
            raiz.primeros=raiz.hijoizquierdo.primeros;
        }
         else
        {
        raiz.primeros=String.valueOf(raiz.identificador);
        }
    }
      
            public void siguientes(arboldeexpresiones raiz)
    {
       
        if(raiz.hijoizquierdo!=null)
        {
            siguientes(raiz.hijoizquierdo);
        }
        if(raiz.hijoderecho!=null)
        {
            siguientes(raiz.hijoderecho);
        }
        
         if(raiz.datos.equals("*"))
        {
        raiz.siguientes=raiz.hijoizquierdo.siguientes;
        }
         else if(raiz.datos.equals("|"))
        {
        raiz.siguientes=raiz.hijoizquierdo.siguientes+","+raiz.hijoderecho.siguientes;
        }
         else if(raiz.datos.equals("."))
        {
            if(raiz.hijoderecho.anulable=='a')
            {
            raiz.siguientes=raiz.hijoizquierdo.siguientes+","+raiz.hijoderecho.siguientes;
            }
            else
            {
            raiz.siguientes=raiz.hijoderecho.siguientes;
            }
        }
         else if(raiz.datos.equals("?"))
        {
        raiz.siguientes=raiz.hijoizquierdo.siguientes;
        }
         else if(raiz.datos.equals("+"))
        {
            raiz.siguientes=raiz.hijoizquierdo.siguientes;
        }
         else
        {
        raiz.siguientes=String.valueOf(raiz.identificador);
        }
    }

ArrayList<nodolistaexpresiones> devolverexpre()
{
    return listaexpre;
    }
}
