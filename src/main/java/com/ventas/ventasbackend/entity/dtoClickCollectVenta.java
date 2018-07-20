package com.ventas.ventasbackend.entity;

import java.math.BigInteger;

public class dtoClickCollectVenta {

    private int fechaActual;
    private BigInteger ingresoNetoActual;
    private int fechaAnterior;
    private BigInteger ingresoNetoAnterior;

    public int getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(int fechaActual) {
        this.fechaActual = fechaActual;
    }

    public BigInteger getIngresoNetoActual() {
        return ingresoNetoActual;
    }

    public void setIngresoNetoActual(BigInteger ingresoNetoActual) {
        this.ingresoNetoActual = ingresoNetoActual;
    }

    public int getFechaAnterior() {
        return fechaAnterior;
    }

    public void setFechaAnterior(int fechaAnterior) {
        this.fechaAnterior = fechaAnterior;
    }

    public BigInteger getIngresoNetoAnterior() {
        return ingresoNetoAnterior;
    }

    public void setIngresoNetoAnterior(BigInteger ingresoNetoAnterior) {
        this.ingresoNetoAnterior = ingresoNetoAnterior;
    }
}
