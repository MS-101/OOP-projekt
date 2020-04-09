package Enviroments;

import MySystem.AccountsFileHandler;

import java.io.Serializable;

public class Location implements Serializable {
    AccountsFileHandler myAccountsFileHandler = new AccountsFileHandler();
    Command myCommand = new Command();
}
