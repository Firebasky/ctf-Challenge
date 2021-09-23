package com.firebasky;


import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface ServerI
        extends Remote
{
    public abstract String action(String paramString)
            throws RemoteException;
}
