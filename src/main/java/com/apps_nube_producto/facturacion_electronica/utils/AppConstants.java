package com.apps_nube_producto.facturacion_electronica.utils;

import java.math.BigDecimal;

public final class AppConstants {

        public static final String TOTAL_POSITIVO = "El total no puede ser negativo";
        public static final String CANTIDAD_NECESARIA = "La cantidad de productos no puede ser nula o vacía";
        public static final String CANTIDAD_MINIMA = "La cantidad de productos no puede ser menor a 1";
        public static final String DNI_RUC = "El DNI debe tener 8 dígitos o el RUC debe tener 11 dígitos";
        public final static String PRECIO_POSITIVO = "El precio tiene que ser un número positivo";
        public final static String PRECIO_NECESARIO = "El precio del producto es necesario";
        public final static String NOMBRE_NECESARIO = "El nombre no puede estar vacío";
        public final static String NOMBRE_TAMAÑO = "El nombre del producto debe tener entre 3 y 25 caracteres";
        public final static BigDecimal IGV = new BigDecimal("0.18");
        public final static String DOCUMENTO_INVALIDO = "DNI o RUC debe ser de 8 o 11 dígitos respectivamente";


        private AppConstants() {
        }

}
