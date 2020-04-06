package Enviroments;

import MySystem.AccountHandler;

import java.io.Serializable;

public class Location implements Serializable {
    AccountHandler myAccountHandler = new AccountHandler();
    Command myCommand = new Command();
}
