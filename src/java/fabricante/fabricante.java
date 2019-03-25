/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabricante;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 *
 * @author jlemu
 */
@WebService(serviceName = "fabricante")
public class fabricante {
    
    private final static String QUEUE_NAME = "pedidos";
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "realizarPedido")
    public String realizarPedido(@WebParam(name = "codigoProducto") String codigoProducto, @WebParam(name = "nombreProducto") String nombreProducto, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "dirEnvio") String dirEnvio) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = codigoProducto+","+nombreProducto+","+cantidad+","+dirEnvio;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] SOA Sent '" + message + "'");
            }
            return "Pedido encolado";
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
            return "Error al ingresasPedido a la cola";
        }
    }
}
