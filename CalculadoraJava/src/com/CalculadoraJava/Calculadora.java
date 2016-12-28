package com.CalculadoraJava;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

//lamina JPanel calculadora, se encargara de mostrar los componentes y realizar las operaciones.

public class Calculadora extends JPanel{

	private static final long serialVersionUID = 1L;
		
	public Calculadora(){
			
		setLayout(new BorderLayout()); //difinimos layout de la pantalla
		
		pantalla= new JButton("0");//
		
		pantalla.setEnabled(false);    //usamos un boton desabilitado de pantalla
		
		add(pantalla,BorderLayout.NORTH);	//
		
		//creamos 2º lamina que tendra los botones 
		
		laminaBotones = new JPanel();
		
		laminaBotones.setLayout(new GridLayout(4,5));
		
		add(laminaBotones,BorderLayout.CENTER);
		
		insertarNumeros eventoNumero = new insertarNumeros();
		insertarOperacion eventoOperacion = new insertarOperacion();
		
		insertarBoton("7",eventoNumero);
		insertarBoton("8",eventoNumero);
		insertarBoton("9",eventoNumero);
		insertarBoton("/",eventoOperacion);
		insertarBoton("C",eventoNumero);
		
		insertarBoton("4",eventoNumero);
		insertarBoton("5",eventoNumero);
		insertarBoton("6",eventoNumero);
		insertarBoton("*",eventoOperacion); 
		insertarBoton("COS",eventoOperacion);
		
		insertarBoton("1",eventoNumero);
		insertarBoton("2",eventoNumero);
		insertarBoton("3",eventoNumero);
		insertarBoton("-",eventoOperacion);
		insertarBoton("TAN",eventoOperacion);
		
		insertarBoton("0",eventoNumero);
		insertarBoton(".",eventoNumero);
		insertarBoton("+",eventoOperacion);
		insertarBoton("√",eventoOperacion);
		insertarBoton("=",eventoOperacion);
		
		inicio=true; //inicio del programa
		ultimaOperacion="="; // la iniciamos a = para realizar la primera operacion y mostar el resultado en caso de ser true			

	};
	
	//metodo para insertar btones y ponerlos a la escucha del evento
	
	private void insertarBoton (String nombre, ActionListener oyente){
		
		JButton boton= new JButton(nombre);
		
		boton.addActionListener(oyente); //ponemos a la escucha 
		
		laminaBotones.add(boton);
		
	};
	
	//clase interna de eventos numericos
	
	private class insertarNumeros implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String entrada = e.getActionCommand(); // capturamos la entrada
			
			if(inicio){ //comprobamos si es la 1º vez que se inicia, si es asi borramos la pantalla cuando se pulse un boton
				
				pantalla.setText("");
				
				inicio=false;
			}
			
			if(entrada.equals("C")){pantalla.setText("0");inicio=true;}//si se pulsa c booramos y ponemos inicio a true
			
			else{pantalla.setText(pantalla.getText() + entrada);} //insertamos valor
			
		}
		
	}
		
		//clase interna de eventos de operacionesç
		
	private class insertarOperacion implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				operacion= e.getActionCommand();
				
				calcular(Double.parseDouble(pantalla.getText()));
				
				ultimaOperacion=operacion; //almacenamos la ultima peracion realizada
				
				inicio=true; //reseteamos pantalla
				
			}
			
		}
		
		//metodo encargado de realizar las operaciones
		private void calcular(double x){
			
			if(ultimaOperacion.equals("+")){resultado+=x;}
			
			else if(ultimaOperacion.equals("-")){resultado-=x;}
			
			else if(ultimaOperacion.equals("/")){resultado/=x;}
			
			else if(ultimaOperacion.equals("*")){resultado*=x;}
			
			else if(operacion.equals("COS")){resultado=Math.cos(x);operacion="=";}//reiniciamos la variable operacion 
			
			else if(operacion.equals("TAN")){resultado=Math.tan(x);operacion="=";}
			
			else if(operacion.equals("√")){resultado=Math.sqrt(x);operacion="=";}
			
			//como hemos declarado ultima operacion como "=" esta debe de ser la ultima intruccion  if
			else if(ultimaOperacion.equals("=")){resultado=x;}
			
			pantalla.setText(resultado + ""); //hacemos casting a string concatenando
		}
		
	
	
	private JPanel laminaBotones;
	JButton pantalla;
	boolean inicio;
	String operacion;
	private double resultado;
	private String ultimaOperacion; //almacenamos la ultima opracion para saber si es un = y mostrar el resultado

}
