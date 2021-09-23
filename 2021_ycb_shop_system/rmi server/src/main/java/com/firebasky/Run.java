package com.firebasky;

import java.io.PrintStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Run
{
    public static void main(String[] args)
    {
        try
        {
            ServerI server = new ServerImp();
            int port = Integer.parseInt("6666");//port
            String registry_name = "rmi";//rmi
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(registry_name, server);
            System.out.println("Service Start!\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
