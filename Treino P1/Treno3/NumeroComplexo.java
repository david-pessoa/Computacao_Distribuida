package Treno3;
import java.io.Serializable;

public class NumeroComplexo implements Serializable
{
    private static long serialVersionUID = 1L;
    private float imaginaria;
    private float real;

    public NumeroComplexo(float real, float imaginaria)
    {
        this.real = real;
        this.imaginaria = imaginaria;
    }

    public NumeroComplexo()
    {
        this(0, 0);
    }

    public float getImaginaria() {
        return imaginaria;
    }

    public void setImaginaria(float imaginaria) {
        this.imaginaria = imaginaria;
    }

    public float getReal() {
        return real;
    }

    public void setReal(float real) {
        this.real = real;
    }

    @Override
    public String toString()
    {
        if(this.imaginaria < 0)
            return this.real + " " +this.imaginaria + "i";

        else if (this.imaginaria == 0)
            return String.format("%f", this.real);
        
        else
            return this.real + " + " + this.imaginaria + "i";
    }
}
