package clue;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Opera {

    Nodo Lista = null;
    int numero_cartas;
    boolean asignado = false;

    public void insertar_al_final(int numero, String nombre, String icon1) {
        Nodo nuevo = new Nodo();
        Nodo puntero;
        puntero = Lista;
        ImageIcon imagen = new ImageIcon(icon1);
        Image scaled = imagen.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon nuevaimagen = new ImageIcon(scaled);
        nuevo.dato = numero;
        nuevo.nombre = nombre;
        nuevo.datoicon1 = nuevaimagen;
        nuevo.siguiente = null;
        if (Lista == null) {
            Lista = nuevo;
            numero_cartas++;
        } else {
            while (puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
            puntero.siguiente = nuevo;
            numero_cartas++;
        }
    }
    
   

    public void borrar(int numero) {
       Nodo puntero = Lista;
        if (Lista == null) {
            JOptionPane.showMessageDialog(null, "Lista vscia");
        } else {
            if (Lista.siguiente == null) {
                Lista = null;
                numero_cartas--;
            } else {
                if (Lista.dato == numero && Lista.siguiente != null) {
                    Lista = Lista.siguiente;
                    numero_cartas--;
                } else {
                    while (puntero.siguiente.dato != numero) {
                        puntero = puntero.siguiente;
                    }
                    if (puntero.siguiente.siguiente == null) {
                        puntero.siguiente = null;
                        numero_cartas--;
                    } else {
                        puntero.siguiente = puntero.siguiente.siguiente;
                        numero_cartas--;
                    }
                }
            }
        }
        
    }

    public void ver_listafinprinima() {
        Nodo puntero;
        puntero = Lista;
          while (puntero != null) {
            JOptionPane.showMessageDialog(null, puntero.datoicon1, "Mis cartas" + " " + puntero.nombre, 1);
           puntero = puntero.siguiente;
        }
    }

    public void  asignar_cartas(int numero_carta, Nodo ListaMazo) {Nodo nuevo = new Nodo();
        Nodo puntero;
        Nodo puntero2;
        puntero = ListaMazo;
        puntero2 = Lista;
        nuevo.siguiente = null;
        while (puntero != null) {
            if (puntero.dato == numero_carta) {
                if (Lista == null) {
                    Lista = nuevo;
                    nuevo.dato = puntero.dato;
                    nuevo.datoicon1 = puntero.datoicon1;
                    nuevo.nombre = puntero.nombre; 
                    numero_cartas++;
                } else {
                    while (puntero2.siguiente != null) {
                        puntero2 = puntero2.siguiente;
                    }
                    puntero2.siguiente = nuevo;
                    nuevo.dato = puntero.dato;
                    nuevo.datoicon1 = puntero.datoicon1;
                    nuevo.nombre = puntero.nombre;
                    numero_cartas++;
                }
            }
            puntero = puntero.siguiente;
        }
    }
    
    public int numero(){
        return numero_cartas;
    }
    
    
     public boolean asignado_metodo(){
        return asignado;
    }
     
      public void reacomodar() {
        Nodo puntero;
        puntero = Lista;
        int acomodar = 0;
          while (puntero != null) {
            puntero.dato=acomodar;
                    acomodar++;
            puntero = puntero.siguiente;
        }
    }
      
      
      
      public boolean buscar(String buscar) {
        Nodo puntero;
        puntero = Lista;
          while (puntero != null) {
              if(puntero.nombre.equals(buscar)){
                  return true;
              }else{
                   puntero = puntero.siguiente;
              }
          
        }
          return false;
          
    }

}
