package com.example.laba.rmi;

import java.rmi.RemoteException;

public interface Bluer
{
    byte[]  blurring(byte[] byteOfImage) throws RemoteException;
}
