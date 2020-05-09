package MySystem;

import java.io.Serializable;

/**
 * This class is stored in account hashtable.
 * It is a linked list of accounts.
 */

public class AccountNode implements Serializable {
    Account curAccount = null;
    AccountNode nextAccount = null;
}
