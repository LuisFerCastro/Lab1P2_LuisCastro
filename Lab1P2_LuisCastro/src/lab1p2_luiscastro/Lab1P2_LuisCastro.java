/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab1p2_luiscastro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lfern
 */
public class Lab1P2_LuisCastro {
static Scanner leer = new Scanner(System.in);
static Scanner leerS = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
      ArrayList<usuario>usuarios = new ArrayList<>();
      
        System.out.println("*****Menu*****");
        System.out.println("1. Registrar usuario.");
        System.out.println("2. Listar todo.");
        System.out.println("3. Listar por dominio.");
        System.out.println("4. Salir del programa.");
        System.out.println("Escoja una opcion:");
        int opcion = leer.nextInt();
        if(opcion > 4 || opcion < 1){
            System.out.println("Opcion invalidad. Escoja de nuevo.");
            opcion = leer.nextInt();
        }
        while(opcion != 4){
            switch(opcion){
                case 1:
                    System.out.println("Bienvenido al ingreso de usuarios.");
                    //Pedimos el nombre
                    System.out.println("Ingrese su primer nombre:");
                    String nombre = leerS.nextLine();
                    
                    // Pedimos el apellido
                    System.out.println("Ingrese su apellido:");
                    String apellido = leerS.nextLine();
                    
                    //Pedimos la fecha de nacimiento
                    System.out.println("Ingrese su fecha de nacimiento en formato dd/MM/yyyy: ");
                    String nacimiento = leerS.nextLine();
                     
                   
                    //Verificamos si es mayor de 13 
                    boolean verificar = ver_edad(nacimiento);
                    
                    if(verificar == false){
                        System.out.println("Eres menor de 13. Devolviendo al menu.");
                        break;
                    }
                    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = sf.parse(nacimiento);
                    
                    
                    System.out.println("Ingrese su correo electronico.");
                    String correo_electronico = leerS.nextLine();
                    
                    String nombre_correo = retornar_nombre(correo_electronico);
                    String dominio_correo = retornar_dominio(correo_electronico);
                    
                    int cant_nombres_repetidos = verificar_nombre(dominio_correo, nombre_correo,usuarios);
                    boolean requisitos = Matching(correo_electronico);
                    boolean dominio_existente = verificar_dominios(dominio_correo);
                    while(requisitos == false||dominio_existente == false || cant_nombres_repetidos > 0){
                        System.out.println("Su correo no ha cunplido los requisitos, no existe el dominio,o ya hay un nombre existente, intente de nuevo:");
                         System.out.println("Los disponibles son gmail.com,outlook.com, yahoo.com, icloud.com, protonmail.com, y fastmail.com");
                        correo_electronico = leerS.nextLine();
                        nombre_correo = retornar_nombre(correo_electronico);
                        dominio_correo = retornar_dominio(correo_electronico);
                        requisitos = Matching(correo_electronico);
                        dominio_existente = verificar_dominios(dominio_correo);
                    }
                    
                    System.out.println("Ingrese su contraseña: ");
                    String contra = leerS.nextLine();
                    
                    boolean verificar_contra = verificar_contra(contra);
                    while(verificar_contra == false){
                        System.out.println("Su contra no tiene los requisitos, vuelva a escribirla: ");
                        contra = leerS.nextLine();
                        verificar_contra = verificar_contra(contra);
                    }
                    
                    String union = nombre_correo+"@"+dominio_correo;
                    String correo = union;
                    usuario user = new usuario(nombre, apellido, fecha , nombre_correo, dominio_correo, contra, correo);
                    usuarios.add(user);
                    break;

                case 2:
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
                    }
                    break;
                case 3:
                    imprimirporOrden(usuarios);
                break;
            }//final del switch
            System.out.println("*****Menu*****");
            System.out.println("1. Registrar usuario.");
            System.out.println("2. Listar todo.");
            System.out.println("3. Listar por dominio.");
            System.out.println("4. Salir del programa.");
            System.out.println("Escoga una opcion:");
            opcion = leer.nextInt();
            if(opcion > 4 || opcion < 1){
                System.out.println("Opcion invalidad. Escoja de nuevo.");
                opcion = leer.nextInt();
                }
            } //final del while 
        }
   
    public static boolean ver_edad(String nacimiento){
        String[] edad = nacimiento.split("/");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha_actual = new Date();
        String fecha_ac = sdf.format(fecha_actual);
        String fecha[] = fecha_ac.split("/");
        boolean meses = false;
        if(Integer.parseInt(edad[2]) < Integer.parseInt(fecha[2])-13){
            return true;
        }else if(Integer.parseInt(edad[1]) < Integer.parseInt(fecha[1])){
            meses = true;
            return true;
        }else if (meses == true && Integer.parseInt(edad[0])< Integer.parseInt(fecha[0])){
            return true;
        }else{
            return false;
        }
        
    }
    public static boolean Matching(String cadena){
        String regex = "^[a-zA-Z0-9._&$%]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,}$";
        Pattern patron = Pattern.compile(regex);
        Matcher m = patron.matcher(cadena);
        return m.matches();
    }
    
    public static String retornar_dominio(String cadena){
        String separar[] = cadena.split("@");
       
        String dominio = separar[1];
        return dominio;
    }
    
    public static String retornar_nombre(String cadena){
        String separar[] = cadena.split("@");
        
        String nombre = separar[0];
        return nombre;
    }
    public static boolean verificar_dominios(String cadena){
        boolean dominio_existente = false;
        
        if(cadena.equals("gmail.com")){
            dominio_existente = true;
            return dominio_existente;
        }else if(cadena.equals("outlook.com")){
            dominio_existente = true;
            return dominio_existente;
        }else if(cadena.equals("yahoo.com")){
            dominio_existente = true;
            return dominio_existente;
        }else if(cadena.equals("icloud.com")){
            dominio_existente = true;
            return dominio_existente;
        }else if(cadena.equals("protonmail.com")){
            dominio_existente = true;
            return dominio_existente;
        }else if(cadena.equals("fastmail.com")){
            dominio_existente = true;
            return dominio_existente;
        }else{
            dominio_existente = false;
            return dominio_existente;
        }
        
    }
    public static boolean verificar_contra(String cadena){
        String regex = "^[a-zA-Z0-9._%&$+-?<>!]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
    public static int verificar_nombre(String dominio,String nombre, ArrayList<usuario> usuarios){
        int cont = 0;
        if(usuarios.isEmpty()){
            cont = 0;
            return cont;
        }else{
            for(int i = 0; i < usuarios.size()-1;i++){
                if(usuarios.get(i).getDominio_correo().equals(dominio) && usuarios.get(i).getNombre_correo().equals(nombre)){
                    cont++;
                }
            }
            return cont;
        }

    }
    
    public static void imprimirporOrden(ArrayList<usuario>usuarios){
        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("gmail.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra()); 
            }
        }

        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("yahoo.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
            }
        }

        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("outlook.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
            }
        }
        System.out.println("");
        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("icloud.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
            }
        }

        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("protonmail.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
            }
        }

        for(int i = 0; i < usuarios.size()-1;i++){
            if(usuarios.get(i).getDominio_correo().equals("fastmail.com")){
                System.out.println("Nombre: "+usuarios.get(i).getNombre() + "\nApellido: "+usuarios.get(i).getApellido()+"\nFecha de nacimiento :"+usuarios.get(i).getFecha()+" \nCorreo: "+usuarios.get(i).getCorreo() + "\nContraseña: "+usuarios.get(i).getContra());
            }
        }

    }
    
}
