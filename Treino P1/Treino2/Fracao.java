package Treino2;

import java.io.Serializable;

public class Fracao implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int numerador;
    public int denominador;

    public Fracao(int numerador, int denominador)
    {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public Fracao()
    {
        this.numerador = 0;
        this.denominador = 1;
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    private static int mdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    public void simplificar()
    {
        int maximo_divisor = mdc(this.numerador, this.denominador);
        if(maximo_divisor != 1)
        {
            this.numerador /= maximo_divisor;
            this.denominador /= maximo_divisor;
        }
    }

    @Override
    public String toString()
    {
        return this.numerador + " / " + this.denominador;
    }
}
