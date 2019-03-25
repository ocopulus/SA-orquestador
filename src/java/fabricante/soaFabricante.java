/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabricante;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jlemu
 */
@WebService(serviceName = "soaFabricante")
public class soaFabricante {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "obtenerProductos")
    public String obtenerProductos(@WebParam(name = "algo") String algo) {
        int proc1 = (int) (Math.random()*1000) + 1;
        int proc2 = (int) (Math.random()*1000) + 1;
        int proc3 = (int) (Math.random()*1000) + 1;
        String result = String.format("[Producto%d, Producto%d, Producto%d]", proc1, proc2, proc3);
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProducto")
    public String getProducto(@WebParam(name = "codigo") String codigo) {
        String result = String.format("{name: 'Producto%s', cantidad: %d, imagen: 'path/%d.jpg'}", codigo, (int)(Math.random()*100+1),(int) (Math.random()*1000+1));
        return result;
    }
}
