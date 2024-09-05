/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fraccion;

/**
 *
 * @author Timoteo
 */
public class ErrorFraccionImposible extends RuntimeException {

    private String mensaje;

    public ErrorFraccionImposible(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

}
