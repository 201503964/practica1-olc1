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
public class arboldeexpresiones {
    
    String datos;
    arboldeexpresiones hijoderecho;
    arboldeexpresiones hijoizquierdo;
    arboldeexpresiones padre;
    char anulable;
    int identificador=0;
    int nografica;
    String primeros;
    String siguientes;
    arboldeexpresiones(String date,arboldeexpresiones hijode, arboldeexpresiones hijoiz)
    {
    datos=date;
    hijoderecho=hijode;
    hijoizquierdo=hijoiz;
    }
}
