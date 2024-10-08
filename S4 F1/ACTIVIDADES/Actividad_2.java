class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}


class CuentaNoEncontradaException extends Exception {
    public CuentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}


class SaldoNoCeroException extends Exception {
    public SaldoNoCeroException(String mensaje) {
        super(mensaje);
    }
}
//
public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;


    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }


    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
    }


    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro.");
        }
        saldo -= monto;
        System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
    }


    public void transferir(CuentaBancaria destino, double monto) throws SaldoInsuficienteException, CuentaNoEncontradaException {
        if (destino == null) {
            throw new CuentaNoEncontradaException("La cuenta destino no fue encontrada.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a transferir debe ser positivo.");
        }
        this.retirar(monto); // Verifica si hay suficiente saldo en la cuenta 
        destino.depositar(monto); // Transfiere el monto a la cuenta destino
        System.out.println("Transferencia de " + monto + " realizada de " + this.numeroCuenta + " a " + destino.numeroCuenta);
    }


    public void cerrarCuenta() throws SaldoNoCeroException {
        if (saldo != 0) {
            throw new SaldoNoCeroException("No se puede cerrar la cuenta. El saldo debe ser cero.");
        }
        System.out.println("La cuenta " + numeroCuenta + " ha sido cerrada.");
    }


    public double consultarSaldo() {
        return saldo;
    }


    public void mostrarInfo() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo actual: " + saldo);
    }
}


