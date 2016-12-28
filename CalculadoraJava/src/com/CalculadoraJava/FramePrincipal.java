package com.CalculadoraJava;

import javax.swing.JFrame;

//Ventana principal, le damos dimensiones y anadimos la lamina de la calculadora

public class FramePrincipal extends JFrame{
	
	private static final long serialVersionUID = 1L;

public FramePrincipal(){
		
		setTitle("Calculadora");
		
		setBounds(900,100,330,430);
		
		Calculadora miPantalla=new Calculadora();
		
		add(miPantalla);
		
		
	}

}
